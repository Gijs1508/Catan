package org.catan.Model;

/**
 * Used to create a new game code.
 * An game code gets created when a player creates a new game.
 *
 * @Author Gijs van der Weijden
 */
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
        int seedLen = 0;
        while (seedLen < 6){
            String chars = "0123456789";
            int count = 6;
            StringBuilder builder = new StringBuilder();
            while (count-- != 0) {
                int character = (int)(Math.random()*chars.length());
                builder.append(chars.charAt(character));
            }
            seed = Long.parseLong(builder.toString());
            seedLen = String.valueOf(seed).length();
        }
        return seed;
    }

    public static long getSeed() {
        return seed;
    }
}
