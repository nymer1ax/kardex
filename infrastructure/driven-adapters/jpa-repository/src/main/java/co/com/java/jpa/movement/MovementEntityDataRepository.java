package co.com.java.jpa.movement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MovementEntityDataRepository extends CrudRepository<MovementEntityData, Integer>, QueryByExampleExecutor<MovementEntityData> {
}
