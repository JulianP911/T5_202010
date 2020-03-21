package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Comparendo;
import model.LlaveComparendo;
import model.data_structures.LinearProbingHash;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	public static String PATH = "./data/comparendos_DEI_2018_Bogotá_D.C_small.geojson";
	//	public static String PATH = "./data/comparendos_DEI_2018_Bogotá_D.C.geojson";

	/**
	 * Lista de comparendos
	 */
	private List<Comparendo> datos1;

	/**
	 * Metodo que hace la carga de los datos comparendos
	 * @return Una lista con los comparendos leidos
	 */
	public List<Comparendo> cargarDatos() 
	{
		List<Comparendo> datos = new ArrayList<Comparendo>();

		JsonReader reader;
		try 
		{
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			for(JsonElement e: e2) 
			{
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();
				String cambio1 = s.replaceFirst("T", " ");
				String cambio2 = cambio1.replaceAll("Z", "");
				Date FECHA_HORA = parser.parse(cambio2); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				String MUNICIPIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("MUNICIPIO").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo nuevo = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, MUNICIPIO, longitud, latitud);
				datos.add(nuevo);
			}

		} 
		catch (FileNotFoundException | ParseException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return datos;			
	}

	/**
	 * Convierte la lista de objetos cargados en una tabla de hash - Linear Probing
	 */
	public LinearProbingHash<LlaveComparendo, Comparendo> darTablaHashLinearProbing()
	{
		LinearProbingHash<LlaveComparendo, Comparendo> tablaLinearProbing = new LinearProbingHash<LlaveComparendo, Comparendo>();
		datos1 = cargarDatos();

		Iterator<Comparendo> it = datos1.iterator();
		while(it.hasNext())
		{
			for(int i = 0; i < datos1.size(); i++)
			{
				Comparendo elementoActual = it.next();
				tablaLinearProbing.put(new LlaveComparendo(elementoActual.getFecha_hora(), elementoActual.getClase_vehi(), elementoActual.getInfraccion()), new Comparendo(elementoActual.getObjective(), elementoActual.getFecha_hora(), elementoActual.getDes_infrac(), elementoActual.getMedio_dete(), elementoActual.getClase_vehi(), elementoActual.getTipo_servi(), elementoActual.getInfraccion(), elementoActual.getLocalidad(), elementoActual.getMunicipio(), elementoActual.getLongitud(), elementoActual.getLatitud()));
			}
		}

		return tablaLinearProbing;
	}
	
	// TODO Convertir la lista de objetos cargados en una tabla de hash - Separate Chainging
	
	/**
	 * Calcular el tiempo en hacer get en un rango de 0 a 10000, haciendo de la forma aleatoria, 8000 llaves existente y 2000 llaves no esxistentes
	 */
	public void darConsultasGetLinearProbing()
	{
		LinearProbingHash<LlaveComparendo, Comparendo> tablaLinearProbing = darTablaHashLinearProbing();
		Iterator<LlaveComparendo> resultado1 = tablaLinearProbing.keys().iterator();
		int j = 0;

		while(resultado1.hasNext() && j <= 10000)
		{
			LlaveComparendo llavesExistente = resultado1.next();
			LlaveComparendo llavesNoExistente = new LlaveComparendo(llavesExistente.getFecha_Hora(), "Particular", "C50");
			int numeroAleatorio1 = (int) (Math.random()* 8000);
			int numeroAleatorio2 = (int) (Math.random()* 2000);
			
			for(int i = 0; i <= numeroAleatorio1; i++)
			{
				tablaLinearProbing.get(llavesExistente);
			}
			
			for(int i = 0; i <= numeroAleatorio2; i++)
			{
				tablaLinearProbing.get(llavesNoExistente);
			}
			
			j++;
		}
	}

	/**
	 * Calcular el tiempo en hacer get en un rango de 0 a 10000, haciendo de la forma aleatoria, 8000 llaves existente y 2000 llaves no esxistentes
	 */
	public void darConsultasGetSeparateChaining() 
	{
		// TODO Realiaza el metodo del requerimiento 3 parecido al anterior
		// TODO No olvidar hacer los test y llenar el documento
	}
	
}


