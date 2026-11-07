package com.TeamYellow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class CharRandomizer {



    public static HashMap<Character, Character> scrambler(){

        ArrayList<Character> input = new ArrayList<>(52);



        char[] output = new char[52];



        // Add lowercase characters (a-z)
        for (char c = 'a'; c <= 'z'; c++) {
            input.add(c);
        }
        // Add uppercase characters (A-Z)
        for (char c = 'A'; c <= 'Z'; c++) {
            input.add(c);
        }

        ArrayList<Character> keys = new ArrayList<>(input);

        HashMap<Character, Character> mappings = new HashMap<>();


        for (int i = 0; i < output.length; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(0, keys.size());

            output[i] = keys.get(randomNum);
            keys.remove(randomNum);


        }

        for (int i = 0; i < input.size(); i++) {
            mappings.put(input.get(i), output[i]);
        }


        return mappings;
    }



}
