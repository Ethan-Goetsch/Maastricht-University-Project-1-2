/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9.project.UI.Drawable;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import org.lwjgl.opengl.Drawable;

/**
 *
 * @author natem
 */
public class DrawableUI2D implements IDrawable {
    
    private DrawableUI drawable;
    Spatial spatial;
    
    public DrawableUI2D(DrawableUI drawable)
    {
        this.drawable = drawable;
    }
    
    @Override
    public float getPreferredViewDistance()
    {
        return drawable.getPreferredViewDistance();
    }
    
    @Override
    public String getName()
    {
        return drawable.getName();
    }
    
    @Override
    public Spatial getDrawable()
    {
        return drawable.getDrawable().clone().scale(1/drawable.getDrawable().getLocalScale().length()).scale(100);
    }
    
    @Override
    public void draw()
    {
        drawable.draw();
    }

    @Override
    public DrawableUI2D clone()
    {
        return new DrawableUI2D(drawable.clone());
    }
    
}
