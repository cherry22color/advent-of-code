package adventOfCode_2016.day3_16_willAddClass;

public class Triangle {
    private int countTriangle = 0;

    public void setCountTriangle(int countTriangle) {
        this.countTriangle = countTriangle;
    }

    // часть 1
    public void makeTriangle(String[] sideArray) {
        // считать содержимое ячейки массива как массив чисел
        for (int i = 0; i < sideArray.length; i++) {
            // разбиение строки на массив 3 строк, с разделителем в один пробел
            String[] arrayString = sideArray[i].split(" ");
            // если в массиве остались пробелы, то их необходимо удалить
            // строка содержит 15 символов
            String[] sideString = deleteSpace(arrayString);
            getDigitArr(sideString);
        }
    }

    /* Часть 2 */
    // Каждый набор из трех чисел в столбце определяет треугольник
    public void makeTriangleVertically(String[] sideArray) {
        // массивы для 3 треугольников
        String[] thriangle_1 = new String[3];
        String[] thriangle_2 = new String[3];
        String[] thriangle_3 = new String[3];
        // считать содержимое ячейки массива как массив чисел
        int j = 0;  // обозначение индекса записи в каждые три массива
        for (int i = 0; i < sideArray.length; i++) {
            // разбиение строки на массив 3 строк, с разделителем в один пробел
            String[] arrayString = sideArray[i].split(" ");
            // если в массиве остались пробелы, то их необходимо удалить
            // строка содержит 15 символов
            String[] sideString = deleteSpace(arrayString);

            thriangle_1[j] = sideString[0];
            thriangle_2[j] = sideString[1];
            thriangle_3[j] = sideString[2];
             if (j == 2){
                 // преобразование в числа и проверка на соотвествие треугольнику
                 getDigitArr(thriangle_1);
                 getDigitArr(thriangle_2);
                 getDigitArr(thriangle_3);
//                 for (int a = 0; a < 3; a++){
//                     // присвоение 0 значений в массив
//                     thriangle_1[a] = String.valueOf(0);
//                     thriangle_2[a] = String.valueOf(0);
//                     thriangle_3[a] = String.valueOf(0);
//                 }
                 j = 0;
             } else{
                 j++;
             }
        }
    }

    // сохранить числа из строк в разные переменные и посчитать треугольники
    private void getDigitArr(String[] sideString) {
        int a = Integer.parseInt(sideString[0]);
        int b = Integer.parseInt(sideString[1]);
        int c = Integer.parseInt(sideString[2]);
        checkIfValid(a, b, c);
    }

    // удалить из массива пробел
    private String[] deleteSpace(String[] arrayString) {
        String[] sideString = new String[3];
        // проверить массив строк на наличие в элементы пробела
        int i = 0;
        for(String item : arrayString){
            if(item != ""){
                sideString[i] = item;
                i++;
            }
        }
        return sideString;
    }

    // проверить является ли тройка чисел сторонами треугольника
    private void checkIfValid(int a, int b, int c) {
        if ((a + b) > c && (b + c) > a && (c + a) > b) {
            this.countTriangle++;
        }
    }

    // получить количество треугольников
    public int getCountTriangle() {
        return countTriangle;
    }
}
