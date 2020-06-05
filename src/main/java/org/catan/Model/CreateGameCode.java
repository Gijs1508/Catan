package org.catan.Model;

public class CreateGameCode {
    private static long seed;

    CreateGameCode(){

    }
    // Generate random game code with given length
    public static long randomCodeGen() {
        String chars = "0123456789";
        int count = 6;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*chars.length());
            builder.append(chars.charAt(character));
        }
        seed = Long.parseLong(builder.toString());
        return seed;
    }

    public static long getSeed() {
        return seed;
    }
}