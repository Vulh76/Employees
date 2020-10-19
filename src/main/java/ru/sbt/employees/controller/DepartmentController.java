package ru.sbt.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ru.sbt.employees.model.Department;
import ru.sbt.employees.service.DepartmentService;

import javax.persistence.PersistenceException;
import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public List<Department> getAll() {
        List<Department> departments;
        try {
            departments = departmentService.getAllDepartments("id", false);
        } catch (PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ошибка выполнения SQL-запрса: " + e.getMessage());
        }
        return departments;
    }

    @GetMapping("/department/{id}")
    public Department getById(@PathVariable("id") int id) {
        Department department = departmentService.getDepartmentById(id);
        return department;
    }

    /*@PostMapping("/add")
    public ModelAndView addEmployee(@ModelAttribute("employee") Department department) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees?page=1");
        departmentService.addDepartment(department);
        return modelAndView;
    }*/
}
