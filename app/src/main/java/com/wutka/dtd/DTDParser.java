package com.wutka.dtd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

public class DTDParser implements EntityExpansion {
    protected Object defaultLocation;
    protected DTD dtd;
    protected Scanner scanner;

    public DTDParser(Reader reader) {
        this.scanner = new Scanner(reader, false, this);
        this.dtd = new DTD();
    }

    public DTDParser(Reader reader, boolean z) {
        this.scanner = new Scanner(reader, z, this);
        this.dtd = new DTD();
    }

    public DTDParser(File file) throws IOException {
        this.defaultLocation = file.getParentFile();
        this.scanner = new Scanner(new BufferedReader(new FileReader(file)), false, this);
        this.dtd = new DTD();
    }

    public DTDParser(File file, boolean z) throws IOException {
        this.defaultLocation = file.getParentFile();
        this.scanner = new Scanner(new BufferedReader(new FileReader(file)), z, this);
        this.dtd = new DTD();
    }

    public DTDParser(URL url) throws IOException {
        String file = url.getFile();
        this.defaultLocation = new URL(url.getProtocol(), url.getHost(), url.getPort(), file.substring(0, file.lastIndexOf(47) + 1));
        this.scanner = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())), false, this);
        this.dtd = new DTD();
    }

    public DTDParser(URL url, boolean z) throws IOException {
        String file = url.getFile();
        this.defaultLocation = new URL(url.getProtocol(), url.getHost(), url.getPort(), file.substring(0, file.lastIndexOf(47) + 1));
        this.scanner = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())), z, this);
        this.dtd = new DTD();
    }

    public DTD parse() throws IOException {
        return parse(false);
    }

    public DTD parse(boolean z) throws IOException {
        while (this.scanner.peek().type != Scanner.EOF) {
            parseTopLevelElement();
        }
        if (z) {
            Hashtable hashtable = new Hashtable();
            Enumeration enumerationElements = this.dtd.elements.elements();
            while (enumerationElements.hasMoreElements()) {
                DTDElement dTDElement = (DTDElement) enumerationElements.nextElement();
                hashtable.put(dTDElement.name, dTDElement);
            }
            Enumeration enumerationElements2 = this.dtd.elements.elements();
            while (enumerationElements2.hasMoreElements()) {
                DTDElement dTDElement2 = (DTDElement) enumerationElements2.nextElement();
                if (dTDElement2.content instanceof DTDContainer) {
                    Enumeration enumerationElements3 = ((DTDContainer) dTDElement2.content).getItemsVec().elements();
                    while (enumerationElements3.hasMoreElements()) {
                        removeElements(hashtable, this.dtd, (DTDItem) enumerationElements3.nextElement());
                    }
                }
            }
            if (hashtable.size() == 1) {
                Enumeration enumerationElements4 = hashtable.elements();
                this.dtd.rootElement = (DTDElement) enumerationElements4.nextElement();
            } else {
                this.dtd.rootElement = null;
            }
        } else {
            this.dtd.rootElement = null;
        }
        return this.dtd;
    }

    protected void removeElements(Hashtable hashtable, DTD dtd, DTDItem dTDItem) {
        if (dTDItem instanceof DTDName) {
            hashtable.remove(((DTDName) dTDItem).value);
        } else if (dTDItem instanceof DTDContainer) {
            Enumeration enumerationElements = ((DTDContainer) dTDItem).getItemsVec().elements();
            while (enumerationElements.hasMoreElements()) {
                removeElements(hashtable, dtd, (DTDItem) enumerationElements.nextElement());
            }
        }
    }

    protected void parseTopLevelElement() throws IOException {
        Token token = this.scanner.get();
        if (token.type == Scanner.LTQUES) {
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                stringBuffer.append(this.scanner.getUntil('?'));
                if (this.scanner.peek().type == Scanner.f2458GT) {
                    this.scanner.get();
                    this.dtd.items.addElement(new DTDProcessingInstruction(stringBuffer.toString()));
                    return;
                }
                stringBuffer.append('?');
            }
        } else {
            if (token.type == Scanner.CONDITIONAL) {
                Token tokenExpect = expect(Scanner.IDENTIFIER);
                if (tokenExpect.value.equals("IGNORE")) {
                    this.scanner.skipConditional();
                    return;
                } else {
                    if (tokenExpect.value.equals("INCLUDE")) {
                        this.scanner.skipUntil('[');
                        return;
                    }
                    throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Invalid token in conditional: ").append(tokenExpect.value).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
                }
            }
            if (token.type == Scanner.ENDCONDITIONAL) {
                return;
            }
            if (token.type == Scanner.COMMENT) {
                this.dtd.items.addElement(new DTDComment(token.value));
                return;
            }
            if (token.type == Scanner.LTBANG) {
                Token tokenExpect2 = expect(Scanner.IDENTIFIER);
                if (tokenExpect2.value.equals("ELEMENT")) {
                    parseElement();
                    return;
                }
                if (tokenExpect2.value.equals("ATTLIST")) {
                    parseAttlist();
                    return;
                }
                if (tokenExpect2.value.equals("ENTITY")) {
                    parseEntity();
                    return;
                } else if (tokenExpect2.value.equals("NOTATION")) {
                    parseNotation();
                    return;
                } else {
                    skipUntil(Scanner.f2458GT);
                    return;
                }
            }
            throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Unexpected token: ").append(token.type.name).append("(").append(token.value).append(")").toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
        }
    }

    protected void skipUntil(TokenType tokenType) throws IOException {
        Token token = this.scanner.get();
        while (token.type != tokenType) {
            token = this.scanner.get();
        }
    }

    protected Token expect(TokenType tokenType) throws IOException {
        Token token = this.scanner.get();
        if (token.type == tokenType) {
            return token;
        }
        if (token.value == null) {
            throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Expected ").append(tokenType.name).append(" instead of ").append(token.type.name).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
        }
        throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Expected ").append(tokenType.name).append(" instead of ").append(token.type.name).append("(").append(token.value).append(")").toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
    }

    protected void parseElement() throws IOException {
        Token tokenExpect = expect(Scanner.IDENTIFIER);
        DTDElement dTDElement = (DTDElement) this.dtd.elements.get(tokenExpect.value);
        if (dTDElement == null) {
            dTDElement = new DTDElement(tokenExpect.value);
            this.dtd.elements.put(dTDElement.name, dTDElement);
        } else if (dTDElement.content != null) {
            throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Found second definition of element: ").append(tokenExpect.value).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
        }
        this.dtd.items.addElement(dTDElement);
        parseContentSpec(this.scanner, dTDElement);
        expect(Scanner.f2458GT);
    }

    protected void parseContentSpec(Scanner scanner, DTDElement dTDElement) throws IOException {
        Token token = scanner.get();
        if (token.type == Scanner.IDENTIFIER) {
            if (token.value.equals("EMPTY")) {
                dTDElement.content = new DTDEmpty();
                return;
            } else {
                if (token.value.equals("ANY")) {
                    dTDElement.content = new DTDAny();
                    return;
                }
                throw new DTDParseException(scanner.getUriId(), new StringBuffer("Invalid token in entity content spec ").append(token.value).toString(), scanner.getLineNumber(), scanner.getColumn());
            }
        }
        if (token.type == Scanner.LPAREN) {
            Token tokenPeek = scanner.peek();
            if (tokenPeek.type == Scanner.IDENTIFIER) {
                if (tokenPeek.value.equals("#PCDATA")) {
                    parseMixed(dTDElement);
                    return;
                } else {
                    parseChildren(dTDElement);
                    return;
                }
            }
            if (tokenPeek.type == Scanner.LPAREN) {
                parseChildren(dTDElement);
            }
        }
    }

    protected void parseMixed(DTDElement dTDElement) throws IOException {
        DTDMixed dTDMixed = new DTDMixed();
        dTDMixed.add(new DTDPCData());
        this.scanner.get();
        dTDElement.content = dTDMixed;
        boolean z = true;
        while (true) {
            Token token = this.scanner.get();
            if (token.type == Scanner.RPAREN) {
                Token tokenPeek = this.scanner.peek();
                if (tokenPeek.type == Scanner.ASTERISK) {
                    this.scanner.get();
                    dTDMixed.cardinal = DTDCardinal.ZEROMANY;
                    return;
                } else {
                    if (!z) {
                        throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Invalid token in Mixed content type, '*' required after (#PCDATA|xx ...): ").append(tokenPeek.type.name).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
                    }
                    dTDMixed.cardinal = DTDCardinal.NONE;
                    return;
                }
            }
            if (token.type == Scanner.PIPE) {
                dTDMixed.add(new DTDName(this.scanner.get().value));
                z = false;
            } else {
                throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Invalid token in Mixed content type: ").append(token.type.name).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
        }
    }

    protected void parseChildren(DTDElement dTDElement) throws IOException {
        DTDContainer choiceSequence = parseChoiceSequence();
        Token tokenPeek = this.scanner.peek();
        choiceSequence.cardinal = parseCardinality();
        if (tokenPeek.type == Scanner.QUES) {
            choiceSequence.cardinal = DTDCardinal.OPTIONAL;
        } else if (tokenPeek.type == Scanner.ASTERISK) {
            choiceSequence.cardinal = DTDCardinal.ZEROMANY;
        } else if (tokenPeek.type == Scanner.PLUS) {
            choiceSequence.cardinal = DTDCardinal.ONEMANY;
        } else {
            choiceSequence.cardinal = DTDCardinal.NONE;
        }
        dTDElement.content = choiceSequence;
    }

    protected DTDContainer parseChoiceSequence() throws IOException {
        TokenType tokenType = null;
        DTDContainer dTDSequence = null;
        while (true) {
            DTDItem cp = parseCP();
            Token token = this.scanner.get();
            if (token.type == Scanner.PIPE || token.type == Scanner.COMMA) {
                if (tokenType != null && tokenType != token.type) {
                    throw new DTDParseException(this.scanner.getUriId(), "Can't mix separators in a choice/sequence", this.scanner.getLineNumber(), this.scanner.getColumn());
                }
                tokenType = token.type;
                if (dTDSequence == null) {
                    if (token.type == Scanner.PIPE) {
                        dTDSequence = new DTDChoice();
                    } else {
                        dTDSequence = new DTDSequence();
                    }
                }
                dTDSequence.add(cp);
            } else {
                if (token.type == Scanner.RPAREN) {
                    if (dTDSequence == null) {
                        dTDSequence = new DTDSequence();
                    }
                    dTDSequence.add(cp);
                    return dTDSequence;
                }
                throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Found invalid token in sequence: ").append(token.type.name).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
        }
    }

    protected DTDItem parseCP() throws IOException {
        DTDItem choiceSequence;
        Token token = this.scanner.get();
        if (token.type == Scanner.IDENTIFIER) {
            choiceSequence = new DTDName(token.value);
        } else if (token.type == Scanner.LPAREN) {
            choiceSequence = parseChoiceSequence();
        } else {
            throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Found invalid token in sequence: ").append(token.type.name).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
        }
        choiceSequence.cardinal = parseCardinality();
        return choiceSequence;
    }

    protected DTDCardinal parseCardinality() throws IOException {
        Token tokenPeek = this.scanner.peek();
        if (tokenPeek.type == Scanner.QUES) {
            this.scanner.get();
            return DTDCardinal.OPTIONAL;
        }
        if (tokenPeek.type == Scanner.ASTERISK) {
            this.scanner.get();
            return DTDCardinal.ZEROMANY;
        }
        if (tokenPeek.type == Scanner.PLUS) {
            this.scanner.get();
            return DTDCardinal.ONEMANY;
        }
        return DTDCardinal.NONE;
    }

    protected void parseAttlist() throws IOException {
        Token tokenExpect = expect(Scanner.IDENTIFIER);
        DTDElement dTDElement = (DTDElement) this.dtd.elements.get(tokenExpect.value);
        DTDAttlist dTDAttlist = new DTDAttlist(tokenExpect.value);
        this.dtd.items.addElement(dTDAttlist);
        if (dTDElement == null) {
            dTDElement = new DTDElement(tokenExpect.value);
            this.dtd.elements.put(tokenExpect.value, dTDElement);
        }
        Token tokenPeek = this.scanner.peek();
        while (tokenPeek.type != Scanner.f2458GT) {
            parseAttdef(this.scanner, dTDElement, dTDAttlist);
            tokenPeek = this.scanner.peek();
        }
        expect(Scanner.f2458GT);
    }

    protected void parseAttdef(Scanner scanner, DTDElement dTDElement, DTDAttlist dTDAttlist) throws IOException {
        Token tokenExpect = expect(Scanner.IDENTIFIER);
        DTDAttribute dTDAttribute = new DTDAttribute(tokenExpect.value);
        dTDAttlist.attributes.addElement(dTDAttribute);
        dTDElement.attributes.put(tokenExpect.value, dTDAttribute);
        Token token = scanner.get();
        if (token.type == Scanner.IDENTIFIER) {
            if (token.value.equals("NOTATION")) {
                dTDAttribute.type = parseNotationList();
            } else {
                dTDAttribute.type = token.value;
            }
        } else if (token.type == Scanner.LPAREN) {
            dTDAttribute.type = parseEnumeration();
        }
        Token tokenPeek = scanner.peek();
        if (tokenPeek.type == Scanner.IDENTIFIER) {
            scanner.get();
            if (tokenPeek.value.equals("#FIXED")) {
                dTDAttribute.decl = DTDDecl.FIXED;
                dTDAttribute.defaultValue = scanner.get().value;
                return;
            } else if (tokenPeek.value.equals("#REQUIRED")) {
                dTDAttribute.decl = DTDDecl.REQUIRED;
                return;
            } else {
                if (tokenPeek.value.equals("#IMPLIED")) {
                    dTDAttribute.decl = DTDDecl.IMPLIED;
                    return;
                }
                throw new DTDParseException(scanner.getUriId(), new StringBuffer("Invalid token in attribute declaration: ").append(tokenPeek.value).toString(), scanner.getLineNumber(), scanner.getColumn());
            }
        }
        if (tokenPeek.type == Scanner.STRING) {
            scanner.get();
            dTDAttribute.decl = DTDDecl.VALUE;
            dTDAttribute.defaultValue = tokenPeek.value;
        }
    }

    protected DTDNotationList parseNotationList() throws IOException {
        DTDNotationList dTDNotationList = new DTDNotationList();
        Token token = this.scanner.get();
        if (token.type != Scanner.LPAREN) {
            throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Invalid token in notation: ").append(token.type.name).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
        }
        while (true) {
            Token token2 = this.scanner.get();
            if (token2.type != Scanner.IDENTIFIER) {
                throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Invalid token in notation: ").append(token2.type.name).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
            dTDNotationList.add(token2.value);
            Token tokenPeek = this.scanner.peek();
            if (tokenPeek.type == Scanner.RPAREN) {
                this.scanner.get();
                return dTDNotationList;
            }
            if (tokenPeek.type != Scanner.PIPE) {
                throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Invalid token in notation: ").append(tokenPeek.type.name).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
            this.scanner.get();
        }
    }

    protected DTDEnumeration parseEnumeration() throws IOException {
        DTDEnumeration dTDEnumeration = new DTDEnumeration();
        while (true) {
            Token token = this.scanner.get();
            if (token.type != Scanner.IDENTIFIER && token.type != Scanner.NMTOKEN) {
                throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Invalid token in enumeration: ").append(token.type.name).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
            dTDEnumeration.add(token.value);
            Token tokenPeek = this.scanner.peek();
            if (tokenPeek.type == Scanner.RPAREN) {
                this.scanner.get();
                return dTDEnumeration;
            }
            if (tokenPeek.type != Scanner.PIPE) {
                throw new DTDParseException(this.scanner.getUriId(), new StringBuffer("Invalid token in enumeration: ").append(tokenPeek.type.name).toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
            this.scanner.get();
        }
    }

    protected void parseEntity() throws IOException {
        boolean z;
        DTDEntity dTDEntity;
        Token tokenExpect = this.scanner.get();
        boolean z2 = true;
        if (tokenExpect.type == Scanner.PERCENT) {
            tokenExpect = expect(Scanner.IDENTIFIER);
            z = true;
        } else {
            if (tokenExpect.type != Scanner.IDENTIFIER) {
                throw new DTDParseException(this.scanner.getUriId(), "Invalid entity declaration", this.scanner.getLineNumber(), this.scanner.getColumn());
            }
            z = false;
        }
        if (((DTDEntity) this.dtd.entities.get(tokenExpect.value)) == null) {
            dTDEntity = new DTDEntity(tokenExpect.value, this.defaultLocation);
            this.dtd.entities.put(dTDEntity.name, dTDEntity);
            z2 = false;
        } else {
            dTDEntity = new DTDEntity(tokenExpect.value, this.defaultLocation);
        }
        this.dtd.items.addElement(dTDEntity);
        dTDEntity.isParsed = z;
        parseEntityDef(dTDEntity);
        if (!dTDEntity.isParsed || dTDEntity.value == null || z2) {
            return;
        }
        this.scanner.addEntity(dTDEntity.name, dTDEntity.value);
    }

    protected void parseEntityDef(DTDEntity dTDEntity) throws IOException {
        Token token = this.scanner.get();
        if (token.type == Scanner.STRING) {
            if (dTDEntity.value == null) {
                dTDEntity.value = token.value;
            }
        } else if (token.type == Scanner.IDENTIFIER) {
            if (token.value.equals("SYSTEM")) {
                DTDSystem dTDSystem = new DTDSystem();
                dTDSystem.system = expect(Scanner.STRING).value;
                dTDEntity.externalID = dTDSystem;
            } else if (token.value.equals("PUBLIC")) {
                DTDPublic dTDPublic = new DTDPublic();
                dTDPublic.pub = expect(Scanner.STRING).value;
                dTDPublic.system = expect(Scanner.STRING).value;
                dTDEntity.externalID = dTDPublic;
            } else {
                throw new DTDParseException(this.scanner.getUriId(), "Invalid External ID specification", this.scanner.getLineNumber(), this.scanner.getColumn());
            }
            if (!dTDEntity.isParsed) {
                Token tokenPeek = this.scanner.peek();
                if (tokenPeek.type == Scanner.IDENTIFIER) {
                    if (!tokenPeek.value.equals("NDATA")) {
                        throw new DTDParseException(this.scanner.getUriId(), "Invalid NData declaration", this.scanner.getLineNumber(), this.scanner.getColumn());
                    }
                    this.scanner.get();
                    dTDEntity.ndata = expect(Scanner.IDENTIFIER).value;
                }
            }
        } else {
            throw new DTDParseException(this.scanner.getUriId(), "Invalid entity definition", this.scanner.getLineNumber(), this.scanner.getColumn());
        }
        expect(Scanner.f2458GT);
    }

    protected void parseNotation() throws IOException {
        DTDNotation dTDNotation = new DTDNotation();
        dTDNotation.name = expect(Scanner.IDENTIFIER).value;
        this.dtd.notations.put(dTDNotation.name, dTDNotation);
        this.dtd.items.addElement(dTDNotation);
        Token tokenExpect = expect(Scanner.IDENTIFIER);
        if (tokenExpect.value.equals("SYSTEM")) {
            DTDSystem dTDSystem = new DTDSystem();
            dTDSystem.system = expect(Scanner.STRING).value;
            dTDNotation.externalID = dTDSystem;
        } else if (tokenExpect.value.equals("PUBLIC")) {
            DTDPublic dTDPublic = new DTDPublic();
            dTDPublic.pub = expect(Scanner.STRING).value;
            dTDPublic.system = null;
            if (this.scanner.peek().type == Scanner.STRING) {
                dTDPublic.system = this.scanner.get().value;
            }
            dTDNotation.externalID = dTDPublic;
        }
        expect(Scanner.f2458GT);
    }

    @Override
    public DTDEntity expandEntity(String str) {
        return (DTDEntity) this.dtd.entities.get(str);
    }
}
