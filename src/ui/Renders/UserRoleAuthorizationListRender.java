package ui.Renders;

import model.AddRoleUserRequest;
import model.NewUserRequest;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class UserRoleAuthorizationListRender extends JPanel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JPanel background = new JPanel();
        AddRoleUserRequest request = (AddRoleUserRequest)value;
        background.setBackground(Color.WHITE);

        // Mostrar solicitante
        String requester = request.getRequesterName();
        JLabel requester_label = new JLabel(requester);
        requester_label.setForeground(new Color(102,102,102));
        Font font = new Font("Segoe UI", Font.BOLD, 10);
        requester_label.setFont(font);

        // Mostrar usuarioACrear
        String newUserName = request.getAttachedRoleName();
        JLabel newUserName_label = new JLabel(newUserName);
        newUserName_label.setForeground(new Color(98,187,206));
        font = new Font("Segoe UI", Font.BOLD, 10);
        newUserName_label.setFont(font);

        // Mostrar descripcion
        String description = "pide asignar el rol";
        JLabel description_label = new JLabel(description);
        description_label.setForeground(new Color(145,145,145));
        font = new Font("Segoe UI", Font.PLAIN, 10);
        description_label.setFont(font);

        JLabel approve_icon = new JLabel();
        approve_icon.setIcon(new ImageIcon(getClass().getResource("/img/approval_edit.png")));


        GroupLayout panel1Layout = new GroupLayout(background);
        background.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(requester_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(description_label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newUserName_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(requester_label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(description_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(newUserName_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );

        background.add(requester_label);
        background.add(description_label);
        background.add(newUserName_label);
        return background;
    }
}
