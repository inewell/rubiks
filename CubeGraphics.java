import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JOptionPane;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;

public class CubeGraphics extends Space3D implements KeyListener
{
  //private static int cubeSize = 4;


  private Cube cube;// = new Cube(3);
  private double margin = 0.1;

  private int size;// = cube.getSize();
  private double stSize = 2*(1-margin);

  private char currentFace;
  private char currentMove;
  private int currentDepth;
  private boolean prime;

  public CubeGraphics(int size, boolean scramble)
  {
    super((double)(size*2));
    cube = new Cube(size);
    if (scramble)
      cube.scramble(size*10);
    this.size = size;
    addKeyListener(this);
  }

  public Cube getCube()
  {
    return cube;
  }

  public int getS()
  {
    return size;
  }

  public static Color tileColor(char face)
  {
    if (face == 'w')
      return Color.WHITE;
    else if (face == 'y')
      return Color.YELLOW;
    else if (face == 'g')
      return new Color(0, 150, 40);
    else if (face == 'b')
      return Color.BLUE;
    else if (face == 'r')
      return Color.RED;
    else if (face == 'o')
      return new Color(255, 125, 0);

    else
      return Color.BLACK;
  }

  public void drawWFace(Graphics g)
  {
    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < size; j++)
      {
        //int[] p1 = pCoords(size-2*j, -1*size+2*i, size);
        //g.fillRect(p1[0], p1[1], 1, 1);
        double x1 = size-2*j - margin;
        double y1 = 2*i-size + margin;
        int[] p1 = pCoords(x1, y1, size);
        int[] p2 = pCoords(x1-stSize, y1, size);
        int[] p3 = pCoords(x1-stSize, y1+stSize, size);
        int[] p4 = pCoords(x1, y1+stSize, size);
        int[] xList = {p1[0], p2[0], p3[0], p4[0]};
        int[] yList = {p1[1], p2[1], p3[1], p4[1]};
        g.setColor(tileColor(cube.getWFace()[i][j]));
        g.fillPolygon(xList, yList, 4);
      }
    }
  }

  public void drawYFace(Graphics g)
  {
    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < size; j++)
      {
        //int[] p1 = pCoords(size-2*j, -1*size+2*i, size);
        //g.fillRect(p1[0], p1[1], 1, 1);
        double x1 = 2*j-size + margin;
        double y1 = 2*i-size + margin;
        int[] p1 = pCoords(x1, y1, -size);
        int[] p2 = pCoords(x1+stSize, y1, -size);
        int[] p3 = pCoords(x1+stSize, y1+stSize, -size);
        int[] p4 = pCoords(x1, y1+stSize, -size);
        int[] xList = {p1[0], p2[0], p3[0], p4[0]};
        int[] yList = {p1[1], p2[1], p3[1], p4[1]};
        g.setColor(tileColor(cube.getYFace()[i][j]));
        g.fillPolygon(xList, yList, 4);
      }
    }
  }

  public void drawGFace(Graphics g)
  {
    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < size; j++)
      {
        //int[] p1 = pCoords(size-2*j, -1*size+2*i, size);
        //g.fillRect(p1[0], p1[1], 1, 1);
        double x1 = 2*j-size + margin;
        double z1 = size-2*i - margin;
        int[] p1 = pCoords(x1, -size, z1);
        int[] p2 = pCoords(x1+stSize, -size, z1);
        int[] p3 = pCoords(x1+stSize, -size, z1-stSize);
        int[] p4 = pCoords(x1, -size, z1-stSize);
        int[] wxList = {p1[0], p2[0], p3[0], p4[0]};
        int[] wyList = {p1[1], p2[1], p3[1], p4[1]};
        g.setColor(tileColor(cube.getGFace()[i][j]));
        g.fillPolygon(wxList, wyList, 4);
      }
    }
  }

  public void drawBFace(Graphics g)
  {
    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < size; j++)
      {
        //int[] p1 = pCoords(size-2*j, -1*size+2*i, size);
        //g.fillRect(p1[0], p1[1], 1, 1);
        double x1 = size-2*j - margin;
        double z1 = size-2*i - margin;
        int[] p1 = pCoords(x1, size, z1);
        int[] p2 = pCoords(x1-stSize, size, z1);
        int[] p3 = pCoords(x1-stSize, size, z1-stSize);
        int[] p4 = pCoords(x1, size, z1-stSize);
        int[] wxList = {p1[0], p2[0], p3[0], p4[0]};
        int[] wyList = {p1[1], p2[1], p3[1], p4[1]};
        g.setColor(tileColor(cube.getBFace()[i][j]));
        g.fillPolygon(wxList, wyList, 4);
      }
    }
  }

  public void drawRFace(Graphics g)
  {
    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < size; j++)
      {
        //int[] p1 = pCoords(size-2*j, -1*size+2*i, size);
        //g.fillRect(p1[0], p1[1], 1, 1);
        double y1 = 2*j-size + margin;
        double z1 = size-2*i - margin;
        int[] p1 = pCoords(size, y1, z1);
        int[] p2 = pCoords(size, y1+stSize, z1);
        int[] p3 = pCoords(size, y1+stSize, z1-stSize);
        int[] p4 = pCoords(size, y1, z1-stSize);
        int[] wxList = {p1[0], p2[0], p3[0], p4[0]};
        int[] wyList = {p1[1], p2[1], p3[1], p4[1]};
        g.setColor(tileColor(cube.getRFace()[i][j]));
        g.fillPolygon(wxList, wyList, 4);
      }
    }
  }

  public void drawOFace(Graphics g)
  {
    for (int i = 0; i < size; i++)
    {
      for (int j = 0; j < size; j++)
      {
        //int[] p1 = pCoords(size-2*j, -1*size+2*i, size);
        //g.fillRect(p1[0], p1[1], 1, 1);
        double y1 = size-2*j - margin;
        double z1 = size-2*i - margin;
        int[] p1 = pCoords(-size, y1, z1);
        int[] p2 = pCoords(-size, y1-stSize, z1);
        int[] p3 = pCoords(-size, y1-stSize, z1-stSize);
        int[] p4 = pCoords(-size, y1, z1-stSize);
        int[] wxList = {p1[0], p2[0], p3[0], p4[0]};
        int[] wyList = {p1[1], p2[1], p3[1], p4[1]};
        g.setColor(tileColor(cube.getOFace()[i][j]));
        g.fillPolygon(wxList, wyList, 4);
      }
    }
  }

  public void paint(Graphics g)
  {
    if (getJustMoved())
    {
      //cube.setEntry('b', 0, 2, 'r');
      super.paint(g);
      //super.drawAxes(g);

      int[] rbw = pCoords(size, size, size);
      int[] rby = pCoords(size, size, -size);
      int[] rgw = pCoords(size,-size, size);
      int[] rgy = pCoords(size, -size, -size);
      int[] obw = pCoords(-size, size, size);
      int[] oby = pCoords(-size, size, -size);
      int[] ogw = pCoords(-size, -size, size);
      int[] ogy = pCoords(-size, -size, -size);

      /*if (getXAxis()[0] >= 0)
      {
        int[] xList1 = {rbw[0], rby[0], rgy[0], rgw[0]};
        int[] yList1 = {rbw[1], rby[1], rgy[1], rgw[1]};
        g.setColor(getLight().lightUp(Color.BLACK, getXAxis()));
        g.fillPolygon(xList1, yList1, 4);
        drawRFace(g);

        if (getYAxis()[0] >= 0)
        {
          int[] xList3 = {rbw[0], rby[0], oby[0], obw[0]};
          int[] yList3 = {rbw[1], rby[1], oby[1], obw[1]};
          g.setColor(getLight().lightUp(Color.BLACK, getYAxis()));
          g.fillPolygon(xList3, yList3, 4);
          drawBFace(g);

          if (getZAxis()[0] >= 0)
          {
            int[] xList5 = {rbw[0], rgw[0], ogw[0], obw[0]};
            int[] yList5 = {rbw[1], rgw[1], ogw[1], obw[1]};
            g.setColor(getLight().lightUp(Color.BLACK, getZAxis()));
            g.fillPolygon(xList5, yList5, 4);
            drawWFace(g);


          }
          else
          {

          }
        }
        else
        {
          if (getZAxis()[0] >= 0)
          {

          }
          else
          {

          }
        }
      }
      else
      {
        if (getYAxis()[0] >= 0)
        {
          if (getZAxis()[0] >= 0)
          {

          }
          else
          {

          }
        }
        else
        {
          if (getZAxis()[0] >= 0)
          {

          }
          else
          {

          }
        }
      }*/

      if (getXAxis()[0] >= 0)
      {
        int[] xList1 = {rbw[0], rby[0], rgy[0], rgw[0]};
        int[] yList1 = {rbw[1], rby[1], rgy[1], rgw[1]};
        //g.setColor(getLight().lightUp(Color.RED, getXAxis()));
        g.setColor(Color.RED);
        g.setColor(getLight().lightUp(Color.BLACK, getXAxis()));
        //System.out.println(getXAxis()[0]);
        g.fillPolygon(xList1, yList1, 4);

        drawRFace(g);
      }

      else
      {
        int[] xList2 = {obw[0], oby[0], ogy[0], ogw[0]};
        int[] yList2 = {obw[1], oby[1], ogy[1], ogw[1]};
        g.setColor(Color.ORANGE);
        g.setColor(getLight().lightUp(Color.BLACK, scale(getXAxis(),-1)));
        g.fillPolygon(xList2, yList2, 4);

        drawOFace(g);
      }

      if (getYAxis()[0] >= 0)
      {
        int[] xList3 = {rbw[0], rby[0], oby[0], obw[0]};
        int[] yList3 = {rbw[1], rby[1], oby[1], obw[1]};
        g.setColor(Color.BLUE);
        g.setColor(getLight().lightUp(Color.BLACK, getYAxis()));
        g.fillPolygon(xList3, yList3, 4);

        drawBFace(g);
      }
      else
      {
        int[] xList4 = {rgw[0], rgy[0], ogy[0], ogw[0]};
        int[] yList4 = {rgw[1], rgy[1], ogy[1], ogw[1]};
        g.setColor(Color.GREEN);
        g.setColor(getLight().lightUp(Color.BLACK, scale(getYAxis(),-1)));
        g.fillPolygon(xList4, yList4, 4);

        drawGFace(g);
      }

      if (getZAxis()[0] >= 0)
      {
        int[] xList5 = {rbw[0], rgw[0], ogw[0], obw[0]};
        int[] yList5 = {rbw[1], rgw[1], ogw[1], obw[1]};
        g.setColor(Color.WHITE);
        g.setColor(getLight().lightUp(Color.BLACK, getZAxis()));
        g.fillPolygon(xList5, yList5, 4);

        drawWFace(g);
      }
      else
      {
        int[] xList6 = {rby[0], rgy[0], ogy[0], oby[0]};
        int[] yList6 = {rby[1], rgy[1], ogy[1], oby[1]};
        g.setColor(Color.YELLOW);
        g.setColor(getLight().lightUp(Color.BLACK, scale(getZAxis(),-1)));
        g.fillPolygon(xList6, yList6, 4);

        drawYFace(g);
      }
    }
    else
    {
      if (getZAxis()[0] >= 0)
        drawWFace(g);
      else
        drawYFace(g);
      if (getYAxis()[0] >= 0)
        drawBFace(g);
      else
        drawGFace(g);
      if (getXAxis()[0] >= 0)
        drawRFace(g);
      else
        drawOFace(g);
    }
  }

  public char getUpFace()
  {
    double q1 = getXAxis()[2];
    double q2 = getYAxis()[2];
    double q3 = getZAxis()[2];

    int m = max(Math.abs(q1), Math.abs(q2), Math.abs(q3));

    if (m == 1)
    {
      if (q1 > 0)
        return 'r';
      else
        return 'o';
    }
    else if (m == 2)
    {
      if (q2 > 0)
        return 'b';
      else
        return 'g';
    }
    else
    {
      if (q3 > 0)
        return 'w';
      else
        return 'y';
    }
  }

  public char getFrontFace()
  {
    double q1 = getXAxis()[0];
    double q2 = getYAxis()[0];
    double q3 = getZAxis()[0];

    int m = max(Math.abs(q1), Math.abs(q2), Math.abs(q3));

    if (m == 1)
    {
      if (q1 > 0)
        return 'r';
      else
        return 'o';
    }
    else if (m == 2)
    {
      if (q2 > 0)
        return 'b';
      else
        return 'g';
    }
    else
    {
      if (q3 > 0)
        return 'w';
      else
        return 'y';
    }
  }

  public char getRightFace()
  {
    double q1 = getXAxis()[1];
    double q2 = getYAxis()[1];
    double q3 = getZAxis()[1];

    int m = max(Math.abs(q1), Math.abs(q2), Math.abs(q3));

    if (m == 1)
    {
      if (q1 > 0)
        return 'r';
      else
        return 'o';
    }
    else if (m == 2)
    {
      if (q2 > 0)
        return 'b';
      else
        return 'g';
    }
    else
    {
      if (q3 > 0)
        return 'w';
      else
        return 'y';
    }
  }

  public char actualFace(char c)
  {
    if (c == 'u' || c == 'U')
    {
      return getUpFace();
    }
    else if (c == 'd' || c == 'D')
    {
      return Cube.opposite(getUpFace());
    }
    else if (c == 'f' || c == 'F')
    {
      return getFrontFace();
    }
    else if (c == 'b' || c == 'B')
    {
      return Cube.opposite(getFrontFace());
    }
    else if (c == 'r' || c == 'R')
    {
      return getRightFace();
    }
    else if (c == 'l' || c == 'L')
    {
      return Cube.opposite(getRightFace());
    }

    else
      return ' ';
  }

  public void keyPressed(KeyEvent e)
  {
    char c = e.getKeyChar();
    if (c >= 97)
      prime = false;
    else
    {
      //System.out.println("capital");
      c = (char)(c+32);
      prime = true;
    }

    char[] possible = {'u', 'd', 'f', 'b', 'l', 'r'};
    for (char ch : possible)
    {
      if (ch == c)
      {
        currentMove = c;
        break;
      }
    }
    c = actualFace(c);

    char[] keystrokes = {'w', 'y', 'r', 'o', 'g', 'b'};
    for (char ch : keystrokes)
    {
      if (c == ch)
      {
        //System.out.println("Setting currentFace to: " + c);
        currentFace = c;
        currentDepth = 0;
        return;
      }
    }

    int code = e.getKeyCode();

    if (code == KeyEvent.VK_UP)
    {
      if (currentMove == 'd' || currentMove == 'f')
      {
        if (currentDepth < size-1)
          currentDepth++;
      }
      else if (currentMove == 'u' || currentMove == 'b')
      {
        if (currentDepth > 0)
          currentDepth--;
      }
      //repaint();
    }
    else if (code == KeyEvent.VK_DOWN)
    {
      if (currentMove == 'u' || currentMove == 'b')
      {
        if (currentDepth < size-1)
          currentDepth++;
      }
      else if (currentMove == 'd' || currentMove == 'f')
      {
        if (currentDepth > 0)
          currentDepth --;
      }
      //repaint();
    }
    else if (code == KeyEvent.VK_LEFT)
    {
      if (currentMove == 'r')
      {
        if (currentDepth < size-1)
          currentDepth++;
      }
      else if (currentMove == 'l')
      {
        if (currentDepth > 0)
          currentDepth --;
      }
      //repaint();
    }
    else if (code == KeyEvent.VK_RIGHT)
    {
      if (currentMove == 'l')
      {
        if (currentDepth < size-1)
          currentDepth++;
      }
      else if (currentMove == 'r')
      {
        if (currentDepth > 0)
          currentDepth --;
      }
      //repaint();
    }
  }

  public void keyTyped(KeyEvent e)
  {
    char c = e.getKeyChar();

    if (c == 'x')
    {
      char face = getRightFace();
      cube.turnAll(face);
      repaint();
    }
    else if (c == 'X')
    {
      char face = getRightFace();
      cube.turnPAll(face);
      repaint();
    }
    else if (c == 'y')
    {
      char face = getUpFace();
      cube.turnAll(face);
      repaint();
    }
    else if (c == 'Y')
    {
      char face = getUpFace();
      cube.turnPAll(face);
      repaint();
    }
    else if (c == 'z')
    {
      char face = getFrontFace();
      cube.turnAll(face);
      repaint();
    }
    else if (c == 'Z')
    {
      char face = getFrontFace();
      cube.turnPAll(face);
      repaint();
    }
  }

  public void keyReleased(KeyEvent e)
  {

    char c = e.getKeyChar();
    if (c < 97)
    {
      c = (char)(c+32);
      prime = true;
    }
    //else
      //prime = false;

    c = actualFace(c);

    char[] keystrokes = {'w', 'y', 'r', 'o', 'g', 'b'};
    for (char ch : keystrokes)
    {
      if (c == ch)
      {
        //System.out.println(currentFace + " " + currentDepth);
        if (prime)
        {
          //System.out.println("turning prime");
          cube.turnP(currentFace, currentDepth);
          repaint();
          prime = false;
          return;
        }
        else
        {
          //System.out.println("turning");
          cube.turn(currentFace, currentDepth);
          repaint();
          return;
        }
      }
    }
  }

  public static void main(String[] args)
  {
    //cube.scramble(30);
    //cube.solve();
    CubeGraphics cg;
    if (args.length == 0)
      cg = new CubeGraphics(3, true);
    else
      cg = new CubeGraphics(Integer.parseInt(args[0]), true);
    //cube.scramble(40);
    cg.repaint();
  }
}
