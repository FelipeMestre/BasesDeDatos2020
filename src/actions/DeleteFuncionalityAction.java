package actions;

import ui.MainMenu;

import java.awt.event.ActionEvent;

public class DeleteFuncionalityAction extends ChangeAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu frame = (MainMenu) (e.getSource());
        frame.deleteFuncionality(Integer.parseInt(e.getActionCommand()));
    }
}
