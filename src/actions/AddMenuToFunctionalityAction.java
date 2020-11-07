package actions;

import ui.MainMenu;

import java.awt.event.ActionEvent;

public class AddMenuToFunctionalityAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.addMenuToFunctionality(Integer.parseInt(e.getActionCommand()));
    }
}
