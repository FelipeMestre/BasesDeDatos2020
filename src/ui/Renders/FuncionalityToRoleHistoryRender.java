package ui.Renders;

import model.Log_FuncionalityToRol;

import javax.swing.*;
import java.awt.*;

public class FuncionalityToRoleHistoryRender extends JPanel implements ListCellRenderer<Log_FuncionalityToRol> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Log_FuncionalityToRol> list, Log_FuncionalityToRol value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel background = new JPanel();
        background.setBackground(Color.WHITE);
        JPanel item_back = new JPanel();
        item_back.setBackground(new Color(243,243,243));

        GroupLayout layout = new GroupLayout(background);
        background.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item_back, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(item_back, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap(2,2)
                        ));

        // Mostrar usuario creador
        String user = value.getCreator_name();
        JLabel user_name = new JLabel(user);
        user_name.setForeground(new Color(56,154,180));
        Font font = new Font("Segoe UI", Font.BOLD, 12);
        user_name.setFont(font);

        // Mostrar descripcion
        String funct_type = value.getEventType();
        String description = null;
        switch (funct_type) {
            case "1":
                description = "agregó la funcionalidad";
                break;
            case "2":
                description = "quitó la funcionalidad";
                break;
            default:
                break;
        }

        JLabel func_description = new JLabel(description);
        func_description.setForeground(new Color(89,89,89));
        font = new Font("Segoe UI", Font.PLAIN, 11);
        func_description.setFont(font);

        // Mostrar Rol
        String role = value.getRole_name();
        JLabel role_name = new JLabel(role);
        role_name.setForeground(new Color(102,102,102));
        font = new Font("Segoe UI", Font.BOLD, 11);
        role_name.setFont(font);

        //Mostrar funcionalidad
        String funcionalidad = value.getFuncionalidad_name();
        JLabel funcionalidad_name = new JLabel(funcionalidad);
        funcionalidad_name.setForeground(new Color(102,102,102));
        font = new Font("Segoe UI", Font.BOLD, 11);
        funcionalidad_name.setFont(font);

        // Mostrar de
        JLabel de_label = new JLabel("de");
        de_label.setForeground(new Color(89,89,89));
        font = new Font("Segoe UI", Font.PLAIN, 11);
        de_label.setFont(font);


        // Mostrar fecha
        String fecha = value.getCreate_date();
        JLabel rol_date = new JLabel(fecha);
        font = new Font("Segoe UI Semibold", Font.PLAIN, 10);
        rol_date.setFont(font);
        rol_date.setForeground(new Color(178,178,178));

        GroupLayout panel1Layout = new GroupLayout(item_back);
        item_back.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(rol_date, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(user_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(func_description, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(funcionalidad_name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(de_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(role_name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                                        ))
                                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(5)
                                .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(user_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(func_description, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(funcionalidad_name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(de_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(role_name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(rol_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5))
        );

        // Agregar labels al panel visible
        item_back.add(user_name);
        item_back.add(func_description);
        item_back.add(funcionalidad_name);
        item_back.add(role_name);
        item_back.add(de_label);
        background.add(item_back);
        return background;
    }

}

