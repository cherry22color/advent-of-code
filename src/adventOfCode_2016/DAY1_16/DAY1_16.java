package adventOfCode_2016.day1_16;

import adventOfCode_2016.ReadFile;

import java.io.FileNotFoundException;

public class DAY1_16 {
    public static void main(String[] args) throws FileNotFoundException {
        ReadFile readFile = new ReadFile("day1_16.txt");
        String line = readFile.readFile();
        /* Разделить строку на массив строк, разделителем ", " */
        String[] sequenceRL = line.split(", ");
        int digit = 0;
        int[] coordinats = getPointXY(sequenceRL);

        digit = Math.abs(coordinats[0]) + Math.abs(coordinats[1]);
        System.out.println("Count steps: " + digit);

        /* Часть 2 */
        int countCoordinat = 10000;
        int[][] allCoordinats = new int[countCoordinat][countCoordinat];
        allCoordinats[0][0] = 1;
        Point point_1 = new Point(5000, 5000);
        Point point_2 = new Point(5000, 5000);

        //int digit;/* Направление движения 0 - север, 1 - восток, 2 - юг, 3 - запад */
        int direction = 0;

        for (int i = 0; i < sequenceRL.length; i++) {

            /* Повернуть на право - на восток */
            if (sequenceRL[i].contains("R")) {
                digit = getDigitInt(sequenceRL[i], "R");
                // сменить направление на + 1
                direction++;
            } else {
                /* Повернуть на лево - на запад */
                digit = getDigitInt(sequenceRL[i], "L");
                // сменить направление на -1
                direction--;
            }
            direction = normalizeDirection(direction);

            // для части 2
            // заполнить вторую точку
            transformPositionXY(point_2, direction, digit);
            // заполнить расстояние между точками
            // поиск пересечений
            Point repeatPoint = point_1.fillArrayPoint(point_1, point_2, allCoordinats); //?????
            // если совпало то вывести точку совпадений и закончить
             if (repeatPoint != null)
            {
                digit = Math.abs(repeatPoint.getX() - 5000) + Math.abs(repeatPoint.getY() - 5000);
                System.out.println("Count steps: " + digit);
                return;
            }
//

            // передвинуть вторую точку на место первой
            point_1.setX(point_2.getX());
            point_1.setY(point_2.getY());


            // для части 1
             transformPositionXY(coordinats, direction, digit);
        }


    }

    private static Point findCommonPoin(int[][] allCoordinats, Point point_2) {
        for (int row = 0; row < allCoordinats.length; row++){
            for (int col = 0; col < allCoordinats.length; col++)
            {
                if(allCoordinats[row][col] > 1){
                    point_2.setY(row);
                    point_2.setX(col);
                 }
            }
        }
        return point_2;
    }

    /* Получить конечную координату положения XY */
    private static int[] getPointXY(String[] sequenceRL) {
        int digit;/* Направление движения 0 - север, 1 - восток, 2 - юг, 3 - запад */
        int direction = 0;
        int[] coordinats = new int[2];
        for (int i = 0; i < sequenceRL.length; i++) {

            /* Повернуть на право - на восток */
            if (sequenceRL[i].contains("R")) {
                digit = getDigitInt(sequenceRL[i], "R");
                // сменить направление на + 1
                direction++;
            } else {
                /* Повернуть на лево - на запад */
                digit = getDigitInt(sequenceRL[i], "L");
                // сменить направление на -1
                direction--;
            }
            direction = normalizeDirection(direction);
            transformPositionXY(coordinats, direction, digit);
        }
        return coordinats;
    }

    /* Привести  направление к числами от 0 до 3 */
    private static int normalizeDirection(int direction) {
        if (direction == -1) {
            return 3;
        } else if (direction == 4) {
            return 0;
        }
        return direction;
    }

    /* Изменить состояния координат в массиве  */
    private static void transformPositionXY(int[] coordinats, int direction, int digit) {

        // если 0, то изменить у+  (х)
        // если 1, то изменить х+  (у+)
        // если 2, то изменить у-  (х)
        // если 3, то изменить х-  (у)
        // если 4, то 0
        // если -1, то 3
        switch (direction) {
            case (0):
                coordinats[1] += digit;
                break;
            case (1):
                coordinats[0] += digit;
                break;
            case (2):
                coordinats[1] -= digit;
                break;
            case (3):
                coordinats[0] -= digit;
                break;
        }
    }

    /* Изменить состояние координат в объекте point */
    private static void transformPositionXY(Point point, int direction, int digit) {

        // если 0, то изменить у+  (х)
        // если 1, то изменить х+  (у+)
        // если 2, то изменить у-  (х)
        // если 3, то изменить х-  (у)
        // если 4, то 0
        // если -1, то 3
        switch (direction) {
            case (0):
                point.setY(point.getY() + digit);
                break;
            case (1):
                point.setX(point.getX() + digit);
                break;
            case (2):
                point.setY(point.getY() - digit);
                break;
            case (3):
                point.setX(point.getX() - digit);
                break;
        }
    }


    /* Получить из элемента строкового массива число */
    private static int getDigitInt(String s2, String character) {
        String stepsReplese = s2.replace(character, "");
        int stepsDigit = Integer.parseInt(stepsReplese);
        return stepsDigit;
    }
}
