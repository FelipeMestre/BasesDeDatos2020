/*
 * Created by JFormDesigner on Tue Nov 03 14:04:54 UYT 2020
 */

package ui;

import actions.AddMenuToFunctionalityAction;
import actions.DeleteRoleAction;
import actions.EditRoleAction;
import org.ucu.bd.ButtonColumn;
import org.ucu.bd.ModelConstructor;
import org.ucu.bd.TableHeaderRender;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

/**
 * @author unknown
 */
public class SelectMenuForm extends JFrame {

    private ModelConstructor constructor;
    private MainMenu parent;
    private String[] menusToFunctionality;
    private String funcionalityID;

    public SelectMenuForm(MainMenu parent, ModelConstructor controller,String funcionalityID) {
        this.constructor = controller;
        this.parent = parent;
        this.funcionalityID = funcionalityID;
        initComponents();
        initMenusTable();
    }

    private void initMenusTable() {
        menus_table = new JTable() {
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 1) {
                    tip = getValueAt(rowIndex, colIndex).toString();
                }
                return tip;
            }
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        fetchMenus();
    }

    private void fetchMenus() {
        Object[][] menus = this.constructor.getMenusForFuncionality(this.funcionalityID);
        menus_table.setModel(new DefaultTableModel(
                menus,
                new String[]{"Nombre", "Descripci\u00f3n","ID"," "}));
        menus_table.setRowHeight(35);
        menus_table.getColumnModel().getColumn(0).setMaxWidth(150);
        menus_table.getColumnModel().getColumn(1).setMaxWidth(250);
        menus_table.getColumnModel().getColumn(2).setMaxWidth(20);
        menus_table.getColumnModel().getColumn(3).setMaxWidth(20);
        menus_table.setShowGrid(false);
        TableCellRenderer baseRenderer = menus_table.getTableHeader().getDefaultRenderer();
        menus_table.getTableHeader().setDefaultRenderer(new TableHeaderRender(baseRenderer));
        scrollMenus.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
        scrollMenus.setViewportView(menus_table);
    }

    public void terminar(){
        this.exitForm();
    }

    public String[] getMenusToFunctionality() {
        return menusToFunctionality;
    }

    private void SubmitActionPerformed() {
        
        exitForm();
    }

    private void CancelActionPerformed(ActionEvent e) {
        exitForm();
        parent.setEnabled(true);
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
        scrollMenus = new JScrollPane();
        menus_table = new JTable();
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
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
            border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER
            ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font
            .BOLD,12),java.awt.Color.red),panel1. getBorder()));panel1. addPropertyChangeListener(
            new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order"
            .equals(e.getPropertyName()))throw new RuntimeException();}});

            //======== scrollMenus ========
            {
                scrollMenus.setViewportView(menus_table);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(30, Short.MAX_VALUE)
                        .addComponent(scrollMenus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(scrollMenus, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
            );
        }

        //======== panel2 ========
        {
            panel2.setBackground(new Color(42, 58, 64));

            //---- Title ----
            Title.setText("Seleccione los menus que desea agregar");
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
            Submit.setText("Seleccionar");
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
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
                    .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollMenus;
    private JTable menus_table;
    private JPanel panel2;
    private JLabel Title;
    private JPanel panel3;
    private JButton Cancel;
    private JButton Submit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
