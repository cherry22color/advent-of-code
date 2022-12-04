package adventOfCode_2022.day3_22;

import commonFile.ReadFile;

import java.io.FileNotFoundException;
//https://adventofcode.com/2022/day/3#part2

/**
 * -- часть 1--
 * Например, предположим, что у вас есть следующий
 * список содержимого шести рюкзаков:
 * <p>
 * vJrwpWtwJgWrhcsFMMfFFhFp
 * jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
 * PmmdzqPrVvPwwTWBwg
 * wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
 * ttgJtRGJQctTZtZT
 * CrZsJsPPZsGzwwsLwLmpwMDw
 * <p>
 * В первом рюкзаке находятся предметы vJrwpWtwJgWrhcsFMMfFFhFp,
 * то есть в его первом отделении находятся предметы vJrwpWtwJgWr,
 * а во втором отделении -- предметы hcsFMMfFFhFp. Единственный тип элементов,
 * который появляется в обоих отсеках, — строчные p.
 * Отделения второго рюкзака содержат jqHRNqRjqzjGDLGLи rsFMfFZSrLrFZsSL.
 * Единственный тип элемента, который отображается в обоих отсеках, — верхний регистр L.
 * Отделения третьего рюкзака содержат PmmdzqPrVи vPwwTWBwg; единственный
 * распространенный тип элемента — верхний регистр P.
 * Отделения четвертого рюкзака имеют только общий тип v.
 * Отделения пятого рюкзака имеют только общий тип t.
 * Отделения шестого рюкзака имеют только общий тип s.
 * Чтобы упростить перестановку элементов, каждый тип элемента можно преобразовать в приоритет :
 * <p>
 * Типы элементов нижнего регистра имеют приоритеты от 1 до 26 a.z
 * Типы элементов Aв верхнем регистре Zимеют приоритеты от 27 до 52.
 * В приведенном выше примере приоритет типа предмета, который
 * появляется в обоих отсеках каждого рюкзака, равен
 * 16 ( p), 38 ( L), 42 ( P), 22 ( v), 20 ( t) и 19 ( s); сумма этих 157.
 * <p>
 * -- часть 2--
 * Каждый набор из трех строк в вашем списке соответствует одной группе,
 * но у каждой группы может быть свой тип элемента значка. Итак,
 * в приведенном выше примере рюкзаки первой группы — это первые три строки:
 * <p>
 * vJrwpWtwJgWrhcsFMMfFFhFp
 * jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
 * PmmdzqPrVvPwwTWBwg
 * А рюкзаки второй группы – это следующие три строчки:
 * <p>
 * wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
 * ttgJtRGJQctTZtZT
 * CrZsJsPPZsGzwwsLwLmpwMDw
 * В первой группе единственный тип предметов, который присутствует
 * во всех трех рюкзаках, — строчные r; это должны быть их значки.
 * Во второй группе их тип элемента значка должен быть Z.
 * <p>
 * Приоритеты для этих элементов все еще должны быть найдены,
 * чтобы организовать усилия по прикреплению наклеек:
 * здесь они равны 18 ( r) для первой группы и 52 ( Z) для второй группы. Сумма этих 70.
 */

public class DAY3_22 {
    public static void main(String[] args) throws FileNotFoundException {
        String name = "DAY3_22";
        ReadFile input = new ReadFile(name);
        String[] contentBackpack = input.readFileArray();
        int sizeArrContent = contentBackpack.length;
        int sumPriorities = 0;


        // часть 1
        sumPriorities = getSumPrioritiesPart1(contentBackpack, sizeArrContent, sumPriorities);
        int c = 9;
        System.out.println("Sum of priorities in part 1 = " + sumPriorities);

        // часть 2
        // от -1  для возможности увеличения при последующих итерациях номера первого рюкзака
        sumPriorities = getSumPrioritiesPart2(contentBackpack, sizeArrContent, sumPriorities);
        System.out.println("Sum of priorities in part 2 = " + sumPriorities);
    }

    private static int getSumPrioritiesPart2(String[] contentBackpack, int sizeArrContent, int sumPriorities) {
        int numberBackpack = 0;
        while (numberBackpack < sizeArrContent) {
            // выбрать 3 строки
            String backpack1S = contentBackpack[numberBackpack]; //  увеличивает значение индекса после записи
            String backpack2S = contentBackpack[numberBackpack + 1];
            String backpack3S = contentBackpack[numberBackpack + 2]; // выходит значение индекса за предел массива
            numberBackpack += 3;

            char[] backpack1C = backpack1S.toCharArray();
            char[] backpack2C = backpack2S.toCharArray();
            char[] backpack3C = backpack3S.toCharArray();

            char letter = '\0'; // хранит встреченный символ
            // проверка содержит ли вторая подстрока что-то из первой
            for (int i = 0; i < backpack1C.length; i++) {
                // содержит ли строка символ
                boolean contains = false;
                // проверка на наличие во второй строке
                for (char c2 : backpack2C) {
                    if (c2 == backpack1C[i]) {
                        // проверка на наличие в третьей строке
                        for (char c3 : backpack3C) {
                            if (c3 == backpack1C[i]) {
                                contains = true;
                                letter = backpack1C[i];
                                break;
                            }
                        }
                    }
                }
                if (contains) {

                    sumPriorities = updateSumPriorities(sumPriorities, letter);
                    break; // убирает  повторное нахождение уже найденных букв
                }
            }
        }
        return sumPriorities;
    }

    // часть 1
    private static int getSumPrioritiesPart1(String[] contentBackpack, int sizeArrContent, int sumPriorities) {
        for (int numberBackpack = 0; numberBackpack < sizeArrContent; numberBackpack++) {
            String currentBackpack = contentBackpack[numberBackpack];

            int sizeString = currentBackpack.length();
            String backpack_1S = currentBackpack.substring(0, sizeString / 2);
            String backpack_2S = currentBackpack.substring(sizeString / 2, sizeString);

            char[] backpack1C = backpack_1S.toCharArray();
            char[] backpack2C = backpack_2S.toCharArray();

            sumPriorities = findDuplicateItem(backpack1C, backpack2C, sumPriorities);
        }
        return sumPriorities;
    }

    // находит повторяющийся предмет, как только находит, обновляет sumPriorities и выходит из обоих for
    private static int findDuplicateItem(char[] backpack1C, char[] backpack2C, int sumPriorities) {
        char letter = '\0'; // хранит встреченный символ
        // проверка содержит ли вторая подстрока что-то из первой
        for (int i = 0; i < backpack1C.length; i++) {
            // содержит ли строка символ
            for (char c : backpack2C) {
                if (c == backpack1C[i]) {
                    letter = backpack1C[i];
                    sumPriorities = updateSumPriorities(sumPriorities, letter);
                    return sumPriorities;
                }
            }
        }

        return sumPriorities;
    }

    private static int updateSumPriorities(int sumPriorities, char letter) {
        int uppercaseAsciiOffset = 38;
        int lowercaseAsciiOffset = 96;

        // буквы A-Z  распределены по значениям 27-52, коррекция -38 от аски
        // буквы a-z распределены по значениям 1-26, коррекция -96 от аски
        if (letter >= 'A' && letter <= 'Z') {
            sumPriorities += letter - uppercaseAsciiOffset;
        } else if (letter >= 'a' && letter <= 'z') {
            sumPriorities += letter - lowercaseAsciiOffset;
        }
        return sumPriorities;
    }
}
