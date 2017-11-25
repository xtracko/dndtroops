package cz.muni.fi.pa165.dndtroops.service;

/**
 * @author Jiří Novotný
 */

public interface RandomService {
    /**
     * Generate next value from normal distribution.
     *
     * @return random value from normal distribution
     */
    float nextNormal();
}
