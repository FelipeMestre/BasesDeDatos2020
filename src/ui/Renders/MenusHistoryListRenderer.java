package ui.Renders;

import model.Log_Menu;

import javax.swing.*;
import java.awt.*;

public class MenusHistoryListRenderer extends JPanel implements ListCellRenderer<Log_Menu> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Log_Menu> list, Log_Menu value, int index, boolean isSelected, boolean cellHasFocus) {
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
        String menu_type = value.getEventType();
        String description = null;
        switch (menu_type) {
            case "ADD":
                description = "ha creado el menu";
                break;
            case "EDIT":
                description = "ha editado el menu";
                break;
            case "DELETE":
                description = "ha eliminado el menu";
                break;
            default:
                break;
        }
        JLabel menu_description = new JLabel(description);
        menu_description.setForeground(new Color(89,89,89));
        font = new Font("Segoe UI", Font.PLAIN, 11);
        menu_description.setFont(font);

        // Mostrar Rol
        String menu = value.getMenu_name();
        JLabel menu_name = new JLabel(menu);
        menu_name.setForeground(new Color(102,102,102));
        font = new Font("Segoe UI", Font.BOLD, 11);
        menu_name.setFont(font);

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
                                                .addComponent(menu_description, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(menu_name, 90, 90, 90)
                                        ))
                                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(5)
                                .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(user_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(menu_description, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(menu_name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(rol_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5))
        );

        // Agregar labels al panel visible
        item_back.add(user_name);
        item_back.add(menu_description);
        item_back.add(menu_name);
        background.add(item_back);
        return background;
    }
}
