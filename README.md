# Fractals
This project showcases various affine fractals.
Affine fractals are defined through a set of affine transformations.

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
    - `createInstance()`: Takes the name of a predefined fractal (e.g. *Sierpinski*, *CantorMaze*, etc.) as input.  Then retrieves the corresponding set of transformation and initialize a `Fractal` object using these transformation and returns it.

## `gui`
- `FractalMain` class
    - This class works as the main class of the application.
    - `showMap` menu calls the `getMap()` method of the `Fractal` class for showing the visual representation of the transformations.
    - `oneStep` methods call the `transform()` method of the `Fractal` class for showing the new shapes.
    - `fractalList` comboBox provides the predefined fractals.  By selecting item, the new fractal instance is instantiated.
- `DrawPanel` class
    - THis class stores a instance of the `BufferedImage` class.
    - `draw()`: Draws the specified shapes on the image and call `repaint()` method.
