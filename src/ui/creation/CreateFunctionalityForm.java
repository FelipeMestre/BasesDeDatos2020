/*
 * Created by JFormDesigner on Sun Nov 01 19:25:33 UYT 2020
 */

package ui.creation;

import java.awt.event.*;
import org.ucu.bd.ModelConstructor;
import ui.MainMenu;

import javax.swing.*;
import java.awt.*;

/**
 * @author unknown
 */
public class CreateFunctionalityForm extends JFrame {

    private ModelConstructor constructor;
    private MainMenu parent;

    public CreateFunctionalityForm(ModelConstructor controller, MainMenu parent) {
        this.constructor = controller;
        this.parent = parent;
        initComponents();;
    }

    private void SubmitActionPerformed() {
        String name = Nombre.getText();
        String description = Descripcion.getText();
        if (constructor.createModel(name,description,"funcionalidad",this)){
            parent.setEnabled(true);
            parent.fetchFuncionalities();
            parent.requestFocus();
            this.dispose();
        }
    }

    private void CancelActionPerformed(ActionEvent e) {
        this.dispose();
        parent.setEnabled(true);
        parent.requestFocus();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        Title = new JLabel();
        NameTitle = new JLabel();
        DescTitle = new JLabel();
        Nombre = new JTextField();
        scrollPane1 = new JScrollPane();
        Descripcion = new JTextArea();
        Submit = new JButton();
        Cancel = new JButton();

        //======== this ========
        setUndecorated(true);
        setBackground(Color.white);
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(244, 244, 244));

            //---- Title ----
            Title.setText("Crear Una Funcionalidad");
            Title.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
            Title.setForeground(new Color(112, 112, 112));

            //---- NameTitle ----
            NameTitle.setText("Nombre de funcionalidad");
            NameTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- DescTitle ----
            DescTitle.setText("Descripci\u00f3n");
            DescTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- Nombre ----
            Nombre.setBackground(Color.white);
            Nombre.setForeground(Color.darkGray);
            Nombre.setBorder(null);

            //======== scrollPane1 ========
            {

                //---- Descripcion ----
                Descripcion.setBorder(null);
                Descripcion.setBackground(Color.white);
                Descripcion.setLineWrap(true);
                Descripcion.setCaretColor(Color.white);
                Descripcion.setForeground(Color.darkGray);
                Descripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                Descripcion.setMargin(new Insets(0, 10, 0, 0));
                scrollPane1.setViewportView(Descripcion);
            }

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

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(0, 258, Short.MAX_VALUE)
                        .addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Submit, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(DescTitle, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NameTitle)
                                    .addComponent(Nombre, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(Title)))
                        .addContainerGap(134, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(Title)
                        .addGap(18, 18, 18)
                        .addComponent(NameTitle)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Nombre, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DescTitle)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(Submit)
                            .addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private JLabel DescTitle;
    private JTextField Nombre;
    private JScrollPane scrollPane1;
    private JTextArea Descripcion;
    private JButton Submit;
    private JButton Cancel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
