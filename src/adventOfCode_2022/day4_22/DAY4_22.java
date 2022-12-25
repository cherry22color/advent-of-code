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
 *
 * -- часть 2--
 * В приведенном выше примере первые две пары ( 2-4,6-8и 2-3,4-5)
 * не перекрываются, а остальные четыре пары
 * ( 5-7,7-9, 2-8,3-7, 6-6,4-6и 2-6,4-8) перекрываются:
 *
 * 5-7,7-9перекрывается в одном разделе, 7.
 * 2-8,3-7перекрывает все разделы 3через 7.
 * 6-6,4-6перекрывается в одном разделе, 6.
 * 2-6,4-8перекрывается в разделах 4, 5, и 6.
 * Таким образом, в этом примере количество перекрывающихся пар назначений равно 4.
 *
 * В скольких парах присваивания диапазоны перекрываются?
 */

import java.io.FileNotFoundException;

//https://adventofcode.com/2022/day/4
public class DAY4_22 {
    public static void main(String[] args) throws FileNotFoundException {
        String name = "DAY4_22";
        ReadFile input = new ReadFile(name);
        String[] coupleElveArr = input.readFileArray();
        int countCouperContain = 0; // полностью содержит
        int countCouperNoOverlap = 0; // частично перекрывается
        int allCount = 0;

        int sizeArrCouper = coupleElveArr.length;

        for (int pairs = 0; pairs < sizeArrCouper; pairs++) {
            String[] currentCouple = coupleElveArr[pairs].split(",");
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

            // часть 1
            if (start1 <= start2 && finish1 >= finish2) {
                countCouperContain++;
            } else if (start1 >= start2 && finish1 <= finish2) {
                countCouperContain++;
            }
            // часть 2
            if(start1 > finish2){
                countCouperNoOverlap++;
            } else if (start2 > finish1){
                countCouperNoOverlap++;
            }
            allCount++;


//           //  countCouper = getCountCouperContain(countCouper, start1, finish1, start2, finish2);
//            if (finish1 >= start2 && start1 <= finish1 && start1 <= start2 && finish1 >= finish2) { // если 2-10 и 3-5
//                countCouperOverlap++;
//            } else if (finish2 >= start1 && start2 <= finish1 && start2 <= start1 && finish2 >= finish1) { // если 3-5 и 2-10
//                countCouperOverlap++;
//
//            } else if (start1 <= start2 && finish1 >= finish2) { // если 2-10 и 3-5
//                countCouperOverlap++;
//            } else if (start2 <= start1 && finish2 >= finish1) { // если 3-5 и 2-10
//                countCouperOverlap++;
//            }
        }
        System.out.println("In " + countCouperContain + " pairs does one range fully contain the other.");
        System.out.println("In " + (allCount-countCouperNoOverlap) + " pairs do the ranges overlap.");

    }

}
