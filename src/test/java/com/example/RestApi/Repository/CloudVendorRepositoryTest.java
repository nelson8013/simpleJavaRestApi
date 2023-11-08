package com.example.RestApi.Repository;

import com.example.RestApi.Model.CloudVendor;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class CloudVendorRepositoryTest {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;


    @BeforeEach
    void setUp(){
     cloudVendor = new CloudVendor("1","Amazon","USA", "xxxxxxx");
     cloudVendorRepository.save(cloudVendor);
    }

    @AfterEach
    void tearDown(){
     cloudVendor = null;
     cloudVendorRepository.deleteAll();
    }

    //Test case SUCCESS
    @Test
    void testFindByVendorName_Found(){
       List<CloudVendor> cloudVendorList =  cloudVendorRepository.findByVendorName("Amazon");
       assertThat( cloudVendorList.get(0).getVendorId()).isEqualTo( cloudVendor.getVendorId());
       assertThat( cloudVendorList.get(0).getVendorName()).isEqualTo( cloudVendor.getVendorName());
       assertThat( cloudVendorList.get(0).getVendorAddress()).isEqualTo( cloudVendor.getVendorAddress());
    }


    //Test case FAILURE
    @Test
    void testFindByVendorName_NotFound(){
        List<CloudVendor> cloudVendorList =  cloudVendorRepository.findByVendorName("GCP");
        assertThat(cloudVendorList.isEmpty()).isTrue();
    }




}
