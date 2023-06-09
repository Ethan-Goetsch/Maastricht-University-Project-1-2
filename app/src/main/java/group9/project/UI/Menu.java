package group9.project.UI;

import group9.project.UI.Camera.CinematicModeController;
import com.jme3.font.BitmapFont;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.scene.Node;
import com.simsilica.lemur.Axis;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.Slider;
import com.simsilica.lemur.component.BorderLayout;
import group9.project.MissionControl;
import group9.project.Physics.Managers.SaveState;
import group9.project.UI.Camera.FreeModeController;
import group9.project.UI.Input.KeybindingManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu extends AbstractMenu implements ActionListener
{
    
    KeybindingMenu keybindingMenu;
    private final String OPEN_MENU = "Open Menu";
    
    /**
     * Constructor.
     * @param guiNode the application's gui node to which the menu should attach
     * @param font the font for all text in the menu
     */
    public Menu(Node guiNode, BitmapFont font)
    {
        super(guiNode, font);
        keybindingMenu = new KeybindingMenu(guiNode, font);
    }
    
    /**
     * Initialises the state of the menu and creates and configures all menu's components.
     */
    @Override
    protected void init()
    {
        
        // position the menu
        myWindow.setLocalTranslation(MissionControl.getWidth()/2 - MissionControl.getWidth()/6, MissionControl.getHeight()/2 + MissionControl.getHeight()/6, 0);
    
        // add the components
        Label menuTitle = myWindow.addChild(new Label("Pause Menu"));

        menuTitle.setFontSize(FONT_SIZE);

        menuTitle.setPreferredSize(componentSize);

        // simulation speed slider
        Container sliderContainer = new Container(new BorderLayout());

        SimSpeedSliderModel sliderModel = new SimSpeedSliderModel(0, 200, 200);

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

        pauseButton.addClickCommands(new Command<Button>()
        {
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
        
        // exit cinematic mode button
        Button toggleCinematicModeButton = myWindow.addChild(new Button("Exit cinematic mode"));
        
        toggleCinematicModeButton.setFontSize(FONT_SIZE);
        
        toggleCinematicModeButton.setPreferredSize(componentSize);
        
        toggleCinematicModeButton.addClickCommands(new Command<Button>()
        {
            @Override
            public void execute(Button source)
            {
                CinematicModeController cinematicController = CinematicModeController.getInstance();
                cinematicController.setCamControlEnabled(!cinematicController.isCamControlEnabled());
                
                FreeModeController freeController = FreeModeController.getInstance();
                freeController.setCamControlEnabled(!freeController.isCamControlEnabled());
                
                if (cinematicController.isCamControlEnabled())
                {
                    source.setText("Exit cinematic mode");
                } else
                {
                    source.setText("Enter cinematic mode");
                }
            }
        }
        );
        
        // set keybindings button
        Button openKeybindingsButton = myWindow.addChild(new Button("Key Bindings"));
        
        openKeybindingsButton.setFontSize(FONT_SIZE);
        
        openKeybindingsButton.setPreferredSize(componentSize);
        
        openKeybindingsButton.addClickCommands(new Command<Button>()
        {
            @Override
            public void execute(Button source)
            {
                setEnabled(false);
                keybindingMenu.setEnabled(true);
            }
        });
        
        // exit menu button
        Button exitButton = myWindow.addChild(new Button("Close Menu"));

        exitButton.setFontSize(FONT_SIZE);

        exitButton.setPreferredSize(componentSize);

        exitButton.addClickCommands(new Command<Button>()
        {
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

        quitButton.addClickCommands(new Command<Button>()
        {
                @Override
                public void execute( Button source )
                {
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
        inputManager.addMapping(OPEN_MENU, new KeyTrigger(KeyInput.KEY_ESCAPE));

        inputManager.addListener(this, new String[]{OPEN_MENU});
        
        inputManager.addRawInputListener(keybindingMenu);
    }
    
    @Override
    public void onAction(String name, boolean keyPressed, float tpf)
    {
        if (name.equals(OPEN_MENU) && keyPressed)
        {
            keybindingMenu.setEnabled(false);
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
    
}