package Ahorcado;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AhorcadoObjetos ao = new AhorcadoObjetos();
		ao.PalabraOculta();
		boolean validador = true;
		do {
			for (int i = 0; i <2; i++) {
				if(i == 0) {
					if (ao.getCantIntentos(i) < 7 && validador == true) {
						System.out.println("Es el turno del jugador");
						ao.ingresarLetra(i);
					}else {
						validador = false;
					}
				}else if (i == 1 && validador == true && ao.getContadorLetras() > 0 ){
					if(ao.getCantIntentos(i) < 7) {
						System.out.println("Es el turno de la maquina");
						ao.ingresarLetra(i);	
					}else {
						validador = false;
					}

				}
			}
		}while(validador && ao.getContadorLetras() != 0);
	}

}
