package co.com.java.model.employee;
import co.com.java.model.rol.Rol;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Employee {
    private Integer id;
    private String name;
    private List<Rol> roles;
}
