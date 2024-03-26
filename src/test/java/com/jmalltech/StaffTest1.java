package com.jmalltech;

import com.jmalltech.entity.Staff;
import com.jmalltech.entity.StaffRole;
import com.jmalltech.service.impl.StaffServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StaffTest1 {
    @Autowired
    private StaffServiceImpl staffService;
    @Test
    public void test1() {
        Staff s = new Staff();
        s.setRole(StaffRole.ADMIN);
        s.setUsername("admin1");
        s.setPassword("123456");
        s.setEmail("admin1@jmalltech.com");
        staffService.save(s);
        System.out.println(staffService.getById(1L));
    }

    @Test
    public void test2(){
        Staff s = staffService.getById(1L);
        s.setEmail("admin1Test@jmalltech.com");
        staffService.updateById(s);
        System.out.println(staffService.getById(1L));
    }


    @Test
    public void test3(){
        Staff s = new Staff();
        s.setRole(StaffRole.OPERATOR);
        s.setUsername("operator1");
        s.setPassword("123456");
        s.setEmail("test2@jm.com");
        staffService.save(s);
        System.out.println(staffService.getById(2L));
        System.out.println(staffService.removeById(2L));
    }
}
