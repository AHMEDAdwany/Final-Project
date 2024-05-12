package gucci.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import gucci.store.entity.Customer;
import gucci.store.entity.Employee;
import gucci.store.entity.GucciStore;

@Data
@NoArgsConstructor
public class GucciStoreData {
    
    private Long gucciStoreId;
    private String gucciStoreName;
    private String gucciStoreAddress;
    private String gucciStoreCity;
    private String gucciStoreState;
    private String gucciStoreZip;
    private String gucciStorePhoneNumber;
    private Set<GucciStoreCustomer> customers = new HashSet<>();
    private Set<GucciStoreEmployee> employees = new HashSet<>();
    
    public GucciStoreData(GucciStore gucciStore) {
        gucciStoreId = gucciStore.getGucciStoreId();
        gucciStoreName = gucciStore.getGucciStoreName();
        gucciStoreAddress = gucciStore.getGucciStoreAddress();
        gucciStoreCity = gucciStore.getGucciStoreCity();
        gucciStoreState = gucciStore.getGucciStoreState();
        gucciStoreZip = gucciStore.getGucciStoreZip();
        gucciStorePhoneNumber = gucciStore.getGucciStorePhoneNumber();

        for (Customer customer : gucciStore.getCustomers()) {
            GucciStoreCustomer gucciStoreCustomer = new GucciStoreCustomer(customer);
            customers.add(gucciStoreCustomer);
        }
        for (Employee employee : gucciStore.getEmployees()) {
            GucciStoreEmployee gucciStoreEmployee = new GucciStoreEmployee(employee);
            employees.add(gucciStoreEmployee);
        }
    }
    
    @Data
    @NoArgsConstructor
    static class GucciStoreEmployee { 
        private Long employeeId;
        private String employeeFirstName;  
        private String employeeLastName;  
        private String employeeWorkPhone;
        private String employeeRole;
        
        public GucciStoreEmployee(Employee employee) {
            employeeId = employee.getEmployeeId();
            employeeFirstName = employee.getEmployeeFirstName();
            employeeLastName = employee.getEmployeeLastName();
            employeeWorkPhone = employee.getEmployeeWorkPhone();
            employeeRole = employee.getEmployeeRole();
        }
    }
    
    @Data
    @NoArgsConstructor
    static class GucciStoreCustomer {
        private Long customerId;
        private String customerFirstName;
        private String customerLastName;
        private String customerEmail;
        
        public GucciStoreCustomer(Customer customer) {
            customerId = customer.getCustomerId();
            customerFirstName = customer.getCustomerFirstName();
            customerLastName = customer.getCustomerLastName();
            customerEmail = customer.getCustomerEmail();
        }
    }
}