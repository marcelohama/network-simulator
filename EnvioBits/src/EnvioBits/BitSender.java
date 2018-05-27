package EnvioBits;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

public class BitSender extends Thread {

    private JEditorPane mensagem_a_enviar;
    private JTextPane mensagem_enviada;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String chatServer;
    private Socket client;
    private JToggleButton bt;
    private int modo;
    private JCheckBox hamming;
    private JCheckBox checksum;
    private JCheckBox crc;
    private JCheckBox jCheckBox_erro;
    
    public BitSender(JEditorPane mensagem_a_enviar, JTextPane mensagem_enviada,
        String host, JToggleButton bt, int modo, JCheckBox checksum,
        JCheckBox crc, JCheckBox hamming, JCheckBox jCheckBox_erro
    ) {
        chatServer = host;
        this.mensagem_a_enviar = mensagem_a_enviar;
        this.mensagem_enviada = mensagem_enviada;
        this.bt = bt;
        this.modo = modo;
        this.checksum = checksum;
        this.crc = crc;
        this.hamming = hamming;
        this.jCheckBox_erro = jCheckBox_erro;
    }
    
    @Override
    public void run() {
        try {
            connectToServer();
            getStreams();
            processConnection();
        }
        catch ( EOFException eofException ) {
            System.err.println( "O Servidor encerrou a conexão!" );
        }
        catch ( IOException ioException ) {
            ioException.printStackTrace();
        }
        catch (NullPointerException ex) {
            
        }
        finally {
            bt.setSelected(false);
            bt.setEnabled(true);
            mensagem_a_enviar.setEditable(true);
            checksum.setEnabled(true);
            crc.setEnabled(true);
            hamming.setEnabled(true);
            closeConnection();
        }
    }
    
    private void processConnection() throws IOException {
        try {
            int posicao = 0;
            while(posicao < mensagem_a_enviar.getText().length()) {
                String s = mensagem_a_enviar.getText().substring(posicao++,posicao);
                if(modo == 0) s = CheckSum.getCodigo(s);
                if(modo == 1) s = CRC.getCodigo(s);
                if(modo == 2) s = Hamming.getCodigo(s);
                // cria um erro na mensagem
                mensagem_enviada.setText(s);
                if(jCheckBox_erro.isSelected() == true) {
                    s = GeradorErro.getErro(s);
                }
                sendData(s);
                try {
                    sleep(1000);
                }
                catch (InterruptedException ex) {

                }
            }
        }
        catch ( AbstractMethodError ex ) {
        }
    }
    
    private void getStreams() throws IOException {
        output = new ObjectOutputStream( client.getOutputStream() );
        output.flush();
        input = new ObjectInputStream( client.getInputStream() );
        
    }
    
    private void connectToServer() throws IOException {
        try {
            client = new Socket( InetAddress.getByName( chatServer ), 12345 );
        }
        catch (ConnectException ex) {
        }
        catch (UnknownHostException ex) {
            mensagem_a_enviar.setText("Host desconhecido");
        }
    }
    
    private void sendData( String message ) {
        try {
            output.writeObject( "" + message );
            output.flush();
        }
        catch ( IOException ioException ) {
        }
    }
    
    private void closeConnection() {
        try {
            output.close();
            input.close();
            client.close();
        }
        catch( IOException ioException ) {
            ioException.printStackTrace();
        }
        catch (NullPointerException ex) {
            
        }
    }
    
}
