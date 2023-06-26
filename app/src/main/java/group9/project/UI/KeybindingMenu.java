package group9.project.UI;

import com.jme3.font.BitmapFont;
import com.jme3.input.KeyNames;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.component.BorderLayout;
import group9.project.MissionControl;
import group9.project.UI.Input.IInputListener;
import group9.project.UI.Input.KeybindingManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class KeybindingMenu extends AbstractMenu implements RawInputListener, IInputListener {
    
    private final int NUMBER_OF_COMPONENTS_ON_PAGE = 6;
    private Page[] pages;
    
    private String activeKeybinding;
    
    /**
     * This class stores the components to display on each page of the keybinding menu.
     */
    class Page 
    {
        private ArrayList<Container> containers;
        public Page()
        {
            containers = new ArrayList<>();
        }
        
        /**
         * Add a container containing gui elements which belong to the page.
         * @param container the container to add
         */
        public void addContainer(Container container)
        {
            containers.add(container);
        }
        
        /**
         * 
         * @return an {@code ArrayList} of the {@Container} objects belonging to the page
         */
        public ArrayList<Container> getContainers()
        {
            return containers;
        }
    }
    
    
    /**
     * 
     * @param guiNode the root node for the keybinding menu scene graph
     * @param font the font to use for all text on the keybinding menu
     */
    public KeybindingMenu(Node guiNode, BitmapFont font)
    {
        super(guiNode, font);
        KeybindingManager.registerListener(this);
    }
    
    /**
     * 
     */
    @Override
    protected void init()
    {
        myWindow.setLocalTranslation(MissionControl.getWidth()/2 - MissionControl.getWidth()/4, MissionControl.getHeight()/2 + MissionControl.getHeight()/4, 0); // set the on-screen location of the menu

        HashMap<String, Integer> inputMap = KeybindingManager.getInputMap();
        
        pages = new Page[(int)Math.ceil((double)((double)(inputMap.keySet().size())/(double)(NUMBER_OF_COMPONENTS_ON_PAGE)))]; // create array which stores enough pages to fit components for every key mapping
        
        // populate the pages array:
        for (int i = 0; i < pages.length; i++) {
            pages[i] = new Page();
        }
        
        int counter = 0;
        for (Map.Entry<String, Integer> entry : inputMap.entrySet())
        {
            Page page = pages[counter/NUMBER_OF_COMPONENTS_ON_PAGE]; // set current page so that we do not add more components to the page than the maximum number of components per page            
            Container container = new Container(new BorderLayout());
            
            Label keyMappingLabel = container.addChild(new Label(entry.getKey()), BorderLayout.Position.West);
            
            keyMappingLabel.setFontSize(FONT_SIZE);
            
            keyMappingLabel.setPreferredSize(componentSize);
            
            Button keyMappingButton = container.addChild(new Button(KeyNames.getName(entry.getValue())), BorderLayout.Position.East);
            
            keyMappingButton.setFontSize(FONT_SIZE);
            
            keyMappingButton.setPreferredSize(componentSize);
            
            // colour button red if the keybinding conflicts with another keybinding:
            if (KeybindingManager.isConflictingKeybind(entry.getKey()))
            {
                keyMappingButton.setColor(ColorRGBA.Red);
            }
            
            // set the active keybinding (that the user will change with the next keyboard input) when the user clicks on the button:
            keyMappingButton.addClickCommands(new Command<Button>() 
            {
                @Override
                public void execute(Button source)
                {
                    activeKeybinding = entry.getKey();
                }
            });
            
            page.addContainer(container); // add above components to the page
            
            counter++;
        }
        
        attachPage(0); // start with the current page
    }
    
    /**
     * Attaches the components stored in a desired {@code Page} instance to the window, as well as other general components for the keybinding menu.
     * @param pageIndex the index of the {@code Page}
     */
    private void attachPage(int pageIndex)
    {
        myWindow.detachAllChildren(); // make sure we remove any pages that were previously added to the window
        
        // add the page's components to the window
        for (Container container : pages[pageIndex].getContainers()) {
            myWindow.addChild(container);
        }
        
        // logic for adding page navigation buttons:
        Container pageNavigateContainer = myWindow.addChild(new Container(new BorderLayout()));
        if (pageIndex != 0)
        {
            Button prevPageButton = pageNavigateContainer.addChild(new Button("Previous page"), BorderLayout.Position.West);
            prevPageButton.setFontSize(FONT_SIZE);
            prevPageButton.setPreferredSize(componentSize);
            prevPageButton.addClickCommands(new Command<Button>()
            {
                @Override
                public void execute(Button source)
                {
                    attachPage(pageIndex-1);
                }
            });
        }
        if (pageIndex != pages.length-1)
        {
            Button nextPageButton = pageNavigateContainer.addChild(new Button("Next page"), BorderLayout.Position.East);
            nextPageButton.setFontSize(FONT_SIZE);
            nextPageButton.setPreferredSize(componentSize);
            nextPageButton.addClickCommands(new Command<Button>()
            {
                @Override
                public void execute(Button source)
                {
                    attachPage(pageIndex+1);
                }
            });
        }
        
        // button to save key bindings:
        Button saveButton = myWindow.addChild(new Button("Save key bindings"));
        saveButton.setFontSize(FONT_SIZE);
        saveButton.setPreferredSize(componentSize);
        saveButton.addClickCommands(new Command<Button>()
            {
                @Override
                public void execute(Button source)
                {
                    try
                    {
                        KeybindingManager.saveKeybindings("keybindings.txt");
                    }
                    catch (IOException e)
                    {
                        System.out.println("Unable to save keybindings file (IOException).");
                    }
                }
            
            });
        
        // button to reset keybinding to default:
        Button resetButton = myWindow.addChild(new Button("Reset to default"));
        resetButton.setFontSize(FONT_SIZE);
        resetButton.setPreferredSize(componentSize);
        resetButton.addClickCommands(new Command<Button>()
            {
                @Override
                public void execute(Button source)
                {
                    try
                    {
                        KeybindingManager.loadKeybindings(KeybindingManager.DEFAULT_KEYBINDINGS_FILENAME);
                    }
                    catch (FileNotFoundException e)
                    {
                        System.out.println("Unable to load keybindings file (FileNotFoundException).");
                    }
                }
            
            });
        
        // button to exit keybinding menu:
        Button exitButton = myWindow.addChild(new Button("Exit"));
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
    }
    
    @Override
    public void onKeyEvent(KeyInputEvent event)
    {
        // set the keymapping
        if (event.isPressed() && activeKeybinding != null && enabled)
        {
            KeybindingManager.setKeyMapping(activeKeybinding, event.getKeyCode());
            activeKeybinding = null;
        }
    }
    
    @Override
    public void onTouchEvent(TouchEvent event)
    {
        
    }
    
    @Override
    public void onMouseButtonEvent(MouseButtonEvent event)
    {
        
    }
    
    @Override
    public void onMouseMotionEvent(MouseMotionEvent event)
    {
        
    }
    
    @Override
    public void onJoyButtonEvent(JoyButtonEvent event)
    {
        
    }
    
    @Override
    public void onJoyAxisEvent(JoyAxisEvent event)
    {
        
    }
    
    @Override
    public void endInput()
    {
        
    }
    
    @Override
    public void beginInput()
    {
        
    }
    
    @Override
    public void onInputChange()
    {
        // reload all components to reflect the new keybinding
        myWindow.detachAllChildren();
        init();
    }
    
}
