/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.digital.stone.pa;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author smarv
 */
public class TimerEnviaCorreo extends TimerTask {
    
    protected static final Logger LOG = LogManager.getLogger(TimerEnviaCorreo.class);
    
    /* Creates a new instance of TimerEnviaCorreo */
    public TimerEnviaCorreo() {
    }
    private static Timer Reloj = new Timer();

    public static void Start() {
        TimerTask TimerEnviaCorreo = new TimerEnviaCorreo();
        Reloj.schedule(TimerEnviaCorreo, getNextExec());
    }

    public void run() {
        Genera();
    }

    private synchronized static void Genera() {
        if (EnviaCorreoFacade.getEstatus() == true) {
            EnviaCorreoFacade.EnviaCorreos();
            Start();
        }
    }

    public static Date getNextExec() {
        Calendar calendario = new GregorianCalendar();
        calendario.add(Calendar.DATE, 0);
        //System.out.println("Fecha Hora Actual : " + calendario.DATE);
        Calendar result = new GregorianCalendar(
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DATE),
                calendario.get(Calendar.HOUR_OF_DAY),
                calendario.get(Calendar.MINUTE) + 1,
                calendario.get(Calendar.SECOND));
        //System.out.println("Fecha Hora proxima ejecucion : " + calendario.DATE);
        LOG.info("Fecha Hora proxima ejecucion : " + calendario.DATE);
        return result.getTime();
    }
}
