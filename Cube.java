public class Cube
{
  private int size;

  private char[][] whiteFace;
  private char[][] yellowFace;
  private char[][] greenFace;
  private char[][] blueFace;
  private char[][] orangeFace;
  private char[][] redFace;

  //private static boolean adjacentSplitterFailed = false;

  public Cube(int size)
  {
    this.size = size;

    whiteFace = new char[size][size];
    yellowFace = new char[size][size];
    greenFace = new char[size][size];
    blueFace = new char[size][size];
    orangeFace = new char[size][size];
    redFace = new char[size][size];

    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < size; j++)
      {
        whiteFace[i][j] = 'w';
        yellowFace[i][j] = 'y';
        greenFace[i][j] = 'g';
        blueFace[i][j] = 'b';
        orangeFace[i][j] = 'o';
        redFace[i][j] = 'r';
      }
    }
  }

  public Cube()
  {
    this(3);
  }

  public Cube(char[][] whiteFace,
              char[][] yellowFace,
              char[][] greenFace,
              char[][] blueFace,
              char[][] orangeFace,
              char[][] redFace)
  {
    size = whiteFace.length;

    this.whiteFace = whiteFace;
    this.yellowFace = yellowFace;
    this.greenFace = greenFace;
    this.blueFace = blueFace;
    this.orangeFace = orangeFace;
    this.redFace = redFace;
  }

  public void setEntry(char face, int i, int j, char c)
  {
    if (face == 'b')
      blueFace[i][j] = c;
  }

  public int getSize()
  {
    return size;
  }

  public char[][] getWFace()
  {
    return whiteFace;
  }

  public char[][] getYFace()
  {
    return yellowFace;
  }

  public char[][] getGFace()
  {
    return greenFace;
  }

  public char[][] getBFace()
  {
    return blueFace;
  }

  public char[][] getOFace()
  {
    return orangeFace;
  }

  public char[][] getRFace()
  {
    return redFace;
  }

  private static char[][] rotate90(char[][] a)
  {
    int s = a.length;
    char[][] newA = new char[s][s];
    for (int i = 0; i < s; i++)
    {
      for (int j = 0; j < s; j++)
      {
        newA[j][s-1-i] = a[i][j];
      }
    }
    return newA;
  }

  public void twist(char face, int depth)
  {
    if (face == 'w')
    {
      for (int i = 0; i < size; i++)
      {
        char temp = greenFace[depth][i];
        greenFace[depth][i] = redFace[depth][i];
        redFace[depth][i] = blueFace[depth][i];
        blueFace[depth][i] = orangeFace[depth][i];
        orangeFace[depth][i] = temp;
      }
    }
    else if (face == 'y')
    {
      for (int i = 0; i < size; i++)
      {
        char temp = greenFace[size-1-depth][i];
        greenFace[size-1-depth][i] = orangeFace[size-1-depth][i];
        orangeFace[size-1-depth][i] = blueFace[size-1-depth][i];
        blueFace[size-1-depth][i] = redFace[size-1-depth][i];
        redFace[size-1-depth][i] = temp;
      }
    }
    else if (face == 'g')
    {
      for (int i = 0; i < size; i++)
      {
        char temp = whiteFace[depth][i];
        whiteFace[depth][i] = orangeFace[i][size-1-depth];
        orangeFace[i][size-1-depth] = yellowFace[depth][i];
        yellowFace[depth][i] = redFace[size-1-i][depth];
        redFace[size-1-i][depth] = temp;
      }
    }
    else if (face == 'b')
    {
      for (int i = 0; i < size; i++)
      {
        char temp = whiteFace[size-1-depth][i];
        whiteFace[size-1-depth][i] = redFace[size-1-i][size-1-depth];
        redFace[size-1-i][size-1-depth] = yellowFace[size-1-depth][i];
        yellowFace[size-1-depth][i] = orangeFace[i][depth];
        orangeFace[i][depth] = temp;
      }
    }
    else if (face == 'o')
    {
      for (int i = 0; i < size; i++)
      {
        char temp = whiteFace[size-1-i][size-1-depth];
        whiteFace[size-1-i][size-1-depth] = blueFace[size-1-i][size-1-depth];
        blueFace[size-1-i][size-1-depth] = yellowFace[i][depth];
        yellowFace[i][depth] = greenFace[i][depth];
        greenFace[i][depth] = temp;
      }
    }
    else if (face == 'r')
    {
      for (int i = 0; i < size; i++)
      {
        char temp = whiteFace[i][depth];
        whiteFace[i][depth] = greenFace[size-1-i][size-1-depth];
        greenFace[size-1-i][size-1-depth] = yellowFace[size-1-i][size-1-depth];
        yellowFace[size-1-i][size-1-depth] = blueFace[i][depth];
        blueFace[i][depth] = temp;
      }
    }
  }

  public void turn(char face, int depth)
  {
    if (depth == 0)
    {
      if (face == 'w')
        whiteFace = rotate90(whiteFace);
      else if (face == 'y')
        yellowFace = rotate90(yellowFace);
      else if (face == 'b')
        blueFace = rotate90(blueFace);
      else if (face == 'g')
        greenFace = rotate90(greenFace);
      else if (face == 'o')
        orangeFace = rotate90(orangeFace);
      else if (face == 'r')
        redFace = rotate90(redFace);
    }
    else if (depth == size-1)
    {
      if (face == 'w')
        for (int i = 1; i <= 3; i++)
          yellowFace = rotate90(yellowFace);
      else if (face == 'y')
        for (int i = 1; i <= 3; i++)
          whiteFace = rotate90(whiteFace);
      else if (face == 'b')
        for (int i = 1; i <= 3; i++)
          greenFace = rotate90(greenFace);
      else if (face == 'g')
        for (int i = 1; i <= 3; i++)
          blueFace = rotate90(blueFace);
      else if (face == 'o')
        for (int i = 1; i <= 3; i++)
          redFace = rotate90(redFace);
      else if (face == 'r')
        for (int i = 1; i <= 3; i++)
          orangeFace = rotate90(orangeFace);
    }
    twist(face, depth);
  }

  public void turn(char face)
  {
    turn(face, 0);
    /*
    if (face == 'w')
    {
      whiteFace = rotate90(whiteFace);
      for (int i = 0; i < size; i++)
      {
        char temp = greenFace[0][i];
        greenFace[0][i] = redFace[0][i];
        redFace[0][i] = blueFace[0][i];
        blueFace[0][i] = orangeFace[0][i];
        orangeFace[0][i] = temp;
      }
    }
    else if (face == 'y')
    {
      yellowFace = rotate90(yellowFace);
      for (int i = 0; i < size; i++)
      {
        char temp = greenFace[size-1][i];
        greenFace[size-1][i] = orangeFace[size-1][i];
        orangeFace[size-1][i] = blueFace[size-1][i];
        blueFace[size-1][i] = redFace[size-1][i];
        redFace[size-1][i] = temp;
      }
    }
    else if (face == 'g')
    {
      greenFace = rotate90(greenFace);
      for (int i = 0; i < size; i++)
      {
        char temp = whiteFace[0][i];
        whiteFace[0][i] = orangeFace[i][size-1];
        orangeFace[i][size-1] = yellowFace[0][i];
        yellowFace[0][i] = redFace[size-1-i][0];
        redFace[size-1-i][0] = temp;
      }
    }
    else if (face == 'b')
    {
      blueFace = rotate90(blueFace);
      for (int i = 0; i < size; i++)
      {
        char temp = whiteFace[size-1][i];
        whiteFace[size-1][i] = redFace[size-1-i][size-1];
        redFace[size-1-i][size-1] = yellowFace[size-1][i];
        yellowFace[size-1][i] = orangeFace[i][0];
        orangeFace[i][0] = temp;
      }
    }
    else if (face == 'o')
    {
      orangeFace = rotate90(orangeFace);
      for (int i = 0; i < size; i++)
      {
        char temp = whiteFace[size-1-i][size-1];
        whiteFace[size-1-i][size-1] = blueFace[size-1-i][size-1];
        blueFace[size-1-i][size-1] = yellowFace[i][0];
        yellowFace[i][0] = greenFace[i][0];
        greenFace[i][0] = temp;
      }
    }
    else if (face == 'r')
    {
      redFace = rotate90(redFace);
      for (int i = 0; i < size; i++)
      {
        char temp = whiteFace[i][0];
        whiteFace[i][0] = greenFace[size-1-i][size-1];
        greenFace[size-1-i][size-1] = yellowFace[size-1-i][size-1];
        yellowFace[size-1-i][size-1] = blueFace[i][0];
        blueFace[i][0] = temp;
      }
    }
    */
  }

  public void turnP(char face, int depth)
  {
    for (int i = 1; i <= 3; i++)
      turn(face, depth);
  }

  public void turnP(char face)
  {
    turnP(face, 0);
  }

  public void turnAll(char face)
  {
    for (int depth = 0; depth < size; depth++)
    {
      turn(face, depth);
    }
  }

  public void turnPAll(char face)
  {
    for (int depth = 0; depth < size; depth++)
    {
      turnP(face, depth);
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
      int depth = (int)(Math.random() * size);
      int index = (int)(Math.random() * 6);
      char face = sides[index];
      int prime = (int)(Math.random()*2);
      if (prime == 0)
      {
        turn(face, depth);
      }
      else
      {
        turnP(face, depth);
      }
    }
  }

  private char[] findEdge(char color1, char color2)
  {
    char[] sides = new char[2];
    //check WhiteFace
    for (int i = 1; i < size-1; i++)
    {
      if (whiteFace[0][i] == color1)
      {
        if (greenFace[0][size-1-i] == color2)
        {
          sides[0] = 'w';
          sides[1] = 'g';
          return sides;
        }
      }
      else if (whiteFace[0][i] == color2)
      {
        if (greenFace[0][size-1-i] == color1)
        {
          sides[0] = 'g';
          sides[1] = 'w';
          return sides;
        }
      }

      if (whiteFace[i][size-1] == color1)
      {
        if (orangeFace[0][size-1-i] == color2)
        {
          sides[0] = 'w';
          sides[1] = 'o';
          return sides;
        }
      }
      else if (whiteFace[i][size-1] == color2)
      {
        if (orangeFace[0][size-1-i] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'w';
          return sides;
        }
      }

      if (whiteFace[size-1][i] == color1)
      {
        if (blueFace[0][i] == color2)
        {
          sides[0] = 'w';
          sides[1] = 'b';
          return sides;
        }
      }
      else if (whiteFace[size-1][i] == color2)
      {
        if (blueFace[0][i] == color1)
        {
          sides[0] = 'b';
          sides[1] = 'w';
          return sides;
        }
      }

      if (whiteFace[i][0] == color1)
      {
        if (redFace[0][i] == color2)
        {
          sides[0] = 'w';
          sides[1] = 'r';
          return sides;
        }
      }
      else if (whiteFace[i][0] == color2)
      {
        if (redFace[0][i] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'w';
          return sides;
        }
      }
    }

    //check yellowFace
    for (int i = 1; i < size-1; i++)
    {
      if (yellowFace[0][i] == color1)
      {
        if (greenFace[size-1][i] == color2)
        {
          sides[0] = 'y';
          sides[1] = 'g';
          return sides;
        }
      }
      else if (yellowFace[0][i] == color2)
      {
        if (greenFace[size-1][i] == color1)
        {
          sides[0] = 'g';
          sides[1] = 'y';
          return sides;
        }
      }

      if (yellowFace[i][size-1] == color1)
      {
        if (redFace[size-1][i] == color2)
        {
          sides[0] = 'y';
          sides[1] = 'r';
          return sides;
        }
      }
      else if (yellowFace[i][size-1] == color2)
      {
        if (redFace[size-1][i] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'y';
          return sides;
        }
      }

      if (yellowFace[size-1][i] == color1)
      {
        if (blueFace[size-1][size-1-i] == color2)
        {
          sides[0] = 'y';
          sides[1] = 'b';
          return sides;
        }
      }
      else if (yellowFace[size-1][i] == color2)
      {
        if (blueFace[size-1][size-1-i] == color1)
        {
          sides[0] = 'b';
          sides[1] = 'y';
          return sides;
        }
      }

      if (yellowFace[i][0] == color1)
      {
        if (orangeFace[size-1][size-1-i] == color2)
        {
          sides[0] = 'y';
          sides[1] = 'o';
          return sides;
        }
      }
      else if (yellowFace[i][0] == color2)
      {
        if (orangeFace[size-1][size-1-i] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'y';
          return sides;
        }
      }
    }

    //check middle faces
    for (int i = 1; i < size-1; i++)
    {
      if (greenFace[i][0] == color1)
      {
        if (orangeFace[i][size-1] == color2)
        {
          sides[0] = 'g';
          sides[1] = 'o';
          return sides;
        }
      }
      else if (greenFace[i][0] == color2)
      {
        if (orangeFace[i][size-1] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'g';
          return sides;
        }
      }

      if (greenFace[i][size-1] == color1)
      {
        if (redFace[i][0] == color2)
        {
          sides[0] = 'g';
          sides[1] = 'r';
          return sides;
        }
      }
      else if (greenFace[i][size-1] == color2)
      {
        if (redFace[i][0] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'g';
          return sides;
        }
      }

      if (blueFace[i][0] == color1)
      {
        if (redFace[i][size-1] == color2)
        {
          sides[0] = 'b';
          sides[1] = 'r';
          return sides;
        }
      }
      else if (blueFace[i][0] == color2)
      {
        if (redFace[i][size-1] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'b';
          return sides;
        }
      }

      if (blueFace[i][size-1] == color1)
      {
        if (orangeFace[i][0] == color2)
        {
          sides[0] = 'b';
          sides[1] = 'o';
          return sides;
        }
      }
      else if (blueFace[i][size-1] == color2)
      {
        if (orangeFace[i][0] == color1)
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
    //check yellowFace
    if (yellowFace[0][0] == color1)
    {
      if (orangeFace[size-1][size-1] == color2)
      {
        if (greenFace[size-1][0] == color3)
        {
          sides[0] = 'y';
          sides[1] = 'o';
          sides[2] = 'g';
          return sides;
        }
      }
    }
    else if (yellowFace[0][0] == color2)
    {
      if (orangeFace[size-1][size-1] == color3)
      {
        if (greenFace[size-1][0] == color1)
        {
          sides[0] = 'g';
          sides[1] = 'y';
          sides[2] = 'o';
        }
      }
    }
    else if (yellowFace[0][0] == color3)
    {
      if (orangeFace[size-1][size-1] == color1)
      {
        if (greenFace[size-1][0] == color2)
        {
          sides[0] = 'o';
          sides[1] = 'g';
          sides[2] = 'y';
        }
      }
    }

    if (yellowFace[0][size-1] == color1)
    {
      if (greenFace[size-1][size-1] == color2)
      {
        if (redFace[size-1][0] == color3)
        {
          sides[0] = 'y';
          sides[1] = 'g';
          sides[2] = 'r';
          return sides;
        }
      }
    }
    else if (yellowFace[0][size-1] == color2)
    {
      if (greenFace[size-1][size-1] == color3)
      {
        if (redFace[size-1][0] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'y';
          sides[2] = 'g';
          return sides;
        }
      }
    }
    else if (yellowFace[0][size-1] == color3)
    {
      if (greenFace[size-1][size-1] == color1)
      {
        if (redFace[size-1][0] == color2)
        {
          sides[0] = 'g';
          sides[1] = 'r';
          sides[2] = 'y';
          return sides;
        }
      }
    }

    if (yellowFace[size-1][size-1] == color1)
    {
      if (redFace[size-1][size-1] == color2)
      {
        if (blueFace[size-1][0] == color3)
        {
          sides[0] = 'y';
          sides[1] = 'r';
          sides[2] = 'b';
          return sides;
        }
      }
    }
    else if (yellowFace[size-1][size-1] == color2)
    {
      if (redFace[size-1][size-1] == color3)
      {
        if (blueFace[size-1][0] == color1)
        {
          sides[0] = 'b';
          sides[1] = 'y';
          sides[2] = 'r';
          return sides;
        }
      }
    }
    else if (yellowFace[size-1][size-1] == color3)
    {
      if (redFace[size-1][size-1] == color1)
      {
        if (blueFace[size-1][0] == color2)
        {
          sides[0] = 'r';
          sides[1] = 'b';
          sides[2] = 'y';
          return sides;
        }
      }
    }

    if (yellowFace[size-1][0] == color1)
    {
      if (blueFace[size-1][size-1] == color2)
      {
        if (orangeFace[size-1][0] == color3)
        {
          sides[0] = 'y';
          sides[1] = 'b';
          sides[2] = 'o';
          return sides;
        }
      }
    }
    else if (yellowFace[size-1][0] == color2)
    {
      if (blueFace[size-1][size-1] == color3)
      {
        if (orangeFace[size-1][0] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'y';
          sides[2] = 'b';
          return sides;
        }
      }
    }
    else if (yellowFace[size-1][0] == color3)
    {
      if (blueFace[size-1][size-1] == color1)
      {
        if (orangeFace[size-1][0] == color2)
        {
          sides[0] = 'b';
          sides[1] = 'o';
          sides[2] = 'y';
          return sides;
        }
      }
    }

    //check whiteFace
    if (whiteFace[0][0] == color1)
    {
      if (redFace[0][0] == color2)
      {
        if (greenFace[0][size-1] == color3)
        {
          sides[0] = 'w';
          sides[1] = 'r';
          sides[2] = 'g';
          return sides;
        }
      }
    }
    else if (whiteFace[0][0] == color2)
    {
      if (redFace[0][0] == color3)
      {
        if (greenFace[0][size-1] == color1)
        {
          sides[0] = 'g';
          sides[1] = 'w';
          sides[2] = 'r';
          return sides;
        }
      }
    }
    else if (whiteFace[0][0] == color3)
    {
      if (redFace[0][0] == color1)
      {
        if (greenFace[0][size-1] == color2)
        {
          sides[0] = 'r';
          sides[1] = 'g';
          sides[2] = 'w';
          return sides;
        }
      }
    }
    if (whiteFace[0][size-1] == color1)
    {
      if (greenFace[0][0] == color2)
      {
        if (orangeFace[0][size-1] == color3)
        {
          sides[0] = 'w';
          sides[1] = 'g';
          sides[2] = 'o';
          return sides;
        }
      }
    }
    else if (whiteFace[0][size-1] == color2)
    {
      if (greenFace[0][0] == color3)
      {
        if (orangeFace[0][size-1] == color1)
        {
          sides[0] = 'o';
          sides[1] = 'w';
          sides[2] = 'g';
          return sides;
        }
      }
    }
    else if (whiteFace[0][size-1] == color3)
    {
      if (greenFace[0][0] == color1)
      {
        if (orangeFace[0][size-1] == color2)
        {
          sides[0] = 'g';
          sides[1] = 'o';
          sides[2] = 'w';
          return sides;
        }
      }
    }
    if (whiteFace[size-1][size-1] == color1)
    {
      if (orangeFace[0][0] == color2)
      {
        if (blueFace[0][size-1] == color3)
        {
          sides[0] = 'w';
          sides[1] = 'o';
          sides[2] = 'b';
          return sides;
        }
      }
    }
    else if (whiteFace[size-1][size-1] == color2)
    {
      if (orangeFace[0][0] == color3)
      {
        if (blueFace[0][size-1] == color1)
        {
          sides[0] = 'b';
          sides[1] = 'w';
          sides[2] = 'o';
          return sides;
        }
      }
    }
    else if (whiteFace[size-1][size-1] == color3)
    {
      if (orangeFace[0][0] == color1)
      {
        if (blueFace[0][size-1] == color2)
        {
          sides[0] = 'o';
          sides[1] = 'b';
          sides[2] = 'w';
          return sides;
        }
      }
    }
    if (whiteFace[size-1][0] == color1)
    {
      if (blueFace[0][0] == color2)
      {
        if (redFace[0][size-1] == color3)
        {
          sides[0] = 'w';
          sides[1] = 'b';
          sides[2] = 'r';
          return sides;
        }
      }
    }
    else if (whiteFace[size-1][0] == color2)
    {
      if (blueFace[0][0] == color3)
      {
        if (redFace[0][size-1] == color1)
        {
          sides[0] = 'r';
          sides[1] = 'w';
          sides[2] = 'b';
          return sides;
        }
      }
    }
    else if (whiteFace[size-1][0] == color3)
    {
      if (blueFace[0][0] == color1)
      {
        if (redFace[0][size-1] == color2)
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
    if (yellowFace[size/2][size/2] == 'w')
    {
      turnAll('r');
      turnAll('r');
    }
    else if (redFace[size/2][size/2] == 'w')
      turnAll('b');
    else if (greenFace[size/2][size/2] == 'w')
      turnAll('r');
    else if (orangeFace[size/2][size/2] == 'w')
      turnAll('g');
    else if (blueFace[size/2][size/2] == 'w')
      turnAll('o');

    char rc = redFace[size/2][size/2];
    if (rc == 'o')
    {
      turnAll('w');
      turnAll('w');
    }
    else if (rc == 'g')
      turnAll('w');
    else if (rc == 'b')
      turnAll('y');
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

  public void reduceWCenter()
  {
    
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
        turn(p1[1]);
        turn(p1[1]);
      }
      //else
        //System.out.println("placing edge " + color2 + " up from bottom");
      if (color2 == opposite(p1[1]))
      {
        turn('y');
        turn('y');
      }
      else if (color2 == clockwise(p1[1]))
      {
        turnP('y');
      }
      else if (color2 == opposite(clockwise(p1[1])))
      {
        turn('y');
      }
      turn(color2);
      turn(color2);
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
          turn(p1[0]);
          turn(p1[0]);
          p1[1] = 'y';
          //System.out.println("moving edge " + color2 + " from flipped top");
        }
        //else
          //System.out.println("moving edge " + color2 + " from flipped bottom");
        if (color2 == opposite(p1[0]))
        {
          turn('y');
        }
        else if (color2 == clockwise(p1[0]))
        {
          turn('y');
          turn('y');
        }
        else if (color2 == p1[0])
        {
          turnP('y');
        }
        turnP(clockwise(color2));
        turn(color2);
        turn(clockwise(color2));
        //if (isInPlace('w', color2))
          //System.out.println("corrected");
        return;
      }
      else
      {
        if (p1[0] == clockwise(p1[1]))
        {
          //System.out.println("turning down clockwise");
          turnP(p1[1]);
          turn('y');
          turn(p1[1]);
        }
        else
        {
          //System.out.println("turning down counterclockwise");
          turn(p1[1]);
          turn('y');
          turnP(p1[1]);
        } //inserts into bottom layer
        p1[1] = opposite(clockwise(p1[1]));
        if (color2 == opposite(p1[1]))
        {
          turn('y');
          turn('y');
        }
        else if (color2 == clockwise(p1[1]))
        {
          turnP('y');
        }
        else if (color2 == opposite(clockwise(p1[1])))
        {
          turn('y');
        }
        turn(color2);
        turn(color2);
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
        turn(e[0]);
        turn('y');
        turnP(e[0]);
      }
      else
      {
        turn(e[1]);
        turn('y');
        turnP(e[1]);
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
            turn('y');
          turnP('y');
          turn(c[1]);
          turn('y');
          turnP(c[1]);
        }
        else
        {
          if (e[0] == c[2] || e[1] == c[2]) //if edge, on top, is on the same face
            turn('y');
          turnP('y');
          turn(c[2]);
          turn('y');
          turnP(c[2]);
        }
      }
      else if (c[1] == 'w')
      {
        if (c[0] == clockwise(c[2]))
        {
          if (e[0] == c[0] || e[1] == c[0])
            turn('y');
          turnP('y');
          turn(c[0]);
          turn('y');
          turnP(c[0]);
        }
        else
        {
          if (e[0] == c[2] || e[1] == c[2])
            turn('y');
          turnP('y');
          turn(c[2]);
          turn('y');
          turnP(c[2]);
        }
      }
      else //if c[2] == 'w'
      {
        if (c[0] == clockwise(c[1]))
        {
          if (e[0] == c[0] || e[1] == c[0])
            turn('y');
          turnP('y');
          turn(c[0]);
          turn('y');
          turnP(c[0]);
        }
        else
        {
          if (e[0] == c[1] || e[1] == c[1])
            turn('y');
          turnP('y');
          turn(c[1]);
          turn('y');
          turnP(c[1]);
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
          turn('y');
          turn('y');
        }
        else if (e[0] == clockwise(color2))
        {
          turn('y');
        }
        else if (e[0] == opposite(clockwise(color2)))
        {
          turnP('y');
        }
        e[0] = color2;
      }
      else //color3 is on the side
      {
        if (e[1] == opposite(color3))
        {
          turn('y');
          turn('y');
        }
        else if (e[1] == clockwise(color3))
        {
          turn('y');
        }
        else if (e[1] == opposite(clockwise(color3)))
        {
          turnP('y');
        }
        e[1] = color3;
      }
      c = findCorner('w', color2, color3);

      if (e[0] == 'y' && c[1] == 'y' && c[2] == color3) //|| e[1] == 'y' && c[2] == 'y') //if already paired
      {
        //System.out.println("already paired 1");
        turn('y');
        turn(color3);
        turnP('y');
        turnP(color3);
        //if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
          //System.out.println("FAILED");
        return;
      }
      else if (e[1] == 'y' && c[2] == 'y' && c[1] == color2)
      {
        //System.out.println("already paired 2");
        turnP('y');
        turnP(color2);
        turn('y');
        turn(color2);
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
          turnP('y');
          turn(color3);
          turnP('y');
          turnP(color3);
        }
        else
        {
          turn('y');
          turn('y');
          turnP(color2);
          turn('y');
          turn(color2);
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
          turn('y');
          turnP(color2);
          turn('y');
          turn(color2);
        }
        else
        {
          turn('y');
          turn('y');
          turn(color3);
          turnP('y');
          turnP(color3);
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
          turn('y');
          turn('y');
        }
        else if (e[0] == clockwise(color2))
        {
          turn('y');
        }
        else if (e[0] == opposite(clockwise(color2)))
        {
          turnP('y');
        }
        e[0] = color2;
        c = findCorner('w', color2, color3);
        turnP(color2);
        if (c[1] == color3 || c[2] == color3)
        {
          turn('y');
          turn('y');
        }
        else
        {
          turnP('y');
        }
        turn(color2);
        turnP('y');
        turnP(color2);
        turn('y');
        turn(color2);
        /*System.out.println("white on top");
        if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
          System.out.println("FAILED");*/
        return;
      }
      else //color3 is on the side
      {
        if (e[1] == opposite(color3))
        {
          turn('y');
          turn('y');
        }
        else if (e[1] == clockwise(color3))
        {
          turn('y');
        }
        else if (e[1] == opposite(clockwise(color3)))
        {
          turnP('y');
        }
        e[1] = color3;
        c = findCorner('w', color2, color3);
        turn(color3);
        if (c[1] == color2 || c[2] == color2)
        {
          turn('y');
          turn('y');
        }
        else
        {
          turn('y');
        }
        turnP(color3);
        turn('y');
        turn(color3);
        turnP('y');
        turnP(color3);
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
            turn('y');
            turn('y');
          }
          else if (c[0] == clockwise(color3))
          {
            turn('y');
          }
          else if (c[0] == opposite(clockwise(color3)))
          {
            turnP('y');
          }
          turn(color3);
          turnP('y');
          turnP(color3);
          turn('y');
          turnP(color2);
          turnP('y');
          turn(color2);
          /*System.out.println("opp color place 1");
          if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
            System.out.println("FAILED");*/
          return;
        }
        else
        {
          if (c[0] == opposite(color2))
          {
            turn('y');
            turn('y');
          }
          else if (c[0] == clockwise(color2))
          {
            turn('y');
          }
          else if (c[0] == opposite(clockwise(color2)))
          {
            turnP('y');
          }
          turnP(color2);
          turnP('y');
          turn(color2);
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
            turn('y');
            turn('y');
          }
          else if (c[0] == clockwise(color2))
          {
            turn('y');
          }
          else if (c[0] == opposite(clockwise(color2)))
          {
            turnP('y');
          }
          turnP(color2);
          turn('y');
          turn(color2);
          turnP('y');
          turn(color3);
          turn('y');
          turnP(color3);
          /*System.out.println("opp color place 3");
          if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
            System.out.println("FAILED");*/
          return;
        }
        else
        {
          if (c[0] == opposite(color3))
          {
            turn('y');
            turn('y');
          }
          else if (c[0] == clockwise(color3))
          {
            turn('y');
          }
          else if (c[0] == opposite(clockwise(color3)))
          {
            turnP('y');
          }
          turn(color3);
          turn('y');
          turnP(color3);
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
          turn('y');
          turn('y');
        }
        else if (c[0] == clockwise(color2))
        {
          turn('y');
        }
        else if (c[0] == opposite(clockwise(color2)))
        {
          turnP('y');
        }
        turnP(color2);
        if (e[0] == opposite(c[0]))
        {
          turnP('y');
        }
        else
        {
          turn('y');
          turn('y');
        }
        turn(color2);
        turn('y');
        turn('y');
        turnP(color2);
        turn('y');
        turn(color2);
        /*System.out.println("same color place 1");
        if (!isInPlace(color2, color3) || !isInPlace('w', color2, color3))
          System.out.println("FAILED");*/
        return;
      }
      else //color3 on side
      {
        if (c[0] == opposite(color3))
        {
          turn('y');
          turn('y');
        }
        else if (c[0] == clockwise(color3))
        {
          turn('y');
        }
        else if (c[0] == opposite(clockwise(color3)))
        {
          turnP('y');
        }
        turn(color3);
        if (e[1] == opposite(c[0]))
        {
          turn('y');
        }
        else
        {
          turn('y');
          turn('y');
        }
        turnP(color3);
        turn('y');
        turn('y');
        turn(color3);
        turnP('y');
        turnP(color3);
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
        turn(uFace);
      else if (c == 'U')
        turnP(uFace);
      else if (c == 'f')
        turn(fFace);
      else if (c == 'F')
        turnP(fFace);
      else if (c == 'r')
        turn(rFace);
      else if (c == 'R')
        turnP(rFace);
      else if (c == 'd')
        turn(opposite(uFace));
      else if (c == 'D')
        turnP(opposite(uFace));
      else if (c == 'b')
        turn(opposite(fFace));
      else if (c == 'B')
        turnP(opposite(fFace));
      else if (c == 'l')
        turn(opposite(rFace));
      else if (c == 'L')
        turnP(opposite(rFace));
      else if (c == 'x')
        turnAll(rFace);
      else if (c == 'X')
        turnPAll(rFace);
      else if (c == 'y')
        turnAll(uFace);
      else if (c == 'Y')
        turnPAll(uFace);
      else if (c == 'z')
        turnAll(fFace);
      else if (c == 'Z')
        turnPAll(fFace);
      else if (c == 'm')
      {
        turnAll(rFace);
        turnP(rFace);
        turn(opposite(rFace));
      }
      else if (c == 'M')
      {
        turnPAll(rFace);
        turn(rFace);
        turnP(opposite(rFace));
      }
    }
  }

  private void makeYCross()
  {
    for (int i = 1; i <= 4; i++)
    {
      if (yellowFace[0][1] != 'y' && yellowFace[size-1][1] != 'y')
      {
        turn('b');
        turn('r');
        turn('y');
        turnP('r');
        turnP('y');
        turnP('b');
        if (yellowFace[0][1] == 'y' && yellowFace[1][0] == 'y')
          return;
        else
        {
          for (int d = 0; d < size-1; d++)
            turn('b', d);
          turn('r');
          turn('y');
          turnP('r');
          turnP('y');
          for (int d = 0; d < size-1; d++)
            turnP('b', d);
          return;
        }
      }
      if (yellowFace[0][1] != 'y' && yellowFace[1][0] != 'y')
      {
        for (int d = 0; d < size-1; d++)
          turn('b', d);
        turn('r');
        turn('y');
        turnP('r');
        turnP('y');
        for (int d = 0; d < size-1; d++)
          turnP('b', d);
        return;
      }

      turn('y');
    }
  }

  private int numYellowCorners()
  {
    int num = 0;
    if (yellowFace[0][0] == 'y')
      num++;
    if (yellowFace[0][size-1] == 'y')
      num++;
    if (yellowFace[size-1][size-1] == 'y')
      num++;
    if (yellowFace[size-1][0] == 'y')
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
        if (orangeFace[size-1][0] == 'y' && orangeFace[size-1][size-1] == 'y' && redFace[size-1][0] == 'y' && redFace[size-1][size-1] == 'y')
        {
          enact("ruRurURuruuR", 'y', 'b', 'r');
          //System.out.println("case 1");
          return;
        }
        else if (orangeFace[size-1][0] == 'y' && orangeFace[size-1][size-1] == 'y' && greenFace[size-1][size-1] == 'y' && blueFace[size-1][0] == 'y')
        {
          enact("ruurrUrrUrruur", 'y', 'b', 'r');
          //System.out.println("case 2");
          return;
        }
        turn('y');
      }
    }
    else if (nYCorners == 1)
    {
      //System.out.println("1 corner yellow");
      for (int i = 1; i <= 4; i++)
      {
        if (yellowFace[size-1][size-1] == 'y' && orangeFace[size-1][0] == 'y')
        {
          enact("RuuruRur", 'y', 'b', 'r');
          //System.out.println("case 1");
          return;
        }
        else if (yellowFace[0][size-1] == 'y' && orangeFace[size-1][size-1] == 'y')
        {
          enact("ruuRUrUR", 'y', 'b', 'r');
          //System.out.println("case 2");
          return;
        }
        turn('y');
      }
    }
    else if (nYCorners == 2)
    {
      //System.out.println("2 corners yellow");
      if (yellowFace[0][0] == yellowFace[size-1][size-1] || yellowFace[0][size-1] == yellowFace[size-1][0])
      {
        for (int i = 1; i <= 4; i++)
        {
          if (yellowFace[0][size-1] == 'y' && orangeFace[size-1][size-1] == 'y')
          {
            enact("FlxuRULXfr", 'y', 'b', 'r');
            //System.out.println("case 1");
            return;
          }
          turn('y');
        }
      }
      else
      {
        for (int i = 1; i <= 4; i++)
        {
          if (greenFace[size-1][0] == 'y' && blueFace[size-1][size-1] == 'y')
          {
            enact("lxuRULXfrF", 'y', 'b', 'r');
            //System.out.println("case 2");
            return;
          }
          else if (blueFace[size-1][0] == 'y' && blueFace[size-1][size-1] == 'y')
          {
            enact("rrdRuurDRuuR", 'y', 'b', 'r');
            //System.out.println("case 3");
            return;
          }
          turn('y');
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
        turn('y');
        enact("frURUruRFruRURfrF", 'y', 'b', 'r');
        return;
      }
      turn('y');
    }
    for (int i = 1; i <= 4; i++)
    {
      if (redFace[size-1][0] == redFace[size-1][size-1])
      {
        enact("xrrddruRddrUrX", 'y', 'b', 'r');
        return;
      }
      turn('y');
    }
  }

  private void permuteEdges()
  {
    for (int i = 1; i <= 4; i++)
    {
      if (blueFace[size-1][1] == blueFace[size-1][0] && orangeFace[size-1][1] == clockwise(greenFace[size-1][1]) && greenFace[size-1][1] == opposite(redFace[size-1][1]) && redFace[size-1][1] == clockwise(orangeFace[size-1][1]))
      {
        //System.out.println("clockwise cycle");
        enact("RuRURURururr", 'y', 'b', 'r');
        for (int j = 1; j <= 4; j++)
        {
          if (isInPlace('y', 'r', 'b'))
            return;
          turn('y');
        }
      }
      else if (blueFace[size-1][1] == blueFace[size-1][0] && redFace[size-1][1] == clockwise(orangeFace[size-1][1]) && orangeFace[size-1][1] == opposite(greenFace[size-1][1]) && greenFace[size-1][1] == clockwise(redFace[size-1][1]))
      {
        //System.out.println("counterclockwise cycle");
        enact("rrURUrururUr", 'y', 'b', 'r');
        for (int j = 1; j <= 4; j++)
        {
          if (isInPlace('y', 'r', 'b'))
            return;
          turn('y');
        }
      }
      else if (orangeFace[size-1][1] == opposite(redFace[size-1][1]) && greenFace[size-1][1] == opposite(blueFace[size-1][1]))
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
          turn('y');
        }
        if (blueFace[size-1][1] == 'g')
        {
          enact("mmummuummumm", 'y', 'b', 'r');
          //System.out.println("opposites");
          return;
        }
        else if (blueFace[size-1][1] == 'r')
        {
          enact("RUrruruRUrurUrURuu", 'y', 'b', 'r');
          //System.out.println("adjacent swap 1");
          return;
        }
        else if (blueFace[size-1][1] == 'o')
        {
          enact("RUrruruRUrurUrURuu", 'y', 'o', 'b');
          //System.out.println("adjacent swap 2");
          return;
        }
      }
      turn('y');
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
    /*
    Cube cube = new Cube(3);
    for (int i = 1; i <= 100; i++)
    {
      cube.scramble(25);
      cube.solve();
      if (adjacentSplitterFailed)
      {
        System.out.println("found error");
        break;
      }
    }
    */
  }
}
