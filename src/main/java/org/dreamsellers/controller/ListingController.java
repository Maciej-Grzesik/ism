package org.dreamsellers.controller;

import lombok.AllArgsConstructor;
import org.api.ListingApi;
import org.dreamsellers.service.ListingService;

import org.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class ListingController implements ListingApi {
    private final ListingService listingService;

    @Override
    public ResponseEntity<List<ListingDto>> listingsGetAllGet(String category, String location, Float rating, String search) {
        List<ListingDto> listingDtos = listingService.getAll(category, location, rating, search);

        return new ResponseEntity<>(listingDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListingResponseDto> listingsCreatePost(ListingDto listingDto) {

        ListingResponseDto listingResponseDto = listingService.createListing(listingDto);

        return new ResponseEntity<>(listingResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListingDto> listingsListingIdGet(Long listingId) {
        ListingDto listingDto = listingService.getListingById(listingId);

        return new ResponseEntity<>(listingDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListingResponseDto> listingsListingIdPut(Long listingId, ListingDto listingDto) {
        ListingResponseDto listingResponseDto = listingService.updateListing(listingId, listingDto);

        return new ResponseEntity<>(listingResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> listingsListingIdDelete(Long listingId) {
        listingService.deleteListing(listingId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<ReviewDto>> listingsListingIdReviewsGet(Long listingId) {
        List<ReviewDto> reviewDtos = listingService.getAllReviews(listingId);

        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListingsListingIdSalesGet200Response> listingsListingIdSalesGet(Long listingId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> listingsListingIdCancelPolicyPut(Long listingId, ListingsListingIdCancelPolicyPutRequest listingsListingIdCancelPolicyPutRequest) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}