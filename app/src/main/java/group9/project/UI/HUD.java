/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9.project.UI;

import group9.project.UI.Camera.CustomCameraControl;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import group9.project.MissionControl;
import group9.project.Utility.Date.DateCalculator;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.UI.Drawable.DrawableManager;

/**
 *
 * @author natem
 */
public class HUD {
    
    private SimpleApplication app;
    private BitmapFont font;
    private boolean enabled;
    private CustomCameraControl camControl;
    
    private BitmapText sunLabel,mercuryLabel,venusLabel,earthLabel,marsLabel,jupiterLabel,saturnLabel,neptuneLabel,uranusLabel,rocketLabel,moonLabel,titanLabel;
    private BitmapText speedLabel, dateLabel, distanceLabel, rocketSpeedLabel;
    
    
    
    public HUD(SimpleApplication app, BitmapFont font)
    {
        this.app = app;
        this.font = font;
        this.enabled = true;
        this.dateLabel = new BitmapText(font);
        this.dateLabel.setLocalTranslation(MissionControl.WIDTH-500, MissionControl.HEIGHT-50, 0);
        this.distanceLabel = new BitmapText(font);
        this.distanceLabel.setLocalTranslation(MissionControl.WIDTH-500, MissionControl.HEIGHT-100, 0);
        this.rocketSpeedLabel = new BitmapText(font);
        this.rocketSpeedLabel.setLocalTranslation(MissionControl.WIDTH-500, MissionControl.HEIGHT-150, 0);
        
        app.getGuiNode().attachChild(dateLabel);
        app.getGuiNode().attachChild(distanceLabel);
        app.getGuiNode().attachChild(rocketSpeedLabel);
        initialize();
        registerInputs();
    }
    
    public void setLabelsVisible(boolean visible)
    {
        
    }
    
    public void setSpeedLabel(CustomCameraControl camControl)
    {
        this.camControl = camControl;
        speedLabel = new BitmapText(font);
        speedLabel.setLocalTranslation(MissionControl.WIDTH/2, MissionControl.HEIGHT-100, 0);
        app.getGuiNode().attachChild(speedLabel);
    }

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
    
    private void registerInputs()
    {
        InputManager inputManager = app.getInputManager();
        
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
    
    private void attachChildren()
    {
        app.getGuiNode().attachChild(sunLabel);
        app.getGuiNode().attachChild(mercuryLabel);
        app.getGuiNode().attachChild(venusLabel);
        app.getGuiNode().attachChild(earthLabel);
        app.getGuiNode().attachChild(marsLabel);
        app.getGuiNode().attachChild(jupiterLabel);
        app.getGuiNode().attachChild(saturnLabel);
        app.getGuiNode().attachChild(neptuneLabel);
        app.getGuiNode().attachChild(uranusLabel);
        app.getGuiNode().attachChild(rocketLabel);
        app.getGuiNode().attachChild(moonLabel);
        app.getGuiNode().attachChild(titanLabel);
    }
    
    private void detachChildren()
    {
        app.getGuiNode().detachChild(sunLabel);
        app.getGuiNode().detachChild(mercuryLabel);
        app.getGuiNode().detachChild(venusLabel);
        app.getGuiNode().detachChild(earthLabel);
        app.getGuiNode().detachChild(marsLabel);
        app.getGuiNode().detachChild(jupiterLabel);
        app.getGuiNode().detachChild(saturnLabel);
        app.getGuiNode().detachChild(neptuneLabel);
        app.getGuiNode().detachChild(uranusLabel);
        app.getGuiNode().detachChild(moonLabel);
        app.getGuiNode().detachChild(rocketLabel);
        app.getGuiNode().detachChild(titanLabel);
    }


    public void update()
    {
        if (!enabled) return;
        
        if (speedLabel != null)
        {
            speedLabel.setText(camControl.getSpeed() + "");
        }
        
        dateLabel.setText("Date: " + DateCalculator.getInstance().getCurrentDate() + "");
        rocketSpeedLabel.setText("Rocket speed: " + PhysicsObjectData.getInstance().getRocketShipSpeed() + " km/s");
        distanceLabel.setText("Distance to titan: " + (float)((int)(PhysicsObjectData.getInstance().getRocketShipDistanceToTitan()*100000))/100000.0 + " km");

        sunLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("sun").getDrawable().getLocalTranslation()));
        mercuryLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("mercury").getDrawable().getLocalTranslation()));
        venusLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("venus").getDrawable().getLocalTranslation()));
        earthLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("earth").getDrawable().getLocalTranslation()));
        marsLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("mars").getDrawable().getLocalTranslation()));
        jupiterLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("jupiter").getDrawable().getLocalTranslation()));
        saturnLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("saturn").getDrawable().getLocalTranslation()));
        neptuneLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("neptune").getDrawable().getLocalTranslation()));
        uranusLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("uranus").getDrawable().getLocalTranslation()));
        moonLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("moon").getDrawable().getLocalTranslation()));
        titanLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("titan").getDrawable().getLocalTranslation()));
        rocketLabel.setLocalTranslation(app.getCamera().getScreenCoordinates(DrawableManager.getInstance().getObjectWithName("rocket").getDrawable().getLocalTranslation()));
        
        BitmapText[] labels = new BitmapText[]{
            sunLabel,
            mercuryLabel,
            venusLabel,
            earthLabel,
            marsLabel,
            jupiterLabel,
            saturnLabel,
            neptuneLabel,
            uranusLabel,
            moonLabel,
            titanLabel,
            rocketLabel
        };
        
        for (BitmapText label : labels) {
            if (label.getLocalTranslation().getZ() > 1)
            {
                label.setAlpha(0);
            } else
            {
                label.setAlpha(1);
            }
        }
    }
    
    private void initialize()
    {
        
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
}
