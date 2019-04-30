package grafo;

public class Arco {
	/**
	 * 
	 */
	private Nodo nodo1;
	/**
	 * 
	 */
	private Nodo nodo2;
	/**
	 * 
	 */
	private int valore;

	/**
	 * 
	 * @param nodo1
	 * @param nodo2
	 * @param valore
	 */
	public Arco(Nodo nodo1, Nodo nodo2, int valore) {
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
		this.valore = valore;
	}

	/**
	 * 
	 * @return
	 */
	public Nodo getNodo1() {
		return nodo1;
	}

	/**
	 * 
	 * @return
	 */
	public Nodo getNodo2() {
		return nodo2;
	}

	/**
	 * 
	 * @return
	 */
	public int getValore() {
		return valore;
	}

	/**
	 * 
	 */
	public void reset() {

	}

}
