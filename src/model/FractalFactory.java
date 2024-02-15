package model;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 * Creating Fractal example
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
        CantorMaze,
        Crystal,
        Dragon,
        TwinChristmasTree,
        Default;
    }

    // No instance is allowed to create
    private FractalFactory() {
    }

    /**
     * Create an instance
     *
     * @param fractalName
     * @return
     */
    public static Fractal createInstance(FractalName fractalName) {
        List<AffineTransform> affineList = new ArrayList<>();
        AffineTransform half = new AffineTransform();
        half.scale(.5, .5);
        
        switch (fractalName) {
            case Sierpinski -> {
                affineList.add(half);
                AffineTransform halfAbove = (AffineTransform) half.clone();
                halfAbove.translate(Math.cos(Math.PI / 3.), 
                        Math.sin(Math.PI / 3.));
                affineList.add(halfAbove);
                AffineTransform halfRight = (AffineTransform) half.clone();
                halfRight.translate(1, 0);
                affineList.add(halfRight);
            }
            case SierpinskiLike -> {
                affineList.add(half);
                AffineTransform halfAbove = (AffineTransform) half.clone();
                halfAbove.translate(0, 1);
                affineList.add(halfAbove);
                AffineTransform halfRight = (AffineTransform) half.clone();
                halfRight.translate(1, 0);
                affineList.add(halfRight);

            }
            case SierpinskiLike1 -> {
                affineList.add(half);
                AffineTransform halfAbove = (AffineTransform) half.clone();
                halfAbove.translate(0, 1);
                affineList.add(halfAbove);
                AffineTransform halfRight = (AffineTransform) half.clone();
                halfRight.translate(2, 0);
                halfRight.rotate(Math.PI / 2.);
                affineList.add(halfRight);
            }
            case SierpinskiLike2 -> {
                affineList.add(half);
                AffineTransform halfAbove = (AffineTransform) half.clone();
                halfAbove.translate(1, 1);
                halfAbove.rotate(Math.PI / 2);
                affineList.add(halfAbove);
                AffineTransform halfRight = (AffineTransform) half.clone();
                halfRight.translate(2, 0);
                halfRight.rotate(Math.PI / 2.);
                affineList.add(halfRight);

            }
            case CantorMaze -> {
                double z = 1. / 3.;
                double r = Math.PI / 2.;
                AffineTransform left = new AffineTransform();
                left.translate(z, 0);
                left.rotate(r);
                left.scale(1., z);
                affineList.add(left);

                AffineTransform right = new AffineTransform();
                right.translate(2 * z, 1);
                right.rotate(-r);
                right.scale(1, z);
                affineList.add(right);

                AffineTransform up = new AffineTransform();
                up.translate(z, 2 * z);
                up.scale(z, z);
                affineList.add(up);
            }
            case Crystal -> {
                double l = Math.sqrt(3.) - 1;
                double s = 1. / 4;
                double r = l / 3;
                AffineTransform up = new AffineTransform();
                up.translate(.5, .5);
                up.translate(-s / 2, r - s / 2);
                up.scale(s, s);
                affineList.add(up);

                AffineTransform left = new AffineTransform();
                left.translate(.5, .5);
                left.translate(r * Math.cos(Math.PI / 6) - s / 2,
                        -r * Math.sin(Math.PI / 6) - s / 2);
                left.scale(s, s);
                affineList.add(left);

                AffineTransform right = new AffineTransform();
                right.translate(.5, .5);
                right.translate(-r * Math.cos(Math.PI / 6) - s / 2,
                        -r * Math.sin(Math.PI / 6) - s / 2);
                right.scale(s, s);
                affineList.add(right);

                AffineTransform main = new AffineTransform();
                main.translate(l * Math.cos(Math.PI / 6.), 0);
                main.rotate(Math.PI / 3);
                main.scale(l, l);
                affineList.add(main);
            }
            case Dragon -> {
                double z = 1 / Math.sqrt(3.);
                double r = Math.PI / 2.;
                AffineTransform one = new AffineTransform();
                one.translate(0., 1.);
                one.rotate(-r);
                one.scale(z, z);
                affineList.add(one);

                AffineTransform two = new AffineTransform();
                two.translate(0., z);
                two.rotate(-r);
                two.scale(z, z);
                affineList.add(two);

                AffineTransform three = new AffineTransform();
                three.translate(1 - z, (1 + z) / 2);
                three.rotate(-r);
                three.scale(z, z);
                affineList.add(three);
            }
            case TwinChristmasTree -> {
                AffineTransform left = (AffineTransform) half.clone();
                left.translate(1, 0);
                left.rotate(Math.PI / 2);
                affineList.add(left);
                AffineTransform right = (AffineTransform) half.clone();
                right.translate(1, 1);
                right.rotate(-Math.PI / 2);
                affineList.add(right);
                AffineTransform up = (AffineTransform) half.clone();
                up.translate(.5, 1);
                affineList.add(up);
            }
            default -> {
                affineList.add(new AffineTransform());
            }
        }
        return new Fractal(affineList);
    }

}
