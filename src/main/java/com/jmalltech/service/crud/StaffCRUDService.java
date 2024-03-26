package com.jmalltech.service.crud;

import com.jmalltech.entity.Staff;
import com.jmalltech.mapper.StaffMapper;
import com.jmalltech.repository.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StaffCRUDService {
    @Autowired
    private StaffService service;

    @Autowired
    private StaffMapper mapper;

    @Cacheable(value = "staff", key = "#id", unless = "#result == null")
    public Staff getById(Long id) {
        return service.getById(id);
    }

    @Cacheable(value = "staff", key = "#username", unless = "#result == null")
    public Staff getByUsername(String username) {
        return mapper.selectByUsername(username);
    }
}

/*@Autowired
    private StaffMapper mapper;

    @Autowired
    private StaffServiceImpl service;

    @Cacheable(value = "staff", key = "#id", unless = "#result == null")
    public Staff getById(Long id) {
        return mapper.selectById(id);
    }

    public Staff getByUsername(String username) {
        return mapper.selectBy
    }*/
