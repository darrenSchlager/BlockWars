import java.awt.Color;

public class Block
{
  private int x, y;  // coordinates of center of the block
  private String kind;  // the kind of block

  private int health;

  public Block( int xIn, int yIn, String k )
  {
    x=xIn;  y=yIn;
    kind=k;
    health = 12;
  }

  public Key getKey()
  {
    return new Key( x, y );
  }

  public String getKind()
  {
    return kind;
  }

  public void draw( Camera cam )
  {
    if( kind.equals("wall") )
      cam.setColor( Color.gray );
    else if( kind.equals("player") )
      cam.setColor( Color.blue );
    else if( kind.equals("monster") )
      cam.setColor( Color.red );
    else if( kind.equals("food") )
      cam.setColor( Color.green );

    cam.fillRect( x-0.5, y-0.5, 1, 1 );    
  }

  private void actuallyMove( int dx, int dy, SackBST sack )
  {
    sack.find( new Key( x, y ) );
    sack.remove();
    x += dx;  y += dy;
    sack.add( this );
  }

  public void move( int dx, int dy, SackBST sack )
  {
    if( ! sack.find( new Key( x+dx, y+dy ) ) )
    {// the location is empty
      actuallyMove( dx, dy, sack );
    }
    else
    {
      Block b = sack.get();
      if( b.getKind().equals( "food" ) )
      {
        health += 3;
        sack.remove();
        actuallyMove( dx, dy, sack );
      }
      else if( b.getKind().equals( "wall" ) )
      {
        health--;
      }
      else if( b.getKind().equals( "monster" ) )
      {
        health = 0;        
      }

    }
  }

  public int getHealth()
  {
    return health;
  }

  public int compareTo( Block other )
  {
    return this.getKey().compareTo( other.getKey() );
  }

}
