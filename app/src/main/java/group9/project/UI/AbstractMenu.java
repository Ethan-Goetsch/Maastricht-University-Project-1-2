package group9.project.UI;

import com.jme3.font.BitmapFont;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.simsilica.lemur.Container;
import group9.project.MissionControl;

public abstract class AbstractMenu {
    protected final int FONT_SIZE = 56;
    protected BitmapFont font;
    protected Vector3f componentSize = new Vector3f(MissionControl.getWidth()/4, MissionControl.getHeight()/16,0);
    protected Node guiNode;
    protected Container myWindow;
    protected boolean enabled;
    
    public AbstractMenu(Node guiNode, BitmapFont font)
    {
        this.guiNode = guiNode;

        this.font = font;
        
        myWindow = new Container();
        
        enabled = false;
        
        init();
    }
    
     /**
     * Enables/disables the menu.
     * When the menu is disabled, is is hidden.
     * When the menu is enabled, the menu is shown and the application is paused.
     * @param enabled true to enable the menu, false to disable it
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;

        MissionControl.getInstance().setPaused(enabled); // pause the game

        if (enabled)
        {
            MissionControl.getInstance().setCursorVisible(true);

            guiNode.attachChild(myWindow);
        } 
        else
        {
            MissionControl.getInstance().setCursorVisible(false);
            
            guiNode.detachChild(myWindow);
        }
    }
    
    protected abstract void init();
}
