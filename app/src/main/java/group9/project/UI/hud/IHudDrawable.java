package group9.project.UI.hud;

import com.jme3.scene.Node;

/**
 *
 * @author natem
 */
public interface IHudDrawable {
    
    /**
     * Use this to retrieve the root node of the drawable's internal scene graph.
     * By attaching the returned node as a child of the application's gui node, the drawable can be made visible on the HUD.
     * @return the drawables root node
     */
    public Node getRootNode();
        
    /**
     * Handles any update procedures the drawable needs to execute within the game loop.
     */
    public void update();
    
    /**
     * Use this to enable/disable to drawable object.
     * Classes which implement this method should add/remove the children of the root node whenever the drawable is enabled/disabled.
     * @param enabled
     */
    public void setEnabled(boolean enabled);
}
