package com.example.pesto;

import com.example.pesto.orderManagement.dao.Order;
import com.example.pesto.orderManagement.dao.ProductDAO;
import com.example.pesto.orderManagement.enums.OrderStatus;
import com.example.pesto.orderManagement.enums.PaymentStatus;
import com.example.pesto.orderManagement.repository.OrderRepository;
import com.example.pesto.orderManagement.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CentralTestConfiguration.class})
@TestPropertySource(properties = {"spring.datasource.url=jdbc:mysql://127.0.0.1/pesto", "spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver","spring.datasource.username=root", "spring.datasource.password=123456", "spring.jpa.hibernate.ddl-auto=update", "spring.jpa.show-sql = true","SPRING_PROFILES_ACTIVE=dev"})
@ActiveProfiles("dev")
public class CentralUnitTestCases {


    String USERNAME="shriram";
    String PASSWORD="123456";

//    @Autowired
//    AuthenticationTokenService tokenService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void sampleTestCase(){
        Assertions.assertEquals(3,3);
    }

//    String generateToken(){
//        String username = USERNAME;
//        String password = PASSWORD;
//        JwtResponse response = tokenService.createToken(username, password);
//        return response.getToken();
//    }

    @Test
    void testOrderConcurrency(){

//        Product product = registerTestProduct();
        Order order =registerTestOrder("");
//        product.setDescription("adfad");

        Order sameOrder = orderRepository.findById(order.getId()).orElse(null);
        sameOrder.setAddress("New Addredd to Mumbai");
        orderService.updateOrder(sameOrder);

        try{
            orderService.updateOrder(order);
//            deleteProduct(product.getId());
            deleteOrder(order.getId());
            Assertions.assertEquals(true,false);
        }catch (OptimisticLockingFailureException ex){
//            deleteProduct(product.getId());
            deleteOrder(order.getId());
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

    private ProductDAO registerTestProduct(){
        String createdById = USERNAME;
        ProductDAO product = new ProductDAO();
//        product.setCreatedBy(createdById);
//        product.setLastUpdatedBy(createdById);
        product.setName("Test Product");
        product.setImage("Test.png");
        product.setDescription("This is test product description");
        product.setId(generateId());
        product.setCreationDate(System.currentTimeMillis());
        product.setModifiedDate(System.currentTimeMillis());

//        productRepository.save(product);

        return product;
    }

    private void deleteOrder(String id){
        orderRepository.deleteById(id);
    }
    private String generateId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
