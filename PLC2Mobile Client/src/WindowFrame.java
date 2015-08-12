
import HTMLGenerator.DataBlock;
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
import javax.swing.JLabel;
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
    ArrayList<Variable> variableBlockArray;
    JPanel pnlDataBlockQueue;
    JPanel pnlVarible;
    JPanel pnlVarible2;
    ActionListener dataBlockListener;;
    ActionListener dataBlockDelListener;
     
     
     ArrayList<JLabel> JlabelArray2;
     
    /**
     * Creates new form WindowFrame
     */
    public WindowFrame() {
        
        initComponents();
        initMyComps();

    }
    
    public void initMyComps(){
        
        dataBlockArray = new ArrayList();
        variableBlockArray = new ArrayList();
        pnlDataBlockQueue = new JPanel(new GridLayout(0, 1));
        ScrollPaneDatablockQueue.setViewportView(pnlDataBlockQueue);
        showButtonsDataBlocks(dataBlockArray);
        
        pnlVarible = new JPanel(new GridLayout(0, 1));
        ScrollPaneDatablockQueue.setViewportView(pnlDataBlockQueue);
        
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
                TxtDatablock.setText(name);
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
                        showButtonsDataBlocks(dataBlockArray);
                        break;
                    }
                    
                }
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
    private void showButtonsDataBlocks(ArrayList<DataBlock> array){
        pnlDataBlockQueue.removeAll();
        int lenght = array.size();
        DataBlock db;
        for(int i=0; i<lenght; i++){
            db = array.get(i);
            JButton bt = new JButton(db.getName());
            bt.addActionListener(dataBlockListener);
            bt.setPreferredSize(new Dimension(90, 35));
            JButton btClose = new JButton();
            
            try {
                Image img = ImageIO.read(getClass().getResource("resources/Close_Box_Red.png"));
                btClose.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
            }
            
            btClose.setPreferredSize(new Dimension(35, 35));
            btClose.addActionListener(dataBlockDelListener);
            JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
            pnl.add(bt);
            pnl.add(btClose);
            
            pnlDataBlockQueue.add(pnl);
            //pnlDataBlockQueue.add(btClose);
            
            ScrollPaneDatablockQueue.setViewportView(pnlDataBlockQueue);
            scrollToBottom(ScrollPaneDatablockQueue);
        }
        ScrollPaneDatablockQueue.setViewportView(pnlDataBlockQueue);
    }
    
     private void showButtonsVariableBlock(ArrayList<Variable> array){
        pnlVarible.removeAll();
        int lenght = array.size();
        Variable db;
        for(int i=0; i<lenght; i++){
            db = array.get(i);
            JLabel LblVariable = new JLabel("Variavel "+(i+1)+":");
            JTextField bt = new JTextField(db.getname());
            bt.setPreferredSize(new Dimension(100, 35));
            JPanel lhurz = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
            lhurz.add(LblVariable);
            lhurz.add(bt);
            pnlVarible.add(lhurz);
            
            ScrollPaneVariable.setViewportView(pnlVarible);
            scrollToBottom(ScrollPaneVariable);
        }
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
        PanelDatablock = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TxtDatablock = new javax.swing.JTextField();
        AddToQueue = new javax.swing.JButton();
        PanelAddVariable = new javax.swing.JPanel();
        LblVariable = new javax.swing.JLabel();
        TxtVariable = new javax.swing.JTextField();
        BtnAddVariable = new javax.swing.JButton();
        ScrollPaneVariable = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PLC2Mobile Client");

        ScrollPaneDatablockQueue.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(51, 51, 255), new java.awt.Color(255, 0, 0), null, null));
        ScrollPaneDatablockQueue.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneDatablockQueue.setToolTipText("");

        PanelDatablock.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 0, 255), new java.awt.Color(255, 0, 51), null, null));

        jLabel2.setText("Datablock Name:");

        TxtDatablock.setText("Hometest");

        AddToQueue.setText("Add to Queue");
        AddToQueue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToQueueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDatablockLayout = new javax.swing.GroupLayout(PanelDatablock);
        PanelDatablock.setLayout(PanelDatablockLayout);
        PanelDatablockLayout.setHorizontalGroup(
            PanelDatablockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatablockLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtDatablock, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddToQueue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelDatablockLayout.setVerticalGroup(
            PanelDatablockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatablockLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelDatablockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtDatablock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddToQueue))
                .addContainerGap())
        );

        PanelAddVariable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 0, 255), new java.awt.Color(255, 0, 51), null, null));

        LblVariable.setText("Variable Name:");

        BtnAddVariable.setText("Add to Queue");
        BtnAddVariable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddVariableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAddVariableLayout = new javax.swing.GroupLayout(PanelAddVariable);
        PanelAddVariable.setLayout(PanelAddVariableLayout);
        PanelAddVariableLayout.setHorizontalGroup(
            PanelAddVariableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAddVariableLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(LblVariable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnAddVariable)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelAddVariableLayout.setVerticalGroup(
            PanelAddVariableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAddVariableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelAddVariableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblVariable)
                    .addComponent(TxtVariable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAddVariable))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPaneDatablockQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelDatablock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelAddVariable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ScrollPaneVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPaneDatablockQueue, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelDatablock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelAddVariable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrollPaneVariable)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddToQueueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToQueueActionPerformed
            
            String btTxt = TxtDatablock.getText();
            TxtDatablock.setText(null);
            TxtDatablock.grabFocus();
            
            Integer index=null;
            for(int i=0; i<dataBlockArray.size(); i++){
                    if(btTxt.equals(dataBlockArray.get(i).getName())){
                        index = i;
                        break;
                    }
                    
                }
            if(!(index==null)){
                System.out.println("ERRO!!");
                return;
            }
            
            DataBlock db = new DataBlock(btTxt);
            //Por as variaveis para o data block
            //
            
            
            dataBlockArray.add(db);
            showButtonsDataBlocks(dataBlockArray);
            
    }//GEN-LAST:event_AddToQueueActionPerformed

    private void BtnAddVariableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddVariableActionPerformed
            String btTxt = TxtVariable.getText();
            TxtVariable.setText(null);
            TxtVariable.grabFocus();
            
            Integer index=null;
            for(int i=0; i<variableBlockArray.size(); i++){
                    if(btTxt.equals(variableBlockArray.get(i).getname())){
                        index = i;
                        break;
                    }
                    
                }
            if(!(index==null)){
                System.out.println("ERRO!!");
                return;
            }
            
            Variable var= new Variable(btTxt);
            variableBlockArray.add(var);
            System.out.println(var.getname());
            showButtonsVariableBlock(variableBlockArray);
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
    private javax.swing.JPanel PanelDatablock;
    private javax.swing.JScrollPane ScrollPaneDatablockQueue;
    private javax.swing.JScrollPane ScrollPaneVariable;
    private javax.swing.JTextField TxtDatablock;
    private javax.swing.JTextField TxtVariable;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
