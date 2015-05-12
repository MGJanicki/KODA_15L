package model;

/**
 * Created by Marcin Janicki on 2015-05-12.
 */
public class UnifiedSource extends RandomSource {

    private int maxValue = Integer.MAX_VALUE;

    @Override
    public int getNextValue() {
        return random.nextInt(maxValue);
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
