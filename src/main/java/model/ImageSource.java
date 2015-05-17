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

    public ImageSource(String path)
    {
        try
        {
            img = ImageIO.read(new File(path));
        }
        catch (IOException e)
        {
            System.out.println("Nie mozna odnalezc pliku: " + path);
        }
        width = img.getWidth();
        height = img.getHeight();
        currentPosition = 0;
        finished = false;
    }

    @Override
    public int getNextValue()
    {
        if(!finished)
        {
            int x = currentPosition % width;
            int y = (int) Math.floor((double) currentPosition / width);
            currentPosition++;
            if(currentPosition == width * height)
            {
                finished = true;
            }
            return img.getRGB(x, y);
        }
        else
        {
            return 0;
        }

    }

    public boolean isFinished()
    {
        return finished;
    }

    public void reset()
    {
        finished = false;
        currentPosition = 0;
    }

}
