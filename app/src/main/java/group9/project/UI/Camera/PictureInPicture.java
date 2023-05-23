package group9.project.UI.Camera;


import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;


public class PictureInPicture {
    
    RenderManager renderManager;
    ViewPort viewport;
    
    public PictureInPicture(Camera cam, RenderManager renderManager)
    {
        this.renderManager = renderManager;
        
        cam.setViewPort(0f, .3f, .7f, 1f);
        
        viewport = renderManager.createPostView("PiP", cam);
        viewport.setClearFlags(false, false, false);
    }
    
    public void setRootNode(Node rootNode)
    {
        viewport.attachScene(rootNode);
    }
}
