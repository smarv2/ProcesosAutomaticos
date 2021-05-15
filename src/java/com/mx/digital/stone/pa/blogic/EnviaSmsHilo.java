/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.digital.stone.pa.blogic;

import com.mx.digital.stone.pa.timer.TimerEnviaSms;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author smarv
 */
public class EnviaSmsHilo {
    
    protected static final Logger LOG = LogManager.getLogger(EnviaSmsHilo.class);
    
    private static boolean isStarted = false;
    

    public EnviaSmsHilo() {}

    public static boolean getEstatus() {
        return isStarted;
    }
    
    public static synchronized void EnviaSmss() {
        LOG.info("************ ENVIANDO SMS'S *************");
        EnviaSMSFacade enviaSMSFacade = new EnviaSMSFacade();
        enviaSMSFacade.enviaSmss();
    }
    
     public static void Start() {
        chStatus(true);
        TimerEnviaSms.Start();
    }

    public static void Stop() {
        chStatus(false);
    }

    private static void chStatus(boolean pEstatus) {
        isStarted = pEstatus;
    }
    
}
