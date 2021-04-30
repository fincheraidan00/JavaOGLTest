package com.aidanfincher.game;

import com.aidanfincher.engine.GameEngine;
import com.aidanfincher.engine.IGameLogic;
import com.aidanfincher.engine.gfx.ShaderProgram;

public class Main {

    public static void main(String[] args) {
        try {
            IGameLogic logic = new DummyGame();
            GameEngine engine = new GameEngine("LWJGLTest", 1280, 720, true, logic);
            engine.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}