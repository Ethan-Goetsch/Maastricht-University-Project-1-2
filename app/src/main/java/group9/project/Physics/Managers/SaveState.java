/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author natem
 */
public class SaveState {
    public static void save(String filename) throws IOException
    {
        PhysicsObject[] physicsObjects = PhysicsEngine.getInstance().getPhysicsObjects();
        PhysicsObjectType[] physicsObjectTypes = PhysicsObjectType.values();
        
        String data = "";
        
        data += SimulationSettings.getSimulationTime() + "\n";
                
        for (int i = 0; i < physicsObjects.length; i++) {
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
    
    public static void load(String filename) throws FileNotFoundException
    {
        File readFile = new File(filename);
        Scanner scanner = new Scanner(readFile);
        SimulationSettings.setSimulationTime(Double.parseDouble(scanner.nextLine()));
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
