package adventOfCode_2016.day2_16;

import commonFile.ReadFile;

import java.io.FileNotFoundException;

/**
 * Далее в документе объясняется, что каждую кнопку, которую нужно нажать,
 * можно найти, начав с предыдущей кнопки и двигаясь к соседним кнопкам на клавиатуре:
 * Uперемещение вверх, Dперемещение вниз, Lперемещение влево и Rперемещение вправо.
 * Каждая строка инструкций соответствует одной кнопке, начиная с предыдущей кнопки
 * (или, для первой строки, кнопки «5» ); нажмите любую кнопку, на которой вы находитесь,
 * в конце каждой строки. Если ход не приводит к кнопке, игнорируйте его.
 * <p>
 * Вы больше не можете сдерживаться, поэтому решаете выяснить код,
 * пока идете в ванную. Вы представляете себе клавиатуру вот так:
 * <p>
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * <p>
 * Предположим, ваши инструкции:
 * <p>
 * ULL
 * RRDDD
 * LURDL
 * UUUUD
 * <p>
 * Вы начинаете с «5» и двигаетесь вверх (к «2»), влево (к «1»)
 * и влево (вы не можете и остаетесь на «1»), поэтому первая кнопка 1.
 * Начиная с предыдущей кнопки («1»), вы дважды двигаетесь вправо (к «3»),
 * а затем трижды вниз (останавливаясь на «9» после двух ходов и игнорируя третий), заканчивая 9.
 * Продолжая от «9», вы двигаетесь влево, вверх, вправо, вниз и влево, заканчивая 8.
 * Наконец, вы двигаетесь вверх четыре раза (останавливаясь на «2»),
 * затем один раз вниз, заканчивая 5.
 * Итак, в этом примере код ванной комнаты 1985.
 *
 *
 *   Часть 2
 *
 *    1
 *   2 3 4
 * 5 6 7 8 9
 *   A B C
 *     D
 * Вы по-прежнему начинаете с «5» и останавливаетесь, когда находитесь на краю,
 * но с учетом тех же инструкций, что и выше, результат будет совсем другим:
 *
 * Вы начинаете с «5» и вообще не двигаетесь (вверх и влево — оба края), заканчивая на 5.
 * Продолжая от «5», вы дважды двигаетесь вправо и три раза вниз (через «6», «7», «B», «D», «D»),
 * заканчивая на D.
 * Затем из «D» вы двигаетесь еще пять раз (через «D», «B», «C», «C», «B»), заканчивая на B.
 * Наконец, после еще пяти ходов вы закончите на 3.
 * Итак, учитывая фактическую раскладку клавиатуры, код будет 5DB3.
 */


public class DAY2_16 {
    public static void main(String[] args) throws FileNotFoundException {
        ReadFile readFile = new ReadFile("day2_16");
        String[] insructionArr = readFile.readFileArray();

        int position = 5;
        int[] password = new int[5];
        /*
         * 1 2 3
         * 4 5 6
         * 7 8 9
         */
         getPassword_1(insructionArr, position, password);
        System.out.println("Password: " + password[0] + password[1] + password[2] + password[3] + password[4]);

        /* Часть 2*/
         /*
         *     1
         *   2 3 4
         * 5 6 7 8 9
         *   A B C          10 11 12
         *     D               13
         */
        getPassword_2(insructionArr, position);
    }

    private static void getPassword_2(String[] insructionArr, int position) {
        char[] letterCodes = {'A', 'B', 'C', 'D'};

        for (int line = 0; line < insructionArr.length; line++ ){
            char[] instructionChar = insructionArr[line].toCharArray();
             for (int character = 0; character < instructionChar.length; character++) {
                // если сейчас символ U, то сделать -4, (для 3 и 13 = -2)
                if ((instructionChar[character] == 'U') &&
                        (position != 1 && position != 2 && position != 4 && position != 5 && position != 9)) {
                   if (position == 3 || position == 13){
                       position -= 2;
                   } else { position -= 4;}
                    // eсли L, то шаг влево -1, и проверить выход за левую границу
                } else if ((instructionChar[character] == 'L') &&
                        (position != 1 && position != 2 && position != 5 && position != 10 && position != 13)) {
                    position--;
                    // если R, то шаг вправо +1, и проверить выход за правую границу
                } else if ((instructionChar[character] == 'R') &&
                        (position != 1 && position != 4 && position != 9 && position != 12 && position != 13)) {
                    position++;
                    //если D, то спустится вниз по табло +3, и проверить на наличие нижней границы
                } else if ((instructionChar[character] == 'D') &&
                        (position != 5 && position != 10 && position != 13 && position != 12 && position != 9)) {
                    if (position == 1 || position == 11){
                        position += 2;
                    } else { position += 4;}
                }
            }
            // записать информацию в массив символов
             if (position >= 10){
                 System.out.print(letterCodes[position - 10]);
            } else {
                System.out.print(position);
             }
         }
    }

    /* Часть 1*/
    /* Стандартное табло 4х4 */
    private static void getPassword_1(String[] insructionArr, int position, int[] password) {
        // берем первый элемент массива
        // разбиваем его на массив символов
        // password массив для хранения пароля
        // переменная для хранения текущего положения  position
        // начало с позиции 5
        for (int line = 0; line < insructionArr.length; line++) {

            char[] instructionChar = insructionArr[line].toCharArray();
            // применение массива символов для передвижения по табло чисел
            //  1 2 3
            //  4 5 6
            //  7 8 9
            for (int character = 0; character < instructionChar.length; character++) {
                // если сейчас символ U, то сделать -3, если число больше 3
                if ((instructionChar[character] == 'U') && (position > 3)) {
                    position -= 3;
                    // eсли L, то шаг влево -1, и проверить выход за левую границу табло
                } else if ((instructionChar[character] == 'L') &&
                        (position != 1 && position != 4 && position != 7)) {
                    position--;
                    // если R, то шаг вправо +1, и проверить выход за правую границу
                } else if ((instructionChar[character] == 'R') && (position % 3 != 0)) {
                    position++;
                    //если D, то спустится вниз по табло +3, и проверить на наличие нижней границы
                } else if ((instructionChar[character] == 'D') && (position < 7)) {
                    position += 3;
                }
            }
            // сохранить последнее значение позиции на табло в массив пароля
            password[line] = position;
        }
     }
}


