package com.example.eshragh.repository;

import com.example.eshragh.aop.exception.log.LogModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogModel, String> {
}
