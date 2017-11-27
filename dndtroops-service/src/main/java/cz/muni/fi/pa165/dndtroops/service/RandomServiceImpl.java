package cz.muni.fi.pa165.dndtroops.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Jiří Novotný
 */

@Service
public class RandomServiceImpl implements RandomService {

    private Random rng = new Random();

    @Override
    public float nextNormal() {
        return (float)rng.nextGaussian();
    }

    @Override
    public int nextInt(int bound) {
        return rng.nextInt(bound);
    }

    @Override
    public boolean nextBoolean(float probability) {
        return Float.compare(probability, rng.nextFloat()) < 0;
    }

}
