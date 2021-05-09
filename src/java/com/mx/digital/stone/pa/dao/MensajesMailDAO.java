/*
 * (#)MensajesMailDAO.java 1.00 04/01/2018
 * 
 * Copyright (c) 2018 SURA Mexico. Derechos reservados. https://www.suramexico.com/afore/
 */
package com.mx.digital.stone.pa.dao;

import com.mx.digital.stone.pa.utils.ConexionMySQL;
import com.mx.digital.stone.pa.vo.MensajesMailVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 	Mario Alan Ramirez Vazquez.
 *	@author MXI01020253A
 *      @version 1.00, 04/01/2018
 */
public class MensajesMailDAO {

    /**
     * Campo LOG de tipo Logger.
     */
    protected static final Logger LOG = LogManager.getLogger(MensajesMailDAO.class);
    

    /**
     * Campo OBTEN_MENSAJES_SIN_ENVIAR de tipo String.
     */
    private static final String OBTEN_MENSAJES_SIN_ENVIAR = "select * from mensajes_mail where enviado = 0 LIMIT 5";

    /**
     * Campo ACTUALIZA_MENSAJES_MAIL de tipo String.
     */
    private static final String ACTUALIZA_MENSAJES_MAIL = "update mensajes_mail set enviado = ?, fechaEnvio = NOW() "
            + "where idMensajesMail = ?";

    /**
     * Metodo que obtiene los reguistros de datos personales.
     *
     * @return List {@code List<MensajesMailVO>}
     * @throws Exception en caso de error.
     */
    public final List<MensajesMailVO> getCorreosSinEnviar() throws Exception {

        List<MensajesMailVO> list = new ArrayList<MensajesMailVO>();
        ConexionMySQL mysql = new ConexionMySQL();
        Connection con = mysql.conectar();

        String sql = OBTEN_MENSAJES_SIN_ENVIAR;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            LOG.info("ps: " + ps);
            //System.out.println("ps: " + ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                MensajesMailVO vo = new MensajesMailVO();
                vo.setIdMensajesMail(rs.getInt("idMensajesMail"));
                vo.setDestinatarios(rs.getString("destinatarios"));
                vo.setRespondera(rs.getString("respondera"));
                vo.setAsunto(rs.getString("asunto"));
                vo.setAdjuntos(rs.getString("adjuntos"));
                vo.setMensaje(rs.getString("mensaje"));
                vo.setFechaRegistro(rs.getDate("fechaRegistro"));
                vo.setFechaEnvio(rs.getDate("fechaEnvio"));
                vo.setEnviado(rs.getInt("enviado"));
                list.add(vo);
            }

        } catch (Exception e) {
            LOG.error("No se pudo obtener el detalle de BD: " + e);
            throw new Exception("Error en getCorreosSinEnviar()", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e2) {
                LOG.error("Ocurrio un error al liberar recursos: " + e2.getMessage());
            }
        }
        return list;
    }

    /**
     * Metodo actualizaMensajesMail.
     *
     * @param enviado {@code int}
     * @param idMensaje {@code int}
     * @throws Exception en caso de error.
     */
    public final void actualizaMensajesMail(final int enviado, final int idMensaje) throws Exception {
        LOG.info("Actualizando mensajes mail.");
        ConexionMySQL mysql = new ConexionMySQL();
        Connection con = mysql.conectar();

        String sql = ACTUALIZA_MENSAJES_MAIL;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, enviado);
            ps.setInt(2, idMensaje);
            //System.out.println("ps: " + ps);
            LOG.info(ps);
            ps.execute();
        } catch (Exception e) {
            LOG.error("No se pudo obtener el detalle de BD: " + e);
            throw new Exception("Error en actualizaMensajesMail()");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e2) {
                LOG.error("Ocurrio un error al liberar recursos: " + e2.getMessage());
            }
        }
    }

}
