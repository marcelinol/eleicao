package Eleicao;

public enum Cargo {
	PRESIDENTE("Presidente"), GOVERNADOR("Governador");
	
	String cargo;
	
	Cargo(String c){
		this.cargo = c;
	}
	
	public String getCargo(){
		return cargo;
	}
}
