package adventOfCode_2015.DAY20_15;

import adventOfCode_2016.ReadFile;

import java.util.ArrayList;

public class DAY20_15_forLittleDigit {
    public static void main(String[] args) {
        int puzzle = 29000000;

        //  не больше 700000
        //  getPresents1(puzzle);

        /* Часть 2 */
        getPresents2(puzzle);

    }

    private static void getPresents2(int puzzle) {
        int present = 0;
        int fistElve = 1;
        /* Коллекция для хранения подарков в каждом доме*/
        //  ArrayList<Integer> countPresents = new ArrayList<Integer>();
        int[] presents = new int[puzzle * 10];
        for (int home = 1; ; home++) {
            /* Проверка дома для эльфа */
            int countHomeElve = 0;
            for (int numberElve = fistElve; numberElve <= home; numberElve++) {
                // подсчет домов, в которые зашел конкретный эльф
                for (int i = 1; i <= home; i++) {
                    /* Если номер текущего дома делится без остатка на номер эльфа */
                    if (i % numberElve == 0) {
                        /* Подсчитать номера зашедших эльфов в дом  */
                        //   countPresents.add(i, countPresents.get(i)+numberElve); // Записать в коллекцию новый элемент, взамен имеющегося
                        //present += numberElve;
                        presents[i] += numberElve;
                        countHomeElve++;
                    }
                    /*Подсчитать количество положенных подарков */
                    if (presents[i] * 11 >= puzzle) {
                        System.out.println("number hous = " + home);
                        return;
                    }
                    /* Если эльф прошел 50 домов, то считать количество домов для следующего */
                    if (countHomeElve == 50) {
                        fistElve = numberElve + 1; /* Следующий отсчет эльфа начать после того, кто прошел 50 домов */
                        break;
                    }
                    System.out.println("house " + home + " № elve " + numberElve + " present " + presents[i] * 11);
                }
                if (countHomeElve == 50) {
                    //fistElve = numberElve + 1; /* Следующий отсчет эльфа начать после того, кто прошел 50 домов */
                    countHomeElve = 0;
                    break;
                }
            }
            present = 0;
        }
    }

    /* Часть 1 */
    // расчет ориентирован на нахождение в конктреном доме
    private static void getPresents1(int puzzle) {
        for (int hous = 660000; ; hous++) {
            int present = 1;
            /* Проверка дома для эльфа */
            for (int elve = 2; elve <= hous; elve++) {
                /* Если номер дома делится без остатка на номер эльфа */
                if (hous % elve == 0) {
                    /* Подсчитать количество зашедших эльфов в дом  */
                    present += elve;
                }
                /*Подсчитать количество положенных подарков */
                if (present * 10 >= puzzle) {
                    System.out.println("number hous = " + hous);
                    return;
                }
            }
            // System.out.println("house" + hous +" present " + present*10);
        }
    }
}
