package Rasterize;

import Model.Line;
import Services.Interface.IRBIService;

import java.awt.*;

public abstract class LineRasterizer {
    protected IRBIService RBIRaster;

    public LineRasterizer(IRBIService RBIRaster) {
        this.RBIRaster = RBIRaster;
    }

    public void rasterize(Line line) {
        drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    protected void drawLine(int x1, int y1, int x2, int y2) {

    }
}
