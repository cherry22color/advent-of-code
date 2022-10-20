package adventOfCode_2016.day5_16;
// https://adventofcode.com/2016/day/5
/**
 *  Часть 1
 *  Восьмисимвольный пароль для двери генерируется по одному символу за раз
 *  путем нахождения хэша MD5 некоторого идентификатора двери (ваш ввод головоломки)
 *  и возрастающего целочисленного индекса (начиная с 0).
 *
 * Хэш указывает на следующий символ в пароле, если его шестнадцатеричное представление
 * начинается с пяти нулей . Если это так, шестой символ в хеше является следующим символом пароля.
 *
 * Например, если идентификатор двери abc:
 *
 * Первый индекс, который дает хеш, начинающийся с пяти нулей, это 3231929,
 * который мы находим путем хеширования abc3231929; шестой символ хеша и,
 * следовательно, первый символ пароля — 1.
 * 5017308создает следующий интересный хеш, который начинается с 000008f82...,
 * поэтому второй символ пароля — 8.
 * Третий раз хэш начинается с пяти нулей для abc5278568обнаружения символа f.
 * В этом примере, после продолжения этого поиска в общей сложности восемь раз,
 * пароль будет 18f47a30.
 *
 * Часть 2
 *
 * Вместо того, чтобы просто вводить пароль слева направо, хэш теперь
 * также указывает позицию в пароле, которую необходимо заполнить.
 * Вы по-прежнему ищете хэши, начинающиеся с пяти нулей;
 * однако теперь шестой символ представляет позицию ( 0- 7),
 * а седьмой символ — это символ, который нужно поместить в эту позицию.
 *
 * Результат хеширования 000001f означает, что f это второй символ в пароле.
 * Используйте только первый результат для каждой позиции и игнорируйте недопустимые позиции.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DAY5_16 {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        String key = "ffykfhsq";

        String password = new String(); /* Для части 1 */

        /* часть 1 */
        /* время компиляции примерно 2 минуты */
        password = getPasswordStr(key, password);
        // вывести строку со сменой символов верхнего регистра на нижний
        System.out.println("Password part 1 = " + password.toLowerCase());

        /* часть 2 */
        /* время компиляции примерно 5 минут */
        password = getPasswordArr(key, password);
        // вывести строку со сменой символов верхнего регистра на нижний
        System.out.println("Password part 2 = " + password.toLowerCase());

    }

    /* Часть 2 */
    /* Получить символ из каждой генерации хэша, где первый символ после 00000 будет номер индекса,
     *  а следующий за ним символ сохранится под номером этого индекса в строковый массив.
     *  С учетом отсуствия перезаполнения уже заполненного элемента массива и полного заполнения
     *  новыми значениями массива, так же исключение всех символов кроме диапазона 0-7 для значения индекса*/
    private static String getPasswordArr(String key, String password) throws NoSuchAlgorithmException {
        String[] passwordString = new String[8]; /* Для части 2 */
        int index = 0; // возрастающее число-индекс  складываемое с key
        int indexArr = 0;  // индекс в массиве пароля
        /* Получить символ из каждой генерации хэша для пароля для возврата строки пароля */
        // количество повторов равно количеству букв ключа
        int i = 0;
        // проверяет наличие в массиве значения null
        while (Arrays.asList(passwordString).contains(null)) {
            i++;
            // получить результат работы хэш-фукции над конкатенацией возрастающего индекса и ключа задачи
            String keyhashWithZero = getHashWithZero5(key, index);
            String keyHash = hash(keyhashWithZero);

            // разбить строку на массив символов, выбрать символ после пятого нуля т.е. 5 индекс массива
            char[] character = keyHash.toCharArray();

            // получить 6 символ, проверить будет ли 6 символ число в диапазоне от 0 до 7
            if (character[5] >= '0' && character[5] <= '7') {
                // при явном преобразовании получится номер из таблицы ASCII, 0 начинается с 48 симвла
                indexArr = (int) character[5] - 48;
                // проверить была ли ячейка под текущим индексом заполнена и заполнить ее если null
                if (passwordString[indexArr] == null) {
                    passwordString[indexArr] = String.valueOf(character[6]);
                }
            }

            // запомнить число индекса из строки  bgvyzdsv1038736
            // начало подстроки это длина ключа, конец это длина конкатенации ключа и индекса сформированного выше
            String indexS = keyhashWithZero.substring(key.length(), keyhashWithZero.length());
            index = Integer.parseInt(indexS);
            // код тестирования с выводом в строку не сработает т.к. String.is Empty() не работает с null значениями
            // удобнее для тестирования нижняя строка
            //Arrays.toString(passwordString) выведет массив в строке [j, null, null, hh, null, null, null, null]
            System.out.println(Arrays.toString(passwordString) + ", index = " + index);

        }
        //  записать пароль из массива в строку
        for (String elemets : passwordString) {
            password = password.concat(elemets);
        }
        return password;
    }

    /* Часть 1 */
    /* Получить символ из каждой генерации хэша для пароля для возврата строки пароля */
    // количество повторов равно количеству букв ключа
    private static String getPasswordStr(String key, String password) throws NoSuchAlgorithmException {
        int index = 0;
        /* Получить символ из каждой генерации хэша для пароля для возврата строки пароля */
        // количество повторов равно количеству букв ключа
        for (int i = 0; i < 8; i++) {
            // получить результат работы хэш-фукции над конкатенацией возрастающего индекса и ключа задачи
            String keyhashWithZero = getHashWithZero5(key, index);
            String keyHash = hash(keyhashWithZero);

            // разбить строку на массив символов, выбрать символ после пятого нуля т.е. 5 индекс массива
            char[] character = keyHash.toCharArray();
            password = password.concat(String.valueOf(character[5]));

            // запомнить число индекса из строки  bgvyzdsv1038736
            // начало подстроки это длина ключа, конец это длина конкатенации ключа и индекса сформированного выше
            String indexS = keyhashWithZero.substring(key.length(), keyhashWithZero.length());
            index = Integer.parseInt(indexS);
        }
        return password;
    }

    /*Сформировать хэш с 5 нулями в начале */
    private static String getHashWithZero5(String key, int i) throws NoSuchAlgorithmException {
        // int i = 0;
        boolean no5 = true;
        String keyhashWithZero = new String();
        // исключает повторное применение уже найденного индекса
        if (i > 0) {
            i++;
        }
        while (no5 == true) {
            /* Обрезка конца строки  */
            String keySubstring = key.substring(0, key.length());
            String keyConcat = keySubstring.concat(String.valueOf(i));
            String keyHash = hash(keyConcat);

            if (keyHash.startsWith("00000") == true) {
                keyhashWithZero = keyConcat;
                no5 = false;
            }
            i++;
        }
        return keyhashWithZero;
    }

    /*Получить хэш исходного текста (ключа) */
    private static String hash(String string) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        /* преобразование объекта класса StringBuilder в объект класса String для применения метода getBytes */
        byte[] bytes = md5.digest(string.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            // преобразование в 16 систему
            builder.append(String.format("%02X", b));
        }
        //возврат строки хэш
        return builder.toString();
    }
}
