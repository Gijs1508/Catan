package org.catan.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.catan.App;

/** Handles the music in the main menu. Allows changes made in one menu to affect for all menu's.
 * @author Jeroen
 */

public class MenuMusicHandler {
    private static final Image musicOnImg = new javafx.scene.image.Image(String.valueOf(App.class.getResource("assets/img/musicOnWhite.png")));
    private static final Image musicOffImg = new Image(String.valueOf(App.class.getResource("assets/img/musicOffWhite.png")));

    public static void initializeMusic(ImageView musicBtn) {
        if(Sound.introMusicIsPlaying()){
            musicBtn.setImage(MenuMusicHandler.getMusicOnImg());
            return;
        }
        musicBtn.setImage(MenuMusicHandler.getMusicOffImg());
    }

    public static void toggleMusic(ImageView musicBtn) {
        if (Sound.introMusicIsPlaying()) {
            Sound.pauseMenuMusic();
            musicBtn.setImage(MenuMusicHandler.getMusicOffImg());
            Sound.introMusicIsPlaying(false);
        }
        else {
            Sound.playMenuMusic();
            musicBtn.setImage(MenuMusicHandler.getMusicOnImg());
            Sound.introMusicIsPlaying(true);
        }
    }

    public static Image getMusicOnImg() {
        return musicOnImg;
    }

    public static Image getMusicOffImg() {
        return musicOffImg;
    }
}
