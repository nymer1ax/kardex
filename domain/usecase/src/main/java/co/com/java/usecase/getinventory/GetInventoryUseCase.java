package co.com.java.usecase.getinventory;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.inventory.gateways.InventoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetInventoryUseCase {
    private final InventoryRepository inventoryRepository;

    List<Inventory> getAllInventory(){
        return inventoryRepository.getAllInventory();
    }
}
