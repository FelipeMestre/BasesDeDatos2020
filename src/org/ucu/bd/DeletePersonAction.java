package org.ucu.bd;

import ui.MainMenu;

import java.awt.event.ActionEvent;

public class DeletePersonAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.deletePerson(Integer.parseInt(e.getActionCommand()));
    }
}
