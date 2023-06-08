package co.com.java.jpa.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface EmployeeEntityDataRepository extends CrudRepository<EmployeeEntityData, Integer>, QueryByExampleExecutor<EmployeeEntityData> {
}
