package org.dreamsellers.service;

import lombok.AllArgsConstructor;
import org.dreamsellers.model.AuthEntity;
import org.dreamsellers.model.BusinessEntity;
import org.dreamsellers.model.ListingEntity;
import org.dreamsellers.repository.AuthRepository;
import org.dreamsellers.repository.BusinessRepository;
import org.model.BusinessDto;
import org.model.ListingDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final ModelMapper modelMapper;
    private final AuthRepository authRepository;

    public List<BusinessDto> getAll() {
        List<BusinessEntity> businessDtos = businessRepository.findAll();

        return businessDtos.stream().map(businessDto -> modelMapper.map(businessDto, BusinessDto.class)).toList();
    }

    public BusinessDto getBusiness(Long businessId) {
        BusinessEntity businessEntity = businessRepository.findById(businessId).orElseThrow(()-> new RuntimeException("No"));
        return modelMapper.map(businessEntity, BusinessDto.class);
    }

    public BusinessDto updateBusiness(Long businessId, BusinessDto businessDto) {
        BusinessEntity businessEntity = businessRepository.findById(businessId).orElseThrow(()-> new RuntimeException("No"));

        businessEntity.setName(businessDto.getName());
        businessEntity.setDescription(businessDto.getDescription());
        businessEntity.setAddress(businessDto.getAddress());
        businessEntity.setUpdatedAt(OffsetDateTime.now());
        BusinessEntity savedBusiness = businessRepository.save(businessEntity);

        return modelMapper.map(savedBusiness, BusinessDto.class);
    }

    public void deleteBusiness(Long businessId) {
        businessRepository.deleteById(businessId);
    }

    public List<ListingDto> getAllListings(Long businessId) {
        List<ListingEntity> listingEntities = businessRepository.findById(businessId).orElseThrow(()-> new RuntimeException("No")).getListings();

        return listingEntities.stream().map(listingEntity -> modelMapper.map(listingEntity, ListingDto.class)).toList();
    }

    public BusinessDto registerBusiness(BusinessDto businessDto) {
        BusinessEntity businessEntity = new BusinessEntity();

        businessEntity.setName(businessDto.getName());
        businessEntity.setDescription(businessDto.getDescription());
        businessEntity.setAddress(businessDto.getAddress());

//        AuthEntity authEntity = authRepository.findById(businessDto.authId());

        OffsetDateTime now = OffsetDateTime.now();
        businessEntity.setCreatedAt(now);
        businessEntity.setUpdatedAt(now);

        BusinessEntity savedBusiness = businessRepository.save(businessEntity);
        System.out.println(savedBusiness.getCreatedAt());
        return modelMapper.map(savedBusiness, BusinessDto.class);
    }
}
