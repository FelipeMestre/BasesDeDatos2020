/*
 * Created by JFormDesigner on Sun Nov 01 19:25:33 UYT 2020
 */

package ui.creation;

import org.ucu.bd.ModelConstructor;
import ui.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author unknown
 */
public class CreatePersonForm extends JFrame {

    private ModelConstructor constructor;
    private MainMenu parent;


    public CreatePersonForm(ModelConstructor controller, MainMenu parent) {
        this.constructor = controller;
        this.parent = parent;
        initComponents();;
    }

    private void SubmitActionPerformed() {
        String name = Nombre.getText();
        String adres = Direccion.getText();

        int document = 0;
        int phone = 0;
        try{
            document = Integer.parseInt(Ci.getText()); //Hay que cambiar para que me tire un int
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Ingrese Documento sin puntos");
            return;
        }
        try {
            phone = Integer.parseInt(Telefono.getText());
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Ingrese un numero \nde  Telefono correcto");
            return;
        }

        if (name.length() > 50){
            JOptionPane.showMessageDialog(this,"El largo maximo de nombre \nes 50 caracteres");
            return;
        }

        if (adres.length() > 100){
            JOptionPane.showMessageDialog(this,"El largo maximo de direccion \nes 100 caracteres");
            return;
        }
        if (constructor.createPersona(document,name,adres,phone)){
            parent.setEnabled(true);
            parent.fetchPersons();
            this.dispose();
        }
    }

    private void CancelActionPerformed(ActionEvent e) {
        this.dispose();
        parent.enable();
        parent.requestFocus();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        Title = new JLabel();
        NameTitle = new JLabel();
        Ci_label = new JLabel();
        Nombre = new JTextField();
        Submit = new JButton();
        Cancel = new JButton();
        label1 = new JLabel();
        Ci = new JTextField();
        direccion_label = new JLabel();
        Direccion = new JTextField();
        Telefono_label = new JLabel();
        Telefono = new JTextField();

        //======== this ========
        setUndecorated(true);
        setBackground(Color.white);
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(244, 244, 244));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
            swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border
            .TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dialo\u0067"
            ,java.awt.Font.BOLD,12),java.awt.Color.red),panel1. getBorder
            ()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java
            .beans.PropertyChangeEvent e){if("borde\u0072".equals(e.getPropertyName()))throw new RuntimeException
            ();}});

            //---- Title ----
            Title.setText("Crear Una Persona");
            Title.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
            Title.setForeground(new Color(112, 112, 112));

            //---- NameTitle ----
            NameTitle.setText("Nombre y Apellido");
            NameTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- Ci_label ----
            Ci_label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- Nombre ----
            Nombre.setBackground(Color.white);
            Nombre.setForeground(Color.darkGray);
            Nombre.setBorder(null);

            //---- Submit ----
            Submit.setText("Crear");
            Submit.setBorder(null);
            Submit.setBackground(new Color(42, 58, 64));
            Submit.setForeground(Color.white);
            Submit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            Submit.setBorderPainted(false);
            Submit.addActionListener(e -> SubmitActionPerformed());

            //---- Cancel ----
            Cancel.setText("Cancelar");
            Cancel.setBorder(null);
            Cancel.setBackground(new Color(181, 181, 181));
            Cancel.setForeground(Color.white);
            Cancel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            Cancel.setBorderPainted(false);
            Cancel.addActionListener(e -> CancelActionPerformed(e));

            //---- label1 ----
            label1.setText("Documento (solo n\u00fameros)");
            label1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- Ci ----
            Ci.setBackground(Color.white);

            //---- direccion_label ----
            direccion_label.setText("Direccion");
            direccion_label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- Direccion ----
            Direccion.setBackground(Color.white);

            //---- Telefono_label ----
            Telefono_label.setText("Telefono");
            Telefono_label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- Telefono ----
            Telefono.setBackground(Color.white);

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(Title)
                                    .addComponent(Direccion, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(direccion_label, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Ci, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(Nombre, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NameTitle))
                                .addGap(51, 51, 51)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(Telefono, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Ci_label, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Telefono_label)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Submit, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(Title)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(Ci_label)
                                .addGap(40, 172, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Submit))
                                .addGap(20, 20, 20))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(NameTitle)
                                    .addComponent(Telefono_label))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(Nombre, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Telefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Ci, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(direccion_label, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 50, Short.MAX_VALUE))))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JLabel Title;
    private JLabel NameTitle;
    private JLabel Ci_label;
    private JTextField Nombre;
    private JButton Submit;
    private JButton Cancel;
    private JLabel label1;
    private JTextField Ci;
    private JLabel direccion_label;
    private JTextField Direccion;
    private JLabel Telefono_label;
    private JTextField Telefono;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
