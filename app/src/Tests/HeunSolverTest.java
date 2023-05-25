import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import group9.project.Solvers.DifferentialSolver;
import group9.project.Solvers.HeunSolver;
import group9.project.Utility.Interfaces.INumericalFunction;
import group9.project.Utility.Math.Vector3;

public class HeunSolverTest
{ 
    private DifferentialSolver solver = new HeunSolver();
    
    private INumericalFunction<Double, Vector3> derivative = (t, w) -> new Vector3(1.0, 0, 0);

    @Test
    @DisplayName("Test solveNumericalEquation")
    void testSolveNumericalEquation()
    {
        Vector3 actual = solver.solveNumericalEquation(new Vector3(0, 0, 0), derivative, 0.1, 0);

        Vector3 expected = new Vector3(0.1, 0, 0);

        assertAll("Vector3 equality",
            () -> assertEquals(expected.getX(), actual.getX()),
            () -> assertEquals(expected.getY(), actual.getY()),
            () -> assertEquals(expected.getZ(), actual.getZ())
        );
    }
}
