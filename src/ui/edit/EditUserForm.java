/*
 * Created by JFormDesigner on Tue Nov 03 14:04:54 UYT 2020
 */

package ui.edit;

import Utils.PasswordManager;
import org.ucu.bd.Database;
import org.ucu.bd.ModelConstructor;
import ui.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author unknown
 */
public class EditUserForm extends JFrame {

    private ModelConstructor constructor;
    private MainMenu parent;
    private String userId;
    private String userName;
    private boolean bloqued;

    public EditUserForm(MainMenu parent, ModelConstructor controller,
                        String userName, boolean bloqued, String userId) {
        this.constructor = controller;
        this.parent = parent;
        this.userName = userName;
        this.bloqued = bloqued;
        this.userId = userId;
        initComponents();
        this.NombreUsuario.setText(userName);
        Bloqueado.setSelected(bloqued);

    }

    private void SubmitActionPerformed() {
        String newUsername = NombreUsuario.getText();
        String newPassword = NuevaContraseña.getText();
        String oldPassword = ContraseñaAnterior.getText();
        int creador = 0;
        int autorizador = 0;
        int ci_persona = 0;
        boolean activo = true;
        boolean admin = true;
        int availabletries = 0;
        boolean newBlocked = Bloqueado.isSelected();
        ResultSet user = constructor.search("usuario", "id_usuario", userId);
        try {
            if (user.first()) {
                creador = user.getInt("creador");
                autorizador = user.getInt("autorizador");
                ci_persona = user.getInt("ci_persona");
                activo = user.getBoolean("activo");
                admin = user.getBoolean("admin");
                availabletries = user.getInt("availabletries");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (PasswordManager.getInstance().isUsernameValid(newUsername,this)){
            //Checkea si se ingresaron las contraseñas
            if(!oldPassword.equals("") && !newPassword.equals("")) {
                //Checkea la vieja contraseña
                if (constructor.checkIfCorrectPassword(userId,userName, oldPassword, "usuario", this)) {
                    //Checkea la nueva contraseña
                    if (PasswordManager.isPasswordValid(newPassword,this)){
                        constructor.updateUser(userId,newUsername,newPassword,newBlocked,true, creador, autorizador, ci_persona, activo, admin, availabletries);
                        parent.fetchUsers();
                        exitForm();
                    }
                }
            } else {
                //Si hay alguno de los dos vacios
                if (!ContraseñaAnterior.getText().equals("") || !NuevaContraseña.getText().equals("")){
                    JOptionPane.showMessageDialog(this,"Para cambiar la contraseña debe ingresar\n Antigua y Nueva contraseña");
                } else {//Si no se quiere actualizar la contraseña
                    constructor.updateUser(userId,newUsername,newPassword,newBlocked,false, creador, autorizador, ci_persona, activo, admin, availabletries);
                    parent.fetchUsers();
                    exitForm();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,"Ingrese un nombre de usuario valido");
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
        ContraseñaAnterior_Label = new JLabel();
        NombreUsuario = new JTextField();
        ContraseñaAnterior = new JTextField();
        NuevaContraseña_Label = new JLabel();
        NuevaContraseña = new JTextField();
        Bloqueado = new JCheckBox();
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
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
            border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER
            , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font
            .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order"
            .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //---- NameTitle ----
            NameTitle.setText("Nombre de Usuario");
            NameTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- ContraseñaAnterior_Label ----
            ContraseñaAnterior_Label.setText("Contrase\u00f1a Anterior");
            ContraseñaAnterior_Label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- NombreUsuario ----
            NombreUsuario.setBackground(Color.white);
            NombreUsuario.setForeground(Color.darkGray);
            NombreUsuario.setBorder(null);

            //---- ContraseñaAnterior ----
            ContraseñaAnterior.setBackground(Color.white);

            //---- NuevaContraseña_Label ----
            NuevaContraseña_Label.setText("Nueva Contrase\u00f1a");
            NuevaContraseña_Label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

            //---- NuevaContraseña ----
            NuevaContraseña.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            NuevaContraseña.setBackground(Color.white);

            //---- Bloqueado ----
            Bloqueado.setText("Bloqueado");
            Bloqueado.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
            Bloqueado.setBackground(new Color(247, 247, 247, 247));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(NuevaContraseña_Label, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(NameTitle)
                                        .addComponent(NombreUsuario, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Bloqueado))
                                    .addGap(44, 44, 44)
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(ContraseñaAnterior, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ContraseñaAnterior_Label, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(NuevaContraseña, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(59, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(NameTitle))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(ContraseñaAnterior_Label)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(NombreUsuario, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(ContraseñaAnterior, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(NuevaContraseña_Label, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NuevaContraseña, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addComponent(Bloqueado))
                        .addContainerGap(60, Short.MAX_VALUE))
            );
        }

        //======== panel2 ========
        {
            panel2.setBackground(new Color(42, 58, 64));

            //---- Title ----
            Title.setText("Edici\u00f3n de Usuario");
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
    private JLabel ContraseñaAnterior_Label;
    private JTextField NombreUsuario;
    private JTextField ContraseñaAnterior;
    private JLabel NuevaContraseña_Label;
    private JTextField NuevaContraseña;
    private JCheckBox Bloqueado;
    private JPanel panel2;
    private JLabel Title;
    private JPanel panel3;
    private JButton Cancel;
    private JButton Submit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
