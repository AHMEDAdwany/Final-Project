package gucci.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gucci.store.controller.model.GucciStoreCustomer;
import gucci.store.controller.model.GucciStoreData;
import gucci.store.controller.model.GucciStoreEmployee;
import gucci.store.service.GucciStoreService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GucciStoreController {

    @Autowired
    private GucciStoreService gucciStoreService;

    @PostMapping("/gucci_store")
    @ResponseStatus(code = HttpStatus.CREATED)
    public GucciStoreData createGucciStore(@RequestBody GucciStoreData gucciStoreData) {
     
    	log.info("Creating Gucci Store {}", gucciStoreData);
        return gucciStoreService.saveGucciStore(gucciStoreData);
   
    }

    @PutMapping("/gucci_store/{gucciStoreId}")
    public GucciStoreData updateGucciStore(@PathVariable Long gucciStoreId, @RequestBody GucciStoreData gucciStoreData) {
      
    	gucciStoreData.setGucciStoreId(gucciStoreId);
        log.info("Updating Gucci Store {}", gucciStoreData);
        return gucciStoreService.saveGucciStore(gucciStoreData);
  
    }

    @DeleteMapping("/gucci_store")
    public void deleteAllGucciStores()
    {
        log.info("Attempting to delete all Gucci stores");
        throw new UnsupportedOperationException("Deleting all Gucci stores is not allowed");
    }

    @DeleteMapping("/gucci_store/{gucciStoreId}")
    public Map<String, String> deleteGucciStore(@PathVariable Long gucciStoreId) {
       
    	log.info("Deleting location with ID =" + gucciStoreId + ".");
        
        gucciStoreService.deleteGucciStoreById(gucciStoreId);
        
        return Map.of("message", "Store with ID=" + gucciStoreId + " was deleted successfully");
    }

    @Autowired
    public GucciStoreController(GucciStoreService gucciStoreService) {
        this.gucciStoreService = gucciStoreService;
    }

    @PostMapping("/gucci_store/{gucciStoreId}/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public GucciStoreEmployee addEmployeeToGucciStore(@RequestBody GucciStoreEmployee employee, @PathVariable Long gucciStoreId) {
        log.info("Adding employee to Gucci store with ID {}: {}", gucciStoreId, employee);
        return gucciStoreService.saveEmployee(gucciStoreId, employee);
    }

    @GetMapping("/gucci_store")
    public List<GucciStoreData> retrieveAllGucciStores() {
        log.info("Retrieving all Gucci Stores");
        return gucciStoreService.retreiveAllGucciStores();
    }

    @RestController
    @RequestMapping("/gucci_store")
    public class CustomerController {

        @Autowired
        private GucciStoreService gucciStoreService;

        @PostMapping("/{gucciStoreId}/customer")
        @ResponseStatus(HttpStatus.CREATED)
        public GucciStoreCustomer addCustomerToGucciStore(@PathVariable Long gucciStoreId, @RequestBody GucciStoreCustomer customer) {
            log.info("Customer details: {}", customer.toString());
            return gucciStoreService.saveCustomer(customer, gucciStoreId);
        }
    }

    @GetMapping("/gucci_store/{gucciStoreId}/")
    public GucciStoreData retrieveGucciStoreById(@PathVariable Long gucciStoreId) {
        log.info("Retrieving Gucci Store with ID = {}", gucciStoreId);
        return gucciStoreService.retrieveGucciStoreById(gucciStoreId);
    }
}