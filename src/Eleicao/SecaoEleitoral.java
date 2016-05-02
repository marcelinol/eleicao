package Eleicao;

public class SecaoEleitoral {
	private Urna urna;
	
	public SecaoEleitoral(){
		Urna novaUrna = new Urna();
		urna = novaUrna;
	}
	
	public Urna getUrna() {
		return urna;
	}	

}
