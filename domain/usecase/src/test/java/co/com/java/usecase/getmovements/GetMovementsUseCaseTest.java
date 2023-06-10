package co.com.java.usecase.getmovements;

import co.com.java.model.movements.Movement;
import co.com.java.model.movements.gateways.MovementsRepository;
import co.com.java.model.movementtype.MovementType;
import co.com.java.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetMovementsUseCaseTest {
    @Mock
    private MovementsRepository movementsRepository;

    private GetMovementsUseCase getMovementsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getMovementsUseCase = new GetMovementsUseCase(movementsRepository);
    }

    @Test
    void getAllMovements_ShouldReturnMovementList_WhenMovementsNotEmpty() {
        // Arrange

        Product product1 = new Product(1, "Product 1", "test", 50.0);
        Product product2 = new Product(2, "Product 2", "test", 250.0);

        Movement movement1 = new Movement(1, product1, 10, MovementType.IN, null);
        Movement movement2 = new Movement(2, product2,  2, MovementType.IN, null);

        when(movementsRepository.getAllMovements()).thenReturn(List.of(movement1, movement2));

        // Act
        List<Movement> movementList = getMovementsUseCase.getAllMovements();

        // Assert
        assertEquals(2, movementList.size());
        assertEquals(movement1, movementList.get(0));
        assertEquals(movement2, movementList.get(1));
    }

    @Test
    void getAllMovements_ShouldReturnEmptyList_WhenMovementsIsEmpty() {
        // Arrange
        when(movementsRepository.getAllMovements()).thenReturn(new ArrayList<>());

        // Act
        List<Movement> movementList = getMovementsUseCase.getAllMovements();

        // Assert
        assertEquals(0, movementList.size());
    }
}
