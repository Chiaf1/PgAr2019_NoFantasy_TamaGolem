package interfaccia;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfacciaUtente {
	/**
	 * scanner per la lettura da console
	 */
	private Scanner lettore = new Scanner(System.in);
	
	/**
	 * metodo per la lettura di una stringa dalla console
	 * @param msg (è il messaggio che viene scritto prima della lettura dei dati)
	 * @return la stringa letta dalla console
	 */
	public String letturaString(String msg) {
		System.out.print(msg);
		return lettore.next();
	}
	
	/**
	 * metodo per la lettura di un double da console, se il dato immesso non è nel formato corretto comunica l'errore e ripete l'operazione
	 * @param msg (è il messaggio che viene scritto prima della lettura dei dati)
	 * @return il double che viene letto
	 */
	public double letturaDouble(String msg) {
		boolean isEnded = false;
		double datoLetto = 0;
		do {
			System.out.print(msg);
			try {
				datoLetto = lettore.nextDouble();
				isEnded = true;
			} catch (InputMismatchException e) {
				System.out.println("il valore inserito non è nel formato corretto");
				lettore.next();
			}
		} while (!isEnded);
		return datoLetto;
	}
	
	/**
	 * scrive la stringa msg andando a capo alla fine
	 * @param msg (la stringa da scrivere)
	 */
	public void scriviR(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * scrive la stringa msg senza andare a capo ala fine
	 * @param msg (la stringa da scrivere)
	 */
	public void scriviC(String msg) {
		System.out.print(msg);
	}
	
	/**
	 * metodo per la scrittura del messaggio di inizio partita
	 */
	public void initPartia() {
		
	}
	
	/**
	 * metodo per la scrittura del messaggio di fine partita con l'annuncio del vincitore
	 * @param vincitore (nome del giocatore vincitore della partita)
	 */
	public void finePartita(String vincitore) {
		
	}
}
