package actions;

import ui.MainMenu;

import java.awt.event.ActionEvent;

public class EditFuncionalityAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.editFuncionality(Integer.parseInt(e.getActionCommand()));
    }
}
