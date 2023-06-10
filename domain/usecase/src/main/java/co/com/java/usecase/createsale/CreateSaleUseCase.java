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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class CreateSaleUseCase {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final MovementsRepository movementsRepository;
    private final SaleRepository saleRepository;

    public void createSale(List<Integer> quantitiesList, LocalDate date, List<Integer> productsList) {

        if(quantitiesList.isEmpty() || quantitiesList == null){
            throw new RuntimeException("Vales must not be null");
        }

        if(date == null){
            throw new RuntimeException("Vales must not be null");
        }

        if(productsList.isEmpty() || productsList == null){
            throw new RuntimeException("Vales must not be null");
        }


        List<Integer> products = productsList;
        List<Integer> quantities = quantitiesList;
        List<Inventory> inventories = inventoryRepository.getAllInventory();
        BigDecimal totalSold = BigDecimal.ZERO;
        List<Movement> movements = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            Integer product = products.get(i);
            Integer quantity = quantities.get(i);
            Product p = getProduct(product);
            Inventory existenceInventory = inventories.stream()
                    .filter(o -> o.getProduct().getId().equals(product))
                    .findFirst()
                    .orElse(null);

            if (existenceInventory == null || existenceInventory.getQuantity() < quantity) {
                throw new RuntimeException("There is not enough stock for productId::" + product);
            }

            existenceInventory.setQuantity(existenceInventory.getQuantity() - quantity);
            inventoryRepository.saveInventory(existenceInventory);

            BigDecimal subtotal = new BigDecimal(p.getPrice()).multiply(BigDecimal.valueOf(quantity));
            totalSold = totalSold.add(subtotal);

            Movement movement = Movement.builder()
                    .quantity(quantity)
                    .product(p)
                    .type(MovementType.OUT)
                    .subtotal(subtotal)
                    .build();
            movements.add(movement);
        }

        movementsRepository.saveAllMovements(movements);

        List<Product> pList = productRepository.findAllById(products);

        Sale sale = Sale.builder()
                .quantities(quantities)
                .products(pList)
                .date(date)
                .totalSold(totalSold)
                .build();
        saleRepository.saveSale(sale);
    }


    private Product getProduct(Integer product) {
        Optional<Product> p = productRepository.findByProductId(product);
        if(p.isEmpty()){
            throw new RuntimeException("Product do not exist");
        }
        return p.get();
    }

}
