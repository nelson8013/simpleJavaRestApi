package com.example.RestApi.Controller;

import com.example.RestApi.Model.CloudVendor;
import com.example.RestApi.Responses.ResponseHandler;
import com.example.RestApi.Services.CloudVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class CloudVendorController {

    @Autowired
    CloudVendorService cloudVendorService;

    @GetMapping("/vendors")
    public List<CloudVendor> cloudVendors()
    {
        return cloudVendorService.cloudVendors();
    }


    @GetMapping("{vendorId}")
    public ResponseEntity<Object> cloudVendor(@PathVariable("vendorId") String vendorId)
    {
        CloudVendor data = cloudVendorService.getCloudVendor(vendorId);
        return ResponseHandler.responseBuilder("Vendor retrieved successfully", HttpStatus.OK, data);
    }


    @PostMapping("/create")
    public ResponseEntity<Object> createVendor(@RequestBody CloudVendor cloudVendor)
    {
        CloudVendor data = cloudVendorService.createCloudVendor(cloudVendor);
        return ResponseHandler.responseBuilder("Cloud vendor Created successfully", HttpStatus.OK, data);

    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCloudVendor(@RequestBody CloudVendor cloudVendor)
    {
        CloudVendor data = cloudVendorService.updateCloudVendor(cloudVendor);
        return ResponseHandler.responseBuilder("Cloud vendor updated successfully", HttpStatus.OK, data);
    }

    @DeleteMapping("{vendorId}")
    public ResponseEntity<Object> deleteCloudVendor(@PathVariable("vendorId") String vendorId)
    {
        cloudVendorService.deleteCloudVendor(vendorId);
        return ResponseHandler.responseBuilder("Cloud vendor deleted successfully", HttpStatus.OK);
    }

}
