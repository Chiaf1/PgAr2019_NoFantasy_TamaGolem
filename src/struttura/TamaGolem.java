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
	private int vitaMax = 1;
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
	 * 
	 */
	public TamaGolem() {

	}

	/**
	 * 
	 */
	public void setUpGolem() {

	}

	/**
	 * 
	 * @return
	 */
	public int getVitaAtt() {
		return vitaAtt;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getIsDisponibile() {
		return isDisponibile;
	}

	/**
	 * 
	 * @param danno
	 */
	public void decVita(int danno) {

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
