package struttura;

import java.util.*;

import grafo.*;
import interfaccia.*;

public class TerrenoDiGioco {
	/**
	 * valore massimo della potenza del legame tra due elementi
	 */
	private static final int V = 10;
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
		int n // numero di elementi
				, g // numero di tamagolem per giocatore
				, p // numero di pietre per golem
				, s // numero di pietre nella scorta comune
				, sp // numero di pietre per elemento nella borsa
				, v; // valore massimo della potenza di un legame tra elementi

		n = (int) interfaccia.letturaDouble("Inserire il numero di elementi per determinare la difficoltà\n"
				+ "(facile (3;5))\n" + "(medio (6;8))\n" + "(difficile (9;10)): ");

		p = (int) Math.ceil((n + 1) / 3) + 1;

		g = (int) Math.ceil(((n - 1) * (n - 2)) / (2 * p));

		sp = (int) Math.ceil((2 * g * p) / n);

		s = sp * n;

		v = V;

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
