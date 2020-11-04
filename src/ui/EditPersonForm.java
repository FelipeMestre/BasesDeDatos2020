/*
 * Created by JFormDesigner on Tue Nov 03 14:04:54 UYT 2020
 */

package ui;

import org.ucu.bd.ModelConstructor;
import ui.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author unknown
 */
public class EditPersonForm extends JFrame {

    private ModelConstructor constructor;
    private MainMenu parent;
    private String person_name;
    private String telefono;
    private String adress;
    private String ci;

    public EditPersonForm(MainMenu parent, ModelConstructor controller,
                          String person_name, String telefono, String adress, String ci) {
        this.constructor = controller;
        this.parent = parent;
        this.telefono = telefono;
        this.adress = adress;
        this.ci = ci;
        initComponents();
        this.Nombre.setText(person_name);
        this.Direccion.setText(adress);
        this.Telefono.setText(telefono);
        this.Documento.setText(ci);
    }

    private void SubmitActionPerformed() {
        String newName = Nombre.getText();
        String newPhone = Telefono.getText();
        String newAdres = Direccion.getText();
        String newCi = Documento.getText();
        if (!newName.equals("") && !newPhone.equals("") && !newAdres.equals("") && !newCi.equals("")){
            constructor.updatePerson(newCi,newName, newAdres,newPhone);
            parent.fetchPersons();
            exitForm();
        }
    }

    private void CancelActionPerformed(ActionEvent e) {
        exitForm();
    }

    private void exitForm(){
        parent.enable();
        parent.requestFocus();
        this.dispose();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        NameTitle = new JLabel();
        Telefono_Label = new JLabel();
        Nombre = new JTextField();
        Telefono = new JTextField();
        Documento_label = new JLabel();
        Documento = new JTextField();
        Direccion_label = new JLabel();
        Direccion = new JTextField();
        panel2 = new JPanel();
        Title = new JLabel();
        panel3 = new JPanel();
        Cancel = new JButton();
        Submit = new JButton();

        //======== this ========
        setResizable(false);
        setUndecorated(true);
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(244, 244, 244));
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );

            //---- NameTitle ----
            NameTitle.setText("Nombre del rol");
            NameTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- Telefono_Label ----
            Telefono_Label.setText("Telefono");
            Telefono_Label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- Nombre ----
            Nombre.setBackground(Color.white);
            Nombre.setForeground(Color.darkGray);
            Nombre.setBorder(null);

            //---- Telefono ----
            Telefono.setBackground(Color.white);

            //---- Documento_label ----
            Documento_label.setText("Documento (solo numeros)");
            Documento_label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- Documento ----
            Documento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            Documento.setBackground(Color.white);

            //---- Direccion_label ----
            Direccion_label.setText("Direccion");
            Direccion_label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- Direccion ----
            Direccion.setBackground(Color.white);

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(Direccion_label)
                            .addComponent(Documento, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                            .addComponent(Documento_label, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(NameTitle)
                                    .addComponent(Nombre, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(Telefono_Label, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Telefono, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
                            .addComponent(Direccion, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(60, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(NameTitle)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(Telefono_Label)
                                .addGap(3, 3, 3)))
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(Nombre, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(Telefono, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(Documento_label, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Documento, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Direccion_label)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Direccion, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
            );
        }

        //======== panel2 ========
        {
            panel2.setBackground(new Color(42, 58, 64));

            //---- Title ----
            Title.setText("Edici\u00f3n de rol ");
            Title.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
            Title.setForeground(Color.white);
            Title.setHorizontalAlignment(SwingConstants.CENTER);

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addComponent(Title, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Title)
                        .addContainerGap())
            );
        }

        //======== panel3 ========
        {
            panel3.setBackground(new Color(203, 203, 203));

            //---- Cancel ----
            Cancel.setText("Cancelar");
            Cancel.setBorder(null);
            Cancel.setBackground(new Color(181, 181, 181));
            Cancel.setForeground(Color.white);
            Cancel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            Cancel.setBorderPainted(false);
            Cancel.addActionListener(e -> CancelActionPerformed(e));

            //---- Submit ----
            Submit.setText("Modificar");
            Submit.setBorder(null);
            Submit.setBackground(new Color(42, 58, 64));
            Submit.setForeground(Color.white);
            Submit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            Submit.setBorderPainted(false);
            Submit.addActionListener(e -> SubmitActionPerformed());

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addContainerGap(339, Short.MAX_VALUE)
                        .addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Submit, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(Submit, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 77, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JLabel NameTitle;
    private JLabel Telefono_Label;
    private JTextField Nombre;
    private JTextField Telefono;
    private JLabel Documento_label;
    private JTextField Documento;
    private JLabel Direccion_label;
    private JTextField Direccion;
    private JPanel panel2;
    private JLabel Title;
    private JPanel panel3;
    private JButton Cancel;
    private JButton Submit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
