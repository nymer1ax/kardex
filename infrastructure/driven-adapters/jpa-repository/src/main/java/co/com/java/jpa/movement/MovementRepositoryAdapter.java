package co.com.java.jpa.movement;

import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.model.movements.Movement;
import co.com.java.model.movements.gateways.MovementsRepository;
import co.com.java.model.movementtype.gateways.MovementtypeRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovementRepositoryAdapter extends AdapterOperations<Movement, MovementEntityData, Integer, MovementEntityDataRepository> implements MovementsRepository {

    public MovementRepositoryAdapter(MovementEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Movement.class/* change for domain model */));
    }

    @Override
    public void saveMovement(Movement movement) {
        MovementEntityData m = toData(movement);
        repository.save(m);
    }

    @Override
    public void saveAllMovements(List<Movement> movements) {
        List<MovementEntityData> e = movements.stream().map(this::toData).collect(Collectors.toList());
        e.forEach(o -> repository.save(o));
    }

    @Override
    public List<Movement> getAllMovements() {
        return repository.findAll().stream().map(this::toEntity).collect(Collectors.toList());
    }
}
