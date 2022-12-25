package adventOfCode_2022.day7_22;
// https://adventofcode.com/2022/day/7

import commonFile.ReadFile;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //  пример работы
        // dir books (58113 kb)
        //    - moem.txt (10000 kb)
        //    - gugo.txt (5000 kb)
        //    dir photos (768 kb)
        //       - photo_moem.png (512 kb)
        //       - photo_gugo.png (256 kb)
        //    dir audio (42345 kb)
        //       - audio_book_instruction.mp3 (12345 kb)
        //       dir important_years (30000 kb)
        //          - important_years_part1.mp3 (10000 kb)
        //          - important_years_part2.mp3 (10000 kb)
        //          - important_years_part3.mp3 (10000 kb)

        String name = "test";
        ReadFile input = new ReadFile("test");
        String[] lines = input.readFileArray();
        int sizeArrLine = lines.length;
        // основная папка-диск
        Folder currentFolder = new Folder("rootFolder");
        Folder rootFolder = currentFolder;
        for (int numLine = 1; numLine < sizeArrLine; numLine++) { // в 0 строке содержится /, встречается один раз
            String[] splitLine = lines[numLine].split(" ");
             // если $ то cd или ls   dir
            if ( splitLine[0].equals("$")) {

                if (splitLine[1].equals("cd") ) {
                    // должны сохранить информацию о предыдущей папке(родительской)
                    // .. или имя папки или переместится на уровегнь выше(..)
                    //
                    //  узнать существует ли папка в текущем каталоге
                    if (splitLine[2].equals("..")) {
                        // вызвать предыдующую папку-объект
                        currentFolder = currentFolder.prevFolder;
                    } else {
                        // обратиться к папке по имени
                        currentFolder = currentFolder.getFolderByName(splitLine[2]);
                    }
                }
                if (splitLine[1].equals("ls")) {
                    // перейти на строку вниз
                    while (numLine != sizeArrLine-1) { // делать пока numLine не стал последней строкой
                        // проверка на наличие след строки
                        numLine++; //
                        String[] subSplitLine = lines[numLine].split(" ");
                        if (subSplitLine[0].equals("$")) {
                            numLine--; // тк в основном цикле работает ++
                            break;
                        }
                        // работа с последуюими строками
                        // если первая цифра, то это файл
                        if (!(subSplitLine[0].equals("dir"))) { // если сейчес НЕ папка
                            int sizeFile = Integer.parseInt(subSplitLine[0]);
                            String nameFile = subSplitLine[1];
                            currentFolder.files.add(new File(nameFile, sizeFile));
                        } else {
                            // работа с папкой, текущая папка указана в классе в методе AddFolder
                            currentFolder.addFolder(new Folder(subSplitLine[1]));
                        }
                    }
                }
            }
        }

        int c = rootFolder.size;
//        rootFolder.calculateTotalSize();
//        System.out.println("book folder size = " + currentFolder.size);
        System.out.println(currentFolder.sumAll());
    }
}
