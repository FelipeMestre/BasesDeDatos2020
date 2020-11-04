package actions;

import actions.ChangeAction;
import ui.MainMenu;

import java.awt.event.ActionEvent;

public class DeleteUserAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.deletePerson(Integer.parseInt(e.getActionCommand()));
    }
}
