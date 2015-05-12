package model;

/**
 * Created by Marcin Janicki on 2015-05-12.
 */
public class UnifiedSource extends RandomSource {

    @Override
    public int getNextValue() {
        return random.nextInt();
    }

}
