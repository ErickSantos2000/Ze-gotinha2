package com.example.demo;

import com.example.demo.model.Frasco;
import com.example.demo.model.Pessoa;
import com.example.demo.service.DoseService;
import com.example.demo.service.FrascoService;
import com.example.demo.service.PessoaService;
import com.example.demo.service.VacinaService;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class TesteVacina {

    public static void main(String[] args) {

        Queue<Pessoa> fila = new ArrayDeque<>();
        Deque<Frasco> pilha = new ArrayDeque<>();

        DoseService doseService = new DoseService();
        PessoaService pessoaService = new PessoaService();
        FrascoService frascoService = new FrascoService(doseService);
        VacinaService vacinaService = new VacinaService(frascoService, pessoaService, fila, pilha);

        // Empilhar frascos
        vacinaService.empilharFrasco("Astrazenica");
        vacinaService.adicionarPessoaFila("Marcos Guedes", "123", 25);
        vacinaService.adicionarPessoaFila("Lucas Cavalera", "123", 25);
        vacinaService.adicionarPessoaFila("Benio Sinesio Ramos", "123", 25);
        vacinaService.adicionarPessoaFila("Leonardo Krutapali", "123", 25);
        vacinaService.adicionarPessoaFila("Vitor Gustavo", "123", 25);

        System.out.println("======================");
        for (Pessoa p : fila) {
            System.out.println(p.getNome() + " - " + p.getCpf());
        }
        System.out.println("======================");
        System.out.println("Quantidade de frasco: " + pilha.size());
        System.out.println("======================");
        for(int i = 0; i < pilha.peek().getDosesDiponiveis().size(); i++){
            System.out.println("Frascos restantes: " + pilha.peek().getDosesDiponiveis().get(i).getNome());
        }
        // Vacinar pessoas
        System.out.println("======================");
        vacinaService.vacinarPessoa();
        vacinaService.vacinarPessoa();
        vacinaService.vacinarPessoa();
        vacinaService.vacinarPessoa();
        System.out.println("======================");
        for (Pessoa p : fila) {
            System.out.println(p.getNome() + " - " + p.getCpf());
        }
        System.out.println("======================");
        for(int i = 0; i < pilha.peek().getDosesDiponiveis().size(); i++){
            System.out.println("Frascos restantes: " + pilha.peek().getDosesDiponiveis().get(i).getNome());
        }
        System.out.println("======================");







    }
}
