/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package group9.project.UI;

import com.jme3.input.InputManager;

/**
 *
 * @author natem
 */
public interface IInputable {
    
    /**
     * Registers this objects inputs with the input manager.
     * @param inputManager
     */
    public void registerInputs(InputManager inputManager);
}
