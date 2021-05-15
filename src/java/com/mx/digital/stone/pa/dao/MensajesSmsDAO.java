package com.mx.digital.stone.pa.dao;

import com.mx.digital.stone.pa.utils.ConexionMySQL;
import com.mx.digital.stone.pa.vo.MensajesSmsVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author smarv
 */
public class MensajesSmsDAO {

    /**
     * Campo LOG de tipo Logger.
     */
    protected static final Logger LOG = LogManager.getLogger(MensajesSmsDAO.class);
    

    /**
     * Campo OBTEN_MENSAJES_SIN_ENVIAR de tipo String.
     */
    private static final String OBTEN_MENSAJES_SIN_ENVIAR = "select * from mensajes_sms where enviado = 0 LIMIT 5";

    /**
     * Campo ACTUALIZA_MENSAJES_MAIL de tipo String.
     */
    private static final String ACTUALIZA_MENSAJES_MAIL = "update mensajes_sms set enviado = ?, fechaEnvio = NOW() "
            + "where idMensajeSms = ?";

    /**
     * Metodo que obtiene los reguistros de datos personales.
     *
     * @return List {@code List<MensajesMailVO>}
     * @throws Exception en caso de error.
     */
    public final List<MensajesSmsVO> getSmsSinEnviar() throws Exception {

        List<MensajesSmsVO> list = new ArrayList<MensajesSmsVO>();
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
                MensajesSmsVO vo = new MensajesSmsVO();
                vo.setIdMensajeSms(rs.getInt("idMensajeSms"));
                vo.setDestinatario(rs.getString("destinatario"));
                vo.setMensaje(rs.getString("mensaje"));
                vo.setFechaRegistro(rs.getDate("fechaRegistro"));
                vo.setFechaEnvio(rs.getDate("fechaEnvio"));
                vo.setEnviado(rs.getInt("enviado"));
                list.add(vo);
            }

        } catch (Exception e) {
            LOG.error("No se pudo obtener el detalle de BD: " + e);
            throw new Exception("Error en getSmsSinEnviar()", e);
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
    public final void actualizaMensajesSms(final int enviado, final int idMensaje) throws Exception {
        LOG.info("Actualizando mensajes sms.");
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
            throw new Exception("Error en actualizaMensajesSms()");
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
