import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class Teste_Denis {

	@Test
	public void test() {
		//inicio testes
		//Prato(double preco, Restaurante restaurante, SpecPrato spec)
		//Restaurante(String nome, Login conta, List<Prato> pratos)
		//SpecPrato(String nomePrato, String ingredientes)
		//Usuario(String email, int idade, String sexo, Login conta)
		//Login(String nomeUsuario, String senha)
		
		/*------------------------------------------ Teste 01 ------------------------------------------
ok		- Adicionar usuário
ok		- Verificar se já existe
ok		- Confirmar se foi adicionado
		------------------------------------------ Teste 02 ------------------------------------------
ok		- Adicionar Restaurante
ok		- Verificar se já existe
ok		- Confirmar se foi adicionado
		------------------------------------------ Teste 03 ------------------------------------------
ok		- Adicionar prato (Prato, Restaurante)
ok		- Verificar se o prato existe nesse restaurante
ok		- Confirmar se foi adicionado
		------------------------------------------ Teste 04 ------------------------------------------
ok		- Excluir prato (Prato, Restaurante)
ok		- Verificar se o prato existe nesse restaurante
ok		- Confirmar se foi exluído
		------------------------------------------ Teste 05 ------------------------------------------
NAO		- Buscar prato (nome prato)
NAO		- Buscar prato (ingrediente)
		------------------------------------------ Teste 06 ------------------------------------------
OK		- Logar restaurante
OK		- Verificar se restaurante está logado
NAO		- Logar usuário
NAO		- Verificar se usuário está logado
		---------------------------------------------------------------------------------------------*/
		
		
		
		//------------------------------------------ Teste 01 ------------------------------------------
		DBUsuarios listUsu = new DBUsuarios();
		listUsu.addUsuario(new Usuario("user1@site.com", 25, "m", new Login("user1", "12345")));
		listUsu.addUsuario(new Usuario("user2@site.com", 20, "f", new Login("user2", "12345")));
		listUsu.addUsuario(new Usuario("user3@site.com", 30, "m", new Login("user3", "12345")));
		
		assertEquals(listUsu.getUsuarios().size(), 3);
		
		//Testa nome usuário repetido
		//listUsu.addUsuario(new Usuario("user3@site.com", 30, "m", new Login("user3", "12345")));
		//assertEquals(listUsu.getUsuarios().size(), 4);
		
		//------------------------------------------ Teste 02 ------------------------------------------
		DBRestaurante listRest = new DBRestaurante();
		listRest.addRestaurante(new Restaurante("Restaurante A", new Login("restA", "12345")));
		listRest.addRestaurante(new Restaurante("Restaurante B", new Login("restB", "12345")));
		listRest.addRestaurante(new Restaurante("Restaurante C", new Login("restC", "12345")));
		listRest.addRestaurante(new Restaurante("Restaurante D", new Login("restD", "12345")));
		listRest.addRestaurante(new Restaurante("Restaurante E", new Login("restE", "12345")));
		
		assertEquals(listRest.getRestaurantes().size(), 5);
	
		//Testa nome usuário repetido
		listRest.addRestaurante(new Restaurante("Restaurante E", new Login("restA", "12345")));
		assertEquals(listRest.getRestaurantes().size(), 5);
		
		//------------------------------------------ Teste 03 ------------------------------------------
		DBPrato listPrato = new DBPrato();
		listPrato.addPrato(new Prato(49.90, listRest.getRestaurante("Restaurante A"), new SpecPrato("Prato 1", "Arroz, feijão")));
		listPrato.addPrato(new Prato(49.90, listRest.getRestaurante("Restaurante A"), new SpecPrato("Prato 2", "Arroz, feijão")));
		listPrato.addPrato(new Prato(49.90, listRest.getRestaurante("Restaurante A"), new SpecPrato("Prato 3", "Arroz, feijão")));

		assertEquals(listPrato.getPratos().size(), 3);
		
		//Teste prato JÁ existente
		listPrato.addPrato(new Prato(49.90, listRest.getRestaurante("Restaurante A"), new SpecPrato("Prato 3", "Arroz, feijão")));
		assertEquals(listPrato.getPratos().size(), 3);
		
		//------------------------------------------ Teste 04 ------------------------------------------
		
		listPrato.excluirPrato("Prato 2", "Restaurante A");		
		assertEquals(listPrato.getPratos().size(), 2);
		
		//Teste remover prato NÃO existente
		//listPrato.excluirPrato("Prato 1", "Restaurante B");
		//assertEquals(listPrato.getPratos().size(), 1);
		
		//------------------------------------------ Teste 05 ------------------------------------------
		
		
		
		//------------------------------------------ Teste 06 ------------------------------------------
		//Teste SEM logar restaurante (deve retornar false)
		Restaurante restB = listRest.getRestaurante("Restaurante B");
		boolean status = restB.getConta().getStatus();
		assertEquals(status, false);
		
		//Teste APÓS logar restaurante (deve retornar true)
		boolean novoStatus = restB.getConta().logar(new Login(restB.getConta().getNomeUsuario(), restB.getConta().getSenha()));
		assertEquals(novoStatus, true);

		
	}

}
