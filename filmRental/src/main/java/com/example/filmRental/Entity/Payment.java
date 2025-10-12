package com.example.filmRental.Entity;
 
import jakarta.persistence.*;
 
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
 
@Entity
@Table(name = "payment")
public class Payment {
 
    public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(Short paymentId, Customer customer, Staff staff, Rental rental, BigDecimal amount,
			LocalDateTime paymentDate, Timestamp lastUpdate) {
		super();
		this.paymentId = paymentId;
		this.customer = customer;
		this.staff = staff;
		this.rental = rental;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.lastUpdate = lastUpdate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short paymentId;
 
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
 
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
 
    public Short getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Short paymentId) {
		this.paymentId = paymentId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
 
    @Column(nullable = false)
    private BigDecimal amount;
 
    @Column(nullable = false)
    private LocalDateTime paymentDate;
 
    private Timestamp lastUpdate;
}