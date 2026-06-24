package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;

class TreeModel implements Model {
    private LabelMap attributes;
    private Detail detail;
    private LabelMap elements;
    private Expression expression;
    private int index;
    private Label list;
    private ModelMap models;
    private String name;
    private OrderList order;
    private Policy policy;
    private String prefix;
    private Label text;

    private static class OrderList extends ArrayList<String> {
    }

    public TreeModel(Policy policy, Detail detail) {
        this(policy, detail, null, null, 1);
    }

    public TreeModel(Policy policy, Detail detail, String str, String str2, int i) {
        this.attributes = new LabelMap(policy);
        this.elements = new LabelMap(policy);
        this.models = new ModelMap(detail);
        this.order = new OrderList();
        this.detail = detail;
        this.policy = policy;
        this.prefix = str2;
        this.index = i;
        this.name = str;
    }

    @Override
    public Model lookup(Expression expression) {
        Model modelLookup = lookup(expression.getFirst(), expression.getIndex());
        if (expression.isPath()) {
            Expression path = expression.getPath(1, 0);
            if (modelLookup != null) {
                return modelLookup.lookup(path);
            }
        }
        return modelLookup;
    }

    @Override
    public void registerElement(String str) throws Exception {
        if (!this.order.contains(str)) {
            this.order.add(str);
        }
        this.elements.put(str, null);
    }

    @Override
    public void registerAttribute(String str) throws Exception {
        this.attributes.put(str, null);
    }

    @Override
    public void registerText(Label label) throws Exception {
        if (this.text != null) {
            throw new TextException("Duplicate text annotation on %s", label);
        }
        this.text = label;
    }

    @Override
    public void registerAttribute(Label label) throws Exception {
        String name = label.getName();
        if (this.attributes.get(name) != null) {
            throw new AttributeException("Duplicate annotation of name '%s' on %s", name, label);
        }
        this.attributes.put(name, label);
    }

    @Override
    public void registerElement(Label label) throws Exception {
        String name = label.getName();
        if (this.elements.get(name) != null) {
            throw new ElementException("Duplicate annotation of name '%s' on %s", name, label);
        }
        if (!this.order.contains(name)) {
            this.order.add(name);
        }
        if (label.isTextList()) {
            this.list = label;
        }
        this.elements.put(name, label);
    }

    @Override
    public ModelMap getModels() throws Exception {
        return this.models.getModels();
    }

    @Override
    public LabelMap getAttributes() throws Exception {
        return this.attributes.getLabels();
    }

    @Override
    public LabelMap getElements() throws Exception {
        return this.elements.getLabels();
    }

    @Override
    public boolean isModel(String str) {
        return this.models.containsKey(str);
    }

    @Override
    public boolean isElement(String str) {
        return this.elements.containsKey(str);
    }

    @Override
    public boolean isAttribute(String str) {
        return this.attributes.containsKey(str);
    }

    @Override
    public Iterator<String> iterator() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.order.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList.iterator();
    }

    @Override
    public void validate(Class cls) throws Exception {
        validateExpressions(cls);
        validateAttributes(cls);
        validateElements(cls);
        validateModels(cls);
        validateText(cls);
    }

    private void validateText(Class cls) throws Exception {
        if (this.text != null) {
            if (!this.elements.isEmpty()) {
                throw new TextException("Text annotation %s used with elements in %s", this.text, cls);
            }
            if (isComposite()) {
                throw new TextException("Text annotation %s can not be used with paths in %s", this.text, cls);
            }
        }
    }

    private void validateExpressions(Class cls) throws Exception {
        for (Label label : this.elements) {
            if (label != null) {
                validateExpression(label);
            }
        }
        for (Label label2 : this.attributes) {
            if (label2 != null) {
                validateExpression(label2);
            }
        }
        Label label3 = this.text;
        if (label3 != null) {
            validateExpression(label3);
        }
    }

    private void validateExpression(Label label) throws Exception {
        Expression expression = label.getExpression();
        Expression expression2 = this.expression;
        if (expression2 != null) {
            String path = expression2.getPath();
            String path2 = expression.getPath();
            if (!path.equals(path2)) {
                throw new PathException("Path '%s' does not match '%s' in %s", path, path2, this.detail);
            }
            return;
        }
        this.expression = expression;
    }

    private void validateModels(Class cls) throws Exception {
        Iterator<ModelList> it = this.models.iterator();
        while (it.hasNext()) {
            int i = 1;
            for (Model model : it.next()) {
                if (model != null) {
                    String name = model.getName();
                    int index = model.getIndex();
                    int i2 = i + 1;
                    if (index != i) {
                        throw new ElementException("Path section '%s[%s]' is out of sequence in %s", name, Integer.valueOf(index), cls);
                    }
                    model.validate(cls);
                    i = i2;
                }
            }
        }
    }

    private void validateAttributes(Class cls) throws Exception {
        for (String str : this.attributes.keySet()) {
            if (this.attributes.get(str) == null) {
                throw new AttributeException("Ordered attribute '%s' does not exist in %s", str, cls);
            }
            Expression expression = this.expression;
            if (expression != null) {
                expression.getAttribute(str);
            }
        }
    }

    private void validateElements(Class cls) throws Exception {
        for (String str : this.elements.keySet()) {
            ModelList modelList = this.models.get(str);
            Label label = this.elements.get(str);
            if (modelList == null && label == null) {
                throw new ElementException("Ordered element '%s' does not exist in %s", str, cls);
            }
            if (modelList != null && label != null && !modelList.isEmpty()) {
                throw new ElementException("Element '%s' is also a path name in %s", str, cls);
            }
            Expression expression = this.expression;
            if (expression != null) {
                expression.getElement(str);
            }
        }
    }

    @Override
    public void register(Label label) throws Exception {
        if (label.isAttribute()) {
            registerAttribute(label);
        } else if (label.isText()) {
            registerText(label);
        } else {
            registerElement(label);
        }
    }

    @Override
    public Model lookup(String str, int i) {
        return this.models.lookup(str, i);
    }

    @Override
    public Model register(String str, String str2, int i) throws Exception {
        Model modelLookup = this.models.lookup(str, i);
        return modelLookup == null ? create(str, str2, i) : modelLookup;
    }

    private Model create(String str, String str2, int i) throws Exception {
        TreeModel treeModel = new TreeModel(this.policy, this.detail, str, str2, i);
        if (str != null) {
            this.models.register(str, treeModel);
            this.order.add(str);
        }
        return treeModel;
    }

    @Override
    public boolean isComposite() {
        Iterator<ModelList> it = this.models.iterator();
        while (it.hasNext()) {
            for (Model model : it.next()) {
                if (model != null && !model.isEmpty()) {
                    return true;
                }
            }
        }
        return !this.models.isEmpty();
    }

    @Override
    public boolean isEmpty() {
        if (this.text == null && this.elements.isEmpty() && this.attributes.isEmpty()) {
            return !isComposite();
        }
        return false;
    }

    @Override
    public Label getText() {
        Label label = this.list;
        return label != null ? label : this.text;
    }

    @Override
    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    public String toString() {
        return String.format("model '%s[%s]'", this.name, Integer.valueOf(this.index));
    }
}
