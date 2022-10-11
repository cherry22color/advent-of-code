package adventOfCode_2015.DAY6_15_Abstractions;

abstract class Lamp {
    public int lightValue = 0;
    public void ExecuteCommand(LampCommand lampCommand)
    {
        if (lampCommand == LampCommand.TurnOn)
        {
            TurnOn();
        }
        else if (lampCommand == LampCommand.TurnOff)
        {
            TurnOff();
        }
        else
        {
            Toggle();
        }
    }
    abstract void TurnOn();
    abstract void TurnOff();
    abstract void Toggle();
}
