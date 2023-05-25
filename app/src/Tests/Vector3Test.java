import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import group9.project.Utility.Math.Vector3;

import java.util.Random;

public class Vector3Test
{
    private Random rand;

    @BeforeEach
    void setUp() 
    {
        rand = new Random();
    }    

    @Test
    @DisplayName("Test getX")
    void testGetX() 
    {
        double rnd = rand.nextDouble();

        Vector3 vector = new Vector3(rnd, 0, 0); 

        assertEquals(rnd, vector.getX());
    }
    
    @Test
    @DisplayName("Test getY")
    void testGetY() 
    {
        double rnd = rand.nextDouble();

        Vector3 vector = new Vector3(0, rnd, 0); 

        assertEquals(rnd, vector.getY());
    }

    @Test
    @DisplayName("Test getZ")
    void testGetZ() 
    {
        double rnd = rand.nextDouble();

        Vector3 vector = new Vector3(0, 0, rnd); 

        assertEquals(rnd, vector.getZ());
    }

    @Test
    @DisplayName("Test clone")
    void testClone() 
    {
        Vector3 v1 = new Vector3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()); 

        Vector3 v2 = v1.clone();

        assertAll("Vectors coordinates",
            () -> assertEquals(v1.getX(), v2.getX()),
            () -> assertEquals(v1.getY(), v2.getY()),
            () -> assertEquals(v1.getZ(), v2.getZ())
        );        
    }

    @Test
    @DisplayName("Test add")
    void testAdd() 
    {
        Vector3 v1 = new Vector3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()); 

        Vector3 v2 = new Vector3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()); 

        Vector3 v3 = v1.clone();

        Vector3 v4 = v1.add(v2);

        assertAll("Vectors add",
            () -> assertEquals(v2.getX() + v3.getX(), v4.getX()),
            () -> assertEquals(v2.getY() + v3.getY(), v4.getY()),
            () -> assertEquals(v2.getZ() + v3.getZ(), v4.getZ())
        );        
    }

    @Test
    @DisplayName("Test getX")
    void testSubtract()
    {
        Vector3 v1 = new Vector3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()); 

        Vector3 v2 = new Vector3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()); 

        Vector3 v3 = v1.clone();

        Vector3 v4 = v1.subtract(v2);

        assertAll("Vectors subtraction",
            () -> assertEquals(v3.getX() - v2.getX(), v4.getX()),
            () -> assertEquals(v3.getY() - v2.getY(), v4.getY()),
            () -> assertEquals(v3.getZ() - v2.getZ(), v4.getZ())
        );        
    }

    @Test
    @DisplayName("Test divideBy")
    void testDivideBy()
    {
        double r = rand.nextDouble();

        Vector3 v1 = new Vector3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()); 

        Vector3 v2 = v1.clone();

        Vector3 v3 = v1.divideBy(r);

        assertAll("Vectors division",
            () -> assertEquals(v2.getX() / r, v3.getX()),
            () -> assertEquals(v2.getY() / r, v3.getY()),
            () -> assertEquals(v2.getZ() / r, v3.getZ())
        );        
    }

    @Test
    @DisplayName("Test multiplyBy")
    void testMultiplyBy()
    {
        double r = rand.nextDouble();

        Vector3 v1 = new Vector3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()); 

        Vector3 v2 = v1.clone();

        Vector3 v3 = v1.multiplyBy(r);

        assertAll("Vectors multiplication",
            () -> assertEquals(v2.getX() * r, v3.getX()),
            () -> assertEquals(v2.getY() * r, v3.getY()),
            () -> assertEquals(v2.getZ() * r, v3.getZ())
        );        
    }

    @Test
    @DisplayName("Test getMagnitude")
    void testGetMagnitude()
    {
        Vector3 v1 = new Vector3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()); 

        assertEquals(Math.sqrt(v1.getX() * v1.getX() + v1.getY() * v1.getY() + v1.getZ() * v1.getZ()), v1.getMagnitude());
    }

    @Test
    @DisplayName("Test normalize")
    void testNormalize()
    {
        Vector3 v1 = new Vector3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()); 

        double m = v1.getMagnitude();

        Vector3 v2 = v1.clone().divideBy(m);

        Vector3 v3 = v1.normalize();

        assertAll("Vectors normalization",
            () -> assertEquals(v2.getX(), v3.getX()),
            () -> assertEquals(v2.getY(), v3.getY()),
            () -> assertEquals(v2.getZ(), v3.getZ())
        );        
    }

    @Test
    @DisplayName("Test toString")
    void testToString()
    {
        Vector3 v1 = new Vector3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()); 

        String s = "(" + v1.getX() + ", " + v1.getY() + ", " + v1.getZ() + ")";

        assertEquals(s, v1.toString());
    }
}