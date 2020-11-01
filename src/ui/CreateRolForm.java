/*
 * Created by JFormDesigner on Sun Nov 01 19:25:33 UYT 2020
 */

package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class CreateRolForm extends JFrame {
    public CreateRolForm() {
        initComponents();
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

        //======== this ========
        setUndecorated(true);
        setBackground(Color.white);
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(244, 244, 244));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );

            //---- Title ----
            Title.setText("Crear un nuevo rol ");
            Title.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
            Title.setForeground(new Color(112, 112, 112));

            //---- NameTitle ----
            NameTitle.setText("Nombre del rol");
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

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(Title))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(NameTitle)
                                    .addComponent(Nombre, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DescTitle, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(158, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(0, 358, Short.MAX_VALUE)
                        .addComponent(Submit, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
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
                        .addGap(0, 0, 0)
                        .addComponent(DescTitle)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(Submit)
                        .addContainerGap(34, Short.MAX_VALUE))
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
