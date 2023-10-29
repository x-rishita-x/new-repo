package player;

public class Player {
    public boolean whiteSide;
    protected PlayerType playerType;
    protected boolean isCheckmated;

    public Player(boolean whiteSide, PlayerType playerType) {
        this.whiteSide = whiteSide;
        this.playerType = playerType;
    }

    public boolean isWhiteSide() {
        return this.whiteSide;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public boolean isCheckmated() {
        return isCheckmated;
    }

    public void setCheckmated(boolean checkmated) {
        isCheckmated = checkmated;
    }
}
