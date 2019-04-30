package struttura;

import java.util.*;

public class Giocatore {
	/**
	 * nome del giocatore
	 */
	private String nome;
	/**
	 * lista dei golem del giocatore
	 */
	private ArrayList<TamaGolem> golems = new ArrayList<TamaGolem>();
	/**
	 * numero massimo dei golem di un giocatore
	 */
	private static int nGolems = 0;
	/**
	 * numero dei golem disponibili del giocatore
	 */
	private int nGolemDisp;
	/**
	 * indice del golem attivo del giocatore
	 */
	private int golemAttivo;
	
	/**
	 * 
	 */
	public Giocatore() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean evocazioneGolem() {
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getGolemAttivo() {
		return golemAttivo;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<TamaGolem> getGolems(){
		return golems;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public TamaGolem getGolem(int i) {
		return golems.get(i);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getGolemDisp() {
		return nGolemDisp;
	}

}
