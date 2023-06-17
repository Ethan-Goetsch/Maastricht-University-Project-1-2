package group9.project.Interpolation;

public abstract class Interpolation
{
    /**
     * 
     * @param alpha a value between 0 and 1
     * @return the new alpha value dependent on the type of interpolation
     */
    protected abstract double apply(double alpha);

    /*
     * @param alpha a value between 0 and 1
     */
    public double apply(double startValue, double endValue, double alpha)
    {
        return startValue + (endValue - startValue) * apply(alpha);
    }

    public static final Interpolation Linear = new Interpolation()
    {
        @Override
        public double apply(double alpha)
        {
            return alpha;
        } 
    };
}