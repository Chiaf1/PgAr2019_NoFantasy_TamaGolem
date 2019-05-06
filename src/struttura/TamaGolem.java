package struttura;

import java.util.*;

public class TamaGolem {
	/**
	 * coda delle pietre inserite nel golem
	 */
	private Queue<String> pietre = new ArrayDeque<String>();
	/**
	 * numero delle pietre che può contenere un golem
	 */
	private static int nPietre = 0;
	/**
	 * vita massima di un golem
	 */
	private static int vitaMax = 100;
	/**
	 * vita attuale del golem
	 */
	private int vitaAtt = 1;
	/**
	 * stato attuale del golem: true disponibile per l'evocazione, false non
	 * disponibile
	 */
	private boolean isDisponibile = true;

	/**
	 * costruttore della classe tamaGolem che inizializza il numero massimo di
	 * pietre
	 */
	public TamaGolem(int nPietre) {
		this.nPietre = nPietre;
	}

	/**
	 * metodo per l'assegnazione delle pietre
	 * 
	 * @param pietreSelez (pietre da assegnare al golem)
	 */
	public void setUpGolem(String[] pietreSelez) {
		for (int i = 0; i < pietreSelez.length; i++) {
			pietre.add(pietreSelez[i]);
		}
	}

	/**
	 * ritorna il numero massimo di pietre
	 * 
	 * @return il numero massimo di pietre
	 */
	public static int getnPietre() {
		return nPietre;
	}

	/**
	 * ritorna il valore della vita massima
	 * 
	 * @return il valore della vita massima
	 */
	public static int getVitaMax() {
		return vitaMax;
	}

	/**
	 * ritorna il valore attuale della vita
	 * 
	 * @return il valore attuale della vita
	 */
	public int getVitaAtt() {
		return vitaAtt;
	}

	/**
	 * ritorna true se il golem è disponibile, false se non lo è
	 * 
	 * @return true se il golem è disponibile, false se non lo è
	 */
	public boolean getIsDisponibile() {
		return isDisponibile;
	}

	/**
	 * metodo per decrementare la vita del golem, quando raggiunge lo 0 il flag
	 * isDisponibile viene messo a false
	 * 
	 * @param danno (valore da sottrarre alla vita)
	 */
	public void decVita(int danno) {
		vitaAtt = vitaAtt - danno;
		if (!(vitaAtt > 0)) {
			isDisponibile = false;
		}
	}

	/**
	 * ritorna la prima pietra della coda e poi la aggiunge in fondo alla coda
	 * 
	 * @return la prima pietra della coda
	 */
	public String getPietra() {
		String pietra = pietre.remove();
		pietre.add(pietra);
		return pietra;
	}
}
