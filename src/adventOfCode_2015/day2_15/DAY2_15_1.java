package adventOfCode_2015.day2_15;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * У эльфов заканчивается оберточная бумага, поэтому им нужно отправить
 * заказ на пополнение. У них есть список размеров (длина l, ширина w, и высота h)
 * каждого подарка, и они хотят заказать ровно столько, сколько им нужно.
 * <p>
 * К счастью, каждый подарок представляет собой коробку
 * (идеальную прямоугольную призму ), что немного упрощает
 * вычисление требуемой оберточной бумаги для каждого подарка:
 * найдите площадь поверхности коробки, которая равна 2*l*w + 2*w*h + 2*h*l.
 * Эльфам также нужно немного дополнительной
 * бумаги для каждого подарка: площадь наименьшей стороны.
 * <p>
 * Сколько всего квадратных футов оберточной бумаги они должны заказать?
 * <p>
 * У эльфов тоже заканчивается лента. Лента имеет одинаковую ширину,
 * поэтому им нужно беспокоиться только о длине, которую они должны заказать,
 * и они хотели бы, чтобы она была точной.
 * <p>
 * Лента, необходимая для обертывания подарка, — это кратчайшее расстояние
 * вокруг его сторон или наименьший периметр любой стороны.
 * К каждому подарку также требуется бант из ленты; футы ленты,
 * необходимые для идеального лука, равны кубическим футам объема настоящего.
 * <p>
 * Для подарка с размерами 2x3x4 требуются 2+2+3+3 = 10 футы ленты,
 * чтобы обернуть подарок, плюс 2*3*4 = 24 футы ленты для банта, всего 34футов.
 * <p>
 * Сколько всего футов ленты они должны заказать?
 */
public class DAY2_15_1 {
    public static void main(String[] args) throws FileNotFoundException {
        String[] lines = readFile();
        int totalSquare = 0;
        int totalFeet = 0;


        // считать до символа х
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            // Делим текущую строку размеров на части
            // Символ-разделитель - 'x'
            // 20x3x11 делится на три элемента - 20 3 11
            String[] lineParts = line.split("x");

            // Integer.parseInt - преобразует строку (аргумент) в целое число
            // Получили длину, ширину, длину

            int length = Integer.parseInt(lineParts[0]);
            int width = Integer.parseInt(lineParts[1]);
            int height = Integer.parseInt(lineParts[2]);

            DAY2_15_1_Dimensions dimensions = new DAY2_15_1_Dimensions(length, width, height);

            // Суммирование площадей
            totalSquare += dimensions.getSquare();
            // Решение для части 2
            // общая длина
            totalFeet += dimensions.getFeet();


        }

        System.out.println("Total square " + totalSquare + " feet wrapping paper.");
        System.out.println("Oder " + totalFeet + " feet ribbon.");
    }


    /* чтение информации с файла */
    private static String[] readFile() throws FileNotFoundException {
        FileReader file = new FileReader("DAY2_15.txt");

        // Scanner считывает все содержимое файла и делит всё   на части (строки-токены)
        // Символ-разделитель на токены - это переход на следующую сторку (\n)
        Scanner fileScanner = new Scanner(file);  // окрываем файл для чтения

        ArrayList<String> lines = new ArrayList<String>();

        while (fileScanner.hasNext()) {
            lines.add(fileScanner.nextLine());
        }
        fileScanner.close();// закрываем файл
        return lines.toArray(new String[lines.size()]);
    }
}

