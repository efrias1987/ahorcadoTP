package Ahorcado;

public class AhorcadoObjetos {
	
	public static void intentos() {
		
		while (cantitdadIntentos <= 7) {
			switch (cantitdadIntentos) {
				case 1:
					System.out.println("O");
					break;
				case 2:
					System.out.println(" O ");
					System.out.println(" | ");
					break;
				case 3:
					System.out.println(" O ");
					System.out.println("/| ");
					break;
				case 4:
					System.out.println("  O  ");
					System.out.println(" /|\\ ");
					break;
				case 5:
					System.out.println("  O ");
					System.out.println(" /|\\ ");
					System.out.println(" / ");
					break;
				case 6:
					System.out.println("  O ");
					System.out.println(" /|\\ ");
					System.out.println(" / \\ ");
					break;
				case 7:
					System.out.println("  - ");
					System.out.println(" /|\\ ");
					System.out.println(" / \\ ");
					System.out.println("Perdió");
			}
		}
	}

	public static char repeticionLetras(final char c){
		final int i=0;
		do {
			if (c == letrasUtilizadas.charAt(i)) {
				ingresarLetra();
			}
		}while(i<letrasUtilizadas.length())
		letrasUtilizadas = c + letrasUtilizadas;
		resultado(c);
	}

	public static char resultado (final char c) {
		Boolean validador = false;
		if (!validador) {
			for(int j = 0; j < palabraSecreta.length(); j++) {
				if(palabraSecreta.charAt(j) == c) {
					palabraADescubrir.charAt(j) = c;
					validador = true;
				}
			}
			if (palabraSecreta == palabraADescubrir) {
				System.out.println("GANASTE");
			} else {
				System.out.println("La palabra " + palabraADescubrir);
			}
			}
			if(validador == false) {
				System.out.println("La letra no se encuentra en ninguna posicion");
				cantitdadIntentos ++;
				System.out.println("Ya realizó " + cantitdadIntentos + " intentos.");
			}
	}
	
	private String palabraSecreta;
	private int cantitdadIntentos;
	private char[] letrasUtilizadas;
}
