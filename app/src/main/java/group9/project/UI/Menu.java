package group9.project.UI;

import com.jme3.font.BitmapFont;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.simsilica.lemur.Axis;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.DefaultRangedValueModel;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.Slider;
import com.simsilica.lemur.component.BorderLayout;
import com.simsilica.lemur.core.GuiLayout;
import com.simsilica.lemur.style.BaseStyles;
import group9.project.MissionControl;
import group9.project.Physics.Managers.SaveState;
import group9.project.UI.Input.InputAction;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu implements ActionListener{
 
    Node guiNode;
    BitmapFont font;
    boolean enabled;
    
    Button quitButton;
    Container myWindow;
    
    private final int FONT_SIZE = 56;
    
    Vector3f componentSize = new Vector3f(MissionControl.getWidth()/4, MissionControl.getHeight()/16,0);
    
    /**
     * Constructor.
     * @param guiNode the application's gui node to which the menu should attach
     * @param font the font for all text in the menu
     */
    public Menu(Node guiNode, BitmapFont font)
    {
        this.guiNode = guiNode;
        this.font = font;
        
        init();
    }
    
    /**
     * Initialises the state of the menu and creates and configures all menu's components.
     */
    public void init()
    {
        enabled = false; // hidden by default
    
        // create a simple container for our elements
        myWindow = new Container();
        
        // position the menu
        myWindow.setLocalTranslation(MissionControl.getWidth()/2 - MissionControl.getWidth()/6, MissionControl.getHeight()/2 + MissionControl.getHeight()/6, 0);
    
        // add the components
        Label menuTitle = myWindow.addChild(new Label("Pause Menu"));
        menuTitle.setFontSize(FONT_SIZE);
        menuTitle.setPreferredSize(componentSize);

        // simulation speed slider
        Container sliderContainer = new Container(new BorderLayout());
        SimSpeedSliderModel sliderModel = new SimSpeedSliderModel(0, 1000, 0);
        Slider simSpeedSlider = new Slider(sliderModel, Axis.X);
        simSpeedSlider.setPreferredSize(componentSize);
        sliderContainer.addChild(simSpeedSlider, BorderLayout.Position.East);
        //simSpeedSlider.setSize(componentSize);
        Label sliderLabel = sliderContainer.addChild(new Label("Simulation Speed"), BorderLayout.Position.West);
        sliderLabel.setFontSize(FONT_SIZE);
        myWindow.addChild(sliderContainer);

        // pause simulation button
        Button pauseButton = myWindow.addChild(new Button("Pause Simulation"));
        pauseButton.setFontSize(FONT_SIZE);
        pauseButton.setPreferredSize(componentSize);
        pauseButton.addClickCommands(new Command<Button>() {
            @Override
            public void execute(Button source)
            {
                MissionControl.getInstance().setSimulationPaused(! MissionControl.getInstance().getIsSimulationPaused());
                if (MissionControl.getInstance().getIsSimulationPaused())
                {
                    source.setText("Resume Simulation");
                } else
                {
                    source.setText("Pause Simulation");
                }
            }
        });
        
        // save simulation button
        Button saveButton = myWindow.addChild(new Button("Save Simulation State"));
        saveButton.setFontSize(FONT_SIZE);
        saveButton.setPreferredSize(componentSize);
        saveButton.addClickCommands(new Command<Button>() {
            @Override
            public void execute(Button source)
            {
                try {
                    SaveState.save("save.txt");
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // load simulation button
        Button loadButton = myWindow.addChild(new Button("Load Previous State"));
        loadButton.setFontSize(FONT_SIZE);
        loadButton.setPreferredSize(componentSize);
        loadButton.addClickCommands(new Command<Button>() {
            @Override
            public void execute(Button source)
            {
                try {
                    SaveState.load("save.txt");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // exit menu button
        Button exitButton = myWindow.addChild(new Button("Close Menu"));
        exitButton.setFontSize(FONT_SIZE);
        exitButton.setPreferredSize(componentSize);
        exitButton.addClickCommands(new Command<Button>() {
            @Override
            public void execute(Button source)
            {
                setEnabled(false);
            }
        });
        
        // quit button
        Button quitButton = myWindow.addChild(new Button("Quit To Desktop"));
        quitButton.setFontSize(FONT_SIZE);
        quitButton.setPreferredSize(componentSize);
        quitButton.addClickCommands(new Command<Button>() {
                @Override
                public void execute( Button source ) {
                    System.exit(0);
                }
            });          

    }
    
    public void addComponent(Container component)
    {
        
    }
    
    /**
     * Registers the inputs for the menu, such as keybinds for opening the menu.
     * @param inputManager
     */
    public void registerKeys(InputManager inputManager)
    {
        inputManager.addMapping(InputAction.OPEN_MENU, new KeyTrigger(KeyInput.KEY_ESCAPE));
        inputManager.addListener(this, new String[]{InputAction.OPEN_MENU});
    }
    
    @Override
    public void onAction(String name, boolean keyPressed, float tpf)
    {
        if (name.equals(InputAction.OPEN_MENU) && keyPressed)
        {
            setEnabled(!enabled);
        }
    }
    
    /**
     * Returns the font size of the menu's text.
     * @return the menu's font size
     */
    public int getFontSize()
    {
        return FONT_SIZE;
    }
    
    /**
     * Returns the font of the menu's text.
     * @return the menu's font
     */
    public BitmapFont getFont()
    {
        return font;
    }
    
    /**
     * Enables/disables the menu.
     * When the menu is disabled, is is hidden.
     * When the menu is enabled, the menu is shown and the application is paused.
     * @param enabled true to enable the menu, false to disable it
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
        MissionControl.getInstance().setPaused(enabled); // pause the game
        if (enabled)
        {
            MissionControl.getInstance().setCursorVisible(true);
            guiNode.attachChild(myWindow);
        } else {
            MissionControl.getInstance().setCursorVisible(false);
            guiNode.detachChild(myWindow);
        }
        
    }
    
    
}
