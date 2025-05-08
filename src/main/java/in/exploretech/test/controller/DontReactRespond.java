package in.exploretech.test.controller;

import com.fasterxml.jackson.databind.JsonNode;
import in.exploretech.test.entity.Employee;
import in.exploretech.test.service.EmployeeService;
import in.exploretech.util.JsonValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dont-react-rather-respond")
public class DontReactRespond {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/test")
    public ResponseEntity<?> doTest(){
        return ResponseEntity.ok("Don't React Respond Rather");
    }

    @PostMapping("/save-employee")
    public ResponseEntity<?> saveEmployee(@RequestBody JsonNode payload) {

        List<String> missingFields = JsonValidationUtil.getMissingFields(
                payload, "name", "gender", "email", "mobileNo"
        );

        if(!missingFields.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing required fields: " + String.join(", ", missingFields));
        }

        //String value = (value = payload.path("value").asText().trim()).isEmpty() ? null : value;
        // or
        String value = payload.path("value").asText().trim();
        //dont use trim() if you allow "" or " "
        value = value.isEmpty() ? null : value;
        System.out.println("value :"+ value);

        String cls = """
                One should not filled with pride of anything you have.
                one should always be mood of accepting good suggestions and be grateful for that
                
                If any suggestions pinch you then you are in conditioned state and in conditioned 
                state one can not develop good quality. We are always tolerate nasty things from 
                our friend circle but good suggestions pinches heart quickly, then your consciousness
                is considered as bad. 
                
                Even kanishtha adhikari association will lead to remember krishna and for doing 
                KC activity with full of heart.
                
                Que: when initially came in KC program then we are getting some pulling program to KC but
                it can not be increased after some time:
         
                Que:
                
                """;

        Employee employee = new Employee();
        employee.setName(payload.get("name").asText());
        employee.setGender(payload.get("gender").asText());
        employee.setEmail(payload.get("email").asText());
        employee.setMobileNo(payload.get("mobileNo").asText());

        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }


    @GetMapping("/get-employee-by-id")
    public ResponseEntity<?> getEmployeeById(@RequestParam String empId){

        Optional<Employee> employee = employeeService.getEmployee(empId);
        if(employee.isPresent()){
            return ResponseEntity.ok(employee.get());
        }
        return ResponseEntity.ok("Employee with given empId is not found");
    }

}
