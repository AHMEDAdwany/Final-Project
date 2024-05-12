package gucci.store.controller.model;

import org.springframework.data.annotation.Id;

import gucci.store.entity.Employee;
import gucci.store.entity.GucciStore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@NoArgsConstructor 
public class GucciStoreEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    
    private String employeeFirstName;
    
    private String employeeLastName;
    
    private String employeeWorkPhone;
    
    private String employeeRole;
    
    public GucciStoreEmployee(Employee employee) {
        employeeFirstName = employee.getEmployeeFirstName();
        employeeLastName = employee.getEmployeeLastName();
        employeeWorkPhone = employee.getEmployeeWorkPhone();
        employeeRole = employee.getEmployeeRole();
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gucci_store_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private GucciStore gucciStore;
}
