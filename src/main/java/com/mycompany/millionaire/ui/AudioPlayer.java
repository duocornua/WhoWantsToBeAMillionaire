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

/**
 * Utility class for playing menu music, game loops, and short sound effects.
 */
public class AudioPlayer {

    private static Clip loopClip;
    private static Clip effectClip;
    private static boolean muted = false;

    /**
     * Prevents creating audio utility instances.
     */
    private AudioPlayer() {
    }

    /**
     * Plays an audio file continuously until another loop starts or audio
     * stops.
     *
     * @param fileName audio file name inside the {@code audio} resource folder
     */
    public static void playLoop(String fileName) {
        if (muted) {
            return;
        }
        stopLoop();
        stopEffect();
        loopClip = loadClip(fileName);
        if (loopClip != null) {
            setVolume(loopClip, 0.5f);
            loopClip.loop(Clip.LOOP_CONTINUOUSLY);
            loopClip.start();
        }
    }

    /**
     * Adjusts clip volume using the master gain control when available.
     *
     * @param clip clip whose volume should be changed
     * @param volume normalized volume from {@code 0.0f} to {@code 1.0f}
     */
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

    /**
     * Plays a one-shot sound effect.
     *
     * @param fileName audio file name inside the {@code audio} resource folder
     */
    public static void playOnce(String fileName) {
        if (muted) {
            return;
        }
        stopEffect();
        effectClip = loadClip(fileName);
        if (effectClip != null) {
            effectClip.start();
        }
    }

    /**
     * Plays a one-shot sound and then runs a callback on the Swing thread.
     *
     * @param fileName audio file name inside the {@code audio} resource folder
     * @param afterAudio code to run after playback finishes
     */
    public static void playOnceThen(String fileName, Runnable afterAudio) {
        if (muted) {
            SwingUtilities.invokeLater(afterAudio);
            return;
        }
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

    /**
     * Stops and releases the active loop clip.
     */
    public static void stopLoop() {
        if (loopClip != null) {
            loopClip.stop();
            loopClip.close();
            loopClip = null;
        }
    }

    /**
     * Stops and releases the active effect clip.
     */
    public static void stopEffect() {
        if (effectClip != null) {
            effectClip.stop();
            effectClip.close();
            effectClip = null;
        }
    }

    /**
     * Stops every currently playing clip.
     */
    public static void stopAll() {
        stopLoop();
        stopEffect();
    }

    /**
     * Checks whether all audio is muted.
     *
     * @return {@code true} when muted
     */
    public static boolean isMuted() {
        return muted;
    }

    /**
     * Sets the global mute state and stops audio immediately when muting.
     *
     * @param muted new mute state
     */
    public static void setMuted(boolean muted) {
        AudioPlayer.muted = muted;
        if (muted) {
            stopAll();
        }
    }

    /**
     * Flips the global mute state.
     *
     * @return new mute state
     */
    public static boolean toggleMuted() {
        setMuted(!muted);
        return muted;
    }

    /**
     * Loads an audio clip from resources or project files.
     *
     * @param fileName audio file name to load
     * @return opened clip, or {@code null} if the file cannot be loaded
     */
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

    /**
     * Opens an audio stream from the classpath, source tree, or working folder.
     *
     * @param fileName audio file name to open
     * @return audio stream, or {@code null} when no matching file exists
     * @throws Exception when the file exists but cannot be decoded
     */
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
