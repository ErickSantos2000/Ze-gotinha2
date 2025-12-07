package com.example.demo.repository;

import com.example.demo.model.Dose;
import com.example.demo.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoa, Integer> {
}
