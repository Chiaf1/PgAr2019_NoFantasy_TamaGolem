package grafo;
import java.util.*;

public class Nodo {
	/**
	 * 
	 */
	private String colore;
	/**
	 * 
	 */
	private ArrayList<Integer> indiciArchi = new ArrayList<Integer>();
	/**
	 * 
	 */
	private static String[] colori = new String[] {"giallo", "verde", "rosso", "indaco", "viola", "magenta", "grigio", "marrone", "rosa", "ciano"};
	/**
	 * 
	 */
	private static int coloreAtt = 0;
	
	/**
	 * 
	 */
	public Nodo() {
		colore = colori[coloreAtt];
		coloreAtt++;
	}
	
	/**
	 * 
	 * @param i
	 */
	public Nodo(boolean i) {
		colore = "Err";
	}
	
	/**
	 * 
	 * @return
	 */
	public String getColore() {
		return colore;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> getIndiciArchi() {
		return indiciArchi;
	}
	
	/**
	 * 
	 * @param i
	 */
	public void addArco(int i) {
		indiciArchi.add(i);
	}
	
	/**
	 * 
	 * @param i
	 */
	public void removeArco(int i) {
		indiciArchi.remove(i);
	}
	
	/**
	 * 
	 */
	public void reset() {
		
	}

}
