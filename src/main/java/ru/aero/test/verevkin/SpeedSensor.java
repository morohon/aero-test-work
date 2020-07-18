package ru.aero.test.verevkin;

import java.util.*;

/**
 * @author Kirill Verevkin
 */
public class SpeedSensor {


    private static final long TEN_MINUTES_MILLISECONDS = 600000;
    private final float circumference;
    private long prevTime;
    private double currentSpeed;
    private final Set<SpeedMeasure> speedData;

    /**
     * @param diameter
     *        Диаметр колеса в метрах
     */
    public SpeedSensor(float diameter){
        this.circumference = (float)Math.PI * diameter;
        speedData = new HashSet<>();
    }

    public void circle() {

        long nowTime = System.currentTimeMillis();

        //Для расчета скорости нам нужно знать время, за которое был выполнен полный оборот
        //т.к. датчик только один необходимо опираться на данные одного оборота
        if (prevTime == 0){
            prevTime = nowTime;
            return;
        }

        long spanTime = nowTime - prevTime;

        prevTime = nowTime;

        //Расчитываем скорость и переводим в км/ч
        currentSpeed = (spanTime != 0) ? circumference/spanTime*1000*3.6 : 0;
        speedData.add(new SpeedMeasure(currentSpeed, nowTime));
        deleteOldSpeedMeasure(nowTime);
    }

    private void deleteOldSpeedMeasure(long nowTime) {
        speedData.removeIf(sm -> nowTime - sm.timestamp > TEN_MINUTES_MILLISECONDS);
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getAverageSpeed() {
        return (speedData.size() == 0) ? 0 : speedData.stream().mapToDouble(sm -> sm.speed).sum() / speedData.size();
    }

    static class SpeedMeasure {
        private final double speed;
        private final long timestamp;

        public SpeedMeasure(double speed, long timestamp) {
            this.speed = speed;
            this.timestamp = timestamp;
        }
    }
}
