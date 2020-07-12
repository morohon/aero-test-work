package ru.aero.test.verevkin;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Kirill Verevkin
 */
public class Car {

    public static void main(String[] args) throws InterruptedException {
        SpeedSensor ss = new SpeedSensor(0.8636f);

        Random rand = new Random(1);
        int r;

        while (true){

            ss.circle();
            System.out.printf("current speed: %.3f / average speed: %.3f", ss.getCurrentSpeed() , ss.getAverageSpeed());
            System.out.println();
            r = rand.nextInt(10);
            System.out.println("sleep: " + 100 * r);
            Thread.sleep(100 * r);
        }

    }

}
