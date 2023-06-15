package group9.project.UI.Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KeybindingManager
{
    public static final String QUIT = "QUIT";

    public static final String SUN = "Follow Sun";

    public static final String MERCURY = "Follow Mercury";

    public static final String VENUS = "Follow Venus";

    public static final String EARTH = "Follow Earth";

    public static final String MARS = "Follow Mars";

    public static final String JUPITER = "Follow Jupiter";

    public static final String SATURN = "Follow Saturn";

    public static final String TITAN = "Follow Titan";

    public static final String URANUS = "Follow Uranus";

    public static final String NEPTUNE = "Follow Neptune";

    public static final String MOON = "Follow Moon";

    public static final String ROCKET = "Follow Rocket";

    public static final String INCREASE_SIM_SPEED = "Increase Simulation Speed";

    public static final String DECREASE_SIM_SPEED = "Decrease Simulation Speed";

    public static final String OPEN_MENU = "Open Menu";

    public static final String PAUSE_SIM = "Pause Simulation";
    
    public static final String DETACH_CAMERA_ROTATION = "Detach Camera Rotation";
    
    public static final String DEFAULT_KEYBINDINGS_FILENAME = "defaultkeybindings.txt";
    
    private static final HashMap<String, Integer> inputMap = new HashMap<>();
    
    private static final ArrayList<IInputListener> listeners = new ArrayList<>();
    
    /**
     * Binds the specified key code to the mapping name and notifies listeners of this change.
     * @param name the name of the mapping
     * @param keyInput the key code which triggers this mapping
     */
    public static void setKeyMapping(String name, int keyInput)
    {
        inputMap.put(name, keyInput);
        notifyListeners();
    }
    
    /**
     * Binds the specified key code to the mapping name.
     * Does the same as {@code KeybindingManager#setKeyMapping}, but does not notify listeners of the change.
     * @param name the name of the mapping
     * @param keyInput the key code which triggers this mapping
     */
    public static void setDefaultKeyMapping(String name, int keyInput)
    {
        inputMap.put(name, keyInput);
    }
    
    /**
     * Returns the key code corresponding to the specified key mapping name.
     * @param name the name of the key mapping
     * @return the keycode corresponding to the mapping name
     */
    public static int getKeyMapping(String name)
    {
        return inputMap.get(name);
    }
    
    /**
     * Returns the key mappings.
     * @return a {@code java.util.HashMap} containing entries where the key is the mapping name and the corresponding key code is the value
     */
    public static HashMap<String, Integer> getInputMap()
    {
        return inputMap;
    }
    
    /**
     * Registers an input listener so that it will be notified of key mapping changes.
     * @param listener the input listener to register
     */
    public static void registerListener(IInputListener listener)
    {
        listeners.add(listener);
    }
    
    /**
     * Notifies registered input listeners by calling the listener's {@code IInputListener#onInputChange()} method.
     */
    private static void notifyListeners()
    {
        for (IInputListener listener : listeners) {
            listener.onInputChange();
        }
    }
    
    /**
     * Checks whether or not the key code corresponding to the specified mapping name also belongs to a different mapping.
     * @param name the name of the desired key mapping
     * @return true if the key code corresponding to {@code name} appears twice in the set of key mappings.
     */
    public static boolean isConflictingKeybind(String name)
    {
        for (Map.Entry<String, Integer> entry : inputMap.entrySet()) {
            if (entry.getKey().equals(name)) continue;
            if (entry.getValue().equals(inputMap.get(name)))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Write the current key bindings to a file.
     * @param filename the name of the file (including file extension) which will store the key binding data (the file will be created if it does not exist)
     * @throws IOException 
     */
    public static void saveKeybindings(String filename) throws IOException
    {
        
        String data = "";
        
        for (Map.Entry<String, Integer> entry : inputMap.entrySet()) {
            data += entry.getKey() + "#" + entry.getValue() + "\n";
        }
        
        File saveFile = new File(filename);
        
        saveFile.createNewFile();
        
        FileWriter fileWriter = new FileWriter(filename, false);
        
        fileWriter.write(data);
        
        fileWriter.close();
    }
    
    /**
     * Loads key bindings from a file and notifies listeners of the new key bindings.
     * @param filename the name of the file to load the keybindings from
     * @throws FileNotFoundException thrown if the specified filename does not exist
     */
    public static void loadKeybindings(String filename) throws FileNotFoundException
    {
        File readFile = new File(filename);
        
        Scanner scanner = new Scanner(readFile);
        
        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            
            String name = line.split("#")[0];
            int keyCode = Integer.parseInt(line.split("#")[1]);
            
            setDefaultKeyMapping(name, keyCode);
        }
        
        notifyListeners();
        
        scanner.close();
    }
    
}