package ru.sbt.employees.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private int itemPerPage = 10;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(this.employeeService.getAll());
    }

    @GetMapping("/{page}")
    public ResponseEntity<List<Employee>> getPage(@PathVariable("page") int page) {
        return ResponseEntity.ok(this.employeeService.getPage(page, itemPerPage));
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(this.employeeService.getById(id));
        } catch (HibernateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> add() {
        Employee employee = new Employee();
        this.employeeService.add(employee);
        return ResponseEntity.ok(employee);
    }



    @GetMapping("/")
    public ModelAndView allEmployees(@RequestParam(defaultValue = "1") int page) {
        List<Employee> employees = employeeService.getPage(page, itemPerPage);
        int employeesCount = employeeService.count();
        int pagesCount = (employeesCount + itemPerPage - 1)/itemPerPage;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employees");
        modelAndView.addObject("page", page);
        modelAndView.addObject("employeesList", employees);
        modelAndView.addObject("employeesCount", employeesCount);
        modelAndView.addObject("pagesCount", pagesCount);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        Employee employee = employeeService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editEmployee(@ModelAttribute("employee") Employee employee) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        employeeService.update(employee);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        employeeService.add(employee);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Employee employee = employeeService.getById(id);
        employeeService.delete(employee);
        return modelAndView;
    }
}
