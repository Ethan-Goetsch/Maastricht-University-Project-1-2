package group9.project.Utility.Interfaces;

@FunctionalInterface
public interface INumericalFunction<T, W>
{
    public W evaluate(T time, W value);
}