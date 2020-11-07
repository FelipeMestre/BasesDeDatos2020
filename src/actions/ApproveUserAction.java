package actions;

import ui.MainMenu;

import java.awt.event.ActionEvent;

public class ApproveUserAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.approveUser(Integer.parseInt(e.getActionCommand()));
    }
}
