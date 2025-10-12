
package com.example.filmRental.Service;

import com.example.filmRental.Entity.Film;
import com.example.filmRental.Entity.Inventory;
import com.example.filmRental.Dto.RentalDto;
import com.example.filmRental.Entity.Customer;
import com.example.filmRental.Entity.Rental;
import com.example.filmRental.Entity.Staff;
import com.example.filmRental.Entity.Store;
import com.example.filmRental.Repository.CustomerRepository;
import com.example.filmRental.Repository.InventoryRepository;
import com.example.filmRental.Repository.RentalRepository;
import com.example.filmRental.Repository.StaffRepository;
import com.example.filmRental.Repository.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private StaffRepository staffRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public List<Rental> getRentalsByCustomerId(Long customerId) {
        return rentalRepository.findByCustomer_CustomerId(customerId);
    }

    public List<Rental> getDueRentals() {
        return rentalRepository.findByReturnDateIsNull();
    }

    public Rental saveRental(RentalDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        Store store = storeRepository.findById(dto.getStoreId())
            .orElseThrow(() -> new RuntimeException("Store not found"));

        List<Inventory> inventories = inventoryRepository.findByFilm_FilmIdAndStore_StoreId(dto.getFilmId(), dto.getStoreId());
        if (inventories.isEmpty()) {
            throw new RuntimeException("No inventory available for this film in the selected store");
        }

        Inventory inventory = inventories.get(0); // Pick the first available copy

        Staff staff = staffRepository.findFirstByStore_StoreId(dto.getStoreId())
            .orElseThrow(() -> new RuntimeException("No staff found for store ID: " + dto.getStoreId()));

        Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setInventory(inventory);
        rental.setRentalDate(dto.getRentalDate().atStartOfDay());
        rental.setReturnDate(dto.getReturnDate().atStartOfDay());
        rental.setStaff(staff);
        rental.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        return rentalRepository.save(rental);
    }

    public Rental updateReturnDate(Integer rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(() -> new RuntimeException("Rental not found"));
        rental.setReturnDate(LocalDateTime.now());
        rental.setLastUpdate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        return rentalRepository.save(rental);
    }

    public List<Customer> getDueCustomersByStore(Long storeId) {
        return rentalRepository.findDueCustomersByStore(storeId);
    }

    public List<Film> getTopTenFilmsByStore(Long storeId) {
        return rentalRepository.findTopTenFilmsByStore(storeId);
    }

    public List<Film> getFilmsRentedToCustomer(Long customerId) {
        return rentalRepository.findFilmsByCustomerId(customerId);
    }

    public List<Film> getTopTenFilmsOverall() {
        return rentalRepository.findTopTenFilmsOverall();
    }
}
