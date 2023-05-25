package group9.project.Utility.Interfaces;

public interface INumericalMethod<T>
{
    public T solveNumericalEquation(T initialValue, INumericalFunction<Double, T> derivativeFunction, double h, double t);
}