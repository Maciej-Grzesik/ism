package org.dreamsellers.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.dreamsellers.model.BusinessEntity;
import org.dreamsellers.model.ListingEntity;
import org.dreamsellers.model.ReviewEntity;
import org.dreamsellers.repository.BusinessRepository;
import org.dreamsellers.repository.ListingRepository;
import org.model.ListingDto;
import org.model.ListingResponseDto;
import org.model.ReviewDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final ModelMapper modelMapper;
    private final BusinessRepository businessRepository;

    public List<ListingDto> getAll(String category, String location, Float rating, String search) {
        List<ListingEntity> listings = listingRepository.findAll().stream()
                .filter(listing -> category == null || listing.getCategory().equalsIgnoreCase(category))
                .filter(listing -> location == null || listing.getLocation().equalsIgnoreCase(location))
                .filter(listing -> rating == null || listing.getRating() >= rating)
                .filter(listing -> search == null || listing.getTitle().toLowerCase().contains(search.toLowerCase()))
                .toList();

        return listings.stream()
                .map(listing -> modelMapper.map(listing, ListingDto.class))
                .collect(Collectors.toList());
    }

    public ListingResponseDto createListing(ListingDto listingDto) {
        ListingEntity listingEntity = new ListingEntity();


        BusinessEntity businessEntity = businessRepository.findById(listingDto.getBusinessId()).orElseThrow(() -> new RuntimeException("Business not found"));

        listingEntity.setBusiness(businessEntity);
        listingEntity.setTitle(listingDto.getTitle());
        listingEntity.setDescription(listingDto.getDescription());
        listingEntity.setCategory(listingDto.getCategory());
        listingEntity.setLocation(listingDto.getLocation());
        listingEntity.setPrice(listingDto.getPrice());
        List<LocalDate> convertedAvailability = listingDto.getAvailability().stream()
                .map(LocalDate::parse)
                .collect(Collectors.toList());

        listingEntity.setAvailability(convertedAvailability);
        listingEntity.setImages(listingDto.getImages());
        listingEntity.setCreatedAt(LocalDateTime.now());
        listingEntity.setUpdatedAt(LocalDateTime.now());

        ListingEntity savedListing = listingRepository.save(listingEntity);

        return modelMapper.map(savedListing, ListingResponseDto.class);
    }

    public ListingDto getListingById(Long listingId) {
        ListingEntity listingEntity = listingRepository.getListingById(listingId).orElseThrow(() -> new RuntimeException("Listing not found"));

        return modelMapper.map(listingEntity, ListingDto.class);
    }

    public ListingResponseDto updateListing(Long listingId, ListingDto listingDto) {
        ListingEntity listingEntity = listingRepository.getListingById(listingId).orElseThrow(() -> new RuntimeException("Listing not found"));

        listingEntity.setTitle(listingDto.getTitle());
        listingEntity.setCategory(listingDto.getCategory());
        listingEntity.setLocation(listingDto.getLocation());
        listingEntity.setRating(listingDto.getRating());

        ListingEntity updatedEntity = listingRepository.save(listingEntity);
        return modelMapper.map(updatedEntity, ListingResponseDto.class);
    }

    public void deleteListing(Long listingId) {
        listingRepository.deleteById(listingId);
    }

    public List<ReviewDto> getAllReviews(Long listingId) {
        ListingEntity listingEntity = listingRepository.getListingById(listingId).orElseThrow(() -> new RuntimeException("Listing not found"));

        List<ReviewEntity> reviews = listingEntity.getReviews();

        return reviews.stream().map(review -> modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList());
    }
}
