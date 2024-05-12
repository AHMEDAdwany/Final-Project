package gucci.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gucci.store.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
