package group9.project.UI.Drawable;

import java.util.Iterator;

import group9.project.Utility.Interfaces.IResetable;
import java.util.HashMap;

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

  /**
   * Constructor.
   */
  private DrawableManager()
  {
    drawablesMap = new HashMap<>();
  }

  /**
   * Stores a drawable in the drawable manager.
   * @param drawable the drawable to be stored
   */
  public void add(DrawableUI drawable)
  {
      if (!drawablesMap.containsKey(drawable.getName()))
      {
          this.drawablesMap.put(drawable.getName(), drawable);
      }
  }

   /**
   * Checks if the drawable manager contains a given drawable.
   * @param drawable the drawable to check for existance 
   * @return true if the drawable exists in the drawable manager, false otherwise
   */
  public boolean contains(DrawableUI drawable)
  {
      return drawablesMap.containsValue(drawable);
  }
  
  /**
   * Returns the drawables stored in the drawable manager.
   * @return a hashmap of the drawables, where the key is the name of the drawable and the value is the drawable object
   */
  public HashMap<String, DrawableUI> getDrawables()
  {
      return drawablesMap;
  }

  /**
   * Removes a drawable from the drawable manager.
   * @param drawable the drawable to be removed
   */
  public void remove(IDrawable drawable)
  {
      drawablesMap.remove(drawable.getName());
  }


  /**
   * Returns an iterator, which iterators over the drawables stored in the drawable manager.
   * @return the iterator for the drawables
   */
  public Iterator<DrawableUI> getIterator()
  {
      return drawablesMap.values().iterator();
  }
  
  /**
   * Returns the drawable, if it exists, the has the given name.
   * If the drawable does not exist in the drawable manager, returns null.
   * @param name the name of the drawable
   * @return the drawable object if it exists in the drawable manager, otherwise null
   */
  public DrawableUI getObjectWithName(String name)
  {
      return drawablesMap.get(name);
  }
  
  /**
   * Updates all the drawables stored in the drawable manager.
   * Equivalent to calling {@code DrawableUI.draw()} on each drawable in the drawable manager.
   */
  public void update()
  {
      Iterator<DrawableUI> iterator = getIterator();
      while (iterator.hasNext())
      {
          iterator.next().draw();
      }
  }
  
  /**
   * Removes all drawables from the drawable manager.
   */
  @Override
  public void reset()
  {
      this.drawablesMap.clear();
  }
}