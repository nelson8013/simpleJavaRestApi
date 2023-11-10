package com.example.RestApi.Controller;

import com.example.RestApi.Model.CloudVendor;
import com.example.RestApi.Services.CloudVendorService;
import com.example.RestApi.Services.CloudVendorServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(CloudVendorController.class)
public class CloudVendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CloudVendorService cloudVendorService;

    CloudVendor cloudVendorOne;
    CloudVendor cloudVendorTwo;
    CloudVendor cloudVendorThree;
    CloudVendor cloudVendorFour;

    List<CloudVendor> cloudVendorList = new ArrayList<>();


    @BeforeEach
    void setUp(){
        cloudVendorOne   = new CloudVendor("1","Amazon", "USA","XXXXXX");
        cloudVendorTwo   = new CloudVendor("2","GCP", "USA","XXXXXX");
        cloudVendorThree = new CloudVendor("3","Microsoft Azure", "USA","XXXXXX");
        cloudVendorFour  = new CloudVendor("4","IBM Cloud", "USA","XXXXXX");

        cloudVendorList.add(cloudVendorOne);
        cloudVendorList.add(cloudVendorTwo);
        cloudVendorList.add(cloudVendorThree);
        cloudVendorList.add(cloudVendorFour);
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void testCloudVendors() throws Exception {
        when(cloudVendorService.cloudVendors()).thenReturn(cloudVendorList);
        this.mockMvc.perform( get("/vendor/vendors") ).andDo( print() ).andExpect( status().isOk());
    }

    @Test
    void testCloudVendor() throws Exception {
        when(cloudVendorService.getCloudVendor("1") ).thenReturn(cloudVendorOne);
        this.mockMvc.perform( get("/vendor/1") ).andDo( print() ).andExpect( status().isOk());
    }

    @Test
    void testCreateVendor() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = mapper.writeValueAsString(cloudVendorOne);

        when(cloudVendorService.createCloudVendor(cloudVendorOne)).thenReturn(cloudVendorOne);
        this.mockMvc.perform(
                        post("/vendor/create")
                        .contentType( MediaType.APPLICATION_JSON)
                        .content(requestJson))
                    .andDo( print())
                    .andExpect( status().isOk());
    }

    @Test
    void testUpdateCloudVendor() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = mapper.writeValueAsString(cloudVendorOne);

        when(cloudVendorService.updateCloudVendor(cloudVendorOne)).thenReturn(cloudVendorThree);
        this.mockMvc.perform(
                        put("/vendor/update")
                                .contentType( MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andDo( print())
                .andExpect( status().isOk());

    }

    @Test
    void testDeleteCloudVendor() throws Exception {
        when(cloudVendorService.deleteCloudVendor("4")).thenReturn("Success");
        this.mockMvc.perform( delete("/vendor/4")).andDo( print() ).andExpect( status().isOk());
    }
}
