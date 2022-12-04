package adventOfCode_2022.day2_22;

import commonFile.ReadFile;

import java.io.FileNotFoundException;
// https://adventofcode.com/2022/day/2

/*
 * A Y
 * B X
 * C Z
 * -- часть 1--
 * «Первая колонка — это то, во что собирается играть ваш противник:
 * Aза Камень, Bза Бумагу и Cза Ножницы. Вторая колонка…»
 * Вторая колонка, по вашему мнению , должна быть тем,
 * что вы должны сыграть в ответ: Xза Камень, Yза Бумагу и Zза Ножницы.
 * <p>
 * Победителем всего турнира становится игрок с наибольшим количеством очков.
 * Ваш общий балл представляет собой сумму ваших баллов за каждый раунд.
 * Очки за один раунд — это очки за выбранную вами фигуру (1 за Камень,
 * 2 за Бумагу и 3 за Ножницы) плюс очки за исход раунда (0, если вы проиграли,
 * 3, если раунд закончился вничью ). , и 6, если вы выиграли).
 * <p>
 * -- часть 2--
 * во второй колонке написано, как должен закончиться раунд:
 * Xзначит, нужно проиграть, Yзначит нужно закончить раунд вничью, а Zзначит нужно выиграть.
 * В первом раунде ваш противник выберет «Камень» ( A), и вам нужно,
 * чтобы раунд закончился вничью ( Y), поэтому вы также выбираете
 * «Камень». Это дает вам оценку 1 + 3 = 4 .
 * Во втором раунде ваш противник выберет Бумагу ( B),
 * а вы выберете Камень, поэтому вы проиграете ( X) со счетом 1 + 0 = 1 .
 * В третьем раунде вы победите ножницы противника с помощью камня и наберете 1 + 6 = 7 очков .
 */

public class DAY2_22 {
    public static void main(String[] args) throws FileNotFoundException {
        String name = "DAY2_22";
        ReadFile readFile = new ReadFile(name);
        String[] mixStep = readFile.readFileArray();

        // часть 1
        int sumPoint = getSumPointPart1(mixStep);
        System.out.println("Part 1 = " + sumPoint);

        // часть 2
        sumPoint = getSumPointPart2(mixStep);
        System.out.println("Part 2 = " + sumPoint);
    }

    // часть 2
    private static int getSumPointPart2(String[] mixStep) {
        int sumPoint;
        int size = mixStep.length;
        sumPoint = 0;
        for (int step = 0; step < size; step++) {
            // разбить на подстроки
            String[] players = mixStep[step].split(" ");
            /*
             *  x проиграть = 0
             *  y ничья += 3
             *  z выиграть += 6
             */
            if (players[1].equals("X")) {
                sumPoint += 0;
            } else if (players[1].equals("Y")) {
                sumPoint += 3;
            } else if (players[1].equals("Z")) {
                sumPoint += 6;
            }
            // выбрать элемент в зависимости от результата исхода игры

            if ((players[0].equals("A") && players[1].equals("Y")) ||
                    ((players[0].equals("C") && players[1].equals("Z"))) ||
                    (players[0].equals("B") && players[1].equals("X"))) {
                // ничья и выбрать камень
                // выиграть против ножниц
                // приграть против бумаги
                sumPoint += 1;
            } else if ((players[0].equals("B") && players[1].equals("Y")) ||
                    ((players[0].equals("A") && players[1].equals("Z"))) ||
                    (players[0].equals("C") && players[1].equals("X"))) {
                // ничья и выбрать бумагу
                // выиграть против камня
                // проиграть против ножниц
                sumPoint += 2;
            } else if ((players[0].equals("C") && players[1].equals("Y")) ||
                    ((players[0].equals("B") && players[1].equals("Z"))) ||
                    (players[0].equals("A") && players[1].equals("X"))) {
                // ничья и выбрать ножницы
                // выиграть против бумаги
                // проиграть против камня
                sumPoint += 3;
            }
        }
        return sumPoint;
    }

    // часть 1
    private static int getSumPointPart1(String[] mixStep) {

        int size = mixStep.length;
        int sumPoint = 0;
        for (int step = 0; step < size; step++) {
            // разбить на подстроки
            String[] players = mixStep[step].split(" ");
            // присвоить баллы за выбор
            if (players[1].equals("X")) {
                sumPoint += 1;
            } else if (players[1].equals("Y")) {
                sumPoint += 2;
            } else {
                sumPoint += 3;
            }

            // заменить  A B C  на X Y Z
            if (players[0].equals("A")) {
                players[0] = "X";
            } else if (players[0].equals("B")) {
                players[0] = "Y";
            } else if (players[0].equals("C")) {
                players[0] = "Z";
            }
            // присвоить баллы за исход игры
            /* проигрышь
             *  a x - z
             *  c z - y
             *  b y - x
             */
            if (players[0].equals(players[1])) {
                sumPoint += 3;
            } else if ((players[0].equals("X") && players[1].equals("Z")) ||
                    (players[0].equals("Z") && players[1].equals("Y")) ||
                    (players[0].equals("Y") && players[1].equals("X"))) {
                sumPoint += 0;
            } else {
                sumPoint += 6;
            }
        }
        return sumPoint;
    }
}