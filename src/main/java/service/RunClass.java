package service;

import model.ImageSource;
import model.UnifiedSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Marcin Janicki on 2015-05-12.
 */
public class RunClass {

    public static void main(String args[]) {
        String encodedSequence = "";
        ExpGolomb expGolomb = new ExpGolomb(new UnifiedSource());
        ((UnifiedSource)expGolomb.getSource()).setMaxValue(5); //bo można zweryfikować z wikipedią :)

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
