import bagel.*;
import bagel.util.Colour;
import bagel.util.Point;

import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
//import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;
import java.util.Random;

public class ShadowLife extends AbstractGame {
    //private Image gathererOld; //remnants from wk5 - workshop 4
    //private double x = 100;
    //private double y = 100;
    private Image background;
    static final int TILESIZE = 64; //the window tiles are 64x64
    private static long currTime;
    private int ticks = 5;

    //actor arrays. dynamic array lists
    private ArrayList<Gatherer> arrGatherers = new ArrayList<Gatherer>();
    private ArrayList<Tree> arrTrees = new ArrayList<Tree>();

    /*
      TO-DO:
        ~~fin~~
    */

    public ShadowLife() {
        super(1024, 768, "Game Session");
            //the width and height of the background image.
            //i would add static constants and a method to calculate background width and height...
            //...but the values are used once, and the super() line has to be first in the constructor
        background = new Image("res/images/background.png");
        //gathererOld = new Image("res/images/gatherer.png");
        currTime = System.currentTimeMillis();
        //System.out.println(currTime);
    }

    //Main method, program entry point
    public static void main(String[] args) {
        ShadowLife game = new ShadowLife();
        String[] fileLine = new String[3]; //each row of 3 arguments (in test.csv) stored in fileLine[]
        CustomPoint tempPt;

        //get actor information from worlds test.csv file. i've tested other files, looks good
        try (Scanner file = new Scanner(new FileReader("res/worlds/test.csv"))){
            while (file.hasNextLine()){
                fileLine = file.nextLine().split(","); //split based on commas ","
                //System.out.println(Arrays.toString(fileLine));
                tempPt = new CustomPoint(Double.parseDouble(fileLine[1]), Double.parseDouble(fileLine[2]));

                if (fileLine[0].equals("Tree")){
                    game.makeTree(tempPt);
                } else if (fileLine[0].equals("Gatherer")){
                    game.makeGatherer(tempPt);
                }
            }
            //System.out.println("testing scan. " + fileLine[0] + " (x,y) = (" + fileLine[1] + "," + fileLine[2] + ")");
        } catch (IOException e){
            //Not sure what IOException does differently to just Exception, but seems both work
            e.printStackTrace();
        }
        game.run();
    }

    //methods to add trees and gatherers to the relevant arrays
    public void makeTree(CustomPoint point){
        this.arrTrees.add(new Tree(point));
    }
    public void makeGatherer(CustomPoint point){
        this.arrGatherers.add(new Gatherer(point));
    }

    //draw out a visual tile grid. each tile is 64x64
    //this is added in for my own sanity, i know it's not necessary
    public void drawTileGrid(){
        //16x12 is the amount of 64 by 64 tiles
        int i=0,j=0;
        while (i<=background.getWidth()){
            //print vertical gridlines
            Drawing.drawLine( new Point(i,j), new Point(i, background.getHeight()), 1.0, Colour.BLUE);
            i+=TILESIZE;
        }
        i=0;
        while (j<=background.getHeight()){
            //print horizontal gridlines
            Drawing.drawLine(new Point(i, j), new Point(background.getWidth(), j), 1.0, Colour.BLUE);
            j+=TILESIZE;
        }
    }

    //given a direction and point, move the point by one tile accordingly. (left,right,up,down) = (1,2,3,4)
    //used to move actors
    public CustomPoint moveGath(CustomPoint point, int direction){
        //CustomPoint tempPt = point;
        if (direction == 1){ //left
            point.setX(point.getX() - TILESIZE);
        } else if (direction == 2){ //right
            point.setX(point.getX() + TILESIZE);
        } else if (direction == 3){ //up
            point.setY(point.getY() - TILESIZE);
        } else { //direction==4 //down
            point.setY(point.getY() + TILESIZE);
        }
        return point;
    }

    /** Performs a state update. */
    @Override
    public void update(Input input) {
        /*
        //This commented part is vestigial skeleton code from wk5 workshop 4. Kept in case for project 2 ;)
        //oldGatherer can be controlled with arrow keys
        double speed = 0.5;
        if (input.isDown(Keys.LEFT)) {
            x -= speed;
        }
        if (input.isDown(Keys.RIGHT)) {
            x += speed;
        }
        if (input.isDown(Keys.UP)) {
            y -= speed;
        }
        if (input.isDown(Keys.DOWN)) {
            y += speed;
        }*/
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        } //game exits when ESCAPE pressed

        background.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);
        //gathererOld.draw(x, y);
        for (Tree tree : arrTrees){  //draw all trees
            tree.drawTree();
            //System.out.println(tree.getPoint());
        }

        //the contents of this if() happen every 500ms tick. simulation happens within
        if ((System.currentTimeMillis()-currTime) >= 500.0){
            if (ticks>=5){
                //set new direction for each gatherer every 5 ticks
                for (Gatherer gatherer : arrGatherers){
                    gatherer.setDirection((int) Math.floor(Math.random()*4)+1); //random num 1,2,3 or 4
                    //System.out.println("gatherer #" + arrGatherers.indexOf(gatherer) + "moves direction " + gatherer.getDirection());
                }
                ticks=0;
                    //reset the counting.
            }

            //move gatherers according to their current direction
            for (Gatherer gatherer : arrGatherers){
                gatherer.setPoint(moveGath(gatherer.getPoint(), gatherer.getDirection()));
                gatherer.drawGath();
                //get current gatherer point, get direction -> use moveGath() to pick direction
                // -> set new gatherer point -> draw gatherer according to that new point
            }
            //System.out.print(System.currentTimeMillis());
            currTime = System.currentTimeMillis();
                //currTime is always tracking the time of the last tick
            ticks++;
        } else {
            //render all gatherers are normal for the frame, no movement
            for (Gatherer gatherer : arrGatherers){
                gatherer.drawGath();
            }
        }

        drawTileGrid();
    }
}
