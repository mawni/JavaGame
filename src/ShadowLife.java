import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
cmd commands for git backup

*/
public class ShadowLife extends AbstractGame {
    public static final int TILE_SIZE = 64;

    private long lastTick = 0;
    private static final long TICK_TIME = 500;

    private static final int MAX_ACTORS = 100;
    private Actor[] actors;

    private final Image background = new Image("res/images/background.png");

    private void loadActors() {
        int count = 0;

        // Hard-code world name for Project 1
        String worldFile = "res/worlds/test.csv";


        try (BufferedReader reader = new BufferedReader(new FileReader("res/worlds/test.csv"))) {
            int size = (int) Files.lines(Path.of(worldFile)).count();
            actors = new Actor[size];

            String line;
            while ((line = reader.readLine()) != null) {
                // Line format is: type,x,y
                String[] parts = line.split(",");
                String type = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                switch (type) {
                    case Tree.TYPE:
                        actors[count++] = new Tree(x, y);
                        break;
                    case Gatherer.TYPE:
                        actors[count++] = new Gatherer(x, y);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public ShadowLife() {
        loadActors();
    }

    @Override
    protected void update(Input input) {
        // If enough time has passed, run the next tick
        if (System.currentTimeMillis() - lastTick > TICK_TIME) {
            lastTick = System.currentTimeMillis();
            for (Actor actor : actors) {
                if (actor != null) {
                    actor.tick();
                }
            }
        }

        // Draw all elements
        background.drawFromTopLeft(0, 0);
        for (Actor actor : actors) {
            if (actor != null) {
                actor.render();
            }
        }
    }

    public static void main(String[] args) {
        new ShadowLife().run();
    }
}
