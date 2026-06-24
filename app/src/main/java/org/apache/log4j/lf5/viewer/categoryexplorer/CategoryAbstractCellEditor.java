package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;

public class CategoryAbstractCellEditor implements TableCellEditor, TreeCellEditor {
    static Class class$javax$swing$event$CellEditorListener;
    protected Object _value;
    protected EventListenerList _listenerList = new EventListenerList();
    protected ChangeEvent _changeEvent = null;
    protected int _clickCountToStart = 1;

    public Component getTableCellEditorComponent(JTable jTable, Object obj, boolean z, int i, int i2) {
        return null;
    }

    public Component getTreeCellEditorComponent(JTree jTree, Object obj, boolean z, boolean z2, boolean z3, int i) {
        return null;
    }

    public Object getCellEditorValue() {
        return this._value;
    }

    public void setCellEditorValue(Object obj) {
        this._value = obj;
    }

    public void setClickCountToStart(int i) {
        this._clickCountToStart = i;
    }

    public int getClickCountToStart() {
        return this._clickCountToStart;
    }

    public boolean isCellEditable(EventObject eventObject) {
        return !(eventObject instanceof MouseEvent) || ((MouseEvent) eventObject).getClickCount() >= this._clickCountToStart;
    }

    public boolean shouldSelectCell(EventObject eventObject) {
        if (isCellEditable(eventObject)) {
            return eventObject == null || ((MouseEvent) eventObject).getClickCount() >= this._clickCountToStart;
        }
        return false;
    }

    public boolean stopCellEditing() throws Throwable {
        fireEditingStopped();
        return true;
    }

    public void cancelCellEditing() throws Throwable {
        fireEditingCanceled();
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public void addCellEditorListener(CellEditorListener cellEditorListener) throws Throwable {
        EventListenerList eventListenerList = this._listenerList;
        Class clsClass$ = class$javax$swing$event$CellEditorListener;
        if (clsClass$ == null) {
            clsClass$ = class$("javax.swing.event.CellEditorListener");
            class$javax$swing$event$CellEditorListener = clsClass$;
        }
        eventListenerList.add(clsClass$, cellEditorListener);
    }

    public void removeCellEditorListener(CellEditorListener cellEditorListener) throws Throwable {
        EventListenerList eventListenerList = this._listenerList;
        Class clsClass$ = class$javax$swing$event$CellEditorListener;
        if (clsClass$ == null) {
            clsClass$ = class$("javax.swing.event.CellEditorListener");
            class$javax$swing$event$CellEditorListener = clsClass$;
        }
        eventListenerList.remove(clsClass$, cellEditorListener);
    }

    protected void fireEditingStopped() throws Throwable {
        Object[] listenerList = this._listenerList.getListenerList();
        for (int length = listenerList.length - 2; length >= 0; length -= 2) {
            Object obj = listenerList[length];
            Class clsClass$ = class$javax$swing$event$CellEditorListener;
            if (clsClass$ == null) {
                clsClass$ = class$("javax.swing.event.CellEditorListener");
                class$javax$swing$event$CellEditorListener = clsClass$;
            }
            if (obj == clsClass$) {
                if (this._changeEvent == null) {
                    this._changeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) listenerList[length + 1]).editingStopped(this._changeEvent);
            }
        }
    }

    protected void fireEditingCanceled() throws Throwable {
        Object[] listenerList = this._listenerList.getListenerList();
        for (int length = listenerList.length - 2; length >= 0; length -= 2) {
            Object obj = listenerList[length];
            Class clsClass$ = class$javax$swing$event$CellEditorListener;
            if (clsClass$ == null) {
                clsClass$ = class$("javax.swing.event.CellEditorListener");
                class$javax$swing$event$CellEditorListener = clsClass$;
            }
            if (obj == clsClass$) {
                if (this._changeEvent == null) {
                    this._changeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) listenerList[length + 1]).editingCanceled(this._changeEvent);
            }
        }
    }
}
