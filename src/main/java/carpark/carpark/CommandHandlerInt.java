package carpark.carpark;

interface CommandHandlerInt<T extends CommandInt>{
    void run(T command);
}