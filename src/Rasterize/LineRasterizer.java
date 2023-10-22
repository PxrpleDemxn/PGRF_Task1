package Rasterize;

import Model.Line;
import Services.Interface.IRBIService;

import java.awt.*;

public abstract class LineRasterizer {
    protected IRBIService RBIRaster;
    Color color;

    public LineRasterizer(IRBIService RBIRaster) {
        this.RBIRaster = RBIRaster;
    }

    public void rasterize(Line line) {
        drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }
    public void rasterize(Line line, boolean isHorizontal) {
        if(isHorizontal) drawLine(line.getX1(), line.getY1(), line.getX1(), line.getY2());
        else drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY1());
    }

    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        drawLine(x1, y1, x2, y2);
        this.color = color;
    }

    protected void drawLine(int x1, int y1, int x2, int y2) {

    }
}
