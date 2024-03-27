package com.jmalltech.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jmalltech.entity.Client;
import com.jmalltech.mapper.ClientMapper;
import com.jmalltech.repository.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class ClientCRUDService {
    private ClientService service;
    private ClientMapper mapper;
    @Autowired
    private CacheManager cacheManager;

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

    @Caching(
            evict = {
                    @CacheEvict(value = "confidentialClientList", allEntries = true),
                    @CacheEvict(value = "clientList", allEntries = true)
            }
    )
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

    @Caching(
            evict = {
                    @CacheEvict(value = "confidentialClientList", allEntries = true),
                    @CacheEvict(value = "clientList", allEntries = true)
            }
    )
    public boolean remove(Long id, String username) {
        Client client = null;
        QueryWrapper<Client> qw = new QueryWrapper<>();
        qw = id != null ? qw.eq("id", id) : qw.eq("username", username);

        client = service.getOne(qw);

        if (client != null) {
            clearUserCache(client.getId(), client.getUsername());
            service.removeById(client.getId());
            return true;
        }
        return false;
    }

    private void clearUserCache(Long id, String username) {
        Objects.requireNonNull(cacheManager.getCache("client")).evict(id);
        Objects.requireNonNull(cacheManager.getCache("client")).evict(username);
    }

    @Cacheable(value = "confidentialClientList", unless = "#result == null || #result.isEmpty()")
    public List<Client> getClientListWithPassword() {
        return service.list();
    }

    @Cacheable(value = "clientList", unless = "#result == null || #result.isEmpty()")
    public List<Client> getClientListWithoutPassword() {
        return mapper.selectClientsWithoutPassword();
    }
}
