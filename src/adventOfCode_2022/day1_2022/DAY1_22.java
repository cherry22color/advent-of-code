package adventOfCode_2022.day1_2022;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DAY1_22 {
    public static void main(String[] args) throws FileNotFoundException {
        String nameFile = "DAY1_22.txt";
        String[] allCalories = readFile(nameFile);
        //boolean part = true;
        ArrayList<Integer> sortAllCalories = new ArrayList<Integer>();

        // часть 1
        int maxCalories = getMaxСalories(allCalories, sortAllCalories); // значение максимального числа калорий
        System.out.println("maximum value for one elf " + maxCalories);

        // часть 2
        // найти сумму трех максимумов
        Collections.sort(sortAllCalories);
        Collections.reverse(sortAllCalories);
        int sumCalories = sortAllCalories.get(0) + sortAllCalories.get(1) + sortAllCalories.get(2);
        System.out.println( "the total calories of 3 elves " + sumCalories);

    }

    private static int getMaxСalories(String[] allCalories, ArrayList<Integer> sortAllCalories) {
        int maxCalories = 0;
        int countCaliries = 0;
        int size = allCalories.length;
        for (int indexElfe = 0; indexElfe < size; indexElfe++) {
            if (allCalories[indexElfe] != "") {
                countCaliries += Integer.parseInt(allCalories[indexElfe]);
            } else {
                if (maxCalories < countCaliries) {
                    maxCalories = countCaliries;
                }
                sortAllCalories.add(countCaliries);
                countCaliries = 0;
            }
        }
        return maxCalories;
    }


    // чтение с файла
    private static String[] readFile(String nameFile) throws FileNotFoundException {
        FileReader file = new FileReader(nameFile);

        Scanner fileScanner = new Scanner(file);
        ArrayList<String> lines = new ArrayList<String>();
        while (fileScanner.hasNext()) {
            lines.add(fileScanner.nextLine());
        }
        fileScanner.close();
        return lines.toArray(new String[lines.size()]);
    }
}



