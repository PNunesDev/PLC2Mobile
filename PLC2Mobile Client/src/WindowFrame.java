
import HTMLGenerator.DataBlock;
import HTMLGenerator.Util;
import HTMLGenerator.Variable;
import java.awt.Adjustable;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Obscu
 */
public class WindowFrame extends javax.swing.JFrame {
    ArrayList<DataBlock> dataBlockArray;
    DataBlock data;
    JPanel pnlDataBlockQueue;
    JPanel pnlVarible;
    JPanel pnlVarible2;
    JPanel pnlVariavel;
    ActionListener dataBlockListener;;
    ActionListener dataBlockDelListener;
    ActionListener dataBlockEditListener;
    ActionListener variableEditListener;
    ActionListener variableDelListener;
    JFrame frame = this;
    JLabel lblVariable2;
     
     
     ArrayList<JLabel> JlabelArray2;
     
    /**
     * Creates new form WindowFrame
     */
    public WindowFrame() {
        Util utils = new Util();
        initComponents();
        initMyComps();

    }
    
    public void initMyComps(){
        BtnAddVariable.setVisible(false);
        pnlVarible= new JPanel();
        dataBlockArray = new ArrayList();
        pnlDataBlockQueue = new JPanel(new GridLayout(0, 1));
        ScrollPaneDatablockQueue.setViewportView(pnlDataBlockQueue);
        showButtonsDataBlocks(dataBlockArray, true);
        
        pnlVariavel = new JPanel(new GridLayout(0, 1));
        ScrollPaneDatablockQueue.setViewportView(pnlDataBlockQueue);
        
        jLabelDataBlock.setVisible(false);
        
        //Listener para os botoes que representam os data blocks
        dataBlockListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton bt = (JButton) e.getSource();
                String txt = bt.getText();
                int index=0;
                for(int i=0; i<dataBlockArray.size(); i++){
                    if(txt.equals(dataBlockArray.get(i).getName())){
                        index = i;
                        break;
                    }
                    
                }
                DataBlock db = dataBlockArray.get(index);
                String name = db.getName();
                jLabelDataBlock.setText(name);
                jLabelDataBlock.setVisible(true);
                data = db;
                showButtonsVariableBlock(data.getVars());
                //TxtDatablock.setText(name);
                //String newName = JOptionPane.showInputDialog(frame, "Novo nome", null);
                //JOptionPane.showInputDialog(frame, "Novo nome:", "Editar nome DataBlock", 3);
                //TxtDatablock.setText(newName);
                
            }
        };
        
        dataBlockDelListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton bt = (JButton) e.getSource();
                JPanel pnl = (JPanel) bt.getParent();
                bt = (JButton) pnl.getComponent(0);
                String txt = bt.getText();
                //int index=0;
                for(int i=0; i<dataBlockArray.size(); i++){
                    if(txt.equals(dataBlockArray.get(i).getName())){
                        dataBlockArray.remove(i);
                        showButtonsDataBlocks(dataBlockArray, false);
                        if(txt.equals(data.getName())){
                            data = dataBlockArray.get(0);
                            showButtonsVariableBlock(data.getVars());
                        }
                        
                        break;
                    }
                    
                }
//                DataBlock db = dataBlockArray.get(index);
//                String name = db.getName();
//                TxtDatablock.setText(name);
            }
        };
        
        //Açao do botao de editar
        dataBlockEditListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton bt = (JButton) e.getSource();
                JPanel pnl = (JPanel) bt.getParent();
                bt = (JButton) pnl.getComponent(0);
                String oldTxt = bt.getText();
                Integer index = null;
                String txt = JOptionPane.showInputDialog(frame, "Novo nome:", "Editar nome", 3);
                Integer dbIndex=null;
                for(int i=0; i<dataBlockArray.size(); i++){
                    if(txt.equals(dataBlockArray.get(i).getName())){
                        index = i;
                        break;
                    }
                    else if(oldTxt.equals(dataBlockArray.get(i).getName())){
                        dbIndex = i;
                    }
                    
                }
                if(!(index==null)){
                JOptionPane.showMessageDialog(frame,
                "Já existe um DataBlock com esse nome!",
                "Conflito",
                JOptionPane.WARNING_MESSAGE);
                return;
            }
                
                
                bt.setText(txt);
                dataBlockArray.get(dbIndex).setName(txt);
//                DataBlock db = dataBlockArray.get(index);
//                String name = db.getName();
//                TxtDatablock.setText(name);
            }
        };
        
         variableDelListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Variable> vars = data.getVars();
                JButton bt = (JButton) e.getSource();
                JPanel pnl = (JPanel) bt.getParent();
                JLabel var = (JLabel) pnl.getComponent(1);
                String txt = var.getText();
                //int index=0;
                for(int i=0; i<vars.size(); i++){
                    if(txt.equals(vars.get(i).getname())){
                        data.delvar(i);
                        showButtonsVariableBlock(data.getVars());
                        break;
                    }
                    
                }
//                DataBlock db = dataBlockArray.get(index);
//                String name = db.getName();
//                TxtDatablock.setText(name);
            }
        };
        
        //Açao do botao de editar
        variableEditListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Variable> vars = data.getVars();
                JButton bt = (JButton) e.getSource();
                JPanel pnl = (JPanel) bt.getParent();
                JLabel var = (JLabel) pnl.getComponent(1);
                String oldTxt = var.getText();
                Integer index = null;
                String txt = JOptionPane.showInputDialog(frame, "Novo nome:", "Editar nome", 3);
                Integer varIndex=null;
                for(int i=0; i<vars.size(); i++){
                    if(txt.equals(vars.get(i).getname())){
                        index = i;
                        break;
                    }
                    else if(oldTxt.equals(vars.get(i).getname())){
                        varIndex = i;
                    }
                    
                }
                if(!(index==null)){
                JOptionPane.showMessageDialog(frame,
                "Já existe um DataBlock com esse nome!",
                "Conflito",
                JOptionPane.WARNING_MESSAGE);
                return;
            }
                
                
                var.setText(txt);
                vars.get(varIndex).setName(txt);
//                DataBlock db = dataBlockArray.get(index);
//                String name = db.getName();
//                TxtDatablock.setText(name);
            }
        };
        
    }
    //Faz scroll para baixo de um scrollpane
    private void scrollToBottom(JScrollPane scrollPane) {
    JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
    AdjustmentListener downScroller = new AdjustmentListener() {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            Adjustable adjustable = e.getAdjustable();
            adjustable.setValue(adjustable.getMaximum());
            verticalBar.removeAdjustmentListener(this);
        }
    };
    verticalBar.addAdjustmentListener(downScroller);
}
    //Cria e mostra os botoes de acordo com o arraylist dos datablocks
    private void showButtonsDataBlocks(ArrayList<DataBlock> array, boolean scroll){
        pnlDataBlockQueue.removeAll();
        int lenght = array.size();
        DataBlock db;
        for(int i=0; i<lenght; i++){
            db = array.get(i);
            JButton bt = new JButton(db.getName());
            bt.addActionListener(dataBlockListener);
            bt.setPreferredSize(new Dimension(150, 35));
            JButton btClose = new JButton();
            
            try {
                Image img = ImageIO.read(getClass().getResource("resources/Close_Box_Red.png"));
                btClose.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
            }
            
            btClose.setPreferredSize(new Dimension(35, 35));
            btClose.addActionListener(dataBlockDelListener);
            
            //Botao de editar
            JButton btEdit = new JButton();
            
            try {
                Image img = ImageIO.read(getClass().getResource("resources/Edit-Icon.png"));
                btEdit.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
            }
            
            btEdit.setPreferredSize(new Dimension(35, 35));
            btEdit.addActionListener(dataBlockEditListener);
            
            JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
            pnl.add(bt);
            pnl.add(btEdit);
            pnl.add(btClose);
            
            pnlDataBlockQueue.add(pnl);
            //pnlDataBlockQueue.add(btClose);
            
            ScrollPaneDatablockQueue.setViewportView(pnlDataBlockQueue);
            if(scroll){
                scrollToBottom(ScrollPaneDatablockQueue);
            }
            
        }
        ScrollPaneDatablockQueue.setViewportView(pnlDataBlockQueue);
    }
    
     private void showButtonsVariableBlock(ArrayList<Variable> array){
        pnlVariavel.removeAll();
        int lenght = array.size();
        Variable var;
        for(int i=0; i<lenght; i++){
            var = array.get(i);
            JLabel LblVariable = new JLabel("Variavel "+(i+1)+":");
            JLabel bt = new JLabel(var.getname());
            bt.setPreferredSize(new Dimension(260, 35));
            JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
            
            JButton btClose = new JButton();
            
            try {
                Image img = ImageIO.read(getClass().getResource("resources/Close_Box_Red.png"));
                btClose.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
            }
            
            btClose.setPreferredSize(new Dimension(25, 25));
            btClose.addActionListener(variableDelListener);
            
            //Botao de editar
            JButton btEdit = new JButton();
            
            try {
                Image img = ImageIO.read(getClass().getResource("resources/Edit-Icon.png"));
                btEdit.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
            }
            
            btEdit.setPreferredSize(new Dimension(25, 25));
            btEdit.addActionListener(variableEditListener);
            
            
            pnl.add(LblVariable);
            pnl.add(bt);
            pnl.add(btEdit);
            pnl.add(btClose);
            
        
            pnlVariavel.add(pnl);
            
        }
        ScrollPaneVariable.setViewportView(pnlVariavel);
        scrollToBottom(ScrollPaneVariable);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollPaneDatablockQueue = new javax.swing.JScrollPane();
        PanelAddVariable = new javax.swing.JPanel();
        LblVariable = new javax.swing.JLabel();
        jLabelDataBlock = new javax.swing.JLabel();
        ScrollPaneVariable = new javax.swing.JScrollPane();
        AddToQueue = new javax.swing.JButton();
        BtnAddVariable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PLC2Mobile Client");

        ScrollPaneDatablockQueue.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(51, 51, 255), new java.awt.Color(255, 0, 0), null, null));
        ScrollPaneDatablockQueue.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneDatablockQueue.setToolTipText("");
        ScrollPaneDatablockQueue.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        PanelAddVariable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 0, 255), new java.awt.Color(255, 0, 51), null, null));

        LblVariable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LblVariable.setText("Data Block:");

        jLabelDataBlock.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelDataBlock.setText("jLabel1");

        javax.swing.GroupLayout PanelAddVariableLayout = new javax.swing.GroupLayout(PanelAddVariable);
        PanelAddVariable.setLayout(PanelAddVariableLayout);
        PanelAddVariableLayout.setHorizontalGroup(
            PanelAddVariableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAddVariableLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(LblVariable)
                .addGap(18, 18, 18)
                .addComponent(jLabelDataBlock)
                .addContainerGap(192, Short.MAX_VALUE))
        );
        PanelAddVariableLayout.setVerticalGroup(
            PanelAddVariableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAddVariableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelAddVariableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblVariable)
                    .addComponent(jLabelDataBlock))
                .addContainerGap())
        );

        ScrollPaneVariable.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        AddToQueue.setText("New Data Block");
        AddToQueue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToQueueActionPerformed(evt);
            }
        });

        BtnAddVariable.setText("New Variable");
        BtnAddVariable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddVariableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ScrollPaneDatablockQueue)
                    .addComponent(AddToQueue, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelAddVariable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScrollPaneVariable)
                    .addComponent(BtnAddVariable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelAddVariable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrollPaneVariable))
                    .addComponent(ScrollPaneDatablockQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddToQueue, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(BtnAddVariable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //Adicionar Data Block ao array
    private void AddToQueueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToQueueActionPerformed
            
            //String btTxt = TxtDatablock.getText();
            String dbTxt = JOptionPane.showInputDialog(rootPane, "Novo nome:", "Editar nome DataBlock", 3);
            if(dbTxt.equals("")){
                JOptionPane.showMessageDialog(this,
                "Atribuir um nome ao DataBlock!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            //TxtDatablock.setText(null);
            //TxtDatablock.grabFocus();
            
            Integer index=null;
            for(int i=0; i<dataBlockArray.size(); i++){
                    if(dbTxt.equals(dataBlockArray.get(i).getName())){
                        index = i;
                        break;
                    }
                    
                }
            if(!(index==null)){
                JOptionPane.showMessageDialog(this,
                "Já existe um DataBlock com esse nome!",
                "Conflito",
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            DataBlock db = new DataBlock(dbTxt);
            jLabelDataBlock.setText(dbTxt);
            jLabelDataBlock.setVisible(true);
            BtnAddVariable.setVisible(true);
            //Por as variaveis para o data block
            //
            
            
            dataBlockArray.add(db);
            showButtonsDataBlocks(dataBlockArray, true);
            data = db;
            
    }//GEN-LAST:event_AddToQueueActionPerformed

    private void BtnAddVariableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddVariableActionPerformed

          //String btTxt = TxtDatablock.getText();
        
        ArrayList<Variable> vars = data.getVars();
            String varTxt = JOptionPane.showInputDialog(rootPane, "Novo nome:", "Editar nome DataBlock", 3);
            if(varTxt.equals("")){
                JOptionPane.showMessageDialog(this,
                "Atribuir um nome ao DataBlock!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            Integer index=null;
            for(int i=0; i<vars.size(); i++){
                    if(varTxt.equals(vars.get(i).getname())){
                        index = i;
                        break;
                    }
                }
            if(!(index==null)){
                JOptionPane.showMessageDialog(this,
                "Já existe um DataBlock com esse nome!",
                "Conflito",
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            Variable var = new Variable(varTxt);
            data.addVar(var);
            //JLabel lblVariable3 = new JLabel(varTxt);
//            dataBlockArray.get(dbindex).addVar(var);
            showButtonsVariableBlock(data.getVars());
    }//GEN-LAST:event_BtnAddVariableActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WindowFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddToQueue;
    private javax.swing.JButton BtnAddVariable;
    private javax.swing.JLabel LblVariable;
    private javax.swing.JPanel PanelAddVariable;
    private javax.swing.JScrollPane ScrollPaneDatablockQueue;
    private javax.swing.JScrollPane ScrollPaneVariable;
    private javax.swing.JLabel jLabelDataBlock;
    // End of variables declaration//GEN-END:variables
}
