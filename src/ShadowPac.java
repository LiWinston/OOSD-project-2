/**
 * Skeleton Code for SWEN20003 Project 1, Semester 1, 2023
 * Please enter your name below
 * @YongchunLi
 */
import bagel.*;
import bagel.Image;
import bagel.Window;
import bagel.util.Colour;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static int supposedGhostNum = 4;
    private final static int supposedWallNum = 145;
    protected final static int supposedDotNum = 121;
    private final static int MID_WIDTH = WINDOW_WIDTH / 2;
    private final static int MID_HEIGHT = WINDOW_HEIGHT / 2;

    private final static String GAME_TITLE = "SHADOW PAC";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");

    protected final static int STEP_SIZE = 3;
    protected Player player;
    protected Ghost[] ghostList = new Ghost[supposedGhostNum];
    protected Wall[] wallList = new Wall[supposedWallNum];
    protected Dot[] dotList = new Dot[supposedDotNum];



    protected enum gameStage {
        Welcome, Gaming, Lose, Success
    }

    protected gameStage gs;


    public static int getWindowWidth(){
        return WINDOW_WIDTH;
    }
    public static int getWindowHeight(){
        return WINDOW_HEIGHT;
    }

    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
    }

    /**
     * Method used to read file and create objects (you can change
     * this method as you wish).
     */
    private void readCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("res/level0.csv"))) {
            String line;
            int ghostNum = 0, wallNum = 0, dotNum = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0];
                int x = Integer.parseInt(data[1]);
                int y = Integer.parseInt(data[2]);
                switch (type) {
                    case "Player":
                        player = new Player(x,y,this);
                        break;
                    case "Ghost":
                        ghostList[ghostNum++] = new Ghost(x, y);
                        break;
                    case "Wall":
                        wallList[wallNum++] = new Wall(x, y);
                        break;
                    case "Dot":
                        dotList[dotNum++] = new Dot(x, y);
                        break;
                    default:
                        System.out.println("invalid csv data!");
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not exist：" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error：" + e.getMessage());
        }
    }


    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.gs = gameStage.Welcome;
        game.readCSV();
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
        if(this.gs == gameStage.Welcome){
            ShowMessage SM_SHADOW_PAC = new ShowMessage("SHADOW PAC",260,250);
            SM_SHADOW_PAC.Show();
            ShowMessage SM_PRESS_SPACE_TO_START = new ShowMessage("PRESS SPACE TO START",320,440,24);
            SM_PRESS_SPACE_TO_START.Show();
            ShowMessage SM_USE_ARROW_KEYS_TO_MOVE = new ShowMessage("USE ARROW KEYS TO MOVE",320,540,24);
            SM_USE_ARROW_KEYS_TO_MOVE.Show();

            if(input.wasPressed(Keys.SPACE)) {
                gs = gameStage.Gaming;
            }
        }
        if(this.gs == gameStage.Gaming) {
            ShowMessage SM_Score = new ShowMessage("SCORE " + player.getScore(),25,25,20);
            SM_Score.Show();
            player.Draw(input);
            Drawing.drawRectangle(player.hitBox.topLeft(), Player.playerOpenMouth.getWidth(), Player.playerOpenMouth.getHeight(), Colour.RED);
            for(Ghost gst : ghostList){
                gst.DrawFixUnit();
            }
            for(Wall wl : wallList){
                wl.DrawFixUnit();
                //Debugging for wall rect
                Drawing.drawRectangle(wl.hitBox.topLeft(),wl.wall.getWidth(),wl.wall.getHeight(), Colour.GREEN);
            }
            for(Dot dt : dotList){
                dt.DrawFixUnit();
            }
            player.checkAround(this);

        }
        if(this.gs == gameStage.Success) {
            ShowMessage SM_SHADOW_PAC = new ShowMessage("WELL DONE!",MID_WIDTH - 4*ShowMessage.SPECIFIC_FONTSIZE,MID_HEIGHT);//More accurate centralization required
            SM_SHADOW_PAC.Show();
            if(input.wasPressed(Keys.SPACE)) {
                Window.close();
            }
        }
        if(this.gs == gameStage.Lose) {
            ShowMessage SM_SHADOW_PAC = new ShowMessage("GAME OVER!",MID_WIDTH - 4*ShowMessage.SPECIFIC_FONTSIZE,MID_HEIGHT);//More accurate centralization required
            SM_SHADOW_PAC.Show();
            if(input.wasPressed(Keys.SPACE)) {
                Window.close();
            }
        }

    }
}
