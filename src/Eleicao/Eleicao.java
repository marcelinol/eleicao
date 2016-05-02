package Eleicao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Eleicao {

	private boolean segundoTurno;
	private ArrayList<EstadoFederativo> estados = new ArrayList<EstadoFederativo>();
	private HashMap<Candidato,Integer> votosCandidatos = new HashMap<Candidato, Integer>();
	
	public Eleicao(boolean segundoTurno){
		this.segundoTurno = segundoTurno;			
	}
	
	public boolean getSegundoTurno(){
		return segundoTurno;
	}
	
	public Urna getUrna(){
		Urna urna = new Urna();
		for(EstadoFederativo estado : estados){
			for(ZonaEleitoral zona : estado.getZonas()){
				for(SecaoEleitoral secao : zona.getSecoes()){
					urna = secao.getUrna();
				}
			}
		}
		return urna;
	}
	
	public ArrayList<EstadoFederativo> getEstados() {
		return estados;
	}
	
	public void addEstados(EstadoFederativo estado) {
		this.estados.add(estado);
	}
	
	public void exibeDetalhesCandidatos(Urna urna, Cargo cargo){
		for(Candidato c : votosCandidatos.keySet()){
			if(c.getCargo().equals(cargo)){
				System.out.println("O(A) candidato(a) " + c.getNome() + " recebeu " + votosCandidatos.get(c) + " votos.");
			}
		}
	}
	
	public void exibeTotalVotos(Urna urna) {
		contagemVotosInvalidos(urna);
		System.out.println("Total de votos: "+ urna.getTotalVotos());
	}

	public void apuraUrna(Urna urna, Cargo cargo) {
		ArrayList<Voto> votos = urna.getVotos();
		for(Voto voto : votos) {
			if(!(voto.getNumero() == 00) && !(voto.getNumero() == 99)) {
				votosCandidatos = contaVotoValido(voto, votosCandidatos);	
			}
		}
		System.out.println("====");
		apuraVencedor(votosCandidatos, Cargo.PRESIDENTE);
		exibeDetalhesCandidatos(urna, Cargo.PRESIDENTE);
		System.out.println("====");
		apuraVencedor(votosCandidatos, Cargo.GOVERNADOR);
		exibeDetalhesCandidatos(urna, Cargo.GOVERNADOR);
	}
	
	private HashMap<Candidato,Integer> contaVotoValido(Voto voto, HashMap<Candidato,Integer> votosCandidatos) {
		VotoValido votoValido = (VotoValido) voto;
		Candidato candidatoVotado = votoValido.getCandidato();
		boolean candidatoEncontrado = false;
		for (Candidato candidato : votosCandidatos.keySet()) {
			if(candidatoVotado.getNumero() == candidato.getNumero() && candidatoVotado.getCargo().equals(candidato.getCargo())){
				int qtdeAnterior = votosCandidatos.get(candidato);
				qtdeAnterior += 1;
				votosCandidatos.put(candidato, qtdeAnterior);
				candidatoEncontrado = true;
			}
		}
		if(!candidatoEncontrado) {
			votosCandidatos.put(candidatoVotado, 1);
		}
		return votosCandidatos;
	}
	
	public Candidato apuraVencedor(HashMap<Candidato,Integer> votosCandidatos, Cargo cargo){
		System.out.println();
		System.out.println("Cargo: "+cargo.getCargo());
		Candidato vencedor = primeiroCandidato(votosCandidatos);
		Candidato perdedor = vencedor;
		try{
			for (Candidato c : votosCandidatos.keySet()) {
				perdedor = c;
				vencedor = descobreVencedor(c, vencedor, votosCandidatos, cargo);
			}
		} catch(DrawException e){
			if(vencedor.getCargo() == perdedor.getCargo() && vencedor.getNumero() != perdedor.getNumero()){
				System.out.println("Candidatos que empataram: "+vencedor.getNome()+" e "+perdedor.getNome());
			}
		}
		return vencedor;
	}

	
	public Candidato descobreVencedor(Candidato perdedor, Candidato vencedor, HashMap<Candidato,Integer> votosCandidatos, Cargo cargo) throws DrawException {
		if(perdedor.getCargo().equals(cargo)){
			if (votosCandidatos.get(perdedor) > votosCandidatos.get(vencedor)) {
				return perdedor;
			}else if(votosCandidatos.get(perdedor) == votosCandidatos.get(vencedor) && perdedor != vencedor){
				throw new DrawException();
			}
		}
		return vencedor;
	}
	
	public void contagemVotosInvalidos(Urna urna){	
		int votosBrancos = urna.getVotosBrancos();
		int votosNulos = urna.getVotosNulos();
		System.out.println("Número de votos brancos: " + votosBrancos);
		System.out.println("Número de votos nulos: " + votosNulos);
	}
	
	private Candidato primeiroCandidato(HashMap<Candidato, Integer> votosCandidatos) {
		for (Candidato c : votosCandidatos.keySet()) {
			return c;
		}
		return null;
	}
}
