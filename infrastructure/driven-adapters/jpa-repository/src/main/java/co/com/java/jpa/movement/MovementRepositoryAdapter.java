package co.com.java.jpa.movement;

import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.model.movements.Movement;
import co.com.java.model.movements.gateways.MovementsRepository;
import co.com.java.model.movementtype.gateways.MovementtypeRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovementRepositoryAdapter extends AdapterOperations<Movement, MovementEntityData, Integer, MovementEntityDataRepository> implements MovementsRepository {

    public MovementRepositoryAdapter(MovementEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Movement.class/* change for domain model */));
    }

    @Override
    public void saveMovement(Movement movement) {

    }

    @Override
    public void saveAllMovements(List<Movement> movements) {

    }

    @Override
    public List<Movement> getAllMovements() {
        return null;
    }
}
