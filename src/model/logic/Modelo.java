package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Comparendo;
import model.data_structures.MaxColaCP;

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
	 * Convierte la lista de objetos cargados a cola de prioridad
	 */
	public MaxColaCP<Comparendo> darColaPrioridadMaxCP()
	{
		MaxColaCP<Comparendo> colaPrioridad = new MaxColaCP<Comparendo>();
		datos1 = cargarDatos();
		shuffle(datos1);

		Iterator<Comparendo> it = datos1.iterator();
		while(it.hasNext())
		{
			for(int i = 0; i < datos1.size(); i++)
			{
				Comparendo elementoActual = it.next();
				colaPrioridad.insert(new Comparendo(elementoActual.getObjective(), elementoActual.getFecha_hora(), elementoActual.getDes_infrac(), elementoActual.getMedio_dete(), elementoActual.getClase_vehi(), elementoActual.getTipo_servi(), elementoActual.getInfraccion(), elementoActual.getLocalidad(), elementoActual.getMunicipio(), elementoActual.getLongitud(), elementoActual.getLatitud()));
			}
		}

		return colaPrioridad;
	}

	// TODO Convertir la lista en MaxHeapCP

	/**
	 * Desorganiza la lista y la vuelve totalmente en desorden utilizando Random
	 * @param list Lista de comparendos
	 */
	public static void shuffle(List<Comparendo> list) 
	{
		Random random = new Random(); 
		int count = list.size() - 1;
		for (int i = count; i > 1; i--) 
		{
			Collections.swap(list, i, random.nextInt(i));
		} 
	}

	/**
	 * Da los comparendos que se encuentran mas hacia el norte
	 * @param listaTipo Lista del clase de vehiculos
	 * @return MaxColaCP con los comparendos mas al norte
	 */
	public MaxColaCP<Comparendo> darComparendosMasNorteYTipo(List<String> listaTipo)
	{
		MaxColaCP<Comparendo> colaMasNorte = new MaxColaCP<Comparendo>();

		String tipo[] = new String[listaTipo.size()];
		for(int i = 0; i < listaTipo.size(); i++)
		{
			tipo[i] = listaTipo.get(i);
		}

		for(int i = 0; i < tipo.length; i++)
		{
			Iterator<Comparendo> comparendosMaxCola = darColaPrioridadMaxCP().iterator();
			while(comparendosMaxCola.hasNext())
			{
				Comparendo elementoActual = comparendosMaxCola.next();
				if(elementoActual.getClase_vehi().equals(tipo[i]))
				{
					colaMasNorte.insert(new Comparendo(elementoActual.getObjective(), elementoActual.getFecha_hora(), elementoActual.getDes_infrac(), elementoActual.getMedio_dete(), elementoActual.getClase_vehi(), elementoActual.getTipo_servi(), elementoActual.getInfraccion(), elementoActual.getLocalidad(), elementoActual.getMunicipio(), elementoActual.getLongitud(), elementoActual.getLatitud()));
				}
			}
		}
		
		return colaMasNorte;	
	}
	
	// TODO hacer el metodo del requerimiento 2 puede ser parecido al de arriba
}
