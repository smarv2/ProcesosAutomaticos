/*
 * (#)ConexionMySQL.java 1.00 04/01/2018
 * 
 * Copyright (c) 2018 SURA Mexico. Derechos reservados. https://www.suramexico.com/afore/
 */
package com.mx.digital.stone.pa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Mario Alan Ramirez Vazquez.
 * 
 * @author MXI01020253A
 * @version 1.00, 04/01/2018
 */
public class ConexionMySQL {
	
	/**
	 * Campo LOG de tipo Logger.
	 */
        protected static final Logger LOG = LogManager.getLogger(ConexionMySQL.class);

	/**
	 * Campo DB de tipo String.
	 */
	public static final String DB = Constantes.DB;
        /**
	 * Campo HOST de tipo String.
	 */
        public static final String HOST = Constantes.DB_HOST;
	/**
	 * Campo URL de tipo String.
	 */
	public static final String URL = "jdbc:mysql://" + HOST + "/" + DB;
	/**
	 * Campo USER de tipo String.
	 */
	public static final String USER = Constantes.DB_USER;
	/**
	 * Campo PASS de tipo String.
	 */
	public static final String PASS = Constantes.DB_PASSWORD;

	/*
	 * public ConexionMySQL() { }
	 */

	/**
	 * Metodo que realiza la conexion a DB.
	 * 
	 * @return link
	 */
	public final Connection conectar() throws Exception {
		Connection link = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			link = DriverManager.getConnection(ConexionMySQL.URL, ConexionMySQL.USER, ConexionMySQL.PASS);
			//LOG.info("Conexion exitosa.");
		} catch (Exception e) {
			LOG.error("Error al realiar la conexion: " + e);
                        throw new Exception("Error al realiar la conexion a BD.");
		}
		return link;
	}

}
