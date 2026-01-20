package my.study.opencode.dto;

import java.util.Objects;

public record EmployeeSummary(Long id, String fullName, String department, double salary) {
  public EmployeeSummary {
    Objects.requireNonNull(fullName);
    Objects.requireNonNull(department);
    if (salary < 0) {
      throw new IllegalArgumentException("Salary cannot be negative");
    }
  }

  public static EmployeeSummary fromTuple(Object[] tuple) {
    return new EmployeeSummary(
        (Long) tuple[0], (String) tuple[1], (String) tuple[2], (double) tuple[3]);
  }
}
