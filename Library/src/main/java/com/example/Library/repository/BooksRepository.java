package com.example.Library.repository;

import com.example.Library.entities.BooksEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends CrudRepository<BooksEntity, Integer> {
}
