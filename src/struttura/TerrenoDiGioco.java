package struttura;

import java.util.*;

import grafo.*;
import interfaccia.*;

public class TerrenoDiGioco {
	/**
	 * lista contenente le queue delle pietre disponibili
	 */
	private ArrayList<Queue<String>> borsaPietre = new ArrayList<Queue<String>>();
	/**
	 * lista dei giocatori della partita
	 */
	private ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
	/**
	 * istanza dell'oggetto interfacciaUtente per la comunicazione con l'utente
	 */
	private InterfacciaUtente interfaccia = new InterfacciaUtente();
	/**
	 * grafo contenente i dati riguardanti le pietre e le loro interazioni
	 */
	private Grafo equilibrio;

	/**
	 * 
	 */
	public TerrenoDiGioco() {

	}

	/**
	 * 
	 */
	public void inizioPartita() {

	}
	
	/**
	 * 
	 */
	public void finePartita() {
		
	}
	
	/**
	 * 
	 * @return il nome del giocatore vincente
	 */
	private String calcoloVincitore() {
		return "";
	}

	/**
	 * 
	 */
	public void scontro() {
		
	}

	/**
	 * 
	 * @param giocatore (indice del giocatore che deve evocare il golem)
	 * @return vero se è stato possibile evocare un golem, falso se non è stato
	 *         possibile
	 */
	private boolean evocazioneGolem(int giocatore) {
		return true;
	}
	
}
