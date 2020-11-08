package actions;

import ui.MainMenu;

import java.awt.event.ActionEvent;

public class ApproveRoleToUserAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.approveRoleToUser(Integer.parseInt(e.getActionCommand()));
    }
}
