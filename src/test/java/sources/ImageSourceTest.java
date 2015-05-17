package sources;

import model.ImageSource;
import org.junit.Test;
import service.ExpGolomb;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by kali
 */
public class ImageSourceTest {

    //TODO: dodac kodowanie roznicowe
    @Test
    public void miniboyTest(){
        ImageSource imageSource = new ImageSource("miniboy.jpg");
        String encodedSequence = "";
        ExpGolomb expGolomb = new ExpGolomb(imageSource);

        while (!imageSource.isFinished())
        {
            String codeWord = expGolomb.encode();
            System.out.println(codeWord);
            encodedSequence = encodedSequence.concat(codeWord);
        }
        List<Integer> decodedSymbols = expGolomb.decode(encodedSequence);

        File result = new File("result.jpg");
        BufferedImage bufferedImage = new BufferedImage(100, 100, 5);
        for(int i = 0; i < decodedSymbols.size(); ++i){
            int x = i % 100;
            int y = (int) Math.floor((double) i / 100);
            bufferedImage.setRGB(x, y, decodedSymbols.get(i));
        }
        try {
            ImageIO.write(bufferedImage, "jpg", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}