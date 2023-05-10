package group9.project;

/*
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
*/

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.util.SkyFactory;
import java.io.IOException;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;

import group9.project.Events.EventManager;
import group9.project.Optimization.Optimization;
import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Physics.Objects.CelestialBodyObject;
import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.Settings.SimulationSettings;
import group9.project.UI.Camera.CustomCameraControl;
import group9.project.UI.Camera.ViewSwitcher;
import group9.project.UI.Drawable.DrawableCelestialBodyUI;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.Drawable.DrawableRocketShipUI;
import group9.project.UI.Drawable.DrawableUI;
import group9.project.UI.HUD;
import group9.project.UI.ScaleConverter;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class MissionControl extends SimpleApplication
{
    public static int WIDTH = (int) (2560/1.2);
    public static int HEIGHT = (int) (1440/1.2);
    private HUD hud;
    
    CustomCameraControl camControl;
    
    
    //#region Singleton
    private static MissionControl instance;

    public static MissionControl getInstance()
    {
        if (instance == null)
        {
            instance = new MissionControl();
        }

        return instance;
    }
    //#endregion



    public static void main(String[] args)
    {
        // display fullscreen
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode mode = gd.getDisplayMode();
        MissionControl.WIDTH = mode.getWidth();
        MissionControl.HEIGHT = mode.getHeight();
        
        MissionControl app = new MissionControl();
        AppSettings settings = new AppSettings(true);
        settings.setResolution(WIDTH, HEIGHT);
        settings.setVSync(true);
        
        
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp()
    {
        createSystems();
        
        initSpatials();
        
        initDrawables();
        
        initCamera();
        
        initEffectsAndFilters();
   
        getRootNode().attachChild(SkyFactory.createSky(getAssetManager(), "Textures/8k_stars_milky_way.jpg", SkyFactory.EnvMapType.SphereMap)); // set background/hdri
        
        initLights();
        
        initKeys();

        
        // create view switcher
        ViewSwitcher viewSwitcher = new ViewSwitcher(this, inputManager, camControl);
        viewSwitcher.registerInputs();
        
        // create HUD
        hud = new HUD(this, assetManager.loadFont("Interface/Fonts/Monospaced.fnt"));
        hud.setSpeedLabel(camControl);
        
        this.getCamera().update();
    }
    
    public void initEffectsAndFilters()
    {
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        BloomFilter bloom = new BloomFilter(BloomFilter.GlowMode.Objects);
        fpp.addFilter(bloom);
        viewPort.addProcessor(fpp);
    }
    
    public void initSpatials()
    {
        sunSpatial = assetManager.loadModel("Models/Sun/sun.j3o");
        mercurySpatial = assetManager.loadModel("Models/Mercury/mercury.j3o");
        venusSpatial = assetManager.loadModel("Models/Venus/venus.j3o");
        earthSpatial = assetManager.loadModel("Models/Earth/earth.j3o");
        moonSpatial = assetManager.loadModel("Models/Moon/moon.j3o");
        marsSpatial = assetManager.loadModel("Models/Mars/mars.j3o");
        jupiterSpatial = assetManager.loadModel("Models/Jupiter/jupiter.j3o");
        saturnSpatial = assetManager.loadModel("Models/Saturn/saturn.j3o");
        titanSpatial = assetManager.loadModel("Models/Titan/titan.j3o");
        neptuneSpatial = assetManager.loadModel("Models/Neptune/neptune.j3o");
        uranusSpatial = assetManager.loadModel("Models/Uranus/uranus.j3o");
        rocketSpatial = assetManager.loadModel("Models/Rocket/rocket.j3o");
    }
    
    public void initLights()
    {
        PointLight sunLight = new PointLight();
        sunLight.setColor(ColorRGBA.White.mult(1f));
        sunLight.setPosition(Vector3f.ZERO);
        sunLight.setRadius(1000000000000f);
        Vector3f f= new Vector3f();
        
        AmbientLight ambientLight = new AmbientLight();
        ambientLight.setColor(ColorRGBA.White.mult(0.0001f));
        
        rootNode.addLight(ambientLight);
        rootNode.addLight(sunLight);
    }
    
    public void initCamera()
    {
        // create camera node
        CameraNode cameraNode = new CameraNode("Main Camera", this.getCamera());
        cameraNode.setControlDir(com.jme3.scene.control.CameraControl.ControlDirection.SpatialToCamera);
        
        // rotate camera node
        Quaternion rotation = new Quaternion();
        rotation.fromAngles(0,0,0);
        cameraNode.setLocalRotation(rotation);
                
        cameraNode.setLocalTranslation(0, 0, DrawableManager.getInstance().getObjectWithName("sun").getPreferredViewDistance() * -1);

        // add custom camera control to camera node
        camControl = new CustomCameraControl();
        camControl.setInput(inputManager);
        camControl.setCamera(cam);
        cameraNode.addControl(camControl);
        
        rootNode.attachChild(cameraNode);
        
        cam.setFrustumFar((float)ScaleConverter.worldToScreenLength(287e+9)); // so we can see far away object

        flyCam.setEnabled(false); // disable fly cam
        
        cam.update();
        
        inputManager.setCursorVisible(false); // make cursor invisible
    }
    
    public void initDrawables()
    {
        PhysicsObject[] physicsObjects = PhysicsEngine.getInstance().getPhysicsObjects();
        
        DrawableManager dmanager = DrawableManager.getInstance();
        
        System.out.println("sun spatial: " + sunSpatial);
        
        new DrawableCelestialBodyUI("sun", sunSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Sun.getValue()]);
        new DrawableCelestialBodyUI("mercury", mercurySpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Mercury.getValue()]);
        new DrawableCelestialBodyUI("venus", venusSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Venus.getValue()]);
        new DrawableCelestialBodyUI("earth", earthSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Earth.getValue()]);
        new DrawableCelestialBodyUI("moon", moonSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Moon.getValue()]);
        new DrawableCelestialBodyUI("mars", marsSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Mars.getValue()]);
        new DrawableCelestialBodyUI("jupiter",jupiterSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Jupiter.getValue()]);
        new DrawableCelestialBodyUI("saturn", saturnSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Saturn.getValue()]);
        new DrawableCelestialBodyUI("titan", titanSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Titan.getValue()]);
        new DrawableCelestialBodyUI("uranus", uranusSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Uranus.getValue()]);
        new DrawableCelestialBodyUI("neptune", neptuneSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Neptune.getValue()]);
        
        new DrawableRocketShipUI("rocket", (float)ScaleConverter.worldToScreenLength(50000), rocketSpatial, (RocketShipObject)physicsObjects[PhysicsObjectType.Rocket.getValue()]);
        
        Iterator<DrawableUI> dIterator = DrawableManager.getInstance().getIterator();
        while (dIterator.hasNext())
        {
            rootNode.attachChild(dIterator.next().getDrawable());
        }
    }

    public void restart()
    {
        PhysicsObjectData.getInstance().start();

        PhysicsEngine.getInstance().start();
    }
    
    public void initKeys()
    {
        inputManager.addMapping("Reduce Simulation Speed", new KeyTrigger(KeyInput.KEY_COMMA));
        inputManager.addMapping("Increase Simulation Speed", new KeyTrigger(KeyInput.KEY_PERIOD));
        
        inputManager.addListener(newActionListener, new String[]{"Reduce Simulation Speed","Increase Simulation Speed"});
    }
    
    ActionListener newActionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals("Reduce Simulation Speed") && isPressed)
            {
                SimulationSettings.setSimulationSpeed(SimulationSettings.getSimulationSpeed()-0.04);
            }
            else if (name.equals("Increase Simulation Speed") && isPressed)
            {
                SimulationSettings.setSimulationSpeed(SimulationSettings.getSimulationSpeed()+0.04);
            }
        }
    };

    private void createSystems()
    {
        PhysicsObjectData.getInstance().start();

        PhysicsEngine.getInstance().start();

        EventManager.getInstance().start();
    }

    @Override
    public void simpleUpdate(float tpf)
    {
   
            PhysicsEngine.getInstance().update();
            
            if (SimulationSettings.getDEVELOPMENT_MODE())
            {
                Optimization.getInstance().update();
            }
            
            DrawableManager.getInstance().update();
            
            camControl.update(tpf);
            hud.update();

    }
    
    
    // spatials
    public static Spatial sunSpatial;
    public static Spatial mercurySpatial;
    public static Spatial venusSpatial;
    public static Spatial earthSpatial;
    public static Spatial moonSpatial;
    public static Spatial marsSpatial;
    public static Spatial jupiterSpatial;
    public static Spatial saturnSpatial;
    public static Spatial titanSpatial;
    public static Spatial uranusSpatial;
    public static Spatial neptuneSpatial;
    public static Spatial rocketSpatial;
}