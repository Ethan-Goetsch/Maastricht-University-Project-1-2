package group9.project;


import com.jme3.app.BasicProfilerState;
import com.jme3.app.DebugKeysAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.font.BitmapFont;
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
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.BaseStyles;

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
import group9.project.UI.Camera.PictureInPicture;
import group9.project.UI.Camera.TopDownView;
import group9.project.UI.Camera.ViewSwitcher;
import group9.project.UI.Drawable.DrawableCelestialBodyUI;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.Drawable.DrawableRocketShipUI;
import group9.project.UI.Drawable.DrawableUI;
import group9.project.UI.HUD;
import group9.project.UI.Menu;
import group9.project.UI.hud.PlanetLabels;
import group9.project.UI.ScaleConverter;
import group9.project.UI.hud.SystemInformationView;
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
    Node newNode;
    
    private TopDownView topDownView;
    
    public CustomCameraControl camControl;
    public CameraNode cameraNode;
    
    private boolean enableCursor;
    
    
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
    
    public MissionControl()
    {
        super(new StatsAppState(), new DebugKeysAppState(), new BasicProfilerState(false),
              new ScreenshotAppState("", System.currentTimeMillis()));
        enableCursor = false;
    }



    public static void main(String[] args)
    {
        
        // display fullscreen
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode mode = gd.getDisplayMode();
        MissionControl.WIDTH = mode.getWidth();
        MissionControl.HEIGHT = mode.getHeight();
        
        MissionControl app = new MissionControl();
        instance = app;
        AppSettings settings = new AppSettings(true);
        settings.setResolution(WIDTH, HEIGHT);
        settings.setVSync(true);
        
        
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp()
    {
        
        GuiGlobals.initialize(this);
        BaseStyles.loadGlassStyle();
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");
        
        createSystems();
        
        initSpatials();
        
        initDrawables();
        
        initCamera();
        System.out.println("camera node: " + cameraNode);
        
        initEffectsAndFilters();
   
        getRootNode().attachChild(SkyFactory.createSky(getAssetManager(), "Textures/8k_stars_milky_way.jpg", SkyFactory.EnvMapType.SphereMap)); // set background/hdri
        
        initLights();
        
        initKeys();

        
        // create view switcher
        ViewSwitcher viewSwitcher = new ViewSwitcher(this, inputManager, camControl);
        viewSwitcher.registerInputs();
        
        // create HUD
        hud = new HUD(guiNode, inputManager);
        BitmapFont font = assetManager.loadFont("Interface/Fonts/Monospaced.fnt");
        hud.addHudDrawable(new PlanetLabels(this.getCamera(), font));
        hud.addHudDrawable(new SystemInformationView(cam, font, camControl));
        
        
        this.getCamera().update();
        
        // Initialize the globals access so that the defualt
        // components can find what they need.
        GuiGlobals.initialize(this);
            
        // Load the 'glass' style
        BaseStyles.loadGlassStyle();
            
        // Set 'glass' as the default style when not specified
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");
        
        System.out.println("camera node: " + cameraNode);
        Menu menu = new Menu(guiNode, guiFont);
        menu.registerKeys(inputManager);
        
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

        sunSpatial2 = assetManager.loadModel("Models/Sun/sun.j3o");
        mercurySpatial2 = assetManager.loadModel("Models/Mercury/mercury.j3o");
        venusSpatial2 = assetManager.loadModel("Models/Venus/venus.j3o");
        earthSpatial2 = assetManager.loadModel("Models/Earth/earth.j3o");
        moonSpatial2 = assetManager.loadModel("Models/Moon/moon.j3o");
        marsSpatial2 = assetManager.loadModel("Models/Mars/mars.j3o");
        jupiterSpatial2 = assetManager.loadModel("Models/Jupiter/jupiter.j3o");
        saturnSpatial2 = assetManager.loadModel("Models/Saturn/saturn.j3o");
        titanSpatial2 = assetManager.loadModel("Models/Titan/titan.j3o");
        neptuneSpatial2 = assetManager.loadModel("Models/Neptune/neptune.j3o");
        uranusSpatial2 = assetManager.loadModel("Models/Uranus/uranus.j3o");
        rocketSpatial2 = assetManager.loadModel("Models/Rocket/rocket.j3o");
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
        
        System.out.println("cam node (lights): " + cameraNode);
    }
    
    public void initCamera()
    {
        inputManager.setCursorVisible(false); // make cursor invisible

        // create camera node
        cameraNode = new CameraNode("Main Camera", this.getCamera());
        cameraNode.setControlDir(com.jme3.scene.control.CameraControl.ControlDirection.SpatialToCamera);
        
        // rotate camera node
        Quaternion rotation = new Quaternion();
        rotation.fromAngles(0,0,0);
        cameraNode.setLocalRotation(rotation);
                
        cameraNode.setLocalTranslation(0, 0, DrawableManager.getInstance().getObjectWithName("sun").getPreferredViewDistance() * -1);

        // add custom camera control to camera node
        camControl = new CustomCameraControl(cam);
        camControl.setInput(inputManager);
        cameraNode.addControl(camControl);
        
        rootNode.attachChild(cameraNode);
        
        cam.setFrustumFar((float)ScaleConverter.worldToScreenLength(287e+9)); // so we can see far away object
        
        cam.update();
        
        inputManager.setCursorVisible(false); // make cursor invisible
        
        // The following code is used for creating a seperate viewport, but its buggy so ive commented it out
        
        //Camera cam2 = cam.clone();
        //cam2.setViewPort(.4f, .6f, 0.8f, 1f);
        //cam2.setLocation(new Vector3f(-0.10f, 1.57f, 4.81f));
        //cam2.setRotation(new Quaternion(0.00f, 0.99f, -0.04f, 0.02f));
        //ViewPort viewPort2 = renderManager.createMainView("PiP", cam2);
        //viewPort2.setClearFlags(true, true, true);
        
        //newNode = new Node("PiP");
        //sun = new DrawableCelestialBodyUI("sun", sunSpatial2, (CelestialBodyObject)PhysicsEngine.getInstance().getPhysicsObjects()[PhysicsObjectType.Sun.getValue()]);
        //newNode.attachChild(sun.getDrawable());
        
        //viewPort2.attachScene(newNode);
        
        /*
        Camera pipCam = cam.clone();
        //pipCam.setLocation(new Vector3f(383,-19672,235109));
        pipCam.setLocation(new Vector3f(0,0,135109));

        pipCam.lookAt(DrawableManager.getInstance().getObjectWithName("sun").getDrawable().getLocalTranslation(), new Vector3f(0, 1, 0));
        PictureInPicture pip = new PictureInPicture(pipCam, renderManager);
        topDownView = new TopDownView();
        topDownView.setLightPosition(pipCam.getLocation());
        pip.setRootNode(topDownView.getRootNode());
*/
        
    }
    
    public void initDrawables()
    {
        PhysicsObject[] physicsObjects = PhysicsEngine.getInstance().getPhysicsObjects();
        
        DrawableManager dmanager = DrawableManager.getInstance();
        
        System.out.println("sun spatial: " + sunSpatial);
        
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("sun", sunSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Sun.getValue()]));
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("mercury", mercurySpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Mercury.getValue()]));
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("venus", venusSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Venus.getValue()]));
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("earth", earthSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Earth.getValue()]));
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("moon", moonSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Moon.getValue()]));
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("mars", marsSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Mars.getValue()]));
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("jupiter",jupiterSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Jupiter.getValue()]));
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("saturn", saturnSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Saturn.getValue()]));
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("titan", titanSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Titan.getValue()]));
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("uranus", uranusSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Uranus.getValue()]));
        DrawableManager.getInstance().add(new DrawableCelestialBodyUI("neptune", neptuneSpatial, (CelestialBodyObject)physicsObjects[PhysicsObjectType.Neptune.getValue()]));
        
        DrawableManager.getInstance().add(new DrawableRocketShipUI("rocket", (float)ScaleConverter.worldToScreenLength(50000), rocketSpatial, (RocketShipObject)physicsObjects[PhysicsObjectType.Rocket.getValue()]));
        
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
        inputManager.deleteMapping( SimpleApplication.INPUT_MAPPING_EXIT);
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
    
    public void setPaused(boolean paused)
    {
        if (paused)
        {
            SimulationSettings.pauseSimulation();
            camControl.setEnabled(false);
        }
        else
        {
            SimulationSettings.unpauseSimulation();
            camControl.setEnabled(true);
        }
    }
    
    public void setCursorVisible(boolean visible) {
        enableCursor = visible;
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
            
            inputManager.setCursorVisible(enableCursor);
            
            
            //topDownView.update(tpf);
            //newNode.updateLogicalState(tpf);
            //newNode.updateGeometricState();

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
    
    public static Spatial sunSpatial2;
    public static Spatial mercurySpatial2;
    public static Spatial venusSpatial2;
    public static Spatial earthSpatial2;
    public static Spatial moonSpatial2;
    public static Spatial marsSpatial2;
    public static Spatial jupiterSpatial2;
    public static Spatial saturnSpatial2;
    public static Spatial titanSpatial2;
    public static Spatial uranusSpatial2;
    public static Spatial neptuneSpatial2;
    public static Spatial rocketSpatial2;

    
}