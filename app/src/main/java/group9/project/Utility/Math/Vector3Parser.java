/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9.project.Utility.Math;

import java.util.Scanner;


public class Vector3Parser {
    
    /**
     * Creates and returns a {@code group9.project.Utility.Math.Vector3} from the given string.
     * @param vectorString the string representation of the vector, should match the format of {@code group9.project.Utility.Math.Vector3.toString()}
     * @return the new Vector3 object
     */
    public static Vector3 parseFromString(String vectorString)
    {
        double x, y, z;
        
        Scanner scanner = new Scanner(vectorString);
        scanner.useDelimiter(",");
        
        x = Double.parseDouble(scanner.next().substring(1));
        y = Double.parseDouble(scanner.next());
        
        String temp = scanner.next();
        z = Double.parseDouble(temp.substring(0,temp.length()-1));
        
        return new Vector3(x,y,z);
    }
}
