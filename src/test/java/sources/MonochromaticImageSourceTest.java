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
    public void minibouTest(){
        imgageTest("miniboy.jpg");
    }

    @Test
    public void dzieciTest(){
        imgageTest("dzieci.jpg");
    }

    @Test
    public void kwadratyTest(){
        imgageTest("kwadraty.jpg");
    }

    @Test
    public void lionTest(){
        imgageTest("lion.jpg");
    }

    @Test
    public void gradient1Test(){
        imgageTest("gradient1.jpg");
    }

    @Test
    public void gradient2Test(){
        imgageTest("gradient2.jpg");
    }

    private void imgageTest(String filename){
        MonochromaticImageSource imageSource = new MonochromaticImageSource(filename);
        String encodedSequence = "";
        ExpGolomb expGolomb = new ExpGolomb(imageSource);

        long startTime = System.currentTimeMillis();
        while (!imageSource.isFinished())
        {
            String codeWord = expGolomb.encode();
            encodedSequence = encodedSequence.concat(codeWord);
        }
        long encodingTime = System.currentTimeMillis();
        List<Integer> decodedSymbols = expGolomb.decode(encodedSequence);

        List<Integer> imageOutputBuffer = new ArrayList<>();
        int previousValue = 0;
        for(Integer i : decodedSymbols)
        {
            imageOutputBuffer.add(imageSource.convertToRGB(i + previousValue));
            previousValue = i + previousValue;
        }
        long decodingTime = System.currentTimeMillis();

        File result = new File(filename.split("\\.")[0] + "_result.jpg");
        BufferedImage bufferedImage = new BufferedImage(imageSource.getWidth(), imageSource.getHeight(), 5);
        for(int i = 0; i < imageOutputBuffer.size(); ++i){
            int x = i % imageSource.getWidth();
            int y = (int) Math.floor((double) i / imageSource.getHeight());
            bufferedImage.setRGB(x, y, imageOutputBuffer.get(i));
        }
        try {
            ImageIO.write(bufferedImage, "jpg", result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Średnia długość zakodowanego symbolu to " + ((double)encodedSequence.length()/10000));
        System.out.println("Czas kodowania: " + (encodingTime - startTime));
        System.out.println("Czas dekodowania: " + (decodingTime - encodingTime));
    }

}
