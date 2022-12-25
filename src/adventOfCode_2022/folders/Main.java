package adventOfCode_2022.folders;

public class Main {

    public static void main(String[] args) {
        Folder bookFolder = new Folder("books");
        File moemBook = new File("moem.txt", 10000);
        File gugoBook = new File("gugo.txt", 5000);
        bookFolder.files.add(moemBook);
        bookFolder.files.add(gugoBook);

        Folder photoFolder = new Folder("photos");
        File moemPhoto = new File("photo_moem.png", 512);
        File gugoPhoto = new File("photo_gugo.png", 256);
        photoFolder.files.add(moemPhoto);
        photoFolder.files.add(gugoPhoto);

        Folder audioBookFolder = new Folder("audio");
        File audioBookInstructionFile = new File("audio_book_instruction.mp3", 12345);
        audioBookFolder.files.add(audioBookInstructionFile);
        Folder importantYearsFolder = new Folder("important_years");
        audioBookFolder.folders.add(importantYearsFolder);

        File importantYearsAudioPart1 = new File("important_years_part1.mp3", 10000);
        File importantYearsAudioPart2 = new File("important_years_part2.mp3", 10000);
        File importantYearsAudioPart3 = new File("important_years_part3.mp3", 10000);
        importantYearsFolder.files.add(importantYearsAudioPart1);
        importantYearsFolder.files.add(importantYearsAudioPart2);
        importantYearsFolder.files.add(importantYearsAudioPart3);

        bookFolder.folders.add(photoFolder);
        bookFolder.folders.add(audioBookFolder);

        bookFolder.calculateTotalSize();

        System.out.println("book folder size = " + bookFolder.size);
        System.out.println("photo folder size = " + photoFolder.size);
        System.out.println("audio book folder size = " + audioBookFolder.size);
        System.out.println("important years folder size = " + importantYearsFolder.size);
    }
}

