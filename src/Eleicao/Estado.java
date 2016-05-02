package Eleicao;

public enum Estado {
	RS("Rio Grande do Sul"), SC("Santa Catarina");
	
	Estado(String nome){
		this.nome = nome;
	}
	
	private String nome;
	
	public String getNome(){
		return nome;
	}
}
