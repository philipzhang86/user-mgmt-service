package com.jmalltech;

import com.jmalltech.service.StaffCRUDService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StaffCRUDCacheTest {
    @Autowired
    private StaffCRUDService staffCRUDService;
    @Test
    public void test1(){
        /*Staff s = new Staff();
        s.setRole(StaffRole.OPERATOR);
        s.setUsername("operator1");
        s.setPassword("123456");
        s.setEmail("operator1@test.com");
        staffCRUDService.insertStaff(s);*/
        //Staff s = staffCRUDService.getByUsername("operator1");
        //s.setEmail("new_operator111@test.com");
        //System.out.println(staffCRUDService.updateStaff(s));
//        System.out.println(staffCRUDService.getByUsername("admin1"));
//        System.out.println(staffCRUDService.getById(1L));
        System.out.println(staffCRUDService.getStaffList());

    }
}
