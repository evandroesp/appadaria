package Testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import Cadastros.*;
import Contabilidade.*;
import Outros.*;
import Produtos.*;

public class Appadaria {

	Pessoa userAuth;

	public Appadaria() {
		Cadastros.registroPessoas = new CadastroPessoas();;
		Cadastros.depositoInsumos = new CadastroInsumos();;
		Cadastros.depositoDerivados = new CadastroDerivados();;
		Cadastros.registroCotacoes = new CadastroCotacaoInsumo();;
		Cadastros.registroEncomendas = new CadastroEncomendas();;
		Cadastros.registroContabil = new CadastroContabil();
		Cadastros.taxaLucro = 1.12;
	}
	
	public void balanco() {
	System.out.print(String.format("\nR$ -- Contabilidade -- R$\nInsumos: %.2f   Derivado: %.2f   Caixa: %.2f    Total: %.2f\n",
			Cadastros.depositoInsumos.getValorAgregado(), Cadastros.depositoDerivados.getValorAgregado(), Cadastros.registroContabil.getDinheiro(),
			Cadastros.depositoInsumos.getValorAgregado()+ Cadastros.depositoDerivados.getValorAgregado()+ Cadastros.registroContabil.getDinheiro())
			);
	}

	public void criar() {
		Pessoa adminT = new Pessoa(999999,"admin","admin");
		Cadastros.registroPessoas.addPessoa(adminT);
		Cadastros.registroPessoas.addPessoa(new Pessoa(7001, "Evandro","0047171","9630","4657316","Rua 77",false,true, "1234", Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(7002, "Marco","001","99554433","123456","Rua 33",false,true, "1234", Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(7003, "Ramiro","002","99887766","654321","Rua 55",false,true, "1234", Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(3, "Mercado Sol","1001564840001","5133333333","4561324","Av. Nuvem Azul",true,false,null, Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(4, "Restaurante Lua","1004564890001","51443333","946573","Praça Cheia",true,false,null, Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(2, "Joana","246573","51433","946573","Av. Casa Grande",false,false,null, Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(1, "Gordo Joe","12346","11332131","45132","Av. 3 Moscas",false,false,null, Cadastros.registroPessoas.getPessoaPorCodigo(999999)));

		Cadastros.depositoInsumos.addInsumo(new Insumo(1,"Banana","un",Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Cadastros.depositoInsumos.addInsumo(new Insumo(2,"Sorvete","L",Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Cadastros.depositoInsumos.addInsumo(new Insumo(3,"Leite","L",Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Cadastros.depositoInsumos.addInsumo(new Insumo(4,"Chocolate","kg",Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Cadastros.depositoInsumos.addInsumo(new Insumo(5,"Pote 2L","pc",Cadastros.registroPessoas.getPessoaPorCodigo(7001)));

		Calendar data1 = new GregorianCalendar(2017, 5, 10);
		Calendar data5 = new GregorianCalendar(2017, 6, 10);
		Calendar data6 = new GregorianCalendar(2017, 7, 10);
		Calendar data2 = new GregorianCalendar(2018, 1, 15);
		Calendar data3 = new GregorianCalendar(2017, 10, 25);
		Calendar data4 = new GregorianCalendar(2017, 9, 25);

		Cadastros.depositoInsumos.getInsumoPorCodigo(1).getEstoque().addEstoque(new Estoque(100,2.4,data2,adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(1).getEstoque().addEstoque(new Estoque(100,3,data1,adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(2).getEstoque().addEstoque(new Estoque(70,6.35,data2,adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(2).getEstoque().addEstoque(new Estoque(70,10,data3,adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(3).getEstoque().addEstoque(new Estoque(200,2,data1,adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(4).getEstoque().addEstoque(new Estoque(300,2,new GregorianCalendar(2018, 10, 1),adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(5).getEstoque().addEstoque(new Estoque(50,2,new GregorianCalendar(3000, 1, 1),adminT));

		Cadastros.depositoDerivados.addDerivado(new Derivado(1,"Banana Split", "porcao" , Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(1).setReceita(new Receita("Coloque 2 bananas na tingela e coloque 500ml de sorvete", 1, 3));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(1).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(1), 2);
		Cadastros.depositoDerivados.getDerivadoPorCodigo(1).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(2), 0.5);

		Cadastros.depositoDerivados.addDerivado(new Derivado(2, "Milkshake de Banana", "copo" , Cadastros.registroPessoas.getPessoaPorCodigo(7002)));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(2).setReceita(new Receita("Coloque 2 bananas, 250ml sorvete, 200ml de leite no liquidificador, apos servir na taca", 1, 2));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(2).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(1), 2);
		Cadastros.depositoDerivados.getDerivadoPorCodigo(2).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(2), 0.25);
		Cadastros.depositoDerivados.getDerivadoPorCodigo(2).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(3), 0.2);

		Cadastros.depositoDerivados.addDerivado(new Derivado(3, "Sorvete Chocolate 2L", "pote" , Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(3).setReceita(new Receita("Processar 1.5L de sorvete com 500g de chocolate e colocar no pote", 1, 400));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(3).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(2), 1.5);
		Cadastros.depositoDerivados.getDerivadoPorCodigo(3).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(4), 0.5);
		Cadastros.depositoDerivados.getDerivadoPorCodigo(3).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(5), 1);

		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(1), Cadastros.registroPessoas.getPessoaPorCodigo(1), 2.4, data1, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(2), Cadastros.registroPessoas.getPessoaPorCodigo(2), 7, data3, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(1), Cadastros.registroPessoas.getPessoaPorCodigo(3), 3, data1, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(2), Cadastros.registroPessoas.getPessoaPorCodigo(4), 6.35, data3, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(1), Cadastros.registroPessoas.getPessoaPorCodigo(2), 10, data3, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(3), Cadastros.registroPessoas.getPessoaPorCodigo(7001), 2, data3, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(4), Cadastros.registroPessoas.getPessoaPorCodigo(7002), 13.2, data3, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(5), Cadastros.registroPessoas.getPessoaPorCodigo(7003), 0.6, data3, adminT);

		Cadastros.registroContabil.addLancamento(adminT, new LancamentoVenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(3),110,15,Cadastros.registroPessoas.getPessoaPorCodigo(2),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(new GregorianCalendar(2016, 7, 15));
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoVenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(2),100,9,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(new GregorianCalendar(2016, 9, 14));
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoVenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(1),256,16,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(data1);		


		Cadastros.registroContabil.addLancamento(adminT, new LancamentoCompra(Cadastros.depositoInsumos.getInsumoPorCodigo(1),100,3,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(data5);
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoCompra(Cadastros.depositoInsumos.getInsumoPorCodigo(3),200,2,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(data6);
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoCompra(Cadastros.depositoInsumos.getInsumoPorCodigo(2),70,10,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(data4);

		Cadastros.registroContabil.addLancamento(adminT, new LancamentoCompra(Cadastros.depositoInsumos.getInsumoPorCodigo(3),5,5,Cadastros.registroPessoas.getPessoaPorCodigo(2),adminT));
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoCompra(Cadastros.depositoInsumos.getInsumoPorCodigo(4),5,5,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoVenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(1),50,20,Cadastros.registroPessoas.getPessoaPorCodigo(4),adminT));
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoVenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(2),256,4,Cadastros.registroPessoas.getPessoaPorCodigo(3),adminT));

		Cadastros.registroEncomendas.addEncomenda(new Encomenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(1), 40, Cadastros.registroPessoas.getPessoaPorCodigo(2), new GregorianCalendar(2017, 10, 18), adminT));
		Cadastros.registroEncomendas.addEncomenda(new Encomenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(2), 40, Cadastros.registroPessoas.getPessoaPorCodigo(1), new GregorianCalendar(2018, 5, 12), adminT));
	}

	public void criar2() {
		Pessoa adminT = new Pessoa(999999,"admin","admin");
		Cadastros.registroPessoas.addPessoa(adminT);
		Cadastros.registroPessoas.addPessoa(new Pessoa(7001, "Evandro","0047171","9630","4657316","Rua 77",false,true, "1234", Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(7002, "Marco","001","99554433","123456","Rua 33",false,true, "1234", Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(7003, "Ramiro","002","99887766","654321","Rua 55",false,true, "1234", Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(3, "Mercado Sol","1001564840001","5133333333","4561324","Av. Nuvem Azul",true,false,null, Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(4, "Restaurante Lua","1004564890001","51443333","946573","Praça Cheia",true,false,null, Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(2, "Joana","246573","51433","946573","Av. Casa Grande",false,false,null, Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.registroPessoas.addPessoa(new Pessoa(1, "Gordo Joe","12346","11332131","45132","Av. 3 Moscas",false,false,null, Cadastros.registroPessoas.getPessoaPorCodigo(999999)));

		Cadastros.depositoInsumos.addInsumo(new Insumo(1,"Banana","un",Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Cadastros.depositoInsumos.addInsumo(new Insumo(2,"Sorvete","L",Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Cadastros.depositoInsumos.addInsumo(new Insumo(3,"Leite","L",Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Cadastros.depositoInsumos.addInsumo(new Insumo(4,"Chocolate","kg",Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Cadastros.depositoInsumos.addInsumo(new Insumo(5,"Pote 2L","pc",Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Calendar data1 = new GregorianCalendar(2017, 5, 10);
		Calendar data5 = new GregorianCalendar(2017, 6, 10);
		Calendar data6 = new GregorianCalendar(2017, 7, 10);
		Calendar data2 = new GregorianCalendar(2018, 1, 15);
		Calendar data3 = new GregorianCalendar(2017, 10, 25);
		Calendar data4 = new GregorianCalendar(2017, 9, 25);

		Cadastros.depositoInsumos.getInsumoPorCodigo(1).getEstoque().addEstoque(new Estoque(100,8,data2,adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(1).getEstoque().addEstoque(new Estoque(100,10,data1,adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(2).getEstoque().addEstoque(new Estoque(70,19,data2,adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(2).getEstoque().addEstoque(new Estoque(50,21,data3,adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(3).getEstoque().addEstoque(new Estoque(200,6.7,data1,adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(4).getEstoque().addEstoque(new Estoque(300,17,new GregorianCalendar(2018, 10, 1),adminT));
		Cadastros.depositoInsumos.getInsumoPorCodigo(5).getEstoque().addEstoque(new Estoque(50,0.4,new GregorianCalendar(3000, 1, 1),adminT));

		Cadastros.depositoDerivados.addDerivado(new Derivado(1, "Banana Split", "porcao" , Cadastros.registroPessoas.getPessoaPorCodigo(7001)));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(1).setReceita(new Receita("Coloque 2 bananas na tingela e coloque 500ml de sorvete", 1, 3));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(1).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(1), 2);
		Cadastros.depositoDerivados.getDerivadoPorCodigo(1).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(2), 0.5);

		Cadastros.depositoDerivados.addDerivado(new Derivado(2, "Milkshake de Banana", "copo" , Cadastros.registroPessoas.getPessoaPorCodigo(7002)));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(2).setReceita(new Receita("Coloque 2 bananas, 250ml sorvete, 200ml de leite no liquidificador, apos servir na taca", 1, 2));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(2).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(1), 2);
		Cadastros.depositoDerivados.getDerivadoPorCodigo(2).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(2), 0.25);
		Cadastros.depositoDerivados.getDerivadoPorCodigo(2).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(3), 0.2);

		Cadastros.depositoDerivados.addDerivado(new Derivado(3, "Sorvete Chocolate 2L", "pote" , Cadastros.registroPessoas.getPessoaPorCodigo(999999)));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(3).setReceita(new Receita("Processar 1.5L de sorvete com 500g de chocolate e colocar no pote", 1, 400));
		Cadastros.depositoDerivados.getDerivadoPorCodigo(3).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(2), 1.5);
		Cadastros.depositoDerivados.getDerivadoPorCodigo(3).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(4), 0.5);
		Cadastros.depositoDerivados.getDerivadoPorCodigo(3).getReceita().adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorCodigo(5), 1);

		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(1), Cadastros.registroPessoas.getPessoaPorCodigo(1), 2.4, data1, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(2), Cadastros.registroPessoas.getPessoaPorCodigo(2), 7, data3, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(1), Cadastros.registroPessoas.getPessoaPorCodigo(3), 3, data1, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(2), Cadastros.registroPessoas.getPessoaPorCodigo(4), 6.35, data3, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(1), Cadastros.registroPessoas.getPessoaPorCodigo(2), 10, data3, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(3), Cadastros.registroPessoas.getPessoaPorCodigo(7001), 2, data3, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(4), Cadastros.registroPessoas.getPessoaPorCodigo(7002), 13.2, data3, adminT);
		Cadastros.registroCotacoes.addCotacao(Cadastros.depositoInsumos.getInsumoPorCodigo(5), Cadastros.registroPessoas.getPessoaPorCodigo(7003), 0.6, data3, adminT);
		/**/
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoVenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(3),110,15,Cadastros.registroPessoas.getPessoaPorCodigo(2),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(new GregorianCalendar(2016, 7, 15));
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoVenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(2),100,9,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(new GregorianCalendar(2016, 9, 14));
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoVenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(1),256,16,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(data1);		


		Cadastros.registroContabil.addLancamento(adminT, new LancamentoCompra(Cadastros.depositoInsumos.getInsumoPorCodigo(1),100,3,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(data5);
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoCompra(Cadastros.depositoInsumos.getInsumoPorCodigo(3),200,2,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(data6);
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoCompra(Cadastros.depositoInsumos.getInsumoPorCodigo(2),70,10,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.getUltimoRegistro(adminT).setData(data4);
		
		/*Lancando no dia atual 
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoCompra(Cadastros.depositoInsumos.getInsumoPorCodigo(3),5,5,Cadastros.registroPessoas.getPessoaPorCodigo(2),adminT));
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoCompra(Cadastros.depositoInsumos.getInsumoPorCodigo(4),5,5,Cadastros.registroPessoas.getPessoaPorCodigo(1),adminT));
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoVenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(1),50,20,Cadastros.registroPessoas.getPessoaPorCodigo(4),adminT));
		Cadastros.registroContabil.addLancamento(adminT, new LancamentoVenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(2),256,4,Cadastros.registroPessoas.getPessoaPorCodigo(3),adminT));
		*/
		
		Cadastros.registroEncomendas.addEncomenda(new Encomenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(1), 40, Cadastros.registroPessoas.getPessoaPorCodigo(2), new GregorianCalendar(2017, 10, 18), adminT));
		Cadastros.registroEncomendas.addEncomenda(new Encomenda(Cadastros.depositoDerivados.getDerivadoPorCodigo(2), 40, Cadastros.registroPessoas.getPessoaPorCodigo(1), new GregorianCalendar(2018, 5, 12), adminT));
	}

	public void simularLogin() {
		userAuth=null;
		int codigoUser; String senha;
		codigoUser = 999999;	//admin
		//codigoUser = 7001;	//funcionarios
		//codigoUser = 7002;	//funcionarios
		//codigoUser = 7003;	//funcionarios
		senha ="admin";		//admin
		//senha ="1234";		//funcionarios
		System.out.println("USER: " + codigoUser);
		System.out.println("PASS: " + senha);
		if (Cadastros.registroPessoas.getPessoaPorCodigo(codigoUser).getSenha().equals(senha)) {
			userAuth = Cadastros.registroPessoas.getPessoaPorCodigo(codigoUser);
			System.out.println(userAuth.getCodNome() + " Logado com sucesso!");
		}
		else
			System.out.println("Login ou senha incorretos");
	}

	public void sim_logarAdmin() {
		userAuth = Cadastros.registroPessoas.getPessoaPorCodigo(999999);	
	}

	public void sim_criaInsumo_CompraEstoque() {
		if(userAuth==null) {
			sim_logarAdmin();
		}

		System.out.println("\n\n----CRIA INSUMO, COMPRA ESTOQUE");
		//criando insumo
		String nome, unidade;
		nome = "Farinha de Trigo";
		unidade = "kg";
		Insumo novoInsumo = new Insumo(Cadastros.depositoInsumos.getNextCodigo(),nome,unidade,userAuth);

		//Cadastrando
		Cadastros.depositoInsumos.addInsumo(novoInsumo);
		System.out.println("\n\nCadastrando novo Insumo");
		System.out.println(novoInsumo.getCodNome());

		//contabilidade
		balanco();

		//adicionando estoque
		double quantidade = 30;
		double preco = 4.5;
		Calendar dataVencimento = new GregorianCalendar(2018, 5, 25);
		Estoque novoEstoque = new Estoque(quantidade, preco, dataVencimento, userAuth);
		novoInsumo.getEstoque().addEstoque(novoEstoque);

		//lancamento contabil
		LancamentoCompra lance = new LancamentoCompra(novoInsumo, quantidade, preco, Cadastros.registroPessoas.getPessoaPorCodigo(3), userAuth);
		System.out.printf("\n\nComprando Estoque: %s %s por R$ %.2f/%s   = %.2f" , quantidade, unidade, preco, unidade, preco*quantidade);
		Cadastros.registroContabil.addLancamento(userAuth, lance);

		//contabilidade
		balanco();
	}

	public void sim_criaDerivado_Receita_Produzir() {
		if(userAuth==null) {
			sim_logarAdmin(); 
		}

		System.out.println("\n\n----CRIA DERIVADO, ADD RECEITA E PRODUZ");
		//criando derivado
		String nome, unidade;
		nome = "Cupcake Split";
		unidade = "bolo";
		Derivado novoDerivado = new Derivado(Cadastros.depositoDerivados.getNextCodigo(),nome,unidade,userAuth);

		//Cadastrando
		Cadastros.depositoDerivados.addDerivado(novoDerivado);
		System.out.println("\n\nCadastrando novo Derivado");
		System.out.println(novoDerivado.getCodNome());

		//criando receita
		String descricaoPreparo = "misture todos os ingredientes, divida em 10 bolinhos e leve ao freezer";
		double rendimento = 10;
		int diasParaVencimento = 50;
		Receita novaReceita = new Receita(descricaoPreparo,rendimento,diasParaVencimento);
		novoDerivado.setReceita(novaReceita);

		//adicionando ingrediente
		novaReceita.adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorDescricao("Banana"), 4);
		novaReceita.adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorDescricao("Leite"), 0.4);
		novaReceita.adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorDescricao("Sorvete"), 0.4);
		novaReceita.adicionaIngrediente(Cadastros.depositoInsumos.getInsumoPorDescricao("Farinha de Trigo"), 0.4);

		System.out.print(novoDerivado);
		System.out.println(novaReceita);
		System.out.println("Limite para produzir: " + novaReceita.limiteGeralProducao());

		//contabilidade
		balanco();

		//produzir Derivado
		double qtd = 36;	//limite 50
		novoDerivado.operarProduzir(qtd, userAuth);

		System.out.print("\n" + novoDerivado);
		System.out.println(novaReceita);
		System.out.println("Limite para produzir: " + novaReceita.limiteGeralProducao());

		//contabilidade
		balanco();

		//taxa de lucro sobre os precos medios dos ingredientes
		System.out.print("Taxa de lucro: " + Cadastros.taxaLucro + "\n" + novoDerivado);
	}

	public void sim_pegarEncomenda_Produzir_Entregar() {
		if(userAuth==null) {
			sim_logarAdmin(); 
		}

		System.out.println("\n\n----ENCONTRA UMA ENCOMENDA, PRODUZ E ENTREGA");

		//procurando encomenda
		System.out.println(Cadastros.registroEncomendas.getRelatorioPorPrazo());
		//olhar primeira encomenda
		Encomenda encomendaEscolhida = Cadastros.registroEncomendas.getEncomenda(1);
		System.out.println(encomendaEscolhida.getDerivado());
		System.out.println(encomendaEscolhida.getDerivado().getReceita());
		System.out.println("Limite da Producao: " + encomendaEscolhida.getDerivado().getReceita().limiteGeralProducao());

		//comprar insumo faltante atraves de cotacao
		Insumo insumoFaltante = Cadastros.depositoInsumos.getInsumoPorCodigo(1);
		System.out.println(Cadastros.registroCotacoes.getRelatorioInsumoEspecifico(insumoFaltante));
		//pegando menor cotacao
		InsumoPrecoFornecedor cotaInsumoFaltante = Cadastros.registroCotacoes.getCotacaoPorInsumo(insumoFaltante, 1);
		System.out.println(insumoFaltante);
		
		//preparando compra
		double quantidade = 40;
		double preco = cotaInsumoFaltante.getCotacao();
		Pessoa cliente = cotaInsumoFaltante.getFornecedor();
		
		//contabilidade
		balanco();

		//adicionando estoque
		Calendar dataVencimento = new GregorianCalendar(2017, 11, 25);
		Estoque novaCompra = new Estoque(quantidade, preco, dataVencimento, userAuth);
		insumoFaltante.getEstoque().addEstoque(novaCompra);

		//lancamento contabil
		LancamentoCompra lanceCompra = new LancamentoCompra(insumoFaltante, quantidade, preco, cliente, userAuth);
		System.out.printf("\nComprando Estoque: %s %s por R$ %.2f/%s   = %.2f\n" , quantidade, insumoFaltante.getUnidade(), preco, insumoFaltante.getUnidade(), preco*quantidade);
		Cadastros.registroContabil.addLancamento(userAuth, lanceCompra);
		System.out.println(insumoFaltante);
		
		//contabilidade
		balanco();
		System.out.println("\nPerda devido desvalorizacao da banana");
		
		//retomando encomenda
		System.out.println(Cadastros.registroEncomendas.getRelatorioPorPrazo());
		System.out.println(encomendaEscolhida.getDerivado());
		System.out.println(encomendaEscolhida.getDerivado().getReceita());
		System.out.println("Limite da Producao: " + encomendaEscolhida.getDerivado().getReceita().limiteGeralProducao());
		
		//produzir
		encomendaEscolhida.getDerivado().operarProduzir(encomendaEscolhida.getQtd(), userAuth);
		System.out.println(encomendaEscolhida.getDerivado());
		balanco();
		
		//atender e lancar
		Derivado derEnc = encomendaEscolhida.getDerivado();
		double qtdEnc = encomendaEscolhida.getQtd();
		double prcEnc = derEnc.getPrecoAtual();
		Pessoa cliEnc = encomendaEscolhida.getCliente();
		
		LancamentoVenda lanceVenda = new LancamentoVenda(derEnc, qtdEnc, prcEnc, cliEnc, userAuth);
		Cadastros.registroContabil.addLancamento(userAuth, lanceVenda);
		derEnc.getEstoque().setDecremento(qtdEnc);
		Cadastros.registroEncomendas.removeEncomenda(encomendaEscolhida);
		
		//conclusao
		System.out.println(encomendaEscolhida.getDerivado());
		System.out.println("Limite da Producao: " + encomendaEscolhida.getDerivado().getReceita().limiteGeralProducao());
		balanco();
		System.out.println("\n" + Cadastros.registroEncomendas.getRelatorioPorPrazo());
		
	}
	
	public void sim_relatorioContabilHoje() {
		
		System.out.println("\n\n----LANCAMENTOS CONTABEIS");
		
		//truncate DATA, set apenas HORA = 0;
		Calendar dataIni = new GregorianCalendar();
		dataIni.set(Calendar.HOUR_OF_DAY,0);
		
		System.out.println(Cadastros.registroContabil.getRelatorioEspecifico(null, null, null, null));
		System.out.println(Cadastros.registroContabil.getRelatorioEspecifico(3,dataIni));
		
		//todos lancamentos
		dataIni.clear();
		System.out.println(Cadastros.registroContabil.getRelatorioEspecifico(null, null, dataIni, null));
		//System.out.println(Cadastros.registroContabil.getRelatorioEspecifico(3,dataIni));
		
		//todos lancamentos de compra
		System.out.println(Cadastros.registroContabil.getRelatorioEspecifico(1,dataIni));
		
		Derivado escolheDerivado = Cadastros.depositoDerivados.getDerivadoPorCodigo(1);
		System.out.println(Cadastros.registroContabil.getRelatorioEspecifico(escolheDerivado, null, dataIni, null));
		
		Pessoa escolheCliente = Cadastros.registroPessoas.getPessoaPorCodigo(1);
		System.out.println(Cadastros.registroContabil.getRelatorioEspecifico(null, escolheCliente, dataIni, null));
	}

	public void teste() {
		System.out.println(Cadastros.registroEncomendas.getRelatorioDisponibilidade());

	}
}
