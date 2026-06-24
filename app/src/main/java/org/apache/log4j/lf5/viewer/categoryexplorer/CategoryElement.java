package org.apache.log4j.lf5.viewer.categoryexplorer;

public class CategoryElement {
    protected String _categoryTitle;

    public CategoryElement() {
    }

    public CategoryElement(String str) {
        this._categoryTitle = str;
    }

    public String getTitle() {
        return this._categoryTitle;
    }

    public void setTitle(String str) {
        this._categoryTitle = str;
    }
}
