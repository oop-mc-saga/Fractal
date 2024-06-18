package model;

import java.awt.geom.AffineTransform;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tadaki
 */
public class FractalFactory {
    // Fractals available in this application

    public static enum FractalName {
        Sierpinski,
        SierpinskiLike,
        SierpinskiLike1,
        SierpinskiLike2,
        Koch,
        CantorMaze,
        Crystal,
        Dragon,
        TwinChristmasTree,
        Default;
    }

    /**
     * Create an instance by fractal name
     *
     * @param fractalName
     * @return
     */
    public static Fractal createInstance(FractalName fractalName) {
        Set<AffineTransform> affineList = new HashSet<>();

        switch (fractalName) {
            case Sierpinski -> {
                double r = 1. / 2.;
                affineList.add(createTransformation(r, r, 0, 0, 0, 0));
                affineList.add(createTransformation(r, r, 0, 0, r, 0));
                affineList.add(createTransformation(r, r, 0, 0, 1. / 4,
                        Math.sqrt(4) / 4));
            }
            case SierpinskiLike -> {
                double r = 1. / 2.;
                affineList.add(createTransformation(r, r, 0, 0, 0, 0));
                affineList.add(createTransformation(r, r, 0, 0, r, 0));
                affineList.add(createTransformation(r, r, 0, 0, 0, r));
            }
            case SierpinskiLike1 -> {
                double r = 1. / 2.;
                double phi = Math.PI / 2;
                affineList.add(createTransformation(r, r, 0, 0, 0, 0));
                affineList.add(createTransformation(r, r, phi, phi, 2 * r, 0));
                affineList.add(createTransformation(r, r, 0, 0, r, r));
            }
            case SierpinskiLike2 -> {
                double r = 1. / 2.;
                double phi = Math.PI / 2;
                affineList.add(createTransformation(r, r, 0, 0, 0, 0));
                affineList.add(createTransformation(r, r, phi, phi, 2 * r, 0));
                affineList.add(createTransformation(r, r, phi, phi, r, r));
            }
            case Koch->{
                double r = 1. / 3.;
                double phi = Math.PI / 3;

                affineList.add(createTransformation(r, r, 0, 0, 0, 0));
                affineList.add(createTransformation(r, r, phi, phi, r, 0));
                affineList.add(createTransformation(r, r, -phi, -phi, .5, r*Math.sin(phi)));                
                affineList.add(createTransformation(r, r, 0, 0, 2*r, 0));
            }
            case CantorMaze -> {
                double r = 1. / 3.;
                double phi = Math.PI / 2;
                affineList.add(createTransformation(1, r, phi, phi, r, 0));
                affineList.add(createTransformation(r, r, 0, 0, r, 2 * r));
                affineList.add(createTransformation(1, r, -phi, -phi, 2 * r, 1));
            }
            case Crystal -> {
                double phi = Math.PI / 3.;
                double l = Math.sqrt(3.) - 1;
                double s = 1. / 4;
                double r = l / 3;
                affineList.add(createTransformation(s, s, 0, 0,
                        .5 - s / 2, .5 + r - s / 2));
                affineList.add(createTransformation(s, s, 0, 0,
                        .5 + r * Math.cos(Math.PI / 6) - s / 2,
                        .5 - r * Math.sin(Math.PI / 6) - s / 2));
                affineList.add(createTransformation(s, s, 0, 0,
                        .5 - r * Math.cos(Math.PI / 6) - s / 2,
                        .5 - r * Math.sin(Math.PI / 6) - s / 2));
                affineList.add(createTransformation(l, l, phi, phi,
                        l * Math.cos(phi / 2), 0));
            }
            case Dragon -> {
                double phi = Math.PI / 2.;
                double x = .55;
                affineList.add(createTransformation(x, x, -phi, -phi, 0, 1));
                affineList.add(createTransformation(x, x, -phi, -phi, 0, x));
                affineList.add(createTransformation(x, x, -phi, -phi,
                        1 - x, (1 + x) / 2));
            }
            case TwinChristmasTree -> {
                double phi = Math.PI / 2.;
                double r = .5;
                affineList.add(createTransformation(r, r, phi, phi, 1. / 2, 0));
                affineList.add(createTransformation(r, r, 0, 0, 1. / 4, 1. / 2));
                affineList.add(createTransformation(r, r, -phi, -phi,
                        1. / 2, 1. / 2));
            }
            default -> {
                affineList.add(new AffineTransform());
            }
        }
        return new Fractal(affineList);
    }

    /**
     * Create a affine transformation
     *
     * @param r
     * @param s
     * @param phi
     * @param psi
     * @param e
     * @param f
     * @return
     */
    public static AffineTransform createTransformation(
            double r, double s, double phi, double psi,
            double e, double f) {
        return new AffineTransform(r * Math.cos(phi), r * Math.sin(phi),
                -s * Math.sin(psi), s * Math.cos(psi), e, f);
    }
}
