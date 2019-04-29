package Grafo;
import java.util.*;

public class Nodo {
	private String colore;
	private ArrayList<Integer> indiciArchi = new ArrayList<Integer>();
	private static String[] colori = new String[] {"giallo", "verde", "rosso", "indaco", "viola", "magenta", "grigio", "marrone", "rosa", "ciano"};
	private static int coloreAtt = 0;
	
	public Nodo() {
		
	}

	public String getColore() {
		return colore;
	}

	public ArrayList<Integer> getIndiciArchi() {
		return indiciArchi;
	}
	
	public void addArco(int i) {
		
	}
	
	public void removeArco(int i) {
		
	}

}
