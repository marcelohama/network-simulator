package EnvioBits;

import java.awt.Toolkit;

public class Sender extends javax.swing.JFrame {

    private BitSender s;
    
    public Sender() {
        initComponents();
        setLocation(
            (Toolkit.getDefaultToolkit().getScreenSize().width-getWidth())/2,
            (Toolkit.getDefaultToolkit().getScreenSize().height-getHeight())/2
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox_checkSum = new javax.swing.JCheckBox();
        jCheckBox_CRC = new javax.swing.JCheckBox();
        jCheckBox_Hamming = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_codigoEnviado = new javax.swing.JTextPane();
        jLabel_codigoEnvido = new javax.swing.JLabel();
        jToggleButton_enviar = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane_mensagemEnviar = new javax.swing.JEditorPane();
        jLabel_mensagemEnviar = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane_IP = new javax.swing.JTextPane();
        jLabel_IP = new javax.swing.JLabel();
        jCheckBox_erro = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sender");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("Sender"); // NOI18N
        setResizable(false);

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

        jTextPane_codigoEnviado.setEditable(false);
        jScrollPane1.setViewportView(jTextPane_codigoEnviado);

        jLabel_codigoEnvido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_codigoEnvido.setText("Bits Enviado");

        jToggleButton_enviar.setText("Conectar e Enviar");
        jToggleButton_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_enviarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jEditorPane_mensagemEnviar);

        jLabel_mensagemEnviar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_mensagemEnviar.setText("Mensagem a enviar");

        jTextPane_IP.setText("127.0.0.1");
        jScrollPane3.setViewportView(jTextPane_IP);

        jLabel_IP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_IP.setText("IP:");

        jCheckBox_erro.setText("Gerar erro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_checkSum)
                            .addComponent(jCheckBox_CRC)
                            .addComponent(jCheckBox_Hamming))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel_codigoEnvido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                    .addComponent(jToggleButton_enviar, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(jLabel_mensagemEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox_erro, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_checkSum)
                    .addComponent(jLabel_codigoEnvido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox_CRC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox_Hamming))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton_enviar)
                .addGap(26, 26, 26)
                .addComponent(jLabel_mensagemEnviar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jCheckBox_erro, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabel_IP, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jCheckBox_checkSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_checkSumActionPerformed
    jCheckBox_CRC.setSelected(false);
    jCheckBox_Hamming.setSelected(false);
    jCheckBox_checkSum.setSelected(true);
}//GEN-LAST:event_jCheckBox_checkSumActionPerformed

private void jCheckBox_CRCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_CRCActionPerformed
    jCheckBox_CRC.setSelected(true);
    jCheckBox_Hamming.setSelected(false);
    jCheckBox_checkSum.setSelected(false);
}//GEN-LAST:event_jCheckBox_CRCActionPerformed

private void jCheckBox_HammingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_HammingActionPerformed
    jCheckBox_CRC.setSelected(false);
    jCheckBox_Hamming.setSelected(true);
    jCheckBox_checkSum.setSelected(false);
}//GEN-LAST:event_jCheckBox_HammingActionPerformed

private void jToggleButton_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_enviarActionPerformed
    if(jToggleButton_enviar.isSelected()) {
        jCheckBox_CRC.setEnabled(false);
        jCheckBox_Hamming.setEnabled(false);
        jCheckBox_checkSum.setEnabled(false);
        jEditorPane_mensagemEnviar.setEditable(false);
        jToggleButton_enviar.setEnabled(false);
        int modo = 0;
        if(jCheckBox_checkSum.isSelected()) modo = 0;
        if(jCheckBox_CRC.isSelected()) modo = 1;
        if(jCheckBox_Hamming.isSelected()) modo = 2;
        s = new BitSender(
            jEditorPane_mensagemEnviar,
            jTextPane_codigoEnviado,
            jTextPane_IP.getText(),
            jToggleButton_enviar,
            modo,
            jCheckBox_checkSum,
            jCheckBox_CRC,
            jCheckBox_Hamming,
            jCheckBox_erro
        );
        s.start();
    }
    else {
        jCheckBox_CRC.setEnabled(true);
        jCheckBox_Hamming.setEnabled(true);
        jCheckBox_checkSum.setEnabled(true);
        jEditorPane_mensagemEnviar.setEditable(true);
    }
}//GEN-LAST:event_jToggleButton_enviarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sender().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox_CRC;
    private javax.swing.JCheckBox jCheckBox_Hamming;
    private javax.swing.JCheckBox jCheckBox_checkSum;
    private javax.swing.JCheckBox jCheckBox_erro;
    private javax.swing.JEditorPane jEditorPane_mensagemEnviar;
    private javax.swing.JLabel jLabel_IP;
    private javax.swing.JLabel jLabel_codigoEnvido;
    private javax.swing.JLabel jLabel_mensagemEnviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane_IP;
    private javax.swing.JTextPane jTextPane_codigoEnviado;
    private javax.swing.JToggleButton jToggleButton_enviar;
    // End of variables declaration//GEN-END:variables

}
