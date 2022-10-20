package adventOfCode_2016.day3_16_willAddClass;

import adventOfCode_2016.ReadFile;

import java.io.FileNotFoundException;

public class Main {
    //  для проверки 5, 10, 25 - нет; 7, 4, 4 - да.
    public static void main(String[] args) throws FileNotFoundException {
        // прочитать содержимое файла
        ReadFile readFile = new ReadFile("day3_16_willAddClass");
        // сохранить прочитанное файла в массив строк вида(  810  679   10)
        String[] sideArray = readFile.readFileArray();
        // передать массив в класс треугольник на подсчет треугольников в массиве
        Triangle triangle = new Triangle();

        /* Часть 1 */
        // подсчитать количество треугольников в файле
        triangle.makeTriangle(sideArray);

        System.out.println("Count triangle in part one " + triangle.getCountTriangle());
        // установить количество треугольников в 0
        triangle.setCountTriangle(0);

        /* Часть 2 */
        triangle.makeTriangleVertically(sideArray);
        System.out.println("Count triangle in file part two " + triangle.getCountTriangle());


    }
}
