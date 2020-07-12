package ru.aero.test.verevkin;

/**
 * @author Kirill Verevkin
 */
public class SpeedSensor {


    private static final long TEN_MINUTES_MILLISECONDS = 600000;
    private final float circumference;
    private long beginDate;
    private long circleCounter;
    private long prevTime;
    private double currentSpeed;
    private double averageSpeed;

    /**
     * @param diameter
     *        Диаметр колеса в метрах
     */
    public SpeedSensor(float diameter){
        this.circumference = (float)Math.PI * diameter;
        beginDate = System.currentTimeMillis();
    }

    public void circle() {

        long nowTime = System.currentTimeMillis();

        if (nowTime - beginDate > TEN_MINUTES_MILLISECONDS){
            circleCounter = 0;
            averageSpeed = 0;
            beginDate = nowTime;
        }

        //Для расчета скорости нам нужно знать время, за которое был выполнен полный оборот
        //т.к. датчик только один необходимо опираться на данные одного оборота
        if (prevTime == 0){
            prevTime = nowTime;
            return;
        }

        long spanTime = nowTime - prevTime;

        prevTime = nowTime;

        //переводим в км/ч
        currentSpeed = (spanTime != 0) ? circumference/spanTime*1000*3.6 : 0;
        averageSpeed += currentSpeed;

        //Сохраняем количество оборотов для расчета среднего за 10 минут
        circleCounter++;

    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getAverageSpeed() {
        return (averageSpeed == 0) ? averageSpeed : averageSpeed/circleCounter;
    }
}
