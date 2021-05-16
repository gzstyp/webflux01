package com.fwtai.repository;

import com.fwtai.entity.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepository extends ReactiveCrudRepository<Student,Long> {}