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
	private static int nMaxGolem = 0;
	/**
	 * numero dei golem disponibili del giocatore
	 */
	private int nGolemDisp;
	/**
	 * indice del golem attivo del giocatore
	 */
	private int golemAttivo;

	/**
	 * al costruttore passo il numero massimo di golem ed il nome, e viene creato
	 * l'array di golem a seconda del loro numero e viene inizializzato il numero di
	 * golem disponibili
	 * 
	 * @param nMaxGolem    (numero massimo di golem)
	 * @param nome         (nome del giocatore)
	 * @param nPietreGolem (numero di pietre che può contenere un golem)
	 */
	public Giocatore(int nMaxGolem, String nome, int nPietreGolem) {
		this.nMaxGolem = nMaxGolem;
		this.nome = nome;

		TamaGolem newGolem = new TamaGolem(nPietreGolem);

		for (int i = 0; i < nMaxGolem; i++) {
			TamaGolem newNewGolem = new TamaGolem();
			golems.add(newNewGolem);
		}
		nGolemDisp = golems.size();
	}

	/**
	 * metodo per l'evocazione del nuovo golem dalla lista del giocatore. quando
	 * viene richiesto viene messo come golem attivo il primo golem disponibile
	 * nella lista del giocatore
	 * 
	 * @return true se è stato possibile selezionare un golem, false se non è stato
	 *         trovato un golem disponibile
	 */
	public boolean evocazioneGolem() {
		if (nGolemDisp > 0) {
			for (int i = 0; i < golems.size(); i++) {
				if (golems.get(i).getIsDisponibile()) {
					golemAttivo = i;
					if (i > 1) {
						nGolemDisp = nMaxGolem - (i - 1);
					}
					return true;
				}
			}
		}
		nGolemDisp = 0;
		return false;
	}

	/**
	 * ritorna il nome del giocatore
	 * 
	 * @return il nome del giocatore
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * ritorna l'indice del golem attivo
	 * 
	 * @return l'indice del golem attivo
	 */
	public int getGolemAttivo() {
		return golemAttivo;
	}

	/**
	 * ritorna la lista di golem del giocatore
	 * 
	 * @return la lista di golem del giocatore
	 */
	public ArrayList<TamaGolem> getGolems() {
		return golems;
	}

	/**
	 * ritorna il golem richiesto
	 * 
	 * @param i (indice del golem da cercare)
	 * @return il golem all'indice i
	 */
	public TamaGolem getGolem(int i) {
		return golems.get(i);
	}

	/**
	 * ritorna il numero di golem disponibili
	 * 
	 * @return il nuemro di golem disponibili
	 */
	public int getGolemDisp() {
		return nGolemDisp;
	}

	/**
	 * metodo per settare il nome
	 * 
	 * @param nome (nuovo nome da dare al giocatore)
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
