package com.aidanfincher.engine;

public class GameEngine implements Runnable {

    public static final int TARGET_FPS = 60;
    public static final int TARGET_UPS = 30;

    private final Window window;
    private final IGameLogic logic;

    public GameEngine(String title, int width, int height, boolean useVsync, IGameLogic logic) throws Exception {
        window = new Window(title, width, height, useVsync);
        this.logic = logic;
    }

    @Override
    public void run() {
        try {
            init();
            loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void init() throws Exception {
        window.create();
        Timer.init();
        logic.init();
    }

    protected void loop() {
        float elapsedTime, accumulator = 0f, interval = 1f / TARGET_UPS;
        while (!window.isCloseRequested()) {
            elapsedTime = Timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if (!window.isVsync())
                sync();
        }
    }

    protected void input() {
        this.logic.input(this.window);
    }

    protected void update(float interval) {
        this.logic.update(interval);
    }

    protected void render() {
        this.logic.render(this.window);
        this.window.update();
    }

    protected void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = Timer.getLastLoopTime() + loopSlot;
        while (Timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {}
        }
    }
}
