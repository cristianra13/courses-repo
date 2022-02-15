package com.managed.servers.service;

import com.managed.servers.models.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {

  Server create(Server server);

  Server ping(String ipAddress) throws IOException;

  Collection<Server> list(int limit);

  Server get(Long id);

  Server update(Server server);

  boolean delete(Long id);

}
