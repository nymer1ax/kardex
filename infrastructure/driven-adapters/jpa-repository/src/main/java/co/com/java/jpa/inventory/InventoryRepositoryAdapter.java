package co.com.java.jpa.inventory;

import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.jpa.product.ProductEntityData;
import co.com.java.jpa.product.ProductEntityDataRepository;
import co.com.java.model.inventory.Inventory;
import co.com.java.model.inventory.gateways.InventoryRepository;
import co.com.java.model.product.Product;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InventoryRepositoryAdapter extends AdapterOperations<Inventory, InventoryEntityData, Integer, InventoryEntityDataRepository> implements InventoryRepository {
    public InventoryRepositoryAdapter(InventoryEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Inventory.class/* change for domain model */));
    }

    @Override
    public Optional<Inventory> findByProduct(Product product) {
        Optional<InventoryEntityData> a = repository.findByProduct(product);
        if(a.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(toEntity(a.get()));
    }

    @Override
    public void saveInventory(Inventory inventory) {
        InventoryEntityData i = toData(inventory);
        repository.save(i);
    }

    @Override
    public List<Inventory> getAllInventory() {
        return repository.findAll().stream().map(o -> toEntity(o)).collect(Collectors.toList());
    }
}
