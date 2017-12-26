# rubiks
Renders a 3D Rubik's cube of any number of layers, able to be manipulated by the user. Includes a solver for 3 by 3.

I did this project just for fun, when I should have been working on my English essay during finals week last winter.

## How to manipulate the cube
When running `CubeGraphics.java`, the program uses a preset set of keystrokes to manipulate the cube, based on standard Rubik's cube notation. It is:
* Each of the following keystrokes turn the corresponding face **clockwise 90 degrees**:
  * `R` turns the right face
  * `L` turns the left face
  * `U` turns the top face
  * `D` turns the bottom face
  * `F` turns the front face
  * `B` turns the back face
* Doing `Shift` + any of the above keystrokes will turn the corresponding face **counterclockwise 90 degrees** (i.e. `Shift` + `R` turns the right face counterclockwise 90 degrees).
* Use the arrow keys to **turn inner layers**. That is, do one of the above keystrokes to make the desired turn to the outside face parallel to the inner slice layer to turn, but while holding down those keys, push the appropriate arrow the appropriate number of times. 
  * Each push of the arrow will bring you one more layer in that direction. 
  * The arrow to use depends on which face you are turning, and makes intuitive sense as it should point towards the center of the cube.
  * For example, if you wish to turn the second-from-top face counterclockwise one turn, do `Shift` + `U` + `Down arrow`. 
  * It should be noted that you could achieve the same result by treating it as a turn in the opposite direction from the opposite face. That is, the previous example, in a 4 by 4 cube is equivalent to turning the third-from-bottom face clockwise one turn. Thus in a 4 by 4, the keystroke `D` + `Up arrow` + `Up arrow` is equivalent.
  * One more example, to turn the third-from-left slice one turn clockwise in a 5 by 5 cube, type `L` + `Right arrow` + `Right arrow`. This will be equivalent to `Shift` + `R` + `Left arrow` + `Left arrow`.
* You can also do **whole-cube rotations**. Each of the following keystrokes rotates the cube clockwise 90 degrees:
  * `X`: rotates the cube about the x-axis, which runs through the right and left faces.
  * `Y`: rotates the cube about the y-axis, which runs through the top and bottom faces.
  * `Z`: rotates the cube about the z-axis, which runs through the front and back faces.
* As with single-layer turns, preceding any of the above whole-cube-rotations will rotate it 90 degrees counterclockwise.
* The graphics in this program is set up so that one face is the top face, and you can only rotate the cube about that axis (as well as manipulate the orientation of that axis). Doing a whole-cube rotation will change the top face.
* You can also **move the cube with the mouse**. Just click and drag - it's pretty easy.
* While these keystrokes may seem complicated, once you just play around with it a bit, it will be quite easy and intuitive. Also this method makes it easy to enter in Rubik's cube algorithms, as it is a variation on standard notation.

## Project explanation
The Rubik's cube is a wonderful puzzle, simple but challenging; that is, unless you know an algorithm that some very intelligent people already figured out to solve it. Beyond the ubiquitous 3 by 3 size, there exist larger versions with more layers; typically the largest you can buy is about 10 layers. These take a lot longer, as you have to "reduce" the centers and edges to a 3 by 3 - like configuration. This program, however, can generate a cube of any size (I don't know who has enough time, nor the desire to solve such a large cube, but it's still fun to experiment with it).

In this project, I was determined to not use any outside libraries. I wrote it all in plain Java. 

This means that I did all the 3D graphics myself. That involves a lot of linear algebra. The basis of my graphics approach uses a local, movable coordinate system within a "universe" with fixed global coordinates. That is, the local coordinate axes are defined as unit vectors in the global coordinate system. However, when we look at the screen, we see a 2D surface, not a 3D scene; thus you must use a formula to project from a virtual point in 3D space to a 2D projected virtual coordinate, then transform to pixel space. When the user rotates the cube, it computes an angle of displacement in the "theta" and "phi" directions, and uses matrix transformation to rotate the local coordinate system appropriately. 

In generating the cube, you have to define the vertices in 3D space, and then fill polygons in pixel space at their corresponding locations. However, it is more complicated than that; not all faces are visible at the same time. This project does not use perspective, as that makes it more complicated still. Thus, when you look at a cube with no perspective, you see only three sides at once. For each visible side, the opposite side is blocked. Therefore, at any time you can look at your local coordinate vectors, and thus make binary decisions based on each of their directions as to which faces in each opposite pair to render. I also experimented with lighting, which takes a dot product of a directional light vector with the normal of each surface and then lightens the surface's original color appropriately.

Once you know which faces to render, then you must divide up the parallelogram that defines the visible faces into a lattice of similar parallelograms that display the colored tiles of the cube.

These faces must be manipulated when the user enters in commands to turn faces of the cube. I saved each face in a 2D Java array, each identified by its unchanging center color. When the user turns a face, it does two things: twist the top face, and rotate the side faces (when you turn an internal slice, it only does the latter). The former requires the rotation of rotation of one 2D array. The latter requires the rotation of one row between the 2D arrays corresponding to the four adjacent faces to the turned face.

## Cube-solving algorithm
In addition to rendering a fully-functional cube, I also wrote an algorithm that solves it (for 3 by 3). This algorithm has the following steps:
* Get the "white cross" on the white face, pairing the white center with its adjacent edge pieces, each placed in the proper order.
* Put the corner-edge pairs into place: pair the side edges (i.e. blue-red) with their appropriate white corner (i.e. blue-red-white), and insert it into the proper spot. This will leave the first 2 layers solved.
* Get the "yellow cross" on the top yellow face. In this case, the edges do not need to be in proper order, just have yellow facing up.
* Get all the yellow corners to have yellow face-up on the top face. This requires following a preset set of moves based on the state of the top face following the previous step. There are about 15 cases.
* Permute the top corners. This will put the corners into their proper places. This is either a swap of adjacent corners or a three-corner cycle.
* Permute the edges. This will put the top edges into their proper places. Depending on the state of the cube following the previous step, this will either swap both pairs of opposite edges, swap both pairs of adjacent edges, or cycle three adjacent edges. Now the cube is solved!

## Table of Contents
* `Cube.java`: Handles the logic of the cube, i.e. holds the colors of all the tiles in 2D arrays and manipulates these arrays appropriately to turn faces.
* `Space3D.java`: Handles general 3D graphics, according to the concepts mentioned earlier about local and global coordinate systems, rotation, and transorming 3D virtual space to 2D projection to 2D pixel space. Also handles mouse events, which rotate the local coordinate system.
* `CubeGraphics.java`: Handles the graphics of the cube, i.e. deals with all the 3D positions of vertices of the cube and individual tiles, and manipulates the tiles appropriately when faces are turned. Basically, it does all the Rubik's cube-specific graphics. Subclass of `Space3D.java`.
* `CubeSolver.java`: Contains the algorithm that solves an arbitrarily scrambled 3 by 3 cube. When it runs, first it scrambles the cube randomly. Then, it pauses. Then, it solves the cube, move-by-move, which you can watch (and also rotate it while it's going). Subclass of `CubeGraphics.java`.
* `Light.java`: Handles lighting. Used by `Space3D.java`.
* `Matrix.java`: Handles matrices and matrix multiplication. Used for rotation in `Space3D.java`.
