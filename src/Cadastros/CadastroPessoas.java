package Cadastros;
import java.util.*;

import Outros.Pessoa;

public class CadastroPessoas{

	public ArrayList<Pessoa> listaPessoas;

	public CadastroPessoas(){
		listaPessoas = new ArrayList<Pessoa>();
	}

	public boolean addPessoa(Pessoa pessoaT){
		/*
		for(Pessoa x:listaPessoas){
			if(x.getRegistro().equals(pessoaT.getRegistro()))
				return false;
		}
		if (pessoaT.getCodigo()==0){
			pessoaT.setCodigo(getNextCodigo());
		}
		else
			for(Pessoa x:listaPessoas)
				if(x.getCodigo()==pessoaT.getCodigo())
					pessoaT.setCodigo(getNextCodigo());
		 */
		listaPessoas.add(pessoaT);
		return true;
	}

	public boolean removePessoa(int codigo){
		for(Pessoa x:listaPessoas){
			if(x.getCodigo()==codigo){
				listaPessoas.remove(x);
				return true;
			}
		}
		return false;
	}
	/*
	    public void AlteraCliente(String nome, String reg, int tel, int cep, String end, int cod){
        for(Pessoa x:listaPessoas){
            if(x.getCodigo()==(cod)){
                x.setNome(nome);
            }
        }
    }
	*/  
	public boolean alteraCodigo(Pessoa pessoaT,int novoCod){
		for(Pessoa x:listaPessoas)
			if(x.getCodigo()==novoCod)
				return false;
		pessoaT.setCodigo(novoCod);
		return true;
	}

	public Pessoa getPessoaPorCodigo(int codigo){
		for(Pessoa x:listaPessoas){
			if(x.getCodigo()==codigo){
				return x;
			}
		}
		return null;
	}

	public Pessoa getPessoaPorNome(String nome){
		for(Pessoa x:listaPessoas){
			if(x.getNome().equals(nome)){
				return x;
			}
		}
		return null;
	}

	public Pessoa getPessoaPorRegistro(String registro){
		for(Pessoa x:listaPessoas){
			if(x.getRegistro().equals(registro)){
				return x;
			}
		}
		return null;
	}

	public int getNextCodigo() {
		ordenaPessoaPorCodigo();
		int i=0;
		for(i=0;i<listaPessoas.size();i++) {
			if(listaPessoas.get(i).getCodigo()!=(i+1))
				return i+1;
		}
		return i+1;
	}

	public void ordenaPessoaPorCodigo() {
		Pessoa pessoaT;
		for(int i=0;i<listaPessoas.size();i++)
			for(int j=i+1;j<listaPessoas.size();j++) {
				if(listaPessoas.get(i).getCodigo()>listaPessoas.get(j).getCodigo()) {
					pessoaT = listaPessoas.get(i);
					listaPessoas.set(i,listaPessoas.get(j));
					listaPessoas.set(j, pessoaT);
				}
			}
	}

	public String listarPessoas() {
		ordenaPessoaPorCodigo();
		String str="-----------------------\nLista de Pessoas:";
		for(Pessoa x:listaPessoas)
			str+= "\n" + x;
		return str;
	}

	/*
	public int size(){
		return listaPessoas.size();
	}
	*/

	public ArrayList<Pessoa> getArrayPessoa() {
		return listaPessoas;
	}
/*
	void setArrayC(ArrayList<Pessoa> arrayList) {
		listaPessoas = arrayList;
	}
*/
}