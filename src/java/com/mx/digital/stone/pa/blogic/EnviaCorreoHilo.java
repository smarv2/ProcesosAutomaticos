/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.digital.stone.pa.blogic;

import com.mx.digital.stone.pa.timer.TimerEnviaCorreo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author smarv
 */
public class EnviaCorreoHilo {
    
    protected static final Logger LOG = LogManager.getLogger(EnviaCorreoHilo.class);
    
    private static boolean isStarted = false;
    

    public EnviaCorreoHilo() {}

    public static boolean getEstatus() {
        return isStarted;
    }
    
    public static synchronized void EnviaCorreos() {
        LOG.info("************ ENVIANDO CORREOS *************");
        EnviaCorreoFacade enviaCorreoFacade = new EnviaCorreoFacade();
        enviaCorreoFacade.enviaCorreos();
    }
    
     public static void Start() {
        chStatus(true);
        TimerEnviaCorreo.Start();
    }

    public static void Stop() {
        chStatus(false);
    }

    private static void chStatus(boolean pEstatus) {
        isStarted = pEstatus;
    }
    
}