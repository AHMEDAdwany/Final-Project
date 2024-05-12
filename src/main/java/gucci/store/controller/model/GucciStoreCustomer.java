package gucci.store.controller.model;

import java.util.HashSet;
import java.util.Set;



import gucci.store.entity.Customer;
import gucci.store.entity.GucciStore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data 
@NoArgsConstructor
public class GucciStoreCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    
    private String customerFirstName;
    
    private String customerLastName;
    
    private String customerEmail;
    
    public GucciStoreCustomer(Customer customer) {
        customerFirstName = customer.getCustomerFirstName();
        customerLastName = customer.getCustomerLastName();
        customerEmail = customer.getCustomerEmail();
    }
    
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<GucciStore> gucciStores = new HashSet<>();
}


