package org.catan.Model;

public class CreateGameCode {
    // Generate random game code with given length
    public static String randomCodeGen(int count) {
        String chars = "0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*chars.length());
            builder.append(chars.charAt(character));
        }
        return builder.toString();
    }
}
