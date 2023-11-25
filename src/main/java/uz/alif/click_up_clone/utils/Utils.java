package uz.alif.click_up_clone.utils;

import lombok.Data;

import java.util.Random;

@Data
public class Utils {

    public static String codeGenerator(int bound) {
        return String.valueOf(new Random().nextInt(countHomeOfNumber(bound)));
    }

    public static int countHomeOfNumber(int bound) {
        String result = "";
        for (int i = 1; i <= bound; i++) {
            result = result.concat("9");
        }
        return Integer.parseInt(result);
    }

}
