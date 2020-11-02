package org.ucu.bd;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableButtonRender extends JPanel implements TableCellRenderer {
    private final String edit_Icon_Path =  "/img/edit_button.png";
    private final String delete_Icon_Path = "/img/delete_button.png";
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(Color.WHITE);
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        switch (column) {
            case 2:
                JLabel edit_button = new JLabel();
                edit_button.setIcon(new ImageIcon(getClass().getResource(edit_Icon_Path)));
                panel.add(edit_button);
            break;
            case 3:
                JLabel delete_icon = new JLabel();
                delete_icon.setIcon(new ImageIcon(getClass().getResource(delete_Icon_Path)));
                panel.setBackground(Color.WHITE);
                panel.add(delete_icon);
                break;
            case 1:
                JTextArea textArea;
                if (value == null){
                    textArea = new JTextArea("NULL");
                } else {
                    textArea = new JTextArea(value.toString());
                    panel.setToolTipText(value.toString());
                }
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setForeground(new Color(144,144,144));
                Font font = new Font("Segoe UI Semibold", Font.PLAIN, 12);
                textArea.setFont(font);
                textArea.setBorder(new EmptyBorder(0,0,0,0));
                panel.add(textArea);
                panel.setBackground(Color.WHITE);

                break;
            default:
                JLabel label;
                if (value == null){
                    label = new JLabel("NULL");
                } else {
                    label = new JLabel(value.toString());
                }
                label.setForeground(new Color(144,144,144));
                font = new Font("Segoe UI Semibold", Font.PLAIN, 12);
                label.setFont(font);
                panel.setBackground(Color.WHITE);
                panel.add(label);

                break;
        }
        this.add(panel);
        return this;
    }

}
