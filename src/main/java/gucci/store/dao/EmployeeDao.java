package gucci.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gucci.store.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
