package javax.xml.stream;

public class XMLStreamException extends Exception {
    protected Location location;
    protected Throwable nested;

    public XMLStreamException() {
    }

    public XMLStreamException(String str) {
        super(str);
    }

    public XMLStreamException(Throwable th) {
        this.nested = th;
    }

    public XMLStreamException(String str, Throwable th) {
        super(str);
        this.nested = th;
    }

    public XMLStreamException(String str, Location location, Throwable th) {
        super(new StringBuffer("ParseError at [row,col]:[").append(location.getLineNumber()).append(",").append(location.getColumnNumber()).append("]\nMessage: ").append(str).toString());
        this.nested = th;
        this.location = location;
    }

    public XMLStreamException(String str, Location location) {
        super(new StringBuffer("ParseError at [row,col]:[").append(location.getLineNumber()).append(",").append(location.getColumnNumber()).append("]\nMessage: ").append(str).toString());
        this.location = location;
    }

    public Throwable getNestedException() {
        return this.nested;
    }

    public Location getLocation() {
        return this.location;
    }
}
