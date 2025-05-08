package in.exploretech.test.service;

import in.exploretech.test.entity.Employee;
import in.exploretech.test.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployee(String empId){
        return employeeRepository.findById(Long.valueOf(empId));
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

}
