package org.catan.Model;

import javafx.scene.media.AudioClip;
import org.catan.App;

public class Sound {

    public static void playPop() {
        AudioClip sound = new AudioClip(App.class.getResource("assets/sounds/soundeffects/pop.wav").toExternalForm());
        sound.setVolume(0.6);
        sound.play();
    }

    public static void playDiceThrow() {
        AudioClip sound = new AudioClip(App.class.getResource("assets/sounds/soundeffects/diceThrow.mp3").toExternalForm());
        sound.setVolume(0.6);
        sound.play();
    }

    public static void playClick() {
        AudioClip sound = new AudioClip(App.class.getResource("assets/sounds/soundeffects/click.mp3").toExternalForm());
        sound.setVolume(0.4);
        sound.play();
    }

    public static void playTakeCard() {
        AudioClip sound = new AudioClip(App.class.getResource("assets/sounds/soundeffects/takeCard.mp3").toExternalForm());
        sound.setVolume(0.2);
        sound.play();
    }

    public static void playSwitch() {
        AudioClip sound = new AudioClip(App.class.getResource("assets/sounds/soundeffects/switch.mp3").toExternalForm());
        sound.setVolume(0.6);
        sound.play();
    }

    public static void playSwitch2() {
        AudioClip sound = new AudioClip(App.class.getResource("assets/sounds/soundeffects/switch2.mp3").toExternalForm());
        sound.setVolume(0.6);
        sound.play();
    }

    public static void playSword() {
        AudioClip sound = new AudioClip(App.class.getResource("assets/sounds/soundeffects/sword.mp3").toExternalForm());
        sound.setVolume(0.3);
        sound.play();
    }

    // todo - play when it's your turn
    public static void playStartTurnJingle() {
        AudioClip sound = new AudioClip(App.class.getResource("assets/sounds/soundeffects/startTurnJingle.mp3").toExternalForm());
        sound.setVolume(0.6);
        sound.play();
    }

    public static void playEndTurnJingle() {
        AudioClip sound = new AudioClip(App.class.getResource("assets/sounds/soundeffects/endTurnJingle.mp3").toExternalForm());
        sound.setVolume(0.2);
        sound.play();
    }
}
