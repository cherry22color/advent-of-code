package commonFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {

    private String name;

    public ReadFile(String name) {
        this.name = name;
    }

    /*Чтение информации с файла встроку */
    public String readFile() throws FileNotFoundException {
        String stringFloor = new String();
        FileReader file = new FileReader(name);
        Scanner fileScanner = new Scanner(file);  // окрываем файл для чтения

        while (fileScanner.hasNext()) {
            stringFloor = fileScanner.nextLine();
        }
        fileScanner.close();// закрываем файл
        return stringFloor;
    }

    /* чтение информации с файла в массив */
    public String[] readFileArray() throws FileNotFoundException{
        FileReader file = new FileReader(name);
        // scanner считывает содержимое файла и делит все на части (строки т.е. токены)
        // \n символ разделитель для токенов
        Scanner fileScanner = new Scanner(file);

        // Коллекция для сохранения массива строк
        ArrayList<String> arrayList = new ArrayList<String>();

        // Запись строк в коллекцию
         while (fileScanner.hasNext()){
             arrayList.add(fileScanner.nextLine());
         }
         fileScanner.close();
         return  arrayList.toArray(new String[arrayList.size()]);
    }

    /* чтение информации с файла в массив */
    public ArrayList readFileArrayList() throws FileNotFoundException{
        FileReader file = new FileReader(name);
        // scanner считывает содержимое файла и делит все на части (строки т.е. токены)
        // \n символ разделитель для токенов
        Scanner fileScanner = new Scanner(file);

        // Коллекция для сохранения массива строк
        ArrayList<String> arrayList = new ArrayList<String>();

        // Запись строк в коллекцию
        while (fileScanner.hasNext()){
            arrayList.add(fileScanner.nextLine());
        }
        fileScanner.close();
        return  arrayList ;
    }

//
//    /* чтение информации с файла в массив */
//    public Integer[] readFileArrayInt() throws FileNotFoundException{
//        FileReader file = new FileReader(name);
//        // scanner считывает содержимое файла и делит все на части (строки т.е. токены)
//        // \n символ разделитель для токенов
//        Scanner fileScanner = new Scanner(file);
//
//        // Коллекция для сохранения массива строк
//        ArrayList<Integer> arrayList = new ArrayList<Integer>();
//
//        // Запись строк в коллекцию
//        while (fileScanner.hasNext()){
//            arrayList.add(Integer.valueOf(fileScanner.nextLine()));
//        }
//        fileScanner.close();
//        return  arrayList.toArray(new Integer[arrayList.size()]);
//    }

}
