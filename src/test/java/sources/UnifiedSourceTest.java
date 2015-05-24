package sources;

import model.UnifiedSource;
import org.junit.Test;
import service.ExpGolomb;

import java.util.List;

/**
 * Created by Marcin Janicki on 2015-05-25.
 */
public class UnifiedSourceTest {

    @Test
    public void unifiedSourceTest()
    {
        String encodedSequence = "";
        ExpGolomb expGolomb = new ExpGolomb(new UnifiedSource());
        ((UnifiedSource)expGolomb.getSource()).setMaxValue(-256);
        ((UnifiedSource)expGolomb.getSource()).setMinValue(255);

        for(int i = 0; i < 10000; ++i) //10000 symboli jak w obrazku
        {
            String codeWord = expGolomb.encode();
            encodedSequence = encodedSequence.concat(codeWord);
        }
        List<Integer> decodedSymbols = expGolomb.decode(encodedSequence);
        System.out.println(decodedSymbols);

        System.out.println("Średnia długość zakodowanego symbolu to " + ((double)encodedSequence.length()/10000));
    }
}
