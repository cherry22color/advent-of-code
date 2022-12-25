package adventOfCode_2022.folders;

import java.util.ArrayList;

public class Folder {
   public String name;
   public int size;
   public ArrayList<Folder> folders;
   public ArrayList<File> files;

   public Folder (String name){
       this.name = name;
       size = 0;
       folders = new ArrayList<Folder>();
       files = new ArrayList<File>();
    }

    // сложение файлов в папке
    public void calculateAllSize(){
       size = 0;

       for(int i = 0; i < files.size(); i++){
           size += files.get(i).size; // как складывается с 0 ?
       }
    }

    public int calculateTotalSize(){
       // не хватает if(0 или 1) то return 1, иначе код
       calculateAllSize();
       for(int i = 0; i < folders.size(); i++){
           size += folders.get(i).calculateTotalSize();
       }
       return size;
    }

}
