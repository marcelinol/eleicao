package Eleicao;
import java.util.ArrayList;

public class EstadoFederativo {
	private Estado estado;
	private ArrayList<ZonaEleitoral> zonas = new ArrayList<ZonaEleitoral>();
	
	public EstadoFederativo(Estado estado){
		this.estado = estado;
		ZonaEleitoral zona = new ZonaEleitoral();
		zonas.add(zona);
	}
	
	public String getNome(){
		return this.estado.getNome();
	}

	public ArrayList<ZonaEleitoral> getZonas() {
		return zonas;
	}	
	
	public void setZonas(ZonaEleitoral zona) {
		this.zonas.add(zona);
	}

}
