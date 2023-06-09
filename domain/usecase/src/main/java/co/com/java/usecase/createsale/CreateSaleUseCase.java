package co.com.java.usecase.createsale;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.inventory.gateways.InventoryRepository;
import co.com.java.model.movements.Movement;
import co.com.java.model.movements.gateways.MovementsRepository;
import co.com.java.model.movementtype.MovementType;
import co.com.java.model.product.Product;
import co.com.java.model.product.gateways.ProductRepository;
import co.com.java.model.sale.Sale;
import co.com.java.model.sale.gateways.SaleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class CreateSaleUseCase {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final MovementsRepository movementsRepository;
    private final SaleRepository saleRepository;

    public void createSale(Sale sale){
        List<Product> products = sale.getProducts();
        List<Integer> quantities = sale.getQuantities();
        List<Inventory> inventories = inventoryRepository.getAllInventory();

        List<Movement> movements = IntStream.range(0, products.size()).mapToObj(i ->
        {
           Product product = products.get(i);
           Integer quantity = quantities.get(i);
           Inventory existenceInventory = inventories.stream().filter(o -> o.getProduct().equals(product)).findFirst().orElse(null);

           if(existenceInventory == null || existenceInventory.getQuantity() < quantity ){
               throw new RuntimeException("There is no enough stock"+product.getName());
           }
           existenceInventory.setQuantity(existenceInventory.getQuantity() - quantity);
           inventoryRepository.saveInventory(existenceInventory);

           return Movement.builder().quantity(quantity).product(product).type(MovementType.OUT).build();
        }).collect(Collectors.toList());

        movementsRepository.saveAllMovements(movements);
        saleRepository.saveSale(sale);
    }
}
