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
	 * 
	 * @param v (potenza massima pietra)
	 * @param n (numero delle pietre)
	 */
	public Grafo(int v, int n) {
		this.v = v;
		this.n = n;

		// creo tutti i nodi
		for (int i = 0; i < n; i++) {
			Nodo newNodo = new Nodo();
			nodi.add(newNodo);
		}
		// creo tutti gli archi
		for (int i = 0; i < nodi.size(); i++) {
			for (int c = i + 1; c < nodi.size(); c++) {
				Arco newArco = new Arco(nodi.get(i), nodi.get(c));
				archi.put(indiceArchiAtt, newArco);
				nodi.get(i).getIndiciArchi().add(indiceArchiAtt);
				nodi.get(c).getIndiciArchi().add(indiceArchiAtt);
				indiceArchiAtt++;
			}
		}

		generaEq();
	}

	/**
	 * ritorna la lista completa dei nodi
	 * 
	 * @return la lista completa dei nodi
	 */
	public ArrayList<Nodo> getNodi() {
		return nodi;
	}

	/**
	 * ritorna il nodo richiesto cercandolo in base al nome/colore, nel caso un cui
	 * non si trovasse il nodo ritorna il nodo "vuoto"
	 * 
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
	 * ritorna il nodo appartenente all'indice, se non viene trovato viene ritornato
	 * l'insieme vuoto
	 * 
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
	 * 
	 * @return la mappa degli archi
	 */
	public Map<Integer, Arco> getArchi() {
		return archi;
	}

	/**
	 * ritorna l'arco appartenente alla chiave i, nel caso in cui non ci siano archi
	 * appartenenti alla chiave i ritorna l'arco vuoto
	 * 
	 * @param i (chiave di ricerca)
	 * @return l'arco richiesto
	 */
	public Arco getArco(int i) {
		if (i < archi.size() && i >= 0) {
			return archi.get(i);
		}
		Arco vuoto = new Arco(this.vuoto, this.vuoto);
		return vuoto;
	}

	/**
	 * ritorna il valore di potenza massima delle pietre
	 * 
	 * @return il valore di potenza massima delle pietre
	 */
	public int getV() {
		return v;
	}

	/**
	 * ritorna il numero di nodi/pietre
	 * 
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
		int d = 0, ingressi = 0, uscite = 0;
		// creazione random dei nodi auto equilibranti
		for (int i = 0; i < Math.floor((nodi.size() - 1) / 2); i++) {
			for (int c = 0; c < nodi.size() - i - 2; c++) {
				archi.get(d).setValore(rnd.nextInt(v - 1) + 1, rnd.nextBoolean());
				;
				d++;
			}

			for (int f = 0; f < nodi.size() - 2; f++) {
				if (archi.get(nodi.get(i).getIndiciArchi().get(f)).getDirezione()) {
					ingressi += archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore();
				} else {
					uscite += archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore();
				}
			}
			if (Math.abs(ingressi - uscite) <= v) {
				if (ingressi < uscite) {
					archi.get(d).setValore(Math.abs(ingressi - uscite), true);
				} else {
					archi.get(d).setValore(Math.abs(ingressi - uscite), false);
				}
			} else if (ingressi < uscite) {
				// scorro tutte le uscite per compensare
				for (int f = i; f < nodi.size() - 2; f++) {
					if (!archi.get(nodi.get(i).getIndiciArchi().get(f)).getDirezione()) {
						if (archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() > Math.abs(ingressi - uscite)
								- v) {
							// toglie i soldi alle uscite abbastanza abbienti per compensare il debito
							archi.get(nodi.get(i).getIndiciArchi().get(f))
									.setValore(archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore()
											- (Math.abs(ingressi - uscite) - v), false);
							break;
						} else {
							// massacra le uscite che non hanno abbastanza soldi per pagarsi l'immunità
							uscite -= archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() - 1;
							archi.get(nodi.get(i).getIndiciArchi().get(f)).setValore(1, false);
						}
					}
				}
				// se le uscite non bastano scorro anche gli ingressi per ingrassarli
				for (int f = i; f < nodi.size() - 2; f++) {
					if (archi.get(nodi.get(i).getIndiciArchi().get(f)).getDirezione()) {
						if (v - archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() < Math.abs(ingressi - uscite)
								- v) {
							archi.get(nodi.get(i).getIndiciArchi().get(f))
									.setValore(archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore()
											+ (Math.abs(ingressi - uscite) - v), true);
							break;
						} else {
							ingressi += v - archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore();
							archi.get(nodi.get(i).getIndiciArchi().get(f)).setValore(v, false);
						}
					}
				}

			} else if (ingressi > uscite) {
				// scorro tutte le uscite per compensare
				for (int f = i; f < nodi.size() - 2; f++) {
					if (!archi.get(nodi.get(i).getIndiciArchi().get(f)).getDirezione()) {
						if (v - archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() < Math.abs(ingressi - uscite)
								- v) {
							// aggiunge alle uscite per colmare il debito
							archi.get(nodi.get(i).getIndiciArchi().get(f))
									.setValore(archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore()
											+ (Math.abs(ingressi - uscite) - v), false);
							break;
						} else {
							// aggiunge parte del debito degli ingressi
							uscite += v - archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore();
							archi.get(nodi.get(i).getIndiciArchi().get(f)).setValore(v, false);
						}
					}
				}
				// se le uscite non bastano scorro anche gli ingressi per massacrarli
				for (int f = i; f < nodi.size() - 2; f++) {
					if (archi.get(nodi.get(i).getIndiciArchi().get(f)).getDirezione()) {
						if (archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() > Math.abs(ingressi - uscite)
								- v) {
							archi.get(nodi.get(i).getIndiciArchi().get(f))
									.setValore(archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore()
											- (Math.abs(ingressi - uscite) - v), true);
							break;
						} else {
							ingressi -= archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() - 1;
							archi.get(nodi.get(i).getIndiciArchi().get(f)).setValore(1, false);
						}
					}
				}

			}

			d++;
		}
		// creazione dei nodi equilibranti in società
		for (int i = (int) (Math.floor((nodi.size() - 1) / 2) - 1); i < nodi.size(); i++) {
			for (int c = 0; c < nodi.size() - i - 2; c++) {
				archi.get(d).setValore(rnd.nextInt(v - 1) + 1, rnd.nextBoolean());
				;
				d++;
			}

			for (int f = 0; f < nodi.size() - 2; f++) {
				if (archi.get(nodi.get(i).getIndiciArchi().get(f)).getDirezione()) {
					ingressi += archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore();
				} else {
					uscite += archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore();
				}
			}
			if (Math.abs(ingressi - uscite) <= v) {
				if (ingressi < uscite) {
					archi.get(d).setValore(Math.abs(ingressi - uscite), true);
				} else {
					archi.get(d).setValore(Math.abs(ingressi - uscite), false);
				}
			} else if (ingressi < uscite) {
				// scorro tutte le uscite per compensare
				for (int f = i; f < nodi.size() - 2; f++) {
					if (!archi.get(nodi.get(i).getIndiciArchi().get(f)).getDirezione()) {
						if (archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() > Math.abs(ingressi - uscite)
								- v) {
							// toglie i soldi alle uscite abbastanza abbienti per compensare il debito
							archi.get(nodi.get(i).getIndiciArchi().get(f))
									.setValore(archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore()
											- (Math.abs(ingressi - uscite) - v), false);
							break;
						} else {
							// massacra le uscite che non hanno abbastanza soldi per pagarsi l'immunità
							uscite -= archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() - 1;
							archi.get(nodi.get(i).getIndiciArchi().get(f)).setValore(1, false);
						}
					}
				}
				// se le uscite non bastano scorro anche gli ingressi per ingrassarli
				for (int f = i; f < nodi.size() - 2; f++) {
					if (archi.get(nodi.get(i).getIndiciArchi().get(f)).getDirezione()) {
						if (v - archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() < Math.abs(ingressi - uscite)
								- v) {
							archi.get(nodi.get(i).getIndiciArchi().get(f))
									.setValore(archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore()
											+ (Math.abs(ingressi - uscite) - v), true);
							break;
						} else {
							ingressi += v - archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore();
							archi.get(nodi.get(i).getIndiciArchi().get(f)).setValore(v, false);
						}
					}
				}

			} else if (ingressi > uscite) {
				// scorro tutte le uscite per compensare
				for (int f = i; f < nodi.size() - 2; f++) {
					if (!archi.get(nodi.get(i).getIndiciArchi().get(f)).getDirezione()) {
						if (v - archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() < Math.abs(ingressi - uscite)
								- v) {
							// aggiunge alle uscite per colmare il debito
							archi.get(nodi.get(i).getIndiciArchi().get(f))
									.setValore(archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore()
											+ (Math.abs(ingressi - uscite) - v), false);
							break;
						} else {
							// aggiunge parte del debito degli ingressi
							uscite += v - archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore();
							archi.get(nodi.get(i).getIndiciArchi().get(f)).setValore(v, false);
						}
					}
				}
				// se le uscite non bastano scorro anche gli ingressi per massacrarli
				for (int f = i; f < nodi.size() - 2; f++) {
					if (archi.get(nodi.get(i).getIndiciArchi().get(f)).getDirezione()) {
						if (archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() > Math.abs(ingressi - uscite)
								- v) {
							archi.get(nodi.get(i).getIndiciArchi().get(f))
									.setValore(archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore()
											- (Math.abs(ingressi - uscite) - v), true);
							break;
						} else {
							ingressi -= archi.get(nodi.get(i).getIndiciArchi().get(f)).getValore() - 1;
							archi.get(nodi.get(i).getIndiciArchi().get(f)).setValore(1, false);
						}
					}
				}

			}

			d++;
		}
	}

	/**
	 * metodo per l'aggiunta di un arco
	 * 
	 * @param nodo1 (primo nodo appartenente all'arco)
	 * @param nodo2 (secondo nodo appartenente all'arco)
	 * @param v     (valore dell'arco)
	 */
	private void addArco(Nodo nodo1, Nodo nodo2, int v) {

	}

	/**
	 * metodo per l'aggiunta di un nodo che viene creato in automatico in mniera
	 * sequenziale
	 */
	private void addNodo() {

	}

	/**
	 * resetta tutto il grafo
	 */
	public void reset() {

	}
}
