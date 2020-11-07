package actions;

import actions.ChangeAction;
import ui.MainMenu;

import java.awt.event.ActionEvent;

public class EditMenuAction extends ChangeAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.editMenu(Integer.parseInt(e.getActionCommand()));
    }
}
