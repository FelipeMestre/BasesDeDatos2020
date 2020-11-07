/*
 * Created by JFormDesigner on Thu Oct 29 02:16:05 UYT 2020
 */

package ui;

import actions.*;
import org.ucu.bd.ButtonColumn;
import org.ucu.bd.ModelConstructor;
import org.ucu.bd.TableHeaderRender;
import ui.Renders.RolesHistoryListRenderer;
import ui.edit.EditRolForm;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author unknown
 */
public class PendentAutorizationsForm extends JFrame {

    private JFrame parent;
    private JLabel selected_option_button;
    private ModelConstructor controller;

    public PendentAutorizationsForm(JFrame parent, String username, ModelConstructor modelConstructor) {
        this.controller = modelConstructor;
        this.parent = parent;
    }

    //Metodo de tabla de autorizaciones
    private void initAutorizationsDashboard(){
        this.TotalAutorizations.setText(String.valueOf(controller.totalAutorizations()));
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
        controller.deleteModel(id_edit, "rol");
        fetchRoles();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        Header = new JPanel();
        main = new JPanel();
        Personas = new JPanel();
        PersonTitle = new JLabel();
        PersonDesc = new JLabel();
        layeredPane5 = new JLayeredPane();
        TotalAutorizations = new JLabel();
        total_title3 = new JLabel();
        dashboard_back3 = new JLabel();
        layeredPane6 = new JLayeredPane();
        label3 = new JLabel();
        scrollTable3 = new JScrollPane();
        AutorizationsTable = new JTable();
        roles_list_back3 = new JLabel();
        layeredPane8 = new JLayeredPane();
        historial_title2 = new JLabel();
        scrollHistoryPersons = new JScrollPane();
        autorizations_history = new JList();
        log_back_people = new JLabel();
        button1 = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(1024, 576));
        setUndecorated(true);
        var contentPane = getContentPane();

        //======== Header ========
        {
            Header.setBackground(Color.white);
            Header.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder (
            new javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn"
            , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
            , new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 )
            ,java . awt. Color .red ) ,Header. getBorder () ) ); Header. addPropertyChangeListener(
            new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
            ;} } );

            GroupLayout HeaderLayout = new GroupLayout(Header);
            Header.setLayout(HeaderLayout);
            HeaderLayout.setHorizontalGroup(
                HeaderLayout.createParallelGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
            );
            HeaderLayout.setVerticalGroup(
                HeaderLayout.createParallelGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
            );
        }

        //======== main ========
        {
            main.setBackground(new Color(244, 244, 244));
            main.setLayout(new CardLayout());

            //======== Personas ========
            {
                Personas.setBackground(new Color(245, 245, 245));
                Personas.setForeground(new Color(185, 185, 185));

                //---- PersonTitle ----
                PersonTitle.setText("Autorizaciones pendientes");
                PersonTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
                PersonTitle.setBackground(new Color(64, 65, 65));
                PersonTitle.setForeground(new Color(64, 65, 65));

                //---- PersonDesc ----
                PersonDesc.setText("Autoriza cambios hechos por otro administrador");
                PersonDesc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
                PersonDesc.setForeground(new Color(126, 126, 126));

                //======== layeredPane5 ========
                {

                    //---- TotalPersons ----
                    TotalAutorizations.setText("24");
                    TotalAutorizations.setFont(new Font("Segoe UI", Font.BOLD, 32));
                    TotalAutorizations.setHorizontalAlignment(SwingConstants.CENTER);
                    TotalAutorizations.setForeground(new Color(52, 154, 162));
                    layeredPane5.add(TotalAutorizations, JLayeredPane.DEFAULT_LAYER);
                    TotalAutorizations.setBounds(140, 20, 70, 35);

                    //---- total_title3 ----
                    total_title3.setText("Autorizaciones pendientes");
                    total_title3.setHorizontalAlignment(SwingConstants.CENTER);
                    total_title3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
                    total_title3.setForeground(new Color(153, 153, 153));
                    layeredPane5.add(total_title3, JLayeredPane.DEFAULT_LAYER);
                    total_title3.setBounds(105, 65, 175, 16);

                    //---- dashboard_back3 ----
                    dashboard_back3.setIcon(new ImageIcon(getClass().getResource("/img/rolesHeader.png")));
                    layeredPane5.add(dashboard_back3, JLayeredPane.DEFAULT_LAYER);
                    dashboard_back3.setBounds(0, 0, 348, 105);
                }

                //======== layeredPane6 ========
                {

                    //---- label3 ----
                    label3.setText("Autorizaciones Pendientes");
                    label3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
                    label3.setForeground(new Color(102, 102, 102));
                    layeredPane6.add(label3, JLayeredPane.DEFAULT_LAYER);
                    label3.setBounds(30, 12, 295, 31);

                    //======== scrollTable3 ========
                    {
                        scrollTable3.setBorder(null);

                        //---- AutorizationsTable ----
                        AutorizationsTable.setBackground(Color.white);
                        AutorizationsTable.setModel(new DefaultTableModel(
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
                        AutorizationsTable.setCellSelectionEnabled(true);
                        AutorizationsTable.setFillsViewportHeight(true);
                        AutorizationsTable.setGridColor(Color.white);
                        scrollTable3.setViewportView(AutorizationsTable);
                    }
                    layeredPane6.add(scrollTable3, JLayeredPane.DEFAULT_LAYER);
                    scrollTable3.setBounds(0, 55, 348, 175);

                    //---- roles_list_back3 ----
                    roles_list_back3.setIcon(new ImageIcon(getClass().getResource("/img/rolesHeader.png")));
                    roles_list_back3.setBackground(Color.white);
                    layeredPane6.add(roles_list_back3, JLayeredPane.DEFAULT_LAYER);
                    roles_list_back3.setBounds(0, 0, 355, 55);
                }

                //======== layeredPane8 ========
                {

                    //---- historial_title2 ----
                    historial_title2.setText("Historial de cambios");
                    historial_title2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
                    historial_title2.setForeground(new Color(102, 102, 102));
                    layeredPane8.add(historial_title2, JLayeredPane.DEFAULT_LAYER);
                    historial_title2.setBounds(30, 10, 175, 31);

                    //======== scrollHistoryPersons ========
                    {
                        scrollHistoryPersons.setBorder(null);

                        //---- autorizations_history ----
                        autorizations_history.setBackground(Color.white);
                        autorizations_history.setBorder(null);
                        autorizations_history.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        autorizations_history.setVisibleRowCount(10);
                        scrollHistoryPersons.setViewportView(autorizations_history);
                    }
                    layeredPane8.add(scrollHistoryPersons, JLayeredPane.DEFAULT_LAYER);
                    scrollHistoryPersons.setBounds(10, 55, 275, 285);

                    //---- log_back_people ----
                    log_back_people.setIcon(new ImageIcon(getClass().getResource("/img/Log-back.png")));
                    layeredPane8.add(log_back_people, JLayeredPane.DEFAULT_LAYER);
                    log_back_people.setBounds(0, 0, 295, 370);
                }

                //---- button1 ----
                button1.setText("OK");

                GroupLayout PersonasLayout = new GroupLayout(Personas);
                Personas.setLayout(PersonasLayout);
                PersonasLayout.setHorizontalGroup(
                    PersonasLayout.createParallelGroup()
                        .addGroup(PersonasLayout.createSequentialGroup()
                            .addGroup(PersonasLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(PersonasLayout.createSequentialGroup()
                                    .addGap(77, 77, 77)
                                    .addGroup(PersonasLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(layeredPane5, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                        .addComponent(layeredPane6, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button1))
                                .addGroup(PersonasLayout.createSequentialGroup()
                                    .addGap(88, 88, 88)
                                    .addGroup(PersonasLayout.createParallelGroup()
                                        .addComponent(PersonTitle)
                                        .addGroup(PersonasLayout.createSequentialGroup()
                                            .addComponent(PersonDesc, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(layeredPane8, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)))))
                            .addContainerGap(62, Short.MAX_VALUE))
                );
                PersonasLayout.setVerticalGroup(
                    PersonasLayout.createParallelGroup()
                        .addGroup(PersonasLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(PersonTitle)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PersonasLayout.createParallelGroup()
                                .addGroup(PersonasLayout.createSequentialGroup()
                                    .addComponent(PersonDesc)
                                    .addGap(18, 18, 18)
                                    .addComponent(layeredPane5, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(layeredPane6, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
                                .addGroup(PersonasLayout.createSequentialGroup()
                                    .addComponent(layeredPane8, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(button1)))
                            .addContainerGap(30, Short.MAX_VALUE))
                );
            }
            main.add(Personas, "personas");
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(203, 203, 203)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(main, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(Header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 36, Short.MAX_VALUE)
                    .addComponent(main, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel Header;
    private JPanel main;
    private JPanel Personas;
    private JLabel PersonTitle;
    private JLabel PersonDesc;
    private JLayeredPane layeredPane5;
    private JLabel TotalAutorizations;
    private JLabel total_title3;
    private JLabel dashboard_back3;
    private JLayeredPane layeredPane6;
    private JLabel label3;
    private JScrollPane scrollTable3;
    private JTable AutorizationsTable;
    private JLabel roles_list_back3;
    private JLayeredPane layeredPane8;
    private JLabel historial_title2;
    private JScrollPane scrollHistoryPersons;
    private JList autorizations_history;
    private JLabel log_back_people;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
