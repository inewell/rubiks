import java.awt.Color;

public class Light
{
  private double[] direction;

  public Light(double[] dir)
  {
    direction = dir;
  }

  public Light()
  {
    double[] dir = {0, 0, -1};
    direction = dir;
  }

  public double[] getDir()
  {
    return direction;
  }

  public static double magnitude(double[] v)
  {
    return Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]);
  }

  public static double[] scale(double[] v, double a)
  {
    double[] v1 = {v[0]*a, v[1]*a, v[2]*a};
    return v1;
  }

  public static double[] scaleTo(double[] v, double newMag)
  {
    double s = newMag/magnitude(v);
    return scale(v, s);
  }

  public static double[] add(double[] v1, double[] v2)
  {
    double[] v4 = {v1[0]+v2[0], v1[1]+v2[1], v1[2]+v2[2]};
    return v4;
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

  public Color lightUp(Color c, double[] normal)
  {
    double[] lightReflected = add(scale(normal, -2*dot(normal, direction)), direction);
    //lightReflected = scaleTo(lightReflected, 1);
    //return new Color((int)(Math.abs(lightReflected[0])), 0, 0);
    double factor = Math.pow(Math.sin(lightReflected[0]*Math.PI/2),2)/2;
    int r = c.getRed();
    int g = c.getGreen();
    int b = c.getBlue();
    if (factor > 0)
    {
      r += (int)(factor*(255-r));
      g += (int)(factor*(255-g));
      b += (int)(factor*(255-b));
    }
    else
    {
      r -= (int)(factor*r);
      g -= (int)(factor*g);
      b -= (int)(factor*b);
    }
    if (r < 0)
      r = 0;
    if (r > 255)
      r = 255;
    if (g < 0)
      g = 0;
    if (g > 255)
      g = 255;
    if (b < 0)
      b = 0;
    if (b > 255)
      b = 255;

    return new Color(r,g,b);
  }

  public Color lightUp(Color c, double[] pt, double[] v1, double[] v2)
  {
    double[] normal = scaleTo(cross(v1, v2), 1); //unit normal vector of surface
    //choose more radial normal
    if (dot(normal, pt) < 0)
      normal = scale(normal, -1);

    double[] lightReflected = add(scale(normal, -2*dot(normal, direction)), direction);
    double factor = Math.sin(Math.PI*lightReflected[0]/2);
    int r = c.getRed();
    int g = c.getGreen();
    int b = c.getBlue();
    if (factor > 0)
    {
      r += (int)(factor*(255-r));
      g += (int)(factor*(255-g));
      b += (int)(factor*(255-b));
    }
    else
    {
      r -= (int)(factor*r);
      g -= (int)(factor*g);
      b -= (int)(factor*b);
    }
    if (r < 0)
      r = 0;
    if (r > 255)
      r = 255;
    if (g < 0)
      g = 0;
    if (g > 255)
      g = 255;
    if (b < 0)
      b = 0;
    if (b > 255)
      b = 255;

    return new Color(r,g,b);
  }
}
