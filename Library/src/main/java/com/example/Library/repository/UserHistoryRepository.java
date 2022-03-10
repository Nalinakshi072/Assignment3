package com.example.Library.repository;

import com.example.Library.entities.UserHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserHistoryRepository extends CrudRepository<UserHistoryEntity, Integer> {

    Optional<UserHistoryEntity> findByUserIdAndBookId(Integer userId, Integer bookId);
}
