package org.dreamsellers.controller;

import lombok.AllArgsConstructor;
import org.api.AdminApi;
import org.dreamsellers.service.AdminService;
import org.model.BusinessDto;
import org.model.ListingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class AdminController implements AdminApi {
    private final AdminService adminService;

    @Override
    public ResponseEntity<Void> adminBusinessesBusinessIdRemoveDelete(Long businessId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<List<BusinessDto>> adminBusinessesGet() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<ListingDto>> adminListingsGet() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> adminListingsListingIdRemoveDelete(Long listingId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> adminUsersGet() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> adminUsersUserIdBlockPost(Long userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> adminUsersUserIdDeleteDelete(Long userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
