package cleancode.minesweeper.tobe.gamelevel;

public class Biginner implements GameLevel{

    @Override
    public int getRowSize() {
        return 8;
    }

    @Override
    public int gettColSize() {
        return 10;
    }

    @Override
    public int getLandMineCount() {
        return 10;
    }
}
