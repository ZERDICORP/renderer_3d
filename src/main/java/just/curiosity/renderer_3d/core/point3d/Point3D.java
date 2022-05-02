package just.curiosity.renderer_3d.core.point3d;

public class Point3D {
  public double x;
  public double y;
  public double z;

  public Point3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Point3D(Point3D otherPoint) {
    x = otherPoint.x;
    y = otherPoint.y;
    z = otherPoint.z;
  }
}
