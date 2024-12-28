package com.project.thymeleafcrud.controller;

import com.project.thymeleafcrud.entity.Employee;
import com.project.thymeleafcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired // autowired is Optional if we have only one contractor
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/employee-form";
    }

    @GetMapping("/showFromForUpdate")
    public String showFromForUpdate(Model theModel, @RequestParam("employeeId") int empId) {
        // get employee by empId
        Employee employee = employeeService.getEmployeeById(empId);
        theModel.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        // use a redirect to call controller back!
        return "redirect:/employees/list";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int empId) {
        employeeService.deleteEmployee(empId);
        return "redirect:/employees/list";
    }

}
