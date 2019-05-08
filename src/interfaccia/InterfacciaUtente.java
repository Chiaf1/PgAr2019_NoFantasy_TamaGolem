package interfaccia;

import java.util.InputMismatchException;
import java.util.Scanner;

import struttura.Giocatore;

public class InterfacciaUtente {
	/**
	 * scanner per la lettura da console
	 */
	private Scanner lettore = new Scanner(System.in);

	/**
	 * metodo per la lettura di una stringa dalla console
	 * 
	 * @param msg (è il messaggio che viene scritto prima della lettura dei dati)
	 * @return la stringa letta dalla console
	 */
	public String letturaString(String msg) {
		System.out.print(msg);
		return lettore.next();
	}

	/**
	 * metodo per la lettura di un intero da console, se il dato immesso non è nel
	 * formato corretto comunica l'errore e ripete l'operazione
	 * 
	 * @param msg (è il messaggio che viene scritto prima della lettura dei dati)
	 * @return l'intero che viene letto
	 */
	public int letturaInt(String msg) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			System.out.print(msg);
			try {
				valoreLetto = lettore.nextInt();
				finito = true;
			} catch (InputMismatchException e) {
				System.out.println("il valore inserito non è nel formato corretto");
				lettore.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	/**
	 * scrive la stringa msg andando a capo alla fine
	 * 
	 * @param msg (la stringa da scrivere)
	 */
	public void scriviR(String msg) {
		System.out.println(msg);
	}

	/**
	 * scrive la stringa msg senza andare a capo ala fine
	 * 
	 * @param msg (la stringa da scrivere)
	 */
	public void scriviC(String msg) {
		System.out.print(msg);
	}

	/**
	 * metodo per la scrittura del messaggio di inizio partita
	 */
	public void initPartia() {
		scriviR("Benvenuti in TamaGolem una versione sotto steroidi di sasso-carta-forbice,\n"
				+ "per prima cosa vi chiediamo di setappare la partita inserendo alcuni valori e poi inizierà la fase di sfida vera e porpria;");
	}

	/**
	 * metodo per la scrittura del messaggio di fine partita con l'annuncio del
	 * vincitore
	 * 
	 * @param vincitore (il giocatore vincitore della partita)
	 */
	public void finePartita(Giocatore vincitore) {
		scriviR("Bene!!! la partita è finalmente finita ora è il momento per insultare il perdente.\n" + "che non è "
				+ vincitore.getNome() + ", lui ha vinto.");
		scriviR("Ha vinto con la bellezza di " + vincitore.getGolemDisp() + " golem ancora disponibili");
	}
}
