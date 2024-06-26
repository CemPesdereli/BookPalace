package com.bookpalace.service;

import com.bookpalace.converter.ProductConverter;
import com.bookpalace.converter.PublisherConverter;
import com.bookpalace.dto.response.PublisherResponse;
import com.bookpalace.repository.PublisherRepository;
import com.bookpalace.dto.request.PublisherSaveRequest;
import com.bookpalace.model.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherResponse save(PublisherSaveRequest request) {

        Publisher publisher = PublisherConverter.toPublisher(request);

        publisherRepository.save(publisher);

        log.info("publisher saved. {}", publisher.toString());
        return PublisherConverter.toResponse(publisher);
    }

    public List<PublisherResponse> getAllPublishers() {
        return PublisherConverter.toResponse(publisherRepository.findAll());
    }

    public Optional<Publisher> getByName(String publisherName) {
        return publisherRepository.findAll().stream()
                .filter(publisher -> publisher.getName().equals(publisherName))
                .findFirst();
    }
}
