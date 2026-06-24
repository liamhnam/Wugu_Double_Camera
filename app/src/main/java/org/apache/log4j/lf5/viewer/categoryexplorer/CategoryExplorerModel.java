package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.apache.log4j.lf5.LogRecord;

public class CategoryExplorerModel extends DefaultTreeModel {
    private static final long serialVersionUID = -3413887384316015901L;
    protected ActionEvent _event;
    protected ActionListener _listener;
    protected boolean _renderFatal;

    public CategoryExplorerModel(CategoryNode categoryNode) {
        super(categoryNode);
        this._renderFatal = true;
        this._listener = null;
        this._event = new ActionEvent(this, 1001, "Nodes Selection changed");
    }

    public void addLogRecord(LogRecord logRecord) {
        CategoryPath categoryPath = new CategoryPath(logRecord.getCategory());
        addCategory(categoryPath);
        CategoryNode categoryNode = getCategoryNode(categoryPath);
        categoryNode.addRecord();
        if (this._renderFatal && logRecord.isFatal()) {
            CategoryNode[] pathToRoot = getPathToRoot(categoryNode);
            int length = pathToRoot.length;
            for (int i = 1; i < length - 1; i++) {
                CategoryNode categoryNode2 = pathToRoot[i];
                categoryNode2.setHasFatalChildren(true);
                nodeChanged(categoryNode2);
            }
            categoryNode.setHasFatalRecords(true);
            nodeChanged(categoryNode);
        }
    }

    public CategoryNode getRootCategoryNode() {
        return (CategoryNode) getRoot();
    }

    public CategoryNode getCategoryNode(String str) {
        return getCategoryNode(new CategoryPath(str));
    }

    public CategoryNode getCategoryNode(CategoryPath categoryPath) {
        CategoryNode categoryNode;
        boolean z;
        CategoryNode categoryNode2 = (CategoryNode) getRoot();
        int i = 0;
        while (i < categoryPath.size()) {
            CategoryElement categoryElementCategoryElementAt = categoryPath.categoryElementAt(i);
            Enumeration enumerationChildren = categoryNode2.children();
            while (true) {
                if (!enumerationChildren.hasMoreElements()) {
                    categoryNode = categoryNode2;
                    z = false;
                    break;
                }
                categoryNode = (CategoryNode) enumerationChildren.nextElement();
                if (categoryNode.getTitle().toLowerCase().equals(categoryElementCategoryElementAt.getTitle().toLowerCase())) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return null;
            }
            i++;
            categoryNode2 = categoryNode;
        }
        return categoryNode2;
    }

    public boolean isCategoryPathActive(CategoryPath categoryPath) {
        boolean z;
        CategoryNode categoryNode = (CategoryNode) getRoot();
        boolean z2 = false;
        for (int i = 0; i < categoryPath.size(); i++) {
            CategoryElement categoryElementCategoryElementAt = categoryPath.categoryElementAt(i);
            Enumeration enumerationChildren = categoryNode.children();
            while (true) {
                if (!enumerationChildren.hasMoreElements()) {
                    z2 = false;
                    z = false;
                    break;
                }
                CategoryNode categoryNode2 = (CategoryNode) enumerationChildren.nextElement();
                if (categoryNode2.getTitle().toLowerCase().equals(categoryElementCategoryElementAt.getTitle().toLowerCase())) {
                    z2 = true;
                    z = true;
                    if (categoryNode2.isSelected()) {
                        categoryNode = categoryNode2;
                    } else {
                        categoryNode = categoryNode2;
                        z2 = false;
                    }
                }
            }
            if (!z2 || !z) {
                return false;
            }
        }
        return z2;
    }

    public CategoryNode addCategory(CategoryPath categoryPath) {
        CategoryNode categoryNode;
        boolean z;
        CategoryNode categoryNode2 = (CategoryNode) getRoot();
        for (int i = 0; i < categoryPath.size(); i++) {
            CategoryElement categoryElementCategoryElementAt = categoryPath.categoryElementAt(i);
            Enumeration enumerationChildren = categoryNode2.children();
            while (true) {
                if (!enumerationChildren.hasMoreElements()) {
                    categoryNode = categoryNode2;
                    z = false;
                    break;
                }
                categoryNode = (CategoryNode) enumerationChildren.nextElement();
                if (categoryNode.getTitle().toLowerCase().equals(categoryElementCategoryElementAt.getTitle().toLowerCase())) {
                    z = true;
                    break;
                }
            }
            if (z) {
                categoryNode2 = categoryNode;
            } else {
                categoryNode2 = new CategoryNode(categoryElementCategoryElementAt.getTitle());
                insertNodeInto(categoryNode2, categoryNode, categoryNode.getChildCount());
                refresh(categoryNode2);
            }
        }
        return categoryNode2;
    }

    public void update(CategoryNode categoryNode, boolean z) {
        if (categoryNode.isSelected() == z) {
            return;
        }
        if (z) {
            setParentSelection(categoryNode, true);
        } else {
            setDescendantSelection(categoryNode, false);
        }
    }

    public void setDescendantSelection(CategoryNode categoryNode, boolean z) {
        Enumeration enumerationDepthFirstEnumeration = categoryNode.depthFirstEnumeration();
        while (enumerationDepthFirstEnumeration.hasMoreElements()) {
            CategoryNode categoryNode2 = (CategoryNode) enumerationDepthFirstEnumeration.nextElement();
            if (categoryNode2.isSelected() != z) {
                categoryNode2.setSelected(z);
                nodeChanged(categoryNode2);
            }
        }
        notifyActionListeners();
    }

    public void setParentSelection(CategoryNode categoryNode, boolean z) {
        CategoryNode[] pathToRoot = getPathToRoot(categoryNode);
        int length = pathToRoot.length;
        for (int i = 1; i < length; i++) {
            CategoryNode categoryNode2 = pathToRoot[i];
            if (categoryNode2.isSelected() != z) {
                categoryNode2.setSelected(z);
                nodeChanged(categoryNode2);
            }
        }
        notifyActionListeners();
    }

    public synchronized void addActionListener(ActionListener actionListener) {
        this._listener = AWTEventMulticaster.add(this._listener, actionListener);
    }

    public synchronized void removeActionListener(ActionListener actionListener) {
        this._listener = AWTEventMulticaster.remove(this._listener, actionListener);
    }

    public void resetAllNodeCounts() {
        Enumeration enumerationDepthFirstEnumeration = getRootCategoryNode().depthFirstEnumeration();
        while (enumerationDepthFirstEnumeration.hasMoreElements()) {
            CategoryNode categoryNode = (CategoryNode) enumerationDepthFirstEnumeration.nextElement();
            categoryNode.resetNumberOfContainedRecords();
            nodeChanged(categoryNode);
        }
    }

    public TreePath getTreePathToRoot(CategoryNode categoryNode) {
        if (categoryNode == null) {
            return null;
        }
        return new TreePath(getPathToRoot(categoryNode));
    }

    protected void notifyActionListeners() {
        ActionListener actionListener = this._listener;
        if (actionListener != null) {
            actionListener.actionPerformed(this._event);
        }
    }

    protected void refresh(final CategoryNode categoryNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CategoryExplorerModel.this.nodeChanged(categoryNode);
            }
        });
    }
}
