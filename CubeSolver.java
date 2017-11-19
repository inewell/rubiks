public class CubeSolver extends CubeGraphics
{
  public static int cubeSize = 3;

  public static int delay = 600;

  public CubeSolver(int s)
  {
    super(s, false);
  }

  private void update()
  {
    repaint();
    try {
      Thread.sleep(delay);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  public static char opposite(char c)
  {
    if (c == 'w')
      return 'y';
    else if (c == 'y')
      return 'w';
    else if (c == 'g')
      return 'b';
    else if (c == 'b')
      return 'g';
    else if (c == 'r')
      return 'o';
    else if (c == 'o')
      return 'r';

    else
      return ' ';
  }

  public static char clockwise(char c)
  {
    if (c == 'r')
      return 'g';
    else if (c == 'g')
      return 'o';
    else if (c == 'o')
      return 'b';
    else if (c == 'b')
      return 'r';
    else
      return ' ';
  }

  public void scramble(int n)
  {
    char[] sides = {'w', 'y', 'b', 'g', 'o', 'r'};
    for (int i = 1; i <= n; i++)
    {
      int depth = (int)(Math.random() * getS());
      int index = (int)(Math.random() * 6);
      char face = sides[index];
      int prime = (int)(Math.random()*2);
      if (prime == 0)
      {
        getCube().turn(face, depth);
        update();
      }
      else
      {
        getCube().turnP(face, depth);
        update();
      }
    }
  }

  public void scramble3by3(int n)
  {
    for (int i = 1; i <= n; i++)
    {
      char[] sides = {'w', 'y', 'b', 'g', 'r', 'o'};
      int index = (int)(Math.random()*sides.length);
      getCube().turn(sides[index]);
    }
  }

  private char[] findEdge(char color1, char color2)
  {
    char[] sides = new char[2];
    //check getCube().getWFace()
    for (int i = 1; i < getS()-1; i++)
    {
      if (getCube().getWFace()[0][i] == color1)
      {
        if (getCube().getGFace()[0][getS()-1-i] == color2)
        {
          sides[0] = 'w';
          sides[1] = 'g';
          return sides;
        }
      }
      else if (getCube().getWFace()[0][i] == color2)
      {
        if (getCube().getGFace()[0][getS()-1-i] == color1)
        {
          sides[0] = 'g';
          sides[1] = 'w';
          return sides;
        }
      }

      if (getCube().getWFace()[i][getS()-1] == color1)
      {
        if (getCube().getOFace()[0][getS()-1-i] == color2)
        {
          sides[0] = 'w';
          sides[1] = 'o';
          return sides;
        }
      }
      else if (getCube().getWFace()[i][getS()-1] == color2)
      {
        if (getCube().getOFace()[0][getS()-1-i] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'w';
          return sides;
        }
      }

      if (getCube().getWFace()[getS()-1][i] == color1)
      {
        if (getCube().getBFace()[0][i] == color2)
        {
          sides[0] = 'w';
          sides[1] = 'b';
          return sides;
        }
      }
      else if (getCube().getWFace()[getS()-1][i] == color2)
      {
        if (getCube().getBFace()[0][i] == color1)
        {
          sides[0] = 'b';
          sides[1] = 'w';
          return sides;
        }
      }

      if (getCube().getWFace()[i][0] == color1)
      {
        if (getCube().getRFace()[0][i] == color2)
        {
          sides[0] = 'w';
          sides[1] = 'r';
          return sides;
        }
      }
      else if (getCube().getWFace()[i][0] == color2)
      {
        if (getCube().getRFace()[0][i] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'w';
          return sides;
        }
      }
    }

    //check getCube().getYFace()
    for (int i = 1; i < getS()-1; i++)
    {
      if (getCube().getYFace()[0][i] == color1)
      {
        if (getCube().getGFace()[getS()-1][i] == color2)
        {
          sides[0] = 'y';
          sides[1] = 'g';
          return sides;
        }
      }
      else if (getCube().getYFace()[0][i] == color2)
      {
        if (getCube().getGFace()[getS()-1][i] == color1)
        {
          sides[0] = 'g';
          sides[1] = 'y';
          return sides;
        }
      }

      if (getCube().getYFace()[i][getS()-1] == color1)
      {
        if (getCube().getRFace()[getS()-1][i] == color2)
        {
          sides[0] = 'y';
          sides[1] = 'r';
          return sides;
        }
      }
      else if (getCube().getYFace()[i][getS()-1] == color2)
      {
        if (getCube().getRFace()[getS()-1][i] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'y';
          return sides;
        }
      }

      if (getCube().getYFace()[getS()-1][i] == color1)
      {
        if (getCube().getBFace()[getS()-1][getS()-1-i] == color2)
        {
          sides[0] = 'y';
          sides[1] = 'b';
          return sides;
        }
      }
      else if (getCube().getYFace()[getS()-1][i] == color2)
      {
        if (getCube().getBFace()[getS()-1][getS()-1-i] == color1)
        {
          sides[0] = 'b';
          sides[1] = 'y';
          return sides;
        }
      }

      if (getCube().getYFace()[i][0] == color1)
      {
        if (getCube().getOFace()[getS()-1][getS()-1-i] == color2)
        {
          sides[0] = 'y';
          sides[1] = 'o';
          return sides;
        }
      }
      else if (getCube().getYFace()[i][0] == color2)
      {
        if (getCube().getOFace()[getS()-1][getS()-1-i] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'y';
          return sides;
        }
      }
    }

    //check middle faces
    for (int i = 1; i < getS()-1; i++)
    {
      if (getCube().getGFace()[i][0] == color1)
      {
        if (getCube().getOFace()[i][getS()-1] == color2)
        {
          sides[0] = 'g';
          sides[1] = 'o';
          return sides;
        }
      }
      else if (getCube().getGFace()[i][0] == color2)
      {
        if (getCube().getOFace()[i][getS()-1] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'g';
          return sides;
        }
      }

      if (getCube().getGFace()[i][getS()-1] == color1)
      {
        if (getCube().getRFace()[i][0] == color2)
        {
          sides[0] = 'g';
          sides[1] = 'r';
          return sides;
        }
      }
      else if (getCube().getGFace()[i][getS()-1] == color2)
      {
        if (getCube().getRFace()[i][0] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'g';
          return sides;
        }
      }

      if (getCube().getBFace()[i][0] == color1)
      {
        if (getCube().getRFace()[i][getS()-1] == color2)
        {
          sides[0] = 'b';
          sides[1] = 'r';
          return sides;
        }
      }
      else if (getCube().getBFace()[i][0] == color2)
      {
        if (getCube().getRFace()[i][getS()-1] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'b';
          return sides;
        }
      }

      if (getCube().getBFace()[i][getS()-1] == color1)
      {
        if (getCube().getOFace()[i][0] == color2)
        {
          sides[0] = 'b';
          sides[1] = 'o';
          return sides;
        }
      }
      else if (getCube().getBFace()[i][getS()-1] == color2)
      {
        if (getCube().getOFace()[i][0] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'b';
          return sides;
        }
      }
    }
    return sides;
  }

  private char[] findCorner(char color1, char color2, char color3) //colors arranged in clockwise order
  {
    char[] sides = new char[3];
    //check getCube().getYFace()
    if (getCube().getYFace()[0][0] == color1)
    {
      if (getCube().getOFace()[getS()-1][getS()-1] == color2)
      {
        if (getCube().getGFace()[getS()-1][0] == color3)
        {
          sides[0] = 'y';
          sides[1] = 'o';
          sides[2] = 'g';
          return sides;
        }
      }
    }
    else if (getCube().getYFace()[0][0] == color2)
    {
      if (getCube().getOFace()[getS()-1][getS()-1] == color3)
      {
        if (getCube().getGFace()[getS()-1][0] == color1)
        {
          sides[0] = 'g';
          sides[1] = 'y';
          sides[2] = 'o';
        }
      }
    }
    else if (getCube().getYFace()[0][0] == color3)
    {
      if (getCube().getOFace()[getS()-1][getS()-1] == color1)
      {
        if (getCube().getGFace()[getS()-1][0] == color2)
        {
          sides[0] = 'o';
          sides[1] = 'g';
          sides[2] = 'y';
        }
      }
    }

    if (getCube().getYFace()[0][getS()-1] == color1)
    {
      if (getCube().getGFace()[getS()-1][getS()-1] == color2)
      {
        if (getCube().getRFace()[getS()-1][0] == color3)
        {
          sides[0] = 'y';
          sides[1] = 'g';
          sides[2] = 'r';
          return sides;
        }
      }
    }
    else if (getCube().getYFace()[0][getS()-1] == color2)
    {
      if (getCube().getGFace()[getS()-1][getS()-1] == color3)
      {
        if (getCube().getRFace()[getS()-1][0] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'y';
          sides[2] = 'g';
          return sides;
        }
      }
    }
    else if (getCube().getYFace()[0][getS()-1] == color3)
    {
      if (getCube().getGFace()[getS()-1][getS()-1] == color1)
      {
        if (getCube().getRFace()[getS()-1][0] == color2)
        {
          sides[0] = 'g';
          sides[1] = 'r';
          sides[2] = 'y';
          return sides;
        }
      }
    }

    if (getCube().getYFace()[getS()-1][getS()-1] == color1)
    {
      if (getCube().getRFace()[getS()-1][getS()-1] == color2)
      {
        if (getCube().getBFace()[getS()-1][0] == color3)
        {
          sides[0] = 'y';
          sides[1] = 'r';
          sides[2] = 'b';
          return sides;
        }
      }
    }
    else if (getCube().getYFace()[getS()-1][getS()-1] == color2)
    {
      if (getCube().getRFace()[getS()-1][getS()-1] == color3)
      {
        if (getCube().getBFace()[getS()-1][0] == color1)
        {
          sides[0] = 'b';
          sides[1] = 'y';
          sides[2] = 'r';
          return sides;
        }
      }
    }
    else if (getCube().getYFace()[getS()-1][getS()-1] == color3)
    {
      if (getCube().getRFace()[getS()-1][getS()-1] == color1)
      {
        if (getCube().getBFace()[getS()-1][0] == color2)
        {
          sides[0] = 'r';
          sides[1] = 'b';
          sides[2] = 'y';
          return sides;
        }
      }
    }

    if (getCube().getYFace()[getS()-1][0] == color1)
    {
      if (getCube().getBFace()[getS()-1][getS()-1] == color2)
      {
        if (getCube().getOFace()[getS()-1][0] == color3)
        {
          sides[0] = 'y';
          sides[1] = 'b';
          sides[2] = 'o';
          return sides;
        }
      }
    }
    else if (getCube().getYFace()[getS()-1][0] == color2)
    {
      if (getCube().getBFace()[getS()-1][getS()-1] == color3)
      {
        if (getCube().getOFace()[getS()-1][0] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'y';
          sides[2] = 'b';
          return sides;
        }
      }
    }
    else if (getCube().getYFace()[getS()-1][0] == color3)
    {
      if (getCube().getBFace()[getS()-1][getS()-1] == color1)
      {
        if (getCube().getOFace()[getS()-1][0] == color2)
        {
          sides[0] = 'b';
          sides[1] = 'o';
          sides[2] = 'y';
          return sides;
        }
      }
    }

    //check getCube().getWFace()
    if (getCube().getWFace()[0][0] == color1)
    {
      if (getCube().getRFace()[0][0] == color2)
      {
        if (getCube().getGFace()[0][getS()-1] == color3)
        {
          sides[0] = 'w';
          sides[1] = 'r';
          sides[2] = 'g';
          return sides;
        }
      }
    }
    else if (getCube().getWFace()[0][0] == color2)
    {
      if (getCube().getRFace()[0][0] == color3)
      {
        if (getCube().getGFace()[0][getS()-1] == color1)
        {
          sides[0] = 'g';
          sides[1] = 'w';
          sides[2] = 'r';
          return sides;
        }
      }
    }
    else if (getCube().getWFace()[0][0] == color3)
    {
      if (getCube().getRFace()[0][0] == color1)
      {
        if (getCube().getGFace()[0][getS()-1] == color2)
        {
          sides[0] = 'r';
          sides[1] = 'g';
          sides[2] = 'w';
          return sides;
        }
      }
    }
    if (getCube().getWFace()[0][getS()-1] == color1)
    {
      if (getCube().getGFace()[0][0] == color2)
      {
        if (getCube().getOFace()[0][getS()-1] == color3)
        {
          sides[0] = 'w';
          sides[1] = 'g';
          sides[2] = 'o';
          return sides;
        }
      }
    }
    else if (getCube().getWFace()[0][getS()-1] == color2)
    {
      if (getCube().getGFace()[0][0] == color3)
      {
        if (getCube().getOFace()[0][getS()-1] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'w';
          sides[2] = 'g';
          return sides;
        }
      }
    }
    else if (getCube().getWFace()[0][getS()-1] == color3)
    {
      if (getCube().getGFace()[0][0] == color1)
      {
        if (getCube().getOFace()[0][getS()-1] == color2)
        {
          sides[0] = 'g';
          sides[1] = 'o';
          sides[2] = 'w';
          return sides;
        }
      }
    }
    if (getCube().getWFace()[getS()-1][getS()-1] == color1)
    {
      if (getCube().getOFace()[0][0] == color2)
      {
        if (getCube().getBFace()[0][getS()-1] == color3)
        {
          sides[0] = 'w';
          sides[1] = 'o';
          sides[2] = 'b';
          return sides;
        }
      }
    }
    else if (getCube().getWFace()[getS()-1][getS()-1] == color2)
    {
      if (getCube().getOFace()[0][0] == color3)
      {
        if (getCube().getBFace()[0][getS()-1] == color1)
        {
          sides[0] = 'b';
          sides[1] = 'w';
          sides[2] = 'o';
          return sides;
        }
      }
    }
    else if (getCube().getWFace()[getS()-1][getS()-1] == color3)
    {
      if (getCube().getOFace()[0][0] == color1)
      {
        if (getCube().getBFace()[0][getS()-1] == color2)
        {
          sides[0] = 'o';
          sides[1] = 'b';
          sides[2] = 'w';
          return sides;
        }
      }
    }
    if (getCube().getWFace()[getS()-1][0] == color1)
    {
      if (getCube().getBFace()[0][0] == color2)
      {
        if (getCube().getRFace()[0][getS()-1] == color3)
        {
          sides[0] = 'w';
          sides[1] = 'b';
          sides[2] = 'r';
          return sides;
        }
      }
    }
    else if (getCube().getWFace()[getS()-1][0] == color2)
    {
      if (getCube().getBFace()[0][0] == color3)
      {
        if (getCube().getRFace()[0][getS()-1] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'w';
          sides[2] = 'b';
          return sides;
        }
      }
    }
    else if (getCube().getWFace()[getS()-1][0] == color3)
    {
      if (getCube().getBFace()[0][0] == color1)
      {
        if (getCube().getRFace()[0][getS()-1] == color2)
        {
          sides[0] = 'b';
          sides[1] = 'r';
          sides[2] = 'w';
          return sides;
        }
      }
    }
    return sides;
  }

  private void correctCenters()
  {
    if (getCube().getYFace()[getS()/2][getS()/2] == 'w')
    {
      getCube().turnAll('r');
      update();
      getCube().turnAll('r');
      update();
    }
    else if (getCube().getRFace()[getS()/2][getS()/2] == 'w')
    {
      getCube().turnAll('b');
      update();
    }
    else if (getCube().getGFace()[getS()/2][getS()/2] == 'w')
    {
      getCube().turnAll('r');
      update();
    }
    else if (getCube().getOFace()[getS()/2][getS()/2] == 'w')
    {
      getCube().turnAll('g');
      update();
    }
    else if (getCube().getBFace()[getS()/2][getS()/2] == 'w')
    {
      getCube().turnAll('o');
      update();
    }

    char rc = getCube().getRFace()[getS()/2][getS()/2];
    if (rc == 'o')
    {
      getCube().turnAll('w');
      update();
      getCube().turnAll('w');
      update();
    }
    else if (rc == 'g')
    {
      getCube().turnAll('w');
    }
    else if (rc == 'b')
    {
      getCube().turnAll('y');
      update();
    }
  }

  private char findClearEdge(char face)
  {
    if (face == 'w' || face == 'y')
    {
      char[] adj = {'r', 'g', 'o', 'b'};
      for (char c : adj)
      {
        if (isClear(face, c))
          return c;
      }
    }
    else if (face == 'r' || face == 'o')
    {
      char[] adj = {'w', 'g', 'y', 'b'};
      for (char c : adj)
      {
        if (isClear(face, c))
          return c;
      }
    }
    else
    {
      char[] adj = {'r', 'w', 'o', 'y'};
      for (char c : adj)
      {
        if (isClear(face, c))
          return c;
      }
    }
    return ' ';
  }

  public boolean isClear(char color1, char color2)
  {
    char[] p1 = findEdge(color1, color2);
    return p1[0] != color1 || p1[1] != color2;
  }

  public boolean isInPlace(char color1, char color2)
  {
    char[] p1 = findEdge(color1, color2);
    return p1[0] == color1 && p1[1] == color2;
  }

  public boolean isInPlace(char color1, char color2, char color3)
  {
    char[] p1 = findCorner(color1, color2, color3);
    return (p1[0] == color1 && p1[1] == color2 && p1[2] == color3);
  }

  public char isAdjacent(char color2, char color3)
  {
    char[] e = findEdge(color2, color3);
    char[] c = findCorner('w', color2, color3);

    char sideFace = ' ';
    if (e[0] == 'y')
      sideFace = e[1];
    else
      sideFace = e[0];
    for (char ch : c)
      if (ch == sideFace)
        return sideFace;
    return 'n';
  }

  private void insertEdge(char color2) //color1 = white
  {
    char[] p1 = findEdge('w', color2);

    if (p1[0] == 'w' && p1[1] == color2)
    {
      //System.out.println("edge " + color2 + " already in place");
      return;
    }

    if (p1[0] == 'w' || p1[0] == 'y')
    {
      if (p1[0] == 'w')
      {
        //System.out.println("placing edge " + color2 + " up from top");
        getCube().turn(p1[1]);
        update();
        getCube().turn(p1[1]);
        update();
      }
      //else
        //System.out.println("placing edge " + color2 + " up from bottom");
      if (color2 == opposite(p1[1]))
      {
        getCube().turn('y');
        update();
        getCube().turn('y');
        update();
      }
      else if (color2 == clockwise(p1[1]))
      {
        getCube().turnP('y');
        update();
      }
      else if (color2 == opposite(clockwise(p1[1])))
      {
        getCube().turn('y');
        update();
      }
      getCube().turn(color2);
      update();
      getCube().turn(color2);
      update();
      //System.out.println("placing edge " + color2 + " up from bottom");
      //if (isInPlace('w', color2))
        //System.out.println("now corrected");
      return;
    }
    else
    {
      if (p1[1] == 'y' || p1[1] == 'w')
      {
        if (p1[1] == 'w')
        {
          getCube().turn(p1[0]);
          update();
          getCube().turn(p1[0]);
          update();
          p1[1] = 'y';
          //System.out.println("moving edge " + color2 + " from flipped top");
        }
        //else
          //System.out.println("moving edge " + color2 + " from flipped bottom");
        if (color2 == opposite(p1[0]))
        {
          getCube().turn('y');
          update();
        }
        else if (color2 == clockwise(p1[0]))
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        else if (color2 == p1[0])
        {
          getCube().turnP('y');
          update();
        }
        getCube().turnP(clockwise(color2));
        update();
        getCube().turn(color2);
        update();
        getCube().turn(clockwise(color2));
        update();
        //if (isInPlace('w', color2))
          //System.out.println("corrected");
        return;
      }
      else
      {
        if (p1[0] == clockwise(p1[1]))
        {
          //System.out.println("turning down clockwise");
          getCube().turnP(p1[1]);
          update();
          getCube().turn('y');
          update();
          getCube().turn(p1[1]);
          update();
        }
        else
        {
          //System.out.println("turning down counterclockwise");
          getCube().turn(p1[1]);
          update();
          getCube().turn('y');
          update();
          getCube().turnP(p1[1]);
          update();
        } //inserts into bottom layer
        p1[1] = opposite(clockwise(p1[1]));
        if (color2 == opposite(p1[1]))
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        else if (color2 == clockwise(p1[1]))
        {
          getCube().turnP('y');
          update();
        }
        else if (color2 == opposite(clockwise(p1[1])))
        {
          getCube().turn('y');
          update();
        }
        getCube().turn(color2);
        update();
        getCube().turn(color2);
        update();
        //if (isInPlace('w', color2))
          //System.out.println("corrected");
      }
    }
  }

  private void makeWCross()
  {
    insertEdge('r');
    insertEdge('g');
    insertEdge('o');
    insertEdge('b');
  }

  private void insertPair(char color2, char color3) //color1 = white
  {
    char[] e = findEdge(color2, color3);
    char[] c = findCorner('w', color2, color3);

    if (e[0] == color2 && e[1] == color3)
    {
      if (c[0] == 'w' && c[1] == color2 && c[2] == color3)
        return;
    }

    if (e[0] != 'y' && e[1] != 'y') //if edge is not in the top layer
    {
      if (e[0] == clockwise(e[1]))
      {
        getCube().turn(e[0]);
        update();
        getCube().turn('y');
        update();
        getCube().turnP(e[0]);
        update();
      }
      else
      {
        getCube().turn(e[1]);
        update();
        getCube().turn('y');
        update();
        getCube().turnP(e[1]);
        update();
      }
      e = findEdge(color2, color3);
      c = findCorner('w', color2, color3);
    }

    if (c[0] != 'y' && c[1] != 'y' && c[2] != 'y') //if corner is not in the top layer
    {
      if (c[0] == 'w')
      {
        if (c[1] == clockwise(c[2]))
        {
          if (e[0] == c[1] || e[1] == c[1]) //if edge, on top, is on the same face
          {
            getCube().turn('y');
            update();
          }
          getCube().turnP('y');
          update();
          getCube().turn(c[1]);
          update();
          getCube().turn('y');
          update();
          getCube().turnP(c[1]);
          update();
        }
        else
        {
          if (e[0] == c[2] || e[1] == c[2]) //if edge, on top, is on the same face
          {
            getCube().turn('y');
            update();
          }
          getCube().turnP('y');
          update();
          getCube().turn(c[2]);
          update();
          getCube().turn('y');
          update();
          getCube().turnP(c[2]);
          update();
        }
      }
      else if (c[1] == 'w')
      {
        if (c[0] == clockwise(c[2]))
        {
          if (e[0] == c[0] || e[1] == c[0])
          {
            getCube().turn('y');
            update();
          }
          getCube().turnP('y');
          update();
          getCube().turn(c[0]);
          update();
          getCube().turn('y');
          update();
          getCube().turnP(c[0]);
          update();
        }
        else
        {
          if (e[0] == c[2] || e[1] == c[2])
          {
            getCube().turn('y');
            update();
          }
          getCube().turnP('y');
          update();
          getCube().turn(c[2]);
          update();
          getCube().turn('y');
          update();
          getCube().turnP(c[2]);
          update();
        }
      }
      else //if c[2] == 'w'
      {
        if (c[0] == clockwise(c[1]))
        {
          if (e[0] == c[0] || e[1] == c[0])
          {
            getCube().turn('y');
            update();
          }
          getCube().turnP('y');
          update();
          getCube().turn(c[0]);
          update();
          getCube().turn('y');
          update();
          getCube().turnP(c[0]);
          update();
        }
        else
        {
          if (e[0] == c[1] || e[1] == c[1])
          {
            getCube().turn('y');
            update();
          }
          getCube().turnP('y');
          update();
          getCube().turn(c[1]);
          update();
          getCube().turn('y');
          update();
          getCube().turnP(c[1]);
          update();
        }
      }
      e = findEdge(color2, color3);
      c = findCorner('w', color2, color3);
    }

    char adjFace = isAdjacent(color2, color3);
    if (adjFace != 'n') //if edge pair is adjacent
    {
      //System.out.println("adjacent");
      if (e[0] != 'y') //color2 is on the side
      {
        if (e[0] == opposite(color2))
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        else if (e[0] == clockwise(color2))
        {
          getCube().turn('y');
          update();
        }
        else if (e[0] == opposite(clockwise(color2)))
        {
          getCube().turnP('y');
          update();
        }
        e[0] = color2;
      }
      else //color3 is on the side
      {
        if (e[1] == opposite(color3))
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        else if (e[1] == clockwise(color3))
        {
          getCube().turn('y');
          update();
        }
        else if (e[1] == opposite(clockwise(color3)))
        {
          getCube().turnP('y');
          update();
        }
        e[1] = color3;
      }
      c = findCorner('w', color2, color3);

      if (e[0] == 'y' && c[1] == 'y' && c[2] == color3) //|| e[1] == 'y' && c[2] == 'y') //if already paired
      {
        //System.out.println("already paired 1");
        getCube().turn('y');
        update();
        getCube().turn(color3);
        update();
        getCube().turnP('y');
        update();
        getCube().turnP(color3);
        update();
        //if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
          //System.out.println("FAILED");
        return;
      }
      else if (e[1] == 'y' && c[2] == 'y' && c[1] == color2)
      {
        //System.out.println("already paired 2");
        getCube().turnP('y');
        update();
        getCube().turnP(color2);
        update();
        getCube().turn('y');
        update();
        getCube().turn(color2);
        update();
        //if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
          //System.out.println("FAILED");
        return;
      }

      //e = findEdge(color2, color3);
      c = findCorner('w', color2, color3);
      //separate
      if (e[0] == 'y') //color3 on side
      {
        if (c[0] == color2 || c[1] == color2 || c[2] == color2)
        {
          getCube().turnP('y');
          update();
          getCube().turn(color3);
          update();
          getCube().turnP('y');
          update();
          getCube().turnP(color3);
          update();
        }
        else
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
          getCube().turnP(color2);
          update();
          getCube().turn('y');
          update();
          getCube().turn(color2);
          update();
        }
        /*if (isAdjacent(color2, color3) != 'n')
        {
          System.out.println("adjacent splitter failed 1");
          adjacentSplitterFailed = true;
        }*/
      }
      else //color2 on side
      {
        if (c[0] == color3 || c[1] == color3 || c[2] == color3)
        {
          getCube().turn('y');
          update();
          getCube().turnP(color2);
          update();
          getCube().turn('y');
          update();
          getCube().turn(color2);
          update();
        }
        else
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
          getCube().turn(color3);
          update();
          getCube().turnP('y');
          update();
          getCube().turnP(color3);
          update();
        }
        /*if (isAdjacent(color2, color3) != 'n')
        {
          System.out.println("adjacent splitter failed 2");
          adjacentSplitterFailed = true;
        }*/
      }
      e = findEdge(color2, color3);
      c = findCorner('w', color2, color3);
    }

    if (c[0] == 'y') //if white side of corner is on top
    {
      if (e[0] != 'y') //color2 is on the side
      {
        if (e[0] == opposite(color2))
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        else if (e[0] == clockwise(color2))
        {
          getCube().turn('y');
          update();
        }
        else if (e[0] == opposite(clockwise(color2)))
        {
          getCube().turnP('y');
          update();
        }
        e[0] = color2;
        c = findCorner('w', color2, color3);
        getCube().turnP(color2);
        update();
        if (c[1] == color3 || c[2] == color3)
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        else
        {
          getCube().turnP('y');
          update();
        }
        getCube().turn(color2);
        update();
        getCube().turnP('y');
        update();
        getCube().turnP(color2);
        update();
        getCube().turn('y');
        update();
        getCube().turn(color2);
        update();
        /*System.out.println("white on top");
        if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
          System.out.println("FAILED");*/
        return;
      }
      else //color3 is on the side
      {
        if (e[1] == opposite(color3))
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        else if (e[1] == clockwise(color3))
        {
          getCube().turn('y');
          update();
        }
        else if (e[1] == opposite(clockwise(color3)))
        {
          getCube().turnP('y');
          update();
        }
        e[1] = color3;
        c = findCorner('w', color2, color3);
        getCube().turn(color3);
        update();
        if (c[1] == color2 || c[2] == color2)
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        else
        {
          getCube().turn('y');
          update();
        }
        getCube().turnP(color3);
        update();
        getCube().turn('y');
        update();
        getCube().turn(color3);
        update();
        getCube().turnP('y');
        update();
        getCube().turnP(color3);
        update();
        /*System.out.println("white on top 2");
        if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
          System.out.println("FAILED");*/
        return;
      }
    }

    else if (c[1] == e[1] || c[2] == e[0]) //if mismatched on top
    {
      if (e[0] != 'y') // if color2 on side
      {
        if (c[0] == opposite(e[0]))
        {
          if (c[0] == opposite(color3))
          {
            getCube().turn('y');
            update();
            getCube().turn('y');
            update();
          }
          else if (c[0] == clockwise(color3))
          {
            getCube().turn('y');
            update();
          }
          else if (c[0] == opposite(clockwise(color3)))
          {
            getCube().turnP('y');
            update();
          }
          getCube().turn(color3);
          update();
          getCube().turnP('y');
          update();
          getCube().turnP(color3);
          update();
          getCube().turn('y');
          update();
          getCube().turnP(color2);
          update();
          getCube().turnP('y');
          update();
          getCube().turn(color2);
          update();
          /*System.out.println("opp color place 1");
          if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
            System.out.println("FAILED");*/
          return;
        }
        else
        {
          if (c[0] == opposite(color2))
          {
            getCube().turn('y');
            update();
            getCube().turn('y');
            update();
          }
          else if (c[0] == clockwise(color2))
          {
            getCube().turn('y');
            update();
          }
          else if (c[0] == opposite(clockwise(color2)))
          {
            getCube().turnP('y');
            update();
          }
          getCube().turnP(color2);
          update();
          getCube().turnP('y');
          update();
          getCube().turn(color2);
          update();
          /*System.out.println("opp color place 2");
          if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
            System.out.println("FAILED");*/
          return;
        }
      }
      else //color3 on side
      {
        if (c[0] == opposite(e[1]))
        {
          if (c[0] == opposite(color2))
          {
            getCube().turn('y');
            update();
            getCube().turn('y');
            update();
          }
          else if (c[0] == clockwise(color2))
          {
            getCube().turn('y');
            update();
          }
          else if (c[0] == opposite(clockwise(color2)))
          {
            getCube().turnP('y');
            update();
          }
          getCube().turnP(color2);
          update();
          getCube().turn('y');
          update();
          getCube().turn(color2);
          update();
          getCube().turnP('y');
          update();
          getCube().turn(color3);
          update();
          getCube().turn('y');
          update();
          getCube().turnP(color3);
          update();
          /*System.out.println("opp color place 3");
          if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
            System.out.println("FAILED");*/
          return;
        }
        else
        {
          if (c[0] == opposite(color3))
          {
            getCube().turn('y');
            update();
            getCube().turn('y');
            update();
          }
          else if (c[0] == clockwise(color3))
          {
            getCube().turn('y');
            update();
          }
          else if (c[0] == opposite(clockwise(color3)))
          {
            getCube().turnP('y');
            update();
          }
          getCube().turn(color3);
          update();
          getCube().turn('y');
          update();
          getCube().turnP(color3);
          update();
          /*System.out.println("opp color place 4");
          if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
            System.out.println("FAILED");*/
          return;
        }
      }
    }

    else //if same color on top
    {
      if (e[0] != 'y') //color2 on side
      {
        if (c[0] == opposite(color2))
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        else if (c[0] == clockwise(color2))
        {
          getCube().turn('y');
          update();
        }
        else if (c[0] == opposite(clockwise(color2)))
        {
          getCube().turnP('y');
          update();
        }
        getCube().turnP(color2);
        update();
        if (e[0] == opposite(c[0]))
        {
          getCube().turnP('y');
          update();
        }
        else
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        getCube().turn(color2);
        update();
        getCube().turn('y');
        update();
        getCube().turn('y');
        update();
        getCube().turnP(color2);
        update();
        getCube().turn('y');
        update();
        getCube().turn(color2);
        update();
        /*System.out.println("same color place 1");
        if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
          System.out.println("FAILED");*/
        return;
      }
      else //color3 on side
      {
        if (c[0] == opposite(color3))
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        else if (c[0] == clockwise(color3))
        {
          getCube().turn('y');
          update();
        }
        else if (c[0] == opposite(clockwise(color3)))
        {
          getCube().turnP('y');
          update();
        }
        getCube().turn(color3);
        update();
        if (e[1] == opposite(c[0]))
        {
          getCube().turn('y');
          update();
        }
        else
        {
          getCube().turn('y');
          update();
          getCube().turn('y');
          update();
        }
        getCube().turnP(color3);
        update();
        getCube().turn('y');
        update();
        getCube().turn('y');
        update();
        getCube().turn(color3);
        update();
        getCube().turnP('y');
        update();
        getCube().turnP(color3);
        update();
        /*System.out.println("same color place 2");
        if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
          System.out.println("FAILED");*/
        return;
      }
    }
  }

  private void f2l()
  {
    insertPair('r', 'g');
    //if (!isInPlace('r', 'g') || !isInPlace('w', 'r', 'g'))
      //System.out.println("Something went wrong: w, r, g");
    insertPair('g', 'o');
    //if (!isInPlace('g', 'o') || !isInPlace('w', 'g', 'o'))
      //System.out.println("Something went wrong: w, g, o");
    insertPair('o', 'b');
    //if (!isInPlace('o', 'b') || !isInPlace('w', 'o', 'b'))
      //System.out.println("Something went wrong: w, o, b");
    insertPair('b', 'r');
    //if (!isInPlace('b', 'r') || !isInPlace('w', 'b', 'r'))
      //System.out.println("Something went wrong: w, b, r");
  }

  private void enact(String algorithm, char uFace, char fFace, char rFace)
  {
    for (int i = 0; i < algorithm.length(); i++)
    {
      char c = algorithm.charAt(i);
      if (c == 'u')
        getCube().turn(uFace);
      else if (c == 'U')
        getCube().turnP(uFace);
      else if (c == 'f')
        getCube().turn(fFace);
      else if (c == 'F')
        getCube().turnP(fFace);
      else if (c == 'r')
        getCube().turn(rFace);
      else if (c == 'R')
        getCube().turnP(rFace);
      else if (c == 'd')
        getCube().turn(opposite(uFace));
      else if (c == 'D')
        getCube().turnP(opposite(uFace));
      else if (c == 'b')
        getCube().turn(opposite(fFace));
      else if (c == 'B')
        getCube().turnP(opposite(fFace));
      else if (c == 'l')
        getCube().turn(opposite(rFace));
      else if (c == 'L')
        getCube().turnP(opposite(rFace));
      else if (c == 'x')
        getCube().turnAll(rFace);
      else if (c == 'X')
        getCube().turnPAll(rFace);
      else if (c == 'y')
        getCube().turnAll(uFace);
      else if (c == 'Y')
        getCube().turnPAll(uFace);
      else if (c == 'z')
        getCube().turnAll(fFace);
      else if (c == 'Z')
        getCube().turnPAll(fFace);
      else if (c == 'm')
      {
        getCube().turnAll(rFace);
        getCube().turnP(rFace);
        getCube().turn(opposite(rFace));
      }
      else if (c == 'M')
      {
        getCube().turnPAll(rFace);
        getCube().turn(rFace);
        getCube().turnP(opposite(rFace));
      }
      update();
    }
  }

  private void makeYCross()
  {
    for (int i = 1; i <= 4; i++)
    {
      if (getCube().getYFace()[0][1] != 'y' && getCube().getYFace()[getS()-1][1] != 'y')
      {
        getCube().turn('b');
        update();
        getCube().turn('r');
        update();
        getCube().turn('y');
        update();
        getCube().turnP('r');
        update();
        getCube().turnP('y');
        update();
        getCube().turnP('b');
        update();
        if (getCube().getYFace()[0][1] == 'y' && getCube().getYFace()[1][0] == 'y')
          return;
        else
        {
          for (int d = 0; d < getS()-1; d++)
            getCube().turn('b', d);
          update();
          getCube().turn('r');
          update();
          getCube().turn('y');
          update();
          getCube().turnP('r');
          update();
          getCube().turnP('y');
          update();
          for (int d = 0; d < getS()-1; d++)
            getCube().turnP('b', d);
          update();
          return;
        }
      }
      if (getCube().getYFace()[0][1] != 'y' && getCube().getYFace()[1][0] != 'y')
      {
        for (int d = 0; d < getS()-1; d++)
          getCube().turn('b', d);
        update();
        getCube().turn('r');
        update();
        getCube().turn('y');
        update();
        getCube().turnP('r');
        update();
        getCube().turnP('y');
        update();
        for (int d = 0; d < getS()-1; d++)
          getCube().turnP('b', d);
        update();
        return;
      }

      getCube().turn('y');
      update();
    }
  }

  private int numYellowCorners()
  {
    int num = 0;
    if (getCube().getYFace()[0][0] == 'y')
      num++;
    if (getCube().getYFace()[0][getS()-1] == 'y')
      num++;
    if (getCube().getYFace()[getS()-1][getS()-1] == 'y')
      num++;
    if (getCube().getYFace()[getS()-1][0] == 'y')
      num++;
    return num;
  }

  private void makeTopYellow()
  {
    int nYCorners = numYellowCorners();

    if (nYCorners == 4)
      return;

    if (nYCorners == 0)
    {
      //System.out.println("0 corners yellow");
      for (int i = 1; i <= 4; i++)
      {
        if (getCube().getOFace()[getS()-1][0] == 'y' && getCube().getOFace()[getS()-1][getS()-1] == 'y' && getCube().getRFace()[getS()-1][0] == 'y' && getCube().getRFace()[getS()-1][getS()-1] == 'y')
        {
          enact("ruRurURuruuR", 'y', 'b', 'r');
          //System.out.println("case 1");
          return;
        }
        else if (getCube().getOFace()[getS()-1][0] == 'y' && getCube().getOFace()[getS()-1][getS()-1] == 'y' && getCube().getGFace()[getS()-1][getS()-1] == 'y' && getCube().getBFace()[getS()-1][0] == 'y')
        {
          enact("ruurrUrrUrruur", 'y', 'b', 'r');
          //System.out.println("case 2");
          return;
        }
        getCube().turn('y');
        update();
      }
    }
    else if (nYCorners == 1)
    {
      //System.out.println("1 corner yellow");
      for (int i = 1; i <= 4; i++)
      {
        if (getCube().getYFace()[getS()-1][getS()-1] == 'y' && getCube().getOFace()[getS()-1][0] == 'y')
        {
          enact("RuuruRur", 'y', 'b', 'r');
          //System.out.println("case 1");
          return;
        }
        else if (getCube().getYFace()[0][getS()-1] == 'y' && getCube().getOFace()[getS()-1][getS()-1] == 'y')
        {
          enact("ruuRUrUR", 'y', 'b', 'r');
          //System.out.println("case 2");
          return;
        }
        getCube().turn('y');
        update();
      }
    }
    else if (nYCorners == 2)
    {
      //System.out.println("2 corners yellow");
      if (getCube().getYFace()[0][0] == getCube().getYFace()[getS()-1][getS()-1] || getCube().getYFace()[0][getS()-1] == getCube().getYFace()[getS()-1][0])
      {
        for (int i = 1; i <= 4; i++)
        {
          if (getCube().getYFace()[0][getS()-1] == 'y' && getCube().getOFace()[getS()-1][getS()-1] == 'y')
          {
            enact("FlxuRULXfr", 'y', 'b', 'r');
            //System.out.println("case 1");
            return;
          }
          getCube().turn('y');
          update();
        }
      }
      else
      {
        for (int i = 1; i <= 4; i++)
        {
          if (getCube().getGFace()[getS()-1][0] == 'y' && getCube().getBFace()[getS()-1][getS()-1] == 'y')
          {
            enact("lxuRULXfrF", 'y', 'b', 'r');
            //System.out.println("case 2");
            return;
          }
          else if (getCube().getBFace()[getS()-1][0] == 'y' && getCube().getBFace()[getS()-1][getS()-1] == 'y')
          {
            enact("rrdRuurDRuuR", 'y', 'b', 'r');
            //System.out.println("case 3");
            return;
          }
          getCube().turn('y');
          update();
        }
      }
    }
  }

  private void permuteCorners()
  {
    for (int i = 1; i <= 4; i++)
    {
      if (isInPlace('y', 'o', 'g') && isInPlace('y', 'r', 'b'))
      {
        if (isInPlace('y', 'g', 'r') && isInPlace('y', 'b', 'o'))
          return;
        enact("frURUruRFruRURfrF", 'y', 'b', 'r');
        return;
      }
      if (isInPlace('y', 'g', 'r') && isInPlace('y', 'b', 'o'))
      {
        getCube().turn('y');
        enact("frURUruRFruRURfrF", 'y', 'b', 'r');
        return;
      }
      getCube().turn('y');
      update();
    }
    for (int i = 1; i <= 4; i++)
    {
      if (getCube().getRFace()[getS()-1][0] == getCube().getRFace()[getS()-1][getS()-1])
      {
        enact("xrrddruRddrUrX", 'y', 'b', 'r');
        return;
      }
      getCube().turn('y');
      update();
    }
  }

  private void permuteEdges()
  {
    for (int i = 1; i <= 4; i++)
    {
      if (getCube().getBFace()[getS()-1][1] == getCube().getBFace()[getS()-1][0] && getCube().getOFace()[getS()-1][1] == clockwise(getCube().getGFace()[getS()-1][1]) && getCube().getGFace()[getS()-1][1] == opposite(getCube().getRFace()[getS()-1][1]) && getCube().getRFace()[getS()-1][1] == clockwise(getCube().getOFace()[getS()-1][1]))
      {
        //System.out.println("clockwise cycle");
        enact("RuRURURururr", 'y', 'b', 'r');
        for (int j = 1; j <= 4; j++)
        {
          if (isInPlace('y', 'r', 'b'))
            return;
          getCube().turn('y');
          update();
        }
      }
      else if (getCube().getBFace()[getS()-1][1] == getCube().getBFace()[getS()-1][0] && getCube().getRFace()[getS()-1][1] == clockwise(getCube().getOFace()[getS()-1][1]) && getCube().getOFace()[getS()-1][1] == opposite(getCube().getGFace()[getS()-1][1]) && getCube().getGFace()[getS()-1][1] == clockwise(getCube().getRFace()[getS()-1][1]))
      {
        //System.out.println("counterclockwise cycle");
        enact("rrURUrururUr", 'y', 'b', 'r');
        for (int j = 1; j <= 4; j++)
        {
          if (isInPlace('y', 'r', 'b'))
            return;
          getCube().turn('y');
          update();
        }
      }
      else if (getCube().getOFace()[getS()-1][1] == opposite(getCube().getRFace()[getS()-1][1]) && getCube().getGFace()[getS()-1][1] == opposite(getCube().getBFace()[getS()-1][1]))
      {
        //System.out.println("other case called");
        for (int j = 1; j <= 4; j++)
        {
          if (isInPlace('y', 'r', 'b'))
          {
            if (isInPlace('y', 'b'))
              return;
            else
              break;
          }
          getCube().turn('y');
          update();
        }
        if (getCube().getBFace()[getS()-1][1] == 'g')
        {
          enact("mmummuummumm", 'y', 'b', 'r');
          //System.out.println("opposites");
          return;
        }
        else if (getCube().getBFace()[getS()-1][1] == 'r')
        {
          enact("RUrruruRUrurUrURuu", 'y', 'b', 'r');
          //System.out.println("adjacent swap 1");
          return;
        }
        else if (getCube().getBFace()[getS()-1][1] == 'o')
        {
          enact("RUrruruRUrurUrURuu", 'y', 'o', 'b');
          //System.out.println("adjacent swap 2");
          return;
        }
      }
      getCube().turn('y');
      update();
    }
  }

  public void solve()
  {
    correctCenters();
    makeWCross();
    f2l();
    makeYCross();
    makeTopYellow();
    permuteCorners();
    permuteEdges();
  }

  public static void main(String[] args)
  {
    CubeSolver cs = new CubeSolver(cubeSize);
    cs.repaint();
    cs.scramble(30);
    try {
      Thread.sleep(3000);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
    cs.solve();
  }
}
