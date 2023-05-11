package group9.project.Utility.Interfaces;

@FunctionalInterface
public interface INumericalFunction<T, W, R>
{
    public R evaluate(T time, W value);
}