package group9.project.UI;

import com.jme3.input.InputManager;

public interface IInputable {
    
    /**
     * Registers this objects inputs with the input manager.
     * @param inputManager
     */
    public void registerInputs(InputManager inputManager);
}
