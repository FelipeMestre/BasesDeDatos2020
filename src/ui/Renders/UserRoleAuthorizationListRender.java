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
        String roleName = request.getAttachedRoleName();
        JLabel roleName_label = new JLabel(roleName);
        roleName_label.setForeground(new Color(98,187,206));
        font = new Font("Segoe UI", Font.BOLD, 10);
        roleName_label.setFont(font);

        // Mostrar descripcion
        String description = "pide";
        JLabel description_label = new JLabel(description);
        description_label.setForeground(new Color(145,145,145));
        font = new Font("Segoe UI", Font.PLAIN, 10);
        description_label.setFont(font);

        // Mostrar a
        String a = "para";
        JLabel a_label = new JLabel(a);
        a_label.setForeground(new Color(145,145,145));
        a_label.setFont(font);

        // Mostrar usuarioRecibe
        String userName = request.getAttachedUserName();
        JLabel userName_label = new JLabel(userName);
        userName_label.setForeground(new Color(102,102,102));
        font = new Font("Segoe UI", Font.BOLD, 10);
        userName_label.setFont(font);

        GroupLayout panel1Layout = new GroupLayout(background);
        background.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(requester_label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(description_label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roleName_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userName_label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(requester_label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(description_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roleName_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(a_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(userName_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );

        background.add(requester_label);
        background.add(description_label);
        background.add(roleName_label);
        background.add(a_label);
        background.add(userName_label);
        return background;
    }
}
