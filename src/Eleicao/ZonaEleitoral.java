package Eleicao;
import java.util.ArrayList;

public class ZonaEleitoral {
	private ArrayList<SecaoEleitoral> secoes = new ArrayList<SecaoEleitoral>();
	
	public ZonaEleitoral(){
		SecaoEleitoral secao = new SecaoEleitoral();
		setSecoes(secao);
	}

	public ArrayList<SecaoEleitoral> getSecoes() {
		return secoes;
	}

	public void setSecoes(SecaoEleitoral secao) {
		this.secoes.add(secao);
	}	
}
