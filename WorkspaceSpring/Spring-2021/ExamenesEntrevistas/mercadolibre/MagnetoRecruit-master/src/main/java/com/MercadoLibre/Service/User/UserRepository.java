/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MercadoLibre.Service.User;

import java.util.Optional;
import javax.persistence.Id;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Alejandro Greggio
 */
public interface UserRepository extends CrudRepository<User, Id> {
    
Optional<User>findByUsername(String username);

Optional<User> findById(int id);

}
