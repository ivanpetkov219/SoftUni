package CompanyRoster;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private List<Employee> employees;

    public Department(){
        this.employees = new ArrayList<>();
    }

    public void add(Employee employee){
        this.employees.add(employee);
    }



}
