package gol;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Point> aliveCells = new ArrayList<>();

    public void markAlive(int x, int y) {
        if (!isAlive(x, y)) {
            aliveCells.add(new Point(x, y));
        }
    }


    public boolean isAlive(int x, int y) {
        for (Point p : aliveCells) {
            if (p.x() == x && p.y() == y) {
                return true;
            }
        }
        return false;
    }

    public void toggle(int x, int y) {
        if (isAlive(x, y)) {
            aliveCells.removeIf(p -> p.x() == x && p.y() == y);
        }
        else {
            markAlive(x, y);
        }
    }

    public Integer getNeighbourCount(int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (isAlive(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void nextFrame() {
        List<Point> nextGeneration = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int neighbors = getNeighbourCount(i, j);
                boolean living = isAlive(i, j);
                if (nextState(living, neighbors)) {
                    nextGeneration.add(new Point(i, j));
                }
            }
        }
        aliveCells = nextGeneration;
    }

    public void clear() {
        aliveCells.clear();
    }

    public boolean nextState(boolean isLiving, int neighborCount) {
        if (isLiving) {
            return neighborCount == 2 || neighborCount == 3;
        }
        else {
            return neighborCount == 3;
        }
    }
}