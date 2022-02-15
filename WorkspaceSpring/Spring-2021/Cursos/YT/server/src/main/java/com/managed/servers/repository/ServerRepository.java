package com.managed.servers.repository;

import com.managed.servers.models.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
  
  Server findByIpAddress(String ipAddress);

}
