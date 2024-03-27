package com.jmalltech.service;

import com.jmalltech.entity.Staff;
import com.jmalltech.mapper.StaffMapper;
import com.jmalltech.repository.StaffService;
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
public class StaffCRUDService {
    private StaffService service;
    private StaffMapper mapper;
    private CacheManager cacheManager;
    @Autowired
    public StaffCRUDService(StaffService service, StaffMapper mapper, CacheManager cacheManager) {
        this.service = service;
        this.mapper = mapper;
        this.cacheManager = cacheManager;
    }

    @Cacheable(value = "staff", key = "#id", unless = "#result == null")
    public Staff getById(Long id) {
        return service.getById(id);
    }

    @Cacheable(value = "staff", key = "#username", unless = "#result == null")
    public Staff getByUsername(String username) {
        return mapper.selectByUsername(username);
    }

    @CachePut(value = "staff", key = "#staff.id", unless = "#result == null")
    public Staff insert(Staff staff) {
        service.save(staff);
        return staff;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "staffList", allEntries = true)},
            put = {
                    @CachePut(value = "staff", key = "#staff.id", unless = "#result == null"),
                    @CachePut(value = "staff", key = "#staff.username", unless = "#result == null")})
    public Staff update(Staff staff) {
        service.updateById(staff);
        return staff;
    }

    @Caching(evict = {
            @CacheEvict(value = "staff", key = "#id"), // delete single staff cache by id
            @CacheEvict(value = "staffList", allEntries = true) // delete staff list cache simultaneously
    })
    public boolean remove(Long id) {
        Staff staff = service.getById(id);
        //if staff with username also exists, delete it. Ensure that the cache is consistent with the database.
        if(staff != null){
            Objects.requireNonNull(cacheManager.getCache("staff")).evict(staff.getUsername());
        }
        return service.removeById(id);
    }

    @Cacheable(value = "staffList", unless = "#result == null || #result.isEmpty()")
    public List<Staff> getStaffList() {
        return service.list();
    }
}

