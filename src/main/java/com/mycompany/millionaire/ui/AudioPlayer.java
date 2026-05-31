package com.mycompany.millionaire.ui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.swing.SwingUtilities;

public class AudioPlayer {

    private static Clip loopClip;
    private static Clip effectClip;

    private AudioPlayer() {
    }

    public static void playLoop(String fileName) {
        stopLoop();
        stopEffect();
        loopClip = loadClip(fileName);
        if (loopClip != null) {
            loopClip.loop(Clip.LOOP_CONTINUOUSLY);
            loopClip.start();
        }
    }

    public static void playOnce(String fileName) {
        stopEffect();
        effectClip = loadClip(fileName);
        if (effectClip != null) {
            effectClip.start();
        }
    }

    public static void playOnceThen(String fileName, Runnable afterAudio) {
        stopEffect();
        effectClip = loadClip(fileName);
        if (effectClip == null) {
            SwingUtilities.invokeLater(afterAudio);
            return;
        }

        effectClip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                stopEffect();
                SwingUtilities.invokeLater(afterAudio);
            }
        });
        effectClip.start();
    }

    public static void stopLoop() {
        if (loopClip != null) {
            loopClip.stop();
            loopClip.close();
            loopClip = null;
        }
    }

    public static void stopEffect() {
        if (effectClip != null) {
            effectClip.stop();
            effectClip.close();
            effectClip = null;
        }
    }

    public static void stopAll() {
        stopLoop();
        stopEffect();
    }

    private static Clip loadClip(String fileName) {
        try {
            AudioInputStream audioStream = openAudioStream(fileName);
            if (audioStream == null) {
                return null;
            }

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            audioStream.close();
            return clip;
        } catch (Exception ex) {
            System.err.println("Unable to play audio file: " + fileName);
            return null;
        }
    }

    private static AudioInputStream openAudioStream(String fileName) throws Exception {
        String resourcePath = "audio/" + fileName;
        InputStream resource = AudioPlayer.class.getClassLoader().getResourceAsStream(resourcePath);
        if (resource != null) {
            return AudioSystem.getAudioInputStream(new BufferedInputStream(resource));
        }

        File sourceFile = new File("src/main/resources/" + resourcePath);
        if (sourceFile.exists()) {
            return AudioSystem.getAudioInputStream(sourceFile);
        }

        File workingFile = new File(resourcePath);
        if (workingFile.exists()) {
            return AudioSystem.getAudioInputStream(workingFile);
        }

        return null;
    }
}
