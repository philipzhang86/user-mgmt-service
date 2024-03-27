package com.jmalltech.service;

import com.jmalltech.entity.Client;
import com.jmalltech.mapper.ClientMapper;
import com.jmalltech.repository.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class ClientCRUDService {
    private ClientService service;
    private ClientMapper mapper;

    @Autowired
    public ClientCRUDService(ClientService service, ClientMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Cacheable(value = "client", key = "#id", unless = "#result == null")
    public Client getById(Long id) {
        return service.getById(id);
    }

    @Cacheable(value = "client", key = "#username", unless = "#result == null")
    public Client getByUsername(String username) {
        return mapper.selectByUsername(username);
    }

    @CachePut(value = "client", key = "#client.id", unless = "#result == null")
    public Client insert(Client client) {
        service.save(client);
        return client;
    }

    @Caching(
            put = {
                    @CachePut(value = "client", key = "#client.id", unless = "#result == null"),
                    @CachePut(value = "client", key = "#client.username", unless = "#result == null")
            }
    )
    public Client update(Client client) {
        service.updateById(client);
        return client;
    }
}
