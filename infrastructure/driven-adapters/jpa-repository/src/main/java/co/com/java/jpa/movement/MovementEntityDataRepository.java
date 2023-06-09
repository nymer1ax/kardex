package co.com.java.jpa.movement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface MovementEntityDataRepository extends CrudRepository<MovementEntityData, Integer>, QueryByExampleExecutor<MovementEntityData> {

List<MovementEntityData> findAll();
}
