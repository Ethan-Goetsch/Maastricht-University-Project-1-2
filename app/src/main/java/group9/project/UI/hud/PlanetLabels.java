/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9.project.UI.hud;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.IInputable;
import group9.project.UI.Input.InputAction;

/**
 *
 * @author natem
 */
public class PlanetLabels implements IHudDrawable, IInputable{
    
    Node rootNode;
    
    BitmapFont font;
    
    Camera cam;
    
    boolean enabled;
    
    private BitmapText sunLabel,mercuryLabel,venusLabel,earthLabel,marsLabel,jupiterLabel,saturnLabel,neptuneLabel,uranusLabel,rocketLabel,moonLabel,titanLabel;

    
    public PlanetLabels(Camera cam)
    {
        rootNode = new Node();
        this.cam = cam;
        init();
    }
    
    public PlanetLabels(Camera cam, BitmapFont font)
    {
        this.font = font;
        rootNode = new Node();
        this.cam = cam;
        init();
    }
    
    private void init()
    {
        this.enabled = true;
        
        earthLabel = new BitmapText(font);
        earthLabel.setSize(font.getCharSet().getRenderedSize());
        earthLabel.setColor(ColorRGBA.Blue);
        earthLabel.setText("Earth");
        earthLabel.setLocalTranslation(300, earthLabel.getLineHeight(), 0);
        
        sunLabel = new BitmapText(font);
        sunLabel.setSize(font.getCharSet().getRenderedSize());
        sunLabel.setColor(ColorRGBA.Yellow);
        sunLabel.setText("Sun");
        sunLabel.setLocalTranslation(300, sunLabel.getLineHeight(), 0);
        
        mercuryLabel = new BitmapText(font);
        mercuryLabel.setSize(font.getCharSet().getRenderedSize());
        mercuryLabel.setColor(ColorRGBA.Brown);
        mercuryLabel.setText("Mercury");
        mercuryLabel.setLocalTranslation(300, mercuryLabel.getLineHeight(), 0);
        
        venusLabel = new BitmapText(font);
        venusLabel.setSize(font.getCharSet().getRenderedSize());
        venusLabel.setColor(ColorRGBA.Orange);
        venusLabel.setText("Venus");
        venusLabel.setLocalTranslation(300, venusLabel.getLineHeight(), 0);
        
        marsLabel = new BitmapText(font);
        marsLabel.setSize(font.getCharSet().getRenderedSize());
        marsLabel.setColor(ColorRGBA.Orange);
        marsLabel.setText("Mars");
        marsLabel.setLocalTranslation(300, marsLabel.getLineHeight(), 0);
        
        jupiterLabel = new BitmapText(font);
        jupiterLabel.setSize(font.getCharSet().getRenderedSize());
        jupiterLabel.setColor(ColorRGBA.Brown);
        jupiterLabel.setText("Jupiter");
        jupiterLabel.setLocalTranslation(300, jupiterLabel.getLineHeight(), 0);
        
        saturnLabel = new BitmapText(font);
        saturnLabel.setSize(font.getCharSet().getRenderedSize());
        saturnLabel.setColor(ColorRGBA.Yellow);
        saturnLabel.setText("Saturn");
        saturnLabel.setLocalTranslation(300, saturnLabel.getLineHeight(), 0);
        
        neptuneLabel = new BitmapText(font);
        neptuneLabel.setSize(font.getCharSet().getRenderedSize());
        neptuneLabel.setColor(ColorRGBA.Blue);
        neptuneLabel.setText("Neptune");
        neptuneLabel.setLocalTranslation(300, neptuneLabel.getLineHeight(), 0);
        
        uranusLabel = new BitmapText(font);
        uranusLabel.setSize(font.getCharSet().getRenderedSize());
        uranusLabel.setColor(ColorRGBA.Cyan);
        uranusLabel.setText("Uranus");
        uranusLabel.setLocalTranslation(300, uranusLabel.getLineHeight(), 0);
        
        rocketLabel = new BitmapText(font);
        rocketLabel.setSize(font.getCharSet().getRenderedSize());
        rocketLabel.setColor(ColorRGBA.Red);
        rocketLabel.setText("Rocket");
        rocketLabel.setLocalTranslation(300, rocketLabel.getLineHeight(), 0);
        
        moonLabel = new BitmapText(font);
        moonLabel.setSize(font.getCharSet().getRenderedSize());
        moonLabel.setColor(ColorRGBA.Gray);
        moonLabel.setText("Moon");
        moonLabel.setLocalTranslation(300, moonLabel.getLineHeight(), 0);

        titanLabel = new BitmapText(font);
        titanLabel.setSize(font.getCharSet().getRenderedSize());
        titanLabel.setColor(ColorRGBA.Gray);
        titanLabel.setText("Titan");
        titanLabel.setLocalTranslation(300, titanLabel.getLineHeight(), 0);
        
        attachChildren();
    }
    
    @Override
    public Node getRootNode()
    {
        return rootNode;
    }
    
    @Override
    public void update()
    {
        updateLabelPositions();
    }
    
    @Override
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
        if (enabled)
        {
            attachChildren();
        } else
        {
            detachChildren();
        }
    }
    
    private void updateLabelPositions()
    {
        sunLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("sun").getDrawable().getLocalTranslation()));
        mercuryLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("mercury").getDrawable().getLocalTranslation()));
        venusLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("venus").getDrawable().getLocalTranslation()));
        earthLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("earth").getDrawable().getLocalTranslation()));
        marsLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("mars").getDrawable().getLocalTranslation()));
        jupiterLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("jupiter").getDrawable().getLocalTranslation()));
        saturnLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("saturn").getDrawable().getLocalTranslation()));
        neptuneLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("neptune").getDrawable().getLocalTranslation()));
        uranusLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("uranus").getDrawable().getLocalTranslation()));
        moonLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("moon").getDrawable().getLocalTranslation()));
        titanLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("titan").getDrawable().getLocalTranslation()));
        rocketLabel.setLocalTranslation(cam.getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("rocket").getDrawable().getLocalTranslation()));
    }
    
    private void attachChildren()
    {
        rootNode.attachChild(sunLabel);
        rootNode.attachChild(mercuryLabel);
        rootNode.attachChild(venusLabel);
        rootNode.attachChild(earthLabel);
        rootNode.attachChild(marsLabel);
        rootNode.attachChild(jupiterLabel);
        rootNode.attachChild(saturnLabel);
        rootNode.attachChild(neptuneLabel);
        rootNode.attachChild(uranusLabel);
        rootNode.attachChild(rocketLabel);
        rootNode.attachChild(moonLabel);
        rootNode.attachChild(titanLabel);
    }
    
    private void detachChildren()
    {
        rootNode.detachChild(sunLabel);
        rootNode.detachChild(mercuryLabel);
        rootNode.detachChild(venusLabel);
        rootNode.detachChild(earthLabel);
        rootNode.detachChild(marsLabel);
        rootNode.detachChild(jupiterLabel);
        rootNode.detachChild(saturnLabel);
        rootNode.detachChild(neptuneLabel);
        rootNode.detachChild(uranusLabel);
        rootNode.detachChild(moonLabel);
        rootNode.detachChild(rocketLabel);
        rootNode.detachChild(titanLabel);
    }
    
    @Override
    public void registerInputs(InputManager inputManager)
    {        
        inputManager.addMapping("Toggle HUD", new KeyTrigger(KeyInput.KEY_H));
        
        ActionListener actionListener = new ActionListener() {
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (name.equals("Toggle HUD") && isPressed)
                {
                    setEnabled(!enabled);
                }
            }
        };
        
        inputManager.addListener(actionListener, new String[]{"Toggle HUD"});
    }
}

