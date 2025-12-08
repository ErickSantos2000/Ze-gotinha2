package com.example.demo;

import com.example.demo.service.VacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private VacinaService vacinaService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n--- MENU DE VACINAÇÃO ---");
			System.out.println("1. Adicionar Pessoa à Fila");
			System.out.println("2. Vacinar Próxima Pessoa");
			System.out.println("3. Empilhar Frasco de Vacina");
			System.out.println("4. Ver Status Atual");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");

			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, digite um número.");
				choice = -1;
				continue;
			}


			switch (choice) {
				case 1:
					System.out.print("Nome da Pessoa: ");
					String nome = scanner.nextLine();
					System.out.print("CPF da Pessoa: ");
					String cpf = scanner.nextLine();
					System.out.print("Idade da Pessoa: ");
					int idade = 0;
					try {
						idade = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Idade inválida. Operação cancelada.");
						break;
					}
					vacinaService.adicionarPessoaFila(nome, cpf, idade);
					System.out.println("Pessoa " + nome + " adicionada à fila.");
					break;
				case 2:
					vacinaService.vacinarPessoa();
					break;
				case 3:
					System.out.print("Nome da Vacina (para o frasco): ");
					String nomeDoseFrasco = scanner.nextLine();
					if(vacinaService.empilharFrasco(nomeDoseFrasco)){
						System.out.println("Frasco de " + nomeDoseFrasco + " empilhado.");
					} else {
						System.out.println("Não foi possível empilhar o frasco.");
					}
					break;
				case 4:
					System.out.println("\n--- STATUS ATUAL ---");
					System.out.println("Pessoas na fila: " + vacinaService.getFila().size());
					if (!vacinaService.getFila().isEmpty()) {
						System.out.println("Próxima pessoa na fila: " + vacinaService.getFila().peek().getNome());
					}
					System.out.println("Frascos na pilha: " + vacinaService.getPilha().size());
					if (!vacinaService.getPilha().isEmpty()) {
						String nomeFrascoDisplay = vacinaService.getPilha().peek().getNomeDose();
						if (nomeFrascoDisplay == null || nomeFrascoDisplay.isEmpty()) {
							nomeFrascoDisplay = "Frasco sem nome (Corrigir FrascoService.criarFrascos)";
						}
						System.out.println("Frasco no topo: " + nomeFrascoDisplay);

						long dosesRestantes = vacinaService.getPilha().peek().getDosesDiponiveis().stream().filter(d -> !d.getAplicada()).count();
						System.out.println("Doses restantes no frasco: " + dosesRestantes);
					}
					break;
				case 0:
					System.out.println("Saindo do sistema. Obrigado!");
					break;
				default:
					System.out.println("Opção inválida. Por favor, tente novamente.");
			}
		} while (choice != 0);

		scanner.close();
	}
}