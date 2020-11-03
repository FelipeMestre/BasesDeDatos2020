package org.ucu.bd;

import ui.MainMenu;
import java.awt.event.ActionEvent;

public class EditRoleAction extends ChangeAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.editRole(Integer.parseInt(e.getActionCommand()));
    }
}
