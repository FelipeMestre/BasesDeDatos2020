package actions;

import ui.MainMenu;

import java.awt.event.ActionEvent;

public class AddFuncionalityToRolAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.addFuncionalityToRolAction(Integer.parseInt(e.getActionCommand()));
    }
}

