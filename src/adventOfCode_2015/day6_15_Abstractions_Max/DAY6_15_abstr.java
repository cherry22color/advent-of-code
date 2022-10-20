package adventOfCode_2015.day6_15_Abstractions_Max;

// https://adventofcode.com/2015/day/6
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DAY6_15_abstr {
    public static void main(String[] args) throws FileNotFoundException {
        String[] instructions = readFile("DAY6_15");

        // BlinkLamp - первая часть, CapacitorLamp - вторая часть
        Lamp[][] lampsArray = new Lamp[1000][1000];

        // Инициализация всех элементов массива lampsArray, т.к. они равны null.
        for (int row = 0; row < 1000; row++) {
            for (int col = 0; col < 1000; col++) {
                // BlinkLamp - первая часть, CapacitorLamp - вторая часть
                lampsArray[row][col] = new BlinkLamp();
            }
        }

        for (int rowString = 0; rowString < instructions.length; rowString++) {
            // считать текущую инструкцию
            String instruction = instructions[rowString];
            // массив для хранения 4 чисел из строки
            int[] digitArr = new int[4];
            String digitSplit = " ";

            LampCommand lampCommand;
            if (instruction.contains("turn on")) {
                lampCommand = LampCommand.TurnOn;
                getDigitArray(instruction, digitArr, "turn on ");
            } else if (instruction.contains("turn off")) {
                lampCommand = LampCommand.TurnOff;
                getDigitArray(instruction, digitArr, "turn off ");
            } else {
                lampCommand = LampCommand.Toggle;
                getDigitArray(instruction, digitArr, "toggle ");
            }

            for (int row = digitArr[0]; row <= digitArr[2]; row++) {
                for (int col = digitArr[1]; col <= digitArr[3]; col++) {
                    lampsArray[row][col].ExecuteCommand(lampCommand);
                }
            }
        }

        // подсчет количества включенных ламп
        int countLampOn = 0;
        countLampOn = getCountLampOn(lampsArray);
        System.out.println("Count lamp " + countLampOn);
    }



    /* Подсчет количества включенных ламп */
    private static int getCountLampOn(Lamp[][] lampsArray) {
        int countLampOn = 0;
        for (Lamp[] row : lampsArray) {
            // аналогично countLampOn += Arrays.stream(row).sum(); (Java 8 Stream API), преобразует массыив в поток чисел
            for (Lamp col : row) {
                countLampOn += col.lightValue;
            }
            // Присвоить элементам подмассива значения 0
            Arrays.fill(row, 0);
        }
        return countLampOn;
    }

    /* Получить массив 4 чисел*/
    private static void getDigitArray(String instruction, int[] digitArr, String s) {
        String digitSplit;
        digitSplit = instruction.replace(s, "");
        String[] digitString = digitSplit.split(",");
        // записать строковое число в числовой тип данных
        for (int i = 0; i < digitString.length; i++) {
            digitArr[i] = Integer.parseInt(digitString[i]);
        }
    }

    private static String[] readFile(String fileName) throws FileNotFoundException {
        FileReader file = new FileReader(fileName);
        //  scanner  считывает содержимое файла и делит всё на части (строки т.е. токены)
        // \n символ разделитель для токенов
        Scanner fileScanner = new Scanner(file);

        //коллекции
        ArrayList<String> lines = new ArrayList<String>();

        while (fileScanner.hasNext()) {
            /* Заменяет ненужное слово through на пустоту */
            String line = fileScanner.nextLine().replace(" through ", ",");
            lines.add(line);
        }
        fileScanner.close();

        return lines.toArray(new String[lines.size()]);
    }
}
