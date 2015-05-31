package sources;

import model.GaussianSource;
import model.LaplaceSource;
import org.junit.Test;
import service.ExpGolomb;

import java.util.List;

/**
 * Created by Marcin Janicki on 2015-05-25.
 */
public class LaplacianSourceTest {

    @Test
    public void laplacianSourceTest()
    {
        String encodedSequence = "";
        ExpGolomb expGolomb = new ExpGolomb(new LaplaceSource(0.0, 80.0));

        for(int i = 0; i < 10000; ++i)
        {
            String codeWord = expGolomb.encode();
            encodedSequence = encodedSequence.concat(codeWord);
        }
        List<Integer> decodedSymbols = expGolomb.decode(encodedSequence);
        System.out.println(decodedSymbols);

        int counter = 0;
        for(Integer symbol: decodedSymbols)
        {
            if(symbol < -255 || symbol > 255) counter++;
        }

        System.out.println("Średnia długość zakodowanego symbolu to " + ((double)encodedSequence.length()/10000));
        System.out.println("% wartosci spoza przedziału <-255, 255> " + (double)counter/100);
    }

}
