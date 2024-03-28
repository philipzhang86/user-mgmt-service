package com.jmalltech.controller;

import com.jmalltech.entity.Staff;
import com.jmalltech.service.StaffCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staffs")
public class StaffController {
    private StaffCRUDService staffService;
    @Autowired
    public StaffController(StaffCRUDService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Staff staff = staffService.getById(id);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Staff> getStaffByUsername(@PathVariable String username) {
        Staff staff = staffService.getByUsername(username);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Staff> insertStaff(@RequestBody Staff staff) {
        Staff createdStaff = staffService.insert(staff);
        return ResponseEntity.ok(createdStaff);
    }

    @PutMapping("/")
    public ResponseEntity<Staff> updateStaff(@RequestBody Staff staff) {
        Staff updatedStaff = staffService.update(staff);
        if (updatedStaff != null) {
            return ResponseEntity.ok(updatedStaff);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        boolean isRemoved = staffService.remove(id);
        if (isRemoved) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Staff>> getAllStaffs() {
        List<Staff> staffList = staffService.getStaffList();
        if (staffList != null && !staffList.isEmpty()) {
            return ResponseEntity.ok(staffList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
