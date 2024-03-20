package com.example.pesto.productManagement.repository;

import com.example.pesto.productManagement.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

//    @Repository
//    public interface TasksRepository extends JpaRepository<Tasks, String>, JpaSpecificationExecutor<Tasks> {
//        List<Tasks> findAllByOperatorIdAndTaskState(String operatorId, String status);
//        List<Tasks> findByOperatorIdIsNull();
//    }

}
