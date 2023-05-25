import group9.project.Utility.Math.Vector3;
import group9.project.Solvers.DifferentialSolver;
import group9.project.Solvers.RalstonSolver;
import group9.project.Utility.Interfaces.INumericalFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RalstonSolverTest
{
    private DifferentialSolver solver = new RalstonSolver();

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