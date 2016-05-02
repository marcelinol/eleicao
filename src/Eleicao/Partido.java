package Eleicao;

public enum Partido {
	PT(13), PSDB(45), PSOL(50), PV(23);
	
	int numeroPartido = 0;
	
	Partido(int p){
		this.numeroPartido = p;
	}
	
	public int getPartido(){
		return numeroPartido;
	}
}
