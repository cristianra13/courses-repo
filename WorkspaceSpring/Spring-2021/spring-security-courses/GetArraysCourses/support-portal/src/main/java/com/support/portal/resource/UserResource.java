package com.support.portal.resource;

import com.support.portal.domain.HttpResponse;
import com.support.portal.domain.User;
import com.support.portal.domain.UserPrincipal;
import com.support.portal.exception.domain.*;
import com.support.portal.service.IUserService;
import com.support.portal.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import static com.support.portal.constant.FileConstant.*;
import static com.support.portal.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping(value = {"/", "/user"})
public class UserResource extends ExceptionHandling {

    public static final String EMAIL_SENT = "An email with new password was sent to: ";
    public static final String USER_DELETED = "User deleted succesfully";
    private IUserService userService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider tokenProvider;

    @Autowired
    public UserResource(IUserService userService,
                        AuthenticationManager authenticationManager,
                        JWTTokenProvider tokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user)
            throws UserNotFoundException, UsernameExistsException, EmailExistsException, MessagingException {
        User newUser =
                userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());

        return new ResponseEntity<>(newUser, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("username") String username,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("activeUser") String activeUser,
                                           @RequestParam("notLockedUser") String notLockedUser,
                                           @RequestParam(value = "profileImage", required = false) MultipartFile profileImage)
            throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, MessagingException, NotAnImageFileException {
        User newUser =
                userService.addNewUser(firstName, lastName, username, email, role, Boolean.parseBoolean(notLockedUser), Boolean.parseBoolean(activeUser), profileImage);
        return new ResponseEntity<>(newUser, OK);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam("currentUsername") String currentUsername,
                                           @RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("username") String username,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("activeUser") String activeUser,
                                           @RequestParam("notLockedUser") String notLockedUser,
                                           @RequestParam(value = "profileImage", required = false) MultipartFile profileImage)
            throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, NotAnImageFileException {
        User updatedUser =
                userService.updateUser(currentUsername, firstName, lastName, username, email, role, Boolean.parseBoolean(notLockedUser), Boolean.parseBoolean(activeUser), profileImage);
        return new ResponseEntity<>(updatedUser, OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Collection<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, OK);
    }

    @GetMapping("/reset-password/{email}")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable String email) throws MessagingException, EmailNotFoundException {
        userService.resetPassword(email);
        return response(OK, EMAIL_SENT + email);
    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteUserr(@PathVariable String username) throws IOException {
        userService.deleteUser(username);
        // return response(NO_CONTENT, USER_DELETED);
        return response(OK, USER_DELETED);
    }

    @PostMapping("/update-profile-image")
    public ResponseEntity<User> updateUser(@RequestParam("username") String username,
                                           @RequestParam(value = "profileImage", required = false) MultipartFile profileImage)
            throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, NotAnImageFileException {
        User user = userService.updateProfileImage(username, profileImage);
        return new ResponseEntity<>(user, OK);
    }

    @GetMapping(path = "/image/{username}/{fileName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable String username, @PathVariable String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + fileName));
    }

    @GetMapping(path = "/image/profile/{username}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable String username) throws IOException {
        URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + username);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream inputStream = url.openStream()) {
            int bytesRead;
            byte[] chunk = new byte[1024];
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
        }

        return byteArrayOutputStream.toByteArray();
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, tokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(), message);
        return new ResponseEntity<>(body, status);
    }

    private void authenticate(String username, String password) {
        // Llama al método loadUserByUsername de UserServiceImpl
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
