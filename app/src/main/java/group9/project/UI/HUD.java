package group9.project.UI;

import group9.project.UI.hud.IHudDrawable;
import com.jme3.input.InputManager;
import com.jme3.scene.Node;
import java.util.ArrayList;

public class HUD {
    
    private final Node guiNode;
    
    private boolean enabled;
        
    private final ArrayList<IHudDrawable> drawables;
    
    InputManager inputManager;
    
    /**
     * Creates an instance of {@code HUD}.
     * @param guiNode the application's root gui node
     * @param inputManager the application's input manager, used to register the appropriate HUD inputs
     */
    public HUD(Node guiNode, InputManager inputManager)
    {
        this.enabled = true;
        
        this.guiNode = guiNode;
        
        drawables = new ArrayList<>();
        
        this.inputManager = inputManager;
    }
    
    /**
     * Adds a {@code IHudDrawable} to the HUD.
     * @param drawable the drawable to be added
     */
    public void addHudDrawable(IHudDrawable drawable)
    {
        drawables.add(drawable);
        guiNode.attachChild(drawable.getRootNode()); // attach the drawable's local scene graph to the gui node

        // register inputs if drawable implements the IInputable interface:
        if (drawable instanceof IInputable)
        {
            ((IInputable) drawable).registerInputs(inputManager);
        }
    }

    /**
     * Enables/disables the HUD.
     * If set to false, the HUD won't update.
     * Be aware that this will not hide the HUD, to do that the {@code setHidden(hidden: boolean)} method should be used.
     * @param enabled
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * Hides the HUD.
     * Calls {@code IHudDrawables.setEnabled(hidden)} on each drawable added to the HUD,
     *      therefore it is important the any class which implements {@code IHudDrawable} handles hiding its scene graph within the {@code setEnabled} method.
     * @param hidden
     */
    public void setHidden(boolean hidden)
    {
        for (IHudDrawable drawable : drawables) {
            drawable.setEnabled(hidden);
        }
    }

    /**
     * Updates the HUD by calling the {@code update} method on each {@code IHudDrawable} added to the HUD.
     */
    public void update()
    {
        if (!enabled) return;
        
        for (IHudDrawable drawable : drawables) {
            drawable.update();
        }

    }
  
}
