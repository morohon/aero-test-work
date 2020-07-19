package ru.aero.test.verevkin;

/**
 * @author Kirill Verevkin
 */
public class Car {

    public static void main(String[] args) {

        Thread[] threads = new Thread[4];

        for(int i = 0; i<=5;i++){
            threads[i] = new Thread(() -> {
                SpeedSensor ss = new SpeedSensor(0.8636f);
                while (true) {
                    ss.circle();
                    System.out.printf(Thread.currentThread().getName()
                            + ": current speed: %.3f / average speed: %.3f\n", ss.getCurrentSpeed(), ss.getAverageSpeed());
                }
            });
            threads[i].start();
        }
    }

}
