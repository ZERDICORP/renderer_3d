package just.curiosity.renderer_3d.core.polygon3d;

import just.curiosity.renderer_3d.core.point3d.Point3D;
import just.curiosity.renderer_3d.core.vector3d.Vector3D;
import just.curiosity.renderer_3d.core.vector3d.Vector3DUtil;

import java.awt.*;

public class Polygon3D {
  public Color color;
  public Point3D v1;
  public Point3D v2;
  public Point3D v3;
  private boolean blocked;

  public Polygon3D(Color color, Point3D v1, Point3D v2, Point3D v3) {
    this.color = color;
    this.v1 = new Point3D(v1);
    this.v2 = new Point3D(v2);
    this.v3 = new Point3D(v3);
  }

  public Vector3D normalVector() {
    return Vector3DUtil.normalize(Vector3DUtil.cross(
      new Vector3D(v2, v1),
      new Vector3D(v3, v1)));
  }

  public synchronized boolean isBlocked() {
    return blocked;
  }

  public synchronized void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }
}
