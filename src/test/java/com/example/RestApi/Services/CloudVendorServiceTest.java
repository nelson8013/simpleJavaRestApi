package com.example.RestApi.Services;

import com.example.RestApi.Model.CloudVendor;
import com.example.RestApi.Repository.CloudVendorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class CloudVendorServiceTest {


    @Mock
    private CloudVendorRepository cloudVendorRepository;

    private CloudVendorServiceInterface cloudVendorServiceInterface;

    private AutoCloseable autoCloseable;

    private CloudVendor cloudVendor;





    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorServiceInterface = new CloudVendorService(cloudVendorRepository);
        cloudVendor = new CloudVendor("1","Amazon","USA","XXXXXX");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCloudVendors(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findAll()).thenReturn(
                new ArrayList<CloudVendor>(Collections.singleton(cloudVendor))
        );
        assertThat( cloudVendorServiceInterface.cloudVendors().get(0).getVendorPhoneNumber() ).isEqualTo(cloudVendor.getVendorPhoneNumber());
    }


    @Test
    void testGetCloudVendor(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorServiceInterface.getCloudVendor("1").getVendorName()).isEqualTo(cloudVendor.getVendorName());
    }

    @Test
    void testCreateCloudVendor(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorServiceInterface.createCloudVendor(cloudVendor)).isEqualTo(cloudVendor);
    }

    @Test
    void testUpdateCloudVendor(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorServiceInterface.updateCloudVendor(cloudVendor)).isEqualTo(cloudVendor);
    }

    @Test
    void testGetVendorByVendorName(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findByVendorName("Amazon")).thenReturn( new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));
        assertThat(cloudVendorServiceInterface.getByVendorName("Amazon").get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
    }


    @Test
    void testDeleteCloudVendor(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository).deleteById(any());
        assertThat(cloudVendorServiceInterface.deleteCloudVendor("1")).isEqualTo("Success");

    }



}
