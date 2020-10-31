package ui;

import javax.swing.*;
import java.awt.*;

public class JCustomPanel extends JPanel {

    private String path;

    public JCustomPanel(String background_path){
        super();
        path = background_path;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image background = Toolkit.getDefaultToolkit().createImage(path);
        g.drawImage(background, 0, 0, null);
    }
}
