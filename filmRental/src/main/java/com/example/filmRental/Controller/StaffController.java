package com.example.filmRental.Controller;

import com.example.filmRental.Dto.StaffDto;
import com.example.filmRental.Entity.Staff;
import com.example.filmRental.Exception.CustomValidationException;
import com.example.filmRental.Exception.ResourceNotFoundException;
import com.example.filmRental.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/post")
    public ResponseEntity<String> addNewStaff(@RequestBody StaffDto staffDto) {
        if (staffDto.getFirstName() == null || staffDto.getLastName() == null || staffDto.getEmail() == null) {
            throw new CustomValidationException("First name, last name, and email are required.");
        }
        staffService.addStaff(staffDto);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<Staff>> searchByLastName(@PathVariable String ln) {
        List<Staff> staffList = staffService.getStaffByLastName(ln);
        if (staffList == null || staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found with last name: " + ln);
        }
        return ResponseEntity.ok(staffList);
    }

    @GetMapping("/firstname/{fn}")
    public ResponseEntity<List<Staff>> searchByFirstName(@PathVariable String fn) {
        List<Staff> staffList = staffService.getStaffByFirstName(fn);
        if (staffList == null || staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found with first name: " + fn);
        }
        return ResponseEntity.ok(staffList);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Staff> searchByEmail(@PathVariable String email) {
        Staff staff = staffService.getStaffByEmail(email);
        if (staff == null) {
            throw new ResourceNotFoundException("No staff found with email: " + email);
        }
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Staff> assignAddress(@PathVariable Byte id, @RequestParam Long addressId) {
        return ResponseEntity.ok(staffService.assignAddress(id, addressId));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Staff>> searchByCity(@PathVariable String city) {
        List<Staff> staffList = staffService.getStaffByCity(city);
        if (staffList == null || staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found in city: " + city);
        }
        return ResponseEntity.ok(staffList);
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Staff>> searchByCountry(@PathVariable String country) {
        List<Staff> staffList = staffService.getStaffByCountry(country);
        if (staffList == null || staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found in country: " + country);
        }
        return ResponseEntity.ok(staffList);
    }

    @PutMapping("/update/fn/{id}")
    public ResponseEntity<Staff> updateFirstName(@PathVariable Byte id, @RequestParam String firstName) {
        return ResponseEntity.ok(staffService.updateFirstName(id, firstName));
    }

    @PutMapping("/update/ln/{id}")
    public ResponseEntity<Staff> updateLastName(@PathVariable Byte id, @RequestParam String lastName) {
        return ResponseEntity.ok(staffService.updateLastName(id, lastName));
    }

    @PutMapping("/update/email/{id}")
    public ResponseEntity<Staff> updateEmail(@PathVariable Byte id, @RequestParam String email) {
        return ResponseEntity.ok(staffService.updateEmail(id, email));
    }

    @PutMapping("/update/store/{id}")
    public ResponseEntity<Staff> assignStore(@PathVariable Byte id, @RequestParam Long storeId) {
        return ResponseEntity.ok(staffService.assignStore(id, storeId));
    }
}