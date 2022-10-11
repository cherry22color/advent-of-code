package adventOfCode_2015;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

// https://adventofcode.com/2015/day/5

/**
 * Санте нужна помощь, чтобы выяснить, какие строки в его текстовом файле являются непослушными или хорошими.
 * <p>
 * Хорошая строка — это строка со всеми следующими свойствами:
 * <p>
 * Он содержит не менее трех гласных ( aeiouтолько), например aei, xazegovили aeiouaeiouaeiou.
 * Он содержит как минимум одну букву, которая встречается дважды подряд,
 * например xx, abcdde( dd) или aabbccdd( aa, bb, cc, или dd).
 * Он не содержит строк ab, cd, pqили xy,
 * даже если они являются частью одного из других требований.
 * <p>
 * Например:
 * <p>
 * ugknbfddgicrmopnудобен тем, что содержит по крайней мере три гласных
 * ( u...i...o...), двойную букву ( ...dd...) и ни одну из запрещенных подстрок.
 * aaaэто хорошо, потому что в нем есть как минимум три гласных и двойная буква,
 * хотя буквы, используемые по разным правилам, перекрываются.
 * jchzalrnumimnmhp непослушный, потому что он не имеет двойной буквы.
 * haegwjzuvuyypxyu непослушный, потому что он содержит строку xy.
 * dvszwmarrgswjxmb непослушный, потому что он содержит только одну гласную
 * <p>
 * сколько послушных строк?
 * <p>
 * часть 2
 * <p>
 * Ни одно из старых правил не применимо, поскольку все они явно нелепы.
 * <p>
 * Итак, хорошая строка — это строка со всеми следующими свойствами:
 * <p>
 * Он содержит пару любых двух букв, которые встречаются в строке
 * не менее двух раз без перекрытия, например xyxy( xy) или
 * aabcdefgaa( aa), но не как aaa( aa, но перекрываются).
 * Он содержит по крайней мере одну букву, которая повторяется
 * ровно с одной буквой между ними, например xyx, abcdefeghi( efe) или даже aaa.
 * Например:
 * <p>
 * qjhvhtzxzqqjkmpb это хорошо, потому что у него есть пара,
 * которая встречается дважды ( qj), и буква, которая повторяется
 * ровно с одной буквой между ними ( zxz).
 * xxyxxэто хорошо, потому что у него есть пара, которая появляется дважды,
 * и буква, которая повторяется с одной между ними, даже если буквы,
 * используемые каждым правилом, перекрываются.
 * uurcxstgmygtbstgявляется непослушным, потому что у него есть пара
 * ( tg), но нет повторения с одной буквой между ними.
 * ieodomkazucvgmuyявляется непослушным, потому что
 * в нем есть повторяющаяся буква с единицей между ( odo),
 * но нет пары, которая появляется дважды.
 */

public class DAY5_15 {
    public static void main(String[] args) throws FileNotFoundException {
        String[] strings = readFile("DAY5_15");
        int nice = 0; /* Счетчик послушных строк */

        /* Часть 2 */

        nice = getNice_2(strings);
        System.out.println("Count nice string №2: " + nice);

        /* Часть 1 */
        nice = getNice_1(strings);
        System.out.println("Count nice strings №1: " + nice);
    }

    /* Послушными строками считаются:
       встречается две и более одинаковых пар xyxy,
      встречаются тройки символов крайинии которых равны xyx */
    private static int getNice_2(String[] string) {
        int nice = 0;
        for (int row = 0; row < string.length; row++) {
            String current = string[row];
            boolean coupleChar = false;

            /* Поиск подстроки в строке*/
            for (int index = 0; index < current.length() - 2; index++) {
                String couple = current.substring(index, index + 2);  /*Выбор подстроки из 2 символов */
                String allString = current.substring(index + 2); /* Оставшаяся часть строки для сравнения */
                if (allString.contains(couple)) {
                    coupleChar = true;
                    break;
                }
            }

            /* Найти тройку символов, где два крайних идентичны */
            boolean triplyChar = false;
            for (int index = 0; index < current.length() - 2; index++) {
                String triply = current.substring(index, index + 3);  /* Сравнение крайних символов строки из 3 символов*/
                /* aaa допустимо*/
                if (triply.charAt(0) == triply.charAt(2)) {
                    triplyChar = true;
                    break;
                }
            }

            /* Проверка строки на послушать/непослушностьб подсчет послушных */
            if (coupleChar && triplyChar) {
                nice++;
            }
        }
        return nice;
    }

    /* Часть 1
     * Получить количество послушных строчек */
    /* Проверка выполнения 3 условий:
     3 гласные в строке aeiou, наличие пары идентичных символов, нет  ab, cd, pq или xy */
    private static int getNice_1(String[] string) {
        int nice = 0;
        for (int row = 0; row < string.length; row++) {
            String line = string[row];

            int vowelCount = 0; /*счетчик гласных букв */
            boolean equalChar = false;

            /* Цикл для проработки строки под каждым индексом*/
            for (int i = 0; i < line.length() - 1; i++) {
                /* Поиск пары идентичных символов */
                if (line.charAt(i) == line.charAt(i + 1)) {
                    equalChar = true;
                    break;
                }
            }

            String vowels = "aeiou";
            for (int i = 0; i < line.length(); i++) {
                /*Подсчет гласных aeiou */

                char currentChar = line.charAt(i);
                if (vowels.indexOf(currentChar) != -1)
                {
                    vowelCount++;
                }
            }

            /* Для условия - не содежит ab, cd, pq или xy */
            String[] ban = {"ab", "cd", "pq", "xy"};
            boolean noContainChar = true;
            for (int i = 0; i < ban.length; i++) {
                String char2 = ban[i];
                if (line.contains(char2)) {
                    noContainChar = false;
                    break;
                }
            }

            /*Проверка выполнения 3 условий:
             3 гласные в строке aeiou, наличие пары идентичных символов, нет  ab, cd, pq или xy */
            if (noContainChar && (vowelCount >= 3) && equalChar) {
                nice++;
            }
        }
        return nice;
    }

    /* чтение информации с файла */
    private static String[] readFile(String fileName) throws FileNotFoundException {
        FileReader file = new FileReader(fileName);
        //  scanner  считывает содержимое файла и делит всё на части (строки т.е. токены)
        // \n символ разделитель для токенов
        Scanner fileScsnner = new Scanner(file);

        //коллекции
        ArrayList<String> lines = new ArrayList<String>();

        while (fileScsnner.hasNext()) {
            lines.add(fileScsnner.nextLine());
        }
        fileScsnner.close();

        return lines.toArray(new String[lines.size()]);
    }
}



