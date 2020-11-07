package ui.Renders;

import model.Log_Role;

import javax.swing.*;
import java.awt.*;

public class RolesHistoryListRenderer extends JPanel implements ListCellRenderer<Log_Role> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Log_Role> list,  Log_Role value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel background = new JPanel();
        background.setBackground(Color.WHITE);
        JPanel item_back = new JPanel();
        item_back.setBackground(new Color(243,243,243));

        GroupLayout layout = new GroupLayout(background);
        background.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5)
                                .addComponent(item_back, 235,235,235)
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
        String rol_type = value.getEventType();
        String description = null;
        switch (rol_type) {
            case "ADD":
                description = "ha creado el rol";
                break;
            case "EDIT":
                description = "ha editado el rol";
                break;
            case "DELETE":
                description = "ha eliminado el rol";
                break;
            default:
                break;
        }
        JLabel rol_description = new JLabel(description);
        rol_description.setForeground(new Color(89,89,89));
        font = new Font("Segoe UI", Font.PLAIN, 11);
        rol_description.setFont(font);

        // Mostrar Rol
        String rol = value.getRole_name();
        JLabel rol_name = new JLabel(rol);
        rol_name.setForeground(new Color(102,102,102));
        font = new Font("Segoe UI", Font.BOLD, 11);
        rol_name.setFont(font);

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
                                                .addComponent(rol_description, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rol_name, 90, 90, 90)
                                               ))
                                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(5)
                                .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(user_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rol_description, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rol_name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(rol_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(5))
        );

        // Agregar labels al panel visible
        item_back.add(user_name);
        item_back.add(rol_description);
        item_back.add(rol_name);
        background.add(item_back);
        return background;
    }

}
