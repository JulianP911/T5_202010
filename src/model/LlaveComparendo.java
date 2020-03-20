package model;

import java.util.Date;
import java.util.Objects;

public class LlaveComparendo implements Comparable<LlaveComparendo>
{
	/**
	 * Fecha en la cual fue tomado el comparendo
	 */
	private Date fecha_hora;

	/**
	 * Clase de vehiculo al cual se le aplico el comparendo
	 */
	private  String clase_vehi;

	/**
	 * Infraccion impuesta al vehiculo
	 */
	private String infraccion;
	
	public LlaveComparendo(Date pFecha, String pClase_Vehi, String pInfraccion) 
	{
		fecha_hora = pFecha;
		clase_vehi = pClase_Vehi;
		infraccion = pInfraccion;
	}

	@Override
	public int hashCode()
	{
		int hash = 17;
		hash = 31 * hash + fecha_hora.hashCode();
		hash = 31 * hash + clase_vehi.hashCode();
		hash = 31 * hash + infraccion.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj) 
		{
			return true;
		}
		if (obj == null) 
		{
			return false;
		}
		if (getClass() != obj.getClass()) 
		{
			return false;
		}
		final LlaveComparendo other = (LlaveComparendo) obj;
		if (!Objects.equals(this.fecha_hora, other.fecha_hora)) 
		{
			return false;
		}
		if (!Objects.equals(this.clase_vehi, other.clase_vehi)) 
		{
			return false;
		}
		if (!Objects.equals(this.infraccion, other.infraccion)) 
		{
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(LlaveComparendo pObjeto) 
	{
		int resultado = 0;
		
		if(this.fecha_hora.compareTo(pObjeto.fecha_hora) < 0)
		{
			resultado = -1;
		}
		else if(this.fecha_hora.compareTo(pObjeto.fecha_hora) == 0)
		{
			resultado = 0;
		}
		else if(this.fecha_hora.compareTo(pObjeto.fecha_hora) > 0)
		{
			resultado = 1;
		}

		return resultado;
	}

}
