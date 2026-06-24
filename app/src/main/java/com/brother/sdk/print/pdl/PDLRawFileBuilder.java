package com.brother.sdk.print.pdl;

import com.brother.sdk.common.Callback;
import com.brother.sdk.common.OutOfMemoryException;
import com.brother.sdk.common.device.printer.Printer;
import com.brother.sdk.print.PrintParameters;
import com.brother.sdk.print.pdl.PageDescriptionLanguageBuilder;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PDLRawFileBuilder extends PageDescriptionLanguageBuilder {
    public PDLRawFileBuilder(Printer printer, Callback callback) {
        super(printer, callback);
    }

    @Override
    protected void buildDataBlocksInternal(Printer printer, PrintParameters printParameters, List<File> list, File file, PageDescriptionLanguageBuilder.PrintDataBlockCreateCallback printDataBlockCreateCallback) throws OutOfMemoryException, IOException {
        Iterator<File> it = list.iterator();
        while (it.hasNext()) {
            printDataBlockCreateCallback.OnDataBlockCreated(new PrintDataBlockFile(it.next()));
        }
        printDataBlockCreateCallback.OnCancelBlockCreated(new PrintDataBlockCancel(new byte[0]));
    }
}
