/*
1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4, 
при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать. 
Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), 
должно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
3. В методе main() вызвать полученный метод, 
обработать возможные исключения MyArraySizeException и MyArrayDataException 
и вывести результат расчета (сумму элементов, при условии что подали на вход корректный массив).
 */

public class Lesson03 {

    public static void main(String[] args) {
        String[][] arr = {
                { "5", "66", "777", "88" },
                { "11", "22", "33", "44" },
                { "7", "8", "9", "9" },
                { "13", "114", "215", "316" }
        };

        try {
            int sum = analyzeArray(arr);
            System.out.println("Массив корректный, сумма его элементов = " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Неверный размер массива (корректный размер 4x4)");
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка ввода данных в ячейке -> (" + e.getRow() + ", " + e.getCol() + ")");
        }
    }

    public static int analyzeArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4 || arr[0].length != 4) {
            throw new MyArraySizeException();
        }

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return sum;
    }
}

class MyArraySizeException extends Exception {
    MyArraySizeException() {
        super();
    }
}

class MyArrayDataException extends Exception {
    private final int row;
    private final int col;

    MyArrayDataException(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}