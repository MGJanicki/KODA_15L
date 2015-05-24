package model;

/**
 * Created by Marcin Janicki on 2015-05-12.
 */
public class UnifiedSource extends RandomSource {

    private int maxValue = Integer.MAX_VALUE/2;

    private int minValue = Integer.MIN_VALUE/2;

    @Override
    public int getNextValue() {
        if(minValue > maxValue)
        {
            int tmp = minValue;
            minValue = maxValue;
            maxValue = tmp;
        }
        else if(minValue == maxValue)
        {
            throw new IllegalArgumentException("Interval's length cannot be 0");
        }
        int intervalLength = maxValue - minValue;
        int intToReturn = random.nextInt(intervalLength); //wygeneruje liczbę dodatnią [0, długośćPrzedziału]
        return intToReturn + minValue; //po dodaniu minimalnej wartości (być może ujemnej) wynikiem będzie żądany przedział
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
}
