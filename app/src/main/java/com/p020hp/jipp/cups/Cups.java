package com.p020hp.jipp.cups;

import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.p020hp.jipp.encoding.BitfieldType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.encoding.TextType;
import com.p020hp.jipp.encoding.UriType;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\t"}, m1293d2 = {"Lcom/hp/jipp/cups/Cups;", "", "()V", "AuthInfoRequired", "MarkerTypes", "NotifyEvents", "Operation", "PageBorder", "Types", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class Cups {
    public static final Cups INSTANCE = new Cups();

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m1293d2 = {"Lcom/hp/jipp/cups/Cups$Operation;", "", "()V", "cupsAddModifyClass", "Lcom/hp/jipp/model/Operation;", "cupsAddModifyPrinter", "cupsAuthenticateJob", "cupsDeleteClass", "cupsDeletePrinter", "cupsGetClasses", "cupsGetDefault", "cupsGetDocument", "cupsGetPrinters", "cupsMoveJob", "cupsSetDefault", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Operation {
        public static final Operation INSTANCE = new Operation();
        public static final com.p020hp.jipp.model.Operation cupsGetDefault = new com.p020hp.jipp.model.Operation(ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_EXECUTING, "CUPS-Get-Default");
        public static final com.p020hp.jipp.model.Operation cupsGetPrinters = new com.p020hp.jipp.model.Operation(16386, "CUPS-Get-Printers");
        public static final com.p020hp.jipp.model.Operation cupsAddModifyPrinter = new com.p020hp.jipp.model.Operation(ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_WAITING, "CUPS-Add-Modify-Printer");
        public static final com.p020hp.jipp.model.Operation cupsDeletePrinter = new com.p020hp.jipp.model.Operation(ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_CANCELING, "CUPS-Delete-Printer");
        public static final com.p020hp.jipp.model.Operation cupsGetClasses = new com.p020hp.jipp.model.Operation(ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_NOTEXIST, "CUPS-Get-Classes");
        public static final com.p020hp.jipp.model.Operation cupsAddModifyClass = new com.p020hp.jipp.model.Operation(ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_CANCELOK, "CUPS-Add-Modify-Class");
        public static final com.p020hp.jipp.model.Operation cupsDeleteClass = new com.p020hp.jipp.model.Operation(ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_PAUSEING, "CUPS-Delete-Class");
        public static final com.p020hp.jipp.model.Operation cupsSetDefault = new com.p020hp.jipp.model.Operation(16394, "CUPS-Set-Default");
        public static final com.p020hp.jipp.model.Operation cupsMoveJob = new com.p020hp.jipp.model.Operation(16397, "CUPS-Move-Job");
        public static final com.p020hp.jipp.model.Operation cupsAuthenticateJob = new com.p020hp.jipp.model.Operation(16398, "CUPS-Authenticate-Job");
        public static final com.p020hp.jipp.model.Operation cupsGetDocument = new com.p020hp.jipp.model.Operation(16423, "CUPS-Get-Document");

        private Operation() {
        }
    }

    private Cups() {
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u00158\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00178\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00178\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u00178\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u00158\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u00158\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u001f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020!8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020$8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020$8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u00020\u00158\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020\u00158\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006("}, m1293d2 = {"Lcom/hp/jipp/cups/Cups$Types;", "", "()V", "authInfo", "Lcom/hp/jipp/encoding/TextType$Set;", "authInfoRequired", "Lcom/hp/jipp/encoding/KeywordType$Set;", "deviceUri", "Lcom/hp/jipp/encoding/UriType;", "jobKLimit", "Lcom/hp/jipp/encoding/IntType;", "jobMediaProgress", "jobOriginatingHostName", "Lcom/hp/jipp/encoding/NameType;", "jobPageLimit", "jobPrinterStateMessage", "Lcom/hp/jipp/encoding/TextType;", "jobPrinterStateReasons", "jobQuotaPeriod", "markerChangeTime", "markerColors", "Lcom/hp/jipp/encoding/NameType$Set;", "markerHighLevels", "Lcom/hp/jipp/encoding/IntType$Set;", "markerLevels", "markerLowLevels", "markerMessage", "markerNames", "markerTypes", "memberNames", "memberUris", "Lcom/hp/jipp/encoding/UriType$Set;", "pageBorder", "Lcom/hp/jipp/encoding/KeywordType;", "printerId", "printerType", "Lcom/hp/jipp/encoding/BitfieldType;", "printerTypeMask", "requestingUserNameAllowed", "requestingUserNameDenied", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Types {
        public static final Types INSTANCE = new Types();
        public static final TextType.Set authInfo = new TextType.Set("auth-info");
        public static final KeywordType.Set authInfoRequired = new KeywordType.Set("auth-info-required");
        public static final UriType deviceUri = new UriType("device-uri");
        public static final IntType jobKLimit = new IntType("job-k-limit");
        public static final IntType jobMediaProgress = new IntType("job-media-progress");
        public static final NameType jobOriginatingHostName = new NameType("job-originating-host-name");
        public static final IntType jobPageLimit = new IntType("job-page-limit");
        public static final TextType jobPrinterStateMessage = new TextType("job-printer-state-message");
        public static final KeywordType.Set jobPrinterStateReasons = new KeywordType.Set("job-printer-state-reasons");
        public static final IntType jobQuotaPeriod = new IntType("job-quota-period");
        public static final IntType markerChangeTime = new IntType("marker-change-time");
        public static final NameType.Set markerColors = new NameType.Set("marker-colors");
        public static final IntType.Set markerHighLevels = new IntType.Set("marker-high-levels");
        public static final IntType.Set markerLevels = new IntType.Set("marker-levels");
        public static final IntType.Set markerLowLevels = new IntType.Set("marker-low-levels");
        public static final TextType markerMessage = new TextType("marker-message");
        public static final NameType.Set markerNames = new NameType.Set("marker-names");
        public static final KeywordType.Set markerTypes = new KeywordType.Set("marker-types");
        public static final NameType.Set memberNames = new NameType.Set("member-names");
        public static final UriType.Set memberUris = new UriType.Set("member-uris");
        public static final KeywordType pageBorder = new KeywordType("page-border");
        public static final IntType printerId = new IntType("printer-id");
        public static final BitfieldType printerType = new BitfieldType("printer-type");
        public static final BitfieldType printerTypeMask = new BitfieldType("printer-type-mask");
        public static final NameType.Set requestingUserNameAllowed = new NameType.Set("requesting-user-name-allowed");
        public static final NameType.Set requestingUserNameDenied = new NameType.Set("requesting-user-name-denied");

        private Types() {
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m1293d2 = {"Lcom/hp/jipp/cups/Cups$AuthInfoRequired;", "", "()V", "domain", "", "negotiate", "none", AuthInfoRequired.password, AuthInfoRequired.username, "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class AuthInfoRequired {
        public static final AuthInfoRequired INSTANCE = new AuthInfoRequired();
        public static final String domain = "domain";
        public static final String negotiate = "negotiate";
        public static final String none = "none";
        public static final String password = "password";
        public static final String username = "username";

        private AuthInfoRequired() {
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001e\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\""}, m1293d2 = {"Lcom/hp/jipp/cups/Cups$MarkerTypes;", "", "()V", "bandingSupply", "", "bindingSupply", "cleanerUnit", "coronaWire", MarkerTypes.covers, MarkerTypes.developer, MarkerTypes.fuser, "fuserCleaningPad", "fuserOil", "fuserOilWick", "fuserOiler", MarkerTypes.ink, "inkCartridge", "inkRibbon", MarkerTypes.inserts, MarkerTypes.opc, "paperWrap", "ribbonWax", "shrinkWrap", "solidWax", MarkerTypes.staples, "stichingWire", MarkerTypes.toner, "tonerCartridge", "transferUnit", "wasteInk", "wasteToner", "wasteWater", "wasteWax", MarkerTypes.water, "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class MarkerTypes {
        public static final MarkerTypes INSTANCE = new MarkerTypes();
        public static final String bandingSupply = "banding-supply";
        public static final String bindingSupply = "binding-supply";
        public static final String cleanerUnit = "cleaner-unit";
        public static final String coronaWire = "corona-wire";
        public static final String covers = "covers";
        public static final String developer = "developer";
        public static final String fuser = "fuser";
        public static final String fuserCleaningPad = "fuser-cleaning-pad";
        public static final String fuserOil = "fuser-oil";
        public static final String fuserOilWick = "fuser-oil-wick";
        public static final String fuserOiler = "fuser-oiler";
        public static final String ink = "ink";
        public static final String inkCartridge = "ink-cartridge";
        public static final String inkRibbon = "ink-ribbon";
        public static final String inserts = "inserts";
        public static final String opc = "opc";
        public static final String paperWrap = "paper-wrap";
        public static final String ribbonWax = "ribbon-wax";
        public static final String shrinkWrap = "shrink-wrap";
        public static final String solidWax = "solid-wax";
        public static final String staples = "staples";
        public static final String stichingWire = "stiching-wire";
        public static final String toner = "toner";
        public static final String tonerCartridge = "toner-cartridge";
        public static final String transferUnit = "transfer-unit";
        public static final String wasteInk = "waste-ink";
        public static final String wasteToner = "waste-toner";
        public static final String wasteWater = "waste-water";
        public static final String wasteWax = "waste-wax";
        public static final String water = "water";

        private MarkerTypes() {
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m1293d2 = {"Lcom/hp/jipp/cups/Cups$NotifyEvents;", "", "()V", "printerAdded", "", "printerDeleted", "printerModified", "serverAudit", "serverRestarted", "serverStarted", "serverStopped", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class NotifyEvents {
        public static final NotifyEvents INSTANCE = new NotifyEvents();
        public static final String printerAdded = "printer-added";
        public static final String printerDeleted = "printer-deleted";
        public static final String printerModified = "printer-modified";
        public static final String serverAudit = "server-audit";
        public static final String serverRestarted = "server-restarted";
        public static final String serverStarted = "server-started";
        public static final String serverStopped = "server-stopped";

        private NotifyEvents() {
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m1293d2 = {"Lcom/hp/jipp/cups/Cups$PageBorder;", "", "()V", "_double", "", "doubleThick", "none", PageBorder.single, "singleThick", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class PageBorder {
        public static final PageBorder INSTANCE = new PageBorder();
        public static final String _double = "double";
        public static final String doubleThick = "double-thick";
        public static final String none = "none";
        public static final String single = "single";
        public static final String singleThick = "single-thick";

        private PageBorder() {
        }
    }
}
