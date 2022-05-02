package just.curiosity.renderer_3d;

import just.curiosity.renderer_3d.core.point3d.Point3D;
import just.curiosity.renderer_3d.core.polygon3d.Polygon3D;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PolygonBuilder {
  public static List<Polygon3D> sphere(Color color) {
    int radius = 5;
    int density = 50;

    List<Polygon3D> polygons = new ArrayList<>();
    List<Point3D> points = new ArrayList<>();

    Point3D top = new Point3D(0, 0, radius);
    Point3D bottom = new Point3D(0, 0, -radius);

    for (int i = 0; i < density; i++) {
      double theta = Math.PI / density * i;
      double z = -Math.cos(theta) * radius;
      double currentRadius = Math.abs(Math.sin(theta) * radius);

      for (int j = 0; j < density; j++) {
        double alpha = 2 * Math.PI / density * j;
        double x = -Math.sin(alpha) * currentRadius;
        double y = Math.cos(alpha) * currentRadius;

        points.add(new Point3D(x, y, z));

        if (i == 0 && (j > 0)) {
          polygons.add(new Polygon3D(color,
            bottom,
            points.get(j - 1),
            points.get(j)));
          continue;
        }

        int index = i * density;

        if (i == density - 1 && (j > 0)) {
          polygons.add(new Polygon3D(color,
            top,
            points.get(index + j),
            points.get(index + j - 1)));

          if (j == density - 1) {
            polygons.add(new Polygon3D(color,
              top,
              points.get(index + j - (density - 1)),
              points.get(index + j)));
          }
        }

        if (!(i > 0) || !(j > 0)) {
          continue;
        }

        polygons.add(new Polygon3D(color,
          points.get(index + j - 1 - density),
          points.get(index + j - 1),
          points.get(index + j)));

        polygons.add(new Polygon3D(color,
          points.get(index + j - 1 - density),
          points.get(index + j),
          points.get(index + j - density)));

        if (j == density - 1) {
          polygons.add(new Polygon3D(color,
            points.get(index + j - density),
            points.get(index + j),
            points.get(index + j - (density - 1))));

          polygons.add(new Polygon3D(color,
            points.get(index + j - density),
            points.get(index + j - (density - 1)),
            points.get(index + j - density - (density - 1))));
        }
      }
    }

    return polygons;
  }

  public static List<Polygon3D> rgbCube() {
    List<Polygon3D> polygons = new ArrayList<>();

    double sideWidth = 10;
    double cubeWidth = 0.1;
    double gap = 0.05;
    double realWidth = (sideWidth * cubeWidth) + (gap * (sideWidth - 1));
    double realWidthHalf = realWidth / 2;

    double rgbStep = 255 / sideWidth;

    double G = 0;
    double zPos = -realWidthHalf;
    for (int z = 0; z < sideWidth; z++) {
      double yPos = -realWidthHalf;
      double B = 0;
      for (int y = 0; y < sideWidth; y++) {
        double xPos = -realWidthHalf;
        double R = 0;
        for (int x = 0; x < sideWidth; x++) {
          polygons.addAll(square(new Color((int) R, (int) G, (int) B),
            xPos,
            yPos,
            zPos,
            cubeWidth));
          xPos += cubeWidth + gap;
          R += rgbStep;
        }
        yPos += cubeWidth + gap;
        B += rgbStep;
      }

      zPos += cubeWidth + gap;
      G += rgbStep;
    }

    return polygons;
  }

  public static List<Polygon3D> square(Color color, double x, double y, double z, double width) {
    List<Polygon3D> polygons = new ArrayList<>();

    /* right wall */

    polygons.add(new Polygon3D(color,
      new Point3D(x + width, y + width, z),
      new Point3D(x + width, y + width, z + width),
      new Point3D(x, y + width, z + width)));

    polygons.add(new Polygon3D(color,
      new Point3D(x + width, y + width, z),
      new Point3D(x, y + width, z + width),
      new Point3D(x, y + width, z)));

    /* left wall */

    polygons.add(new Polygon3D(color,
      new Point3D(x, y, z),
      new Point3D(x, y, z + width),
      new Point3D(x + width, y, z + width)));

    polygons.add(new Polygon3D(color,
      new Point3D(x, y, z),
      new Point3D(x + width, y, z + width),
      new Point3D(x + width, y, z)));

    /* front wall */

    polygons.add(new Polygon3D(color,
      new Point3D(x + width, y, z),
      new Point3D(x + width, y, z + width),
      new Point3D(x + width, y + width, z + width)));

    polygons.add(new Polygon3D(color,
      new Point3D(x + width, y, z),
      new Point3D(x + width, y + width, z + width),
      new Point3D(x + width, y + width, z)));

    /* back wall */

    polygons.add(new Polygon3D(color,
      new Point3D(x, y + width, z),
      new Point3D(x, y + width, z + width),
      new Point3D(x, y, z + width)));

    polygons.add(new Polygon3D(color,
      new Point3D(x, y + width, z),
      new Point3D(x, y, z + width),
      new Point3D(x, y, z)));

    /* top wall */

    polygons.add(new Polygon3D(color,
      new Point3D(x + width, y, z + width),
      new Point3D(x, y, z + width),
      new Point3D(x, y + width, z + width)));

    polygons.add(new Polygon3D(color,
      new Point3D(x + width, y, z + width),
      new Point3D(x, y + width, z + width),
      new Point3D(x + width, y + width, z + width)));

    /* bottom wall */

    polygons.add(new Polygon3D(color,
      new Point3D(x + width, y + width, z),
      new Point3D(x, y + width, z),
      new Point3D(x, y, z)));

    polygons.add(new Polygon3D(color,
      new Point3D(x + width, y + width, z),
      new Point3D(x, y, z),
      new Point3D(x + width, y, z)));

    return polygons;
  }

  public static List<Polygon3D> loadFromFile(String path, Color color) throws IOException {
    List<Polygon3D> polygons = new ArrayList<>();
    List<Point3D> vertices = new ArrayList<>();

    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
    String line;
    while ((line = br.readLine()) != null) {
      String[] split = line.split(" ");

      if (split[0].equals("v")) {
        Point3D v = new Point3D(
          Double.parseDouble(split[1]),
          Double.parseDouble(split[2]),
          Double.parseDouble(split[3]));

        vertices.add(v);
      }

      if (split[0].equals("f")) {
        polygons.add(new Polygon3D(color,
          vertices.get(Integer.parseInt(split[1]) - 1),
          vertices.get(Integer.parseInt(split[2]) - 1),
          vertices.get(Integer.parseInt(split[3]) - 1)));
      }
    }

    return polygons;
  }
}
