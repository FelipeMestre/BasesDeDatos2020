/*
 * Created by JFormDesigner on Tue Nov 03 14:04:54 UYT 2020
 */

package ui.edit;

import org.ucu.bd.ModelConstructor;
import ui.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author unknown
 */
public class EditFuncionalityForm extends JFrame {

    private ModelConstructor constructor;
    private MainMenu parent;
    private String funcionality;

    public EditFuncionalityForm(MainMenu parent, ModelConstructor controller, String func_id, String func_name, String func_desc) {
        this.constructor = controller;
        this.parent = parent;
        this.funcionality = func_id;
        initComponents();
        this.Nombre.setText(func_name);
        this.Descripcion.setText(func_desc);
    }

    private void SubmitActionPerformed() {
        String newName = Nombre.getText();
        String newDesc = Descripcion.getText();
        if (!newName.equals("") && !newDesc.equals("")){
            constructor.updateFuncionality(funcionality, newName, newDesc);
            parent.fetchFuncionalities();
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
        DescTitle = new JLabel();
        Nombre = new JTextField();
        scrollPane1 = new JScrollPane();
        Descripcion = new JTextArea();
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

            //---- NameTitle ----
            NameTitle.setText("Nombre del funcionalidad");
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

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(NameTitle)
                            .addComponent(Nombre, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                            .addComponent(DescTitle, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(NameTitle)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Nombre, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DescTitle)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))
            );
        }

        //======== panel2 ========
        {
            panel2.setBackground(new Color(42, 58, 64));

            //---- Title ----
            Title.setText("Edici\u00f3n de Funcionalidad");
            Title.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
            Title.setForeground(Color.white);
            Title.setHorizontalAlignment(SwingConstants.CENTER);

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addComponent(Title, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
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
                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Submit, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(Submit, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JLabel NameTitle;
    private JLabel DescTitle;
    private JTextField Nombre;
    private JScrollPane scrollPane1;
    private JTextArea Descripcion;
    private JPanel panel2;
    private JLabel Title;
    private JPanel panel3;
    private JButton Cancel;
    private JButton Submit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
