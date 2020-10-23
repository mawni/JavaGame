import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;
import org.lwjgl.system.CallbackI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/*
project 1 sample solution used as base
*/

/*
* TO DO
* - i think sign is finished implementing. should test it with the file
* */

/**
 * This class is for simulation to continuously run, with its methods allowing for sim updates multiple times a second.
 */
public class ShadowLife extends AbstractGame {

    /**
     * Side length of square tiles in the gaming simulation. tile dimensions are 64x64.
     */
    public static final int TILE_SIZE = 64;

    /**
     * File directory of the font file.
     */
    public static final String FONT_FILE = "res/VeraMono.ttf";

    /**
     * Size of font rendered throughout this game.
     */
    public static final int FONT_SIZE = 20;

    private long lastTick = 0;  //time of the last tick in milliseconds
    private static long max_ticks;
    private static long tick_time;
        //tick rate AKA time in milliseconds between each tick.
        //is static rather than final. Can't have both because it's initialised with cmd args[] and hence in main()
    private static long tickCtr = 0;
    //private static final int MAX_ACTORS = 100;
    private ArrayList<Actor> actors = new ArrayList<Actor>();

    private final Image background = new Image("res/images/background.png");

    /**
     * Used for setting the tick_time attribute i.e. time between ticks.
     * @param time The value that tick_time will be set to.
     */
    public void setTick_time(long time){
        tick_time = time;
    }

    /**
     * Used to set the maximum number of ticks.
     * @param ticks The value that max_ticks will be set to.
     */
    public void setMax_ticks(long ticks){
        max_ticks = ticks;
    }

    private void loadActors(String worldFile) {
        //reads through world files
        //file path is input argument
        int currentLine = 0;
        ArrayList<Actor> thieves = new ArrayList<Actor>();
            //used so that thieves are added to the end of the actors array list.
            //means that all thief ticks will only ever happen after all gatherer ticks done (and all other actors for that matter)s

        try (BufferedReader reader = new BufferedReader(new FileReader(worldFile))) {
            int size = (int) Files.lines(Path.of(worldFile)).count();
            String line;
            while ((line = reader.readLine()) != null) {
                //while not an empty line
                // Line format is: type,x,y
                String type;
                int x,y;
                try {
                    int readSuccess = 0; //used for seeing if the item type is recognised
                    String[] parts = line.split(",");
                    type = parts[0];
                    x = Integer.parseInt(parts[1]);
                    y = Integer.parseInt(parts[2]);
                    switch (type) {
                        case Tree.TYPE:
                            //if type = "Tree"
                            actors.add(new Tree(x, y));
                            readSuccess = 1;
                            break;
                        case Gatherer.TYPE:
                            //if type = "Gatherer"
                            actors.add(new Gatherer(x, y));
                            readSuccess = 1;
                            break;
                        case Thief.TYPE:
                            //if type = "Thief"
                            thieves.add(new Thief(x, y));
                            readSuccess = 1;
                            break;
                        case Visual.TYPE_F: //"Fence"
                            actors.add(new Visual(Visual.TYPE_F, type.toLowerCase(), x, y));
                            readSuccess = 1;
                            //"Fence" becomes "fence"
                            break;
                        case Visual.TYPE_G: //"GoldTree"
                            actors.add(new Visual(Visual.TYPE_G, "gold-tree", x, y));
                            //one-off hardcode of file path. i think it's appropriate
                            readSuccess = 1;
                            break;
                        case Visual.TYPE_P: //"Pool"
                            actors.add(new Visual(Visual.TYPE_P, type.toLowerCase(), x, y));
                            //"Pool" becomes "pool"
                            readSuccess = 1;
                            break;
                        case Visual.TYPE_D: //"Pad"
                            actors.add(new Visual(Visual.TYPE_D, type.toLowerCase(), x, y));
                            //"Pad" becomes "pad"
                            readSuccess = 1;
                            break;
                        case HoardStock.TYPE_H: //"Hoard"
                            actors.add(new HoardStock(HoardStock.TYPE_H, type.toLowerCase(), x, y));
                            //"Hoard" becomes "hoard"
                            readSuccess = 1;
                            break;
                        case HoardStock.TYPE_S: //"Stockpile"
                            actors.add(new HoardStock(HoardStock.TYPE_S, "cherries", x, y));
                            //System.out.println("stockpile generated @ (x,y) = " + "(" + x + "," + y + ")");
                            //one-off hardcode of file path
                            readSuccess = 1;
                            break;
                    }
                    if (type.contains(Sign.TYPE)){
                        //if args[0] contains "Sign"
                        actors.add(new Sign(type, x, y));
                        readSuccess = 1;
                    }
                    if (readSuccess==0){
                        System.out.println("error: in file \"" + worldFile + "\" at line " + currentLine);
                        System.exit(-1);
                    }
                    currentLine++;
                } catch (Exception e){
                    System.out.println("error: in file \"" + worldFile + "\" at line " + currentLine);
                    System.exit(-1);
                }

            }
            actors.addAll(thieves); //append all thieves to the end of actors array list
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("error: file \"" + worldFile + "\" not found");
            System.exit(-1);
        }
        Actor.setArrActors(actors);
            //the Actor class itself has a static attribute in form of array. Is self-referential basically
    }

    /**
     * Constructor for the class. used to load actors upon instancing ShadowLife.
     * @param worldFile The file path of the world file to load actors from.
     */
    public ShadowLife(String worldFile) {
        //loadActors() is called in the constructor of ShadowLife
        loadActors(worldFile);
    }

    @Override
    protected void update(Input input) {
        if (tickCtr>max_ticks){
            //if more than max ticks pass, exit
            System.out.println("Timed out");
            System.exit(-1);
        }

        // If enough time has passed, run the next tick
        if (System.currentTimeMillis() - lastTick > tick_time) {
            lastTick = System.currentTimeMillis();
            tickCtr++;
            for (int i = 0; i < actors.size(); i++) {
                //for loop goes by index because some actors will make new actors within their ticks,
                //so those new actors will not have their own full ticks until the next tick
                if (actors.get(i) != null) {
                    actors.get(i).tick();
                        //all updates relevant to each actor are executed within tick()
                }
            }
        }

        Actor.moveThievesToEnd();
        //some actors will change the Actor class ArrayList. This next line updates ShadowLife ArrayList
        actors = Actor.getArrActors();

        // Draw all elements
        background.drawFromTopLeft(0, 0);
        for (Actor actor : actors) {
            if (actor != null) {
                actor.render();
            }
        }

        //check if all gatherers and thieves now inactive
        if (Actor.anyActivePlayers()==false){
            //at this point, Actor.arrActors and Shadow.actors are both exactly the same
            System.out.println(tickCtr + " ticks");
            for (Actor actor : actors) {
                if (actor != null && (actor instanceof HoardStock)) {
                    System.out.println(actor.getAttribute());
                }
            }
        }
    }

    /**
     * Used to terminate simulation, primarily when command line arguments are invalid.
     */
    public static void abandonShip(){
        System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
        System.exit(-1);
    }

    //main method is entry pt. ShadowLife object made then run. Is by nature continually updated with update()
    /**
     * Entry point to program.
     * @param args Array of command line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 3){
            //if more or fewer than 3 arguments provided
            abandonShip();
        }
        ShadowLife game = new ShadowLife(args[2]);
        //haven't been asked to error check this so i didn't. obviously could be easily implemented with try..catch
        try{
            game.setTick_time(Long.parseLong(args[0]));
            game.setMax_ticks(Long.parseLong(args[1]));
        }catch(NumberFormatException e){
            //not valid
            abandonShip();
        }
        if (tick_time<0 || max_ticks<0){
            abandonShip();
        }

        game.run();
            //update() happens within
    }
}
