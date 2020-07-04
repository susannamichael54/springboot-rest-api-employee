package com.employee.controller;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/{id}")
    @ApiOperation(value = "Get Employee details by passing Employee Id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Could not find employee {employee id}")
    })
    public ResponseEntity<Object> getEmployee(@PathVariable Long id) {

        EmployeeDTO employee = this.modelMapper.map(this.employeeService.getEmployee(id), EmployeeDTO.class);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Get details of all employees")
    public ResponseEntity<Object> getAllEmployees() {

        List<EmployeeDTO> employeeDTOList =  this.employeeService
                                                .getAllEmployees()
                                                .stream()
                                                .map(empEntity ->
                                                        modelMapper.map(empEntity, EmployeeDTO.class))
                                                .collect(Collectors.toList());
        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value ="Create a new employee")
    public ResponseEntity<Object> saveEmployee(@RequestBody @Valid final EmployeeDTO employee) {

        Long employeeId = this.employeeService.saveEmployee(this.modelMapper.map(employee, Employee.class));
        return new ResponseEntity<>("Employee (id = " + employeeId + ") created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update the details of an employee")
    public ResponseEntity<Object> updateEmployee(
            @RequestBody @Valid final EmployeeDTO employee, @PathVariable Long id) {

        Employee updateEmployee = this.employeeService.updateEmployee(this.modelMapper.map(employee, Employee.class), id);
        return  new ResponseEntity<>(this.modelMapper.map(updateEmployee, EmployeeDTO.class), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete employee with Employee Id")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {

        this.employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee (id = " + id + ") deleted successfully", HttpStatus.OK);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
