package com.security.app.domain.service.impl;

import com.security.app.domain.models.Role;
import com.security.app.domain.models.User;
import com.security.app.domain.service.UserService;
import com.security.app.repository.RoleRepository;
import com.security.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // buscamos el usuario
    User user = userRepository.findByUsername(username);
    if (user == null) {
      log.error("user not found in the database");
      throw new UsernameNotFoundException("user not found in the database");
    } else {
      log.info("User found in the database: {}", username);
    }
    /*
      Creamos una lista para almacenar los roles y los pasamos a userdetails
     */
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(),
        authorities);
  }

  @Override
  public User saveUser(User user) {
    log.info("Saving new user '{}' to database", user.getName());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    log.info("Saving new role '{}' to database", role);
    return roleRepository.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    log.info("Adding new role {} to an user", roleName);
    User user = userRepository.findByUsername(username);
    Role role = roleRepository.findByName(roleName);
    user.getRoles().add(role);
  }

  @Override
  public User getUser(String username) {
    log.info("Fetching user '{}' from database", username);
    return userRepository.findByUsername(username);
  }

  @Override
  public List<User> getAllUsers() {
    log.info("Fetching all users from database");
    return userRepository.findAll();
  }
}
