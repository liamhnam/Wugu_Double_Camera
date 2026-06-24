package org.apache.log4j.lf5.viewer;

import com.brother.sdk.print.pdl.PrintImageUtil;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.log4j.lf5.util.DateFormatManager;

public class LogTable extends JTable {
    private static final long serialVersionUID = 4867085140195148458L;
    protected JTextArea _detailTextArea;
    protected int _rowHeight = 30;
    protected int _numCols = 9;
    protected TableColumn[] _tableColumns = new TableColumn[9];
    protected int[] _colWidths = {40, 40, 40, 70, 70, PrintImageUtil.ROUND_ROTATE, UiPosIndexEnum.KEYBOARD_DEL, 200, 60};
    protected LogTableColumn[] _colNames = LogTableColumn.getLogTableColumnArray();
    protected int _colDate = 0;
    protected int _colThread = 1;
    protected int _colMessageNum = 2;
    protected int _colLevel = 3;
    protected int _colNDC = 4;
    protected int _colCategory = 5;
    protected int _colMessage = 6;
    protected int _colLocation = 7;
    protected int _colThrown = 8;
    protected DateFormatManager _dateFormatManager = null;

    public LogTable(JTextArea jTextArea) {
        int i = 0;
        init();
        this._detailTextArea = jTextArea;
        setModel(new FilteredLogTableModel());
        Enumeration columns = getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn tableColumn = (TableColumn) columns.nextElement();
            tableColumn.setCellRenderer(new LogTableRowRenderer());
            tableColumn.setPreferredWidth(this._colWidths[i]);
            this._tableColumns[i] = tableColumn;
            i++;
        }
        getSelectionModel().addListSelectionListener(new LogTableListSelectionListener(this));
    }

    public DateFormatManager getDateFormatManager() {
        return this._dateFormatManager;
    }

    public void setDateFormatManager(DateFormatManager dateFormatManager) {
        this._dateFormatManager = dateFormatManager;
    }

    public synchronized void clearLogRecords() {
        getFilteredLogTableModel().clear();
    }

    public FilteredLogTableModel getFilteredLogTableModel() {
        return getModel();
    }

    public void setDetailedView() {
        TableColumnModel columnModel = getColumnModel();
        for (int i = 0; i < this._numCols; i++) {
            columnModel.removeColumn(this._tableColumns[i]);
        }
        for (int i2 = 0; i2 < this._numCols; i2++) {
            columnModel.addColumn(this._tableColumns[i2]);
        }
        sizeColumnsToFit(-1);
    }

    public void setView(List list) {
        TableColumnModel columnModel = getColumnModel();
        for (int i = 0; i < this._numCols; i++) {
            columnModel.removeColumn(this._tableColumns[i]);
        }
        Iterator it = list.iterator();
        Vector columnNameAndNumber = getColumnNameAndNumber();
        while (it.hasNext()) {
            columnModel.addColumn(this._tableColumns[columnNameAndNumber.indexOf(it.next())]);
        }
        sizeColumnsToFit(-1);
    }

    public void setFont(Font font) {
        super.setFont(font);
        Graphics graphics = getGraphics();
        if (graphics != null) {
            int height = graphics.getFontMetrics(font).getHeight();
            int i = height + (height / 3);
            this._rowHeight = i;
            setRowHeight(i);
        }
    }

    protected void init() {
        setRowHeight(this._rowHeight);
        setSelectionMode(0);
    }

    protected Vector getColumnNameAndNumber() {
        Vector vector = new Vector();
        int i = 0;
        while (true) {
            LogTableColumn[] logTableColumnArr = this._colNames;
            if (i >= logTableColumnArr.length) {
                return vector;
            }
            vector.add(i, logTableColumnArr[i]);
            i++;
        }
    }

    class LogTableListSelectionListener implements ListSelectionListener {
        protected JTable _table;

        public LogTableListSelectionListener(JTable jTable) {
            this._table = jTable;
        }

        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            if (listSelectionEvent.getValueIsAdjusting()) {
                return;
            }
            ListSelectionModel listSelectionModel = (ListSelectionModel) listSelectionEvent.getSource();
            if (listSelectionModel.isSelectionEmpty()) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            int minSelectionIndex = listSelectionModel.getMinSelectionIndex();
            for (int i = 0; i < LogTable.this._numCols - 1; i++) {
                Object valueAt = this._table.getModel().getValueAt(minSelectionIndex, i);
                String string = valueAt != null ? valueAt.toString() : "";
                stringBuffer.append(new StringBuffer().append(LogTable.this._colNames[i]).append(":").toString());
                stringBuffer.append("\t");
                if (i == LogTable.this._colThread || i == LogTable.this._colMessage || i == LogTable.this._colLevel) {
                    stringBuffer.append("\t");
                }
                if (i == LogTable.this._colDate || i == LogTable.this._colNDC) {
                    stringBuffer.append("\t\t");
                }
                stringBuffer.append(string);
                stringBuffer.append("\n");
            }
            stringBuffer.append(new StringBuffer().append(LogTable.this._colNames[LogTable.this._numCols - 1]).append(":\n").toString());
            Object valueAt2 = this._table.getModel().getValueAt(minSelectionIndex, LogTable.this._numCols - 1);
            if (valueAt2 != null) {
                stringBuffer.append(valueAt2.toString());
            }
            LogTable.this._detailTextArea.setText(stringBuffer.toString());
        }
    }
}
