package cz.muni.fi.pa165.dndtroops.enums;

/**
 * @author Jiří Novotný
 */

public enum Power {
    MAGIC, WEAPONS, MARTIAL_ARTS;

    /**
     * Checks if the string contains any valueOf string.
     *
     * @param valueString a string to search for
     * @return true if the string is contained within the enum, false otherwise
     */
    public static boolean contains(final String valueString) {
        for (Power os : Power.values()) {
            if (os.name().equals(valueString)) {
                return true;
            }
        }
        return false;
    }
}
