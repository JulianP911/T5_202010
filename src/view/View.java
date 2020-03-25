package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar Muestra de Datos");
			System.out.println("2. Requerimiento #1 - Buscar tiempos de viaje por fecha, clase de vehículo e infracción (Tabla de Hash Linear Probing).");
			System.out.println("3. Requerimiento #2 - Buscar tiempos de viaje por fecha, clase de vehículo e infracción (Tabla de Hash Separate Chaining).");
			System.out.println("4. Requerimiento #3 - Pruebas de desempeño");
			System.out.println("5. Cerrar");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			System.out.println(modelo);
		}
}
