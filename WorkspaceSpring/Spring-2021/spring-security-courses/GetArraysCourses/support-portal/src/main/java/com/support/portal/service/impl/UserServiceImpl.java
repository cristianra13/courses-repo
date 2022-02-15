package com.support.portal.service.impl;

import com.support.portal.domain.User;
import com.support.portal.domain.UserPrincipal;
import com.support.portal.enumeration.Role;
import com.support.portal.exception.domain.*;
import com.support.portal.repository.IUserRepository;
import com.support.portal.service.IUserService;
import com.support.portal.service.LoginAttemptService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.support.portal.constant.FileConstant.*;
import static com.support.portal.constant.UserImplConstant.*;
import static com.support.portal.enumeration.Role.ROLE_USER;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.http.MediaType.*;

/**
 * Clase encargada del CRUD de User
 */

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements IUserService, UserDetailsService {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private IUserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private LoginAttemptService loginAttemptService;
    private EmailService emailService;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           LoginAttemptService loginAttemptService,
                           EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;
        this.emailService = emailService;
    }

    /**
     * Método encvargado de validar el usuario en BD
     *
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            LOGGER.error(USER_NOT_FOUND_BY_USERNAME + username);
            // loginAttemptService.addUserToLoginAttempCached(username);
            throw new UsernameNotFoundException(USER_NOT_FOUND_BY_USERNAME + username);
        } else {
            validateLoginAttempt(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info(RETURNING_FOUND_USER_BY_USERNAME + username);

            return userPrincipal;
        }
    }

    /**
     * Método encargado de validar si un usuario está bloqueado
     * y si no ha excedido el maximo de intentos permitidos para
     * login
     *
     * @param user
     * @throws ExecutionException
     */
    private void validateLoginAttempt(User user) {
        if (user.isNotLockedUser()) {
            if (loginAttemptService.hasExceededMaxAttempt(user.getUsername())) {
                user.setNotLockedUser(false);
            } else {
                user.setNotLockedUser(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCached(user.getUsername());
        }
    }

    /**
     * Método para registrar un un usuario
     *
     * @param firstName
     * @param lastName
     * @param username
     * @param email
     * @return User
     * @throws UserNotFoundException
     * @throws UsernameExistsException
     * @throws EmailExistsException
     */
    @Override
    public User register(String firstName, String lastName, String username, String email)
            throws UserNotFoundException,
            UsernameExistsException,
            EmailExistsException,
            MessagingException {
        validateNewUsernameAndEmail(EMPTY, username, email);
        User user = new User();
        user.setUserId(generateUserId());
        String password = generatePassword();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setJoinDate(new Date());
        user.setPassword(encodePassword(password));
        user.setActiveUser(true);
        user.setNotLockedUser(true);
        user.setRole(ROLE_USER.name());
        user.setAuthorities(ROLE_USER.getAuthorities());
        user.setProfileImageUrl(getTemporaryProfileImageUrl(username));
        userRepository.save(user);
        emailService.sendNewPasswordEmail(firstName, password, email);
        LOGGER.info("New user: " + password);

        return user;
    }

    /**
     * Metodo encargado de consultar todos los usuarios en BD
     *
     * @return
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Método encargado de buscar un usuario por username
     *
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Método encargado de buscar un usuario por email
     *
     * @param email
     * @return
     */
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Método encargado de agregar un usuario nuevo
     * una vez logueados en la aplicación.
     *
     * @param firstName
     * @param lastName
     * @param username
     * @param email
     * @param role
     * @param isNonLocked
     * @param isActive
     * @param profileImage
     * @return
     * @throws UserNotFoundException
     * @throws UsernameExistsException
     * @throws EmailExistsException
     * @throws IOException
     */
    @Override
    public User addNewUser(String firstName, String lastName, String username, String email,
                           String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage)
            throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, MessagingException, NotAnImageFileException {
        validateNewUsernameAndEmail(EMPTY, username, email);
        User user = new User();
        String password = generatePassword();
        user.setUserId(generateUserId());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setJoinDate(new Date());
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encodePassword(password));
        user.setActiveUser(isActive);
        user.setNotLockedUser(isNonLocked);
        user.setRole(getRoleEnumName(role).name());
        user.setAuthorities(getRoleEnumName(role).getAuthorities());
        user.setProfileImageUrl(getTemporaryProfileImageUrl(username));
        userRepository.save(user);
        saveProfileImage(user, profileImage);
        emailService.sendNewPasswordEmail(firstName, password, email);
        LOGGER.info("New password " + username + " - " + password);

        return user;
    }

    /**
     * Método encargado de actualizar un usuario
     *
     * @param currentUsername
     * @param newFirstName
     * @param newLastName
     * @param newUsername
     * @param newEmail
     * @param role
     * @param isNonLocked
     * @param isActive
     * @param profileImage
     * @return User
     * @throws UserNotFoundException
     * @throws UsernameExistsException
     * @throws EmailExistsException
     */
    @Override
    public User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername,
                           String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage)
            throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, NotAnImageFileException {
        User currentUser = validateNewUsernameAndEmail(currentUsername, newUsername, newEmail);
        currentUser.setFirstName(newFirstName);
        currentUser.setLastName(newLastName);
        currentUser.setJoinDate(new Date());
        currentUser.setUsername(newUsername);
        currentUser.setEmail(newEmail);
        currentUser.setActiveUser(isActive);
        currentUser.setNotLockedUser(isNonLocked);
        currentUser.setRole(getRoleEnumName(role).name());
        currentUser.setAuthorities(getRoleEnumName(role).getAuthorities());
        userRepository.save(currentUser);
        saveProfileImage(currentUser, profileImage);

        return currentUser;
    }

    /**
     * Método encargado de eliminar un usuario por id
     *
     * @param username
     */
    @Override
    public void deleteUser(String username) throws IOException {
        User user = userRepository.findByUsername(username);
        Path userFolder = Paths.get(USER_FOLDER + username).toAbsolutePath().normalize();
        FileUtils.deleteDirectory(new File(userFolder.toString()));
        userRepository.deleteById(user.getId());
    }

    /**
     * Método encargado de resetear el password de un usuario
     * consultandolo por email
     *
     * @param email
     * @throws MessagingException
     * @throws EmailNotFoundException
     */
    @Override
    public void resetPassword(String email) throws MessagingException, EmailNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EmailNotFoundException(USER_NOT_FOUND_BY_EMAIL + email);
        }
        String password = generatePassword();
        user.setPassword(encodePassword(password));
        userRepository.save(user);
        emailService.sendNewPasswordEmail(user.getFirstName(), password, user.getEmail());
    }

    @Override
    public User updateProfileImage(String username, MultipartFile profileImage)
            throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, NotAnImageFileException {
        User user = validateNewUsernameAndEmail(username, null, null);
        saveProfileImage(user, profileImage);

        return user;
    }

    /**
     * Metodo encargado de validar la existencia
     * de un usuario o un email
     *
     * @param currentUsername
     * @param newUsername
     * @param newEmail
     * @return User
     * @throws UserNotFoundException
     * @throws UsernameExistsException
     * @throws EmailExistsException
     */
    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail)
            throws UserNotFoundException, UsernameExistsException, EmailExistsException {
        User userByNewUsername = this.findUserByUsername(newUsername);
        User userByNewEmail = this.findUserByEmail(newEmail);
        if (StringUtils.isNotBlank(currentUsername)) {
            User currentUser = this.findUserByUsername(currentUsername);
            if (currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if (userByNewUsername != null && !currentUser.getUserId().equals(userByNewUsername.getUserId())) {
                throw new UsernameExistsException(USERNAME_ALREADY_EXISTS);
            }
            if (userByNewEmail != null && !currentUser.getUserId().equals(userByNewEmail.getUserId())) {
                throw new EmailExistsException(EMAIL_ALREADY_EXISTS);
            }
            return currentUser;
        } else {
            if (userByNewUsername != null) {
                throw new UsernameExistsException(USERNAME_ALREADY_EXISTS);
            }
            if (userByNewEmail != null) {
                throw new EmailExistsException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }

    /**
     * Método encargado de obtener el path temporal de la imagen
     *
     * @param username
     * @return
     */
    private String getTemporaryProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
    }

    /**
     * Método encargado de encriptar el password del usuario
     *
     * @param password
     * @return
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Método encargado de generar un password aleatorio
     *
     * @return
     */
    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    /**
     * Método encargado de guardar la imagen del usuario
     *
     * @param user
     * @param profileImage
     * @throws IOException
     */
    private void saveProfileImage(User user, MultipartFile profileImage) throws IOException, NotAnImageFileException {
        if (profileImage != null) {
            if (!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(profileImage.getContentType())) {
                throw new NotAnImageFileException(profileImage.getOriginalFilename() + " is not an image file. Please upload an image");
            }
            Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                LOGGER.info(DIRECTORY_CREATED + userFolder);
            }
            Files.deleteIfExists(Paths.get(userFolder + user.getUsername() + DOT + JPG_EXTENSION));
            Files.copy(profileImage.getInputStream(), userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION), REPLACE_EXISTING);
            user.setProfileImageUrl(setProfileImageUrl(user.getUsername()));
            userRepository.save(user);
            LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
        }
    }

    /**
     * Método encaragdo de setear la imagen del usuario
     *
     * @param username
     * @return
     */
    private String setProfileImageUrl(String username) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(USER_IMAGE_PATH + username + FORWARD_SLASH + username + DOT + JPG_EXTENSION).toUriString();
    }

    /**
     * Método encaragado de obtener el rol del usuario
     *
     * @param role
     * @return
     */
    private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }

}
