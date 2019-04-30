package grafo;

import java.util.*;

public class Grafo {
	/**
	 * lista che contiene tutti i nodi appartenenti al grafo
	 */
	private ArrayList<Nodo> nodi = new ArrayList<Nodo>();
	/**
	 * mappa contenente tutti gli archi che collegano i vari nodi
	 */
	private Map<Integer, Arco> archi = new HashMap<>();
	/**
	 * valore massimo della potenza di un arco
	 */
	private int v;
	/**
	 * numero di nodi/pietre
	 */
	private int n;
	/**
	 * nodo privo di dati utilizzato in caso di errore
	 */
	private Nodo vuoto = new Nodo(false);
	/**
	 * indice dell'arco che è stato aggiunto per ultimo nella mappa
	 */
	private int indiceArchiAtt = 0;

	/**
	 * costruttore del grafo che autocompila il tutto creando anche l'equilibrio
	 * @param v (potenza massima pietra)
	 * @param n (numero delle pietre)
	 */
	public Grafo(int v, int n) {
		this.v = v;
		this.n = n;

		generaEq();
	}

	/**
	 * ritorna la lista completa dei nodi
	 * @return la lista completa dei nodi
	 */
	public ArrayList<Nodo> getNodi() {
		return nodi;
	}

	/**
	 * ritorna il nodo richiesto cercandolo in base al nome/colore, nel caso un cui non si trovasse il nodo ritorna il nodo "vuoto"
	 * @param colore (indice per la ricerca)
	 * @return il nodo trovato
	 */
	public Nodo getNodo(String colore) {
		for (int i = 0; i < nodi.size(); i++) {
			if (nodi.get(i).getColore().equals(colore)) {
				return nodi.get(i);
			}
		}
		return vuoto;
	}

	/**
	 * ritorna il nodo appartenente all'indice, se non viene trovato viene ritornato l'insieme vuoto
	 * @param i (indice del nodo da cercare)
	 * @return il nodo richiesto
	 */
	public Nodo getNodo(int i) {
		if (i < nodi.size() && i >= 0) {
			return nodi.get(i);
		}
		return vuoto;
	}

	/**
	 * ritorna la mappa contenente gli archi del grafo
	 * @return la mappa degli archi
	 */
	public Map<Integer, Arco> getArchi() {
		return archi;
	}

	/**
	 * ritorna l'arco appartenente alla chiave i, nel caso in cui non ci siano archi appartenenti alla chiave i ritorna l'arco vuoto
	 * @param i (chiave di ricerca)
	 * @return l'arco richiesto
	 */
	public Arco getArco(int i) {
		if (i < archi.size() && i >= 0) {
			return archi.get(i);
		}
		Arco vuoto = new Arco(this.vuoto, this.vuoto, 0);
		return vuoto;
	}

	/**
	 * ritorna il valore di potenza massima delle pietre
	 * @return il valore di potenza massima delle pietre
	 */
	public int getV() {
		return v;
	}

	/**
	 * ritorna il numero di nodi/pietre
	 * @return il numero di nodi/pietre
	 */
	public int getN() {
		return n;
	}

	/**
	 * metodo che genera l'equilibrio della parita
	 */
	private void generaEq() {
		Random rnd = new Random();
		for (int i = 0; i < n; i++) {
			Nodo newNodo = new Nodo();
			nodi.add(newNodo);
		}

		for (int d = 0; d < nodi.size() - 1; d++) {
			for (int i = d + 1; i < nodi.size() - 1; i++) {
				int c = rnd.nextInt(2);
				if (c == 0) {
					Arco newArco = new Arco(nodi.get(d), nodi.get(i), rnd.nextInt(v - 1) + 1);
					archi.put(indiceArchiAtt, newArco);
					nodi.get(d).getIndiciArchi().add(indiceArchiAtt);
					nodi.get(i).getIndiciArchi().add(indiceArchiAtt);
					indiceArchiAtt++;
				} else {
					Arco newArco = new Arco(nodi.get(i), nodi.get(d), rnd.nextInt(v - 1) + 1);
					archi.put(indiceArchiAtt, newArco);
					nodi.get(d).getIndiciArchi().add(indiceArchiAtt);
					nodi.get(i).getIndiciArchi().add(indiceArchiAtt);
					indiceArchiAtt++;
				}
			}
			int ingressi = 0, uscite = 0;
			for (int i = 0; i < nodi.get(d).getIndiciArchi().size(); i++) {
				if (archi.get(i).getNodo1().getColore().contentEquals(nodi.get(d).getColore())) {
					uscite += archi.get(i).getValore();
				} else {
					ingressi += archi.get(i).getValore();
				}
			}
			if (Math.abs(ingressi - uscite) <= v) {
				if (ingressi - uscite < 0) {
					Arco newArco = new Arco(nodi.get(nodi.size() - 1), nodi.get(d), uscite - ingressi);
					archi.put(indiceArchiAtt, newArco);
					nodi.get(d).getIndiciArchi().add(indiceArchiAtt);
					nodi.get(nodi.size() - 1).getIndiciArchi().add(indiceArchiAtt);
					indiceArchiAtt++;
				} else {
					Arco newArco = new Arco(nodi.get(d), nodi.get(nodi.size() - 1), uscite - ingressi);
					archi.put(indiceArchiAtt, newArco);
					nodi.get(d).getIndiciArchi().add(indiceArchiAtt);
					nodi.get(nodi.size() - 1).getIndiciArchi().add(indiceArchiAtt);
					indiceArchiAtt++;
				}
			} else {
				if (ingressi - uscite < 0) {

				}
			}
		}
	}

	/**
	 * metodo per l'aggiunta di un arco
	 * @param nodo1 (primo nodo appartenente all'arco)
	 * @param nodo2 (secondo nodo appartenente all'arco)
	 * @param v (valore dell'arco)
	 */
	private void addArco(Nodo nodo1, Nodo nodo2, int v) {

	}

	/**
	 * metodo per l'aggiunta di un nodo che viene creato in automatico in mniera sequenziale
	 */
	private void addNodo() {

	}
	
	/**
	 * resetta tutto il grafo
	 */
	public void reset() {
		
	}
}
