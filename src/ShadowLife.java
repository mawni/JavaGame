import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
project 1 sample solution used as base
*/

/*
* TO DO
* - i think sign is finished implementing. should test it with the file
* */

public class ShadowLife extends AbstractGame {
    public static final int TILE_SIZE = 64;

    private long lastTick = 0;  //time of the last tick in milliseconds
    private static long max_ticks;
    private static long tick_time;
        //tick rate AKA time in milliseconds between each tick.
        //is static rather than final. Can't have both because it's initialised with cmd args[] and hence in main()

    private static final int MAX_ACTORS = 100;
    private Actor[] actors;

    private final Image background = new Image("res/images/background.png");

    public void setTick_time(long time){
        tick_time = time;
    }
    public void setMax_ticks(long ticks){
        max_ticks = ticks;
    }

    private void loadActors(String worldFile) {
        //read through world files
        //file path is input argument

        int count = 0; //counter for adding actors to Actor[] array


        try (BufferedReader reader = new BufferedReader(new FileReader(worldFile))) {
            int size = (int) Files.lines(Path.of(worldFile)).count();
            actors = new Actor[size];
                //generate Actor array of size size based on num of lines in file

            String line;
            while ((line = reader.readLine()) != null) {
                //while not an empty line
                // Line format is: type,x,y
                String[] parts = line.split(",");
                String type = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                switch (type) {
                    case Tree.TYPE:
                        //if type = "Tree"
                        actors[count++] = new Tree(x, y);
                        break;
                    case Gatherer.TYPE:
                        //if type = "Gatherer"
                        actors[count++] = new Gatherer(x, y);
                        break;
                    case Thief.TYPE:
                        //if type = "Thief"
                        actors[count++] = new Thief(x, y);
                        break;
                    case Visual.TYPE_F: //"Fence"
                        actors[count++] = new Visual(Visual.TYPE_F, type.toLowerCase(), x, y);
                        //"Fence" becomes "fence"
                        break;
                    case Visual.TYPE_G: //"GoldTree"
                        actors[count++] = new Visual(Visual.TYPE_G, "gold-tree", x, y);
                        //one-off hardcode of file path. i think it's appropriate
                        break;
                    case Visual.TYPE_P: //"Pool"
                        actors[count++] = new Visual(Visual.TYPE_P, type.toLowerCase(), x, y);
                        //"Pool" becomes "pool"
                        break;
                    case Visual.TYPE_D: //"Pad"
                        actors[count++] = new Visual(Visual.TYPE_D, type.toLowerCase(), x, y);
                        //"Pad" becomes "pad"
                        break;
                    case HoardStock.TYPE_H: //"Hoard"
                        actors[count++] = new Visual(HoardStock.TYPE_H, type.toLowerCase(), x, y);
                        //"Hoard" becomes "hoard"
                        break;
                    case HoardStock.TYPE_S: //"Stockpile"
                        actors[count++] = new Visual(HoardStock.TYPE_S, "cherries", x, y);
                        //one-off hardcode of file path
                        break;
                }
                if (type.contains(Sign.TYPE)){
                    //if args[0] contains "Sign"
                    type = type.replace(Sign.TYPE, "").toLowerCase();
                    //this should make "SignLeft" into "left" for example
                    actors[count++] = new Sign(type, x, y);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public ShadowLife(String worldFile) {
        //loadActors() is called in the constructor of ShadowLife
        loadActors(worldFile);
    }

    @Override
    protected void update(Input input) {
        // If enough time has passed, run the next tick
        if (System.currentTimeMillis() - lastTick > tick_time) {
            lastTick = System.currentTimeMillis();
            for (Actor actor : actors) {
                if (actor != null) {
                    actor.tick();
                        //all updates relevant to each actor are executed within tick()
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

    //main method is entry pt. ShadowLife object made then run. Is by nature continually updated with update()
    public static void main(String[] args) {
        ShadowLife game = new ShadowLife(args[2]);
        game.setTick_time(Long.parseLong(args[0]));
        game.setMax_ticks(Long.parseLong(args[1]));
        game.run();
            //update() happens within
    }
}
