/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9.project.UI.Camera;

import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import static group9.project.MissionControl.earthSpatial2;
import static group9.project.MissionControl.jupiterSpatial2;
import static group9.project.MissionControl.marsSpatial2;
import static group9.project.MissionControl.mercurySpatial2;
import static group9.project.MissionControl.moonSpatial2;
import static group9.project.MissionControl.neptuneSpatial2;
import static group9.project.MissionControl.saturnSpatial2;
import static group9.project.MissionControl.sunSpatial2;
import static group9.project.MissionControl.titanSpatial2;
import static group9.project.MissionControl.uranusSpatial2;
import static group9.project.MissionControl.venusSpatial2;
import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Objects.CelestialBodyObject;
import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.UI.Drawable.DrawableCelestialBodyUI;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.Drawable.DrawableUI;
import group9.project.UI.Drawable.DrawableUI2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author natem
 */
public class TopDownView {
    
    Node rootNode;
    ArrayList<DrawableUI2D> drawables = new ArrayList<>();
    PointLight sunLight;
    
    public TopDownView()
    {
        rootNode = new Node("Top Down Node");
        rootNode.setQueueBucket(RenderQueue.Bucket.Gui);
        rootNode.setCullHint(Spatial.CullHint.Never);
        
        sunLight = new PointLight();
        sunLight.setColor(ColorRGBA.White.mult(1f));
        sunLight.setPosition(Vector3f.ZERO);
        sunLight.setRadius(1000000000000f);
        AmbientLight ambientLight = new AmbientLight();
        ambientLight.setColor(ColorRGBA.White.mult(100));
        rootNode.addLight(ambientLight);
        rootNode.addLight(sunLight);
        init();
    }
    
    public void setLightPosition(Vector3f pos)
    {
        sunLight.setPosition(pos);
    }
    
    private void init()
    {
        
        PhysicsObject[] physicsObjects = PhysicsEngine.getInstance().getPhysicsObjects();
        drawables.add(new DrawableUI2D(new DrawableCelestialBodyUI("sun", sunSpatial2, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Sun.getValue()])));
        drawables.add(new DrawableUI2D(new DrawableCelestialBodyUI("mercury", mercurySpatial2, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Mercury.getValue()])));
        drawables.add(new DrawableUI2D(new DrawableCelestialBodyUI("venus", venusSpatial2, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Venus.getValue()])));
        drawables.add(new DrawableUI2D(new DrawableCelestialBodyUI("earth", earthSpatial2, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Earth.getValue()])));
        drawables.add(new DrawableUI2D(new DrawableCelestialBodyUI("mars", marsSpatial2, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Mars.getValue()])));
        drawables.add(new DrawableUI2D(new DrawableCelestialBodyUI("jupiter",jupiterSpatial2, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Jupiter.getValue()])));
        drawables.add(new DrawableUI2D(new DrawableCelestialBodyUI("saturn", saturnSpatial2, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Saturn.getValue()])));
        drawables.add(new DrawableUI2D(new DrawableCelestialBodyUI("uranus", uranusSpatial2, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Uranus.getValue()])));
        drawables.add(new DrawableUI2D(new DrawableCelestialBodyUI("neptune", neptuneSpatial2, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Neptune.getValue()])));

        for (DrawableUI2D drawable : drawables) {
            //drawable.getDrawable().scale(1/drawable.getDrawable().getLocalScale().length()).scale(5000);
            //System.out.println(drawable.getDrawable().getLocalScale());
            rootNode.attachChild(drawable.getDrawable());
        }
    }
    
    public Node getRootNode()
    {
        return rootNode;
    }
    
    public void update(float tpf)
    {
        for (DrawableUI2D drawable : drawables) {
            drawable.draw();
        }
        rootNode.updateLogicalState(tpf);
        rootNode.updateGeometricState();
    }
}
