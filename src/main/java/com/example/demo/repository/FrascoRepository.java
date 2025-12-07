package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Frasco;

@Repository
public interface FrascoRepository extends JpaRepository<Frasco, Integer> {
}
