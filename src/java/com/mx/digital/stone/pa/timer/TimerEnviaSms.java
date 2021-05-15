/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.digital.stone.pa.timer;

import com.mx.digital.stone.pa.blogic.EnviaSmsHilo;
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
public class TimerEnviaSms extends TimerTask {
    
    protected static final Logger LOG = LogManager.getLogger(TimerEnviaSms.class);
    
    /* Creates a new instance of TimerEnviaCorreo */
    public TimerEnviaSms() {
    }
    private static Timer Reloj = new Timer();

    public static void Start() {
        TimerTask timerEnviaSms = new TimerEnviaSms();
        Reloj.schedule(timerEnviaSms, getNextExec());
    }

    public void run() {
        Genera();
    }

    private synchronized static void Genera() {
        if (EnviaSmsHilo.getEstatus() == true) {
            EnviaSmsHilo.EnviaSmss();
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
        //LOG.info("Fecha Hora proxima ejecucion : " + calendario.DATE);
        return result.getTime();
    }
}
