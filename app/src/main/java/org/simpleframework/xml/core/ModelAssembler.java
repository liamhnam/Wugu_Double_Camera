package org.simpleframework.xml.core;

import org.simpleframework.xml.Order;
import org.simpleframework.xml.stream.Format;

class ModelAssembler {
    private final ExpressionBuilder builder;
    private final Detail detail;
    private final Format format;

    public ModelAssembler(ExpressionBuilder expressionBuilder, Detail detail, Support support) throws Exception {
        this.format = support.getFormat();
        this.builder = expressionBuilder;
        this.detail = detail;
    }

    public void assemble(Model model, Order order) throws Exception {
        assembleElements(model, order);
        assembleAttributes(model, order);
    }

    private void assembleElements(Model model, Order order) throws Exception {
        for (String str : order.elements()) {
            Expression expressionBuild = this.builder.build(str);
            if (expressionBuild.isAttribute()) {
                throw new PathException("Ordered element '%s' references an attribute in %s", expressionBuild, this.detail);
            }
            registerElements(model, expressionBuild);
        }
    }

    private void assembleAttributes(Model model, Order order) throws Exception {
        for (String str : order.attributes()) {
            Expression expressionBuild = this.builder.build(str);
            if (!expressionBuild.isAttribute() && expressionBuild.isPath()) {
                throw new PathException("Ordered attribute '%s' references an element in %s", expressionBuild, this.detail);
            }
            if (!expressionBuild.isPath()) {
                model.registerAttribute(this.format.getStyle().getAttribute(str));
            } else {
                registerAttributes(model, expressionBuild);
            }
        }
    }

    private void registerAttributes(Model model, Expression expression) throws Exception {
        String prefix = expression.getPrefix();
        String first = expression.getFirst();
        int index = expression.getIndex();
        if (expression.isPath()) {
            Model modelRegister = model.register(first, prefix, index);
            Expression path = expression.getPath(1);
            if (modelRegister == null) {
                throw new PathException("Element '%s' does not exist in %s", first, this.detail);
            }
            registerAttributes(modelRegister, path);
            return;
        }
        registerAttribute(model, expression);
    }

    private void registerAttribute(Model model, Expression expression) throws Exception {
        String first = expression.getFirst();
        if (first != null) {
            model.registerAttribute(first);
        }
    }

    private void registerElements(Model model, Expression expression) throws Exception {
        String prefix = expression.getPrefix();
        String first = expression.getFirst();
        int index = expression.getIndex();
        if (first != null) {
            Model modelRegister = model.register(first, prefix, index);
            Expression path = expression.getPath(1);
            if (expression.isPath()) {
                registerElements(modelRegister, path);
            }
        }
        registerElement(model, expression);
    }

    private void registerElement(Model model, Expression expression) throws Exception {
        String prefix = expression.getPrefix();
        String first = expression.getFirst();
        int index = expression.getIndex();
        if (index > 1 && model.lookup(first, index - 1) == null) {
            throw new PathException("Ordered element '%s' in path '%s' is out of sequence for %s", first, expression, this.detail);
        }
        model.register(first, prefix, index);
    }
}
