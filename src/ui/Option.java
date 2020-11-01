package ui;

import javax.swing.*;
import java.awt.*;

public class Option {
    private JLabel background;
    private JLabel text;
    private JLabel icon;
    private String back_icon_path;
    private String icon_path;
    private String icon_path_selected;
    private final Color option_selected = new Color(59,230,246);
    private final Color option_unselected = Color.WHITE;

    public Option(JLabel background, JLabel text, JLabel icon, String back_icon_path, String icon_path, String icon_path_selected){
        this.back_icon_path = back_icon_path;
        this.background = background;
        this.text = text;
        this.icon = icon;
        this.icon_path = icon_path;
        this.icon_path_selected = icon_path_selected;
    }

    public void select(){
        this.background.setIcon(new ImageIcon(getClass().getResource(back_icon_path)));
        this.text.setForeground(option_selected);
        this.icon.setIcon(new ImageIcon(getClass().getResource(icon_path_selected)));
    }

    public void reset(){
        this.background.setIcon(null);
        this.text.setForeground(option_unselected);
        this.icon.setIcon(new ImageIcon(getClass().getResource(icon_path)));
    }
}
