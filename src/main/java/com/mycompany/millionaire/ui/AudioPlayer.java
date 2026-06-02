package com.mycompany.millionaire.ui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.swing.SwingUtilities;
import javax.sound.sampled.FloatControl;

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
            setVolume(loopClip, 0.5f);
            loopClip.loop(Clip.LOOP_CONTINUOUSLY);
            loopClip.start();
        }
    }
    
    private static void setVolume(Clip clip, float volume) {
        try {
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume = Math.max(0.0f, Math.min(1.0f, volume));
                
                float dB = (volume == 0.0f) ? gainControl.getMinimum() : (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                
                gainControl.setValue(dB);
            }
        } catch (Exception e) {
            System.err.println("Cannot adjust volume: " + e.getMessage());
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
