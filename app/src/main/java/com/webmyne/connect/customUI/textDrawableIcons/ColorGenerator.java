package com.webmyne.connect.customUI.textDrawableIcons;

/**
 * Created by priyasindkar on 12-02-2016.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.webmyne.connect.Utils.Constants;

/**
 * @author amulya
 * @datetime 14 Oct 2014, 5:20 PM
 */
public class ColorGenerator {

    public static ColorGenerator DEFAULT;

    public static ColorGenerator MATERIAL;

    static {
        DEFAULT = create(Arrays.asList(
                0xfff16364,
                0xfff58559,
                0xfff9a43e,
                0xffe4c62e,
                0xff67bf74,
                0xff59a2be,
                0xff2093cd,
                0xffad62a7,
                0xff805781
        ));
        MATERIAL = create(Arrays.asList(
              /*  0xffe57373,
                0xfff06292,
                0xffba68c8,
                0xff9575cd,
                0xff7986cb,
                0xff64b5f6,
                0xff4fc3f7,
                0xff4dd0e1,
                0xff4db6ac,
                0xff81c784,
                0xffaed581,
                0xffff8a65,
                0xffd4e157,
                0xffffd54f,
                0xffffb74d,
                0xffa1887f,
                0xff90a4ae*/
                0xff009688,
                0xfff44336,
                0xff673AB7,
                0xff2196F3,
                0xffE91E63,
                0xff00BCD4,
                0xff4CAF50,
                0xffFF9800,
                0xff795548,
                0xff607D8B
        ));
    }

    private final List<Integer> mColors;
    private final Random mRandom;

    public static ColorGenerator create(List<Integer> colorList) {
        return new ColorGenerator(colorList);
    }

    private ColorGenerator(List<Integer> colorList) {
        mColors = colorList;
        mRandom = new Random(System.currentTimeMillis());
    }

    public ArrayList<Integer> getRandomColor() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < Constants.VERTICAL_SHORT_NAMES.size()) {

            int random = randomGenerator .nextInt(mColors.size());
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        return numbers;
        //return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public int getARandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public int getColor(Object key) {
        return mColors.get(Math.abs(key.hashCode()) % mColors.size());
    }
    public int getColorAtIndex(int index) {
        return mColors.get(index);
    }



}
