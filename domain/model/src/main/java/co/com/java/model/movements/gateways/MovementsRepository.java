package co.com.java.model.movements.gateways;

import co.com.java.model.movements.Movement;

import java.util.List;

public interface MovementsRepository {
    void saveMovement(Movement movement);

    void saveAllMovements(List<Movement> movements);

    List<Movement> getAllMovements();
}
