/*
 * Created by JFormDesigner on Thu Oct 29 02:16:05 UYT 2020
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * @author unknown
 */
public class MainMenu extends JPanel {
    public MainMenu() {
        initComponents();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel mainMenu;
    private JPanel menuSelector;
    private JLabel label2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        mainMenu = new JPanel();
        menuSelector = new JPanel();
        label2 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
        0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
        . BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
        red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
        beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(new CardLayout());

        //======== mainMenu ========
        {
            mainMenu.setBackground(Color.white);
            mainMenu.setBorder(null);
            mainMenu.setForeground(new Color(81, 104, 223));

            //======== menuSelector ========
            {
                menuSelector.setBackground(new Color(81, 104, 223));
                menuSelector.setForeground(new Color(81, 104, 223));

                //---- label2 ----
                label2.setText("Main menu");
                label2.setFont(new Font("Dubai", Font.PLAIN, 24));
                label2.setForeground(Color.white);

                //---- button1 ----
                button1.setText("usuario");
                button1.setBackground(new Color(81, 104, 223));
                button1.setForeground(Color.white);
                button1.setBorder(null);
                button1.setFont(new Font("Segoe UI", Font.PLAIN, 16));

                //---- button2 ----
                button2.setText("menus");
                button2.setBackground(new Color(81, 104, 223));
                button2.setForeground(Color.white);
                button2.setBorder(null);
                button2.setFont(new Font("Segoe UI", Font.PLAIN, 16));

                //---- button3 ----
                button3.setText("personas");
                button3.setBackground(new Color(81, 104, 223));
                button3.setForeground(Color.white);
                button3.setBorder(null);
                button3.setFont(new Font("Segoe UI", Font.PLAIN, 16));

                //---- button4 ----
                button4.setText("roles");
                button4.setBackground(new Color(81, 104, 223));
                button4.setForeground(Color.white);
                button4.setBorder(null);
                button4.setFont(new Font("Segoe UI", Font.PLAIN, 16));

                GroupLayout menuSelectorLayout = new GroupLayout(menuSelector);
                menuSelector.setLayout(menuSelectorLayout);
                menuSelectorLayout.setHorizontalGroup(
                    menuSelectorLayout.createParallelGroup()
                        .addGroup(menuSelectorLayout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(label2)
                            .addGap(30, 30, 30)
                            .addComponent(button1)
                            .addGap(18, 18, 18)
                            .addComponent(button3)
                            .addGap(18, 18, 18)
                            .addComponent(button2)
                            .addGap(18, 18, 18)
                            .addComponent(button4)
                            .addContainerGap(66, Short.MAX_VALUE))
                );
                menuSelectorLayout.setVerticalGroup(
                    menuSelectorLayout.createParallelGroup()
                        .addGroup(menuSelectorLayout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(menuSelectorLayout.createParallelGroup()
                                .addComponent(label2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(menuSelectorLayout.createSequentialGroup()
                                    .addGroup(menuSelectorLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button4, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addContainerGap())
                );
            }

            GroupLayout mainMenuLayout = new GroupLayout(mainMenu);
            mainMenu.setLayout(mainMenuLayout);
            mainMenuLayout.setHorizontalGroup(
                mainMenuLayout.createParallelGroup()
                    .addComponent(menuSelector, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            mainMenuLayout.setVerticalGroup(
                mainMenuLayout.createParallelGroup()
                    .addGroup(mainMenuLayout.createSequentialGroup()
                        .addComponent(menuSelector, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(346, Short.MAX_VALUE))
            );
        }
        add(mainMenu, "card1");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
