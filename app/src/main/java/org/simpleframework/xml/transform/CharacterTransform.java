package org.simpleframework.xml.transform;

class CharacterTransform implements Transform<Character> {
    CharacterTransform() {
    }

    @Override
    public Character read(String str) throws Exception {
        if (str.length() != 1) {
            throw new InvalidFormatException("Cannot convert '%s' to a character", str);
        }
        return Character.valueOf(str.charAt(0));
    }

    @Override
    public String write(Character ch) throws Exception {
        return ch.toString();
    }
}
