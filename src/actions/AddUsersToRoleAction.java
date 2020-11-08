package actions;

import ui.MainMenu;

import java.awt.event.ActionEvent;

public class AddUsersToRoleAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.addUsersToRole(Integer.parseInt(e.getActionCommand()));
    }
}
