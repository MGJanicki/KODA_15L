package service;

import model.GaussianSource;
import model.UnifiedSource;

import java.util.List;

/**
 * Created by Marcin Janicki on 2015-05-12.
 */
public class RunClass {

    public static void main(String args[]) {
        String encodedSequence = "";
        //ExpGolomb expGolomb = new ExpGolomb(new GaussianSource(4, 1));
        ExpGolomb expGolomb = new ExpGolomb(new UnifiedSource());
        ((UnifiedSource)expGolomb.getSource()).setMaxValue(-5); //bo można zweryfikować z wikipedią :)
        ((UnifiedSource)expGolomb.getSource()).setMinValue(5); //bo można zweryfikować z wikipedią :)

        for(int i = 0; i < 10; ++i)
        {
            String codeWord = expGolomb.encode();
            System.out.println(codeWord);
            encodedSequence = encodedSequence.concat(codeWord);
        }
        List<Integer> decodedSymbols = expGolomb.decode(encodedSequence);
        System.out.println(decodedSymbols);
    }

}
