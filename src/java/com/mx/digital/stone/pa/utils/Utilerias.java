/*
 * (#)Utilerias.java 1.00 04/01/2018
 * 
 * Copyright (c) 2018 SURA Mexico. Derechos reservados. https://www.suramexico.com/afore/
 */
package com.mx.digital.stone.pa.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 	Mario Alan Ramirez Vazquez.
 *	@author MXI01020253A
 *  @version 1.00, 04/01/2018
 */
public class Utilerias {
	
	/**
	 * Campo LOG de tipo Logger.
	 */
        protected static final Logger LOG = LogManager.getLogger(Utilerias.class);
	
    /**
     * Campo CLOSE_RS_WARN de tipo String.
     */
    private static final String CLOSE_RS_WARN = "Ocurri\u00F3 un error al liberar los recursos del ResultSet.";
    /**
     * Campo CLOSE_PS_WARN de tipo String.
     */
    private static final String CLOSE_PS_WARN = "Ocurri\u00F3 un error al liberar los recursos del PreparedStatement.";
    /**
     * Campo CLOSE_CS_WARN de tipo String.
     */
    private static final String CLOSE_CS_WARN = "Ocurri\u00F3 un error al liberar los recursos del CallableStatement.";
    /**
     * Campo CLOSE_CONN_WARN de tipo String.
     */
    private static final String CLOSE_CONN_WARN = "Ocurri\u00F3 un error al cerrar la conexi\u00F3n";
    /**
	 * Constante para substraer un d&iacute;a.
	 */
	public static final int SUBTRACT_ONE_DAY = -1;
	/**
	 * Constante para agregar un d&iacute;a.
	 */
	public static final int ADD_ONE_DAY = 1;
	
	/**
	 * Convierte una fecha a una cadena.
	 * @param date una instancia de {@link java.util.Date}
	 * @param format formato para la fecha
	 * @return una cadena con la fecha
	 */
	public static String dateToString(final java.util.Date date, final String format) {
		final SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		return sdf.format(date);
	}
	
	/**
	 * Convierte una Cadena a una fecha.
	 * @param cadena una instancia de {@link java.lang.String}
	 * @param format formato para la fecha
	 * @return una fecha
	 * @throws ParseException excepcion 
	 */
	public static java.util.Date stringToDate(final String cadena, final String format) throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		return sdf.parse(cadena);
	}
	
	/**
	 * Valida si no es vacia una cadena.
	 * @param cadena {@code String} para valida.
	 * @return true no es vacia, false es vacia.
	 */
	public static boolean isNotEmpty(final String cadena) {
		return cadena != null && !cadena.trim().equals("");
	}
	
	/**
	 * Obtiene el ultimo dia del mes de una fecha.
	 * @param fecha {@code Date} fecha de entrada.
	 * @return {@code Integer} ultimo dia del mes.
	 */
	public static Integer obtenUltimoDiaDelMes(final Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
	}
	
	/**
	 * Metodo close.
	 * @param preparedStatement {@code PreparedStatement}
	 */
    public static void close(final PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                LOG.warn(CLOSE_PS_WARN, ex);
            }
        }
    }
    
    /**
     * Metodo close.
     * @param conn {@code Connection}
     */
    public static void close(final Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            LOG.warn(CLOSE_CONN_WARN, ex);
        }
    }
    
    /**
     * Metodo close.
     * @param resultSet {@code ResultSet}
     */
    public static void close(final ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                LOG.warn(CLOSE_RS_WARN, ex);
            }
        }
    }
    
    /**
     * Metodo close.
     * @param callableStatement {@code CallableStatement}
     */
    public static void close(final CallableStatement callableStatement) {
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException ex) {
                LOG.warn(CLOSE_CS_WARN, ex);
            }
        }
    }
    
    /**
     * Metodo obtenerAnio.
     * @param date {@code Date}
     * @return {@code int}
     */
    public static final int obtenerAnio(final Date date) {
        if (null == date) {
            return 0;
        } else {
            String formato = "yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));
        }
    }
    
    /**
     * Metodo obtenFechaActual.
     * @return {@code Date}
     */
    public static Date obtenFechaActual() {
        Date ahora = new Date();
        return ahora;
    }
    
    /**
     * Metodo objToStr.
     * @param obj {@code Object}
     * @return {@code String}
     */
	public static String objToStr(final Object obj) {
		String ret = "";
		ret = obj != null ? obj.toString() : "";
		return ret.trim();
	}

	/**
	 * @Metodo que agrega un String a una coleccion para poder procesarlo
	 *         en el metodo de mail
	 * @param  to  destinatario        
	 * @return nombresCol coleccion con destinatario
	 */
	public static Collection<String> obtenerColeccion(final String to) {
		Collection<String> nombresCol = new LinkedList<String>();
    	//Se agregan los destinatarios a la coleccion
		nombresCol.add(to);
		return nombresCol;
	}
		
	/**
	 * Obtiene el dia del mes atraves de una fecha.
	 * @param fecha {@code Date} fecha de entrada.
	 * @return {@code Integer} dia del mes.
	 */
	public static Integer obtenDia(final Date fecha) {
		Integer dia = null;
		if (fecha != null) {
			final Calendar calAux = Calendar.getInstance();
			calAux.setTime(fecha);
			dia = calAux.get(Calendar.DAY_OF_MONTH);
		}
		return dia;
	}	
	
}
