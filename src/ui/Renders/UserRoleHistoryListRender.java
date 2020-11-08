package ui.Renders;

import model.Log_Role_User;

import javax.swing.*;
import java.awt.*;

public class UserRoleHistoryListRender extends JPanel implements ListCellRenderer<Log_Role_User> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Log_Role_User> list, Log_Role_User value, int index, boolean isSelected, boolean cellHasFocus) {
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
                                .addComponent(item_back, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        String eventType = value.getEventType();
        String description = null;
        switch (eventType) {
            case "ADD":
                description = "asignó";
                break;
            case "DELETE":
                description = "revocó";
                break;
            case "APPROVE":
                description = "aprobó";
            default:
                break;
        }
        JLabel user_descripcion = new JLabel(description);
        user_descripcion.setForeground(new Color(89,89,89));
        font = new Font("Segoe UI", Font.PLAIN, 11);
        user_descripcion.setFont(font);

        // Mostrar usuario afectado por el cambio
        String user_C = value.getUserAttached();
        JLabel actioned_user = new JLabel(user_C);
        actioned_user.setForeground(new Color(102,102,102));
        font = new Font("Segoe UI", Font.BOLD, 11);
        actioned_user.setFont(font);

        // Mostrar rol afectado por el cambio
        String rol = value.getRoleAttached();
        JLabel actioned_rol = new JLabel(rol);
        actioned_rol.setForeground(new Color(56,154,180));
        font = new Font("Segoe UI", Font.BOLD, 11);
        actioned_rol.setFont(font);

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
                                        .addComponent(rol_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(user_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(user_descripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(actioned_rol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(actioned_user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap()
                                        ))
                                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(5)
                                .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(user_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(user_descripcion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(actioned_user, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(actioned_rol, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(rol_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5))
        );

        // Agregar labels al panel visible
        item_back.add(user_name);
        item_back.add(user_descripcion);
        item_back.add(actioned_rol);
        item_back.add(actioned_user);
        background.add(item_back);
        return background;
    }
}
