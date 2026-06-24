package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.util.Enumeration;
import javax.swing.tree.TreeNode;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;

public class CategoryExplorerLogRecordFilter implements LogRecordFilter {
    protected CategoryExplorerModel _model;

    public CategoryExplorerLogRecordFilter(CategoryExplorerModel categoryExplorerModel) {
        this._model = categoryExplorerModel;
    }

    @Override
    public boolean passes(LogRecord logRecord) {
        return this._model.isCategoryPathActive(new CategoryPath(logRecord.getCategory()));
    }

    public void reset() {
        resetAllNodes();
    }

    protected void resetAllNodes() {
        Enumeration enumerationDepthFirstEnumeration = this._model.getRootCategoryNode().depthFirstEnumeration();
        while (enumerationDepthFirstEnumeration.hasMoreElements()) {
            TreeNode treeNode = (CategoryNode) enumerationDepthFirstEnumeration.nextElement();
            treeNode.resetNumberOfContainedRecords();
            this._model.nodeChanged(treeNode);
        }
    }
}
