package com.example.demo;

import com.example.demo.service.VacinaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private VacinaService vacinaService;

	@Test
	void contextLoads() {
	}

	@Test
	void testVacinarPessoa() {
		// Garante que a fila de pessoas e a pilha de frascos estão vazias no início
		assertTrue(vacinaService.getFila().isEmpty(), "A fila de pessoas deveria começar vazia.");
		assertTrue(vacinaService.getPilha().isEmpty(), "A pilha de frascos deveria começar vazia.");

		// Adiciona uma pessoa à fila
		vacinaService.adicionarPessoaFila("João da Silva", "111.222.333-44", 30);
		assertEquals(1, vacinaService.getFila().size(), "A fila deveria ter 1 pessoa após adição.");

		// Empilha um frasco de vacina
		vacinaService.empilharFrasco("Pfizer");
		assertEquals(1, vacinaService.getPilha().size(), "A pilha deveria ter 1 frasco após adição.");

		// Executa o método de vacinação
		vacinaService.vacinarPessoa();

		// Verifica o resultado
		// Como o frasco padrão tem 5 doses, a pessoa foi vacinada e removida da fila.
		assertEquals(0, vacinaService.getFila().size(), "A fila de pessoas deveria estar vazia após a vacinação.");
		assertEquals(1, vacinaService.getPilha().size(), "A pilha de frascos ainda deve conter 1 frasco.");

		// O frasco agora deve ter 4 doses restantes (considerando que começa com 5)
		assertEquals(4, vacinaService.getPilha().peek().getDosesDiponiveis().size(), "O frasco deveria ter 4 doses restantes.");
	}
}
