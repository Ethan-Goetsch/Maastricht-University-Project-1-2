/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9.project.UI;

import group9.project.UI.hud.IHudDrawable;
import com.jme3.input.InputManager;
import com.jme3.scene.Node;
import java.util.ArrayList;

/**
 *
 * @author natem
 */
public class HUD {
    
    private final Node guiNode;
    
    private boolean enabled;
        
    private final ArrayList<IHudDrawable> drawables;
    
    InputManager inputManager;
    
    public HUD(Node guiNode, InputManager inputManager)
    {
        this.enabled = true;
        
        this.guiNode = guiNode;
        
        drawables = new ArrayList<>();
        
        this.inputManager = inputManager;
        
        registerInputs();
    }
    
    public void addHudDrawable(IHudDrawable drawable)
    {
        drawables.add(drawable);
        guiNode.attachChild(drawable.getRootNode());
        if (drawable instanceof IInputable)
        {
            ((IInputable) drawable).registerInputs(inputManager);
        }
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
    
    private void registerInputs()
    {
        for (IHudDrawable drawable : drawables) {
            if (drawable instanceof IInputable)
            {
                ((IInputable) drawable).registerInputs(inputManager);
            }
        }
    }

    public void update()
    {
        if (!enabled) return;
        
        for (IHudDrawable drawable : drawables) {
            drawable.update();
        }

    }
  
}
