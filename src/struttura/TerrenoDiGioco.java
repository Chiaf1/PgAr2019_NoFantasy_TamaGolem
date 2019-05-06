package struttura;

import java.util.*;

import grafo.*;
import interfaccia.*;

public class TerrenoDiGioco {
	/**
	 * numero dei giocatori
	 */
	private static final int N_GIOCATORI = 2;
	/**
	 * valore massimo della potenza del legame tra due elementi
	 */
	private static final int V = 10;
	/**
	 * numero di elementi
	 */
	private int n;
	/**
	 * numero di tamagolem per giocatore
	 */
	private int g;
	/**
	 * numero di pietre per golem
	 */
	private int p;
	/**
	 * numero di pietre per elemento nella borsa
	 */
	private int sp;
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
		
		interfaccia.initPartia();
		
		do {
			n = interfaccia.letturaInt("Inserire il numero di elementi per determinare la difficoltà\n"
					+ "(facile (3;5))\n" + "(medio (6;8))\n" + "(difficile (9;10)): ");
			if (n > 2 && n < 11) {
				interfaccia.scriviR("Il valore inserito non va bene, prego reinserire");
			}
		} while (n > 2 && n < 11);

		p = (int) Math.ceil((n + 1) / 3) + 1;

		g = (int) Math.ceil(((n - 1) * (n - 2)) / (2 * p));

		sp = (int) Math.ceil((2 * g * p) / n);

		equilibrio = new Grafo(V, n);

		for (int i = 0; i <= equilibrio.getNodi().size(); i++) {
			Queue<String> newQueue = new ArrayDeque<String>();
			for (int j = 0; j <= sp; j++) {
				newQueue.add(equilibrio.getNodo(i).getColore());
			}
			borsaPietre.add(newQueue);
		}

		for (int i = 0; i <= N_GIOCATORI; i++) {
			Giocatore newGiocatore = new Giocatore(g,
					interfaccia.letturaString("Inserire il nome del giocatore" + i + ": "), p);
			giocatori.add(newGiocatore);
		}

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
	 * riprende le pietre dal golem morto
	 * 
	 * @param giocatore (indice del giocatore il cui golem è appena stato
	 *                  massacrato)
	 */
	private void recoverPietre(int giocatore) {
		for (int i = 0; i < giocatori.get(giocatore).getGolem(giocatori.get(giocatore).getGolemAttivo())
				.getnPietre(); i++) {
			for (int j = 0; j < borsaPietre.size(); j++) {
				if (borsaPietre.get(j).element().equals(giocatori.get(giocatore)
						.getGolem(giocatori.get(giocatore).getGolemAttivo()).getPietre().element())) {
					borsaPietre.get(j).add(giocatori.get(giocatore).getGolem(giocatori.get(giocatore).getGolemAttivo())
							.getPietre().remove());
				}
			}
		}
	}

	/**
	 * metodo per gestire l'evocazione del golem
	 * 
	 * @param giocatore (indice del giocatore che deve evocare il golem)
	 * @return vero se è stato possibile evocare un golem, falso se non è stato
	 *         possibile
	 */
	private boolean evocazioneGolem(int giocatore) {

		// evoco il primo golem disponibile
		if (!(giocatori.get(giocatore).evocazioneGolem())) {
			return false;
		}

		// carico le pietre
		String[] newPietre = new String[p];
		for (int i = 0; i < newPietre.length; i++) {
			printBorsaPietre();
			int ind = 0;
			do {
				ind = interfaccia.letturaInt("Inserire l'indice della pietra da aggiungere: ");
				if (ind >= 0 && ind < borsaPietre.size()) {
					interfaccia.scriviR("L'indice inserito non va bene, ritentala");
				}
			} while (ind >= 0 && ind < borsaPietre.size());
			newPietre[i] = borsaPietre.get(ind).remove();
		}

		giocatori.get(giocatore).getGolem(giocatori.get(giocatore).getGolemAttivo()).setUpGolem(newPietre);

		return true;
	}

	/**
	 * metodo per la stampa a console della borsa delle pietre
	 */
	private void printBorsaPietre() {
		for (int i = 0; i < borsaPietre.size(); i++) {
			interfaccia.scriviR("_" + i + " " + borsaPietre.get(i).element() + "*" + borsaPietre.get(i).size());
		}
	}

}
