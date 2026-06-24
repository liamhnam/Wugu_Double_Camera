package com.tom_roush.pdfbox.pdmodel.common.function;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;
import com.tom_roush.pdfbox.pdmodel.common.function.type4.ExecutionContext;
import com.tom_roush.pdfbox.pdmodel.common.function.type4.InstructionSequence;
import com.tom_roush.pdfbox.pdmodel.common.function.type4.InstructionSequenceBuilder;
import com.tom_roush.pdfbox.pdmodel.common.function.type4.Operators;
import java.io.IOException;

public class PDFunctionType4 extends PDFunction {
    private static final Operators OPERATORS = new Operators();
    private final InstructionSequence instructions;

    @Override
    public int getFunctionType() {
        return 4;
    }

    public PDFunctionType4(COSBase cOSBase) throws IOException {
        super(cOSBase);
        this.instructions = InstructionSequenceBuilder.parse(new String(getPDStream().toByteArray(), "ISO-8859-1"));
    }

    @Override
    public float[] eval(float[] fArr) throws IOException {
        ExecutionContext executionContext = new ExecutionContext(OPERATORS);
        for (int i = 0; i < fArr.length; i++) {
            PDRange domainForInput = getDomainForInput(i);
            executionContext.getStack().push(Float.valueOf(clipToRange(fArr[i], domainForInput.getMin(), domainForInput.getMax())));
        }
        this.instructions.execute(executionContext);
        int numberOfOutputParameters = getNumberOfOutputParameters();
        int size = executionContext.getStack().size();
        if (size < numberOfOutputParameters) {
            throw new IllegalStateException("The type 4 function returned " + size + " values but the Range entry indicates that " + numberOfOutputParameters + " values be returned.");
        }
        float[] fArr2 = new float[numberOfOutputParameters];
        for (int i2 = numberOfOutputParameters - 1; i2 >= 0; i2--) {
            PDRange rangeForOutput = getRangeForOutput(i2);
            float fPopReal = executionContext.popReal();
            fArr2[i2] = fPopReal;
            fArr2[i2] = clipToRange(fPopReal, rangeForOutput.getMin(), rangeForOutput.getMax());
        }
        return fArr2;
    }
}
