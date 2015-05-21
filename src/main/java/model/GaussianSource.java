package model;

/**
 * Created by Marcin Janicki on 2015-05-12.
 */
public class GaussianSource extends RandomSource {

    private double meanValue;
    private double standardDeviation;

    public GaussianSource(double meanValue, double standardDeviation) {
        this.meanValue = meanValue;
        this.standardDeviation = standardDeviation;
    }

    @Override
    public int getNextValue() {
        //Jesli X ~ N(mi,sigma^2) , oraz a, b , s¹ liczbami rzeczywistymi,
        // to aX + b ~ N(a*mi + b, (a*sigma)^2)
        //wiec a=sqrt(sigma) i b=mi
        return (int)((Math.sqrt(standardDeviation) * random.nextGaussian()) + meanValue);
    }

}
