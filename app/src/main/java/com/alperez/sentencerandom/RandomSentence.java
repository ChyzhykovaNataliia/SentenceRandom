package com.alperez.sentencerandom;

import java.util.Random;

/**
 * Created by stanislav.perchenko on 10.01.2023 at 21:12.
 */
public class RandomSentence {
    private final int min;
    private final int max;

    public RandomSentence(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public String createText() {
        char letter;
        Random r = new Random();
        int nLetters = r.nextInt(max - min) + min;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nLetters; i++){
            if((sb.length() > 0) && needSpace(r, 0.05f)) {
                letter = ' ';
            } else {
                letter = getRandomLetter(r);
            }
            sb.append(letter);
        }
        return sb.toString();
    }

    /**
     * Returns true with the probability defined by parameter
     * @param probability probability of TRUE result - [0, 1]
     * @return
     */
    private boolean needSpace(Random rnd, float probability) {
        final float v = rnd.nextFloat();
        return (v < probability);
    }

    private char getRandomLetter(Random rnd) {
        final int index = rnd.nextInt(52);
        if(index < 26) {
            return (char)('A' + index);
        } else {
            return (char)('a' + (index - 26));
        }
    }
}
