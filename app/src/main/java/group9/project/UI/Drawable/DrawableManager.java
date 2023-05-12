package group9.project.UI.Drawable;

import java.util.HashMap;
import java.util.Iterator;

import group9.project.Utility.Interfaces.IResetable;

public class DrawableManager implements IResetable
{
  private HashMap<String, DrawableUI> drawablesMap;

  //#region Singleton
  private static DrawableManager instance;

  public static DrawableManager getInstance()
  {
      if (instance == null)
      {
        instance = new DrawableManager();
      }
      
      return instance;
  }
  //#endregion

  // private constructor
  private DrawableManager()
  {
    drawablesMap = new HashMap<>();
  }

  // adds a drawable to the drawables list
  public void add(DrawableUI drawable)
  {
      if (!drawablesMap.containsKey(drawable.getName()))
      {
          this.drawablesMap.put(drawable.getName(), drawable);
      }
  }

  // returns true if the drawables list containt the argument drawable
  public boolean contains(DrawableUI drawable)
  {
      return drawablesMap.containsValue(drawable);
  }
  
  public HashMap<String, DrawableUI> getDrawables()
  {
      return drawablesMap;
  }

  // removes a drawable from the list
  public void remove(IDrawable drawable)
  {
      drawablesMap.remove(drawable.getName());
  }


  // returns an iterator for the drawables list
  public Iterator<DrawableUI> getIterator()
  {
      return drawablesMap.values().iterator();
  }
  
  public DrawableUI getObjectWithName(String name) 
  {
      return drawablesMap.get(name);
  }
  
  public void update()
  {
      Iterator<DrawableUI> iterator = getIterator();
      while (iterator.hasNext())
      {
          iterator.next().draw();
      }
  }
  
  @Override
  public void reset()
  {
      this.drawablesMap.clear();
  }
}