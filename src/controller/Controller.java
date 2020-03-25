package controller;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import model.Comparendo;
import model.LlaveComparendo;
import model.data_structures.LinearProbingHash;
import model.data_structures.SeparateChainingHash;
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

				LinearProbingHash<LlaveComparendo, Comparendo> linearProbing1 = modelo.darTablaHashLinearProbing();
				Iterator<Comparendo> resultado1 = linearProbing1.values().iterator();

				int i = 0;
				while(resultado1.hasNext())
				{
					Comparendo elemento = resultado1.next();
					if (i == 0)
					{
						view.printMessage("El primer comparendo de la tabla de hash: " + elemento.getObjective() + ", " + elemento.getFecha_hora() + ", " + elemento.getLocalidad() + ", " + elemento.getInfraccion());
					}
					else if(i == (linearProbing1.size() - 1))
					{
						view.printMessage("El ultimo comparendo de la tabla de hash: " + elemento.getObjective() + ", " + elemento.getFecha_hora() + ", " + elemento.getLocalidad() + ", " + elemento.getInfraccion());
					}
					i++;
				}
				view.printMessage("El numero de duplas es 20, el arreglo inicial de 63 posiciones, arreglo final 21 posiciones, numero de rehashes 18, se utilizo un archivo de 20 de datos ya que el grande no corre en el computador (Linear Probing), los datos se obtuvieron de la implementacion haciendo calculos y sysouts para obtener la informacion.");				
				
				view.printMessage("\n");
				
				
				modelo.darTablaHashSeparateChaining();

				view.printMessage("El numero de comparendos es de: " + modelo.darTablaHashLinearProbing().size());

				LinearProbingHash<LlaveComparendo, Comparendo> separateChaining1 = modelo.darTablaHashLinearProbing();
				Iterator<Comparendo> resultat2 = linearProbing1.values().iterator();

				int j = 0;
				while(resultat2.hasNext())
				{
					Comparendo elemento = resultat2.next();
					if (j == 0)
					{
						view.printMessage("El primer comparendo de la tabla de hash: " + elemento.getObjective() + ", " + elemento.getFecha_hora() + ", " + elemento.getLocalidad() + ", " + elemento.getInfraccion());
					}
					else if(j == (separateChaining1.size() - 1))
					{
						view.printMessage("El ultimo comparendo de la tabla de hash: " + elemento.getObjective() + ", " + elemento.getFecha_hora() + ", " + elemento.getLocalidad() + ", " + elemento.getInfraccion());
					}
					j++;
				}
				view.printMessage("El numero de duplas es 20, el arreglo inicial de 63 posiciones, arreglo final 21 posiciones, numero de rehashes 18, se utilizo un archivo de 20 de datos ya que el grande no corre en el computador (Linear Probing), los datos se obtuvieron de la implementacion haciendo calculos y sysouts para obtener la informacion.");				
				
				view.printMessage("\n");
				
				
				
				break;

			case 2:
				view.printMessage("Dado una fecha (año/mes/día), clase de vehículo e infracción retornar los comparendos que tengan esos valores.");
				view.printMessage("Por favor ingrese la fecha en el formato yyyy/MM/dd : ");
				String entrada1 = lector.next();
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		        Date testDate = null;
		        String date = entrada1;
		        try 
		        {
					testDate = df.parse(date);
					view.printMessage("Por favor ingrese la clase del vehiculo: ");
					String entrada2 = lector.next();
					view.printMessage("Por favor ingrese el tipo de infraccion:");
					String entrada3 = lector.next();
					
					view.printMessage("Los siguientes comparendos tienen los valores ingresados por el usuario: ");
					LlaveComparendo nueva = new LlaveComparendo(testDate, entrada2, entrada3);
					LinearProbingHash<LlaveComparendo, Comparendo> linearProbing2 = modelo.darTablaHashLinearProbing();
					//System.out.println(linearProbing2.size());
					Iterator<LlaveComparendo> resultado2 = linearProbing2.keys().iterator();
					Iterator<Comparendo> resultado3 = linearProbing2.values().iterator();
					
					while(resultado2.hasNext() && resultado3.hasNext())
					{
						LlaveComparendo elemento1 = resultado2.next();
						Comparendo elemento2 = resultado3.next();
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
						String fechaN = df1.format(elemento1.getFecha_Hora());
						Date testDate1 = df1.parse(fechaN);
						
						LlaveComparendo actual = new LlaveComparendo(testDate1, elemento1.getClase_Vehi(), elemento1.getInfraccion());
						if(actual.equals(nueva))
						{
							view.printMessage(elemento2.getObjective() + ", " + elemento1.getFecha_Hora() + ", " + elemento2.getTipo_servi() + ", " + elemento1.getClase_Vehi() + ", " + elemento1.getInfraccion());
						}
					}	
				} 
		        catch (ParseException e) 
		        {
					e.printStackTrace();
				}
		        
		        if(!df.format(testDate).equals(date))
		        {
		            System.out.println("invalid date!!");
		        } 
		        else 
		        {
		            System.out.println("valid date");
		        }
		        view.printMessage("\n");
				break;

			case 3:
				// TODO Requerimiento 2 
				// Probar con la fecha 2018/11/11 , MOTOCICLETA , C38 
				view.printMessage("Dado una fecha (año/mes/día), clase de vehículo e infracción retornar los comparendos que tengan esos valores.");
				view.printMessage("Por favor ingrese la fecha en el formato yyyy/MM/dd : ");
				String entrada4 = lector.next();
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
		        Date testDate2 = null;
		        String date2 = entrada4;
		        try 
		        {
					testDate2 = df2.parse(date2);
					view.printMessage("Por favor ingrese la clase del vehiculo: ");
					String entrada5 = lector.next();
					view.printMessage("Por favor ingrese el tipo de infraccion:");
					String entrada6 = lector.next();
					
					view.printMessage("Los siguientes comparendos tienen los valores ingresados por el usuario: ");
					LlaveComparendo nueva = new LlaveComparendo(testDate2, entrada5, entrada6);
					SeparateChainingHash<LlaveComparendo, Comparendo> separateChaining2 = modelo.darTablaHashSeparateChaining();
					//System.out.println(separateChaining2.size());
					Iterator<LlaveComparendo> resultado2 = separateChaining2.keys().iterator();
					
					Iterator<Comparendo> resultado3 = separateChaining2.vals().iterator();
					
					/**
					int cllave = 0;
					int cvalor = 0;
					
					while(resultado2.hasNext())
					{
						LlaveComparendo elemento1 = resultado2.next();
						//cllave++;
						System.out.println( elemento1.getInfraccion() );

					}
					
					while(resultado3.hasNext())
					{
						Comparendo elemento1 = resultado3.next();
						//cvalor++;
						System.out.println( elemento1.getObjective() );

					}
					*/
					
					ArrayList<String> arr = new ArrayList<String>();
					
					while(resultado2.hasNext() && resultado3.hasNext())
					{
						LlaveComparendo elemento1 = resultado2.next();
						Comparendo elemento2 = resultado3.next();
						
						SimpleDateFormat df3 = new SimpleDateFormat("yyyy/MM/dd");
						String fechaN = df3.format(elemento1.getFecha_Hora());
						Date testDate1 = df3.parse(fechaN);
						
						LlaveComparendo actual = new LlaveComparendo(testDate1, elemento1.getClase_Vehi(), elemento1.getInfraccion());
						
						//System.out.println( actual.getFecha_Hora() + " " + actual.getClase_Vehi() + " "  + actual.getInfraccion() + " --- " + nueva.getFecha_Hora() + " " + nueva.getClase_Vehi() + " "  + nueva.getInfraccion() );
						
						if(actual.equals(nueva))
						{
							arr.add(elemento2.getObjective() + ", " + elemento1.getFecha_Hora() + ", " + elemento2.getTipo_servi() + ", " + elemento1.getClase_Vehi() + ", " + elemento1.getInfraccion());
						}
					}	
					
					
					
					
					
					String temp;
					 
					for (int l = 1; l < arr.size(); l++) 
					{
						for (int m = l; m > 0; m--) 
						{
							int coma1 = (arr.get(m).indexOf(","));
							int coma2 = (arr.get(m-1).indexOf(","));

							if (  Integer.parseInt(arr.get(m).substring(0, coma1)) < Integer.parseInt(arr.get(m-1).substring(0, coma1))  ) 
						    {
								temp = arr.get(m) ;
						        arr.set(m, arr.get(m - 1));
						        arr.set(m-1, temp);
						    }
						 }
				     }
					
					String imprimir = "";
				     for (int n = 0; n < arr.size(); n++) 
			   	     {
				    	 imprimir += arr.get(n) + ", \n";
				     }
				     
				     
				     System.out.println( imprimir.substring(0, imprimir.length()-3 ) );
						   
     
				     
				     
						   
				} 
		        catch (ParseException e) 
		        {
					e.printStackTrace();
				}
		        
		        if(!df2.format(testDate2).equals(date2))
		        {
		            System.out.println("invalid date!!");
		        } 
		        else 
		        {
		            System.out.println("valid date");
		        }
		        view.printMessage("\n");
				break;
				
			case 4:
				long start1 = System.currentTimeMillis();
				modelo.darConsultasGetLinearProbing();
				long end1 = System.currentTimeMillis();
				
				view.printMessage("Tiempo de hacer las 10000 consultas (Linear probing) es de: " + (end1-start1)/1000.0);

				long start2 = System.currentTimeMillis();
				modelo.darConsultasGetSeparateChaining();
				long end2 = System.currentTimeMillis();
				
				view.printMessage("Tiempo de hacer las 10000 consultas (Separate Chaining) es de: " + (end2-start2)/1000.0);
				break;
				
			case 5:
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
