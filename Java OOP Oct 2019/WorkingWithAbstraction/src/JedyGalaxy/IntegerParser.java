package JedyGalaxy;

import java.util.Arrays;

public class IntegerParser {
    public static int[] parseIntegerArray (String string){

        return Arrays
                .stream(string.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
