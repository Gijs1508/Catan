package org.catan.Model;

import javafx.fxml.Initializable;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.catan.App;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Takes care of all the sounds in the game (music, sound effects).
 *
 * @author Jeroen
 */

public class Sound {

    private static AudioClip pop = new AudioClip(App.class.getResource("assets/sounds/soundeffects/pop.wav").toExternalForm());
    private static AudioClip diceShuffle = new AudioClip(App.class.getResource("assets/sounds/soundeffects/diceShuffle.mp3").toExternalForm());
    private static AudioClip diceThrow = new AudioClip(App.class.getResource("assets/sounds/soundeffects/diceThrow.mp3").toExternalForm());
    private static AudioClip click = new AudioClip(App.class.getResource("assets/sounds/soundeffects/click.mp3").toExternalForm());
    private static AudioClip takeCard = new AudioClip(App.class.getResource("assets/sounds/soundeffects/takeCard.mp3").toExternalForm());
    private static AudioClip buildSettlement = new AudioClip(App.class.getResource("assets/sounds/soundeffects/buildSettlement.mp3").toExternalForm());
    private static AudioClip upgradeSettlement = new AudioClip(App.class.getResource("assets/sounds/soundeffects/upgradeSettlement.mp3").toExternalForm());
    private static AudioClip buildRoad = new AudioClip(App.class.getResource("assets/sounds/soundeffects/buildRoad.mp3").toExternalForm());
    private static AudioClip switch1 = new AudioClip(App.class.getResource("assets/sounds/soundeffects/switch.mp3").toExternalForm());
    private static AudioClip switch2 = new AudioClip(App.class.getResource("assets/sounds/soundeffects/switch2.mp3").toExternalForm());
    private static AudioClip sword = new AudioClip(App.class.getResource("assets/sounds/soundeffects/sword.mp3").toExternalForm());
    private static AudioClip startTurn = new AudioClip(App.class.getResource("assets/sounds/soundeffects/startTurnJingle.mp3").toExternalForm());
    private static AudioClip endTurn = new AudioClip(App.class.getResource("assets/sounds/soundeffects/endTurnJingle.mp3").toExternalForm());
    private static AudioClip victory = new AudioClip(App.class.getResource("assets/sounds/soundeffects/victoryJingle.mp3").toExternalForm());
    private static AudioClip defeat = new AudioClip(App.class.getResource("assets/sounds/soundeffects/defeatJingle.mp3").toExternalForm());
    private static ArrayList<AudioClip> soundEffects = new ArrayList<>();
    private static HashMap<AudioClip, Double> defaultVolume;

    private static MediaPlayer bgm = new MediaPlayer(new Media(App.class.getResource("assets/sounds/music/bgm.mp3").toExternalForm()));
    private static MediaPlayer intromusic = new MediaPlayer(new Media(App.class.getResource("assets/sounds/music/intromusic.mp3").toExternalForm()));

    private static boolean introMusicIsPlaying = true;

    /** Initializes the sounds (assigns a default volume for all sounds). */
    public static void initializeSounds() {
        Collections.addAll(soundEffects, pop, diceShuffle, diceThrow, click, takeCard, switch1, switch2, sword, startTurn, endTurn);
        defaultVolume = new HashMap<>(){{
            put(pop, 0.6);
            put(diceShuffle, 0.6);
            put(diceThrow, 0.6);
            put(click, 0.2);
            put(takeCard, 0.1);
            put(buildSettlement, 0.4);
            put(upgradeSettlement, 0.4);
            put(buildRoad, 0.4);
            put(switch1, 0.6);
            put(switch2, 0.6);
            put(sword, 0.3);
            put(startTurn, 0.1);
            put(endTurn, 0.1);
            put(victory, 0.1);
            put(defeat, 0.1);
        }};
        for(AudioClip soundEffect : soundEffects) {
            soundEffect.setVolume(defaultVolume.get(soundEffect));
        }

        bgm.setVolume(0.1);
        bgm.setCycleCount(Integer.MAX_VALUE);
        intromusic.setVolume(0.1);
        intromusic.setCycleCount(Integer.MAX_VALUE);
    }

    public static void playPop() {
        if(pop.getVolume() > 0)
            pop.play();
    }

    public static void playDiceShuffle() {
        if(diceShuffle.getVolume() > 0)
            diceShuffle.play();
    }

    public static void playDiceThrow() {
        if(diceThrow.getVolume() > 0)
            diceThrow.play();
    }

    public static void playClick() {
        if(click.getVolume() > 0)
            click.play();
    }

    public static void playTakeCard() {
        if(takeCard.getVolume() > 0)
            takeCard.play();
    }

    public static void playBuildSettlement() {
        if(buildSettlement.getVolume() > 0)
            buildSettlement.play();
    }

    public static void playUpgradeSettlement() {
        if(upgradeSettlement.getVolume() > 0)
            upgradeSettlement.play();
    }

    public static void playBuildRoad() {
        if(buildRoad.getVolume() > 0)
            buildRoad.play();
    }

    public static void playSwitch() {
        if(switch1.getVolume() > 0)
            switch1.play();
    }

    public static void playSwitch2() {
        if(switch2.getVolume() > 0)
            switch2.play();
    }

    public static void playSword() {
        if(sword.getVolume() > 0)
            sword.play();
    }

    public static void playStartTurnJingle() {
        if(startTurn.getVolume() > 0)
            startTurn.play();
    }

    public static void playEndTurnJingle() {
        if(endTurn.getVolume() > 0)
            endTurn.play();
    }

    public static void playVictoryJingle() {
        if(victory.getVolume() > 0)
            victory.play();
    }

    public static void playDefeatJingle() {
        if(defeat.getVolume() > 0)
            defeat.play();
    }

    public static void muteSoundEffects() {
        for (AudioClip soundEffect : soundEffects) {
            soundEffect.setVolume(0);
        }
    }

    public static void unmuteSoundEffects() {
        for (AudioClip soundEffect : soundEffects) {
            soundEffect.setVolume(defaultVolume.get(soundEffect));
        }
    }

    public static void muteGameMusic() {
        bgm.pause();
    }

    public static void unmuteGameMusic() {
        bgm.play();
    }

    public static void playMenuMusic() {
        intromusic.play();
    }

    public static void pauseMenuMusic() {
        intromusic.pause();
    }

    public static void setIntroMusicIsPlaying(boolean state) {
        introMusicIsPlaying = state;
    }
    public static boolean introMusicIsPlaying() {
        return introMusicIsPlaying;
    }
}
