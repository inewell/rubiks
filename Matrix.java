public class Matrix
{
  private double[][] matrix;

  public Matrix(double[][] m)
  {
    matrix = m;
  }

  public double[] multiply(double[] v)
  {
    double[] v1 = new double[matrix.length];
    for (int i = 0; i < matrix.length; i++)
    {
      for (int j = 0; j < matrix[0].length; j++)
      {
        v1[i] += matrix[i][j] * v[j];
      }
    }
    return v1;
  }
}
