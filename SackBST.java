/*
  Darren Schlager
  Exercise 4
*/

import java.util.ArrayList;

public class SackBST
{
  private Node root;  // reference to the root node
  private int n;    // number of items

  // instance variables to store info from find:
  private Node parent;  // parent of the node where the key was found
  private boolean left; // key was found in the left child of parent?
    // note:  if parent==null after finding a key, the key was found
    //        as root.data

  private ArrayList<Block> travList;
  private int current;  // index of current item in the traversal list

  public SackBST()
  {
    n = 0;
    root = null;
  }

  public void add( Block b )
  {
    if( n == 0 )    // or    root==null
    {// adding first item
      root = new Node( b );
    }
    else
    {// already have at least the root node
      
      Node current = root;
      boolean done = false;

      while( !done )
      {
        if( b.compareTo( current.data ) < 0 )    
        {// b belongs on the left
          if( current.left != null )
            current = current.left;
          else
          {
            current.left = new Node( b );
            done = true;
          }
        }
        else
        {// b belongs on the right
          if( current.right != null )
            current = current.right;
          else
          {
            current.right = new Node( b );
            done = true;
          }
        }
      }
      
    }

    n++;

  }

  public int size()
  {
    return n;
  }

  public boolean find( Key key )
  {
    return find( root, null, false, key );    
  }

  // given start pointing to the root of a tree to be searched,
  //       previous pointing to the parent node of start,...
  //  (start or parent may be null)
  private boolean find( Node start, Node previous, boolean wentLeft,
                           Key key )
  {
    if( start == null )
    {
      return false;
    }
    else
    {
      if( start.data.getKey().compareTo( key ) == 0 )
      {// found it!
        parent = previous;
        left = wentLeft;
        return true;
      }
      else if( start.data.getKey().compareTo( key ) > 0 )
      {// if it's here, it's in the left subtree
        return find( start.left, start, true, key );
      }
      else
      {// if it's here, it's in the right subtree
        return find( start.right, start, false, key );
      }
    }

  }

  public Block get()
  {
    if( parent != null )
    {
      if( left )
      {
        return parent.left.data;
      }
      else
      {
        return parent.right.data;
      }
    }
    else
    {
      return root.data;
    }
  }

  public void remove()
    {
      if(n > 0)
      {
        //if root
        if(parent == null)
        {
  //System.out.println("\nroot");
          //has no children
          if(root.left == null && root.right == null)
          {
  //System.out.println("no children");
            root = null;   
          }
          //has only left child
          else if(root.left != null && root.right == null)
          {
  //System.out.println("left child");
            root = root.left;
          }
          //has only right child
          else if(root.left == null && root.right != null)
          {
  //System.out.println("right child");
            root = root.right;
          }
          //has two children
          else if(root.left != null && root.right != null)
          {
  //System.out.println("two children");
              Node current = root;
              if(current.left.right == null)
              {
                current.data = current.left.data;
                current.left = current.left.left;
              }
              else
              {
                current = current.left;
                while(current.right.right != null)
                  current = current.right;
                root.data = current.right.data;
                current.right = current.right.left;
              }
          }
        } 
        //if not root
        else if(parent != null)
        {
  //System.out.println("\nnot root");
          //left tree
          if(left)
          {
  //System.out.println("left tree");
            //has no children
            if(parent.left.left == null && parent.left.right == null)
            {
  //System.out.println("no children");
              parent.left = null;
            }
            //has only left child
            else if(parent.left.left != null && parent.left.right == null)
            {
  //System.out.println("left child");
              parent.left = parent.left.left;
            }
            //has only right child
            else if(parent.left.left == null && parent.left.right != null)
            {
  //System.out.println("right child");
              parent.left = parent.left.right;
            }
            //has two children
            else if(parent.left.left != null && parent.left.right != null)
            {
  //System.out.println("two children"); 
              Node current = parent.left;
              if(current.left.right == null)
              {
                current.data = current.left.data;
                current.left = current.left.left;
              }
              else
              {
                current = current.left;
                while(current.right.right != null)
                  current = current.right;
                parent.left.data = current.right.data;
                current.right = current.right.left;
              }
            }
          }
          //right tree
          else
          {
  //System.out.println("right tree");
            //has no children
            if(parent.right.left == null && parent.right.right == null)
            {
  //System.out.println("no children");
              parent.right = null;
            }
            //has only left child
            else if(parent.right.left != null && parent.right.right == null)
            {
  //System.out.println("left child");
              parent.right = parent.right.left;
            }
            //has only right child
            else if(parent.right.left == null && parent.right.right != null)
            {
  //System.out.println("right child");
              parent.right = parent.right.right;
            }
            //has two children
            else if(parent.right.left != null && parent.right.right != null)
            {
  //System.out.println("two children");
              Node current = parent.right;
              if(current.left.right == null)
              {
                current.data = current.left.data;
                current.left = current.left.left;
              }
              else
              {
                current = current.left;
                while(current.right.right != null)
                  current = current.right;
                parent.right.data = current.right.data;
                current.right = current.right.left;
              }
            }
          }
        }
        n--;
      }
    }

  public void initForTraverse()
  {
    // do pre-order traversal of the tree into travList:
    travList = new ArrayList<Block>();
    preorderTraverse( root );
    current= 0;
  }

  // add to travList all the items in the subtree rooted at start
  private void preorderTraverse( Node start )
  {
    if( start == null )
    {
      // nobody to add
    }
    else
    {
      travList.add( start.data );
      preorderTraverse( start.left );
      preorderTraverse( start.right );
    }
  }

  public boolean hasNext()
  {
    return current < travList.size();
  }

  public Block next()
  {
    Block temp = travList.get(current);
    current++;
    return temp;
  }

// -------------------- instance methods but not part of Sack ADT
  /*

  public void display( double x, double y, Camera cam, double spread,
                         double levelHeight )
  {
    int h = height( root );
    // System.out.println( "height is " + h );

    display( x, y, root, cam, Math.pow(2,h-1)*spread, levelHeight );
    
  }

  private void display( double x, double y, 
                        Node node, Camera cam, 
                        double width, double levelHeight )
  {
    if( node != null )
    {
      cam.drawCenteredText( node.data, x, y );

      double smidge1 = 0.75, smidge2 = 2.5;

      if( node.left != null )
        cam.drawLine( x, y-smidge1, x-width, y - levelHeight + smidge2 );

      if( node.right != null )
        cam.drawLine( x, y-smidge1, x+width, y - levelHeight + smidge2 );

      display( x - width, y - levelHeight, node.left, cam, 
                                           width/2, levelHeight );   
      display( x + width, y - levelHeight, node.right, cam, 
                                           width/2, levelHeight );   
    }
   
  }

  // find height of tree (number of levels in longest branch)
  // rooted at the given node
  public int height( Node node )
  {
    if( node == null )
      return 0;
    else if( node.left == null && node.right == null )
      return 1;
    else
    {
      int lh, rh;
      lh = height( node.left );
      rh = height( node.right );
      int h = 1 + (int) Math.max( lh, rh );
      return h;
    }

  }*/

  public static void main(String[] args)
  {
    SackBST stack = new SackBST();
    stack.add(new Block(2,1,"wall"));  
    stack.add(new Block(5,3,"wall")); 
    stack.add(new Block(3,5,"wall")); 
    stack.add(new Block(9,16,"wall")); 
    stack.add(new Block(21,11,"wall")); 
    stack.add(new Block(10,12,"wall")); 
    System.out.println(stack.find(new Key(2,1)));
    stack.remove();
    System.out.println(stack.find(new Key(2,1)));

    System.out.println(stack.find(new Key(5,3)));
    stack.remove();
    System.out.println(stack.find(new Key(5,3)));

    System.out.println(stack.find(new Key(3,5)));
    stack.remove();
    System.out.println(stack.find(new Key(3,5)));

    System.out.println(stack.find(new Key(9,16)));
    stack.remove();
    System.out.println(stack.find(new Key(9,16)));

    System.out.println(stack.find(new Key(21,11)));
    stack.remove();
    System.out.println(stack.find(new Key(21,11)));

    System.out.println(stack.find(new Key(10,12)));
    stack.remove();
    System.out.println(stack.find(new Key(10,12)));
    stack.remove();
  }

}
