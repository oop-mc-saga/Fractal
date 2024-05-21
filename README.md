# Fractals
This project showcases various affine fractals.
Affine fractals are defined through a set of affine transformations.
An affine transformation is a linear transformation followed by a translation and can be represented as a $3\times3$ matrix.
$$
\begin{align*}
\begin{pmatrix}
x'\\y'\\1
\end{pmatrix}
&=
\begin{pmatrix}
r\cos\phi& -s\sin\psi&e\\
r\sin\phi& s\cos\psi&f\\
0&0&1
\end{pmatrix}
\begin{pmatrix}
x\\y\\1
\end{pmatrix}
\end{align*}
$$

# packages
## `model`
- `Fractal` class
    - This class encapsulates the core materials for generating affine fractals.
        - `transformations`: Stores a list of affine transformations defining the fractal.
        - `shapes`: Maintains a sequence of shapes, starting with a unit square.
    - `transform()`: Applies each transformation sequentially to every shape in the `shapes`.
    The resulting transformed shapes are replaced to the `shapes`. This method returns the new shape list.
    - `getMap()`: Returns the list of shapes, effectively forming visual representation of the affine transformations.
- `FractalFactory` class
    - You need to give a list of affine transformations for defining a concrete example of fractal.
    - This class serves as a builder for creating specific fractal instances.
    - `createInstance()`: Takes the name of a predefined fractal (e.g. *Sierpinski*, *CantorMaze*, etc.) as input.  Then retrieves the corresponding set of transformation and initializes a `Fractal` object using these transformation and returns it.

## `gui`
- `FractalMain` class
    - This class is an extention of `JFrame` class and works as the main class of the application.
    - `showMap` menu calls the `getMap()` method of the `Fractal` class for showing the visual representation of the transformations.
    - `oneStep` methods call the `transform()` method of the `Fractal` class for showing the new shapes by applying the transformations.
    - `fractalList` comboBox provides the predefined fractals.  By selecting item, the new fractal instance is instantiated.
- `DrawPanel` class
    - This class stores a instance of the `BufferedImage` class for drawing fractals.
    - `draw()`: Draws the specified shapes on the image and call `repaint()` method for displaying the shapes.
