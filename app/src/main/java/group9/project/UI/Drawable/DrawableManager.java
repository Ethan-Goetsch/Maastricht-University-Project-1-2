package group9.project.UI.Drawable;

import java.util.ArrayList;
import java.util.Iterator;

import group9.project.Utility.Interfaces.IResetable;

public class DrawableManager implements IResetable
{
  private ArrayList<IDrawable> drawables;

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
    drawables = new ArrayList<IDrawable>();
  }

  // adds a drawable to the drawables list
  public void add(IDrawable drawable)
  {
      this.drawables.add(drawable);
  }

  // returns true if the drawables list containt the argument drawable
  public boolean contains(IDrawable drawable)
  {
      return drawables.contains(drawable);
  }

  // removes a drawable from the list
  public void remove(IDrawable drawable)
  {
      drawables.remove(drawable);
  }

  // returns an iterator for the drawables list
  public Iterator<IDrawable> getIterator()
  {
      return drawables.iterator();
  }

  // resets all drawables by clearing the list of drawables
  @Override
  public void reset()
  {
    drawables.clear();
  }
}