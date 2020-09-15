package Ahorcado;
import java.util.*;

public class AhorcadoObjetos {
	static String palabraSecreta;
	static String letrasUtilizadas;
	private int cantidadIntentos = 0;

protected static String PalabOculta;

	public void PalabraOculta() {			
		Scanner teclado= new Scanner(System.in);
		String [] Palabra= {"telescopio","perro","escoba","caballo","carrusel","cataratas","avion","iguana",};
		Random R= new Random();
		int NumAleatorio= R.nextInt(Palabra.length);
		PalabOculta = Palabra[NumAleatorio].toUpperCase();	
		Asteriscos(PalabOculta);
	}
	
	public void Asteriscos(String palabra) {
		int Numletrasdepalabra = PalabOculta.length();
		char[] PalabraenAsterisco = new char[Numletrasdepalabra];
		for(int i=0; i<PalabraenAsterisco.length; i++) {
			PalabraenAsterisco[i] = '*';
		}
		System.out.println(PalabraenAsterisco);
	}
	
	public void intentos() {
		while (cantidadIntentos <= 7) {
			switch (cantidadIntentos) {
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

	public void repeticionLetras(char c, int turno){
		int i=0;
		do {
			if (c == letrasUtilizadas.charAt(i)) {
				ingresarLetra(turno);
			}
		}while(i<letrasUtilizadas.length());
		letrasUtilizadas = c + letrasUtilizadas;
		resultado(c, turno);
	}

	public void ingresarLetra(int turno) {
		if(turno == 0) {
			System.out.println("Ingrese la letra: ");
			char letra;
			Scanner teclado= new Scanner(System.in);
			letra = teclado.next().toUpperCase().charAt(0);
			repeticionLetras(letra, turno);
		}else {
			Random rnd = new Random();
			char letra;
			letra = (char)(rnd.nextInt(26) + 'A');
			repeticionLetras(letra, turno);
		}
		
	}
	
	public void resultado(char c, int turno) {
		Boolean validador = false;
		if (!validador) {
			for(int j = 0; j < palabraSecreta.length(); j++) {
				if(palabraSecreta.charAt(j) == c) {
					validador = true;
				}
			}
			if (palabraSecreta.equals(PalabOculta)) {
				System.out.println("GANASTE");
			} else {
				System.out.println("La palabra " + PalabOculta);
			}
			}
			if(validador == false) {
				System.out.println("La letra no se encuentra en ninguna posicion");
				cantidadIntentos++;
				System.out.println("Ya realizó " + cantidadIntentos + " intentos.");
			}
	}	
}


