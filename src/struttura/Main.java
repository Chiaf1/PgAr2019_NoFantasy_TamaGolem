package struttura;

public class Main {
	
	public static TerrenoDiGioco terreno;
	
	public static void main(String[] args) {
		
		do {
			//richiamo il costruttore di terreno di gioco che setuppa la partita
			terreno = new TerrenoDiGioco();
			
			//richiamo il metodo scontro che gestisce tutta la fase di battaglia
			terreno.scontro();
						
			
		}while(terreno.finePartita());//il metodo fine partita gestisce la possibilità di una nuova partita o della chiusura del programma
		
	}

}
