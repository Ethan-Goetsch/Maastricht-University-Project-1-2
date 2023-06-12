
package group9.project.Physics.Managers;

import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Settings.SimulationSettings;
import group9.project.Utility.Math.Vector3;
import group9.project.Utility.Math.Vector3Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class SaveState
{
    
    /**
     * Saves the position and velocity vectors of all the objects stored in {@code group9.project.Physics.Managers.PhysicsObjectData} to a file.
     * 
     * @param filename the name for the text file (including file extension) to which the physics objects' positions and velocities should be written.
     * @throws IOException 
     */
    public static void save(String filename) throws IOException
    {
        PhysicsObject[] physicsObjects = PhysicsEngine.getInstance().getPhysicsObjects();

        PhysicsObjectType[] physicsObjectTypes = PhysicsObjectType.values();
        
        String data = "";
        
        data += SimulationSettings.getSimulationTime() + "\n";
                
        for (int i = 0; i < physicsObjects.length; i++) 
        {
            PhysicsObject physicsObject = physicsObjects[physicsObjectTypes[i].getValue()];

            data += physicsObjectTypes[i].getValue() + "#" + physicsObject.getPosition() + "#" + physicsObject.getVelocity() + "\n";
        }
        
        // create the file
        File saveFile = new File(filename);

        saveFile.createNewFile();
        
        FileWriter fileWriter = new FileWriter(filename, false);

        fileWriter.write(data); 
        
        fileWriter.close();
    }
    
    /**
     * Reads in velocity and position data for objects in the physics simulation from a text file.
     * The first line in the file should contain the simulation time.
     * Each consecutive line should should be in the following format: physicsObjectType#position#velocity,
     * where physicsObjectType is a number, and position and velocity are vectors in the format defined in {@code group9.project.Utility.Math.Vector3.toString()}.
     * 
     * @param filename the path of the file to read
     * @throws FileNotFoundException
     */
    public static void load(String filename) throws FileNotFoundException
    {
        File readFile = new File(filename);

        Scanner scanner = new Scanner(readFile);
        
        SimulationSettings.setSimulationTime(Double.parseDouble(scanner.nextLine())); // read simulation time
        
        // read position and velocity data:
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            Vector3 position = Vector3Parser.parseFromString(line.split("#")[1]);

            Vector3 velocity = Vector3Parser.parseFromString(line.split("#")[2]);

            PhysicsEngine.getInstance().getPhysicsObjects()[Integer.parseInt(line.split("#")[0])].setPosition(position);
            
            PhysicsEngine.getInstance().getPhysicsObjects()[Integer.parseInt(line.split("#")[0])].setVelocity(velocity);
        }
    }
}
