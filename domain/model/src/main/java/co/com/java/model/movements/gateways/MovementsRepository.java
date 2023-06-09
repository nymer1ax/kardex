package co.com.java.model.movements.gateways;

import co.com.java.model.movements.Movement;

public interface MovementsRepository {
    void saveMovement(Movement movement);
}
