package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Eleicao.Candidato;
import Eleicao.CandidatoNotFoundException;
import Eleicao.Cargo;
import Eleicao.ControladorUrna;
import Eleicao.Urna;

public class TelaPrincipal extends JFrame {

	private JFrame mainFrame;
	private JLabel nomeVisorTexto = new JLabel();
	private JLabel numeroVisorTexto = new JLabel();
	private JLabel cargoVisorTexto = new JLabel();
	private boolean voltaControle = false;
	Urna urna = new Urna();
	Cargo cargo;
	Candidato candidato;
	private ControladorUrna controladorUrna;
	private int quantosFaltam;
	
	public TelaPrincipal (Urna urna, Cargo cargo, ControladorUrna controladorUrna, int quantosFaltam) {
		this.urna = urna;
		this.cargo = cargo;
		this.controladorUrna = controladorUrna;
		this.quantosFaltam = quantosFaltam;
	}
	
	public void iniciaTela(){
		mainFrame = new JFrame("Eleição Bagual 2014");
		mainFrame.setLocation(300,0);
		mainFrame.setVisible(true);
		mainFrame.setSize(new Dimension(800,400));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JPanel jpKeyboard = new JPanel();
	    JPanel visor = new JPanel();
	    mainFrame.add(visor, BorderLayout.WEST);
	    mainFrame.add(jpKeyboard, BorderLayout.EAST);
	    visor.setLayout(new BorderLayout());
	    
	    JPanel nomeVisor = new JPanel();
	    JPanel numeroVisor = new JPanel();
	    JPanel cargoVisor = new JPanel();
	    
	    
	    visor.add(nomeVisor, BorderLayout.NORTH);
	    visor.add(numeroVisor);
	    visor.add(cargoVisor, BorderLayout.SOUTH);
	    
	  //TELA
	    nomeVisorTexto.setText("");
	    nomeVisor.add(nomeVisorTexto);
	    
	    numeroVisorTexto.setText("_ _");
	    numeroVisor.add(numeroVisorTexto);
	    
	    cargoVisorTexto.setText(cargo.getCargo());
	    cargoVisor.add(cargoVisorTexto);
	    
	    String primeiraLinha[] = {"1","2","3"};
	    String segundaLinha[] = {"4","5","6"};
	    String terceiraLinha[] = {"7","8","9"};
	    String quartaLinha[] = {"0"};
	    String quintaLinha[] = {"Branco","Corrige","Confirma"};
	    JButton botoesPrimeiraLinha[];
	    JButton botoesSegundaLinha[];
	    JButton botoesTerceiraLinha[];
	    JButton botoesQuartaLinha[];
	    JButton botoesQuintaLinha[];
	    
	    jpKeyboard.setLayout(new GridLayout(5,1));
	    GerenciadorBotoes gerenciador = new GerenciadorBotoes();
	    
	    botoesPrimeiraLinha = new JButton[primeiraLinha.length];
	    JPanel painelTemporario = new JPanel(new GridLayout(1, primeiraLinha.length)); // firstRow.length = 3 nesse caso
	    for(int i = 0; i < primeiraLinha.length; ++i) 
	    {
	        JButton b= new JButton(primeiraLinha[i]);
	        botoesPrimeiraLinha[i] = b;
	        botoesPrimeiraLinha[i].addActionListener(gerenciador);
	        painelTemporario.add(botoesPrimeiraLinha[i]);
	    }
	    jpKeyboard.add(painelTemporario);

	    botoesSegundaLinha = new JButton[segundaLinha.length];
	    painelTemporario = new JPanel(new GridLayout(1, segundaLinha.length));
	    for(int i = 0; i < segundaLinha.length; ++i) 
	    {
	        JButton b= new JButton(segundaLinha[i]);
	        botoesSegundaLinha[i] = b;
	        botoesSegundaLinha[i].addActionListener(gerenciador);
	        painelTemporario.add(botoesSegundaLinha[i]);

	    }
	    jpKeyboard.add(painelTemporario);
	    
	    botoesTerceiraLinha = new JButton[terceiraLinha.length];
	    painelTemporario = new JPanel(new GridLayout(1, terceiraLinha.length));
	    for(int i = 0; i < terceiraLinha.length; ++i) 
	    {
	        JButton b= new JButton(terceiraLinha[i]);
	        botoesTerceiraLinha[i] = b;
	        botoesTerceiraLinha[i].addActionListener(gerenciador);
	        painelTemporario.add(botoesTerceiraLinha[i]);

	    }
	    jpKeyboard.add(painelTemporario);
	    
	    botoesQuartaLinha = new JButton[quartaLinha.length];
	    painelTemporario = new JPanel(new GridLayout(1, quartaLinha.length));
	    for(int i = 0; i < quartaLinha.length; ++i) 
	    {
	        JButton b= new JButton(quartaLinha[i]);
	        botoesQuartaLinha[i] = b;
	        botoesQuartaLinha[i].addActionListener(gerenciador);
	        painelTemporario.add(botoesQuartaLinha[i]);

	    }
	    jpKeyboard.add(painelTemporario);
	    
	    botoesQuintaLinha = new JButton[quintaLinha.length];
	    painelTemporario = new JPanel(new GridLayout(1, quintaLinha.length));
	    for(int i = 0; i < quintaLinha.length; ++i) 
	    {
	        JButton b = new JButton(quintaLinha[i]);
	        botoesQuintaLinha[i] = b;
	        botoesQuintaLinha[i].addActionListener(gerenciador);
	        painelTemporario.add(botoesQuintaLinha[i]);
	    }
	    jpKeyboard.add(painelTemporario);
	}
	
	public boolean getControle(){
		return voltaControle;
	}
	
	public void setCargoVisorTexto(Cargo cargo) {
		cargoVisorTexto.setText(cargo.getCargo());
	}
	
	private class GerenciadorBotoes implements ActionListener {
		
		private String numeroVoto = "";
		
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals("Confirma")){
				
				int numeroVotoInteiro = Integer.parseInt(numeroVoto);
				urna.computaVoto(numeroVotoInteiro, cargo);
				calculaQuantosFaltam();
				numeroVisorTexto.setText("_ _");
				numeroVoto = "";
				nomeVisorTexto.setText("");
				trocaCargo();
				System.out.println("Cargo na Tela: "+cargo.getCargo());
				cargoVisorTexto.setText(cargo.getCargo());
				
			}else if(event.getActionCommand().equals("Corrige")){
				numeroVisorTexto.setText("_ _");
				nomeVisorTexto.setText("");
				numeroVoto = "";
			}else if(event.getActionCommand().equals("Branco")){
				nomeVisorTexto.setText("VOTO EM BRANCO");
				numeroVisorTexto.setText("00");
				numeroVoto = "00";
			}else if(!(event.getActionCommand().equals("Confirma")) && !(event.getActionCommand().equals("Branco")) ){
				if(numeroVoto.length() < 2) {
					numeroVoto += event.getActionCommand();
					numeroVisorTexto.setText(numeroVoto);
				} 
				
				if(numeroVoto.length() == 2) {
					int number = Integer.parseInt(numeroVoto);
					try {
						candidato = urna.buscaCandidato(number, cargo);
						String nomeCandidato = candidato.getNome();
						nomeVisorTexto.setText(nomeCandidato);
					} catch (CandidatoNotFoundException e) {
						nomeVisorTexto.setText("VOTO NULO");
					}
				}
			
			}
		}

		private void trocaCargo() {
			if(cargo == Cargo.GOVERNADOR) {
				cargo = Cargo.PRESIDENTE;
			} else if (controladorUrna.getQtdeCargos() > 1) { 
				cargo = Cargo.GOVERNADOR;
			}
		}

		private void calculaQuantosFaltam() {
			quantosFaltam--;
			if(quantosFaltam < 1) {
				controladorUrna.encerraEleicao();
				fechaTela();
			}
		}
		
		private void fechaTela() {
			try{
				if(mainFrame.isVisible()){
					mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
				}
			} catch(Exception e){}
		}
		
	}

}
