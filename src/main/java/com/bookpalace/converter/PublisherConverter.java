package com.bookpalace.converter;

import com.bookpalace.dto.request.PublisherSaveRequest;
import com.bookpalace.model.Publisher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class  PublisherConverter {

    public static Publisher toPublisher(PublisherSaveRequest request) {
        return Publisher.builder()
                .name(request.getName())
                .creatDate(request.getCreateDate())
                .build();
    }
}
