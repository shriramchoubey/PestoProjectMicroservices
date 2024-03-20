package com.example.pesto.productManagement;

import com.example.pesto.productManagement.dao.Product;
import com.example.pesto.productManagement.repository.ProductRepository;
import com.example.pesto.productManagement.service.ProductService;
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
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

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
    void testProductConcurrency(){
        Product product = registerTestProduct();
        product.setDescription("adfad");

        Product sameProduct = productRepository.findById(product.getId()).orElse(null);
        sameProduct.setName("Updaed new Name");
        productService.updateProduct(sameProduct);

        try{
            productService.updateProduct(product);
            Assertions.assertEquals(true,false);
        }catch (OptimisticLockingFailureException ex){
            Assertions.assertEquals(true,true);
        }
    }

    private Product registerTestProduct(){
        String createdById = USERNAME;
        Product product = new Product();
        product.setCreatedBy(createdById);
        product.setLastUpdatedBy(createdById);
        product.setName("Test Product");
        product.setImage("Test.png");
        product.setDescription("This is test product description");
        product.setId(generateId());
        product.setCreationDate(System.currentTimeMillis());
        product.setModifiedDate(System.currentTimeMillis());

        productRepository.save(product);

        return product;
    }

    private void deleteProduct(String id){
        productRepository.deleteById(id);
    }

    private String generateId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
