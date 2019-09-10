package Outros;
public class Pessoa{
    
    private String nome;
    private String registro;
    private String telefone;
    private String cep;
    private String endereco;
    private int codigo;
    private boolean pessoaJuridica;
    private boolean funcionario;
    private String senha;
    private Pessoa regUserAuth;
    
    //Cadastro rapido para login
    public Pessoa(int cod, String nome, String pass){
    	setNome(nome);
    	setRegistro("---");
    	setCodigo(cod);
    	setSenha(pass);
    	setPessoaJuridica(false);
        setRegUserAuth(null);
    }
    
    //Construtor rapido
    public Pessoa(Pessoa userAuth){
    	setNome("---");
    	setRegistro("---");
    	codigo = 0;
        setRegUserAuth(userAuth);
        setPessoaJuridica(false);
        setSenha(null);
    }
    
    //Construtor testes
    public Pessoa(int cod, String nome, String reg, String tel, String cep, String end, boolean pJuridica, boolean pFuncionario, String pass, Pessoa userAuth){
    	setNome(nome);
        setRegistro(reg);
        setTelefone(tel);
        setCep(cep);
        setEndereco(end);
        setPessoaJuridica(pJuridica);
        setFuncionario(pFuncionario);
        setCodigo(cod);
        setRegUserAuth(userAuth);
        setSenha(pass);
    }


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public boolean getPessoaJuridica() {
		return pessoaJuridica;
	}
	
	public void setPessoaJuridica(boolean pJuridica) {
		pessoaJuridica = pJuridica;
	}
	
	public boolean getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(boolean isFuncionario) {
		this.funcionario = isFuncionario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getRegUserAuth() {
		return regUserAuth;
	}

	public void setRegUserAuth(Pessoa regUserAuth) {
		this.regUserAuth = regUserAuth;
	}
	
	public String getCodNome() {
		return codigo + " - " + nome;
	}
	
	public String toString(){
		String str = getCodNome() + "\t Registro: " + getRegistro() + "\t Pessoa Juridica: ";
		if(getPessoaJuridica())
			str+="Sim";
		else
			str+="Nao";
		str+= "\t Funcionario: ";
		if(getFuncionario())
			str+="Sim";
		else
			str+="Nao";
		str+= "\nTelefone: " + getTelefone();
		str+= "\t CEP: " + getCep() + "\t Endereco: " + getEndereco() +"\n";
		return str;
	}

	
}
