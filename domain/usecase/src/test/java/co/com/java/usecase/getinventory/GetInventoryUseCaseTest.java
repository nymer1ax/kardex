package co.com.java.usecase.getinventory;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.inventory.gateways.InventoryRepository;
import co.com.java.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetInventoryUseCaseTest {
    @Mock
    private InventoryRepository inventoryRepository;

    private GetInventoryUseCase getInventoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getInventoryUseCase = new GetInventoryUseCase(inventoryRepository);
    }

    @Test
    void getAllInventory_ShouldReturnInventoryList_WhenInventoryNotEmpty() {

        Product product1 = new Product(1, "Product 1", "test", 50.0);
        Product product2 = new Product(2, "Product 2", "test", 250.0);
        // Arrange
        Inventory inventory1 = new Inventory(1, 10, product1);
        Inventory inventory2 = new Inventory(2, 5, product2);

        when(inventoryRepository.getAllInventory()).thenReturn(List.of(inventory1, inventory2));

        // Act
        List<Inventory> inventoryList = getInventoryUseCase.getAllInventory();

        // Assert
        assertEquals(2, inventoryList.size());
        assertEquals(inventory1, inventoryList.get(0));
        assertEquals(inventory2, inventoryList.get(1));
    }

    @Test
    void getAllInventory_ShouldReturnEmptyList_WhenInventoryIsEmpty() {
        // Arrange
        when(inventoryRepository.getAllInventory()).thenReturn(new ArrayList<>());

        // Act
        List<Inventory> inventoryList = getInventoryUseCase.getAllInventory();

        // Assert
        assertEquals(0, inventoryList.size());
    }
}
