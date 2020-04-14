package com.cars;

public class Main {
    public static final int FINISH_LINE = 2000;
    public static final int STEP = 1;

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
