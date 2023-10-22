package Rasterize;

import Model.Line;
import Services.Interface.IRBIService;

import java.awt.*;

public abstract class LineRasterizerDotted {
    protected IRBIService RBIRaster;
    Color color;

    public LineRasterizerDotted(IRBIService RBIRaster) {
        this.RBIRaster = RBIRaster;
    }

    public void rasterize(Line line, int spacing) {

        drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2(), spacing);

    }

    public void rasterize(Line line, int spacing, boolean isHorizontal) {
        if (isHorizontal) {
            drawLine(line.getX1(), line.getY1(), line.getX1(), line.getY2(),spacing);
        } else drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY1(),spacing);

    }


    protected void drawLine(int x1, int y1, int x2, int y2, int spacing) {

    }
}
