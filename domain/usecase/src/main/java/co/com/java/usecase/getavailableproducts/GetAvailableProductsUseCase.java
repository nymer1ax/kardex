package co.com.java.usecase.getavailableproducts;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.inventory.gateways.InventoryRepository;
import co.com.java.model.product.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAvailableProductsUseCase {

    private final InventoryRepository inventoryRepository;

    public List<Product> getAvailableProducts(){
        return inventoryRepository.getAllInventory().stream().filter(i -> i.getQuantity() > 0).map(Inventory::getProduct).collect(Collectors.toList());
    }

}
