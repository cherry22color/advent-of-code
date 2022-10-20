package adventOfCode_2015.day1_15;
//https://adventofcode.com/2015/day/1

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *  Санта пытается доставить подарки в большом многоквартирном доме,
 *  но не может найти нужный этаж — полученные им направления
 *  немного сбивают с толку. Он начинает с первого этажа (floor 0),
 *  а затем следует инструкциям по одному персонажу за раз.
 *
 * Открывающая скобка (означает, что он должен подняться на один этаж,
 * а закрывающая скобка )означает, что он должен спуститься на один этаж.
 *
 * Многоквартирный дом очень высокий, а подвал очень глубокий;
 * он никогда не найдет верхний или нижний этажи.
 *
 * Найдите позицию первого персонажа, из-за которой он входит в подвал (этаж -1).
 * Первый символ в инструкции имеет position 1, второй символ имеет position 2и так далее.
 */
public class DAY1_15 {
    public static void main(String[] args) throws FileNotFoundException {
        //  throws FileNotFoundException не обращать внимание на отсутствие файла (сомнтительное решение)
        // запись в переменную stringFloor
        String stringFloor = readingFile();

        goToFloor(stringFloor);

    }

    private static void goToFloor(String stringFloor) {
        int floor = 0;

        boolean basement = true;                        /* для обозначения первого захода в подвал */
        for (int i = 0; i < stringFloor.length(); i++) {
            // сравнение элемента строки с символом
            if (stringFloor.charAt(i) == '(') {
                floor++;
            }
            else {
                floor--;
            }
            /* часть 2 */
            if ((floor == -1) && (basement == true)) {
                // массив начинается с 0 эл., вывести номер шага на котором он попал в подвал
                System.out.println("Santa entered the basement after the " + (i+1) + " floor.");
                basement = false;
            }
        }
        //  для 1 части. вывод последнего номера этажа на которм остановился
        System.out.println("Santa is now on the " + floor + " floor");
    }

    private static String readingFile() throws FileNotFoundException {
       String stringFloor = new String();
        FileReader file = new FileReader("DAY1_15.txt");
        Scanner fileScanner = new Scanner(file);  // окрываем файл для чтения

        while (fileScanner.hasNext())
        {
            stringFloor = fileScanner.nextLine();
        }
        fileScanner.close();// закрываем файл
        return stringFloor;
    }
}
