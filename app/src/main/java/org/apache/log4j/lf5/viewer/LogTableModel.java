package org.apache.log4j.lf5.viewer;

import javax.swing.table.DefaultTableModel;

public class LogTableModel extends DefaultTableModel {
    private static final long serialVersionUID = 3593300685868700894L;

    public boolean isCellEditable(int i, int i2) {
        return false;
    }

    public LogTableModel(Object[] objArr, int i) {
        super(objArr, i);
    }
}
