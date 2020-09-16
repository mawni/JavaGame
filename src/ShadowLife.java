import bagel.*;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;

import java.util.Random;

/**
 * An example Bagel game.
 *
 * @author Eleanor McMurtry
 */
public class ShadowLife extends AbstractGame {
    private Image gatherer;
    private Image background;
    private Image tree;
    private double x = 100;
    private double y = 100;

    private static Integer treePos[][] = new Integer[4][2];
    private static Integer gathPos[][] = new Integer [2][2];
        //arrays which will have the (x,y) coords from each actor from the worlds.csv

    public ShadowLife() {
        super(1024, 768, "Game Session");
        background = new Image("res/images/background.png");
        gatherer = new Image("res/images/gatherer.png");
        tree = new Image("res/images/tree.png");
    }

    /**
     * The entry point for the program. the main method
     */
    public static void main(String[] args) {
        String[] fileLine = new String[3];
        int treeCtr = 0, gathCtr = 0;

        //get actor information from worlds test.csv file
        try (Scanner file = new Scanner(new FileReader("res/worlds/test.csv"))){
            while (file.hasNextLine()){
                fileLine = file.nextLine().split(","); //split based on commas ","
                //System.out.println(Arrays.toString(fileLine));

                if (fileLine[0].equals("Tree")){
                    treePos[treeCtr][0] = Integer.parseInt(fileLine[1]); //x coordinate
                    treePos[treeCtr][1] = Integer.parseInt(fileLine[2]); //y coordinate
                    treeCtr++;
                } else if (fileLine[0].equals("Gatherer")){
                    gathPos[gathCtr][0] = Integer.parseInt(fileLine[1]); //x coordinate
                    gathPos[gathCtr][1] = Integer.parseInt(fileLine[2]); //y coordinate
                    gathCtr++;
                }
            }
            //System.out.println("testing scan. 1st tree (x,y) = (" + treePos[0][0] + "," + treePos[0][1] + ")");
            //System.out.println(Arrays.deepToString(treePos));
            //System.out.println(Arrays.deepToString(gathPos));
        } catch (Exception e){
            e.printStackTrace();
        }

        ShadowLife game = new ShadowLife();
        game.run();
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
        gatherer.draw(x, y);
            //gatherer isn't supposed to move like this, so at this stage it's placeholder.
            //currently program is still the old bagel test skeleton w/ the arroy input moving gatherer
        for (int i=0; i< treePos.length; i++){
            tree.draw(treePos[i][0],treePos[i][1]);
        }
        for (int i=0; i< gathPos.length; i++){
            gatherer.draw(gathPos[i][0],gathPos[i][1]);
        }
    }
}
