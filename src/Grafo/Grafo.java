package Grafo;

import java.util.*;

public class Grafo {
	/**
	 * 
	 */
	private ArrayList<Nodo> nodi = new ArrayList<Nodo>();
	/**
	 * 
	 */
	private Map<Integer, Arco> archi = new HashMap<>();
	/**
	 * 
	 */
	private int v;
	/**
	 * 
	 */
	private int n;
	/**
	 * 
	 */
	private Nodo vuoto = new Nodo(false);
	/**
	 * 
	 */
	private int indiceArchiAtt = 0;
	
	/**
	 * 
	 * @param v
	 * @param n
	 */
	public Grafo(int v, int n) {
		this.v = v;
		this.n = n;
		
		generaEq();
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Nodo> getNodi() {
		return nodi;
	}
	
	/**
	 * 
	 * @param colore
	 * @return
	 */
	public Nodo getNodo(String colore) {
		for(int i = 0; i<nodi.size(); i++) {
			if(nodi.get(i).getColore().equals(colore)) {
				return nodi.get(i);
			}
		}
		return vuoto;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public Nodo getNodo(int i) {
		if(i< nodi.size() && i >= 0) {
			return nodi.get(i);
		}
		return vuoto;
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<Integer, Arco> getArchi() {
		return archi;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public Arco getArco(int i) {
		if (i < archi.size() && i >= 0) {
			return archi.get(i);
		}
		Arco vuoto = new Arco(this.vuoto, this.vuoto, 0);
		return vuoto;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getV() {
		return v;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getN() {
		return n;
	}
	
	/**
	 * 
	 */
	private void generaEq() {
		Random rnd = new Random();
		for(int i = 0; i < n; i++) {
			Nodo newNodo = new Nodo();
			nodi.add(newNodo);
		}
		for (int i = 1; i<nodi.size()-1; i++ ) {
			int c = rnd.nextInt(2);
			if(c == 0) {
				Arco newArco = new Arco(nodi.get(0), nodi.get(i), rnd.nextInt(n));
				archi.put(indiceArchiAtt, newArco);
				nodi.get(0).getIndiciArchi().add(indiceArchiAtt);
				nodi.get(i).getIndiciArchi().add(indiceArchiAtt);				
				indiceArchiAtt++;
			}else {
				Arco newArco = new Arco(nodi.get(i), nodi.get(0), rnd.nextInt(n));
				archi.put(indiceArchiAtt, newArco);
				nodi.get(0).getIndiciArchi().add(indiceArchiAtt);
				nodi.get(i).getIndiciArchi().add(indiceArchiAtt);				
				indiceArchiAtt++;
			}
		}
		int ingressi = 0, uscite = 0;
		for (int i = 0; i < nodi.get(0).getIndiciArchi().size(); i++) {
			if (archi.get(i).getNodo1().getColore().contentEquals(nodi.get(0).getColore())) {
				uscite += archi.get(i).getValore();
			}else {
				ingressi += archi.get(i).getValore();
			}
		}
		if (Math.abs(ingressi-uscite) <= v) {
			if (ingressi - uscite < 0) {
				Arco newArco = new Arco(nodi.get(nodi.size()-1), nodi.get(0), uscite-ingressi);
				archi.put(indiceArchiAtt, newArco);
				nodi.get(0).getIndiciArchi().add(indiceArchiAtt);
				nodi.get(nodi.size()-1).getIndiciArchi().add(indiceArchiAtt);				
				indiceArchiAtt++;
			}else {
				Arco newArco = new Arco(nodi.get(0), nodi.get(nodi.size()-1), uscite-ingressi);
				archi.put(indiceArchiAtt, newArco);
				nodi.get(0).getIndiciArchi().add(indiceArchiAtt);
				nodi.get(nodi.size()-1).getIndiciArchi().add(indiceArchiAtt);				
				indiceArchiAtt++;
			}
		}
	}
	
	/**
	 * 
	 * @param nodo1
	 * @param nodo2
	 * @param v
	 */
	private void addArco(Nodo nodo1, Nodo nodo2, int v) {
		
	}
	
	/**
	 * 
	 */
	private void addNodo() {
		
	}
}
