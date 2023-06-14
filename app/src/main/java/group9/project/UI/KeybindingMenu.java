package group9.project.UI;

import com.jme3.font.BitmapFont;
import com.jme3.input.KeyInput;
import com.jme3.input.KeyNames;
import com.jme3.input.RawInputListener;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.scene.Node;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.component.BorderLayout;
import group9.project.MissionControl;
import group9.project.UI.Input.InputAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;


public class KeybindingMenu extends AbstractMenu implements RawInputListener {
    
    private final int NUMBER_OF_COMPONENTS_ON_PAGE = 6;
    private Page[] pages;
    
    private String activeKeybinding;
    
    class Page 
    {
        private ArrayList<Container> containers;
        public Page()
        {
            containers = new ArrayList<>();
        }
        
        public void addContainer(Container container)
        {
            containers.add(container);
        }
        
        public ArrayList<Container> getContainers()
        {
            return containers;
        }
    }
    
    
    public KeybindingMenu(Node guiNode, BitmapFont font)
    {
        super(guiNode, font);
    }
    
    @Override
    protected void init()
    {
        myWindow.setLocalTranslation(MissionControl.getWidth()/2 - MissionControl.getWidth()/6, MissionControl.getHeight()/2 + MissionControl.getHeight()/6, 0);

        HashMap<String, Integer> inputMap = InputAction.getInputMap();
        
        pages = new Page[(int)Math.ceil((double)((double)(inputMap.keySet().size())/(double)(NUMBER_OF_COMPONENTS_ON_PAGE)))];
        
        for (int i = 0; i < pages.length; i++) {
            pages[i] = new Page();
        }
        
        int counter = 0;
        for (Map.Entry<String, Integer> entry : inputMap.entrySet())
        {
            Page page = pages[counter/NUMBER_OF_COMPONENTS_ON_PAGE];            
            Container container = new Container(new BorderLayout());
            
            Label keyMappingLabel = container.addChild(new Label(entry.getKey()), BorderLayout.Position.West);
            
            keyMappingLabel.setFontSize(FONT_SIZE);
            
            keyMappingLabel.setPreferredSize(componentSize);
            
            Button keyMappingButton = container.addChild(new Button(KeyNames.getName(entry.getValue())), BorderLayout.Position.East);
            
            keyMappingButton.setFontSize(FONT_SIZE);
            
            keyMappingButton.setPreferredSize(componentSize);
            
            keyMappingButton.addClickCommands(new Command<Button>() 
            {
                @Override
                public void execute(Button source)
                {
                    activeKeybinding = entry.getKey();
                }
            });
            
            page.addContainer(container);
            
            // TODO: add buttons to navigate between pages
            
            counter++;
        }
        
        attachPage(0);
    }
    
    private void attachPage(int pageIndex)
    {
        for (Container container : pages[pageIndex].getContainers()) {
            myWindow.addChild(container);
            System.out.println("attached container");
        }
    }
    
    @Override
    public void onKeyEvent(KeyInputEvent event)
    {
        if (event.isPressed() && activeKeybinding != null && enabled)
        {
            System.out.println(InputAction.getKeyMapping(activeKeybinding));
            InputAction.setKeyMapping(activeKeybinding, event.getKeyCode());
            System.out.println(InputAction.getKeyMapping(activeKeybinding));
            activeKeybinding = null;
            System.out.println("set keymapping");
            myWindow.detachAllChildren();
            init();
            
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
    
}
