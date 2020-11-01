package org.ucu.bd;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableButtonRender extends JPanel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(Color.WHITE);
        JPanel panel = new JPanel();

        switch (column) {
            case 2:
                JLabel edit_button = new JLabel("Edit");
                panel.setBackground(Color.WHITE);
                panel.add(edit_button);
                this.add(panel);
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
                this.add(panel);
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
                this.add(panel);
                break;
        }
        return this;
    }

}
