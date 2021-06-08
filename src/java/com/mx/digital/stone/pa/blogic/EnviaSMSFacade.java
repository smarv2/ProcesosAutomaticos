/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.digital.stone.pa.blogic;

import com.mx.digital.stone.pa.dao.MensajesSmsDAO;
import com.mx.digital.stone.pa.exception.BlogicException;
import com.mx.digital.stone.pa.utils.Constantes;
import com.mx.digital.stone.pa.vo.MensajesSmsVO;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author smarv
 */
public class EnviaSMSFacade {
    
    protected static final Logger LOG = LogManager.getLogger(EnviaSMSFacade.class);
    
    public void enviaSmss(){
        try {
            LOG.info("Enviando sms's");
            
            //Se obitenen correos sin enviar.
            LOG.info("Obteniendo sms's sin enviar.");
            List<MensajesSmsVO> listaSms;
            MensajesSmsDAO mensajesSmsDAO = new MensajesSmsDAO();
            listaSms = mensajesSmsDAO.getSmsSinEnviar();
            
            LOG.info("Tamaño de la lista de sms sin enviar: " + listaSms.size());
            
            if (listaSms.size() > 0) {
                LOG.info("Hay " + listaSms.size() + " mensajes por enviar.");
                //Se envian correos
                enviaSms(listaSms);
            } else {
                LOG.info("No hay sms's por enviar.");
            }
        } catch (Exception ex) {
            LOG.error("Error al enviar sms's." , ex);
        }
    }
    
     private void enviaSms(final List<MensajesSmsVO> listaMensajes) throws Exception {
        MensajesSmsDAO mensajesSmsDAO = new MensajesSmsDAO();
        
        EnvioSMS envioSMS = new EnvioSMS(Constantes.COM_PORT);
        //Se itera la lista
        for (MensajesSmsVO mensajesSmsVO : listaMensajes) {
            try {
                //Se envia mensaje
                LOG.info("Se envia mensaje: " + mensajesSmsVO.getMensaje() + " a "
                        + mensajesSmsVO.getDestinatario());
                
                envioSMS.SendSMS(mensajesSmsVO);

                //Se actualiza a enviado exitoso.
                mensajesSmsDAO.actualizaMensajesSms(Constantes.ENVIO_EXITOSO, mensajesSmsVO.getIdMensajeSms());

            } catch (BlogicException e) {
                //Se actualiza a enviado con error.
                LOG.error("No se pudo enviar el SMS" , e);
                mensajesSmsDAO.actualizaMensajesSms(Constantes.ENVIO_FALLIDO, mensajesSmsVO.getIdMensajeSms());
            }
            
            catch (Exception e) {
                //Se actualiza a enviado con error.
                LOG.error("No se pudo enviar el SMS" , e);
                mensajesSmsDAO.actualizaMensajesSms(Constantes.ENVIO_FALLIDO, mensajesSmsVO.getIdMensajeSms());
            }

        }
        LOG.info("Termina pool de envio de mensajes.");
    }
    
}
