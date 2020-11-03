package org.ucu.bd;

import ui.MainMenu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class ButtonColumn extends AbstractCellEditor
        implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener
{
    private JTable table;
    private Action action;
    private MainMenu parent;
    private int mnemonic;
    private Border originalBorder;
    private Border focusBorder;
    private String iconPath;

    private JButton renderButton;
    private JButton editButton;
    private Object editorValue;
    private boolean isButtonColumnEditor;

    public ButtonColumn(JTable table, MainMenu parent, String iconPath, Action action, int column)
    {
        this.table = table;
        this.action = action;
        this.parent = parent;
        this.iconPath = iconPath;

        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted( false );
        editButton.addActionListener( this );
        originalBorder = editButton.getBorder();
        setFocusBorder( new LineBorder(Color.BLUE) );

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
        table.addMouseListener( this );
    }


    /**
     *  Get foreground color of the button when the cell has focus
     *
     *  @return the foreground color
     */
    public Border getFocusBorder()
    {
        return focusBorder;
    }

    /**
     *  The foreground color of the button when the cell has focus
     *
     *  @param focusBorder the foreground color
     */
    public void setFocusBorder(Border focusBorder)
    {
        this.focusBorder = focusBorder;
        editButton.setBorder( focusBorder );
    }

    public int getMnemonic()
    {
        return mnemonic;
    }

    /**
     *  The mnemonic to activate the button when the cell has focus
     *
     *  @param mnemonic the mnemonic
     */
    public void setMnemonic(int mnemonic)
    {
        this.mnemonic = mnemonic;
        renderButton.setMnemonic(mnemonic);
        editButton.setMnemonic(mnemonic);
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
    {
        if (value == null)
        {
            editButton.setText( "" );
            editButton.setIcon( null );
        }
        else if (value instanceof Icon)
        {
            editButton.setText( "" );
            editButton.setIcon( (Icon)value );
        }
        else
        {
            editButton.setText( value.toString() );
            editButton.setIcon( null );
        }

        this.editorValue = value;
        return editButton;
    }

    @Override
    public Object getCellEditorValue()
    {
        return editorValue;
    }

    //
//  Implement TableCellRenderer interface
//
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        renderButton.setBorder(null);
        renderButton.setBackground(Color.WHITE);
        renderButton.setIcon(new ImageIcon(getClass().getResource(iconPath)));

        return renderButton;
    }

    //
//  Implement ActionListener interface
//
    /*
     *	The button has been pressed. Stop editing and invoke the custom Action
     */
    public void actionPerformed(ActionEvent e)
    {
        int row = table.convertRowIndexToModel( table.getEditingRow() );
        fireEditingStopped();

        //  Invoke the Action
        ActionEvent event = new ActionEvent(
                parent,
                ActionEvent.ACTION_PERFORMED,
                "" + row);
        action.actionPerformed(event);
    }

    //
//  Implement MouseListener interface
//
    /*
     *  When the mouse is pressed the editor is invoked. If you then then drag
     *  the mouse to another cell before releasing it, the editor is still
     *  active. Make sure editing is stopped when the mouse is released.
     */
    public void mousePressed(MouseEvent e)
    {
        if (table.isEditing()
                &&  table.getCellEditor() == this)
            isButtonColumnEditor = true;
    }

    public void mouseReleased(MouseEvent e)
    {
        if (isButtonColumnEditor
                &&  table.isEditing())
            table.getCellEditor().stopCellEditing();

        isButtonColumnEditor = false;
    }

    public void mouseClicked(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
