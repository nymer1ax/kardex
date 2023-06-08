package co.com.java.jpa.movement;

import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.model.movements.Movement;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MovementRepositoryAdapter extends AdapterOperations<Movement, MovementEntityData, Integer, MovementEntityDataRepository> {

    public MovementRepositoryAdapter(MovementEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Movement.class/* change for domain model */));
    }
}
