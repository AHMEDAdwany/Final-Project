package gucci.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gucci.store.entity.GucciStore;

public interface GucciStoreDao extends JpaRepository<GucciStore, Long> {

}
