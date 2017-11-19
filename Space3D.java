import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import java.lang.Math;

public class Space3D extends JFrame implements MouseListener, MouseMotionListener
{
  private static int width = 700;
  private static int height = width+20;
  private static int axesMargin = 30;

  private double rho;

  private double arrowL;
  private double arrowR;
  private int numTics;
  private double ticL;

  private double[] xAxis = {1, 0, 0};
  private double[] yAxis = {0, 1, 0};
  private double[] zAxis = {0, 0, 1};

  private int mouseX;
  private int mouseY;
  private int mouseXi;
  private int mouseYi;
  private double theta;
  private double phi;
  private Matrix zrm;
  private Matrix yrm;

  private double scalar;

  private boolean justMoved = true;

  private double[] direction = {-1*Math.sqrt(3)/3, -1*Math.sqrt(3)/3, -1*Math.sqrt(3)/3};
  private Light light = new Light(direction);

  public Space3D(double rho)
  {
    this.rho = rho;

    arrowL = rho/12;
    arrowR = arrowL/4;
    numTics = 4;
    ticL = arrowR;

    scalar = (width-2*axesMargin)/(2*rho);
    setSize(width,height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  public static int getW()
  {
    return width;
  }

  public static int getH()
  {
    return height;
  }

  public boolean getJustMoved()
  {
    return justMoved;
  }

  public void paint(Graphics g)
  {
    g.setColor(Color.LIGHT_GRAY);
    g.fillRect(0, 20, width, height);
  }

  public double getRho()
  {
    return rho;
  }

  public Light getLight()
  {
    return light;
  }

  public double[] getXAxis()
  {
    return xAxis;
  }

  public double[] getYAxis()
  {
    return yAxis;
  }

  public double[] getZAxis()
  {
    return zAxis;
  }

  public static double[] scale(double[] v, double a)
  {
    double[] v1 = {v[0]*a, v[1]*a, v[2]*a};
    return v1;
  }

  public static double[] scale(double x, double y, double z, double a)
  {
    double[] v1 = {a*x, a*y, a*z};
    return v1;
  }

  public static double[] scaleTo(double[] xyz, double newMag)
  {
    double m = Math.sqrt(xyz[0]*xyz[0] + xyz[1]*xyz[1] + xyz[2]*xyz[2]);
    double s = newMag/m;
    return scale(xyz, s);
  }

  public static double[] add(double[] v1, double[] v2, double[] v3)
  {
    double[] v4 = {v1[0]+v2[0]+v3[0], v1[1]+v2[1]+v3[1], v1[2]+v2[2]+v3[2]};
    return v4;
  }

  public static double[] add(double[] v1, double[] v2)
  {
    double[] v3 = {v1[0]+v2[0], v1[1]+v2[1], v1[2]+v2[2]};
    return v3;
  }

  public static double dot(double[] v1, double[] v2)
  {
    double v3 = v1[0]*v2[0] + v1[1]*v2[1] + v1[2]*v2[2];
    return v3;
  }

  public static double[] cross(double[] v1, double[] v2)
  {
    double[] v3 = {v1[1]*v2[2]-v1[2]*v2[1],
                   v1[2]*v2[0]-v1[0]*v2[2],
                   v1[0]*v2[1]-v1[1]*v2[0]};
    return v3;
  }

  public static double[] subtract(double[] v1, double[] v2)
  {
    double[] v = {v1[0]-v2[0], v1[1]-v2[1], v1[2]-v2[2]};
    return v;
  }

  public int[] add(int[] p1, int[] p2, int[] p3)
  {
    int[] p4 = {p1[0]+p2[0]+p3[0], p1[1]+p2[1]+p3[0]};
    return p3;
  }

  public double[] twoDCoords(double[] xyz)
  {
    double a = Math.pow(2, xyz[0]/(2*rho));
    double[] xyzA = add(scale(xAxis, xyz[0]), scale(yAxis, xyz[1]), scale(zAxis, xyz[2]));
    xyzA = scale(xyzA, a);

    double[] xy = {xyzA[1], xyzA[2]};
    return xy;
  }

  public int[] pCoords(double[] xyz)
  {
    double[] xyzA = add(scale(xAxis, xyz[0]), scale(yAxis, xyz[1]), scale(zAxis, xyz[2]));
    //double a = Math.pow(2, xyzA[0]/(5*rho));
    //xyzA = scale(xyzA, a);

    double[] xy = {xyzA[1], xyzA[2]};

    int[] p = {(int)(scalar * (xy[0] + rho))+axesMargin,
                  (int)(scalar * (rho - xy[1]))+axesMargin + 20};
    return p;
  }

  public int[] pCoords(double x, double y, double z)
  {
    double[] xyzA = add(scale(xAxis, x), scale(yAxis, y), scale(zAxis, z));
    //double a = Math.pow(2, xyzA[0]/(5*rho));
    //xyzA = scale(xyzA, a);

    double[] xy = {xyzA[1], xyzA[2]};

    int[] p = {(int)(scalar * (xy[0] + rho))+axesMargin,
                  (int)(scalar * (rho - xy[1]))+axesMargin + 20};
    return p;
  }

  public double[] globalCoords(double[] xyz)
  {
    double[] xyzA = add(scale(xAxis, xyz[0]), scale(yAxis, xyz[1]), scale(zAxis, xyz[2]));
    return xyzA;
  }

  public static Matrix zRotMatrix(double t)
  {
    double[][] m = {{Math.cos(t), -1*Math.sin(t), 0},
                    {Math.sin(t), Math.cos(t)   , 0},
                    {0          , 0             , 1}};
    return new Matrix(m);
  }

  public static Matrix yRotMatrix(double t)
  {
    double[][] m = {{Math.cos(t), 0, -1*Math.sin(t)},
                    {0          , 1, 0             },
                    {Math.sin(t), 0, Math.cos(t)   }};
    return new Matrix(m);
  }

  public static int max(double a, double b, double c)
  {
    if (a > b)
    {
      if (a > c)
        return 1;
      else
        return 3;
    }
    else
    {
      if (b > c)
        return 2;
      else
        return 3;
    }
  }

  public void mouseDragged(MouseEvent e)
  {
    mouseX = e.getX();
    mouseY = e.getY();

    theta = -2*Math.atan((mouseX-mouseXi)/(rho*scalar));
    phi = -2*Math.atan((mouseY-mouseYi)/(rho*scalar));

    if (zAxis[2] < 0)
      theta *= -1;

    //System.out.println("mouseXi: " + mouseXi + " MouseX: " + mouseX + " theta: " + theta);
    //System.out.println("y*z " + dot(yAxis, zAxis));

    //zrm = zRotMatrix(theta);
    yrm = yRotMatrix(phi);

    //xAxis = zrm.multiply(xAxis);
    //yAxis = zrm.multiply(yAxis);
    //zAxis = zrm.multiply(zAxis);
    double[] temp = xAxis;
    xAxis = add(scale(xAxis, Math.cos(theta)), scale(yAxis, -1*Math.sin(theta)));
    yAxis = add(scale(yAxis, Math.cos(theta)), scale(temp, Math.sin(theta)));

    xAxis = yrm.multiply(xAxis);
    yAxis = yrm.multiply(yAxis);
    zAxis = yrm.multiply(zAxis);

    repaint();
    mouseXi = e.getX();
    mouseYi = e.getY();
  }

  public void mouseMoved(MouseEvent e)
  {

  }

  public void mousePressed(MouseEvent e)
  {
    justMoved = true;
    mouseXi = e.getX();
    mouseYi = e.getY();
  }

  public void mouseReleased(MouseEvent e)
  {
    justMoved = false;
  }

  public void mouseClicked(MouseEvent e)
  {

  }

  public void mouseEntered(MouseEvent e)
  {

  }

  public void mouseExited(MouseEvent e)
  {

  }
}
