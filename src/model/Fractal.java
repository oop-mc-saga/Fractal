package model;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tadaki
 */
public class Fractal {

    protected final Set<AffineTransform> transformations;
    private Set<Shape> shapes;

    /**
     * Constructor: this method is private to prevent direct instantiation
     *
     * @param affine
     */
    public Fractal(Set<AffineTransform> affine) {
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
        shapes = new HashSet<>();
        shapes.add(createSquare(false));
    }

    /**
     * Transforming the shapes
     *
     * @return
     */
    public Set<Shape> transform() {
        Set<Shape> newShapes = transform(shapes);
        shapes = newShapes;
        return newShapes;
    }

    public Set<Shape> transform(Set<Shape> shapeList) {
        Set<Shape> newShapes = new HashSet<>();
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
    public Set<Shape> getMap() {
        Set<Shape> newShapes = new HashSet<>();
        newShapes.add(createSquare(true));
        Set<Shape> shapesList = transform(newShapes);
        return shapesList;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
