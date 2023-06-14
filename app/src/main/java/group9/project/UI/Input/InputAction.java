package group9.project.UI.Input;

import com.jme3.input.KeyInput;
import java.util.ArrayList;
import java.util.HashMap;

public class InputAction
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
    
    private static final HashMap<String, Integer> inputMap = new HashMap<>();
    
    private static final ArrayList<IInputListener> listeners = new ArrayList<>();
    
    public static void setKeyMapping(String name, int keyInput)
    {
        inputMap.put(name, keyInput);
        System.out.println("set " + name + " to " + keyInput);
        notifyListeners();
    }
    
    public static void setDefaultKeyMapping(String name, int keyInput)
    {
        inputMap.put(name, keyInput);
    }
    
    public static int getKeyMapping(String name)
    {
        return inputMap.get(name);
    }
    
    public static HashMap<String, Integer> getInputMap()
    {
        return inputMap;
    }
    
    public static void registerListener(IInputListener listener)
    {
        listeners.add(listener);
    }
    
    private static void notifyListeners()
    {
        for (IInputListener listener : listeners) {
            listener.onInputChange();
        }
    }
    
}