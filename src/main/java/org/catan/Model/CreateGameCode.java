package org.catan.Model;

public class CreateGameCode {
    private static long seed;

    CreateGameCode(){

    }
    /*
    This method generates a random code based on numbers.
    It returns the game code as seed, since that is also used as
    seed to generate the tiles et.
     */
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
