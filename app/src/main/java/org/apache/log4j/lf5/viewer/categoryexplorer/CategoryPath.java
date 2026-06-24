package org.apache.log4j.lf5.viewer.categoryexplorer;

import com.faceunity.core.controller.bodyBeauty.BodyBeautyParam;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class CategoryPath {
    protected LinkedList _categoryElements = new LinkedList();

    public CategoryPath() {
    }

    public CategoryPath(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer((str == null ? BodyBeautyParam.IS_DEBUG : str).replace('/', '.').replace('\\', '.'), ".");
        while (stringTokenizer.hasMoreTokens()) {
            addCategoryElement(new CategoryElement(stringTokenizer.nextToken()));
        }
    }

    public int size() {
        return this._categoryElements.size();
    }

    public boolean isEmpty() {
        return this._categoryElements.size() == 0;
    }

    public void removeAllCategoryElements() {
        this._categoryElements.clear();
    }

    public void addCategoryElement(CategoryElement categoryElement) {
        this._categoryElements.addLast(categoryElement);
    }

    public CategoryElement categoryElementAt(int i) {
        return (CategoryElement) this._categoryElements.get(i);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("\n===========================\nCategoryPath:                   \n---------------------------\n\nCategoryPath:\n\t");
        if (size() > 0) {
            for (int i = 0; i < size(); i++) {
                stringBuffer.append(categoryElementAt(i).toString());
                stringBuffer.append("\n\t");
            }
        } else {
            stringBuffer.append("<<NONE>>");
        }
        stringBuffer.append("\n===========================\n");
        return stringBuffer.toString();
    }
}
