package com.brother.sdk.common.device;

public enum CountrySpec {
    USCanada,
    China,
    Japan,
    Asia,
    EastAsia,
    Europe,
    Oceania,
    Others;

    private static final String ASA = "ZA/TR/VN/ID/IN/PH/SA/MY/AF";

    private static final String f480CN = "CN";
    private static final String ESA = "HK/TW/KR";

    private static final String f481EU = "GE/FR/BE/AT/LU/IT/UK/DK/ES/PT/CH/SE/NO/IE/SM/NL/MC/MA/RU/FI/VA/IL";

    private static final String f482JP = "JP";
    private static final String OCE = "AU/NZ/FJ/PG/WS/VU";

    private static final String f483US = "US/CA/AR/BR/BO/CL/PR/PE/PY/UY/CR/KY/BM/BZ/BB/BS/MX";

    public static CountrySpec fromISO_3166_1_Alpha2(String str) {
        if (f483US.contains(str)) {
            return USCanada;
        }
        if (f481EU.contains(str)) {
            return Europe;
        }
        if (ASA.contains(str)) {
            return Asia;
        }
        if (ESA.contains(str)) {
            return EastAsia;
        }
        if (OCE.contains(str)) {
            return Oceania;
        }
        if (f480CN.contains(str)) {
            return China;
        }
        if (f482JP.contains(str)) {
            return Japan;
        }
        return Others;
    }
}
