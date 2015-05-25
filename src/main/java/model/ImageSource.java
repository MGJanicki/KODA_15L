package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Marcin Janicki on 2015-05-12.
 */
public class ImageSource extends Source {

    private BufferedImage img;
    private int width;
    private int height;
    private int currentPosition;
    private boolean finished;
    private int previousValue;

    public ImageSource(String path) {
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Nie mozna odnalezc pliku: " + path);
        }
        width = img.getWidth();
        height = img.getHeight();
        currentPosition = 0;
        finished = false;
        previousValue = 0;
    }

    @Override
    public int getNextValue() {
        if (!finished) {
            int x = currentPosition % width;
            int y = (int) Math.floor((double) currentPosition / width);
            currentPosition++;
            if (currentPosition == width * height) {
                finished = true;
            }
            int currentValue = img.getRGB(x, y);
            int result = currentValue - previousValue;
            previousValue = currentValue;
            return result;
        } else {
            return 0;
        }

    }

    public boolean isFinished() {
        return finished;
    }

    public void reset() {
        finished = false;
        currentPosition = 0;
    }

}
