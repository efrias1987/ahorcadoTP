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
	
	//inicia el programa
	void inicio() {
		//validador en true para poder verificar que por cada vuelta se verifique que la cantidad de intentos sea menor a 7
		boolean validador = true;
		//bucle principal que realiza 7 veces un bucle interno para jugar contra la maquina
		do {
			//bucle interno que permite dividir por turnos entre la maquina y la persona (turno 0 la persona, turno 1 la maquina)
			for (int i = 0; i <2; i++) {
				if(i == 0) {
					if (getCantIntentos(i) < 7 && validador == true) {
						System.out.println("Es el turno del jugador");
						ingresarLetra(i);
					}
				}else if (i == 1 && validador == true && getContadorLetras() > 0 ){
					if(getCantIntentos(i) < 7) {
						System.out.println("Es el turno de la maquina");
						ingresarLetra(i);	
					}
				}
				if(getCantIntentos(i) > 6 || getContadorLetras() == 0) {
					validador = false;
				}
			}
		}while(validador && getContadorLetras() != 0);

	}
	
	//retorna la cantidad de intentos que va 	
	int getCantIntentos(int turno){
		return cantidadIntentos[turno];
	}
	
	//retorna el contador de las letras que utilizamos como validador de que la palabra ha sido encontrada
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
		for (int i = 0; i < intentos; i++ ) {
			switch (i) {
				case 0:
					if (intentos==7) {
						System.out.println("  -");	
					}else {
						System.out.println("  O");
					}
					break;
				case 1:
					System.out.print(" /");
					break;
				case 2:
					System.out.print("|");
					break;
				case 3:
					System.out.println("\\ ");
					break;
				case 4:
					System.out.print(" / ");
					break;
				case 5:
					System.out.println("\\");
					break;
				case 6:
					System.out.println("Perdió");
					break;
			}
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
		System.out.println();
		if(turno == 1 && !validador) {
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
				System.out.println();
				System.out.println("El jugador ya realizó " + cantidadIntentos[turno] + " intentos.");	
			}else {
				System.out.println();
				System.out.println("La maquina realizo " + cantidadIntentos[turno] + " intentos.");	
			}
		intentos(cantidadIntentos[turno]);
		}
	}
}
