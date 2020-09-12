import java.util.*;

public class Ahorcado {

	ArrayList<String> palabras = new ArrayList<String>();
	Scanner scn = new Scanner(System.in);
	Random rnd = new Random();
	
	void ahorcado() {
		String palabra, palabraADescubrir, letrasUtilizadas = " ";
		char opcion, letra;
		int[] intentos = {0,0};
		boolean validador, modo;
		int cantLetras;
		int[] cantTurnos = {10,10};

		modo = modoDeJuego();
		palabra = palabraGenerada();
		cantLetras = palabra.length();
		char [] letras = new char[palabra.length()];
		
		letras = asignarValores(palabra.length());
		
		do {
			for(int i = 0; i < 2; i++) {
				System.out.println("Lleva " + intentos[i+1] + " intentos");
				if(i == 0) {
					System.out.println("Turno del jugador 1");
					//System.out.println("Intengo nro " + (intentos[i]+1));
					if(intentos[i] > 0) {
						System.out.println("Letras utilizadas " + (letrasUtilizadas));
						System.out.print(letras);
						System.out.println();
					}
				}else if(i > 0 && modo) {
					System.out.println("Turno de la maquina");
				}else {
					System.out.println("Turno del jugador 2");
				}
				validador = false;
				if(i == 0 ) {
					System.out.println("Desea ingresar una letra o una palabra?");
					System.out.println("1 - Letra");
					System.out.println("2 - Palabra");
					//esto esta mal
					opcion = scn.next().charAt(0);
					
				}else {
					opcion = '1';
				}
				
				switch(opcion) {
				case '1':
					//validador = false;
					if(!modo || i == 0) {
						System.out.println("Ingrese letra a analizar");
						letra = scn.next().toUpperCase().charAt(0);
					}else {
						letra = (char)(rnd.nextInt(26) + 'A');
						System.out.println("la letra es: " + letra);
					}
					//cambiar tipo de variable para no romper la logica
					validador = chequearCaracteres(letra, letrasUtilizadas);
					
					if (!validador) {
						letrasUtilizadas = letra + letrasUtilizadas;
						for(int j = 0; j < palabra.length(); j++) {
							if(palabra.charAt(j) == letra) {
								System.out.println("Letra encontrada en la posicion " + (j+1));
								letras[j+j] = letra;
								validador = true;
								//modificar para no forzar la logica
								cantLetras--;
							}
						}
						if(validador == false) {
							System.out.println("La letra no se encuentra en ninguna posicion");
							System.out.println("Le quedan " + (cantTurnos[i]- (intentos[i]+1)) + " intentos.");
							intentos[i]++;
						}
						if (cantLetras == 0) {
							System.out.println("Palabra encontrada, la palabra es: " + palabra);
							System.out.println("Ha ganado.");
							//????
							//modificar linea para que sea reutilizable
							intentos[0] = 12;
							intentos[1] = 12;
							i = 3;
						}else {
							System.out.println("Letras utilizadas " + (letrasUtilizadas));
							System.out.print(letras);
							System.out.println();
						}
					}else {
						i--;
					}
					break;
				case '2':
					System.out.println("Ingrese palabra a verificar: ");
					palabraADescubrir = scn.next().toUpperCase();
					if(palabraADescubrir.equals(palabra)) {
						System.out.println("Ha descubierto la palabra. Ha ganado.");
						//????
						//modificar linea para que sea reutilizable
						intentos[i] = 12;
					}else {
						System.out.println("La palabra introducida no es correcta.");
						intentos[i] = cantTurnos[i];
					}
					break;
					default:
						System.out.println("Opcion incorrecta. Ingrese nuevamente");
						i--;//intentos--;
						break;
				}
			}
		//si bien la logica funciona, de este manera hay que escribir mas codigo del que debemos
		}while ((intentos[0] < cantTurnos[0]) && (intentos[1] < cantTurnos[1]) );
		if(intentos[0] == cantTurnos[0] || intentos[1] == cantTurnos[1]) {
			System.out.println("Ha perdido");
			System.out.println("La palabra a descubrir era: " + palabra);
		}
		System.out.println("Fin del juego");
		scn.close();
	}
//Cambiar tipo de metodo si se hace en objetos para que sea reutilizable
	public boolean modoDeJuego() {
		char modo;
		do {
			System.out.println("Ingrese el modo de juego que desea utilizar: ");
			System.out.println("1 - Un jugador contra la maquina");
			System.out.println("2 - Dos jugadores");
			modo = scn.next().charAt(0);
			System.out.println(modo);
			switch (modo){
				case '1':
					System.out.println("Ha elegido el modo para jugar contra la maquina");
					return true;
				case '2':
					System.out.println("Ha elegido el modo dos jugadores");
					return false;
					default:
						System.out.println("Ingrese nuevamente: ");
						break;
			}
		}while (modo != '1' || modo != '2');
		
		return false;
	}
//Cambiar a metodo recursivo
	public boolean chequearCaracteres(char letraAChequear, String letrasAsignadas) {
		for(int i = 0; i < letrasAsignadas.length(); i++) {
			if(letrasAsignadas.charAt(i) == letraAChequear) {
				//System.out.println("Letra ya utilizada, vuelva a intentarlo");
				return true;
			}
		}
		return false;
	}
	
	public static char[] asignarValores(int index) {
		index = (index*2)-1;
		char[] letraConvertida = new char[index];
		for(int i = 0; i < index; i++) {
			letraConvertida[i] = '*';
			if(i % 2 != 0 && i < index && i > 0) {
				letraConvertida[i] = '-';
			}
		}
		System.out.println(letraConvertida);
		return letraConvertida;
	}
	
	public String palabraGenerada() {
		int opcion, idxPalabra;
		String palabraARetornar = "";
		do {
			System.out.println("Seleccione método de generación de palabra");
			System.out.println("1 - Automatica");
			System.out.println("2 - Manual");
			try {
				opcion = scn.nextInt();
				if (opcion > 2 || opcion < 1) {
					System.out.println("Opcion incorrecta vuelva a intentarlo");
				}
			}catch(Exception exeption) {
					System.out.println("Ha ingresado una opcion incorrecta, modo automatico seleccionado.");
					opcion = 1;
			}
		}while(opcion > 2 || opcion < 1);
		switch(opcion) {
		case 1:
			System.out.println("Agregando palabras al diccionario automaticamente...");
			agregarPalabras();
			System.out.println("Eligiendo palabra aleatoriamente");
			idxPalabra = new Random().nextInt(palabras.size());
			palabraARetornar = palabras.get(idxPalabra).toUpperCase();
			break;
		case 2:
			System.out.println("Ingrese palabra:");
			palabraARetornar = scn.next().toUpperCase();
			break;
		}
		return palabraARetornar;
	}
	
	public void agregarPalabras() {
		palabras.add("Alejandro");
		palabras.add("Alejandria");
		palabras.add("Alemania");
		palabras.add("Alfonso");
		palabras.add("Alfredo");
		palabras.add("Alicia");
		palabras.add("Alpes");
		palabras.add("Amazonia");
		palabras.add("America");
		palabras.add("Amon");
		palabras.add("Ana");
		palabras.add("Andorra");
		palabras.add("Andres");
		palabras.add("Antillas");
		palabras.add("Antonia");
		palabras.add("Antonio");
		palabras.add("Aquiles");
		palabras.add("Arabia");
		palabras.add("Argel");
		palabras.add("Aries");
		palabras.add("Arras");
		palabras.add("Arturo");
		palabras.add("Asia");
		palabras.add("Asturias");
		palabras.add("Australia");
		palabras.add("Austria");
		palabras.add("Babel");
		palabras.add("Barcelona");
		palabras.add("Benedicto");
		palabras.add("Bernarda");
		palabras.add("Bernardo");
		palabras.add("Bierzo");
		palabras.add("Bilbao");
		palabras.add("Boadilla");
		palabras.add("Brasil");
		palabras.add("Bruselas");
		palabras.add("Belgica");
		palabras.add("Bosforo");
		palabras.add("Calahorra");
		palabras.add("Calatrava");
		palabras.add("California");
		palabras.add("Cambridge");
		palabras.add("Canada");
		palabras.add("Caribe");
		palabras.add("Cariñena");
		palabras.add("Carlos");
		palabras.add("Carlota");
		palabras.add("Catalina");
		palabras.add("Cain");
		palabras.add("Ceilan");
		palabras.add("Cibeles");
		palabras.add("Cid");
		palabras.add("Compostela");
		palabras.add("Congo");
		palabras.add("Constantino");
		palabras.add("Constantinopl");
		palabras.add("Corinto");
		palabras.add("Coruña");
		palabras.add("Coulomb");
		palabras.add("Creta");
		palabras.add("Cristina");
		palabras.add("Cristobal");
		palabras.add("Cordoba");
		palabras.add("Daimiel");
		palabras.add("Dante");
		palabras.add("David");
		palabras.add("Delia");
		palabras.add("Diego");
		palabras.add("Diesel");
		palabras.add("Eduardo");
		palabras.add("Emilio");
		palabras.add("Escorpio");
		palabras.add("España");
		palabras.add("Esther");
		palabras.add("Etiopia");
		palabras.add("Eufrasia");
		palabras.add("Eugenia");
		palabras.add("Eugenio");
		palabras.add("Eulalia");
		palabras.add("Europa");
		palabras.add("Eusebio");
		palabras.add("Eustaquio");
		palabras.add("Ezequiel");
		palabras.add("Fernando");
		palabras.add("Feroe");
		palabras.add("Filadelfia");
		palabras.add("Filomena");
		palabras.add("Flandes");
		palabras.add("Florencia");
		palabras.add("Francia");
		palabras.add("Francisca");
		palabras.add("Francisco");
		palabras.add("Frisia");
		palabras.add("Felix");
		palabras.add("Gabriel");
		palabras.add("Galeno");
		palabras.add("Gales");
		palabras.add("Gante");
		palabras.add("German");
		palabras.add("Gerona");
		palabras.add("Godoy");
		palabras.add("Grial");
		palabras.add("Griñon");
		palabras.add("Guernesey");
		palabras.add("Guillermo");
		palabras.add("Gustavo");
		palabras.add("Genova");
		palabras.add("Hinojosa");
		palabras.add("Hiadas");
		palabras.add("Hiades");
		palabras.add("ISBN");
		palabras.add("Ignacio");
		palabras.add("Inglaterra");
		palabras.add("Irene");
		palabras.add("Isaac");
		palabras.add("Isabel");
		palabras.add("Isidoro");
		palabras.add("Isidro");
		palabras.add("Isidroness");
		palabras.add("Islandia");
		palabras.add("Islandias");
		palabras.add("Ismael");
		palabras.add("Italia");
		palabras.add("Jaime");
		palabras.add("Javier");
		palabras.add("Jerico");
		palabras.add("Jeronima");
		palabras.add("Jeronimo");
		palabras.add("Jesucristo");
		palabras.add("Jese");
		palabras.add("Jesus");
		palabras.add("Jesuses");
		palabras.add("Joaquin");
		palabras.add("Job");
		palabras.add("Jordan");
		palabras.add("Jose");
		palabras.add("Juan");
		palabras.add("Judit");
		palabras.add("Julia");
		palabras.add("Laura");
		palabras.add("Lladra");
		palabras.add("Lladras");
		palabras.add("Londres");
		palabras.add("Lorenzo");
		palabras.add("Luis");
		palabras.add("Luisa");
		palabras.add("Madrid");
		palabras.add("Manuel");
		palabras.add("Mario");
		palabras.add("Marte");
		palabras.add("Meca");
		palabras.add("Melisa");
		palabras.add("Mesta");
		palabras.add("Miguel");
		palabras.add("Milan");
		palabras.add("Morfeo");
		palabras.add("Mejico");
		palabras.add("Mexico");
		palabras.add("Monica");
		palabras.add("Nicaragua");
		palabras.add("Nicolas");
		palabras.add("Noe");
		palabras.add("Nuria");
		palabras.add("Oceania");
		palabras.add("Oporto");
		palabras.add("Orion");
		palabras.add("Pablo");
		palabras.add("Paco");
		palabras.add("Palencia");
		palabras.add("Panama");
		palabras.add("Pancracio");
		palabras.add("Paraguay");
		palabras.add("Paulina");
		palabras.add("Pedro");
		palabras.add("Peru");
		palabras.add("Peñaranda");
		palabras.add("Platon");
		palabras.add("Pluton");
		palabras.add("Polonia");
		palabras.add("Portugal");
		palabras.add("Prusia");
		palabras.add("Pucela");
		palabras.add("Rafael");
		palabras.add("Ramiro");
		palabras.add("Raquel");
		palabras.add("Raul");
		palabras.add("Ricardo");
		palabras.add("Roberto");
		palabras.add("Roma");
		palabras.add("Rusia");
		palabras.add("Salamanca");
		palabras.add("Santander");
		palabras.add("Santiago");
		palabras.add("Saturno");
		palabras.add("Sergio");
		palabras.add("Silvia");
		palabras.add("Sonia");
		palabras.add("Texas");
		palabras.add("Tobias");
		palabras.add("Tomas");
		palabras.add("Toñi");
		palabras.add("Troya");
		palabras.add("Turquia");
		palabras.add("Tunez");
		palabras.add("Ucrania");
		palabras.add("Urano");
		palabras.add("Utrera");
		palabras.add("Valdepeñas");
		palabras.add("Vancouver");
		palabras.add("Venecia");
		palabras.add("Venezuela");
		palabras.add("Venus");
		palabras.add("Vicente");
		palabras.add("Virginia");
		palabras.add("Vivar");
		palabras.add("Vizcaya");
		palabras.add("Victor");
		palabras.add("Yolanda");
		palabras.add("Zafra");
		palabras.add("Zamora");
		palabras.add("Zaragoza");
		palabras.add("Zelanda");
	}
}
