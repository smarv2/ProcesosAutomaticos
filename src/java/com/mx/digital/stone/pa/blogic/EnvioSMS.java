package com.mx.digital.stone.pa.blogic;

import com.mx.digital.stone.pa.exception.BlogicException;
import com.mx.digital.stone.pa.utils.Constantes;
import com.mx.digital.stone.pa.vo.MensajesSmsVO;
import gnu.io.*;
import java.io.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvioSMS implements SerialPortEventListener, CommPortOwnershipListener {
    
    protected static final Logger LOG = LogManager.getLogger(EnvioSMS.class);

    // This COM Port must be connect with GSM Modem or your mobile phone
    //private static String comPorts = "COM14"; 
    private static String comPort = ""; 
    
    //private String messageString = "";
    private CommPortIdentifier portId = null;
    private Enumeration portList;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private SerialPort serialPort;
    String readBufferTrial = "";

    /**
     * Creates a new instance of GSMConnect
     * @param comm
     */
    public EnvioSMS(String comm) {
        EnvioSMS.comPort = comm;
    }

    public boolean init() {
        LOG.info("Obteniendo puerto: " + comPort);
        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(comPort)) {
                    LOG.info("puerto obtenido: " + comPort);
                    return true;
                }
            }
        }
        return false;
    }

    public void checkStatus() {
        send("AT+CREG?\r\n");
    }

    public void send(String cmd) {
        try {
            outputStream.write(cmd.getBytes());
        } catch (IOException e) {
            LOG.error("Error en metodo send.", e);
        }
    }

    public void sendMessage(String phoneNumber, String message) {
        char quotes = '"';
        try {
            send("AT+CMGF=1\r\n");
            Thread.sleep(Constantes.TIME_SLEEP);
            send("AT+CMGS=" + quotes + phoneNumber + quotes + "\r\n");
            Thread.sleep(Constantes.TIME_SLEEP);
        } catch (InterruptedException e) {
            LOG.error("Error en metodo sendMessage.", e);
        }
        //   send("AT+CMGS=\""+ phoneNumber +"\"\r\n");
        send(message + '\032');
        LOG.info("SMS Enviado.");
    }

    public void hangup() {
        send("ATH\r\n");
    }

    public void connect() throws NullPointerException {
        if (portId != null) {
            try {
                portId.addPortOwnershipListener(this);

                serialPort = (SerialPort) portId.open("MobileGateWay", 2000);
                serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            } catch (PortInUseException | UnsupportedCommOperationException e) {
                LOG.error("Error en metodo connect.", e);
            }

            try {
                inputStream = serialPort.getInputStream();
                outputStream = serialPort.getOutputStream();

            } catch (IOException e) {
                LOG.error("Error en metodo connect.", e);
            }

            try {
                /**
                 * These are the events we want to know about
                 */
                serialPort.addEventListener(this);
                serialPort.notifyOnDataAvailable(true);
                serialPort.notifyOnRingIndicator(true);
            } catch (TooManyListenersException e) {
                LOG.error("Error en metodo connect.", e);
            }

            //Register to home network of sim card
            //send("ATZ\r\n");
            try {
                send("AT\r\n");
            } catch (Error e){
                LOG.error("Error en metodo connect.", e);
            }

        } else {
            throw new NullPointerException("Puerto no encontrado: " + comPort + " !!");
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            case SerialPortEvent.DATA_AVAILABLE:

                byte[] readBuffer = new byte[2048];
                try {
                    while (inputStream.available() > 0) {
                        int numBytes = inputStream.read(readBuffer);

                        System.out.print(numBytes);
                        if ((Arrays.toString(readBuffer)).contains("RING")) {
                            LOG.info("Enter Inside if RING Loop");
                        }
                    }

                    LOG.info("readBuffer: " + new String(readBuffer));
                } catch (IOException e) {
                }
                break;
        }
    }

    public void outCommand() {
        System.out.print(readBufferTrial);
    }

    @Override
    public void ownershipChange(int type) {
        switch (type) {
            case CommPortOwnershipListener.PORT_UNOWNED:
                LOG.info(portId.getName() + ": PORT_UNOWNED");
                break;
            case CommPortOwnershipListener.PORT_OWNED:
                LOG.info(portId.getName() + ": PORT_OWNED");
                break;
            case CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED:
                LOG.info(portId.getName() + ": PORT_INUSED");
                break;
        }

    }

    public void closePort() {
        serialPort.close();
    }

    /*public static void main(String args[]) {
        EnvioSMS gsm = new EnvioSMS(comPort);
        if (gsm.init()) {
            try {
                System.out.println("Initialization Success");
                gsm.connect();
                Thread.sleep(5000);
                System.out.println("Check status");
                gsm.checkStatus();
                Thread.sleep(5000);
                System.out.println("Sending message.");
                gsm.sendMessage("525585342864", "hola amor te amos.");
                Thread.sleep(5000);
                gsm.sendMessage("525511956792", "te puedo marcar?.");
                Thread.sleep(5000);
                System.out.println("hang up");
                gsm.hangup();
                Thread.sleep(5000);
                System.out.println("Closing port");
                gsm.closePort();
                gsm.outCommand();
                //System.exit(1);

            } catch (InterruptedException | NullPointerException e) {
                System.err.println("Error: " + e);
            }
        } else {
            System.out.println("Can't init this port");
        }
    }*/

    //Metodo que envia el SMS
    void SendSMS(MensajesSmsVO mensajesSmsVO) throws BlogicException {
         EnvioSMS gsm = new EnvioSMS(comPort);
        if (gsm.init()) {
            try {
                LOG.info("Inicializacion completada.");
                gsm.connect();
                Thread.sleep(Constantes.TIME_SLEEP);
                LOG.info("Verificando estatus.");
                gsm.checkStatus();
                Thread.sleep(Constantes.TIME_SLEEP);
                LOG.info("Enviando mensaje.");
                gsm.sendMessage(mensajesSmsVO.getDestinatario(), mensajesSmsVO.getMensaje());
                /*Thread.sleep(5000);
                gsm.sendMessage("525511956792", "te puedo marcar?.");*/
                Thread.sleep(Constantes.TIME_SLEEP);
                LOG.info("Colgando.");
                gsm.hangup();
                Thread.sleep(Constantes.TIME_SLEEP);
                LOG.info("Cerrando puerto.");
                gsm.closePort();
                gsm.outCommand();
                //System.exit(1);

            } catch (InterruptedException | NullPointerException e) {
                LOG.error("Error al enviar SMS", e);
                throw new BlogicException("Error al enviar SMS");
            }
        } else {
            LOG.error("No se pudo inicializar el puerto: " + comPort);
            throw new BlogicException("Error al incializar puerto.");
        }
    }

}
