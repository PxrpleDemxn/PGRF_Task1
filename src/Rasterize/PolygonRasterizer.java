package Rasterize;

import Model.Line;
import Model.Point;
import Model.Polygon;

public class PolygonRasterizer {
    private LineRasterizer lineRasterizer;

    public PolygonRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    public void rasterize(Polygon polygon) {
        // pokud jsou 3 body v listu tak se vykreslí čáry mezi sebou
        if (polygon.getPointsLength() < 3) return;
        for (int i = 0; i < polygon.getPointsLength(); i++) {
            int point1 = i;
            int point2 = i + 1;
            if (i == polygon.getPointsLength() - 1) {
                point2 = 0;
            }


            Point pointA = polygon.getPoint(point1);
            Point pointB = polygon.getPoint(point2);
            lineRasterizer.rasterize(new Line(pointA, pointB, 0xffff00));
        }
    }
}
