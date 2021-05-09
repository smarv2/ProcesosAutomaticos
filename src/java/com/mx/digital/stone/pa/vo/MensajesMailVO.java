/*
 * (#)MensajesMailVO.java 1.00 04/01/2018
 * 
 * Copyright (c) 2018 SURA Mexico. Derechos reservados. https://www.suramexico.com/afore/
 */
package com.mx.digital.stone.pa.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 	Mario Alan Ramirez Vazquez.
 *	@author MXI01020253A
 *      @version 1.00, 04/01/2018
 */
public class MensajesMailVO implements Serializable {

    /**
     * Campo serialVersionUID de tipo long.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Campo idMensajesMail de tipo int.
     */
    private int idMensajesMail;
    /**
     * Campo destinatarios de tipo String.
     */
    private String destinatarios;
    /**
     * Campo respondera de tipo String.
     */
    private String respondera;
    /**
     * Campo asunto de tipo String.
     */
    private String asunto;
    /**
     * Campo adjuntos de tipo String.
     */
    private String adjuntos;
    /**
     * Campo mensaje de tipo String.
     */
    private String mensaje;
    /**
     * Campo fechaRegistro de tipo Date.
     */
    private Date fechaRegistro;
    /**
     * Campo fechaEnvio de tipo Date.
     */
    private Date fechaEnvio;
    /**
     * Campo envado de tipo int.
     */
    private int enviado;

    /**
     * Get de {@code idMensajesMail}.
     *
     * @return idMensajesMail
     */
    public final int getIdMensajesMail() {
        return idMensajesMail;
    }

    /**
     * Set {@code idMensajesMail}.
     *
     * @param idMensajesMail valor de {@code idMensajesMail}
     */
    public final void setIdMensajesMail(final int idMensajesMail) {
        this.idMensajesMail = idMensajesMail;
    }

    /**
     * Get de {@code destinatarios}.
     *
     * @return destinatarios
     */
    public final String getDestinatarios() {
        return destinatarios;
    }

    /**
     * Set {@code destinatarios}.
     *
     * @param destinatarios valor de {@code destinatarios}
     */
    public final void setDestinatarios(final String destinatarios) {
        this.destinatarios = destinatarios;
    }

    /**
     * Get de {@code mensaje}.
     *
     * @return mensaje
     */
    public final String getMensaje() {
        return mensaje;
    }

    /**
     * Set {@code mensaje}.
     *
     * @param mensaje valor de {@code mensaje}
     */
    public final void setMensaje(final String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Get de {@code fechaRegistro}.
     *
     * @return fechaRegistro
     */
    public final Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Set {@code fechaRegistro}.
     *
     * @param fechaRegistro valor de {@code fechaRegistro}
     */
    public final void setFechaRegistro(final Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Get de {@code fechaEnvio}.
     *
     * @return fechaEnvio
     */
    public final Date getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * Set {@code fechaEnvio}.
     *
     * @param fechaEnvio valor de {@code fechaEnvio}
     */
    public final void setFechaEnvio(final Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     * Get de {@code enviado}.
     *
     * @return enviado
     */
    public final int getEnviado() {
        return enviado;
    }

    /**
     * Set {@code enviado}.
     *
     * @param enviado valor de {@code enviado}
     */
    public final void setEnviado(final int enviado) {
        this.enviado = enviado;
    }

    /**
     * @return the asunto
     */
    public final String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public final void setAsunto(final String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the adjuntos
     */
    public final String getAdjuntos() {
        return adjuntos;
    }

    /**
     * @param adjuntos the adjuntos to set
     */
    public final void setAdjuntos(final String adjuntos) {
        this.adjuntos = adjuntos;
    }

    /**
     * @return the respondera
     */
    public final String getRespondera() {
        return respondera;
    }

    /**
     * @param respondera the respondera to set
     */
    public final void setRespondera(String respondera) {
        this.respondera = respondera;
    }

    
}
