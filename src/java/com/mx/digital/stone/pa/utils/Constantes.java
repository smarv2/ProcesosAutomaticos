/*
 * (#)Constantes.java 1.00 04/01/2018
 * 
 * Copyright (c) 2018 SURA Mexico. Derechos reservados. https://www.suramexico.com/afore/
 */
package com.mx.digital.stone.pa.utils;

/**
 * 	Mario Alan Ramirez Vazquez.
 *	@author MXI01020253A
 *      @version 1.00, 04/01/2018
 */
public class Constantes {
	
	//ENVIO DE CORREO
	/**
	 * Campo ENVIO_EXITOSO de tipo int.
	 */
	public static final int ENVIO_EXITOSO = 1;
	/**
	 * Campo ENVIO_FALLIDO de tipo int.
	 */
	public static final int ENVIO_FALLIDO = 2;
        /**
	 * Campo PASSWORD_SMTP de tipo String.
	 */
        public static final String PASSWORD_SMTP = "M4r10r48.g";
        /**
	 * Campo USER_SMTP de tipo String.
	 */
        public static final String USER_SMTP = "robot@digitalstonemx.com";
        /**
	 * Campo FROM_SMTP de tipo String.
	 */
        public static final String FROM_SMTP = "robot@digitalstonemx.com";

        //SMS
        //private static final Stirng comPort = "COM14"; 
        public static final String COM_PORT = "/dev/ttyUSB0"; 
        public static final int TIME_SLEEP = 2000;
        
        //DB
        public static final String DB_HOST = "192.168.1.79";
        public static final String DB = "procesos_automaticos";
        public static final String DB_USER = "remoto";
        public static final String DB_PASSWORD = "remotopass";
        
        
        
        
}
