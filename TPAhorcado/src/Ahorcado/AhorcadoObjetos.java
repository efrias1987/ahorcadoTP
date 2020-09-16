package Ahorcado;
import java.util.*;

public class AhorcadoObjetos {
	//static String palabraSecreta;
	static String letrasUtilizadas = " ";
	private int[] cantidadIntentos = {0,0};
	protected static String PalabOculta;
	char[] PalabraenAsterisco;
	int contadorLetras;
	Scanner teclado= new Scanner(System.in);
	
	int getCantIntentos(int turno){
		return cantidadIntentos[turno];
	}
	
	int getContadorLetras() {
		return contadorLetras;
	}
	
	public void PalabraOculta() {			
		String [] Palabra= {"telescopio","perro","escoba","caballo","carrusel","cataratas","avion","iguana"};
		Random R= new Random();
		int NumAleatorio= R.nextInt(Palabra.length);
		PalabOculta = Palabra[NumAleatorio].toUpperCase();
		PalabraenAsterisco = new char[PalabOculta.length()];
		contadorLetras = PalabOculta.length();
		Asteriscos(PalabOculta);
	}
	
	public void Asteriscos(String palabra) {
		for(int i=0; i<palabra.length(); i++) {
			PalabraenAsterisco[i] = '*';
		}
		System.out.println(PalabraenAsterisco);
	}
	
	public void intentos(int intentos) {
		switch (intentos) {
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
					break;
		}
	}

	public void repeticionLetras(char c, int turno){
		boolean validador = false;
		
		for (int i = 0; i < letrasUtilizadas.length(); i++) {
			if (c == letrasUtilizadas.charAt(i)) {
				validador = true;
			}
		}
		
		if (validador) {
			if (turno == 0) {
				System.out.println("La letra ya existe, vuelva a intentarlo");
			}
			ingresarLetra(turno);
		}else {
			letrasUtilizadas = c + letrasUtilizadas;
			resultado(c, turno);
		}
		if(turno == 1) {
			System.out.println("La maquina eligio la letra "+ c);
		}
		System.out.println("Las letras utilizadas son " + letrasUtilizadas);
	}

	public void ingresarLetra(int turno) {
		char letra;
		if(turno == 0) {
			System.out.println("Ingrese la letra: ");
			letra = teclado.next().toUpperCase().charAt(0);
			repeticionLetras(letra, 0);
		}else {
			Random rnd = new Random();
			letra = (char)(rnd.nextInt(26) + 'A');
			repeticionLetras(letra, 1);
		}
		
	}
	
	public void resultado(char c, int turno) {
		boolean validador = false;	
		for(int j = 0; j < PalabOculta.length(); j++) {
				if(c == PalabOculta.charAt(j)) {
					PalabraenAsterisco[j] = c;
					contadorLetras--;
					validador = true;
				}
			}
			System.out.println(PalabraenAsterisco);
			
			if(contadorLetras == 0) {
				if(turno ==0) {
					System.out.print("El jugador ");
				}else {
					System.out.print("La maquina ");
				}
				System.out.println("Ha ganado");
			}
			
			if(validador == false) {
				System.out.println("La letra no se encuentra en ninguna posicion");
				cantidadIntentos[turno]++;
				if(turno == 0) {
					System.out.println("El jugador ya realizó " + cantidadIntentos[turno] + " intentos.");
					
				}else {
					System.out.println("La maquina realizo " + cantidadIntentos[turno] + " intentos.");	
				}
				intentos(cantidadIntentos[turno]);
			}
			teclado.close();
	}
	
}
