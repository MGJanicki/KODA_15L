package model;

/**
 * Created by Marcin Janicki on 2015-05-12.
 *
 * mi - okrela przesuniecie rozkladu wzgledem zera.
 * b - parametr skalujacy
 */
public class LaplaceSource extends RandomSource {

    private Double mi;
    private Double beta;

    public LaplaceSource(Double m, Double b) {
        mi = m;
        beta = b;
    }

    @Override
    public int getNextValue() {
        // liczba losowa rozkladu normalnego z przedzialu (-0.5, 0.5]
        Double uniformRandom = random.nextDouble() - 0.5;
        Double value = mi - beta * Math.signum(uniformRandom) * Math.log(1 - 2 * Math.abs(uniformRandom));
        return Math.round(Math.round(value));
    }

}
