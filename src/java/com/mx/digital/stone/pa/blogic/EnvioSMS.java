package com.mx.digital.stone.pa.blogic;


import com.mx.digital.stone.pa.utils.Constantes;
import com.mx.digital.stone.pa.vo.MensajesSmsVO;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author smarv
 */
public class EnvioSMS {
    
    protected static final Logger LOG = LogManager.getLogger(EnvioSMS.class);

    public void SendSMS(MensajesSmsVO mensajesSmsVO) throws Exception {

        //Se fija el tiempo maximo de espera para conectar con el servidor (5000)
        //Se fija el tiempo maximo de espera de la respuesta del servidor (60000)
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(60000)
                .build();

        //Se inicia el objeto HTTP
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setDefaultRequestConfig(config);
        CloseableHttpClient httpClient = builder.build();

        //Se fija la URL sobre la que enviar la petici�n POST
        HttpPost post = new HttpPost(Constantes.URL_API_ALTIRA);

        //Se crea la lista de par�metros a enviar en la petici�n POST
        List<NameValuePair> parametersList = new ArrayList<NameValuePair>();
        //XX, YY y ZZ se corresponden con los valores de identificaci�n del
        //usuario en el sistema.
        parametersList.add(new BasicNameValuePair("cmd", Constantes.ALTIRA_PARAM_CMD));
        //domainId solo es necesario si el login no es un email
        //parametersList.add(new BasicNameValuePair("domainId", "XX"));
        parametersList.add(new BasicNameValuePair("login", Constantes.ALTIRA_PARAM_USER));
        parametersList.add(new BasicNameValuePair("passwd", Constantes.ALTIRA_PARAM_PASSWORD));
        parametersList.add(new BasicNameValuePair("dest", mensajesSmsVO.getDestinatario()));
        //parametersList.add(new BasicNameValuePair("dest", "346yyyyyyyy"));
        parametersList.add(new BasicNameValuePair("msg", mensajesSmsVO.getMensaje()));
        //No es posible utilizar el remitente en América pero sí en España y Europa
        //Descomentar la línea solo si se cuenta con un remitente autorizado por Altiria
        //parametersList.add(new BasicNameValuePair("senderId", "remitente"));

        try {
            //Se fija la codificacion de caracteres de la peticion POST
            post.setEntity(new UrlEncodedFormEntity(parametersList, "UTF-8"));
        } catch (UnsupportedEncodingException uex) {
            LOG.error("ERROR: codificación de caracteres no soportada");
            throw new Exception("codificación de caracteres no soportada");
        }

        CloseableHttpResponse response = null;

        try {
            LOG.info("Enviando petición SMS");
            //Se envía la petición
            response = httpClient.execute(post);
            //Se consigue la respuesta
            String resp = EntityUtils.toString(response.getEntity());

            //Error en la respuesta del servidor
            if (response.getStatusLine().getStatusCode() != 200) {
                LOG.error("ERROR: Código de error HTTP:  " + response.getStatusLine().getStatusCode());
                throw new Exception("ERROR: Código de error HTTP:  " + response.getStatusLine().getStatusCode());
            } else {
                //Se procesa la respuesta capturada en la cadena 'response'
                if (resp.startsWith("ERROR")) {
                    LOG.info("**Error**");
                    LOG.info("resp: " + resp);
                    LOG.info("Codigo de error de Altiria. Compruebe las especificaciones");
                    throw new Exception("Error al enviar respuesta");    
                } else {
                    LOG.info("Mensaje Enviado");
                    LOG.info("resp: " + resp);
                }
            }
        } catch (Exception e) {
            LOG.error("Excepcion en envio de sms", e);
        } finally {
            //En cualquier caso se cierra la conexi�n
            post.releaseConnection();
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ioe) {
                    LOG.error("ERROR cerrando recursos");
                }
            }
        }
    }
}
