package com.bookpalace.converter;

import com.bookpalace.dto.request.PublisherSaveRequest;
import com.bookpalace.dto.response.ProductResponse;
import com.bookpalace.dto.response.PublisherResponse;
import com.bookpalace.model.Product;
import com.bookpalace.model.Publisher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class  PublisherConverter {

    public static Publisher toPublisher(PublisherSaveRequest request) {
        return Publisher.builder()
                .name(request.getName())
                .createDate(request.getCreateDate())
                .id(request.getId())
                .build();
    }

    public static PublisherResponse toResponse(Publisher publisher) {
        return PublisherResponse.builder()
                .name(publisher.getName())
                .createDate(publisher.getCreateDate())
                .id(publisher.getId())
                .build();
    }

    public static List<PublisherResponse> toResponse(List<Publisher> publishers) {
        return publishers
                .stream()
                .map(PublisherConverter::toResponse)
                .collect(Collectors.toList());
    }
}
