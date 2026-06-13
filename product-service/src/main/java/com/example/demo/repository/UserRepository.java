package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.document.UserDocument;

@Repository
public interface UserRepository
        extends ElasticsearchRepository<UserDocument, String> {

    Optional<UserDocument> findByUsername(String username);

}
