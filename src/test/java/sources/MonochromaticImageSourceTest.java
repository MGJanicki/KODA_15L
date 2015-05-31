package sources;

import model.ImageSource;
import model.MonochromaticImageSource;
import org.junit.Test;
import service.ExpGolomb;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Janicki on 2015-05-31.
 */
public class MonochromaticImageSourceTest {

    @Test
    public void miniboyTest(){
        MonochromaticImageSource imageSource = new MonochromaticImageSource("miniboy.jpg");
        String encodedSequence = "";
        ExpGolomb expGolomb = new ExpGolomb(imageSource);

        while (!imageSource.isFinished())
        {
            String codeWord = expGolomb.encode();
            encodedSequence = encodedSequence.concat(codeWord);
        }
        List<Integer> decodedSymbols = expGolomb.decode(encodedSequence);

        List<Integer> imageOutputBuffer = new ArrayList<>();
        int previousValue = 0;
        for(Integer i : decodedSymbols)
        {
            imageOutputBuffer.add(imageSource.convertToRGB(i + previousValue));
            previousValue = i + previousValue;
        }

        File result = new File("result.jpg");
        BufferedImage bufferedImage = new BufferedImage(100, 100, 5);
        for(int i = 0; i < imageOutputBuffer.size(); ++i){
            int x = i % 100;
            int y = (int) Math.floor((double) i / 100);
            bufferedImage.setRGB(x, y, imageOutputBuffer.get(i));
        }
        try {
            ImageIO.write(bufferedImage, "jpg", result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Średnia długość zakodowanego symbolu to " + ((double)encodedSequence.length()/10000));
    }

}