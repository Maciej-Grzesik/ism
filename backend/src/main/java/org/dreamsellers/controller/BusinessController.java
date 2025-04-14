package org.dreamsellers.controller;


import lombok.AllArgsConstructor;
import org.api.BusinessApi;
import org.dreamsellers.repository.BusinessRepository;
import org.dreamsellers.service.BusinessService;
import org.model.BusinessDto;
import org.model.BusinessStatsDto;
import org.model.ListingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class BusinessController implements BusinessApi {
    private final BusinessService businessService;

    @Override
    public ResponseEntity<List<BusinessDto>> businessesGet() {
        List<BusinessDto> businessDtos = businessService.getAll();

        return new ResponseEntity<>(businessDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BusinessStatsDto> businessesBusinessIdStatsGet(String businessId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<BusinessDto> businessesBusinessIdGet(Long businessId) {
        BusinessDto businessDto = businessService.getBusiness(businessId);

        return new ResponseEntity<>(businessDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BusinessDto> businessesBusinessIdPut(Long businessId, BusinessDto businessDto) {
        BusinessDto businessResponseDto = businessService.updateBusiness(businessId, businessDto);

        return new ResponseEntity<>(businessResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> businessesBusinessIdDelete(Long businessId) {
        businessService.deleteBusiness(businessId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<ListingDto>> businessesBusinessIdListingsGet(Long businessId) {
        List<ListingDto> listingDtos = businessService.getAllListings(businessId);

        return new ResponseEntity<>(listingDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BusinessDto> businessesRegisterPost(BusinessDto businessDto) {
        BusinessDto businessResponseDto = businessService.registerBusiness(businessDto);
        return new ResponseEntity<>(businessResponseDto, HttpStatus.OK);
    }
}
