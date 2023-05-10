package group9.project.UI.Drawable;

import java.util.Iterator;

import group9.project.Utility.Interfaces.IResetable;
import java.util.HashMap;

public class DrawableManager implements IResetable
{
  private HashMap<String, IDrawable> drawablesMap;

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
  public void add(IDrawable drawable)
  {
      if (!drawablesMap.containsKey(drawable.getName()))
      {
          this.drawablesMap.put(drawable.getName(), drawable);
      }
  }

  // returns true if the drawables list containt the argument drawable
  public boolean contains(IDrawable drawable)
  {
      return drawablesMap.containsValue(drawable);
  }

  // removes a drawable from the list
  public void remove(IDrawable drawable)
  {
      drawablesMap.remove(drawable.getName());
  }


  // returns an iterator for the drawables list
  public Iterator<IDrawable> getIterator()
  {
      return drawablesMap.values().iterator();
  }
  
  public IDrawable getObjectWithName(String name) 
  {
      return drawablesMap.get(name);
  }
  
  @Override
  public void reset()
  {
      this.drawablesMap.clear();
  }
}