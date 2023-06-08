package co.com.java.jpa.employee;

import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.jpa.product.ProductEntityData;
import co.com.java.jpa.product.ProductEntityDataRepository;
import co.com.java.model.employee.Employee;
import co.com.java.model.product.Product;
import org.reactivecommons.utils.ObjectMapper;

public class EmployeeRepositoryAdapter extends AdapterOperations<Employee, EmployeeEntityData, Integer, EmployeeEntityDataRepository> {

    public EmployeeRepositoryAdapter(EmployeeEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Employee.class/* change for domain model */));
    }

}
