package RecebeBits;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;

public class Receiver extends JFrame {

    private javax.swing.JCheckBox jCheckBox_CRC;
    private javax.swing.JCheckBox jCheckBox_Hamming;
    private javax.swing.JCheckBox jCheckBox_checkSum;
    private javax.swing.JLabel jLabel_bitsCorrigidos;
    private javax.swing.JLabel jLabel_bitsRecebidos;
    private javax.swing.JLabel jLabel_modo;
    private javax.swing.JLabel jLabel_msnRecebida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_msnRecebida;
    private javax.swing.JTextField jTextField_bitsCorrigidos;
    private javax.swing.JTextField jTextField_bitsRecebidos;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private List<ServerSocket> clientes;
    private List<Socket> connections;
    private int counter = 1;
    private int nClientes = 0;

    // configura GUI
    public Receiver() {
        super( "Receiver" );
        initComponents();
        clientes = new ArrayList<ServerSocket>();
        connections = new ArrayList<Socket>();
        
        // tamanho, resize e visibilidade
        setSize( 350, 255 );
        setResizable(false);
        setVisible( true );
        // localização no centro da tela
        setLocation(
            (Toolkit.getDefaultToolkit().getScreenSize().width-getWidth())/2,
            (Toolkit.getDefaultToolkit().getScreenSize().height-getHeight())/2
        );
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_msnRecebida = new javax.swing.JTextArea();
        jTextField_bitsRecebidos = new javax.swing.JTextField();
        jLabel_bitsRecebidos = new javax.swing.JLabel();
        jLabel_bitsCorrigidos = new javax.swing.JLabel();
        jTextField_bitsCorrigidos = new javax.swing.JTextField();
        jCheckBox_checkSum = new javax.swing.JCheckBox();
        jCheckBox_CRC = new javax.swing.JCheckBox();
        jCheckBox_Hamming = new javax.swing.JCheckBox();
        jLabel_msnRecebida = new javax.swing.JLabel();
        jLabel_modo = new javax.swing.JLabel();
        
        jTextArea_msnRecebida.setColumns(20);
        jTextArea_msnRecebida.setEditable(false);
        jTextArea_msnRecebida.setRows(5);
        jScrollPane1.setViewportView(jTextArea_msnRecebida);

        jTextField_bitsRecebidos.setEditable(false);

        jLabel_bitsRecebidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_bitsRecebidos.setText("Bits Recebidos");

        jLabel_bitsCorrigidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_bitsCorrigidos.setText("Tratamento");

        jTextField_bitsCorrigidos.setEditable(false);
        
        jCheckBox_checkSum.setSelected(true);
        jCheckBox_checkSum.setText("CheckSum");
        jCheckBox_checkSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_checkSumActionPerformed(evt);
            }
        });

        jCheckBox_CRC.setText("CRC");
        jCheckBox_CRC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_CRCActionPerformed(evt);
            }
        });

        jCheckBox_Hamming.setText("Hamming");
        jCheckBox_Hamming.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_HammingActionPerformed(evt);
            }
        });

        jLabel_msnRecebida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_msnRecebida.setText("Mensagem Recebida");

        jLabel_modo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_modo.setText("Modo");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_bitsCorrigidos, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(jLabel_bitsRecebidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_bitsRecebidos)
                            .addComponent(jTextField_bitsCorrigidos, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(jLabel_msnRecebida, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jCheckBox_checkSum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_modo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jCheckBox_Hamming)
                            .addComponent(jCheckBox_CRC))
                        .addGap(12, 12, 12))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_msnRecebida)
                    .addComponent(jLabel_modo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox_checkSum)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox_CRC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jCheckBox_Hamming))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_bitsRecebidos)
                    .addComponent(jTextField_bitsRecebidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_bitsCorrigidos)
                    .addComponent(jTextField_bitsCorrigidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }
    
    private void jCheckBox_checkSumActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        jCheckBox_CRC.setSelected(false);
        jCheckBox_Hamming.setSelected(false);
        jCheckBox_checkSum.setSelected(true);
    }                                                  

    private void jCheckBox_CRCActionPerformed(java.awt.event.ActionEvent evt) {                                              
        jCheckBox_CRC.setSelected(true);
        jCheckBox_Hamming.setSelected(false);
        jCheckBox_checkSum.setSelected(false);
    }                                             

    private void jCheckBox_HammingActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        jCheckBox_CRC.setSelected(false);
        jCheckBox_Hamming.setSelected(true);
        jCheckBox_checkSum.setSelected(false);
    }    
    
    // configura e executa o servidor
    public void runServer() {
        
        // configura o servidor para receber conexões; processa as conexões
        try {
            // Cria o ServerSocket.
            clientes.add(nClientes++, new ServerSocket( 12345, 100 ));
            while ( true ) {
                try {
                    waitForConnection(); // Aguarda conexão.
                    getStreams(); // Obtém input & output streams.
                    processConnection(); // Processa conexão.
                }
                // processa EOFException quando o cliente encerra a conexão
                catch ( EOFException eofException ) {
                    System.err.println( "O Cliente encerrou a conexão!" );
                }
                finally {
                    for(int i=0; i<nClientes; i++)
                        closeConnection(i); // Passo 5: Fecha a conexão.
                    ++counter;
                }
            }
        }
        // processa problemas com I/O
        catch ( IOException ioException ) {
            ioException.printStackTrace();
        }
        
    }
    
    // Aguarda os pedidos de conexão, e depois mostra a informação de conexão
    private void waitForConnection() throws IOException {
        
        jTextArea_msnRecebida.append( "Aguardando por conexão...\n" );
        for(int i=0; i<nClientes; i++) {
            connections.add(i,clientes.get(i).accept()); // permite o servidor aceitar conexões
            jTextArea_msnRecebida.append( "Conexão " + counter + " recebida de : " +
            connections.get(i).getInetAddress().getHostName() );
        }
        
    }
    
    // Obtém streams para enviar e receber dados
    private void getStreams() throws IOException {
        
        // configura output stream
        for(int i=0; i<nClientes; i++) {
            output = new ObjectOutputStream( connections.get(i).getOutputStream() );
            output.flush(); // esvazia buffer de saída

            // configura input stream
            input = new ObjectInputStream( connections.get(i).getInputStream() );
        }
    }
    
    private String getChar(int modo, String str) {
        int ascii = 0;
        String s = "";
        if(modo == 0 || modo == 1) {
            s = str.substring(0,8);
        }
        else {
            s += str.charAt(2);
            s += str.charAt(4);
            s += str.charAt(5);
            s += str.charAt(6);
            s += str.charAt(8);
            s += str.charAt(9);
            s += str.charAt(10);
            s += str.charAt(11);
        }
        for(int i=s.length()-1; i>=0; i--) {
            ascii += (s.charAt(i)-48)*Math.pow(2, 7-i);
        }
        s = " = "+(char)ascii;
        return s;
    }
    
    // processa conexão com o cliente
    private void processConnection() throws IOException {
        
        // envia mensagem de conexão bem-sucedida para o cliente
        String message = "Sender conectado!";
        sendData( message );
        
        do { // processa mensagens enviadas pelo cliente
            // lê as mensagens e as mostra
            try {
                message = ( String ) input.readObject();    
                jTextArea_msnRecebida.append( "\n" + message + " " );
                if(jCheckBox_Hamming.isSelected() == true) jTextArea_msnRecebida.append(getChar(2,message));
                else jTextArea_msnRecebida.append(getChar(0,message));
                // tipo da decodificação
                if(jCheckBox_checkSum.isSelected() == true)
                    jTextField_bitsCorrigidos.setText( "\n" + TratadorErro.tratamentoErros(0,message,jTextArea_msnRecebida) );
                else if(jCheckBox_CRC.isSelected() == true)
                    jTextField_bitsCorrigidos.setText( "\n" + TratadorErro.tratamentoErros(1,message,jTextArea_msnRecebida) );
                else if(jCheckBox_Hamming.isSelected() == true)
                    jTextField_bitsCorrigidos.setText( "\n" + TratadorErro.tratamentoErros(2,message,jTextArea_msnRecebida) );
                // exibe os bits recebidos
                jTextField_bitsRecebidos.setText( "\n" + message );
            }
            // captura problemas com as mensagens enviadas pelo cliente
            catch ( ClassNotFoundException classNotFoundException ) {
                jTextArea_msnRecebida.append( "\nO tipo de objeto é desconhecido!" );
            }
        } while ( !message.equals( "CLIENTE>>> FIM" ) );
        
    }
    
    // Fecha streams e socket
    private void closeConnection(int conexao) {
        
        jTextArea_msnRecebida.append( "\nEncerrando conexão\n" );
        try {
            output.close();
            input.close();
            connections.get(conexao).close();
        }
        catch( IOException ioException ) {
            ioException.printStackTrace();
        }
        
    }
    
    // envia mensagem para o cliente
    private void sendData( String message ) {

        try {
            output.writeObject( "Receiver: " + message );
            output.flush();
            jTextArea_msnRecebida.append( "\nReceiver: " + message );
        }
        catch ( IOException ioException ) {
            jTextArea_msnRecebida.append( "\nErro ao enviar a mensagem!" );
        }
    }
    
    // main
    public static void main( String args[] ) {
        Receiver application = new Receiver();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.runServer();
    }
    
}