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

    private JFrame parent;
    private final String main_back_path = "src/img/Main-back.png";

    public MainMenu(JFrame parent, String username) {
        initComponents();
        this.parent = parent;
        this.name.setText(username);
    }

    private void exitMouseClicked(MouseEvent e) {
        parent.dispose();
    }

    private void exitMouseEntered(MouseEvent e) {
        exit.setIcon(new ImageIcon(getClass().getResource("/img/logout-edit-pressed.png")));
    }

    private void exitMouseExited(MouseEvent e) {
        exit.setIcon(new ImageIcon(getClass().getResource("/img/logout-edit.png")));
    }
    
    

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel Header;
    private JLabel exit;
    private JPanel panel2;
    private JLayeredPane Nav;
    private JLabel iconUser;
    private JLabel name;
    private JLabel nameUser;
    private JLabel serparator_1;
    private JLabel serparator_2;
    private JLabel serparator_3;
    private JLabel serparator_4;
    private JLabel serparator_5;
    private JLabel icon_user;
    private JLabel icon_roles;
    private JLabel icon_people;
    private JLabel icon_roles2;
    private JLabel main_back;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        Header = new JPanel();
        exit = new JLabel();
        panel2 = new JPanel();
        Nav = new JLayeredPane();
        iconUser = new JLabel();
        name = new JLabel();
        nameUser = new JLabel();
        serparator_1 = new JLabel();
        serparator_2 = new JLabel();
        serparator_3 = new JLabel();
        serparator_4 = new JLabel();
        serparator_5 = new JLabel();
        icon_user = new JLabel();
        icon_roles = new JLabel();
        icon_people = new JLabel();
        icon_roles2 = new JLabel();
        main_back = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1024, 576));
        setMaximumSize(new Dimension(1920, 1080));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
        swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e" , javax. swing .border
        . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dialo\u0067"
        , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder
        () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
        . beans. PropertyChangeEvent e) { if( "borde\u0072" .equals ( e. getPropertyName () ) )throw new RuntimeException
        ( ) ;} } );

        //======== Header ========
        {
            Header.setBackground(Color.white);

            //---- exit ----
            exit.setIcon(new ImageIcon(getClass().getResource("/img/logout-edit.png")));
            exit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    exitMouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    exitMouseEntered(e);
                }
            });

            GroupLayout HeaderLayout = new GroupLayout(Header);
            Header.setLayout(HeaderLayout);
            HeaderLayout.setHorizontalGroup(
                HeaderLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                        .addContainerGap(779, Short.MAX_VALUE)
                        .addComponent(exit)
                        .addGap(14, 14, 14))
            );
            HeaderLayout.setVerticalGroup(
                HeaderLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(exit, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

        //======== panel2 ========
        {
            panel2.setBackground(new Color(244, 244, 244));
            panel2.setLayout(new CardLayout());
        }

        //======== Nav ========
        {

            //---- iconUser ----
            iconUser.setIcon(new ImageIcon(getClass().getResource("/img/IconUser.png")));
            Nav.add(iconUser, JLayeredPane.DEFAULT_LAYER);
            iconUser.setBounds(60, 60, 85, 90);

            //---- name ----
            name.setText("Harry");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Segoe UI", Font.BOLD, 12));
            name.setForeground(Color.white);
            Nav.add(name, JLayeredPane.DEFAULT_LAYER);
            name.setBounds(72, 155, 58, 15);

            //---- nameUser ----
            nameUser.setIcon(new ImageIcon(getClass().getResource("/img/NameUser.png")));
            Nav.add(nameUser, JLayeredPane.DEFAULT_LAYER);
            nameUser.setBounds(65, 150, 70, 25);

            //---- serparator_1 ----
            serparator_1.setIcon(new ImageIcon(getClass().getResource("/img/serparator.png")));
            Nav.add(serparator_1, JLayeredPane.DEFAULT_LAYER);
            serparator_1.setBounds(0, 215, 205, 1);

            //---- serparator_2 ----
            serparator_2.setIcon(new ImageIcon(getClass().getResource("/img/serparator.png")));
            Nav.add(serparator_2, JLayeredPane.DEFAULT_LAYER);
            serparator_2.setBounds(0, 270, 205, 1);

            //---- serparator_3 ----
            serparator_3.setIcon(new ImageIcon(getClass().getResource("/img/serparator.png")));
            Nav.add(serparator_3, JLayeredPane.DEFAULT_LAYER);
            serparator_3.setBounds(0, 325, 205, 1);

            //---- serparator_4 ----
            serparator_4.setIcon(new ImageIcon(getClass().getResource("/img/serparator.png")));
            Nav.add(serparator_4, JLayeredPane.DEFAULT_LAYER);
            serparator_4.setBounds(0, 380, 205, 1);

            //---- serparator_5 ----
            serparator_5.setIcon(new ImageIcon(getClass().getResource("/img/serparator.png")));
            Nav.add(serparator_5, JLayeredPane.DEFAULT_LAYER);
            serparator_5.setBounds(0, 435, 205, 1);

            //---- icon_user ----
            icon_user.setIcon(new ImageIcon(getClass().getResource("/img/usability-edit.png")));
            Nav.add(icon_user, JLayeredPane.DEFAULT_LAYER);
            icon_user.setBounds(35, 225, 30, 35);

            //---- icon_roles ----
            icon_roles.setIcon(new ImageIcon(getClass().getResource("/img/roles-edit.png")));
            Nav.add(icon_roles, JLayeredPane.DEFAULT_LAYER);
            icon_roles.setBounds(35, 280, 30, 35);

            //---- icon_people ----
            icon_people.setIcon(new ImageIcon(getClass().getResource("/img/people-edit.png")));
            Nav.add(icon_people, JLayeredPane.DEFAULT_LAYER);
            icon_people.setBounds(35, 335, 30, 35);

            //---- icon_roles2 ----
            icon_roles2.setIcon(new ImageIcon(getClass().getResource("/img/link-edit.png")));
            Nav.add(icon_roles2, JLayeredPane.DEFAULT_LAYER);
            icon_roles2.setBounds(35, 390, 30, 35);

            //---- main_back ----
            main_back.setIcon(new ImageIcon(getClass().getResource("/img/Main-back.png")));
            Nav.add(main_back, JLayeredPane.DEFAULT_LAYER);
            main_back.setBounds(0, 0, 203, 575);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(Nav, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(Header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(Header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
                .addComponent(Nav, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
