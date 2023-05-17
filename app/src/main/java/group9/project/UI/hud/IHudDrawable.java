/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package group9.project.UI.hud;

import com.jme3.scene.Node;

/**
 *
 * @author natem
 */
public interface IHudDrawable {
    
    public Node getRootNode();
        
    public void update();
    
    public void setEnabled(boolean enabled);
}
