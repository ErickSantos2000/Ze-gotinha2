package com.example.demo.service;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    @Autowired
    private PessoasRepository pessoasRepository;

    public Pessoa criarPessoa(String nome, String cpf, int idade,boolean vacinada){

        return pessoasRepository.save(new Pessoa(nome, cpf, idade, vacinada)); // faz a pesistencia de dados
    }
}
