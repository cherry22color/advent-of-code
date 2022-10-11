package adventOfCode_2015;

public class DAY2_15_1_Dimensions {
    private int length;
    private int width;
    private int height;

    DAY2_15_1_Dimensions(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getFeet() {
        int min = 2 * (height + length) <= 2 * (width + length) ? 2 * (height + length) : 2 * (width + length);
        min = (min <= 2 * (height + width) ? min : 2 * (height + width));
        // длину мин периметра для обвязки и V для бантика
        // что даст всю длину ленты
        return height * length * width + min;
    }

    // Поиск площади коробок
    public int getSquare() {
        // Поиск общей площади
        // Необходимо добавить меньшую сторону
        int square;
        int min = (height * length <= width * length ? height * length : width * length);
        min = (min <= height * width ? min : height * width);
        // Использование формулы для расчета площади упаковки для подарка
        square = 2 * length * width + 2 * width * height + 2 * height * length + min;
        return square;
    }
}