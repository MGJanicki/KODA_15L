package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Marcin Janicki on 2015-05-12.
 */
public class ImageSource extends Source {

    protected BufferedImage img;
    protected int width;
    protected int height;
    protected int currentPosition;
    protected boolean finished;
    protected int previousValue;

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
            int currentValue = getNextSymbol();
            int result = currentValue - previousValue;
            previousValue = currentValue;
            return result;
        } else {
            return 0;
        }

    }

    protected int getNextSymbol()
    {
        int x = currentPosition % width;
        int y = (int) Math.floor((double) currentPosition / width);
        currentPosition++;
        if (currentPosition == width * height) {
            finished = true;
        }
        return img.getRGB(x, y);
    }

    public boolean isFinished() {
        return finished;
    }

    public void reset() {
        finished = false;
        currentPosition = 0;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}
