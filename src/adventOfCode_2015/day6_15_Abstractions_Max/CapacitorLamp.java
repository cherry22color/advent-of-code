package adventOfCode_2015.day6_15_Abstractions_Max;

// Лампа во второй части задачи
public class CapacitorLamp extends Lamp {

    public CapacitorLamp()
    {

    }

    @Override
    void TurnOn() {
        lightValue++;
    }

    @Override
    void TurnOff() {
        lightValue -= (lightValue == 0) ? 0 : 1;
    }

    @Override
    void Toggle() {
        lightValue += 2;
    }
}
