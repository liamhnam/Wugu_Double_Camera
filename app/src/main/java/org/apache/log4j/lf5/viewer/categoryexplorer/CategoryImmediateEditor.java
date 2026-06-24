package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.TreePath;

public class CategoryImmediateEditor extends DefaultTreeCellEditor {
    protected Icon editingIcon;
    private CategoryNodeRenderer renderer;

    public CategoryImmediateEditor(JTree jTree, CategoryNodeRenderer categoryNodeRenderer, CategoryNodeEditor categoryNodeEditor) {
        super(jTree, categoryNodeRenderer, categoryNodeEditor);
        this.editingIcon = null;
        this.renderer = categoryNodeRenderer;
        categoryNodeRenderer.setIcon(null);
        categoryNodeRenderer.setLeafIcon(null);
        categoryNodeRenderer.setOpenIcon(null);
        categoryNodeRenderer.setClosedIcon(null);
        ((DefaultTreeCellEditor) this).editingIcon = null;
    }

    public boolean shouldSelectCell(EventObject eventObject) {
        if (!(eventObject instanceof MouseEvent)) {
            return false;
        }
        MouseEvent mouseEvent = (MouseEvent) eventObject;
        return ((CategoryNode) this.tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY()).getLastPathComponent()).isLeaf();
    }

    public boolean inCheckBoxHitRegion(MouseEvent mouseEvent) {
        TreePath pathForLocation = this.tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
        if (pathForLocation == null) {
            return false;
        }
        Rectangle rowBounds = this.tree.getRowBounds(this.lastRow);
        Dimension checkBoxOffset = this.renderer.getCheckBoxOffset();
        rowBounds.translate(this.offset + checkBoxOffset.width, checkBoxOffset.height);
        rowBounds.contains(mouseEvent.getPoint());
        return true;
    }

    protected boolean canEditImmediately(EventObject eventObject) {
        if (eventObject instanceof MouseEvent) {
            return inCheckBoxHitRegion((MouseEvent) eventObject);
        }
        return false;
    }

    protected void determineOffset(JTree jTree, Object obj, boolean z, boolean z2, boolean z3, int i) {
        this.offset = 0;
    }
}
