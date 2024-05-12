package gucci.store.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gucci.store.controller.model.GucciStoreCustomer;
import gucci.store.controller.model.GucciStoreData;
import gucci.store.controller.model.GucciStoreEmployee;
import gucci.store.dao.CustomerDao;
import gucci.store.dao.EmployeeDao;
import gucci.store.dao.GucciStoreDao;
import gucci.store.entity.Customer;
import gucci.store.entity.Employee;
import gucci.store.entity.GucciStore;

@Service
public class GucciStoreService {
    
    @Autowired    
    private GucciStoreDao gucciStoreDao;
    
    @Autowired 
    private EmployeeDao employeeDao;
    
    @Autowired 
    private CustomerDao customerDao;
    
    
    @Transactional(readOnly = false)
    public GucciStoreData saveGucciStore(GucciStoreData gucciStoreData) {
        GucciStore gucciStore = findOrCreateGucciStore(gucciStoreData.getGucciStoreId());

        copyGucciStoreFields(gucciStore, gucciStoreData);
        
        return new GucciStoreData(gucciStoreDao.save(gucciStore));
    }
    
      private void copyGucciStoreFields(GucciStore gucciStore, GucciStoreData gucciStoreData) {
          gucciStore.setGucciStoreName(gucciStoreData.getGucciStoreName());
          gucciStore.setGucciStoreAddress(gucciStoreData.getGucciStoreAddress());
          gucciStore.setGucciStoreCity(gucciStoreData.getGucciStoreCity());
          gucciStore.setGucciStoreId(gucciStoreData.getGucciStoreId());
          gucciStore.setGucciStoreZip(gucciStoreData.getGucciStoreZip());
          gucciStore.setGucciStorePhoneNumber(gucciStoreData.getGucciStorePhoneNumber());
      }

    private GucciStore findOrCreateGucciStore(Long gucciStoreId) {
        if (Objects.isNull(gucciStoreId)) {
            return new GucciStore(); 
        } else {
            return findGucciStoreById(gucciStoreId);
        }
    }

    
        @Transactional(readOnly = false)
        public GucciStoreData retrieveGucciStoreById(Long gucciStoreId) {
            GucciStore gucciStore = findGucciStoreById(gucciStoreId);
            return new GucciStoreData(gucciStore);
        }
    
        private GucciStore findGucciStoreById(Long gucciStoreId) {
            return gucciStoreDao.findById(gucciStoreId)
                    .orElseThrow(() -> new NoSuchElementException("Gucci Store with ID = "+ gucciStoreId + " was not found. "));
        }
        
        
    @Transactional(readOnly = false)
    public GucciStoreEmployee saveEmployee(Long gucciStoreId, GucciStoreEmployee gucciStoreEmployee) {
        
        GucciStore gucciStore = findGucciStoreById(gucciStoreId);
        Long employeeId = gucciStoreEmployee.getEmployeeId();
        Employee employee = findOrCreateEmployee(gucciStoreId, employeeId);
        
        copyEmployeeFields(employee, gucciStoreEmployee);
        employee.setGucciStore(gucciStore);
        gucciStore.getEmployees().add(employee);
        
        return new GucciStoreEmployee(employeeDao.save(employee));
    }
    
    public Employee findOrCreateEmployee(Long gucciStoreId, Long employeeId) {
        if (employeeId == null) {
            return new Employee();
        } else {
            return findEmployeeById(gucciStoreId, employeeId);
        }
    }
    
        @Transactional(readOnly = false)
        public Employee findEmployeeById(Long gucciStoreId, Long employeeId) {
            Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee not found"));
            if (!employee.getGucciStore().getGucciStoreId().equals(gucciStoreId)) {
                throw new IllegalArgumentException("Employee does not belong to the given Gucci store");
            }
            return employee;
        }
        
        public void copyEmployeeFields(Employee employee, GucciStoreEmployee gucciStoreEmployee) {
            employee.setEmployeeFirstName(gucciStoreEmployee.getEmployeeFirstName());
            employee.setEmployeeLastName(gucciStoreEmployee.getEmployeeLastName());
            employee.setEmployeeWorkPhone(gucciStoreEmployee.getEmployeeWorkPhone());
            employee.setEmployeeRole(gucciStoreEmployee.getEmployeeRole());
            
        }
        
       @Transactional(readOnly = false)
        public List<GucciStoreData> retreiveAllGucciStores(){
            
           return gucciStoreDao.findAll()
           .stream()
           .map(GucciStoreData::new)
           .toList();
      }


        @Transactional
        public void deleteGucciStoreById(Long gucciStoreId) {
            GucciStore gucciStore = findGucciStoreById(gucciStoreId);
            gucciStoreDao.delete(gucciStore);
        
                
    }
    
        @Transactional(readOnly = false)
        public GucciStoreCustomer saveCustomer(GucciStoreCustomer gucciStoreCustomer, Long gucciStoreId) {
            GucciStore gucciStore = findGucciStoreById(gucciStoreId);
            Long customerId = gucciStoreCustomer.getCustomerId();
            Customer customer = findOrCreateCustomer(customerId);
            
            copyCustomerFields(customer, gucciStoreCustomer);
            customer.getGucciStores().add(gucciStore); 
            gucciStore.getCustomers().add(customer);
            
            return new GucciStoreCustomer(customerDao.save(customer));
        }

        public Customer findOrCreateCustomer(Long customerId) {
            if (customerId == null) {
                return new Customer();
            } else {
                return findCustomerById(customerId);
            }
        }

        @Transactional(readOnly = false)
        public Customer findCustomerById(Long customerId) {
            return customerDao.findById(customerId)
                    .orElseThrow(() -> new NoSuchElementException("Customer with ID = " + customerId + " not found"));
        }

        public void copyCustomerFields(Customer customer, GucciStoreCustomer gucciStoreCustomer) {
            customer.setCustomerFirstName(gucciStoreCustomer.getCustomerFirstName());
            customer.setCustomerLastName(gucciStoreCustomer.getCustomerLastName());
            customer.setCustomerEmail(gucciStoreCustomer.getCustomerEmail());
        }
    
}