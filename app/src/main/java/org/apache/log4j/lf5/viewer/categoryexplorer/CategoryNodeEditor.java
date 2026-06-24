package org.apache.log4j.lf5.viewer.categoryexplorer;

import com.p020hp.jipp.model.PowerState;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

public class CategoryNodeEditor extends CategoryAbstractCellEditor {
    protected CategoryExplorerModel _categoryModel;
    protected JCheckBox _checkBox;
    protected CategoryNode _lastEditedNode;
    protected CategoryNodeEditorRenderer _renderer;
    protected JTree _tree;

    public CategoryNodeEditor(CategoryExplorerModel categoryExplorerModel) {
        CategoryNodeEditorRenderer categoryNodeEditorRenderer = new CategoryNodeEditorRenderer();
        this._renderer = categoryNodeEditorRenderer;
        JCheckBox checkBox = categoryNodeEditorRenderer.getCheckBox();
        this._checkBox = checkBox;
        this._categoryModel = categoryExplorerModel;
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this._categoryModel.update(CategoryNodeEditor.this._lastEditedNode, CategoryNodeEditor.this._checkBox.isSelected());
                CategoryNodeEditor.this.stopCellEditing();
            }
        });
        this._renderer.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 4) != 0) {
                    CategoryNodeEditor categoryNodeEditor = CategoryNodeEditor.this;
                    categoryNodeEditor.showPopup(categoryNodeEditor._lastEditedNode, mouseEvent.getX(), mouseEvent.getY());
                }
                CategoryNodeEditor.this.stopCellEditing();
            }
        });
    }

    @Override
    public Component getTreeCellEditorComponent(JTree jTree, Object obj, boolean z, boolean z2, boolean z3, int i) {
        this._lastEditedNode = (CategoryNode) obj;
        this._tree = jTree;
        return this._renderer.getTreeCellRendererComponent(jTree, obj, z, z2, z3, i, true);
    }

    @Override
    public Object getCellEditorValue() {
        return this._lastEditedNode.getUserObject();
    }

    protected JMenuItem createPropertiesMenuItem(final CategoryNode categoryNode) {
        JMenuItem jMenuItem = new JMenuItem("Properties");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this.showPropertiesDialog(categoryNode);
            }
        });
        return jMenuItem;
    }

    protected void showPropertiesDialog(CategoryNode categoryNode) {
        JOptionPane.showMessageDialog(this._tree, getDisplayedProperties(categoryNode), new StringBuffer("Category Properties: ").append(categoryNode.getTitle()).toString(), -1);
    }

    protected Object getDisplayedProperties(CategoryNode categoryNode) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StringBuffer("Category: ").append(categoryNode.getTitle()).toString());
        if (categoryNode.hasFatalRecords()) {
            arrayList.add("Contains at least one fatal LogRecord.");
        }
        if (categoryNode.hasFatalChildren()) {
            arrayList.add("Contains descendants with a fatal LogRecord.");
        }
        arrayList.add(new StringBuffer("LogRecords in this category alone: ").append(categoryNode.getNumberOfContainedRecords()).toString());
        arrayList.add(new StringBuffer("LogRecords in descendant categories: ").append(categoryNode.getNumberOfRecordsFromChildren()).toString());
        arrayList.add(new StringBuffer("LogRecords in this category including descendants: ").append(categoryNode.getTotalNumberOfRecords()).toString());
        return arrayList.toArray();
    }

    protected void showPopup(CategoryNode categoryNode, int i, int i2) {
        JPopupMenu jPopupMenu = new JPopupMenu();
        jPopupMenu.setSize(PowerState.Code.resetSoftGraceful, 400);
        if (categoryNode.getParent() == null) {
            jPopupMenu.add(createRemoveMenuItem());
            jPopupMenu.addSeparator();
        }
        jPopupMenu.add(createSelectDescendantsMenuItem(categoryNode));
        jPopupMenu.add(createUnselectDescendantsMenuItem(categoryNode));
        jPopupMenu.addSeparator();
        jPopupMenu.add(createExpandMenuItem(categoryNode));
        jPopupMenu.add(createCollapseMenuItem(categoryNode));
        jPopupMenu.addSeparator();
        jPopupMenu.add(createPropertiesMenuItem(categoryNode));
        jPopupMenu.show(this._renderer, i, i2);
    }

    protected JMenuItem createSelectDescendantsMenuItem(final CategoryNode categoryNode) {
        JMenuItem jMenuItem = new JMenuItem("Select All Descendant Categories");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this._categoryModel.setDescendantSelection(categoryNode, true);
            }
        });
        return jMenuItem;
    }

    protected JMenuItem createUnselectDescendantsMenuItem(final CategoryNode categoryNode) {
        JMenuItem jMenuItem = new JMenuItem("Deselect All Descendant Categories");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this._categoryModel.setDescendantSelection(categoryNode, false);
            }
        });
        return jMenuItem;
    }

    protected JMenuItem createExpandMenuItem(final CategoryNode categoryNode) {
        JMenuItem jMenuItem = new JMenuItem("Expand All Descendant Categories");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this.expandDescendants(categoryNode);
            }
        });
        return jMenuItem;
    }

    protected JMenuItem createCollapseMenuItem(final CategoryNode categoryNode) {
        JMenuItem jMenuItem = new JMenuItem("Collapse All Descendant Categories");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this.collapseDescendants(categoryNode);
            }
        });
        return jMenuItem;
    }

    protected JMenuItem createRemoveMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Remove All Empty Categories");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                while (CategoryNodeEditor.this.removeUnusedNodes() > 0) {
                }
            }
        });
        return jMenuItem;
    }

    protected void expandDescendants(CategoryNode categoryNode) {
        Enumeration enumerationDepthFirstEnumeration = categoryNode.depthFirstEnumeration();
        while (enumerationDepthFirstEnumeration.hasMoreElements()) {
            expand((CategoryNode) enumerationDepthFirstEnumeration.nextElement());
        }
    }

    protected void collapseDescendants(CategoryNode categoryNode) {
        Enumeration enumerationDepthFirstEnumeration = categoryNode.depthFirstEnumeration();
        while (enumerationDepthFirstEnumeration.hasMoreElements()) {
            collapse((CategoryNode) enumerationDepthFirstEnumeration.nextElement());
        }
    }

    protected int removeUnusedNodes() {
        Enumeration enumerationDepthFirstEnumeration = this._categoryModel.getRootCategoryNode().depthFirstEnumeration();
        int i = 0;
        while (enumerationDepthFirstEnumeration.hasMoreElements()) {
            MutableTreeNode mutableTreeNode = (CategoryNode) enumerationDepthFirstEnumeration.nextElement();
            if (mutableTreeNode.isLeaf() && mutableTreeNode.getNumberOfContainedRecords() == 0 && mutableTreeNode.getParent() != null) {
                this._categoryModel.removeNodeFromParent(mutableTreeNode);
                i++;
            }
        }
        return i;
    }

    protected void expand(CategoryNode categoryNode) {
        this._tree.expandPath(getTreePath(categoryNode));
    }

    protected TreePath getTreePath(CategoryNode categoryNode) {
        return new TreePath(categoryNode.getPath());
    }

    protected void collapse(CategoryNode categoryNode) {
        this._tree.collapsePath(getTreePath(categoryNode));
    }
}
