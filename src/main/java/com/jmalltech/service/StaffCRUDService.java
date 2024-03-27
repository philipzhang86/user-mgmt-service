package com.jmalltech.service;

import com.jmalltech.entity.Staff;
import com.jmalltech.mapper.StaffMapper;
import com.jmalltech.repository.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffCRUDService {
    private StaffService service;
    private StaffMapper mapper;

    @Autowired
    public StaffCRUDService(StaffService service, StaffMapper mapper) {
        this.service = service;
        this.mapper = mapper;
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
    public Staff insertStaff(Staff staff) {
        service.save(staff);
        return staff;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "staffList", allEntries = true)},
            put = {
                    @CachePut(value = "staff", key = "#staff.id", unless = "#result == null"),
                    @CachePut(value = "staff", key = "#staff.username", unless = "#result == null")})
    public Staff updateStaff(Staff staff) {
        service.updateById(staff);
        return staff;
    }

    @Caching(evict = {
            @CacheEvict(value = "staff", key = "#id"), // 删除单个staff的缓存
            @CacheEvict(value = "staffList", allEntries = true) // 使staff列表缓存失效
    })
    public boolean removeStaff(Long id) {
        return service.removeById(id);
    }

    @Cacheable(value = "staffList", unless = "#result == null || #result.isEmpty()")
    public List<Staff> getStaffList() {
        return service.list();
    }
}

