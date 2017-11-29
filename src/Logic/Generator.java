package Logic;

import java.awt.*;
import java.util.Random;

/**
 * Клас в якому відбувається генерування таблиці з комірками
 * і надання коміркам певних властивостей.
 */

public class Generator {
    /**
     * Генерує готову таблицю
     * @param cells таблиця комірок
     * @param size розміри таблиці
     * @param level рівень гри (концентрація бомб в таблиці)
     * @return повертає сформовану таблицю
     */
    public static Cell[][] generate(Cell[][] cells , Dimension size, int level){
        /*Заповнення масиву унікальних випадкових чисел*/
        int [] randomCells = getRandomArray(size.width*size.height , size.width*size.height*level/10);
        for (int i = 0; i <size.height ; i++) {
            for (int j = 0; j <size.width ; j++) {
                /*Створення обєкту комірки*/
                cells[i][j]= new OriginCell(j,i);
                /*Дії ,якщо порядковий номер комірки співпадає з якимось числом із масиву*/
                if(contain(randomCells , i*size.width+j)){
                    /*Надання комірці властивості з бомбою*/
                    cells[i][j].setDanger();
                }
            }
        }
        return cells;
    }

    /**
     * Формує масив випадкових чисел кількістю need від 0 до all для розміщення бомб
     * @param all максимальне випадкове число
     * @param need кількість необхідних випадкових чисел
     * @return повертає масив випадкових чисел
     */
     private static int[] getRandomArray(int all , int need){
         /*Створення масиву чисел за необхідним обємом*/
        int[] neededArray = new int[need];
        /*Створення обєкта класу рандом для подальшого генерування цілих чисел*/
        Random random = new Random();
        /*прапорець виходу з циклу у випадку якщо число вже існує в масиві*/
        boolean flag = true;
        /*тимчасове випадкове число*/
        int temporary;
        /*цикл запису унікалььних випадкових чисел в масив*/
         for (int i = 0; i <need ; i++) {
             /*цикл перевірки випадкового числа на присутність його у масиві*/
             do{
                 /*генерування випадкового числа*/
                 temporary = Math.abs(random.nextInt())%all;
                 /*якщо число не міститься в масиві то виходимо з циклу*/
                 if(!contain(neededArray , temporary)){
                     flag = false;
                 }
             }while(flag);
             /*записуємо число в масив*/
             neededArray[i] = temporary;
             /*ставимо прапорець в початковий стан*/
             flag = true;
         }
         /*повертаємо масив унікальних випадкових чисел*/
        return neededArray;
    }

    /**
     * Перевіряє присутність числа в масиві
     * @param randomCells масив випадкових чисел
     * @param number число яке треба перевірити
     * @return повертає true , якщо число міститься в масиві, в іншому випадку повертає false
     */
    private static boolean contain(int [] randomCells ,int number){
        /*прапорець перевірки числа в масиві*/
        boolean check = false;
        /*цикл перевірки числа в масиві*/
        for (int i: randomCells) {
            /*Дії , якщо число міститься в масиві*/
            if(i==number){
                /*ставимо прапорець присутності в true*/
                check = true;
                /*виходимо з циклу*/
                break;
            }
        }
        /*повертаємо прапорець*/
        return check;
    }
}
