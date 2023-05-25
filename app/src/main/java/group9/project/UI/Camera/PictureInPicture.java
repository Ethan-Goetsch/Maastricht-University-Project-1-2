package group9.project.UI.Camera;


import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;


public class PictureInPicture {
    
    RenderManager renderManager;
    ViewPort viewport;
    
    /**
     * Constructor.
     * @param cam the application's camera instance 
     * @param renderManager the application's render manager`
     */
    public PictureInPicture(Camera cam, RenderManager renderManager)
    {
        this.renderManager = renderManager;
        
        cam.setViewPort(0f, .3f, .7f, 1f);
        
        viewport = renderManager.createPostView("PiP", cam);
        viewport.setClearFlags(false, false, false);
    }
    
    /**
     * Attaches a node to the viewport, so that all contents of the nodes subtree are displayed.
     * @param rootNode the root node of the scene graph to be displayed in the viewport
     */
    public void setRootNode(Node rootNode)
    {
        viewport.attachScene(rootNode);
    }
}
