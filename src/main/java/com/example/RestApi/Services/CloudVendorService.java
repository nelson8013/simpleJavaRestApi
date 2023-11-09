package com.example.RestApi.Services;

import com.example.RestApi.Exceptions.CloudVendorNotFoundException;
import com.example.RestApi.Model.CloudVendor;
import com.example.RestApi.Repository.CloudVendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorService implements CloudVendorServiceInterface{

    CloudVendorRepository cloudVendorRepository;


    public CloudVendorService(CloudVendorRepository cloudVendorRepository){
        this.cloudVendorRepository = cloudVendorRepository;
    }

    @Override
    public List<CloudVendor> cloudVendors() {
        return cloudVendorRepository.findAll();
    }

    @Override
    public CloudVendor getCloudVendor(String vendorId) {
        boolean cloudVendorDoesNotExist = cloudVendorRepository.findById(vendorId).isEmpty();

        if(cloudVendorDoesNotExist)
            throw new CloudVendorNotFoundException("Requested Cloud vendor does not exist");

        return cloudVendorRepository.findById(vendorId).get();

    }

    @Override
    public CloudVendor createCloudVendor(CloudVendor cloudVendor) {
        return cloudVendorRepository.save(cloudVendor);
    }

    @Override
    public CloudVendor updateCloudVendor(CloudVendor cloudVendor) {
        return cloudVendorRepository.save(cloudVendor);
    }

    @Override
    public String deleteCloudVendor(String vendorId) {
        cloudVendorRepository.deleteById(vendorId);
        return "Success";
    }

    @Override
    public List<CloudVendor> getByVendorName(String vendorName) {
        return cloudVendorRepository.findByVendorName(vendorName);
    }
}
