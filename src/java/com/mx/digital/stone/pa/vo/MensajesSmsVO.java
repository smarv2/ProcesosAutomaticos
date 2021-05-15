/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.digital.stone.pa.vo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smarv
 */
public class MensajesSmsVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idMensajeSms;
    private String destinatario;
    private String mensaje;
    private Date fechaRegistro;
    private Date fechaEnvio;
    private int enviado;

    /**
     * @return the idMensajeSms
     */
    public int getIdMensajeSms() {
        return idMensajeSms;
    }

    /**
     * @param idMensajeSms the idMensajeSms to set
     */
    public void setIdMensajeSms(int idMensajeSms) {
        this.idMensajeSms = idMensajeSms;
    }

    /**
     * @return the destinatario
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaEnvio
     */
    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * @param fechaEnvio the fechaEnvio to set
     */
    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     * @return the enviado
     */
    public int getEnviado() {
        return enviado;
    }

    /**
     * @param enviado the enviado to set
     */
    public void setEnviado(int enviado) {
        this.enviado = enviado;
    }
    
    
}
