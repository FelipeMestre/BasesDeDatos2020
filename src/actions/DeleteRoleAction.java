package actions;

import ui.MainMenu;
import java.awt.event.ActionEvent;

public class DeleteRoleAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.deleteRole(Integer.parseInt(e.getActionCommand()));
    }
}
