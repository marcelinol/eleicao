package Eleicao;
 
public class Main {
 
	public static void main(String[] args) {
		
		
		//INSTANCIANDO ELEMENTOS MÍNIMOS PARA ELEIÇÃO
		Eleicao eleicao = new Eleicao(false);
		ControladorUrna controlador = new ControladorUrna();
		
		//CRIANDO ELEIÇÃO PARA SANTA CATARINA
		EstadoFederativo rs = new EstadoFederativo(Estado.RS);
		eleicao.addEstados(rs);
		Urna urna = new Urna();
		urna = eleicao.getUrna();
		controlador.iniciaEleicao(urna);
		
		
		//SANTA CATARINA
//		EstadoFederativo sc = new EstadoFederativo(Estado.SC);
//		eleicao.addEstados(sc);
//		Urna urnaSC = new Urna();
//		urnaSC = eleicao.getUrna();
//		controlador.iniciaEleicao(urnaSC);
 
	}
}