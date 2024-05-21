package model;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tadaki
 */
public class Fractal {

    protected final List<AffineTransform> transformations;
    private List<Shape> shapes;

    /**
     * Constructor: this method is private to prevent direct instantiation
     *
     * @param affine
     */
    public Fractal(List<AffineTransform> affine) {
        this.transformations = affine;
        initialize();
    }

    /**
     * Create a square.
     *
     * @param forMap
     * @return
     */
    private Shape createSquare(boolean forMap) {
        Path2D.Double square = new Path2D.Double();
        Rectangle2D.Double outer = new Rectangle2D.Double(0., 0., 1., 1.);
        square.append(outer, false);

        if (forMap) {//for drawing the map, add an inner shape
            Path2D.Double inner = new Path2D.Double();
            inner.moveTo(.1, .2);
            inner.lineTo(.3, .2);
            inner.lineTo(.3, .3);
            inner.lineTo(.4, .3);
            inner.lineTo(.4, .1);
            inner.lineTo(.1, .1);
            inner.closePath();
            square.append(inner, false);
        }
        return square;
    }

    public final void initialize() {
        shapes = new ArrayList<>();
        shapes.add(createSquare(false));
    }

    /**
     * Transforming the shapes
     *
     * @return
     */
    public List<Shape> transform() {
        List<Shape> newShapes = transform(shapes);
        shapes = newShapes;
        return newShapes;
    }

    public List<Shape> transform(List<Shape> shapeList) {
        List<Shape> newShapes = new ArrayList<>();
        for (AffineTransform af : transformations) {
            for (Shape s : shapeList) {
                Shape t = af.createTransformedShape(s);
                newShapes.add(t);
            }
        }
        return newShapes;
    }

    /**
     * Get the map
     *
     * @return
     */
    public List<Shape> getMap() {
        List<Shape> newShapes = new ArrayList<>();
        newShapes.add(createSquare(true));
        List<Shape> shapesList = transform(newShapes);
        return shapesList;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
