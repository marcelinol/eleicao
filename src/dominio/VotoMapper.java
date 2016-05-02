package dominio;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
 
 

import Eleicao.Voto;
 
public class VotoMapper {
	private final String fileName = "Votos.urn";
	private ArrayList<Voto> cacheVotos = new ArrayList<>();
	
	public void putVoto(Voto voto){
		File votosFile = new File("/Users/LeoSL/Dropbox/Documents/workspace/Eleicao/votos.urn");
		if(votosFile.exists()){
			System.out.println("File exists!");
			this.cacheVotos = load();
			System.out.println(cacheVotos.size());
		}
		this.cacheVotos.add(voto);
	}
	
	public ArrayList<Voto> getCacheVotos(){
		return this.cacheVotos;
	}
	
	public void persist(){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			ObjectOutputStream objectOutputStrem = new ObjectOutputStream(fileOutputStream);
			objectOutputStrem.writeObject(this.cacheVotos);
			System.out.println(this.cacheVotos);
			objectOutputStrem.flush();
			fileOutputStream.flush();
			objectOutputStrem.close();
			fileOutputStream.close();
			objectOutputStrem = null;
			fileOutputStream = null;
		} catch (FileNotFoundException e) {
			System.out.println("Tem arquivo não sô... " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Desculpe, deu alguma zica. " + e.getMessage());
		}
	}
	
	public ArrayList<Voto> load(){
		FileInputStream file;
		try{
			file = new FileInputStream(this.fileName);
			ObjectInputStream object = new ObjectInputStream(file);
			this.cacheVotos = (ArrayList<Voto>) object.readObject();
		} catch (FileNotFoundException e){
			System.out.println(e);
			
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return this.cacheVotos;
	}
}