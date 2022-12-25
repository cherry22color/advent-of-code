package adventOfCode_2022.recursionTree;

public class recursionTree {
    public static void main(String[] args) {
        Tree root =
                new Tree(20, // корень дерева
                        new Tree(7,         // левый потомок,  внутри потомки 3-4 уровня
                                new Tree(4, null, new Tree(6)), new Tree(9)),
                        new Tree(35,         // правый потомок
                                new Tree(31, new Tree(28), null),
                                new Tree(40, new Tree(38), new Tree(52)))); // правый потомок

        System.out.println(root.sumAll());
    }

    static class Tree {
        int value;
        Tree left; // левый потомок
        Tree right; // правый потомок

        public Tree(int value, Tree left, Tree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        // конструктор для описания листов
        public Tree(int value) {
            this.value = value;
        }

        // рекурсивный обход в глубину,
        // вычисление суммы узла и его потомков:
        // прибавляет собственное значение и потомков, эти потомки вызывают эту функцию для своих потомков
        public int sumAll() {
            int sum = value;
            if (left != null) {
                sum += left.sumAll();

            }
            if (right != null) {
                sum += right.sumAll();
            }
            return sum;
        }
    }
}
