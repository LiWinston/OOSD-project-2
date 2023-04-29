/**
 * The ShadowPacLogic(Level 0) class provides custom logic for the ShadowPac game.
 * This class handles the game state when the game is either failed or succeeded,
 * and provides access to the lists of Ghosts, Dots and Walls in the game.
 * Notice that setPlayer() MUST BE DONE RIGHT AFTER AN playerL0 HAS BEEN INITIALIZED
 *
 * @YongchunLi
 */
public class ShadowPacLogic_L0 {
    private final short gamePID;//HashCode of game ID
    private final ShadowPac game;
    private Player_L0 playerL0;

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

    public void setPlayer_L0(int x, int y, ShadowPacLogic_L0 logic) {
        this.playerL0 = new Player_L0(x, y, logic);
    }

    /*
     * Method to retrieve the list of Ghosts in the ShadowPac game.
     *
     * @return an array of Ghost objects representing the Ghosts in the game
     */

    public Ghost[] getGhostList() {
        return game.getGhostList_L0();
    }

    /**
     * Method to retrieve the list of Dots in the ShadowPac game.
     *
     * @return an array of Dot objects representing the Dots in the game
     */

    public Dot[] getDotList() {
        return game.getDotList_L0();
    }

    /**
     * Method to retrieve the list of Walls in the ShadowPac game.
     *
     * @return an array of Wall objects representing the Walls in the game
     */

    public Wall[] getWallList() {
        return game.getWallList_L0();
    }

    /**
     * Method to set the game state to 'Failed'.
     */

    public void gameFailed() {
        game.setGameStageLOSE(this);
    }

    /**
     * Method to set the game state to 'Success'.
     */

    public void level_completed() {
        game.setGameStageWIN(this);
    }

    /**
     * Method to call Player_L0 to checkAround without receiving a Game reference.
     */

    public void letPlayerCheckAround() {
        if (null == this.playerL0) {
            System.err.println("Need Set playerL0 for ShadowPacLogic object!");
            System.err.println();
            return;
        }
        playerL0.checkAround();
    }

    /*
     * Method to get the game DotNum.
     */
    public int getSupposedDotNum() {
        return game.getSupposedDotNum();
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
    public Player_L0 getPlayer() {
        return playerL0;
    }

    public short getPID() {
        return gamePID;
    }
}
