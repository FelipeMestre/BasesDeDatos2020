/*
 * Created by JFormDesigner on Mon Oct 26 17:43:52 UYT 2020
 */

package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import org.ucu.bd.Database;

/**
 * @author unknown
 */
public class UI extends JFrame {

    private Database loginDatabase;

    public UI() {
        initComponents();
    }

    public void initialize(){
        this.setVisible(true);
    }
/*
    private void enterActionPerformed(ActionEvent e) {
        if (user.getText() != null && password != null){
          //  ResultSet loginDatabase.login();
        }
    }
*/
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        login = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        user = new JTextField();
        label_user = new JLabel();
        label_password = new JLabel();
        button1 = new JButton();
        password = new JTextField();

        //======== this ========
        setResizable(false);
        setUndecorated(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new CardLayout());

        //======== login ========
        {
            login.setBackground(Color.white);
            login.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
            javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax
            . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
            .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,login. getBorder( )) ); login. addPropertyChangeListener (new java. beans.
            PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .
            equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //---- label1 ----
            label1.setIcon(new ImageIcon("C:\\Users\\ptcna\\Documents\\GitHub\\BasesDeDatos2020\\lib\\Login_Logo.png"));

            //---- label2 ----
            label2.setText("Login");
            label2.setFont(new Font("Segoe UI", Font.BOLD, 24));
            label2.setForeground(new Color(75, 75, 75));

            //---- user ----
            user.setBorder(new LineBorder(Color.lightGray, 1, true));
            user.setOpaque(false);

            //---- label_user ----
            label_user.setText("Username");
            label_user.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            label_user.setForeground(new Color(112, 112, 112));

            //---- label_password ----
            label_password.setText("Password");
            label_password.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            label_password.setForeground(new Color(112, 112, 112));

            //---- button1 ----
            button1.setText("Login");
            button1.setBackground(new Color(42, 58, 64));
            button1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
            button1.setForeground(Color.white);

            //---- password ----
            password.setBorder(new LineBorder(Color.lightGray, 1, true));
            password.setOpaque(false);

            GroupLayout loginLayout = new GroupLayout(login);
            login.setLayout(loginLayout);
            loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup()
                    .addGroup(loginLayout.createSequentialGroup()
                        .addComponent(label1)
                        .addGroup(loginLayout.createParallelGroup()
                            .addGroup(loginLayout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addGroup(loginLayout.createParallelGroup()
                                    .addComponent(label_user, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(user, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_password, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(password, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(loginLayout.createSequentialGroup()
                                .addGap(172, 172, 172)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 129, Short.MAX_VALUE))
            );
            loginLayout.setVerticalGroup(
                loginLayout.createParallelGroup()
                    .addComponent(label1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addGroup(loginLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(label2)
                        .addGap(18, 18, 18)
                        .addComponent(label_user, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(user, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label_password, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(69, Short.MAX_VALUE))
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
    private JButton button1;
    private JTextField password;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
