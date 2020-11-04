package org.ucu.bd;

import ui.MainMenu;

import java.awt.event.ActionEvent;

public class EditPersonAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.editPerson(Integer.parseInt(e.getActionCommand()));
    }
}
