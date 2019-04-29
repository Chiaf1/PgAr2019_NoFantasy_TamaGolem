package Grafo;

public class Arco {
	private Nodo nodo1;
	private Nodo nodo2;
	private int valore;

	public Arco(Nodo nodo1, Nodo nodo2, int valore) {
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
		this.valore = valore;
	}

	public Nodo getNodo1() {
		return nodo1;
	}

	public Nodo getNodo2() {
		return nodo2;
	}

	public int getValore() {
		return valore;
	}
	
}
