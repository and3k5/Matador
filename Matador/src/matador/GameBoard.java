/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matador;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author and3k5
 */
public class GameBoard extends javax.swing.JFrame {

    /**
     * Creates new form GameBoard
     */
    public GameControl gamecontrol;
    public GameBoard() {
        initComponents();
        setVisible(true);
        gamecontrol=new GameControl(this);
        gamecontrol.setVisible(true);
        int width=this.getWidth()+gamecontrol.getWidth();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width/2)-(width/2), (screenSize.height/2)-(this.getHeight()/2));
        updatePosition();
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                mapBoard1.updateUI();
                updatePosition();
            }
            
        },100,10);
        JButton throwDiceBtn = new JButton();
        throwDiceBtn.setText("Kast terningerne");
        throwDiceBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.GA_ThrowDice(); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        JButton mortgageBtn = new JButton();
        mortgageBtn.setText("Pantsæt");
        mortgageBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        choices.add(throwDiceBtn);
        choices.add(mortgageBtn);
    }
    public void closing(JFrame frame) {
        if (JOptionPane.showConfirmDialog(null, "Dette vil afslutte Matador. Er du sikker på at du vil lukke?", "Er du sikker?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) {
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }else{
            frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }
    ArrayList<JButton> choices = new ArrayList<>();
    public void initGame() {
        System.out.println("initGame");
        Game.currentPlayer=0;
        getPlayOptions();
    }
    public void getPlayOptions() {
        System.out.println("getPlayOptions");
        Boolean mortgageOption = false;
        int player=Game.currentPlayer;
        for (Field field : Game.fields) {
            if (field.getClass()==Brewery.class) {
                Brewery brew = ((Brewery)field);
                if (brew.Owner==player) {
                    mortgageOption=true;
                }
            }else if (field.getClass()==Street.class) {
                Street street = ((Street)field);
                if (street.Owner==player) {
                    mortgageOption=true;
                }
            }else if (field.getClass()==ShippingLines.class) {
                ShippingLines sl = ((ShippingLines)field);
                if (sl.Owner==player) {
                    mortgageOption=true;
                }
            }
        }
        
        Boolean throwDiceOption=!Game.players.get(player).InPrison;
        
        
        
        
        // mortgageOption
        gamecontrol.optionPanel.removeAll();
        int y=0;
        if (throwDiceOption) {
            JButton copy = choices.get(0);
            copy.setSize(gamecontrol.optionPanel.getWidth(),50);
            copy.setLocation(0,y+=copy.getHeight());
            gamecontrol.optionPanel.add(copy);
            System.out.println("May throw dice");
        }
        if (mortgageOption) {
            JButton copy = choices.get(1);
            copy.setSize(gamecontrol.optionPanel.getWidth(),50);
            copy.setLocation(0,y+=copy.getHeight());
            gamecontrol.optionPanel.add(copy);
            System.out.println("May mortgage");
        }
        gamecontrol.optionPanel.updateUI();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mapBoard1 = new matador.MapBoard();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Matador");
        setMaximumSize(new java.awt.Dimension(2000, 2000));
        setMinimumSize(new java.awt.Dimension(300, 300));
        setPreferredSize(new java.awt.Dimension(600, 600));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                formAncestorResized(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        mapBoard1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        mapBoard1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
         int W = 4;  
         int H = 4;  
         Rectangle b = evt.getComponent().getBounds();
         evt.getComponent().setSize(b.width, b.width*H/W);
    }//GEN-LAST:event_formComponentResized

    private void formAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorResized
        // TODO add your handling code here:
        int W = 4;  
         int H = 4;  
         Rectangle b = evt.getComponent().getBounds();
         evt.getComponent().setBounds(b.x, b.y, b.width, b.width*H/W);
    }//GEN-LAST:event_formAncestorResized
    public void updatePosition() {
        gamecontrol.setLocation(this.getX()+this.getWidth()+8, this.getY());
        
    }
    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
        // TODO add your handling code here:
        updatePosition();
    }//GEN-LAST:event_formComponentMoved

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        closing(this);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        updatePosition();
        gamecontrol.toFront();
        gamecontrol.setFocusableWindowState(false);
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
        switch (this.getState()) {
            case 0:
                gamecontrol.setState(NORMAL);
                break;
            case 1:
                gamecontrol.setState(ICONIFIED);
                break;
            default:
                break;
        }
        
    }//GEN-LAST:event_formWindowStateChanged

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
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private matador.MapBoard mapBoard1;
    // End of variables declaration//GEN-END:variables
}
