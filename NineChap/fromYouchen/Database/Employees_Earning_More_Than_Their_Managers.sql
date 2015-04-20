# Write your MySQL query statement below
SELECT emp.Name
From Employee emp INNER JOIN Employee manager
on emp.ManagerId = manager.Id 
Where emp.Salary > manager.Salary