package sources;

import model.GaussianSource;
import model.UnifiedSource;
import org.junit.Test;
import service.ExpGolomb;

import java.util.List;

/**
 * Created by Marcin Janicki on 2015-05-25.
 */
public class GaussianSourceTest {

    @Test
    public void gaussianSourceTest()
    {
        String encodedSequence = "";
        ExpGolomb expGolomb = new ExpGolomb(new GaussianSource(0, 84)); //99,7% wartości z zakresu wartość średnia +/- 3 * odchylenie standardowe

        for(int i = 0; i < 10000; ++i)
        {
            String codeWord = expGolomb.encode();
            encodedSequence = encodedSequence.concat(codeWord);
        }
        List<Integer> decodedSymbols = expGolomb.decode(encodedSequence);
        System.out.println(decodedSymbols);

        System.out.println("Średnia długość zakodowanego symbolu to " + ((double)encodedSequence.length()/10000));
    }
}
