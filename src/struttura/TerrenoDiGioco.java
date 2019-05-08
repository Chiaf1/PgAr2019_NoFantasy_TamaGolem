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
	 * HashMap contenente le queue delle pietre disponibili
	 */
	private Map<String, Queue<String>> borsaPietre = new HashMap<String, Queue<String>>();
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
		interfaccia.scriviR("\n-------------------------------Inizio Partita-------------------------------");
		interfaccia.initPartia();

		do {
			n = interfaccia.letturaInt("Inserire il numero di elementi per determinare la difficoltà\n"
					+ "(facile (3;5))\n" + "(medio (6;8))\n" + "(difficile (9;10)): ");
			if (n < 2 && n > 11) {
				interfaccia.scriviR("Il valore inserito non va bene, prego reinserire");
			}
		} while (n < 2 && n > 11);

		p = (int) Math.ceil((n + 1.0) / 3.0) + 1;

		g = (int) Math.ceil(((n - 1.0) * (n - 2)) / (2.0 * p));

		sp = (int) Math.ceil((2.0 * g * p) / n);

		equilibrio = new Grafo(V, n);

		for (int i = 0; i < equilibrio.getNodi().size(); i++) {
			Queue<String> newQueue = new ArrayDeque<String>();
			for (int j = 0; j < sp; j++) {
				newQueue.add(equilibrio.getNodo(i).getColore());
			}
			borsaPietre.put(newQueue.element(), newQueue);
		}

		for (int i = 0; i < N_GIOCATORI; i++) {
			Giocatore newGiocatore = new Giocatore(g,
					interfaccia.letturaString("Inserire il nome del giocatore" + i + ": "), p);
			giocatori.add(newGiocatore);
		}
		if (giocatori.get(0).getNome().equals(giocatori.get(1).getNome())) {
			giocatori.get(1).setNome(giocatori.get(1).getNome() + "1");
			interfaccia.scriviR("Il secondo giocatore ha inserito il nome uguale a quello del primo,\n"
					+ " per tanto è stato cambiato." + "Il nuovo nome è: " + giocatori.get(1).getNome());
		}

	}

	/**
	 * metodo che dichiara il vincitore e indica se fare un altra partita oppure
	 * chiudere il programma
	 * 
	 * @return true se si vuole fare un altra partita, false se si vuole chiudere il
	 *         programma
	 */
	public boolean finePartita() {
		interfaccia.scriviR("\n-------------------------------Fine Partita-------------------------------");
		if (giocatori.get(0).getGolemDisp() == 0) {
			interfaccia.finePartita(giocatori.get(1));
		} else {
			interfaccia.finePartita(giocatori.get(0));
		}

		printEquilibrio();
		
		interfaccia.scriviR("\n-------------------------------Chiusura-------------------------------");
		while (true) {
			String ms = interfaccia
					.letturaString("Desideri fare un altra partita? \n" + "_si\n" + "_no\n" + "rispondi qui: ");
			if (ms.equals("si")) {
				equilibrio.reset();
				return true;
			} else if (ms.equals("no")) {
				interfaccia.scriviR("\nok allora chiudo,\n" + "ciao");
				return false;
			}
		}

	}

	/**
	 * metodo principale della classe TerrenoDiGioco, gestisce la partita vera e
	 * propria con lo scontro dei golem e la loro evocazione
	 */
	public void scontro() {
		Random rnd = new Random();
		int[] indGiocatori = new int[2];
		// scelta del primo giocatore che deve partire
		if (rnd.nextBoolean()) {
			indGiocatori[0] = 0;
			indGiocatori[1] = 1;
		} else {
			indGiocatori[0] = 1;
			indGiocatori[1] = 0;
		}

		interfaccia.scriviR("Il giocatore che parte per primo è: " + giocatori.get(indGiocatori[0]).getNome());
		// giocatore 1 evoca il primo golem
		evocazioneGolem(indGiocatori[0]);
		// giocatore 2 evoca il primo golem
		evocazioneGolem(indGiocatori[1]);

		// turno, i golem si tirano la prima pietra e vince chi lo ha più lungo
		// dopo aver preso il danno viene segnalata quanta vita rimane
		while (true) {
			interfaccia.scriviR("-------------------------------Inizio Turno-------------------------------");
			String pietraG1 = giocatori.get(indGiocatori[0]).getGolem(giocatori.get(indGiocatori[0]).getGolemAttivo())
					.getPietra();
			interfaccia.scriviR(
					"Il Golem Di " + giocatori.get(indGiocatori[0]).getNome() + " lancia la pietra " + pietraG1);
			String pietraG2 = giocatori.get(indGiocatori[1]).getGolem(giocatori.get(indGiocatori[1]).getGolemAttivo())
					.getPietra();
			interfaccia.scriviR(
					"Il Golem Di " + giocatori.get(indGiocatori[1]).getNome() + " lancia la pietra " + pietraG2);

			if (pietraG1 == pietraG2) {
				interfaccia.scriviR("non è stato inflitto alcun danno.");
			} else {// cerco nella lista degli indici dei due nodi gli inidici uguali, che dovrebbe
					// essere solo uno
				ArrayList<Integer> indiceArchi = new ArrayList<>();
				for (int i = 0; i < equilibrio.getNodi().size(); i++) {
					if (equilibrio.getNodo(i).getColore().equals(pietraG1)) {
						for (Integer luca : equilibrio.getNodo(i).getIndiciArchi()) {
							indiceArchi.add(luca);
						}
						for (int j = 0; j < equilibrio.getNodi().size(); j++) {
							if (equilibrio.getNodo(j).getColore().equals(pietraG2)) {
								indiceArchi.retainAll(equilibrio.getNodo(j).getIndiciArchi());
								break;
							}
						}
						break;
					}
				}
				int indiceArco = indiceArchi.get(0); // l'arrylist inidiciArchi dovrebbe contenere solamente un indice
														// che è l'arco che collega i due nodi

				// disitinguo i due casi dove la pietraG1 puù essere o al nodo1 o al nodo2
				// dell'arco
				if (equilibrio.getArco(indiceArco).getNodo1().getColore().equals(pietraG1)) {
					if (equilibrio.getArco(indiceArco).getDirezione()) {// la direzione positiva va da nodo1 a nodo2
						giocatori.get(indGiocatori[1]).getGolem(giocatori.get(indGiocatori[1]).getGolemAttivo())
								.decVita(equilibrio.getArco(indiceArco).getValore());
						interfaccia.scriviR("La pietra " + pietraG1 + " ha prevalso sulla pietra " + pietraG2
								+ " causando " + equilibrio.getArco(indiceArco).getValore() + " danni al golem di "
								+ giocatori.get(indGiocatori[1]).getNome());
						interfaccia.scriviR("La vita attuale del golem di " + giocatori.get(indGiocatori[1]).getNome()
								+ " è: " + giocatori.get(indGiocatori[1])
										.getGolem(giocatori.get(indGiocatori[1]).getGolemAttivo()).getVitaAtt());
					} else {
						giocatori.get(indGiocatori[0]).getGolem(giocatori.get(indGiocatori[0]).getGolemAttivo())
								.decVita(equilibrio.getArco(indiceArco).getValore());
						interfaccia.scriviR("La pietra " + pietraG2 + " ha prevalso sulla pietra " + pietraG1
								+ " causando " + equilibrio.getArco(indiceArco).getValore() + " danni al golem di "
								+ giocatori.get(indGiocatori[0]).getNome());
						interfaccia.scriviR("La vita attuale del golem di " + giocatori.get(indGiocatori[0]).getNome()
								+ " è: " + giocatori.get(indGiocatori[0])
										.getGolem(giocatori.get(indGiocatori[0]).getGolemAttivo()).getVitaAtt());
					}
				} else if (equilibrio.getArco(indiceArco).getNodo2().getColore().equals(pietraG1)) {
					if (!equilibrio.getArco(indiceArco).getDirezione()) {// la direzione positiva va da nodo1 a nodo2
						giocatori.get(indGiocatori[1]).getGolem(giocatori.get(indGiocatori[1]).getGolemAttivo())
								.decVita(equilibrio.getArco(indiceArco).getValore());
						interfaccia.scriviR("La pietra " + pietraG1 + " ha prevalso sulla pietra " + pietraG2
								+ " causando " + equilibrio.getArco(indiceArco).getValore() + " danni al golem di "
								+ giocatori.get(indGiocatori[1]).getNome());
						interfaccia.scriviR("La vita attuale del golem di " + giocatori.get(indGiocatori[1]).getNome()
								+ " è: " + giocatori.get(indGiocatori[1])
										.getGolem(giocatori.get(indGiocatori[1]).getGolemAttivo()).getVitaAtt());
					} else {
						giocatori.get(indGiocatori[0]).getGolem(giocatori.get(indGiocatori[0]).getGolemAttivo())
								.decVita(equilibrio.getArco(indiceArco).getValore());
						interfaccia.scriviR("La pietra " + pietraG2 + " ha prevalso sulla pietra " + pietraG1
								+ " causando " + equilibrio.getArco(indiceArco).getValore() + " danni al golem di "
								+ giocatori.get(indGiocatori[0]).getNome());
						interfaccia.scriviR("La vita attuale del golem di " + giocatori.get(indGiocatori[0]).getNome()
								+ " è: " + giocatori.get(indGiocatori[0])
										.getGolem(giocatori.get(indGiocatori[0]).getGolemAttivo()).getVitaAtt());
					}
				}
			}

			// controllo stato dei golem attivi per i due giocatori
			if (!giocatori.get(indGiocatori[0]).getGolem(giocatori.get(indGiocatori[0]).getGolemAttivo())
					.getIsDisponibile()) {
				interfaccia.scriviR("Il golem di " + giocatori.get(indGiocatori[0]).getNome()
						+ " ha preso troppi danni quindi deve essre cambiato.");
				recoverPietre(indGiocatori[0]);
				if (!evocazioneGolem(indGiocatori[0])) {
					return;
				}
				interfaccia.scriviR("Al giocatore " + giocatori.get(indGiocatori[0]).getNome() + " rimangono "
						+ giocatori.get(indGiocatori[0]).getGolemDisp() + " golem");
			}
			if (!giocatori.get(indGiocatori[1]).getGolem(giocatori.get(indGiocatori[1]).getGolemAttivo())
					.getIsDisponibile()) {
				interfaccia.scriviR("Il golem di " + giocatori.get(indGiocatori[1]).getNome()
						+ " ha preso troppi danni quindi deve essre cambiato.");
				recoverPietre(indGiocatori[1]);
				if (!evocazioneGolem(indGiocatori[1])) {
					return;
				}
				interfaccia.scriviR("Al giocatore " + giocatori.get(indGiocatori[1]).getNome() + " rimangono "
						+ (giocatori.get(indGiocatori[1]).getGolemDisp()-1) + " golem");
			}

		}
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
			borsaPietre
					.get(giocatori.get(giocatore).getGolem(giocatori.get(giocatore).getGolemAttivo()).getPietre()
							.element())
					.add(giocatori.get(giocatore).getGolem(giocatori.get(giocatore).getGolemAttivo()).getPietre()
							.remove());

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
			interfaccia.scriviR("Il giocatore " + giocatori.get(giocatore).getNome() + " non ha più golem disponibili.");
			return false;
		}
		interfaccia.scriviR("-------------------------------Inizio Evocazione-------------------------------");
		interfaccia.scriviR("L'evocazione del golem per " + giocatori.get(giocatore).getNome() + " è iniziata:\n"
				+ "Devi scegliare " + p + " pietre");

		// carico le pietre
		String[] newPietre = new String[p];
		for (int i = 0; i < newPietre.length; i++) {
			printBorsaPietre();
			String ind = "";
			boolean isCorretto = false;
			do {
				ind = interfaccia.letturaString("Inserire il nome della pietra da aggiungere: ");
				for (Map.Entry<String, Queue<String>> entry : borsaPietre.entrySet()) {
					if (entry.getKey().equals(ind)) {
						isCorretto = true;
					}
				}
				if (!isCorretto) {
					interfaccia.scriviR("L'indice inserito non va bene, ritentala");
				} else if (borsaPietre.get(ind).size() == 0) {
					interfaccia.scriviR("L'indice inserito non va bene, ritentala");
					isCorretto = false;
				}
			} while (!isCorretto);
			newPietre[i] = borsaPietre.get(ind).remove();
		}

		giocatori.get(giocatore).getGolem(giocatori.get(giocatore).getGolemAttivo()).setUpGolem(newPietre);

		return true;
	}

	/**
	 * metodo per la stampa a console della borsa delle pietre
	 */
	private void printBorsaPietre() {
		for (Map.Entry<String, Queue<String>> entry : borsaPietre.entrySet()) {
			interfaccia.scriviR("_" + entry.getKey() + "*" + borsaPietre.get(entry.getKey()).size());

		}
	}

	/**
	 * metodo per la stampa a console dell'equilibrio della partita
	 */
	private void printEquilibrio() {
		interfaccia.scriviR("\n\n-------------------------------Equilibrio-------------------------------");
		interfaccia.scriviR("L'equilibrio utilizzato all'interno della partita appena trascorsa è:\n");
		for (int i = 0; i < equilibrio.getArchi().size(); i++) {
			if (!equilibrio.getArco(i).getDirezione()) {
				interfaccia.scriviR(equilibrio.getArco(i).getNodo1().getColore() + " - "
						+ equilibrio.getArco(i).getValore() + " -> " + equilibrio.getArco(i).getNodo2().getColore());
			} else {
				interfaccia.scriviR(equilibrio.getArco(i).getNodo2().getColore() + " - "
						+ equilibrio.getArco(i).getValore() + " -> " + equilibrio.getArco(i).getNodo1().getColore());
			}

		}
		interfaccia.scriviC("\n\n");
	}

}
