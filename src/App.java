import Model.Line;
import Model.Point;
import Model.Polygon;
import Rasterize.LineRasterizer;
import Rasterize.LineRasterizerDotted;
import Rasterize.PolygonRasterizer;
import Rasterize.Trivial.LineRasterizerDottedTrivial;
import Rasterize.Trivial.LineRasterizerTrivial;
import Services.RBIService;
import Services.SaveImageService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class App {
    //region VARIABLES
    private final JPanel canvas;
    private final RBIService RBIRaster;
    private final LineRasterizer lineRasterizer;
    private final LineRasterizerDotted lineRasterizerDotted;
    private final PolygonRasterizer polygonRasterizer;

    private Point startPoint;
    private Point currentPoint;
    Polygon polygon = new Polygon();
    private int selection = 1;
    private int dotSpacing = 5;
    private boolean allowAlignment = false;
    private final SaveImageService saveImageService = new SaveImageService();
    //endregion

    public App(int width, int height) {
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF - Task 1");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        RBIRaster = new RBIService(width, height);
        lineRasterizer = new LineRasterizerTrivial(RBIRaster);
        lineRasterizerDotted = new LineRasterizerDottedTrivial(RBIRaster);
        polygonRasterizer = new PolygonRasterizer(lineRasterizer);

        canvas = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };
        canvas.setPreferredSize(new Dimension(width, height));

        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        canvas.requestFocus();
        canvas.requestFocusInWindow();

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    clearEverything();
                    allowAlignment = false;
                } else if (e.getKeyCode() == KeyEvent.VK_Z) {
                    selection = 1;
                } else if (e.getKeyCode() == KeyEvent.VK_X) {
                    selection = 2;
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    clearEverything();
                    selection = 3;
                    allowAlignment = false;
                } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    allowAlignment = true;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    saveImageService.saveImage(RBIRaster);
                }

                if (e.getKeyCode() == KeyEvent.VK_P) {
                    dotSpacing += 5;
                }
                if (e.getKeyCode() == KeyEvent.VK_O) {
                    if (dotSpacing >= 10) dotSpacing -= 5;
                }
            }
        });

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    allowAlignment = false;
                }
            }
        });

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (selection == 1 || selection == 2) {
                    startPoint = new Point(e.getX(), e.getY());
                    currentPoint = startPoint;
                } else if (selection == 3) {
                    clear(0x000000);
                    Point point = new Point(e.getX(), e.getY());
                    polygon.addPoint(point);
                    polygonRasterizer.rasterize(polygon);
                    canvas.repaint();
                }

            }
        });
        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selection == 1 || selection == 2) {

                    clear(0x000000);
                    currentPoint = new Point(e.getX(), e.getY());
                    if (allowAlignment) {
                        if (calculateK() > 1) {
                            Line line = new Line(startPoint.x, startPoint.y, startPoint.x, currentPoint.y, 0xff0000);
                            if (selection == 1) {
                                lineRasterizer.rasterize(line);
                            } else lineRasterizerDotted.rasterize(line, dotSpacing);


                        } else {
                            Line line = new Line(startPoint.x, startPoint.y, currentPoint.x, startPoint.y, 0xff0000);
                            if (selection == 1) {
                                lineRasterizer.rasterize(line);
                            } else lineRasterizerDotted.rasterize(line, dotSpacing);
                        }
                    } else {
                        Line line = new Line(startPoint, currentPoint, 0xff0000);
                        if (selection == 1) {
                            lineRasterizer.rasterize(line);
                        }


                        if (selection == 2) {
                            lineRasterizerDotted.rasterize(line, dotSpacing);
                        }

                    }


                    canvas.repaint();
                }

            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App(800, 600).start());

    }

    private float calculateK() {
        return Math.abs((float) (currentPoint.y - startPoint.y) / (currentPoint.x - startPoint.x));
    }

    public void clear(int color) {
        RBIRaster.setClearColor(color);
        RBIRaster.clear();
    }

    public void present(Graphics graphics) {
        RBIRaster.repaint(graphics);
    }

    private void start() {
        clear(0x000000); // black
        canvas.repaint();
    }

    private void clearEverything() {
        RBIRaster.clear();
        polygon.clear();
        canvas.repaint();
    }

}
