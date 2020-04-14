package com.cars;

import java.util.Random;

public class RacingCars implements Runnable {
    public static final int FINISH_LINE = 2000;
    public static final int STEP = 1;
    private String name;

    public RacingCars(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int runDistance = 0;
        long startTime = System.currentTimeMillis();
        while (runDistance < FINISH_LINE) {
            try {
                int speed = (new Random().nextInt(20));
                runDistance += speed;
                String log = "|";
                int percentTravel = (runDistance * 100) / FINISH_LINE;
                for (int index = 0; index < 100; index += STEP) {
                    if (percentTravel >= index + STEP) {
                        log += "=";
                    } else if (percentTravel >= index && percentTravel < index + STEP) {
                        log += "o";
                    } else {
                        log += "-";
                    }
                }
                log += "|";
                System.out.printf(" %-10s:%s %d Km\n", name, log, Math.min(FINISH_LINE, runDistance));
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println("Car " + name + " broken...");
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Car " + name + " Finish in " + (endTime - startTime));
    }

    public static void main(String[] args) {
        RacingCars ferrari = new RacingCars("Ferrari");
        RacingCars wave = new RacingCars("Wave Tàu");
        RacingCars dream = new RacingCars("Dream Thái");

        Thread car1 = new Thread(ferrari);
        Thread car2 = new Thread(wave);
        Thread car3 = new Thread(dream);

        System.out.println("Đích: " + FINISH_LINE + " Km");
        car1.start();
        car2.start();
        car3.start();
    }
}
