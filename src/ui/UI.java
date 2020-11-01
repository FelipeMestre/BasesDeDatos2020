/*
 * Created by JFormDesigner on Mon Oct 26 17:43:52 UYT 2020
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;


//Cosas de passwords
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import Utils.PasswordManager;
import model.currentUser;
import org.ucu.bd.Database;

/**
 * @author unknown
 */
public class UI extends JFrame {

    private Database loginDatabase;
    private String lastUsedUsername;
    private int tries = 3;
    private final Border defaultBorder = new LineBorder(Color.lightGray, 1, true);
    private final Border highlightBorder = new LineBorder(new Color(93,95,94), 1,true);
    private final Color highlightColor = new Color(192, 238, 242);
    private final Color defaultColor = new Color(42, 58, 64);
    private int xx;
    private int xy;

    public UI(Database loginDatabase)
    {
        this.loginDatabase = loginDatabase;
        initComponents();
    }

    public void initialize(){
        this.setVisible(true);
    }

    private void userFocusGained(FocusEvent e) {
        user.setBorder(highlightBorder);
    }

    private void userFocusLost(FocusEvent e) {
        user.setBorder(defaultBorder);
    }

    private void passwordFocusGained(FocusEvent e) {
        password.setBorder(highlightBorder);
    }

    private void passwordFocusLost(FocusEvent e) {
        password.setBorder(defaultBorder);
    }

    private void button1MouseEntered(MouseEvent e) {
        Rectangle r = new Rectangle();
        enter.getBounds(r);
        r.setLocation(r.x-5,r.y-3);
        r.setSize(160,39);
        enter.setBounds(r);
        enter.setBackground(highlightColor);
        enter.setForeground(defaultColor);

    }

    private void button1MouseExited(MouseEvent e) {
        Rectangle r = new Rectangle();
        enter.getBounds(r);
        r.setLocation(r.x+5,r.y+3);
        r.setSize(150,33);
        enter.setBounds(r);
        enter.setBackground(defaultColor);
        enter.setForeground(Color.WHITE);
    }

    private void thisMousePressed(MouseEvent e) {
        this.xx = e.getX();
        this.xy = e.getY();
    }

    private void thisMouseDragged(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();

        setLocation(x-xx, y-xy);
    }

    private void enterActionPerformed() {
        //Tomamos el texto de los campos a ingresar
        String userText = user.getText();
        String passwordText = password.getText();
        if (user!= null && password != null){
            //Ejecutamos el query
            ResultSet rs = loginDatabase.login(userText, "usuario");
            try {
                if (!rs.next()){ //Si no hay resultados

                    JOptionPane.showMessageDialog(this, "Usuario Incorrecto");

                } else { //Si hay resultados
                    int tries = rs.getInt("availabletries");
                    if (rs.getBoolean("admin")){ //Si es administrador
                        if (!rs.getBoolean("bloqueado")){ //Si no esta bloqueado
                            if(PasswordManager.validatePassword2(rs.getString("Contraseña"),passwordText) ){
                                //Iniciar programa
                                if (tries != 5){
                                    rs.updateInt("availableTries",5);
                                    rs.updateRow();
                                }
                                currentUser user = currentUser.getCurrentUser();
                                user.setUser_id(rs.getInt("id_usuario"));

                                JFrame menu = new MainMenu(this, userText, this.loginDatabase);
                                menu.setVisible(true);
                                this.dispose();

                            } else {
                                JOptionPane.showMessageDialog(this, "Contraseña Incorrecta");
                                if(tries == 0){
                                    rs.updateBoolean("bloqueado",true);
                                } else {
                                    rs.updateInt("availableTries",--tries);
                                }
                                rs.updateRow();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Cuenta Bloqueada.\nContacte al administrador");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Usuario Incorrecto");
                    }
                }
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        login = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        user = new JTextField();
        label_user = new JLabel();
        label_password = new JLabel();
        enter = new JButton();
        password = new JPasswordField();

        //======== this ========
        setResizable(false);
        setUndecorated(true);
        setIconImage(new ImageIcon(getClass().getResource("/org/ucu/bd/img/logo.png")).getImage());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                thisMousePressed(e);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                thisMouseDragged(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new CardLayout());

        //======== login ========
        {
            login.setBackground(Color.white);
            login.setBorder(null);
            login.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,login. getBorder( )) ); login. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/img/Login_Logo.png")));

            //---- label2 ----
            label2.setText("Login");
            label2.setFont(new Font("Segoe UI", Font.BOLD, 24));
            label2.setForeground(new Color(75, 75, 75));

            //---- user ----
            user.setBorder(new LineBorder(Color.lightGray, 1, true));
            user.setOpaque(false);
            user.setBackground(new Color(34, 148, 143, 96));
            user.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    userFocusGained(e);
                }
                @Override
                public void focusLost(FocusEvent e) {
                    userFocusLost(e);
                }
            });

            //---- label_user ----
            label_user.setText("Username");
            label_user.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            label_user.setForeground(new Color(112, 112, 112));

            //---- label_password ----
            label_password.setText("Password");
            label_password.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            label_password.setForeground(new Color(112, 112, 112));

            //---- enter ----
            enter.setText("Login");
            enter.setBackground(new Color(42, 58, 64));
            enter.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
            enter.setForeground(Color.white);
            enter.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button1MouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button1MouseExited(e);
                }
            });
            enter.addActionListener(e -> enterActionPerformed());

            //---- password ----
            password.setBorder(new LineBorder(Color.lightGray, 1, true));
            password.setOpaque(false);
            password.setCaretColor(new Color(153, 153, 255));
            password.setBackground(new Color(34, 148, 143, 96));
            password.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    passwordFocusGained(e);
                }
                @Override
                public void focusLost(FocusEvent e) {
                    passwordFocusLost(e);
                }
            });

            GroupLayout loginLayout = new GroupLayout(login);
            login.setLayout(loginLayout);
            loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup()
                    .addGroup(loginLayout.createSequentialGroup()
                        .addComponent(label1)
                        .addGroup(loginLayout.createParallelGroup()
                            .addGroup(loginLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                                .addComponent(enter, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(172, Short.MAX_VALUE))
                            .addGroup(loginLayout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addGroup(loginLayout.createParallelGroup()
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(user, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(password, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_user, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_password, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(133, Short.MAX_VALUE))))
            );
            loginLayout.setVerticalGroup(
                loginLayout.createParallelGroup()
                    .addGroup(loginLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(label2)
                        .addGap(18, 18, 18)
                        .addComponent(label_user, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(user, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label_password, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(enter, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(81, Short.MAX_VALUE))
                    .addComponent(label1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
            );
        }
        contentPane.add(login, "card1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel login;
    private JLabel label1;
    private JLabel label2;
    private JTextField user;
    private JLabel label_user;
    private JLabel label_password;
    private JButton enter;
    private JPasswordField password;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
