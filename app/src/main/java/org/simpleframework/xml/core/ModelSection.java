package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;

class ModelSection implements Section {
    private LabelMap attributes;
    private LabelMap elements;
    private Model model;
    private ModelMap models;

    public ModelSection(Model model) {
        this.model = model;
    }

    @Override
    public String getName() {
        return this.model.getName();
    }

    @Override
    public String getPrefix() {
        return this.model.getPrefix();
    }

    @Override
    public String getPath(String str) throws Exception {
        Expression expression = this.model.getExpression();
        return expression == null ? str : expression.getElement(str);
    }

    @Override
    public String getAttribute(String str) throws Exception {
        Expression expression = this.model.getExpression();
        return expression == null ? str : expression.getAttribute(str);
    }

    @Override
    public Iterator<String> iterator() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.model.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList.iterator();
    }

    @Override
    public boolean isSection(String str) throws Exception {
        return getModels().get(str) != null;
    }

    public ModelMap getModels() throws Exception {
        if (this.models == null) {
            this.models = this.model.getModels();
        }
        return this.models;
    }

    @Override
    public Label getText() throws Exception {
        return this.model.getText();
    }

    @Override
    public LabelMap getAttributes() throws Exception {
        if (this.attributes == null) {
            this.attributes = this.model.getAttributes();
        }
        return this.attributes;
    }

    @Override
    public LabelMap getElements() throws Exception {
        if (this.elements == null) {
            this.elements = this.model.getElements();
        }
        return this.elements;
    }

    @Override
    public Label getElement(String str) throws Exception {
        return getElements().getLabel(str);
    }

    @Override
    public Section getSection(String str) throws Exception {
        Model modelTake;
        ModelList modelList = getModels().get(str);
        if (modelList == null || (modelTake = modelList.take()) == null) {
            return null;
        }
        return new ModelSection(modelTake);
    }
}
