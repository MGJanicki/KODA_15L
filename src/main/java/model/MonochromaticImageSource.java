package model;

/**
 * Created by Marcin Janicki on 2015-05-31.
 */
public class MonochromaticImageSource extends ImageSource {

    public MonochromaticImageSource(String path) {
        super(path);
    }

    @Override
    public int getNextValue()
    {
        if (!finished)
        {
            int currentValue = convertToYUV(getNextSymbol());
            int result = currentValue - previousValue;
            previousValue = currentValue;
            return result;
        }
        else
        {
            return 0;
        }
    }

    private int convertToYUV(int rgb)
    {
        int blue = rgb & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int red = (rgb >> 16) & 0xFF;

        //w sumie to potrzebne jest tylko wyznaczenie składowej Y - w obrazie monochromatycznym tylko ona niesie informację
        double y = 0.299d * red + 0.587d * green + 0.114d * blue;

        return (int)y;
    }

    public int convertToRGB(int y)
    {
        int rgb = 0;
        rgb = rgb + y + (y << 8) + (y << 16);
        return rgb;
    }
}
