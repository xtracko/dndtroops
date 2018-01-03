package cz.muni.fi.pa165.dndtroops.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Jiří Novotný
 */

@Service
public class RandomServiceImpl implements RandomService {

    private Random rng = new Random();

    @Override
    public double nextNormal() {
        return rng.nextGaussian();
    }

    @Override
    public int nextInt(int bound) {
        return rng.nextInt(bound);
    }

    @Override
    public boolean nextBoolean(float probability) {
        return Float.compare(probability, rng.nextFloat()) < 0;
    }

    @Override
    public void shuffle(List<?> collection) {
        Collections.shuffle(collection, rng);
    }
}
