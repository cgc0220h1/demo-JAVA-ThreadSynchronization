package com.cars;

import java.util.Random;

public class RacingCars implements Runnable {
    private String name;

    public RacingCars(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int runDistance = 0;
        long startTime = System.currentTimeMillis();
        while (runDistance < Main.FINISH_LINE) {
            try {
                int speed = (new Random().nextInt(20));
                runDistance += speed;
                String log = "|";
                int percentTravel = (runDistance * 100) / Main.FINISH_LINE;
                for (int index = 0; index < 100; index += Main.STEP) {
                    if (percentTravel >= index + Main.STEP) {
                        log += "=";
                    } else if (percentTravel >= index && percentTravel < index + Main.STEP) {
                        log += "o";
                    } else {
                        log += "-";
                    }
                }
                log += "|";
                System.out.printf(" %-10s:%s %d Km\n", name, log, Math.min(Main.FINISH_LINE, runDistance));
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println("Car " + name + " broken...");
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Car " + name + " Finish in " + (endTime - startTime));
    }
}
