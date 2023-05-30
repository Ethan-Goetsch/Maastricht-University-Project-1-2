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
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.style.BaseStyles;

import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Physics.Objects.CelestialBodyObject;
import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.SimulationSettings;
import group9.project.UI.Camera.CustomCameraControl;
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
import group9.project.Managers.SystemsManager;
import group9.project.Optimization.LaunchToEarthOptimization;
import group9.project.Optimization.LaunchToTitanOptimization;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MissionControl extends SimpleApplication
{
    private static int WIDTH;
    private static int HEIGHT;

    private HUD hud;

    Node newNode;
        
    private CustomCameraControl camControl;
    private CameraNode cameraNode;
    private ViewSwitcher viewSwitcher;
    
    private boolean enableCursor;
    
    private boolean isPaused;
    private boolean isSimulationPaused;
    
    private long frameWaitTime = 0;
    
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
        
        // display in fullscreen by getting width and height of display:
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode mode = gd.getDisplayMode();
        MissionControl.WIDTH = mode.getWidth();
        MissionControl.HEIGHT = mode.getHeight();
        
        instance  = new MissionControl();

        // settings for the application:
        AppSettings settings = new AppSettings(true);
        settings.setResolution(WIDTH, HEIGHT);
        settings.setVSync(false);
        settings.setFrameRate(800);
        
        
        instance.setSettings(settings);
        instance.start(); 
    }

    @Override
    public void simpleInitApp()
    {
        
        setDisplayStatView(false);
        
        // initialise GUI style:
        GuiGlobals.initialize(this);
        BaseStyles.loadGlassStyle();
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");
        
        createSystems();
        
        initSpatials();
        
        initDrawables();
        
        initCamera();
        
        initEffectsAndFilters();
   
        initLights();
        
        initKeys();

        getRootNode().attachChild(SkyFactory.createSky(getAssetManager(), "Textures/8k_stars_milky_way.jpg", SkyFactory.EnvMapType.SphereMap)); // set background/hdri

        
        // create view switcher to switch camera views between planets/rocket:
        viewSwitcher = new ViewSwitcher(cam, inputManager, camControl, cameraNode);
        
        // create HUD:
        hud = new HUD(guiNode, inputManager);
        BitmapFont font = assetManager.loadFont("Interface/Fonts/Monospaced.fnt");
        hud.addHudDrawable(new PlanetLabels(this.getCamera(), font));
        hud.addHudDrawable(new SystemInformationView(cam, font, camControl));
        
        
        this.getCamera().update();
        
        // create menu (to exit program and whatever other features get added to the menu):
        Menu menu = new Menu(guiNode, guiFont);
        menu.registerKeys(inputManager); // register inputs (button clicks and such)
        
    }
    
    /**
     * Adds effects and filters (such as bloom) to the viewport.
     */
    private void initEffectsAndFilters()
    {
        // adds a bloom filter to the viewport:
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        BloomFilter bloom = new BloomFilter(BloomFilter.GlowMode.Objects);
        fpp.addFilter(bloom);
        viewPort.addProcessor(fpp);
    }
    
    /**
     * Instantiates the spatials for the planets and the rockets by loading the .j3o model files into memory.
     */
    private void initSpatials()
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
    
    /**
     * Creates the lights so that we can see the planets and rocket.
     * The sun light is what is most important here.
     */
    private void initLights()
    {
        // TODO: make the sun light follow the sun instead of being stuck in place
        PointLight sunLight = new PointLight();
        sunLight.setColor(ColorRGBA.White.mult(1f));
        sunLight.setPosition(Vector3f.ZERO);
        sunLight.setRadius(1000000000000f); // radius has to be big enough to light up the planets at the edge of the solar system
        
        // the ambient light is kind of useless right now, but if we want want to brighten the shadows we can increase the scalar for the ambient light color
        AmbientLight ambientLight = new AmbientLight();
        ambientLight.setColor(ColorRGBA.White.mult(0.0001f));
        
        rootNode.addLight(ambientLight);
        rootNode.addLight(sunLight);
    }
    
    /**
     * Initialises the application's camera.
     * Camera should initially be pointing towards the sun at an appropriate distance away.
     * The custom camera control is also created here.
     */
    private void initCamera()
    {
        inputManager.setCursorVisible(false);

        // create camera node
        cameraNode = new CameraNode("Main Camera", this.getCamera());
        cameraNode.setControlDir(com.jme3.scene.control.CameraControl.ControlDirection.SpatialToCamera);
        
        // rotate camera node
        Quaternion rotation = new Quaternion();
        rotation.fromAngles(0,0,0);
        cameraNode.setLocalRotation(rotation);
                
        cameraNode.setLocalTranslation(0, 0, DrawableManager.getInstance().getObjectWithName("sun").getPreferredViewDistance() * -1);

        // add custom camera control to camera node so that we can move the camera nicely (see the CustomCameraControl class for more details)
        camControl = new CustomCameraControl(cam);
        camControl.setInput(inputManager);
        cameraNode.addControl(camControl);
        
        rootNode.attachChild(cameraNode);
        
        cam.setFrustumFar((float)ScaleConverter.worldToScreenLength(287e+9)); // so we can see far away object
        
        cam.update();
        
        inputManager.setCursorVisible(false); // make cursor invisible
    }
    
    /**
     * Creates the drawables using the appropriate physics objects and spatials, and adds them to the drawable manager.
     */
    private void initDrawables()
    {
        PhysicsObject[] physicsObjects = PhysicsEngine.getInstance().getPhysicsObjects();
        
        DrawableManager dmanager = DrawableManager.getInstance();
        
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
        
        DrawableManager.getInstance().add(new DrawableRocketShipUI("rocket", (float)ScaleConverter.worldToScreenLength(50), rocketSpatial, (RocketShipObject)physicsObjects[PhysicsObjectType.Rocket.getValue()]));
        
        Iterator<DrawableUI> dIterator = DrawableManager.getInstance().getIterator();
        while (dIterator.hasNext())
        {
            rootNode.attachChild(dIterator.next().getDrawable()); // attach the drawables to the scene graph so we can see them
        }
    }

    /**
     * Restarts the physics simulation.
     * All planets as well as the rocket will be reset back to their initial states.
     */
    public void restart()
    {
        PhysicsObjectData.getInstance().start();

        PhysicsEngine.getInstance().start();
    }
    
    /**
     * Adds application wide input handling.
     */
    private void initKeys()
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

    /**
     * Starts the physics simulation.
     */
    private void createSystems()
    {
        SystemsManager.getInstance().start();
        SimulationSettings.playSimulation();

    }
    
    /**
     * Sets the paused state of the application.
     * This pauses/unpauses the physics simulation, and disables/enabled camera movement.
     * The simulation will only be unpaused if the simulation has not been paused using the method setSimulationPaused() (that is, if getIsSimulationPaused does not return true).
     * Since the spatials that can be seen on screen match the movement of the physics objects, there will be no spatial movement while the physics engine is paused.
     * @param paused if true, the application will be set to paused, if false the application will be set to unpaused.
     */
    public void setPaused(boolean paused)
    {
        isPaused = paused;
        
        if (paused)
        {
            SimulationSettings.pauseSimulation();
            camControl.setEnabled(false);
        }
        else
        {
            if (!isSimulationPaused)
            {
                SimulationSettings.playSimulation();
            }
            camControl.setEnabled(true);
        }
        
        camControl.setEnabled(!paused);
        viewSwitcher.setEnabled(!paused);
        
    }
    
    public void setSimulationPaused(boolean paused)
    {
        isSimulationPaused = paused;
        if (paused)
        {
            SimulationSettings.pauseSimulation();
        } else
        {
            SimulationSettings.playSimulation();
        }
    }
    
    public void setFrameWaitTime(long time)
    {
        if (time < 0) time = 0;
        frameWaitTime = time;
    }
    
    /**
     * Sets cursor visibility.
     * @param visible if true, the mouse cursor will be visible on screen, if false it will be hidden
     */
    public void setCursorVisible(boolean visible) {
        enableCursor = visible;
    }
    
    /**
     * 
     * @return the width of the application window in pixels
     */
    public static int getWidth()
    {
        return WIDTH;
    }
    
    /**
     * 
     * @return the height of the application window in pixels
     */
    public static int getHeight()
    {
        return HEIGHT;
    }
    
    /**
     * 
     * @return true if the application is paused, false otherwise.
     */
    public boolean getIsPaused()
    {
        return isPaused;
    }
    
    /**
     * 
     * @return true if the physics simulation is paused, false otherwise
     */
    public boolean getIsSimulationPaused()
    {
        return isSimulationPaused;
    }

    @Override
    public void simpleUpdate(float tpf)
    {
        PhysicsEngine.getInstance().update();

        if (LaunchToTitanOptimization.getInstance().getIsOptimizationDevelopmentMode())
        {
            LaunchToTitanOptimization.getInstance().update();
        }

        if (LaunchToEarthOptimization.getInstance().getIsOptimizationDevelopmentMode())
        {
            LaunchToEarthOptimization.getInstance().update();
        }
            
        DrawableManager.getInstance().update(); // this updates all the drawables, so that their position matches that of their corresponding physics objects
                        
        camControl.update(tpf); // this lets the camera move

        hud.update();
            
        inputManager.setCursorVisible(enableCursor); // not ideal to call this method in the update loop, but I ran into issues where the cursor would still be visible even after setting it to be not so. So this is a work around.
        try {
            Thread.sleep(frameWaitTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(MissionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    // spatials:
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