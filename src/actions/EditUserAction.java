package actions;

import actions.ChangeAction;
import ui.MainMenu;

import java.awt.event.ActionEvent;

public class EditUserAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.editPerson(Integer.parseInt(e.getActionCommand()));
    }
}
