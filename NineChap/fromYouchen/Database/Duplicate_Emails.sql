# Write your MySQL query statement below
SELECT Email
from Person
group by Email
having count(Email) > 1