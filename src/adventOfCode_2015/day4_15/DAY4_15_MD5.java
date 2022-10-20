package adventOfCode_2015.day4_15;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//https://adventofcode.com/2015/day/4

/**
 * Санте нужна помощь в добыче нескольких AdventCoins (очень похожих на биткойны ),
 * чтобы использовать их в качестве подарков для всех экономически дальновидных маленьких девочек и мальчиков.
 *
 * Для этого ему нужно найти хеши MD5 , которые в шестнадцатеричном формате
 * начинаются как минимум с пяти нулей . Входными данными для хэша MD5
 * является некоторый секретный ключ (ваш ввод головоломки, приведенный ниже),
 * за которым следует десятичное число. Чтобы добывать AdventCoins,
 * вы должны найти Санта с наименьшим положительным числом
 * (без начальных нулей: 1, 2, 3, ...), которое дает такой хэш.
 *
 * Например:
 *
 * Если ваш секретный ключ abcdef, ответ будет 609043,
 * потому что хэш MD5 abcdef609043начинается с пяти нулей
 * ( 000001dbbfa...), и это наименьшее число для этого.
 *
 * часть 2 - получить с хэш с 6 нулями
 */
public class DAY4_15_MD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {



        String key = "bgvyzdsv";
        String keyhashWithZero = getHashWithZero5(key);
        System.out.println(keyhashWithZero);

    }

    /*Сформировать хэш с 5 нулями в начале */
    private static String getHashWithZero5(String key) throws NoSuchAlgorithmException {
        int i = 0;
        boolean no5 = true;
        String keyhashWithZero = new String();

        while (no5 == true) {

            /* Обрезка конца строки  */
            String keySubstring = key.substring(0, key.length());
            // key.append(i);  key.
            String keyConcat = keySubstring.concat(String.valueOf(i));
            String keyHash = hash(keyConcat);

            if (keyHash.startsWith("00000") == true){  // для 5
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
        //String str = string.toString();

        byte[] bytes = md5.digest(string.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            // преобразование в 16 систему
            builder.append(String.format("%02X", b));
        }
        //System.out.println(builder.toString());
        //возврат строки хэш
        return builder.toString();
    }
}





