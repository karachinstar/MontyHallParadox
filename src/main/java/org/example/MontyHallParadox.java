package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Урок 6. Управление проектом: сборщики проектов
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла
 * (Парадокс Монти Холла — Википедия) и наглядно убедиться в верности парадокса
 * (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<шаг теста, результат>;
 * Вывести на экран статистику по победам и поражениям.
 */


public class MontyHallParadox {

    static Random random;
    static Map<Integer, Boolean> staticNotChains;       // Статистика для игрока, не меняющего свой выбор.
    static Map<Integer, Boolean> staticChains;       // Статистика для игрока, изменяющего свой выбор.
    static int doorsNub;                      // Количество дверей.
    static int attempts;                         // Количество попыток.

    public static void main(String[] args) {
        random = new Random();
        staticNotChains = new HashMap<>();
        staticChains = new HashMap<>();
        doorsNub = 3;
        attempts = 1000;

        for (int i = 0; i < attempts; i++) {     // Розыгрыш (1000 попыток).
            trial(i);
        }

        int win = 0;                             // Статистика для первого игрока, не меняющего свой выбор.
        for (Map.Entry<Integer, Boolean> entry: staticNotChains.entrySet()){
            if (entry.getValue()){
                win++;
            }
        }
        System.out.println("\nПарадокс Монти Холла");
        System.out.println("\nСтатистика выигрышей для игрока, не меняющего свой выбор:");
        System.out.println("Количество побед: " + win + " раз из " + attempts + " попыток.");

        win = 0;                                  // Статистика для второго игрока, изменяющего свой выбор.
        for (Map.Entry<Integer, Boolean> entry: staticChains.entrySet()){
            if (entry.getValue()){
                win++;
            }
        }
        System.out.println("\nСтатистика выигрышей для игрока, изменяющего свой выбор:");
        System.out.println("Количество побед: " + win + " раз из " + attempts + " попыток.");
    }


    private static void trial(int numRound) {
        int success = random.nextInt(doorsNub);
        int firstChoice = random.nextInt(doorsNub);
        int freeOpenDoor = -1;
        int secondChoice = -1;

        for (int i = 0; i < doorsNub; i++) {
            if (i != success && i != firstChoice){
                freeOpenDoor = i;
            }
        }



        for (int i = 0; i < doorsNub; i++) {            // Игрок не изменяет свой выбор.
            if (i != freeOpenDoor && i != firstChoice){
                secondChoice = i;
            }
        }
        staticChains.put(numRound, success == secondChoice);   // Статистика для второго игрока.

        for (int i = 0; i < doorsNub; i++) {            // Игрок не изменяет свой выбор.
            if (i != freeOpenDoor && i != firstChoice){
                secondChoice = firstChoice;
            }
        }
        staticNotChains.put(numRound, success == secondChoice);   // Статистика для первого игрока.
    }
}