/**
 * The ShadowPacLogic(Level 0) class provides custom logic for the ShadowPac game.
 * This class handles the game state when the game is either failed or succeeded,
 * and provides access to the lists of Ghosts, Dots and Walls in the game.
 * Notice that setPlayer() MUST BE DONE RIGHT AFTER AN playerL0 HAS BEEN INITIALIZED
 * The establishment of GameLogic is basically to decouple ShadowPac and Player, especially to provide a layer of
 * delegation to provide players with surrounding information, so that players can obtain game unit pools while avoiding
 * storing game class references or need of parameter of game - cross-reference is always bad practice, and this kind of
 * thing should be reduced-but the cross-reference between logic and ShadowPac is okay, because the game logic is only an
 * ancillary to help manage the game, and is not required to act independently, while the unit is not, they are all one by
 * one Independent individuals should not be too entangled with other classes.
 *
 * @author YongchunLi
 */
public class ShadowPacLogic_L0 {
    private final short gamePID;
    private final ShadowPac game;
    protected Player player;

    /**
     * Constructor for ShadowPacLogic class that takes in a ShadowPac game instance
     * and assigns it to the private variable 'game'.
     *
     * @param game the ShadowPac game instance
     */
    public ShadowPacLogic_L0(ShadowPac game) {
        this.game = game;
        gamePID = game.getPID();
    }

    /**
     * use level 0 logic to generate the
     * @param x
     * @param y
     * @param logic
     */
    public void setPlayer(int x, int y, ShadowPacLogic_L0 logic) {
        this.setPlayer(new Player(x, y, logic));
    }

    /*
     * Method to retrieve the list of Ghosts in the ShadowPac game.
     *
     * @return an array of Ghost objects representing the Ghosts in the game
     */

    public Ghost[] getGhostList() {
        return getGame().getGhostList_L0();
    }

    /**
     * Method to retrieve the list of Dots in the ShadowPac game.
     *
     * @return an array of Dot objects representing the Dots in the game
     */

    public Dot[] getDotList() {
        return getGame().getDotList_L0();
    }

    /**
     * Method to retrieve the list of Walls in the ShadowPac game.
     *
     * @return an array of Wall objects representing the Walls in the game
     */

    public Wall[] getWallList() {
        return getGame().getWallList_L0();
    }

    /**
     * Method to set the game state to 'Failed'.
     */

    public void gameFailed() {
        getGame().setGameStageLOSE(this);
    }

    /**
     * Method to set the game state to 'Success'.
     */

    public void level_completed() {
        getGame().setGameStageWIN(this);
    }

    /**
     * Method to call Player to checkAround without receiving a Game reference.
     */

    public void letPlayerCheckAround() {
        if (null == this.getPlayer()) {
            System.err.println("Need Set playerL0 for ShadowPacLogic object!");
            System.err.println();
            return;
        }
        getPlayer().checkAround();
    }

    /*
     * Method to get the game DotNum.
     */
    public int getSupposedDotNum() {
        return getGame().getSupposedDotNum();
    }

    /*
     * Method to get the game StepSize.
     */
    public int getSTEP_SIZE() {
        return ShadowPac.getSTEP_SIZE();
    }

    /*
     * Method to get the playerL0 instance reference.
     */
    public Player getPlayer() {
        return player;
    }

    public short getPID() {
        return getGamePID();
    }

    protected short getGamePID() {
        return gamePID;
    }

    protected ShadowPac getGame() {
        return game;
    }



    protected void setPlayer(Player player) {
        this.player = player;
    }
}
