package controller;

import java.util.Iterator;
import java.util.Scanner;

import model.Comparendo;
import model.LlaveComparendo;
import model.data_structures.LinearProbingHash;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin )
		{
			view.printMenu();
			int option = lector.nextInt();
			switch(option)
			{
			case 1:
				modelo.darTablaHashLinearProbing();

				view.printMessage("El numero de comparendos es de: " + modelo.darTablaHashLinearProbing().size());

				LinearProbingHash<LlaveComparendo, Comparendo> linearProbing = modelo.darTablaHashLinearProbing();
				Iterator<Comparendo> resultado1 = linearProbing.values().iterator();

				int i = 0;
				while(resultado1.hasNext())
				{
					Comparendo elemento = resultado1.next();
					if (i == 0)
					{
						view.printMessage("El primer comparendo de la tabla de hash: " + elemento.getObjective() + ", " + elemento.getFecha_hora() + ", " + elemento.getLocalidad() + ", " + elemento.getInfraccion());
					}
					else if(i == (linearProbing.size() - 1))
					{
						view.printMessage("El ultimo comparendo de la tabla de hash: " + elemento.getObjective() + ", " + elemento.getFecha_hora() + ", " + elemento.getLocalidad() + ", " + elemento.getInfraccion());
					}
					i++;
				}
				view.printMessage("\n");
				break;

			case 2:
				break;

			case 3:
				break;

			case 4:
				lector.close();
				fin = true;
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
