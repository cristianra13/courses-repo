package com.security.app.domain.service;

import com.security.app.domain.models.Role;
import com.security.app.domain.models.User;

import java.util.List;

public interface UserService {
  User saveUser(User user);
  Role saveRole(Role role);
  void addRoleToUser(String username, String roleName);
  User getUser(String username);
  List<User> getAllUsers();
}
