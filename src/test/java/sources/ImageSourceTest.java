package sources;

import model.ImageSource;
import org.junit.Test;
import service.ExpGolomb;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kali
 */
public class ImageSourceTest {

    @Test
    public void minibouTest(){
        imgageTest("miniboy.jpg");
    }

    private void imgageTest(String filename){
        ImageSource imageSource = new ImageSource(filename);
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
            imageOutputBuffer.add(i + previousValue);
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

        System.out.println("Średnia długość zakodowanego symbolu to " + ((double)encodedSequence.length()/(imageSource.getWidth() * imageSource.getHeight())));
        System.out.println("Czas kodowania: " + (encodingTime - startTime));
        System.out.println("Czas dekodowania: " + (decodingTime - encodingTime));
    }
}
