/*
Responsibilities:

- Manage game sounds, including playing sound files.
- Implement the Singleton pattern to ensure a single instance of the SoundManager class.

Data:

- None (the SoundManager class is a stateless utility class and does not maintain specific data).
 */

package main.java.utility;

public class SoundManager {
    private static SoundManager instance;

    // Private constructor to prevent instantiation
    private SoundManager() {
        // TODO: Initialize sound system or resources, if needed
    }

    /**
     * Gets the instance of the SoundManager class.
     * 
     * @return The SoundManager instance.
     */
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Plays a sound file.
     * 
     * @param soundFile The file path or description of the sound to be played.
     */
    public void playSound(String soundFile) {
        // Placeholder for play sound logic
        System.out.println(soundFile);
    }
}
