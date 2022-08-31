/*Matrix Library class created by @Devansh Maruti Saraf on 30/08/2022 updated on 31/08/2022
  Author- Devansh Maruti Saraf
  Purpose- to provide library class with all Matrix related operations
  Permission- free to use for all purposes (must mention documentation in source)
*/
public class Matrix
{
    double[][] matrix;
    Matrix(int rows,int columns)
    {
        matrix=new double[rows][columns];
    }
    public static double det(double[][] mat)
    {
        if(!isSquare(mat))return 0;
        int n=mat.length;
        if(n==1)return mat[0][0];
        double det=0;
        for(int j=0;j<n;j++)
        det+= mat[0][j]*cofactor(mat,0,j);
        return det;
    }
    public static double[][] pow(double[][] mat, int pow)
    {
        double[][] ret=identity(mat.length,mat[0].length);
        for(int i=0;i<Math.abs(pow);i++)
        ret=mult(ret,mat);
        return pow<0?inv(ret):ret;
    }
    public static double[][] inv(double[][] mat)
    {
        return div(adj(mat),det(mat));
    }
    public static double[][] cofactors(double[][] mat)
    {
        double[][] ret=new double[mat.length][mat[0].length];
        for(int i=0;i<ret.length;i++)
        for(int j=0;j<ret[0].length;j++)
        ret[i][j]=cofactor(mat,i,j);
        return ret;
    }
    public static double[][] trans(double[][] mat)
    {
       double[][] ret=new double[mat[0].length][mat.length];
       for(int i=0;i<ret.length;i++)
       for(int j=0;j<ret[0].length;j++)
       ret[i][j]=mat[j][i];
       return ret;
    }
    public static double cofactor(double[][] mat,int i,int j)
    {
        if(!isSquare(mat))return 0;
        return Math.pow(-1,i+j)*minor(mat,i,j);
    }
    public static double minor(double[][] mat,int r,int c)
    {
        if(!isSquare(mat))return 0;
        return det(cut(mat,r,c));
    }
    public static double[][] cut(double[][] mat,int r,int c)
    {
        double[][] ret=new double[mat.length-1][mat[0].length-1];
        for(int i=0,a=0;i<ret.length;i++,a++)
        {
        if(a==r)a++;
        for(int j=0,b=0;j<ret[0].length;j++,b++)
         {
             if(b==c)b++;ret[i][j]=mat[a][b];
         }
       }
        return ret;
    }
    public static double[][] adj(double[][] mat)
    {
     return trans(cofactors(mat));
    }
    public static double[][] mult(double[][] mat,double factor)
    {
      double[][] ret=new double[mat.length][mat[0].length];
      for(int i=0;i<ret.length;i++)for(int j=0;j<ret[0].length;j++)ret[i][j]=factor*mat[i][j];
      return ret;
    }
    public static double[][] div(double[][] mat,double factor)
    {
      return mult(mat,1.0/factor);
    }
    public static double[][] mult(double mat1[][],double[][] mat2)
    {
        if(mat1[0].length!=mat2.length)return null;
        double ret[][]=new double[mat1.length][mat2[0].length];
        for(int i=0;i<ret.length;i++)
        for(int j=0;j<ret[0].length;j++)
        ret[i][j]=Multsum(mat1[i],col(mat2,j));
        return ret;
    }
    private static double Multsum(double[] ar1,double[] ar2)
    {
        if(ar1.length!=ar2.length)return 0;
        double sum=0;
        for(int i=0;i<ar1.length;i++)sum+=ar1[i]*ar2[i];
        return sum;
    }
    public static double[] col(double[][] mat,int j)
    {
        double[] ret=new double[mat.length];
        for(int i=0;i<ret.length;i++)ret[i]=mat[i][j];
        return ret;
    }
    public static double[][] add(double mat1[][],double[][] mat2)
    {
      if(mat1.length!=mat2.length||mat1[0].length!=mat2[0].length)return null;
      double[][] ret=new double[mat1.length][mat1[0].length];
      for(int i=0;i<ret.length;i++)
      for(int j=0;j<ret[0].length;j++)
      ret[i][j]=mat1[i][j]+mat2[i][j];
      return ret;
    }
    public static double[][] sub(double mat1[][],double[][] mat2)
    {
      if(mat1.length!=mat2.length ||mat1[0].length!=mat2[0].length)return null;
      double[][] ret=new double[mat1.length][mat2[0].length];
      for(int i=0;i<ret.length;i++)for(int j=0;j<ret[0].length;j++)ret[i][j]=mat1[i][j]-mat2[i][j];
      return ret;
    }
    public static double[][] div(double mat1[][],double[][] mat2)
    {
      return mult(mat1,inv(mat2));
    }
    public static boolean isSquare(double[][] mat)
    {
        return mat.length==mat[0].length&&mat.length>=1? true:false;
    }
    public static double[][] identity(int r,int c)
    {
        double[][] ret= new double[r][c];
        for(int i=0;i<r&&i<c;i++)ret[i][i]=1;
        return ret;
    }
}