package adventOfCode_2022.day4_22;

import commonFile.ReadFile;
/**
 * -- часть 1--
 * .234.....  2-4
 * .....678.  6-8
 *
 * .23......  2-3
 * ...45....  4-5
 *
 * ....567..  5-7
 * ......789  7-9
 *
 * .2345678.  2-8
 * ..34567..  3-7
 *
 * .....6...  6-6
 * ...456...  4-6
 *
 * .23456...  2-6
 * ...45678.  4-8
 * Некоторые пары заметили, что одно из их заданий полностью содержит другое.
 * Например, 2-8полностью содержит 3-7и 6-6полностью содержится в 4-6.
 * В парах, где одно задание полностью содержит другое, один эльф в паре
 * будет исключительно убирать разделы, которые уже будет убирать их партнер,
 * поэтому они кажутся наиболее нуждающимися в пересмотре. В этом примере есть 2такие пары.
 *
 * В скольких парах присваивания один диапазон полностью содержит другой?
 */

import java.io.FileNotFoundException;

//https://adventofcode.com/2022/day/4
public class DAY4_22 {
    public static void main(String[] args) throws FileNotFoundException {
        String name = "test";
        ReadFile input = new ReadFile(name);
        String[] coupleElveArr = input.readFileArray();
        int countCouper = 0;
        int sizeArrCouper = coupleElveArr.length;
        for (int couple = 0; couple < sizeArrCouper; couple++) {
            String[] currentCouple = coupleElveArr[couple].split(",");
            String border1 = currentCouple[0];
            String border2 = currentCouple[1];

            // выделить начало и конец для каждого эльфа  в массив по 2 элемента
            String[] digitRange_1 = border1.split("-");
            String[] digitRange_2 = border2.split("-");

            // начало и конец превратить в числа
            int start1 = Integer.parseInt(digitRange_1[0]);
            int finish1 = Integer.parseInt(digitRange_1[1]);
            int start2 = Integer.parseInt(digitRange_2[0]);
            int finish2 = Integer.parseInt(digitRange_2[1]);

            // строки для хранения диапазонов
            String rangeStr_1 = new String();
            String rangeStr_2 = new String();

            // в строки добавить элементы из диапазонов  .234.....  2-4
            if (start1 != finish1) { //  если начало и конец не равны 14-14
                for (int elve1 = start1; elve1 <= finish1; elve1++) {
                    rangeStr_1 += elve1;
                }
            } else {
                rangeStr_1 += start1;
            }

            if(start2 != finish2) { //  если начало и конец не равны 14-14
                for (int elve2 = start2; elve2 <= finish2; elve2++) {
                    rangeStr_2 += elve2;
                }
            } else {
                rangeStr_2 += start2;
            }

            // равны ли стоки по длине
            if (rangeStr_1.length() == rangeStr_2.length()) {
                if (rangeStr_1.equals(rangeStr_2)) {
                    countCouper++;
                }
            } else if (rangeStr_1.length() > rangeStr_2.length()) { // если первая строка больше второй
                if (rangeStr_1.contains(rangeStr_2)) {
                    countCouper++;
                }
            } else if (rangeStr_1.length() < rangeStr_2.length()) { //  // если вторая строка больше первой
                if (rangeStr_2.contains(rangeStr_1)) {
                    countCouper++;
                }
            }
        }
        System.out.println("In " + countCouper + " pairs does one range fully contain the other.");
    }
}
