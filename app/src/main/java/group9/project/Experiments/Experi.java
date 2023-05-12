package group9.project.Experiments;
import group9.project.Utility.Interfaces.INumericalFunction;
import group9.project.Utility.Math.Vector;
import group9.project.Utility.Math.Vector3;
import java.lang.Math;

public class Experi {
    
INumericalFunction<Double,Vector3> function = new INumericalFunction<Double,Vector3>() {

    @Override
    public Vector3 evaluate(Double time, Vector3 value) {
    
        double x = value.getX();

        return new Vector3(Math.pow(x + time ,2) - 1 , 0 , 0);
    }
    
};

INumericalFunction<Double,Vector3> exactFunction = new INumericalFunction<Double,Vector3>() {

    @Override
    public Vector3 evaluate(Double time, Vector3 value) {

        return new Vector3(2 / (3-2*time) - time, 0,0);
    
    }
    
};


}
