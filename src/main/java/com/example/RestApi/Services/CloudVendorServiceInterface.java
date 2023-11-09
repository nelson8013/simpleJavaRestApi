package com.example.RestApi.Services;

import com.example.RestApi.Model.CloudVendor;

import java.util.List;

public interface CloudVendorServiceInterface {
    public List<CloudVendor> cloudVendors();
    public CloudVendor getCloudVendor(String vendorId);
    public CloudVendor createCloudVendor(CloudVendor cloudVendor);
    public CloudVendor updateCloudVendor(CloudVendor cloudVendor);

    public String deleteCloudVendor(String vendorId);

    public List<CloudVendor> getByVendorName(String vendorName);
}
