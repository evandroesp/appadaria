package Telas;

import java.util.Calendar;
import java.util.GregorianCalendar;

import Cadastros.Cadastros;
import Outros.Pessoa;
import Produtos.Produto;

public class TelasContabilidade extends ControleTelas{

	public static void menu_5_contabilidade() {
		do {
			cabecalho();
			System.out.println("=== RELATORIOS CONTABEIS === ");
			System.out.println("1 - Saldo Ultimo Registro");
			System.out.println("2 - Relatorio Mensal");
			System.out.println("4 - Relatorios especificos...");
			System.out.println("5 - Lancamentos...");
			System.out.println("7 - Alterar taxa de Lucro");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				System.out.println(Cadastros.registroContabil.getUltimoRegistro(userAuth).getSaldo());
				break;
			case 2:
				System.out.println(Cadastros.registroContabil.getRelatorioTotalMensal());
				break;
			case 4:
				m54_relatorios_especificos();
				break;
			case 5:
				m55_relatorios_lancamentos();
				break;
			case 7:
				//System.out.println("Taxa de lucro atual sobre derivados: " + Cadastros.registroContabil.getTaxaLucro() + "\nDigite a nova taxa de lucro: ");
				System.out.println("Taxa de lucro atual sobre derivados: " + Cadastros.taxaLucro + "\nDigite a nova taxa de lucro: ");
				Cadastros.taxaLucro = lerNumeroDouble();
				break;
			case 9:
				op = -1;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
			if(op>0)
				ajustaTela(1);

		}while(op!=-1 && op!=0);
	}

	protected static void m54_relatorios_especificos(){
		Pessoa client = null;
		Pessoa funcio = null;
		Produto produtoT = null;
		pessoaT = null;
		insumoT = null;
		derivadoT = null;
		Calendar dataIni = new GregorianCalendar(2000,1-1,1);
		Calendar dataFim = new GregorianCalendar();
		do {
			cabecalho();
			System.out.println("=== RELATORIOS CONTABEIS ESPECIFICOS === ");
			if(insumoT != null)
				System.out.println("1 - por Insumos:  \t " + insumoT.getNome());
			else
				System.out.println("1 - por Insumos:");
			if(derivadoT != null)
				System.out.println("2 - por Derivados:\t " + derivadoT.getNome());
			else
				System.out.println("2 - por Derivados: ");

			System.out.println("------------------------------");

			if(client != null)
				System.out.println("4 - por Cliente:    \t " + client.getCodNome());
			else
				System.out.println("4 - por Cliente:    \t ");
			if(funcio != null)
				System.out.println("5 - por Funcionario:\t " + funcio.getCodNome());
			else
				System.out.println("5 - por Funcionario:\t ");

			System.out.println("------------------------------");
			System.out.println("7 - Data inicial:\t" + validarData(dataIni));
			System.out.println("8 - Data final:  \t" + validarData(dataFim));
			System.out.println("777 - Consultar");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				encontrarInsumo(1);
				produtoT = insumoT;
				derivadoT = null;
				break;
			case 2:
				encontrarDerivado(1);
				produtoT = derivadoT;
				insumoT = null;
				break;
			case 4:
				encontrarPessoa();
				client = pessoaT;
				funcio = null;
				break;
			case 5:
				encontrarPessoa();
				client = null;
				funcio = pessoaT;
				break;
			case 7:
				validarData(scan.nextLine(), 1);
				dataIni = dataT;
				break;
			case 8:
				validarData(scan.nextLine(), 1);
				dataFim = dataT;
				break;
			case 777:
				System.out.println(Cadastros.registroContabil.getRelatorioEspecifico(produtoT, pessoaT, dataIni, dataFim));
				break;
			case 9:
				op = -2;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
			if(op == 777)
				ajustaTela(1);

		}while(op!=-2 && op!=0);
	}

	protected static void m55_relatorios_lancamentos() {
		int opProd=3,opPrazo=1;
		do {
			cabecalho();
			System.out.println("=== LANCAMENTOS... === ");
			if(opProd==1)
				System.out.println("1 - Insumos    (x)");
			else
				System.out.println("1 - Insumos    ( )");
			if(opProd==2)
				System.out.println("2 - Derivados  (x)");
			else
				System.out.println("2 - Derivados  ( )");
			if(opProd==3)
				System.out.println("3 - Ambos      (x)");
			else
				System.out.println("3 - Ambos      ( )");
			System.out.println("------------------------------");
			if(opPrazo==1) {
				System.out.println("4 - Hoje       (x)");
				System.out.println("5 - Mensal     ( )");
				System.out.println("6 - Anual      ( )");
			}
			else if(opPrazo==2) {
				System.out.println("4 - Hoje       ( )");
				System.out.println("5 - Mensal     (x)");
				System.out.println("6 - Anual      ( )");
			}
			else if(opPrazo==3) {
				System.out.println("4 - Hoje       ( )");
				System.out.println("5 - Mensal     ( )");
				System.out.println("6 - Anual      (x)");
			}
			System.out.println("------------------------------");
			System.out.println("777 - Consultar");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				opProd=1;
				break;
			case 2:
				opProd=2;
				break;
			case 3:
				opProd=3;
				break;
			case 4:
				opPrazo=1;
				break;
			case 5:
				opPrazo=2;
				break;
			case 6:
				opPrazo=3;
				break;
			case 777:
				m55_777_relatorios_lancamentos_rapidos(opProd, opPrazo);
				break;
			case 9:
				op = -2;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
			if(op==777)
				ajustaTela(1);

		}while(op!=-2 && op!=0);
	}

	protected static void m55_777_relatorios_lancamentos_rapidos(int opProd, int opPrazo){
		dataT = new GregorianCalendar();
		if(opPrazo==1)
			dataT.add(Calendar.DATE, -1);
		else if(opPrazo==2)
			dataT.add(Calendar.MONTH, -1);
		else if(opPrazo==3)
			dataT.add(Calendar.YEAR, -1);
		System.out.println();
		System.out.println(Cadastros.registroContabil.getRelatorioEspecifico(opProd, dataT));
	}
}
