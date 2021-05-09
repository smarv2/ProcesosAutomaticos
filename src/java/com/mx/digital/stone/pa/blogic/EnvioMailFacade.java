/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.digital.stone.pa.blogic;

import com.mx.digital.stone.pa.utils.Constantes;
import com.mx.digital.stone.pa.vo.MensajesMailVO;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 	Mario Alan Ramirez Vazquez.
 *	@author MXI01020253A
 *      @version 1.00, 04/01/2018
 */
public class EnvioMailFacade {
    
    /**
     * Campo LOG de tipo Logger.
     */
    protected static final Logger LOG = LogManager.getLogger(EnvioMailFacade.class);
    
    /**
     * Metodo SendMail.
     * @param mensajesMailVO
     * @throws java.lang.Exception 
     */
    public void SendMail(MensajesMailVO mensajesMailVO) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", "mail.digitalstonemx.com");
        props.put("mail.smtp.port", "26");
        props.put("mail.smtp.ssl.trust", "*");
        
        //Variables de conexion;
        final String Username = Constantes.USER_SMTP;
        final String PassWord = Constantes.PASSWORD_SMTP;

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, PassWord);
                    }
                });
        
        //session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //session.getProperties().put("mail.smtp.starttls.enable", "true");

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Constantes.FROM_SMTP));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mensajesMailVO.getDestinatarios()));
            //message.setFileName("C:\\applogs\\logMensajesMailBatch.log");
            message.setSubject(mensajesMailVO.getAsunto());
            
            message.setText(mensajesMailVO.getMensaje());
            message.setReplyTo(InternetAddress.parse(mensajesMailVO.getRespondera()));

            Transport.send(message);
            LOG.info("mensaje enviado.");

        } catch (MessagingException e) {
            LOG.error("Ocurrio un error al enviar el correo." , e);
            throw new Exception(e);
        }
    }
    
}
