package group9.project.UI;

import com.simsilica.lemur.Button;
import com.simsilica.lemur.Container;

public class MenuComponentExit {
    
    Container container;
    
    public MenuComponentExit(Menu menu)
    {
        container = new Container();
        Button button = container.addChild(new Button("Quit to Desktop"));
        button.setFontSize(menu.getFontSize());
        container.addChild(button);
        menu.addComponent(container);
    }
    
}
