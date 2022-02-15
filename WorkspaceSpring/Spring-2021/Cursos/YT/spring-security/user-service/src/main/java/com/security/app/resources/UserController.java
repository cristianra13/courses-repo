package com.security.app.resources;

import com.security.app.domain.models.Role;
import com.security.app.domain.models.User;
import com.security.app.domain.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor // se debe crear los beans como final
public class UserController {

  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers() {
    userService.getAllUsers().forEach(System.out::println);
    return  ResponseEntity.ok(userService.getAllUsers());
  }

  @PostMapping("/user/save")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
    return  ResponseEntity.created(uri).body(userService.saveUser(user));
  }

  @PostMapping("/role/save")
  public ResponseEntity<Role> saveRole(@RequestBody Role role) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
    return  ResponseEntity.created(uri).body(userService.saveRole(role));
  }

  @PostMapping("/role/addtouser")
  public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
    userService.addRoleToUser(form.getUsername(), form.getUsername());
    return  ResponseEntity.ok().build();
  }

}

@Data
class RoleToUserForm {
  private String username;
  private String roleName;
}
