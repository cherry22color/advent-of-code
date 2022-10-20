package adventOfCode_2015.day6_15;
// https://adventofcode.com/2015/day/6

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * вы решили разместить миллион светильников в сетке 1000x1000.
 * Огни в вашей сетке пронумерованы от 0 до 999 в каждом направлении;
 * огни на каждом углу находятся в 0,0, 0,999, 999,999и 999,0.
 * Инструкции включают в себя, следует ли turn on, turn off,
 * или toggle различные включающие диапазоны, заданные как пары координат.
 * Каждая пара координат представляет противоположные углы прямоугольника включительно;
 * таким образом, пара координат 0,0 through 2,2относится
 * к 9 источникам света в квадрате 3x3. Все огни начинают выключаться.
 * <p>
 * turn on 0,0 through 999,999 включил бы (или оставил включенным) каждый свет.
 * toggle 0,0 through 999,0 переключал бы первую строку из 1000 источников света,
 * выключая те, которые были включены, и включая те, которые были выключены.
 * turn off 499,499 through 500,500 выключит (или не выключит) четыре средних индикатора.
 * <p>
 * часть 2
 * <p>
 * Световая сетка, которую вы купили, на самом деле имеет индивидуальную регулировку яркости;
 * каждый свет может иметь яркость ноль или больше. Все огни начинаются с нуля.
 * <p>
 * Фраза turn on на самом деле означает, что вы должны увеличить яркость этих огней на 1.
 * <p>
 * Фраза turn off на самом деле означает, что вы должны уменьшить яркость этих огней на 1, до минимума нуля.
 * <p>
 * Фраза toggle на самом деле означает, что вы должны увеличить яркость этих огней на 2.
 * <p>
 * Какова общая яркость всех огней?
 */
public class DAY6_15 {

    public static void main(String[] args) throws FileNotFoundException {
        String[] insrtuctionS = readFile("DAY6_15");
        int[][] lampsArray = new int[1000][1000];
        boolean partOne = true;

        /* считать строку */
        parseString(insrtuctionS, lampsArray, partOne);
        // подсчет количества включенных ламп
        int countLampOn = 0;
        countLampOn = getCountLampOn(lampsArray);
        System.out.println("Count lamp " + countLampOn);
        // булевую переменную, часть 1 изменить на ложное значение, для входа в часть 2
        partOne = false;

        /* Часть 2 */

        parseString(insrtuctionS, lampsArray, partOne);
        countLampOn = getCountLampOn(lampsArray);
        System.out.println("Count lamp2 " + countLampOn);
    }

    /* Изменить каждую строку */
    private static void parseString(String[] insrtuctionS, int[][] lampsArray, boolean partOne) {
        for (int rowString = 0; rowString < insrtuctionS.length; rowString++) {
            // считать текущую инструкцию
            String instruction = insrtuctionS[rowString];
            // массив для хранения 4 чисел из строки
            int[] digitArr = new int[4];
            String digitSplit = " ";

            if (instruction.contains("turn off")) {
                // убрать слова
                getDigitArray(instruction, digitArr, "turn off ");
                // изменение состояния элементов подмассива массива
                transformLamp(lampsArray, digitArr, "turn off", partOne);
            } else if (instruction.contains("turn on")) {
                getDigitArray(instruction, digitArr, "turn on ");
                 transformLamp(lampsArray, digitArr, "turn on", partOne);
            } else if (instruction.contains("toggle")) {
                getDigitArray(instruction, digitArr, "toggle ");
                 transformLamp(lampsArray, digitArr, "toggle", partOne);
            }
        }
    }

    /* Изменить сосояние лампы */
    private static void transformLamp(int[][] lampsArray, int[] digitArr, String transform, boolean part) {
        for (int row = digitArr[0]; row <= digitArr[2]; row++) {
            for (int col = digitArr[1]; col <= digitArr[3]; col++) {
                     /* часть 1
                     изменить состояние лампочек на вкл/ выкд
                     */
                if (part) {
                    if (transform == "turn off") {
                        lampsArray[row][col] = 0;
                    } else if (transform == "turn on") {
                        lampsArray[row][col] = 1;
                    } else {
                        lampsArray[row][col] = lampsArray[row][col] == 1 ? 0 : 1;
                    }
                } else {
                    if (transform == "turn off") {
                        lampsArray[row][col] += lampsArray[row][col] >= 1 ? -1 : 0;
                    } else if (transform == "turn on") {
                        lampsArray[row][col] += 1;
                    } else {
                        lampsArray[row][col] += 2;
                    }
                }

            }
        }
    }

    /* Подсчет количества включенных ламп */
    private static int getCountLampOn(int[][] lampsArray) {
        int countLampOn = 0;
        for (int[] row : lampsArray) {
            // аналогично countLampOn += Arrays.stream(row).sum(); (Java 8 Stream API), преобразует массыив в поток чисел
            for (int col : row) {
                countLampOn += col;
            }
            // Присвоить элементам подмассива значения 0
            Arrays.fill(row, 0);
        }
        return countLampOn;
    }

    /* Получить массив 4 чисел*/
    private static void getDigitArray(String instruction, int[] digitArr, String s) {
        // переменная для хранения разбитых  строк(split)
        String digitSplit;
        digitSplit = instruction.replace(s, "");
        String[] digitString = digitSplit.split(",");
        // записать строковое число в числовой тип данных
        for (int i = 0; i < digitString.length; i++) {
            digitArr[i] = Integer.parseInt(digitString[i]);
        }
    }

    /* Считать содержимое файла */
    private static String[] readFile(String fileName) throws FileNotFoundException {
        FileReader file = new FileReader(fileName);
        //  scanner  считывает содержимое файла и делит всё на части (строки т.е. токены)
        // \n символ разделитель для токенов
        Scanner fileScanner = new Scanner(file);

        //коллекции
        ArrayList<String> lines = new ArrayList<String>();

        while (fileScanner.hasNext()) {
            /* Заменяет ненужное слово through на пустоту */
            // nextLine смотреть полную строку до \n
            String line = fileScanner.nextLine().replace(" through ", ",");
            lines.add(line);
        }
        fileScanner.close();
        // какой именно массив передается(строки)
        return lines.toArray(new String[lines.size()]);
    }
}
