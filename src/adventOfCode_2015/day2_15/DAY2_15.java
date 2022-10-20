package adventOfCode_2015.day2_15;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * У эльфов заканчивается оберточная бумага, поэтому им нужно отправить
 * заказ на пополнение. У них есть список размеров (длина l, ширина w, и высота h)
 * каждого подарка, и они хотят заказать ровно столько, сколько им нужно.
 *
 * К счастью, каждый подарок представляет собой коробку
 * (идеальную прямоугольную призму ), что немного упрощает
 * вычисление требуемой оберточной бумаги для каждого подарка:
 * найдите площадь поверхности коробки, которая равна 2*l*w + 2*w*h + 2*h*l.
 * Эльфам также нужно немного дополнительной
 * бумаги для каждого подарка: площадь наименьшей стороны.
 *
 * Сколько всего квадратных футов оберточной бумаги они должны заказать?
 *
 * У эльфов тоже заканчивается лента. Лента имеет одинаковую ширину,
 * поэтому им нужно беспокоиться только о длине, которую они должны заказать,
 * и они хотели бы, чтобы она была точной.
 *
 * Лента, необходимая для обертывания подарка, — это кратчайшее расстояние
 * вокруг его сторон или наименьший периметр любой стороны.
 * К каждому подарку также требуется бант из ленты; футы ленты,
 * необходимые для идеального лука, равны кубическим футам объема настоящего.
 *
 * Для подарка с размерами 2x3x4 требуются 2+2+3+3 = 10 футы ленты,
 * чтобы обернуть подарок, плюс 2*3*4 = 24 футы ленты для банта, всего 34футов.
 *
 * Сколько всего футов ленты они должны заказать?
 */
public class DAY2_15 {
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


            // Суммирование площадей
            totalSquare += getSquare(length, width, height);
            // Решение для части 2
            // общая длина
            totalFeet += getFeet(length, width, height);


        }

        System.out.println("Total square " + totalSquare + " feet wrapping paper.");
        System.out.println("Oder " + totalFeet + " feet ribbon.");
    }

    private static int getFeet(int length, int width, int height) {
        int min = 2 * (height + length) <= 2 * (width + length) ? 2 * (height + length) : 2 * (width + length);
        min = (min <= 2 * (height + width) ? min : 2 * (height + width));
        // длину мин периметра для обвязки и V для бантика
        // что даст всю длину ленты
        return height * length * width + min;
    }

    // Поиск площади коробок
    private static int getSquare(int length, int width, int height) {
        // Поиск общей площади
        // Необходимо добавить меньшую сторону
        int square;
        int min = (height * length <= width * length ? height * length : width * length);
        min = (min <= height * width ? min : height * width);
        // Использование формулы для расчета площади упаковки для подарка
        square = 2 * length * width + 2 * width * height + 2 * height * length + min;
        return square;
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

