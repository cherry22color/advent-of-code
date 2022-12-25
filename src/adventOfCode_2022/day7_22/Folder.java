package adventOfCode_2022.day7_22;

import java.util.ArrayList;

public class Folder {

    public String name;
    public int size;
    // помнить предыдущую папку
    public Folder prevFolder;
    public ArrayList<Folder> folders;
    public ArrayList<File> files;

    public Folder(String name) {
        this.name = name;
        size = 0;
        folders = new ArrayList<Folder>();
        files = new ArrayList<File>();
    }


    // заполнение массива папок с указананием, что заполняется текущая this  (использовать текущий обьект)
    public void addFolder(Folder newFolder) {
        newFolder.prevFolder = this;
        folders.add(newFolder);
    }

    // полчить папку по имени
    public Folder getFolderByName(String searchedName) {
        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).name.equals(searchedName)) {
                return folders.get(i);
            }
        }
        return null; // вернуть исключение thrue exeption
    }

    // аналог calculateTotalSize, с учетом null значений(листов дерева), решение через рекурсивный обход в глубину
    public int sumAll() {

        int sumFile = calculateAllFileSize();
        int sum = prevFolder.size + sumFile;
        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i) != null) {
                sum += folders.get(i).sumAll();
            }
        }
        return sum;
    }

    // подсчет суммы размера файлов в папке
    private int calculateAllFileSize() {
        int sumFile = prevFolder.size; // prevFolder.size; обеспечит заход в корневую папку. при size = 0; она будет проигнорирована,
        //в этом методе выдает обшику с null

        for (int i = 0; i < files.size(); i++) {
            sumFile += files.get(i).size;
        }
        return sumFile;
    }
//    public int calculateTotalSize() {
//        calculateAllFileSize();
//        for (int i = 0; i < folders.size(); i++) {
//            size += folders.get(i).calculateTotalSize();
//        }
//        return size;
//    }
}
