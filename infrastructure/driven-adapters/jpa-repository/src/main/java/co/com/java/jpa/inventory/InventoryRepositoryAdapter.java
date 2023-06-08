package co.com.java.jpa.inventory;

import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.jpa.product.ProductEntityData;
import co.com.java.jpa.product.ProductEntityDataRepository;
import co.com.java.model.inventory.Inventory;
import co.com.java.model.product.Product;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepositoryAdapter extends AdapterOperations<Inventory, InventoryEntityData, Integer, InventoryEntityDataRepository> {
    public InventoryRepositoryAdapter(InventoryEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Inventory.class/* change for domain model */));
    }
}
