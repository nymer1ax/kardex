package co.com.java.jpa.inventory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface InventoryEntityDataRepository extends CrudRepository<InventoryEntityData, Integer>, QueryByExampleExecutor<InventoryEntityData> {
}
