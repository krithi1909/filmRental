package com.example.filmRental;


import com.example.filmRental.Controller.StaffController;
import com.example.filmRental.Dto.StaffDto;
import com.example.filmRental.Entity.Staff;
import com.example.filmRental.Service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StaffControllerTest {

    @InjectMocks
    private StaffController staffController;

    @Mock
    private StaffService staffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewStaff() {
        StaffDto dto = new StaffDto();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john.doe@example.com");
        dto.setStoreId(1L);
        dto.setAddressId(2L); // if addressId is also required

        doNothing().when(staffService).addStaff(dto);

        ResponseEntity<String> response = staffController.addNewStaff(dto);
        assertEquals("Record Created Successfully", response.getBody());
    }

    @Test
    void testSearchByLastName() {
        when(staffService.getStaffByLastName("Doe")).thenReturn(List.of(new Staff()));
        ResponseEntity<List<Staff>> response = staffController.searchByLastName("Doe");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testSearchByFirstName() {
        when(staffService.getStaffByFirstName("John")).thenReturn(List.of(new Staff()));
        ResponseEntity<List<Staff>> response = staffController.searchByFirstName("John");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testSearchByEmail() {
        Staff staff = new Staff();
        when(staffService.getStaffByEmail("john.doe@example.com")).thenReturn(staff);
        ResponseEntity<Staff> response = staffController.searchByEmail("john.doe@example.com");
        assertEquals(staff, response.getBody());
    }

    @Test
    void testAssignAddress() {
        Staff staff = new Staff();
        when(staffService.assignAddress((byte) 1, 100L)).thenReturn(staff);
        ResponseEntity<Staff> response = staffController.assignAddress((byte) 1, 100L);
        assertEquals(staff, response.getBody());
    }

    @Test
    void testSearchByCity() {
        when(staffService.getStaffByCity("Chennai")).thenReturn(List.of(new Staff()));
        ResponseEntity<List<Staff>> response = staffController.searchByCity("Chennai");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testSearchByCountry() {
        when(staffService.getStaffByCountry("India")).thenReturn(List.of(new Staff()));
        ResponseEntity<List<Staff>> response = staffController.searchByCountry("India");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testUpdateFirstName() {
        Staff staff = new Staff();
        when(staffService.updateFirstName((byte) 1, "Jane")).thenReturn(staff);
        ResponseEntity<Staff> response = staffController.updateFirstName((byte) 1, "Jane");
        assertEquals(staff, response.getBody());
    }

    @Test
    void testUpdateLastName() {
        Staff staff = new Staff();
        when(staffService.updateLastName((byte) 1, "Smith")).thenReturn(staff);
        ResponseEntity<Staff> response = staffController.updateLastName((byte) 1, "Smith");
        assertEquals(staff, response.getBody());
    }

    @Test
    void testUpdateEmail() {
        Staff staff = new Staff();
        when(staffService.updateEmail((byte) 1, "jane.smith@example.com")).thenReturn(staff);
        ResponseEntity<Staff> response = staffController.updateEmail((byte) 1, "jane.smith@example.com");
        assertEquals(staff, response.getBody());
    }

    @Test
    void testAssignStore() {
        Staff staff = new Staff();
        when(staffService.assignStore((byte) 1, 2L)).thenReturn(staff);
        ResponseEntity<Staff> response = staffController.assignStore((byte) 1, 2L);
        assertEquals(staff, response.getBody());
    }
}

