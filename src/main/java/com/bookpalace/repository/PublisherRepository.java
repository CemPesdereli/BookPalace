package com.bookpalace.repository;

import com.bookpalace.model.Publisher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PublisherRepository {
    private List<Publisher> publishers = new ArrayList<>();

    public void save(Publisher publisher) {
        publishers.add(publisher);
    }

    public List<Publisher> findAll() {
        return publishers;
    }
}
