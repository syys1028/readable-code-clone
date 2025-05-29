package cleancode.minesweeper.tobe.gamelevel;

public class VeryBiginner implements GameLevel {

    @Override
    public int getRowSize() {
        return 4;
    }

    @Override
    public int gettColSize() {
        return 5;
    }

    @Override
    public int getLandMineCount() {
        return 2;
    }
}
