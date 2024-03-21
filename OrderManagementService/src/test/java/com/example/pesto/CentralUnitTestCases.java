package com.example.pesto;

import com.example.pesto.dao.Order;
import com.example.pesto.enums.OrderStatus;
import com.example.pesto.enums.PaymentStatus;
import com.example.pesto.repository.OrderRepository;
import com.example.pesto.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

@Slf4j
@SpringBootTest(classes = {CentralTestConfiguration.class})
@TestPropertySource(properties = {"spring.datasource.url=jdbc:mysql://127.0.0.1:3306/pesto", "hibernate.dialect=org.hibernate.dialect.MySQLDialect", "spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver","spring.datasource.username=root", "spring.datasource.password=123456", "spring.jpa.hibernate.ddl-auto=update", "spring.jpa.show-sql = true","SPRING_PROFILES_ACTIVE=dev"})
public class CentralUnitTestCases {


    String USERNAME="shriram";
    String PASSWORD="123456";


    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void sampleTestCase(){
        Assertions.assertEquals(3,3);
    }


    @Test
    void testOrderOptimisticLockingConcurrency(){

        String sampleProdId = generateId();
        Order order =registerTestOrder(sampleProdId);

        Order sameOrder = orderRepository.findById(order.getId()).orElse(null);
        sameOrder.setAddress("New Addredd to Mumbai");
        orderService.updateOrder(sameOrder);

        try{
            orderService.updateOrder(order);
            deleteOrder(order.getId());
            Assertions.assertEquals(true,false);
        }catch (OptimisticLockingFailureException ex){
            deleteOrder(order.getId());

            // Test Passes here expected case
            Assertions.assertEquals(true,true);
        }
    }

    private Order registerTestOrder(String prodId){
        Order order = new Order();
        order.setId(generateId());

        order.setStatus(OrderStatus.ORDERED);
        order.setCreationDate(System.currentTimeMillis());

        order.setModifiedDate(System.currentTimeMillis());

        order.setProdId(prodId);
        order.setUserId(USERNAME);
        order.setAddress("MG road Delhi");
        order.setPaymentStatus(PaymentStatus.valueOf("UNPAID"));
        order.setQuantity(1);
        orderRepository.save(order);
        return order;
    }

    private void deleteOrder(String id){
        orderRepository.deleteById(id);
    }
    private String generateId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}