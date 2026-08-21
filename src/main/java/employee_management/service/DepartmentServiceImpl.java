package employee_management.service;

import employee_management.entity.Department;
import employee_management.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));
    }

    @Override
    public void deleteDepartment(Long id) {
        repository.deleteById(id);
    }
    
}