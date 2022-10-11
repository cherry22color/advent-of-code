package adventOfCode_2016.DAY1_16;

public class Point {

    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    Point fillArrayPoint(Point point_1, Point point_2, int[][] allCoordinats) {

        int startX;
        int startY;
        int endX;
        int endY;
        /* Установить правильное направление заполнения массива от меньшего к больше */
        if (point_1.getX() < point_2.getX()) {
            startX = point_1.getX();
            endX = point_2.getX();
        } else {
            startX = point_2.getX();
            endX = point_1.getX();
        }
        if (point_1.getY() < point_2.getY()) {
            startY = point_1.getY();
            endY = point_2.getY();
        } else {
            startY = point_2.getY();
            endY = point_1.getY();
        }

        allCoordinats[point_1.getX()][point_1.getY()]--;
        for (int X = startX; X <= endX; X++) {

            for (int Y = startY; Y <= endY; Y++) {
                allCoordinats[X][Y]++;
                    /*При совпадении вернуть точку совпадения */
                if (allCoordinats[X][Y] > 1) {
                    Point repeat = new Point(X, Y);
                    return repeat;
                }
            }
        }
        /* Вернуть null при отсуствии совпадающей точки*/
        return null;
    }
}
