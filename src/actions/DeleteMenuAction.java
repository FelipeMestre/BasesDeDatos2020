package actions;

import ui.MainMenu;

import java.awt.event.ActionEvent;

public class DeleteMenuAction extends ChangeAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.deleteMenu(Integer.parseInt(e.getActionCommand()));
    }
}
