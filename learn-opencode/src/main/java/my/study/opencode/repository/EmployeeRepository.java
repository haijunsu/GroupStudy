package my.study.opencode.repository;

import java.util.List;
import my.study.opencode.dto.EmployeeNameRecord;
import my.study.opencode.dto.EmployeeSalaryRecord;
import my.study.opencode.dto.EmployeeSummary;
import my.study.opencode.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  // 1. Constructor Expression Projection
  @Query(
      "SELECT new my.study.opencode.dto.EmployeeSummary(e.id, CONCAT(e.firstName, ' ', e.lastName), e.department, e.salary) "
          + "FROM Employee e WHERE e.department = :dept")
  List<EmployeeSummary> findEmployeeSummariesByDepartment(@Param("dept") String department);

  // 2. Interface-like Record Projection (Spring Data JPA 3.0+)
  @Query("SELECT e.firstName, e.lastName FROM Employee e WHERE e.salary > :salary")
  List<EmployeeNameRecord> findHighEarners(@Param("salary") double salary);

  // 3. Tuple Mapping (Returning Object[])
  @Query("SELECT e.id, e.firstName, e.department, e.salary FROM Employee e")
  List<Object[]> findEmployeeData();

  // 4. Native Query Record Projection
  @Query(
      value =
          "SELECT id, first_name || ' ' || last_name as full_name, department, salary "
              + "FROM employees WHERE salary > :min",
      nativeQuery = true)
  List<EmployeeSalaryRecord> findHighEarnersNative(@Param("min") double minSalary);
}
