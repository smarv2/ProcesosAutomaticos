/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.digital.stone.pa.blogic;


import com.mx.digital.stone.pa.dao.MensajesMailDAO;
import com.mx.digital.stone.pa.utils.Constantes;
import com.mx.digital.stone.pa.vo.MensajesMailVO;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 	Mario Alan Ramirez Vazquez.
 *	@author MXI01020253A
 *      @version 1.00, 04/01/2018
 */
public class EnviaCorreoFacade {
    
    protected static final Logger LOG = LogManager.getLogger(EnviaCorreoFacade.class);
    
    public void enviaCorreos(){
        try {
            LOG.info("Enviado correros");
            
            //Se obitenen correos sin enviar.
            LOG.info("Obteniendo correos sin enviar.");
            List<MensajesMailVO> listaMensajes;
            MensajesMailDAO mensajesMailDAO = new MensajesMailDAO();
            listaMensajes = mensajesMailDAO.getCorreosSinEnviar();
            
            LOG.info("Tamaño de la lista de correos sin enviar: " + listaMensajes.size());
            
            if (listaMensajes.size() > 0) {
                LOG.info("Hay " + listaMensajes.size() + " mensajes por enviar.");
                //Se envian correos
                enviaCorreos(listaMensajes);
            } else {
                LOG.info("No hay mensajes por enviar.");
            }
        } catch (Exception ex) {
            LOG.error("Error al enviar correos." , ex);
        }
    }
    
    private void enviaCorreos(final List<MensajesMailVO> listaMensajes) throws Exception {
        MensajesMailDAO mensajesMailDAO = new MensajesMailDAO();
        EnvioMailFacade envioMailFacade = new EnvioMailFacade();
        //Se itera la lista
        for (MensajesMailVO mensajesMailVO : listaMensajes) {
            try {
                //Se envia mensaje
                LOG.info("Se envia mensaje: " + mensajesMailVO.getMensaje() + " a "
                        + mensajesMailVO.getDestinatarios());
                
                envioMailFacade.SendMail(mensajesMailVO);

                //Se actualiza a enviado exitoso.
                mensajesMailDAO.actualizaMensajesMail(Constantes.ENVIO_EXITOSO, mensajesMailVO.getIdMensajesMail());

            } catch (Exception e) {
                //Se actualiza a enviado con error.
                LOG.error("No se pudo enviar el correo electronico" , e);
                mensajesMailDAO.actualizaMensajesMail(Constantes.ENVIO_FALLIDO, mensajesMailVO.getIdMensajesMail());
            }

        }
    }
    
}
