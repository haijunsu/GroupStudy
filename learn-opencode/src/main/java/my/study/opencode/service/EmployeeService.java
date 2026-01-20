package my.study.opencode.service;

import java.util.List;
import my.study.opencode.dto.EmployeeNameRecord;
import my.study.opencode.dto.EmployeeSalaryRecord;
import my.study.opencode.dto.EmployeeSummary;
import my.study.opencode.dto.ProductDto;
import my.study.opencode.repository.EmployeeRepository;
import my.study.opencode.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final ProductRepository productRepository;

  public EmployeeService(
      EmployeeRepository employeeRepository, ProductRepository productRepository) {
    this.employeeRepository = employeeRepository;
    this.productRepository = productRepository;
  }

  public List<EmployeeSummary> getEmployeeSummariesByDept(String department) {
    return employeeRepository.findEmployeeSummariesByDepartment(department);
  }

  public List<EmployeeNameRecord> getHighEarnersNames(double salary) {
    return employeeRepository.findHighEarners(salary);
  }

  public List<EmployeeSummary> getEmployeeSummariesFromTuples() {
    return employeeRepository.findEmployeeData().stream().map(EmployeeSummary::fromTuple).toList();
  }

  public List<EmployeeSalaryRecord> getHighEarnersNative(double minSalary) {
    return employeeRepository.findHighEarnersNative(minSalary);
  }

  public List<ProductDto> getAllProducts() {
    return productRepository.findProductDtos();
  }
}
