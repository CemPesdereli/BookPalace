package com.bookpalace.controller;

import com.bookpalace.dto.request.PublisherSaveRequest;
import com.bookpalace.dto.response.CustomerResponse;
import com.bookpalace.dto.response.GenericResponse;
import com.bookpalace.dto.response.ProductResponse;
import com.bookpalace.dto.response.PublisherResponse;
import com.bookpalace.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public GenericResponse<PublisherResponse> save(@RequestBody PublisherSaveRequest request) {

        return GenericResponse.success(publisherService.save(request));
    }

    @GetMapping
    public GenericResponse<List<PublisherResponse>> getAll() {
        return GenericResponse.success(publisherService.getAllPublishers());
    }



}
