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

    /**
     * Generate next integer value from uniform distribution from interval (0, bound]
     *
     * @param bound an upper bound of an interval to generate random value from
     * @return random integer from uniform distribution
     */
    int nextInt(int bound);

    /**
     * Generate random boolean with a given probability.
     *
     * @param probability the probability the boolean will be true
     * @return random boolean with a given probability
     */
    boolean nextBoolean(float probability);
}
