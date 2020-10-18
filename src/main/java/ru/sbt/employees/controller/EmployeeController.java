package ru.sbt.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.service.EmployeeService;

import javax.persistence.PersistenceException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private int itemPerPage = 10;
    private int page = 1;
    private final Map<String, Boolean> sortingDirectionMap = new HashMap<>();

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    //public ModelAndView getPage(HttpServletRequest request) {
    public ModelAndView getPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "sort", defaultValue = "id") String sortColumn) {
        if(page < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Номер страницы не может быть меньше 1: " + page);
            //return errorMessage("Номер страницы не может быть меньше 1: " + page);
        List<Employee> employees;
        int employeesCount;
        boolean sortingDirection = sortingDirectionMap.merge(sortColumn, false, (oldVal, newVal) -> !oldVal);
        try {
            employees = employeeService.getPageEmployees(page, itemPerPage, sortColumn, sortingDirection);
            employeesCount = employeeService.countEmployees();
        } catch (PersistenceException e) {
            return errorMessage("Ошибка выполнения SQL-запрса: " + e.getMessage());
        }
        int pagesCount = (employeesCount + itemPerPage - 1)/itemPerPage;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employees");
        modelAndView.addObject("page", page);
        modelAndView.addObject("employeesList", employees);
        modelAndView.addObject("pagesCount", pagesCount);
        this.page = page;
        return modelAndView;
    }

    @GetMapping("/employee")
    public ModelAndView getById(@RequestParam(name = "id", required = true) int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null)
            return errorMessage("Сотрудник не найден. ID: " + id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addEmployee");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees?page=" + this.page);
        employeeService.addEmployee(employee);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView editView(@RequestParam(name = "id", required = true) int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null)
            return errorMessage("Сотрудник не найден. ID: " + id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editEmployee");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editEmployee(@ModelAttribute("employee") Employee employee) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees?page=" + this.page);
        employeeService.updateEmployee(employee);
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteEmployee(@RequestParam(name = "id", required = true) int id) {
        if(id < 0)
            return errorMessage("Идентификатор сотрудника не может быть отрицательным");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees?page=" + this.page);
        Employee employee = employeeService.getEmployeeById(id);
        employeeService.deleteEmployee(employee);
        return modelAndView;
    }

    private ModelAndView errorMessage(String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
