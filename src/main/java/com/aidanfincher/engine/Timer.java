package com.aidanfincher.engine;

public class Timer {

    private static double lastLoopTime;

    public static void init() {
        lastLoopTime = Timer.getTime();
    }

    public static double getTime() {
        return System.nanoTime() / 1000000000.0;
    }

    public static float getElapsedTime() {
        double time = Timer.getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    public static double getLastLoopTime() {
        return Timer.lastLoopTime;
    }
}
