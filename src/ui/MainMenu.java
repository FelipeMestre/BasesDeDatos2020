/*
 * Created by JFormDesigner on Thu Oct 29 02:16:05 UYT 2020
 */

package ui;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import org.ucu.bd.Database;
import org.ucu.bd.ModelConstructor;
import org.ucu.bd.TableButtonRender;
import org.ucu.bd.TableHeaderRender;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * @author unknown
 */
public class MainMenu extends JFrame {

    private JFrame parent;
    private JLabel selected_option_button;
    private Option[] options;
    private ModelConstructor controller;

    public MainMenu(JFrame parent, String username, ModelConstructor modelConstructor) {
        initComponents();
        this.controller = modelConstructor;
        options = createOptions();
        this.parent = parent;
        this.name.setText(username);
        initRolesTable();
        initRolesDashboard();
    }

    private Option[] createOptions(){
        Option option1 = new Option(optionBack_1, usersLabel, icon_user, "/img/option.png",
                "/img/usability-edit.png", "/img/usability-edit-selected.png");
        Option option2 = new Option(optionBack_2, rolesLabel, icon_roles, "/img/option.png",
                "/img/roles-edit.png", "/img/diamond.png");
        Option option3 = new Option(optionBack_3, peopleLabel, icon_people, "/img/option.png",
                "/img/people-edit.png","/img/people-edit-selected.png");
        Option option4 = new Option(optionBack_4, RelationshipsLabel, icon_relation, "/img/option.png",
                        "/img/link-edit.png","/img/link-edit-selected.png");
        return new Option[]{option1, option2, option3, option4};
    }

    private void initRolesDashboard(){
        this.TotalRoles.setText(String.valueOf(controller.totalRoles()));
        this.ActiveRoles.setText(String.valueOf(controller.activeRoles()));
    }
    private void initRolesTable() {
        String[][] roles_info = this.controller.getRoles();
        RolesTable = new JTable(){
            public TableCellRenderer getCellRenderer( int row, int column ) {
                return new TableButtonRender();
            }
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 1) { //Sport column
                    tip = getValueAt(rowIndex, colIndex).toString();
                }
                return tip;
            }
        };
        RolesTable.setModel(new DefaultTableModel(
                roles_info,
                new String[] {"Nombre", "Descripci\u00f3n", " "}));
        RolesTable.setRowHeight(35);
        RolesTable.getColumnModel().getColumn(2).setMaxWidth(90);
        RolesTable.setShowGrid(false);
        TableCellRenderer baseRenderer = RolesTable.getTableHeader().getDefaultRenderer();
        RolesTable.getTableHeader().setDefaultRenderer(new TableHeaderRender(baseRenderer));
        scrollTable.setBorder(new LineBorder(new Color(0,0,0,0)));
        scrollTable.setViewportView(RolesTable);
    }

    private void exitMouseClicked(MouseEvent e) {
        this.dispose();
    }

    private void exitMouseEntered(MouseEvent e) {
        exit.setIcon(new ImageIcon(getClass().getResource("/img/logout-edit-pressed.png")));
    }

    private void exitMouseExited(MouseEvent e) {
        exit.setIcon(new ImageIcon(getClass().getResource("/img/logout-edit.png")));
    }

    private void optionBack_1MouseEntered(MouseEvent e) {
        updateOptionState(optionBack_1, true);
    }

    private void optionBack_1MouseExited(MouseEvent e) {
        updateOptionState(optionBack_1, false);
    }

    private void updateOptionState(JLabel option,boolean state){
        if (!option.equals(selected_option_button)){
            if (state){
                option.setIcon(new ImageIcon(getClass().getResource("/img/option.png")));
            } else {
                option.setIcon(null);
            }
        }
    }

    private void optionBack_2MouseEntered(MouseEvent e) {
        updateOptionState(optionBack_2, true);
    }

    private void optionBack_2MouseExited(MouseEvent e) {
        updateOptionState(optionBack_2, false);
    }

    private void optionBack_3MouseEntered(MouseEvent e) {
        updateOptionState(optionBack_3, true);
    }

    private void optionBack_3MouseExited(MouseEvent e) {
        updateOptionState(optionBack_3, false);
    }

    private void optionBack_4MouseEntered(MouseEvent e) {
        updateOptionState(optionBack_4, true);
    }

    private void optionBack_4MouseExited(MouseEvent e) {
        updateOptionState(optionBack_4, false);
    }

    private void optionBack_2MouseClicked(MouseEvent e) {
        resetOptions();
        options[1].select();
        changeCard("roles");
        selected_option_button = optionBack_2;
    }

    private void optionBack_1MouseClicked(MouseEvent e) {
        resetOptions();
        options[0].select();
        selected_option_button = optionBack_1;
    }

    private void optionBack_3MouseClicked(MouseEvent e) {
        resetOptions();
        options[2].select();
        selected_option_button = optionBack_3;
    }

    private void resetOptions(){
        for (Option op: options){
            op.reset();
        }
    }

    private void changeCard(String card_name){
        CardLayout cl = (CardLayout)(this.main.getLayout());
        cl.show(this.main, card_name);
    }

    private void optionBack_4MouseClicked(MouseEvent e) {
        resetOptions();
        options[3].select();
        selected_option_button = optionBack_4;
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel Header;
    private JLabel exit;
    private JPanel main;
    private JPanel Roles;
    private JLabel RolesTitle;
    private JLabel RolesDesc;
    private JLayeredPane layeredPane1;
    private JLabel TotalRoles;
    private JLabel ActiveRoles;
    private JLabel total_title;
    private JLabel active_title;
    private JLabel dashboard_back;
    private JLayeredPane layeredPane2;
    private JLabel label1;
    private JScrollPane scrollTable;
    private JTable RolesTable;
    private JLabel roles_list_back;
    private JPanel Default;
    private JLayeredPane Nav;
    private JLabel iconUser;
    private JLabel name;
    private JLabel nameUser;
    private JLabel usersLabel;
    private JLabel serparator_1;
    private JLabel serparator_2;
    private JLabel serparator_3;
    private JLabel serparator_4;
    private JLabel serparator_5;
    private JLabel icon_user;
    private JLabel icon_roles;
    private JLabel icon_people;
    private JLabel icon_relation;
    private JLabel rolesLabel;
    private JLabel peopleLabel;
    private JLabel RelationshipsLabel;
    private JLabel Usuarios_Button;
    private JLabel Roles_Button;
    private JLabel Personas_Button;
    private JLabel Relaciones_Button;
    private JLabel main_back;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        Header = new JPanel();
        exit = new JLabel();
        main = new JPanel();
        Roles = new JPanel();
        RolesTitle = new JLabel();
        RolesDesc = new JLabel();
        layeredPane1 = new JLayeredPane();
        TotalRoles = new JLabel();
        ActiveRoles = new JLabel();
        total_title = new JLabel();
        active_title = new JLabel();
        dashboard_back = new JLabel();
        layeredPane2 = new JLayeredPane();
        label1 = new JLabel();
        scrollTable = new JScrollPane();
        RolesTable = new JTable();
        roles_list_back = new JLabel();
        Default = new JPanel();
        Nav = new JLayeredPane();
        iconUser = new JLabel();
        name = new JLabel();
        nameUser = new JLabel();
        usersLabel = new JLabel();
        serparator_1 = new JLabel();
        serparator_2 = new JLabel();
        serparator_3 = new JLabel();
        serparator_4 = new JLabel();
        serparator_5 = new JLabel();
        icon_user = new JLabel();
        icon_roles = new JLabel();
        icon_people = new JLabel();
        icon_relation = new JLabel();
        rolesLabel = new JLabel();
        peopleLabel = new JLabel();
        RelationshipsLabel = new JLabel();
        Usuarios_Button = new JLabel();
        Roles_Button = new JLabel();
        Personas_Button = new JLabel();
        Relaciones_Button = new JLabel();
        main_back = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(1024, 576));
        setUndecorated(true);
        var contentPane = getContentPane();

        //======== Header ========
        {
            Header.setBackground(Color.white);
            Header.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,Header. getBorder( )) )
            ; Header. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;

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
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        //======== main ========
        {
            main.setBackground(new Color(244, 244, 244));
            main.setLayout(new CardLayout());

            //======== Roles ========
            {
                Roles.setBackground(new Color(245, 245, 245));

                //---- RolesTitle ----
                RolesTitle.setText("Roles");
                RolesTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
                RolesTitle.setBackground(new Color(64, 65, 65));
                RolesTitle.setForeground(new Color(64, 65, 65));

                //---- RolesDesc ----
                RolesDesc.setText("Crea y administra los roles de tu aplicaci\u00f3n.");
                RolesDesc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
                RolesDesc.setForeground(new Color(126, 126, 126));

                //======== layeredPane1 ========
                {

                    //---- TotalRoles ----
                    TotalRoles.setText("24");
                    TotalRoles.setFont(new Font("Segoe UI", Font.BOLD, 32));
                    TotalRoles.setHorizontalAlignment(SwingConstants.CENTER);
                    TotalRoles.setForeground(new Color(52, 154, 162));
                    layeredPane1.add(TotalRoles, JLayeredPane.DEFAULT_LAYER);
                    TotalRoles.setBounds(60, 22, 70, 35);

                    //---- ActiveRoles ----
                    ActiveRoles.setText("16");
                    ActiveRoles.setFont(new Font("Segoe UI", Font.BOLD, 32));
                    ActiveRoles.setHorizontalAlignment(SwingConstants.CENTER);
                    ActiveRoles.setForeground(new Color(52, 154, 162));
                    layeredPane1.add(ActiveRoles, JLayeredPane.DEFAULT_LAYER);
                    ActiveRoles.setBounds(210, 22, 70, 35);

                    //---- total_title ----
                    total_title.setText("Roles creados");
                    total_title.setHorizontalAlignment(SwingConstants.CENTER);
                    total_title.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
                    total_title.setForeground(new Color(153, 153, 153));
                    layeredPane1.add(total_title, JLayeredPane.DEFAULT_LAYER);
                    total_title.setBounds(50, 65, 90, 16);

                    //---- active_title ----
                    active_title.setText("Roles activos");
                    active_title.setHorizontalAlignment(SwingConstants.CENTER);
                    active_title.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
                    active_title.setForeground(new Color(153, 153, 153));
                    layeredPane1.add(active_title, JLayeredPane.DEFAULT_LAYER);
                    active_title.setBounds(200, 65, 90, 16);

                    //---- dashboard_back ----
                    dashboard_back.setIcon(new ImageIcon(getClass().getResource("/img/rolesHeader.png")));
                    layeredPane1.add(dashboard_back, JLayeredPane.DEFAULT_LAYER);
                    dashboard_back.setBounds(0, 0, 348, 105);
                }

                //======== layeredPane2 ========
                {

                    //---- label1 ----
                    label1.setText("Lista de Roles");
                    label1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
                    label1.setForeground(new Color(102, 102, 102));
                    layeredPane2.add(label1, JLayeredPane.DEFAULT_LAYER);
                    label1.setBounds(30, 12, 120, 31);

                    //======== scrollTable ========
                    {
                        scrollTable.setBorder(null);

                        //---- RolesTable ----
                        RolesTable.setBackground(Color.white);
                        RolesTable.setModel(new DefaultTableModel(
                            new Object[][] {
                                {null, "", null},
                                {null, null, null},
                            },
                            new String[] {
                                "Nombre", "Descripci\u00f3n", " "
                            }
                        ) {
                            Class<?>[] columnTypes = new Class<?>[] {
                                String.class, String.class, Object.class
                            };
                            @Override
                            public Class<?> getColumnClass(int columnIndex) {
                                return columnTypes[columnIndex];
                            }
                        });
                        RolesTable.setCellSelectionEnabled(true);
                        RolesTable.setFillsViewportHeight(true);
                        RolesTable.setGridColor(Color.white);
                        scrollTable.setViewportView(RolesTable);
                    }
                    layeredPane2.add(scrollTable, JLayeredPane.DEFAULT_LAYER);
                    scrollTable.setBounds(0, 55, 348, 175);

                    //---- roles_list_back ----
                    roles_list_back.setIcon(new ImageIcon(getClass().getResource("/img/RolesTableBack.png")));
                    layeredPane2.add(roles_list_back, JLayeredPane.DEFAULT_LAYER);
                    roles_list_back.setBounds(0, 0, 355, 55);
                }

                GroupLayout RolesLayout = new GroupLayout(Roles);
                Roles.setLayout(RolesLayout);
                RolesLayout.setHorizontalGroup(
                    RolesLayout.createParallelGroup()
                        .addGroup(RolesLayout.createSequentialGroup()
                            .addGroup(RolesLayout.createParallelGroup()
                                .addGroup(RolesLayout.createSequentialGroup()
                                    .addGap(88, 88, 88)
                                    .addGroup(RolesLayout.createParallelGroup()
                                        .addComponent(RolesTitle)
                                        .addComponent(RolesDesc, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(RolesLayout.createSequentialGroup()
                                    .addGap(77, 77, 77)
                                    .addGroup(RolesLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(layeredPane1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                        .addComponent(layeredPane2, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))))
                            .addContainerGap(363, Short.MAX_VALUE))
                );
                RolesLayout.setVerticalGroup(
                    RolesLayout.createParallelGroup()
                        .addGroup(RolesLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(RolesTitle)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(RolesDesc)
                            .addGap(18, 18, 18)
                            .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(layeredPane2, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(40, Short.MAX_VALUE))
                );
            }
            main.add(Roles, "roles");

            //======== Default ========
            {
                Default.setBackground(new Color(245, 245, 245));

                GroupLayout DefaultLayout = new GroupLayout(Default);
                Default.setLayout(DefaultLayout);
                DefaultLayout.setHorizontalGroup(
                    DefaultLayout.createParallelGroup()
                        .addGap(0, 820, Short.MAX_VALUE)
                );
                DefaultLayout.setVerticalGroup(
                    DefaultLayout.createParallelGroup()
                        .addGap(0, 529, Short.MAX_VALUE)
                );
            }
            main.add(Default, "card2");
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

            //---- usersLabel ----
            usersLabel.setText("Usuarios");
            usersLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            usersLabel.setForeground(Color.white);
            Nav.add(usersLabel, JLayeredPane.DEFAULT_LAYER);
            usersLabel.setBounds(75, 231, 55, usersLabel.getPreferredSize().height);

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

            //---- icon_relation ----
            icon_relation.setIcon(new ImageIcon(getClass().getResource("/img/link-edit.png")));
            Nav.add(icon_relation, JLayeredPane.DEFAULT_LAYER);
            icon_relation.setBounds(35, 390, 30, 35);

            //---- rolesLabel ----
            rolesLabel.setText("Roles");
            rolesLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            rolesLabel.setForeground(Color.white);
            Nav.add(rolesLabel, JLayeredPane.DEFAULT_LAYER);
            rolesLabel.setBounds(75, 286, 35, 20);

            //---- peopleLabel ----
            peopleLabel.setText("Personas");
            peopleLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            peopleLabel.setForeground(Color.white);
            Nav.add(peopleLabel, JLayeredPane.DEFAULT_LAYER);
            peopleLabel.setBounds(75, 341, 60, 20);

            //---- RelationshipsLabel ----
            RelationshipsLabel.setText("Relaciones");
            RelationshipsLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            RelationshipsLabel.setForeground(Color.white);
            Nav.add(RelationshipsLabel, JLayeredPane.DEFAULT_LAYER);
            RelationshipsLabel.setBounds(75, 396, 70, 20);

            //---- Usuarios_Button ----
            Usuarios_Button.setBackground(new Color(120, 223, 225, 106));
            Usuarios_Button.setRequestFocusEnabled(false);
            Usuarios_Button.setIcon(null);
            Usuarios_Button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    optionBack_1MouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    optionBack_1MouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    optionBack_1MouseExited(e);
                }
            });
            Nav.add(Usuarios_Button, JLayeredPane.DEFAULT_LAYER);
            Usuarios_Button.setBounds(0, 215, 203, 55);

            //---- Roles_Button ----
            Roles_Button.setBackground(new Color(60, 63, 65, 0));
            Roles_Button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    optionBack_2MouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    optionBack_2MouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    optionBack_2MouseExited(e);
                }
            });
            Nav.add(Roles_Button, JLayeredPane.DEFAULT_LAYER);
            Roles_Button.setBounds(0, 270, 203, 55);

            //---- Personas_Button ----
            Personas_Button.setBackground(new Color(60, 63, 65, 0));
            Personas_Button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    optionBack_3MouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    optionBack_3MouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    optionBack_3MouseExited(e);
                }
            });
            Nav.add(Personas_Button, JLayeredPane.DEFAULT_LAYER);
            Personas_Button.setBounds(0, 325, 203, 55);

            //---- Relaciones_Button ----
            Relaciones_Button.setBackground(new Color(60, 63, 65, 0));
            Relaciones_Button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    optionBack_4MouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    optionBack_4MouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    optionBack_4MouseExited(e);
                }
            });
            Nav.add(Relaciones_Button, JLayeredPane.DEFAULT_LAYER);
            Relaciones_Button.setBounds(0, 380, 203, 55);

            //---- main_back ----
            main_back.setIcon(new ImageIcon(getClass().getResource("/img/Main-back.png")));
            Nav.add(main_back, JLayeredPane.DEFAULT_LAYER);
            main_back.setBounds(0, 0, 203, 576);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(Nav, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(Header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(main, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(Header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(main, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(Nav)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

}
