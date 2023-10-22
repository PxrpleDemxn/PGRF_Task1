package Rasterize.Trivial;

import Rasterize.LineRasterizerDotted;
import Services.Interface.IRBIService;

public class LineRasterizerDottedTrivial extends LineRasterizerDotted {
    public LineRasterizerDottedTrivial(IRBIService RBIRaster) {
        super(RBIRaster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2, int spacing) {
        float k = (float) (y2 - y1) / (x2 - x1);
        float q = y1 - (k * x1);
        boolean drawDot = true;

        if (Math.abs((float) y2 - y1) < Math.abs((float) x2 - x1)) {
            if (x2 < x1) {
                int tmp = x1;
                x1 = x2;
                x2 = tmp;
            }

            for (int x = x1; x <= x2; x++) {
                int y = Math.round((k * x) + q);

                if (drawDot) {
                    RBIRaster.setPixel(x, y, 0xffff00);
                }

                drawDot = !drawDot;

                if (drawDot) {
                    int dotCounter = 0;
                    while (dotCounter < spacing && x < x2) {
                        x++;
                        dotCounter++;
                    }
                }
            }
        } else {
            if (y2 < y1) {
                int tmp = y1;
                y1 = y2;
                y2 = tmp;
            }

            for (int y = y1; y <= y2; y++) {
                int x = Math.round((y - q) / k);

                if (drawDot) {
                    if (x2 == x1) {
                        RBIRaster.setPixel(x2, y, 0xffff00);
                    } else {
                        RBIRaster.setPixel(x, y, 0xffff00);
                    }
                }

                drawDot = !drawDot;

                if (drawDot) {
                    int dotCounter = 0;
                    while (dotCounter < spacing && y < y2) {
                        y++;
                        dotCounter++;
                    }
                }
            }
        }
    }
}
