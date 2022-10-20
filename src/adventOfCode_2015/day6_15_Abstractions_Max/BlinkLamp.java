package adventOfCode_2015.day6_15_Abstractions_Max;

// Лампа в первой части задачи
public class BlinkLamp extends Lamp {

    public BlinkLamp()
    {

    }

    @Override
    void TurnOn() {
        lightValue = 1;
    }

    @Override
    void TurnOff() {
        lightValue = 0;
    }

    @Override
    void Toggle() {
        lightValue = (lightValue == 1) ? 0 : 1;
    }
}
