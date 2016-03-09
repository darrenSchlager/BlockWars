import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.io.*;

public class BlockWars extends Basic
{
  private static Random randy = new Random(1);

  public static void main(String[] args)
  {
    // example: hard-coded location and size of window:
    BlockWars a = new BlockWars("Block Wars!!!", 0, 0, 440, 470);

  }

  // instance variables for the application:
  //------------------------------------------------------------------

  private SackBST sack;
  private Block player;

  //------------------------------------------------------------------

  public BlockWars( String title, int ulx, int uly, int pw, int ph )
  {
    super(title,ulx,uly,pw,ph);

    // code to initialize instance variables before animation begins:
    //------------------------------------------------------------------

    sack = new SackBST();

    player = new Block( 12, 12, "player" );
    sack.add( player );

    // generate some random blocks:

    makeBlocks( "wall", 50 );
    makeBlocks( "food", 10 );
    makeBlocks( "monster", 5 );

    // code to finish setting up entire window:
    //------------------------------------------------------------------
  
    setBackgroundColor( Color.black );

    // code to set up camera(s)
    //------------------------------------------------------------------
  
    cameras.add( new Camera( 20, 50, 400, 400,
                         0, 25, 0,
                         Color.white ) );

    //------------------------------------------------------------------
    // start up the animation:
    super.start();
  }

  private void makeBlocks( String k, int num )
  {
    for( int j=1; j<=num; j++ )
    {
      int x=randy.nextInt(25);
      int y=randy.nextInt(25);

      if( ! sack.find( new Key(x,y) ) )
      {
        sack.add( new Block( x, y, k ) );
      }
      else
      {
        System.out.println("got overlap");
        j--;
      }
    }

  }


  public void step()
  {
    if( player.getHealth() <= 0 )
    {
      Camera cam = cameras.get(0);
      cam.activate();
      cam.setColor( Color.red );
      cam.drawCenteredText( "You lose!", 12.5, 12.5 );
    }

    else
    {
      // code to update the world and display the world:
      //------------------------------------------------------------------
      // make monsters move randomly:
      
      makeBlocks( "monster", 1 );
      int num = 0;
      while(num < 7)
      {
        int x=randy.nextInt(25);
        int y=randy.nextInt(25);
        if( sack.find( new Key(x,y) ) && sack.get().getKind().compareTo("player") != 0 && sack.get().getKind().compareTo("wall") != 0 && sack.get().getKind().compareTo("food") != 0 )
        {
        sack.remove( );
        }
        num++;
      }
      
      //-----------------------------------------------------------
      Camera cam = cameras.get(0);
  
      cam.activate();
  
      sack.initForTraverse();
      while( sack.hasNext() )
      {
        Block b = sack.next();
        b.draw( cam );
      }
      //------------------------------------------------------------------
    }
  }

  public void keyTyped( KeyEvent e )
  {
    char key = e.getKeyChar();
    
    // code to change instance variables depending on key:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void keyPressed( KeyEvent e )
  {
    int code = e.getKeyCode();

    // code to change instance variables depending on which key pressed (code):
    //------------------------------------------------------------------

    if( code == KeyEvent.VK_LEFT )
    {
      player.move( -1, 0, sack );
    }
    else if( code == KeyEvent.VK_RIGHT )
    {
      player.move( 1, 0, sack );
    }
    else if( code == KeyEvent.VK_UP )
    {
      player.move( 0, 1, sack );
    }
    else if( code == KeyEvent.VK_DOWN )
    {
      player.move( 0, -1, sack );
    }

    //------------------------------------------------------------------
  }

  public void mouseMoved(MouseEvent e)
  {
    super.mouseMoved(e);

    // code to respond to mouse motion:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseDragged(MouseEvent e)
  {
    super.mouseDragged(e);

    // code to respond to mouse motion with a button pressed:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseClicked(MouseEvent e)
  {
    super.mouseClicked(e);

    // code to respond to mouse click:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mousePressed(MouseEvent e)
  {
    super.mousePressed(e);

    // code to respond to mouse button pressed:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseReleased(MouseEvent e)
  {
    super.mouseReleased(e);

    // code to respond to mouse button released:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseEntered(MouseEvent e)
  {
    super.mouseEntered(e);

    // code to respond to mouse entering window:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseExited(MouseEvent e)
  {
    super.mouseExited(e);

    // code to respond to mouse exiting window:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

}
