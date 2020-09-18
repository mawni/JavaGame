import bagel.*;
import bagel.util.Colour;
import bagel.util.Point;

import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

import java.util.Random;

public class ShadowLife extends AbstractGame {
    private Image gathererOld; //i should delete this later, it's from bagelTest
    private Image background;
    private double x = 100;
    private double y = 100;
    static final int TILESIZE = 64; //the window tiles are 64x64

    //actor arrays
    private ArrayList<Gatherer> arrGatherers = new ArrayList<Gatherer>();
    private ArrayList<Tree> arrTrees = new ArrayList<Tree>();

    /*
      TO-DO:
      - the weird milisecond, change sim only once every 500ms.
            - renders happen 60fps, but actual sim changes only every 500ms
      - add the gatherer functionality to move directions
    */

    public ShadowLife() {
        super(1024, 768, "Game Session");
            //the width and height of the background image.
            //i would add static constants and a method to calculate background width and height...
            //...but the values are used once, and the super(); line has to be first in the constructor
        background = new Image("res/images/background.png");
        gathererOld = new Image("res/images/gatherer.png");
        //tree = new Image("res/images/tree.png");
    }

    //Main method, program entry point
    public static void main(String[] args) {
        ShadowLife game = new ShadowLife();
        String[] fileLine = new String[3]; //each row of 3 arguments (in test.csv) stored in fileLine[]
        Point tempPt;

        //get actor information from worlds test.csv file
        try (Scanner file = new Scanner(new FileReader("res/worlds/test.csv"))){
            while (file.hasNextLine()){
                fileLine = file.nextLine().split(","); //split based on commas ","
                //System.out.println(Arrays.toString(fileLine));
                tempPt = new Point(Double.parseDouble(fileLine[1]), Double.parseDouble(fileLine[2]));

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
    //arrTrees and arrGatherers are both dynamic array lists, and class attributes for ShadowLife
    public void makeTree(Point point){
        this.arrTrees.add(new Tree(point));
    }
    public void makeGatherer(Point point){
        this.arrGatherers.add(new Gatherer(point));
    }

    //draw out a visual tile grid. each tile is 64x64
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

    /**
     * Performs a state update. This simple example shows an image that can be controlled with the arrow keys, and
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {
        //this is executed 60 times per second
        //state of simulation changed here
        double speed = 0.5;
        //System.out.println("height = " + background.getHeight() + ", width = " + background.getWidth());
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
        }

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }


        background.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);
        gathererOld.draw(x, y);
            //gatherer isn't supposed to move like this, so at this stage it's placeholder.
            //currently program is still the old bagel test skeleton w/ the array input moving gatherer

        //draw all trees and gatherers with their own class methods
        for (Tree tree : arrTrees){
            tree.drawTree();
            //System.out.println(tree.getPoint());
        }
        for (Gatherer gatherer : arrGatherers){
            gatherer.drawGath();
        }

        drawTileGrid();
    }
}
