package adventOfCode_2015.day3_15;

/* https://adventofcode.com/2015/day/3 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Санта доставляет подарки в бесконечной двумерной сетке домов.
 *
 * Он начинает с доставки подарка в дом в его стартовой точке,
 * а затем эльф на Северном полюсе звонит ему по радио и сообщает,
 * куда двигаться дальше. Ходы всегда ровно на один дом к северу
 * ( ^), югу ( v), востоку ( >) или западу ( <). После каждого
 * переезда он доставляет очередной подарок в дом на новом месте.
 * Сколько домов получили хотя бы один подарок ?
 */
public class DAY3_15 {

    public static void main(String[] args) throws FileNotFoundException {
        //  throws FileNotFoundException не обращать внимание на отсутствие файла (сомнитительное решение)
        // объект для считывания файла в строку
        String steps = readFile();
        int length = steps.length();
        // массив для заполнения поля подарками
        int[][] houses = new int[length][length];
        /* первый аргумент - начальное значение счетчика  */
        /* последний аргумент для  выбора изменения счетчика, при false. будет i++, иначе i +=2 */
        boolean part = true;
        // переменные для объявления первого значения счетчика
        int santa = 0;
        int robo = 1;
        fillHousesGift(santa,steps, length, houses, part);

        // цикл на подсчет домов с подарками
       int countHouses = putGift(length, houses);
       System.out.println("Santa filled " + countHouses + " houses");

        /**
         * Санта и Робо-Санта начинают с одного и того же места
         * (доставляя два подарка в один и тот же стартовый дом),
         * а затем по очереди двигаются в соответствии с инструкциями эльфа,
         * который упорно читает тот же сценарий, что и в прошлом году.
         *
         * В этом году сколько домов получили хотя бы один подарок ?
         */
       /* часть 2 */
        /* работа Санты и Робота для изменения счетчика цикла в вызываемом методе необходимо сменить значение part */
        part = false;

        /*  обнуление массива*/
        for ( int[] row: houses){
            Arrays.fill(row, 0);
        }
        fillHousesGift(santa,steps, length, houses, part);          /* заполнение подарками */
        fillHousesGift(robo,steps, length, houses, part);

        countHouses = putGift(length, houses);
        System.out.println("Santa and Robo filled " + countHouses + " houses");

    }

    private static void fillHousesGift(int start, String steps, int length, int[][] gifts, boolean part) {
        // выбор индексов массива с которого начинается движение(с середины поля)
        int row = length / 2;
        int column = length / 2;
        gifts[row][column] = 1;                     /*  на момент первого шага в первый дом положен подарок */
        for ( int i = start; i < steps.length(); ) {
            /* действие в соотвествие с выбранным символом из строки */
            if (steps.charAt(i) == '>') {
                gifts[row][++column]++;            /*  добавить подарок в дом по указанным индексам */
            } else if (steps.charAt(i) == '<') {
                gifts[row][--column]++;
            } else if (steps.charAt(i) == '^') {
                gifts[--row][column]++;
            } else if (steps.charAt(i) == 'v') {
                gifts[++row][column]++;
            }

            /* условие изменения счетчика */
            if ( part == false){
                /* в первой части условие будет ложным, при его окончании станет true */
                i +=2;
            } else {
                i++;
            }
        }
    }


    private static int putGift(int length, int[][] houses) {
        int countHouses = 0;
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++){
                if(houses[i][j] > 0){
                    countHouses++;
                }
            }
        }
        return countHouses;
    }

    /* чтение информации с файла */
    private static String readFile() throws FileNotFoundException {
        String line = new String();
        FileReader file = new FileReader("DAY3_15.txt");
        Scanner fileScanner = new Scanner(file);  // окрываем файл для чтения

        while (fileScanner.hasNext()) {
            line = fileScanner.nextLine();
        }
        fileScanner.close();// закрываем файл
        return line;
    }
}
