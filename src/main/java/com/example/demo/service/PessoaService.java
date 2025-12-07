package com.example.demo.service;

import com.example.demo.model.Pessoa;

public class PessoaService {

    public Pessoa criarPessoa(String nome, String cpf, int idade){
        return new Pessoa(nome, cpf, idade);
    }
}
