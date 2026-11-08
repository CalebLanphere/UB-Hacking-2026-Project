package com.calebandwill.webserver.TextObfuscation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class CharRandomizer {



    public static HashMap<Character, Character> scrambler(){



        // Mapping will be of type <Input, Output>
        ArrayList<Character> input = new ArrayList<>(52);
        char[] output = new char[52];



        // Add lowercase characters a-z
        for (char c = 'a'; c <= 'z'; c++) {
            input.add(c);
        }
        // Add uppercase characters A-Z
        for (char c = 'A'; c <= 'Z'; c++) {
            input.add(c);
        }

        // Dynamic List of Keys to fill Mapping With
        ArrayList<Character> keys = new ArrayList<>(input);


        //Mapping to be returned
        HashMap<Character, Character> mappings = new HashMap<>();



        // This picks a key from Keys at random, and adds it to the output list.
        // Keys are picked at most once.
        for (int i = 0; i < output.length; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(0, keys.size());
            output[i] = keys.get(randomNum);
            keys.remove(randomNum);
        }

        // Updates mappings to be returned.
        for (int i = 0; i < input.size(); i++) {
            mappings.put(input.get(i), output[i]);
        }


        // incase it needs to be reversed:
        /*
        for (int i = 0; i < input.size(); i++){
            mappings.put(output[i], input.get(i));
        }


         */


        return mappings;
    }



}
