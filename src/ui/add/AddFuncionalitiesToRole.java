/*
 * Created by JFormDesigner on Sat Nov 07 18:40:53 UYT 2020
 */

package ui.add;

import model.CurrentUser;
import model.Role;
import org.ucu.bd.ModelConstructor;
import org.ucu.bd.TableHeaderRender;
import ui.MainMenu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

/**
 * @author unknown
 */
public class AddFuncionalitiesToRole extends JFrame {
    
    private ModelConstructor constructor;
    private MainMenu parent;
    private Role role;
    private LinkedList<String> addedUsers;

    public AddFuncionalitiesToRole(MainMenu parent, ModelConstructor controller, Role role) {
        this.constructor = controller;
        this.parent = parent;
        this.role = role;
        addedUsers = new LinkedList<>();
        initComponents();
        this.selectedRole.setText(role.toString());
        initializeUsersTable();

    }
    
    private void initializeUsersTable(){
        FuncionalitiesToRoleTable = new JTable(){
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        Object[][] users = constructor.getFuncionalitiesForRole(role);
        for (int i = 0; i < users.length; i++){
            if ((boolean)users[i][3]){
                addedUsers.add(String.valueOf(users[i][0]));
            }
        }

        FuncionalitiesToRoleTable.setModel(new DefaultTableModel(users, new String[]{"ID", "Nombre", "Descripcion",""}));
        TableCellRenderer baseRenderer = FuncionalitiesToRoleTable.getTableHeader().getDefaultRenderer();
        FuncionalitiesToRoleTable.getTableHeader().setDefaultRenderer(new TableHeaderRender(baseRenderer));
        FuncionalitiesToRoleTable.getColumnModel().getColumn(2).setMaxWidth(60);
        scrollPane1.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
        scrollPane1.setViewportView(FuncionalitiesToRoleTable);
    }
    private void CancelActionPerformed(ActionEvent e) {
        exitForm();
    }
    private void SubmitActionPerformed() {
        // Obtener seleccion
        String currentUserID = String.valueOf(CurrentUser.getCurrentUser().get_userId());
        for (int row = 0; row < FuncionalitiesToRoleTable.getRowCount(); row++){
            String id_funcionalidad = (String) FuncionalitiesToRoleTable.getValueAt(row, 0);
            boolean checked = (boolean) FuncionalitiesToRoleTable.getValueAt(row, 3);
            if (!addedUsers.contains(id_funcionalidad) && checked){
                this.constructor.addRelation(id_funcionalidad,"Funcionalidad",role.getId_role(),"Rol",currentUserID);
            }
            if(addedUsers.contains(id_funcionalidad) && !checked){
                this.constructor.removeRelation(id_funcionalidad,"Funcionalidad",role.getId_role(),"Rol",currentUserID);
            }
        }
        parent.fetchFuncionalityRole();
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
        panel3 = new JPanel();
        label1 = new JLabel();
        panel2 = new JPanel();
        Cancel = new JButton();
        Submit = new JButton();
        panel4 = new JPanel();
        selectedLabel = new JLabel();
        selectedRole = new JLabel();
        scrollPane1 = new JScrollPane();
        FuncionalitiesToRoleTable = new JTable();
        label2 = new JLabel();

        //======== this ========
        setUndecorated(true);
        var contentPane = getContentPane();

        //======== panel3 ========
        {
            panel3.setBackground(new Color(42, 58, 64));


            //---- label1 ----
            label1.setText("Asignar Funcionalidades a rol");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setForeground(Color.white);
            label1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))
            );
        }

        //======== panel2 ========
        {
            panel2.setBackground(new Color(203, 203, 203));

            //---- Cancel ----
            Cancel.setText("Cancelar");
            Cancel.setBorder(null);
            Cancel.setBackground(new Color(181, 181, 181));
            Cancel.setForeground(Color.white);
            Cancel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            Cancel.setBorderPainted(false);
            Cancel.addActionListener(e -> CancelActionPerformed(e));

            //---- Submit ----
            Submit.setText("Crear");
            Submit.setBorder(null);
            Submit.setBackground(new Color(42, 58, 64));
            Submit.setForeground(Color.white);
            Submit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            Submit.setBorderPainted(false);
            Submit.addActionListener(e -> SubmitActionPerformed());

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addContainerGap(307, Short.MAX_VALUE)
                        .addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Submit, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(Submit, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(10, Short.MAX_VALUE))
            );
        }

        //======== panel4 ========
        {
            panel4.setBackground(Color.white);

            //---- selectedLabel ----
            selectedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            //---- selectedRole ----
            selectedRole.setText("Placeholder");
            selectedRole.setForeground(new Color(153, 153, 153));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(FuncionalitiesToRoleTable);
            }

            //---- label2 ----
            label2.setText("Funcionalidad Seleccionada");
            label2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
            label2.setForeground(new Color(101, 102, 102));

            GroupLayout panel4Layout = new GroupLayout(panel4);
            panel4.setLayout(panel4Layout);
            panel4Layout.setHorizontalGroup(
                panel4Layout.createParallelGroup()
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addComponent(label2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectedLabel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
                            .addComponent(selectedRole, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(82, Short.MAX_VALUE))
            );
            panel4Layout.setVerticalGroup(
                panel4Layout.createParallelGroup()
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGroup(panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(selectedLabel))
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label2)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedRole)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(47, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(panel2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panel4, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, 0))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel3;
    private JLabel label1;
    private JPanel panel2;
    private JButton Cancel;
    private JButton Submit;
    private JPanel panel4;
    private JLabel selectedLabel;
    private JLabel selectedRole;
    private JScrollPane scrollPane1;
    private JTable FuncionalitiesToRoleTable;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
