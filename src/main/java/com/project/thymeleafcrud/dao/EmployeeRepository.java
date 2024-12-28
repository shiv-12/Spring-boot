package com.project.thymeleafcrud.dao;


import com.project.thymeleafcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // And That Is It, We don’t need DAO Anymore!

}
