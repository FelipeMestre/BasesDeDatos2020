/*
 * Created by JFormDesigner on Thu Oct 29 02:16:05 UYT 2020
 */

package ui;

import javax.swing.border.LineBorder;
import javax.swing.table.*;

import actions.DeletePersonAction;
import actions.DeleteRoleAction;
import actions.EditPersonAction;
import actions.EditRoleAction;
import org.ucu.bd.*;
import ui.creation.CreatePersonForm;
import ui.creation.CreateRolForm;
import ui.edit.EditPersonForm;
import ui.edit.EditRolForm;

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
    }


    private Option[] createOptions(){
        Option option1 = new Option(Usuarios_Button, usersLabel, icon_user, "/img/option.png",
                "/img/usability-edit.png", "/img/usability-edit-selected.png");
        Option option2 = new Option(Roles_Button, rolesLabel, icon_roles, "/img/option.png",
                "/img/roles-edit.png", "/img/diamond.png");
        Option option3 = new Option(Personas_Button, peopleLabel, icon_people, "/img/option.png",
                "/img/people-edit.png","/img/people-edit-selected.png");
        Option option4 = new Option(Relaciones_Button, RelationshipsLabel, icon_relation, "/img/option.png",
                        "/img/link-edit.png","/img/link-edit-selected.png");
        return new Option[]{option1, option2, option3, option4};
    }

    //Metodos de Rol

    private void initRolesDashboard(){
        this.TotalRoles.setText(String.valueOf(controller.totalRoles()));
        this.ActiveRoles.setText(String.valueOf(controller.activeRoles()));
    }

    private void initRolesTable() {
        RolesTable = new JTable(){
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 2) { //Sport column
                    tip = getValueAt(rowIndex, colIndex).toString();
                }
                return tip;
            }
        };
        fetchRoles();
    }

    public void fetchRoles(){
        updateRolesTable(this.controller.getRoles());
    }

    private void updateRolesTable(String[][] newData){
        RolesTable.setModel(new DefaultTableModel(
                newData,
                new String[] {"ID Rol","Nombre", "Descripci\u00f3n", " ", " "})
        {   @Override
        public boolean isCellEditable(int row, int column) {
            return column == 3 || column == 4;
        }});
        ButtonColumn editButton = new ButtonColumn(RolesTable,this, "/img/edit_button.png", new EditRoleAction(),3);
        ButtonColumn deleteButton = new ButtonColumn(RolesTable,this, "/img/delete_button.png", new DeleteRoleAction(),3);
        RolesTable.setRowHeight(35);
        RolesTable.getColumnModel().getColumn(3).setCellRenderer(editButton);
        RolesTable.getColumnModel().getColumn(3).setCellEditor(editButton);
        RolesTable.getColumnModel().getColumn(4).setCellEditor(deleteButton);
        RolesTable.getColumnModel().getColumn(4).setCellRenderer(deleteButton);
        RolesTable.getColumnModel().getColumn(0).setMaxWidth(50);
        RolesTable.getColumnModel().getColumn(3).setMaxWidth(32);
        RolesTable.getColumnModel().getColumn(4).setMaxWidth(32);
        RolesTable.setShowGrid(false);
        TableCellRenderer baseRenderer = RolesTable.getTableHeader().getDefaultRenderer();
        RolesTable.getTableHeader().setDefaultRenderer(new TableHeaderRender(baseRenderer));
        scrollTable.setBorder(new LineBorder(new Color(0,0,0,0)));
        scrollTable.setViewportView(RolesTable);
        roles_history.setListData(controller.getRoleLog());
        roles_history.setCellRenderer(new RolesHistoryListRenderer());
    }

    public void editRole(int row){
        String id_edit = String.valueOf(RolesTable.getValueAt(row, 0));
        String role_name = String.valueOf(RolesTable.getValueAt(row, 1));
        String role_desc = String.valueOf(RolesTable.getValueAt(row,2));
        this.disable();
        EditRolForm edit_screen = new EditRolForm(this, controller, id_edit, role_name, role_desc);
        edit_screen.setVisible(true);
    }

    public void deleteRole(int row){
        String id_edit = String.valueOf(RolesTable.getValueAt(row, 0));
        controller.deleteModel(id_edit,"Rol");
        fetchRoles();
    }

    //Metodos de Person

    private void initPersonDashboard(){
        this.TotalPersons.setText(String.valueOf(controller.totalPersons()));
    }

    private void initPersonTable() {
        String[][] person_info = this.controller.getPersonas();
        PersonTable = new JTable(){
            /*   public TableCellRenderer getCellRenderer( int row, int column ) {
                   return new TableButtonRender();
               }*/
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 2) { //Sport column
                    tip = getValueAt(rowIndex, colIndex).toString();
                }
                return tip;
            }
        };
        PersonTable.setModel(new DefaultTableModel(
                person_info,
                new String[] {"CI","Nombre", "Direccion", " ", " "})
        {   @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        });
        PersonTable.setRowHeight(35);
        PersonTable.getColumnModel().getColumn(0).setMaxWidth(50);
        PersonTable.getColumnModel().getColumn(3).setMaxWidth(32);
        PersonTable.getColumnModel().getColumn(4).setMaxWidth(32);
        PersonTable.setShowGrid(false);
        TableCellRenderer baseRenderer = PersonTable.getTableHeader().getDefaultRenderer();
        PersonTable.getTableHeader().setDefaultRenderer(new TableHeaderRender(baseRenderer));
        PersonTable.setCellSelectionEnabled(false);
        scrollTable3.setBorder(new LineBorder(new Color(0,0,0,0)));
        scrollTable3.setViewportView(PersonTable);
        fetchPersons();
    }

    public void fetchPersons(){
        updatePersonTable(this.controller.getPersonas());
    }

    public void editPerson(int row){
        String id_edit = String.valueOf(PersonTable.getValueAt(row, 0));
        String person_name = String.valueOf(PersonTable.getValueAt(row, 1));
        String person_direccion = String.valueOf(PersonTable.getValueAt(row,2));
        String telefono = String.valueOf(PersonTable.getValueAt(row,3));
        this.disable();
        EditPersonForm edit_screen = new EditPersonForm(this, controller,person_name,telefono,person_direccion,id_edit);
        edit_screen.setVisible(true);
    }

    public void deletePerson(int row){
        String id_edit = String.valueOf(PersonTable.getValueAt(row, 0));
        controller.deleteModel(id_edit,"persona");
        fetchPersons();
    }

    private void updatePersonTable(String[][] newData){
        PersonTable.setModel(new DefaultTableModel(
                newData,
                new String[] {"CI","Nombre", "Direcci\u00f3n", " ", " "})
        {   @Override
        public boolean isCellEditable(int row, int column) {
            return column == 3 || column == 4;
        }});
        ButtonColumn editButton = new ButtonColumn(PersonTable,this, "/img/edit_button.png", new EditPersonAction(),3);
        ButtonColumn deleteButton = new ButtonColumn(PersonTable,this, "/img/delete_button.png", new DeletePersonAction(),3);
        PersonTable.setRowHeight(35);
        PersonTable.getColumnModel().getColumn(3).setCellRenderer(editButton);
        PersonTable.getColumnModel().getColumn(3).setCellEditor(editButton);
        PersonTable.getColumnModel().getColumn(4).setCellEditor(deleteButton);
        PersonTable.getColumnModel().getColumn(4).setCellRenderer(deleteButton);
        PersonTable.getColumnModel().getColumn(0).setMaxWidth(50);
        PersonTable.getColumnModel().getColumn(3).setMaxWidth(32);
        PersonTable.getColumnModel().getColumn(4).setMaxWidth(32);
        PersonTable.setShowGrid(false);
        TableCellRenderer baseRenderer = PersonTable.getTableHeader().getDefaultRenderer();
        PersonTable.getTableHeader().setDefaultRenderer(new TableHeaderRender(baseRenderer));
        PersonTable.setBorder(new LineBorder(new Color(0,0,0,0)));
        scrollTable3.setViewportView(PersonTable);
    }

    //Metodos de User

    private void initUserDashboard(){
        this.TotalUsuarios.setText(String.valueOf(controller.totalUsers()));
        this.UsuariosBloqueado.setText(String.valueOf(controller.bloquedUsers()));
    }

    private void initUserTable() {
        String[][] user_info = this.controller.getUsuarios();
        UserTable = new JTable(){
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 2) { //Sport column
                    tip = getValueAt(rowIndex, colIndex).toString();
                }
                return tip;
            }
        };
        UserTable.setModel(new DefaultTableModel(
                user_info,
                new String[] {"ID user","Nombre", "CI", " ", " "})
        {   @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        });
        UserTable.setRowHeight(35);
        UserTable.getColumnModel().getColumn(0).setMaxWidth(50);
        UserTable.getColumnModel().getColumn(3).setMaxWidth(32);
        UserTable.getColumnModel().getColumn(4).setMaxWidth(32);
        UserTable.setShowGrid(false);
        TableCellRenderer baseRenderer = UserTable.getTableHeader().getDefaultRenderer();
        UserTable.getTableHeader().setDefaultRenderer(new TableHeaderRender(baseRenderer));
        UserTable.setCellSelectionEnabled(false);
        scrollTable2.setBorder(new LineBorder(new Color(0,0,0,0)));
        scrollTable2.setViewportView(UserTable);
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
        updateOptionState(Usuarios_Button, true);
    }

    private void optionBack_1MouseExited(MouseEvent e) {
        updateOptionState(Usuarios_Button, false);
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
        updateOptionState(Roles_Button, true);
    }

    private void optionBack_2MouseExited(MouseEvent e) {
        updateOptionState(Roles_Button, false);
    }

    private void optionBack_3MouseEntered(MouseEvent e) {
        updateOptionState(Personas_Button, true);
    }

    private void optionBack_3MouseExited(MouseEvent e) {
        updateOptionState(Personas_Button, false);
    }

    private void optionBack_4MouseEntered(MouseEvent e) {
        updateOptionState(Relaciones_Button, true);
    }

    private void optionBack_4MouseExited(MouseEvent e) {
        updateOptionState(Relaciones_Button, false);
    }

    private void optionBack_2MouseClicked(MouseEvent e) {
        resetOptions();
        options[1].select();
        changeCard("roles");
        initRolesTable();
        initRolesDashboard();
        selected_option_button = Roles_Button;
    }

    private void optionBack_1MouseClicked(MouseEvent e) {
        resetOptions();
        options[0].select();
        changeCard("usuarios");
        initUserTable();
        initUserDashboard();
        selected_option_button = Usuarios_Button;
    }

    private void optionBack_3MouseClicked(MouseEvent e) {
        resetOptions();
        options[2].select();
        changeCard("personas");
        initPersonTable();
        initPersonDashboard();
        selected_option_button = Personas_Button;
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
        selected_option_button = Relaciones_Button;
    }

    private void add_buttonMouseEntered(MouseEvent e) {
        add_button_roles.setIcon(new ImageIcon(getClass().getResource("/img/add_button_pressed.png")));
    }

    private void add_buttonMouseExited(MouseEvent e) {
        add_button_roles.setIcon(new ImageIcon(getClass().getResource("/img/add_button.png")));
    }

    private void add_buttonMouseClicked(MouseEvent e) {
        JFrame addRoleFrame = new CreateRolForm(controller, this);
        this.disable();
        addRoleFrame.setVisible(true);
    }

    private void add_button_usuariosMouseEntered() {
        add_button_usuarios.setIcon(new ImageIcon(getClass().getResource("/img/add_button_pressed.png")));
    }

    private void add_button_usuariosMouseClicked() {
        JFrame addRoleFrame = new CreateRolForm(controller, this);
        this.disable();
        addRoleFrame.setVisible(true);
    }

    private void add_button_usuariosMouseExited() {
        add_button_usuarios.setIcon(new ImageIcon(getClass().getResource("/img/add_button.png")));
    }

    private void add_button_personasMouseClicked() {
        JFrame addRoleFrame = new CreatePersonForm(controller, this);
        this.disable();
        addRoleFrame.setVisible(true);
    }

    private void add_button_personasMouseEntered() {
        add_button_usuarios.setIcon(new ImageIcon(getClass().getResource("/img/add_button_pressed.png")));
    }

    private void add_button_personasMouseExited() {
        add_button_roles.setIcon(new ImageIcon(getClass().getResource("/img/add_button.png")));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel Header;
    private JLabel exit;
    private JPanel main;
    private JPanel Welcome;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
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
    private JLabel add_button_roles;
    private JLabel label1;
    private JScrollPane scrollTable;
    private JTable RolesTable;
    private JLabel roles_list_back;
    private JLayeredPane layeredPane7;
    private JLabel label11;
    private JScrollPane scrollPane1;
    private JList roles_history;
    private JLabel log_back;
    private JPanel Usuarios;
    private JLabel UserTitle;
    private JLabel UserDesc;
    private JLayeredPane layeredPane3;
    private JLabel TotalUsuarios;
    private JLabel UsuariosBloqueado;
    private JLabel total_title2;
    private JLabel boqued_title2;
    private JLabel dashboard_back2;
    private JLayeredPane layeredPane4;
    private JLabel add_button_usuarios;
    private JLabel label2;
    private JScrollPane scrollTable2;
    private JTable UserTable;
    private JLabel roles_list_back2;
    private JPanel Personas;
    private JLabel PersonTitle;
    private JLabel PersonDesc;
    private JLayeredPane layeredPane5;
    private JLabel TotalPersons;
    private JLabel total_title3;
    private JLabel dashboard_back3;
    private JLayeredPane layeredPane6;
    private JLabel add_button_personas;
    private JLabel label3;
    private JScrollPane scrollTable3;
    private JTable PersonTable;
    private JLabel roles_list_back3;
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
        Welcome = new JPanel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
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
        add_button_roles = new JLabel();
        label1 = new JLabel();
        scrollTable = new JScrollPane();
        RolesTable = new JTable();
        roles_list_back = new JLabel();
        layeredPane7 = new JLayeredPane();
        label11 = new JLabel();
        scrollPane1 = new JScrollPane();
        roles_history = new JList();
        log_back = new JLabel();
        Usuarios = new JPanel();
        UserTitle = new JLabel();
        UserDesc = new JLabel();
        layeredPane3 = new JLayeredPane();
        TotalUsuarios = new JLabel();
        UsuariosBloqueado = new JLabel();
        total_title2 = new JLabel();
        boqued_title2 = new JLabel();
        dashboard_back2 = new JLabel();
        layeredPane4 = new JLayeredPane();
        add_button_usuarios = new JLabel();
        label2 = new JLabel();
        scrollTable2 = new JScrollPane();
        UserTable = new JTable();
        roles_list_back2 = new JLabel();
        Personas = new JPanel();
        PersonTitle = new JLabel();
        PersonDesc = new JLabel();
        layeredPane5 = new JLayeredPane();
        TotalPersons = new JLabel();
        total_title3 = new JLabel();
        dashboard_back3 = new JLabel();
        layeredPane6 = new JLayeredPane();
        add_button_personas = new JLabel();
        label3 = new JLabel();
        scrollTable3 = new JScrollPane();
        PersonTable = new JTable();
        roles_list_back3 = new JLabel();
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
                        .addGap(20, 20, 20))
            );
            HeaderLayout.setVerticalGroup(
                HeaderLayout.createParallelGroup()
                    .addComponent(exit, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            );
        }

        //======== main ========
        {
            main.setBackground(new Color(244, 244, 244));
            main.setLayout(new CardLayout());

            //======== Welcome ========
            {
                Welcome.setBackground(Color.white);

                //---- label4 ----
                label4.setText("Bienvenido!");
                label4.setForeground(Color.gray);
                label4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 40));
                label4.setHorizontalAlignment(SwingConstants.CENTER);

                //---- label5 ----
                label5.setText("Esta es la aplicaci\u00f3n oficial");
                label5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                label5.setForeground(Color.gray);

                //---- label6 ----
                label6.setText("Es");
                label6.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                label6.setForeground(Color.gray);

                //---- label7 ----
                label7.setText("r\u00e1pida");
                label7.setFont(new Font("Segoe UI", Font.BOLD, 15));
                label7.setForeground(Color.gray);

                //---- label8 ----
                label8.setText("y");
                label8.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                label8.setForeground(Color.gray);

                //---- label9 ----
                label9.setText("segura");
                label9.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label9.setForeground(Color.gray);

                //---- label10 ----
                label10.setText("Wise Admin");
                label10.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
                label10.setForeground(new Color(50, 182, 195));

                GroupLayout WelcomeLayout = new GroupLayout(Welcome);
                Welcome.setLayout(WelcomeLayout);
                WelcomeLayout.setHorizontalGroup(
                    WelcomeLayout.createParallelGroup()
                        .addGroup(WelcomeLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(WelcomeLayout.createParallelGroup()
                                .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(WelcomeLayout.createSequentialGroup()
                                    .addGap(261, 261, 261)
                                    .addComponent(label5)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label10)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, WelcomeLayout.createSequentialGroup()
                                    .addGap(338, 338, 338)
                                    .addComponent(label6)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label7)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label8)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label9)
                                    .addGap(334, 334, 334)))
                            .addContainerGap())
                );
                WelcomeLayout.setVerticalGroup(
                    WelcomeLayout.createParallelGroup()
                        .addGroup(WelcomeLayout.createSequentialGroup()
                            .addGap(168, 168, 168)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(WelcomeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label10, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addGroup(WelcomeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label7)
                                .addComponent(label6)
                                .addComponent(label8)
                                .addComponent(label9))
                            .addContainerGap(255, Short.MAX_VALUE))
                );
            }
            main.add(Welcome, "welcome");

            //======== Roles ========
            {
                Roles.setBackground(new Color(245, 245, 245));
                Roles.setForeground(new Color(185, 185, 185));

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

                    //---- add_button_roles ----
                    add_button_roles.setIcon(new ImageIcon(getClass().getResource("/img/add_button.png")));
                    add_button_roles.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            add_buttonMouseClicked(e);
                        }
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            add_buttonMouseEntered(e);
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            add_buttonMouseExited(e);
                        }
                    });
                    layeredPane2.add(add_button_roles, JLayeredPane.DEFAULT_LAYER);
                    add_button_roles.setBounds(220, 10, 105, 35);

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
                    roles_list_back.setIcon(new ImageIcon(getClass().getResource("/img/rolesHeader.png")));
                    roles_list_back.setBackground(Color.white);
                    layeredPane2.add(roles_list_back, JLayeredPane.DEFAULT_LAYER);
                    roles_list_back.setBounds(0, 0, 355, 55);
                }

                //======== layeredPane7 ========
                {

                    //---- label11 ----
                    label11.setText("Historial de cambios");
                    label11.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
                    label11.setForeground(new Color(102, 102, 102));
                    layeredPane7.add(label11, JLayeredPane.DEFAULT_LAYER);
                    label11.setBounds(30, 10, 175, 31);

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setBorder(null);

                        //---- roles_history ----
                        roles_history.setBackground(Color.white);
                        roles_history.setBorder(null);
                        roles_history.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        roles_history.setVisibleRowCount(10);
                        scrollPane1.setViewportView(roles_history);
                    }
                    layeredPane7.add(scrollPane1, JLayeredPane.DEFAULT_LAYER);
                    scrollPane1.setBounds(10, 55, 275, 285);

                    //---- log_back ----
                    log_back.setIcon(new ImageIcon(getClass().getResource("/img/Log-back.png")));
                    layeredPane7.add(log_back, JLayeredPane.DEFAULT_LAYER);
                    log_back.setBounds(0, 0, 295, 350);
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
                                        .addGroup(RolesLayout.createSequentialGroup()
                                            .addComponent(RolesDesc, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(layeredPane7, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(RolesLayout.createSequentialGroup()
                                    .addGap(77, 77, 77)
                                    .addGroup(RolesLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(layeredPane1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                        .addComponent(layeredPane2, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))))
                            .addContainerGap(62, Short.MAX_VALUE))
                );
                RolesLayout.setVerticalGroup(
                    RolesLayout.createParallelGroup()
                        .addGroup(RolesLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(RolesTitle)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(RolesLayout.createParallelGroup()
                                .addGroup(RolesLayout.createSequentialGroup()
                                    .addComponent(RolesDesc)
                                    .addGap(18, 18, 18)
                                    .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(layeredPane2, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
                                .addComponent(layeredPane7, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(48, Short.MAX_VALUE))
                );
            }
            main.add(Roles, "roles");

            //======== Usuarios ========
            {
                Usuarios.setBackground(new Color(245, 245, 245));
                Usuarios.setForeground(new Color(185, 185, 185));

                //---- UserTitle ----
                UserTitle.setText("Usuarios");
                UserTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
                UserTitle.setBackground(new Color(64, 65, 65));
                UserTitle.setForeground(new Color(64, 65, 65));

                //---- UserDesc ----
                UserDesc.setText("Crea y administra los usuarios de tu aplicaci\u00f3n.");
                UserDesc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
                UserDesc.setForeground(new Color(126, 126, 126));

                //======== layeredPane3 ========
                {

                    //---- TotalUsuarios ----
                    TotalUsuarios.setText("24");
                    TotalUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 32));
                    TotalUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
                    TotalUsuarios.setForeground(new Color(52, 154, 162));
                    layeredPane3.add(TotalUsuarios, JLayeredPane.DEFAULT_LAYER);
                    TotalUsuarios.setBounds(60, 22, 70, 35);

                    //---- UsuariosBloqueado ----
                    UsuariosBloqueado.setText("16");
                    UsuariosBloqueado.setFont(new Font("Segoe UI", Font.BOLD, 32));
                    UsuariosBloqueado.setHorizontalAlignment(SwingConstants.CENTER);
                    UsuariosBloqueado.setForeground(new Color(52, 154, 162));
                    layeredPane3.add(UsuariosBloqueado, JLayeredPane.DEFAULT_LAYER);
                    UsuariosBloqueado.setBounds(210, 22, 70, 35);

                    //---- total_title2 ----
                    total_title2.setText("Usuarios creados");
                    total_title2.setHorizontalAlignment(SwingConstants.CENTER);
                    total_title2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
                    total_title2.setForeground(new Color(153, 153, 153));
                    layeredPane3.add(total_title2, JLayeredPane.DEFAULT_LAYER);
                    total_title2.setBounds(45, 65, 115, 16);

                    //---- boqued_title2 ----
                    boqued_title2.setText("Usuarios bloqueados");
                    boqued_title2.setHorizontalAlignment(SwingConstants.CENTER);
                    boqued_title2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
                    boqued_title2.setForeground(new Color(153, 153, 153));
                    layeredPane3.add(boqued_title2, JLayeredPane.DEFAULT_LAYER);
                    boqued_title2.setBounds(190, 65, 130, 16);

                    //---- dashboard_back2 ----
                    dashboard_back2.setIcon(new ImageIcon(getClass().getResource("/img/rolesHeader.png")));
                    layeredPane3.add(dashboard_back2, JLayeredPane.DEFAULT_LAYER);
                    dashboard_back2.setBounds(0, 0, 348, 105);
                }

                //======== layeredPane4 ========
                {

                    //---- add_button_usuarios ----
                    add_button_usuarios.setIcon(new ImageIcon(getClass().getResource("/img/add_button.png")));
                    add_button_usuarios.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            add_button_usuariosMouseClicked();
                        }
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            add_button_usuariosMouseEntered();
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            add_button_usuariosMouseExited();
                        }
                    });
                    layeredPane4.add(add_button_usuarios, JLayeredPane.DEFAULT_LAYER);
                    add_button_usuarios.setBounds(220, 10, 105, 35);

                    //---- label2 ----
                    label2.setText("Lista de Usuarios");
                    label2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
                    label2.setForeground(new Color(102, 102, 102));
                    layeredPane4.add(label2, JLayeredPane.DEFAULT_LAYER);
                    label2.setBounds(30, 12, 150, 31);

                    //======== scrollTable2 ========
                    {
                        scrollTable2.setBorder(null);

                        //---- UserTable ----
                        UserTable.setBackground(Color.white);
                        UserTable.setModel(new DefaultTableModel(
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
                        UserTable.setCellSelectionEnabled(true);
                        UserTable.setFillsViewportHeight(true);
                        UserTable.setGridColor(Color.white);
                        scrollTable2.setViewportView(UserTable);
                    }
                    layeredPane4.add(scrollTable2, JLayeredPane.DEFAULT_LAYER);
                    scrollTable2.setBounds(0, 55, 348, 175);

                    //---- roles_list_back2 ----
                    roles_list_back2.setIcon(new ImageIcon(getClass().getResource("/img/rolesHeader.png")));
                    roles_list_back2.setBackground(Color.white);
                    layeredPane4.add(roles_list_back2, JLayeredPane.DEFAULT_LAYER);
                    roles_list_back2.setBounds(0, 0, 355, 55);
                }

                GroupLayout UsuariosLayout = new GroupLayout(Usuarios);
                Usuarios.setLayout(UsuariosLayout);
                UsuariosLayout.setHorizontalGroup(
                    UsuariosLayout.createParallelGroup()
                        .addGroup(UsuariosLayout.createSequentialGroup()
                            .addGroup(UsuariosLayout.createParallelGroup()
                                .addGroup(UsuariosLayout.createSequentialGroup()
                                    .addGap(88, 88, 88)
                                    .addGroup(UsuariosLayout.createParallelGroup()
                                        .addComponent(UserTitle)
                                        .addComponent(UserDesc, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(UsuariosLayout.createSequentialGroup()
                                    .addGap(77, 77, 77)
                                    .addGroup(UsuariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(layeredPane3, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                        .addComponent(layeredPane4, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))))
                            .addContainerGap(363, Short.MAX_VALUE))
                );
                UsuariosLayout.setVerticalGroup(
                    UsuariosLayout.createParallelGroup()
                        .addGroup(UsuariosLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(UserTitle)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(UserDesc)
                            .addGap(18, 18, 18)
                            .addComponent(layeredPane3, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(layeredPane4, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(48, Short.MAX_VALUE))
                );
            }
            main.add(Usuarios, "usuarios");

            //======== Personas ========
            {
                Personas.setBackground(new Color(245, 245, 245));
                Personas.setForeground(new Color(185, 185, 185));

                //---- PersonTitle ----
                PersonTitle.setText("Personas");
                PersonTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
                PersonTitle.setBackground(new Color(64, 65, 65));
                PersonTitle.setForeground(new Color(64, 65, 65));

                //---- PersonDesc ----
                PersonDesc.setText("Crea y administra los personas de tu aplicaci\u00f3n.");
                PersonDesc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
                PersonDesc.setForeground(new Color(126, 126, 126));

                //======== layeredPane5 ========
                {

                    //---- TotalPersons ----
                    TotalPersons.setText("24");
                    TotalPersons.setFont(new Font("Segoe UI", Font.BOLD, 32));
                    TotalPersons.setHorizontalAlignment(SwingConstants.CENTER);
                    TotalPersons.setForeground(new Color(52, 154, 162));
                    layeredPane5.add(TotalPersons, JLayeredPane.DEFAULT_LAYER);
                    TotalPersons.setBounds(140, 20, 70, 35);

                    //---- total_title3 ----
                    total_title3.setText("Personas creadas");
                    total_title3.setHorizontalAlignment(SwingConstants.CENTER);
                    total_title3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
                    total_title3.setForeground(new Color(153, 153, 153));
                    layeredPane5.add(total_title3, JLayeredPane.DEFAULT_LAYER);
                    total_title3.setBounds(120, 65, 115, 16);

                    //---- dashboard_back3 ----
                    dashboard_back3.setIcon(new ImageIcon(getClass().getResource("/img/rolesHeader.png")));
                    layeredPane5.add(dashboard_back3, JLayeredPane.DEFAULT_LAYER);
                    dashboard_back3.setBounds(0, 0, 348, 105);
                }

                //======== layeredPane6 ========
                {

                    //---- add_button_personas ----
                    add_button_personas.setIcon(new ImageIcon(getClass().getResource("/img/add_button.png")));
                    add_button_personas.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            add_button_personasMouseClicked();
                        }
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            add_button_personasMouseEntered();
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            add_button_personasMouseExited();
                        }
                    });
                    layeredPane6.add(add_button_personas, JLayeredPane.DEFAULT_LAYER);
                    add_button_personas.setBounds(220, 10, 105, 35);

                    //---- label3 ----
                    label3.setText("Lista de Personas");
                    label3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
                    label3.setForeground(new Color(102, 102, 102));
                    layeredPane6.add(label3, JLayeredPane.DEFAULT_LAYER);
                    label3.setBounds(30, 12, 150, 31);

                    //======== scrollTable3 ========
                    {
                        scrollTable3.setBorder(null);

                        //---- PersonTable ----
                        PersonTable.setBackground(Color.white);
                        PersonTable.setModel(new DefaultTableModel(
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
                        PersonTable.setCellSelectionEnabled(true);
                        PersonTable.setFillsViewportHeight(true);
                        PersonTable.setGridColor(Color.white);
                        scrollTable3.setViewportView(PersonTable);
                    }
                    layeredPane6.add(scrollTable3, JLayeredPane.DEFAULT_LAYER);
                    scrollTable3.setBounds(0, 55, 348, 175);

                    //---- roles_list_back3 ----
                    roles_list_back3.setIcon(new ImageIcon(getClass().getResource("/img/rolesHeader.png")));
                    roles_list_back3.setBackground(Color.white);
                    layeredPane6.add(roles_list_back3, JLayeredPane.DEFAULT_LAYER);
                    roles_list_back3.setBounds(0, 0, 355, 55);
                }

                GroupLayout PersonasLayout = new GroupLayout(Personas);
                Personas.setLayout(PersonasLayout);
                PersonasLayout.setHorizontalGroup(
                    PersonasLayout.createParallelGroup()
                        .addGroup(PersonasLayout.createSequentialGroup()
                            .addGroup(PersonasLayout.createParallelGroup()
                                .addGroup(PersonasLayout.createSequentialGroup()
                                    .addGap(88, 88, 88)
                                    .addGroup(PersonasLayout.createParallelGroup()
                                        .addComponent(PersonTitle)
                                        .addComponent(PersonDesc, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(PersonasLayout.createSequentialGroup()
                                    .addGap(77, 77, 77)
                                    .addGroup(PersonasLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(layeredPane5, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                        .addComponent(layeredPane6, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))))
                            .addContainerGap(363, Short.MAX_VALUE))
                );
                PersonasLayout.setVerticalGroup(
                    PersonasLayout.createParallelGroup()
                        .addGroup(PersonasLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(PersonTitle)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(PersonDesc)
                            .addGap(18, 18, 18)
                            .addComponent(layeredPane5, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(layeredPane6, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(48, Short.MAX_VALUE))
                );
            }
            main.add(Personas, "personas");
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
            main_back.setBounds(0, 0, 205, 576);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(Nav, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(main, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(Header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(main, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
                .addComponent(Nav)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

}
