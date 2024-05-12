package gucci.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class GucciStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gucciStoreId;

    private String gucciStoreName;
    private String gucciStoreAddress;
    private String gucciStoreCity;
    private String gucciStoreState;
    private String gucciStoreZip;
    private String gucciStorePhoneNumber;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "gucci_store_customer",
            joinColumns = @JoinColumn(name = "gucci_store_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customer> customers = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(
            mappedBy = "gucciStore",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Employee> employees = new HashSet<>();
}
