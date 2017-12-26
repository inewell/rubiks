# rubiks
Renders a 3D Rubik's cube of any number of layers, able to be manipulated by the user. Includes a solver for 3 by 3.

I did this project just for fun, when I should have been working on my English essay during finals week last winter.

The Rubik's cube is a wonderful puzzle, simple but challenging; that is, unless you know an algorithm that some very intelligent people already figured out to solve it. Beyond the ubiquitous 3 by 3 size, there exist larger versions with more layers; typically the largest you can buy is about 10 layers. These take a lot longer, as you have to "reduce" the centers and edges to a 3 by 3 - like configuration. This program, however, can generate a cube of any size (I don't know who has enough time, nor the desire to solve such a large cube, but it's still fun to experiment with it).

In this project, I was determined to not use any outside libraries. I wrote it all in plain Java. 

This means that I did all the 3D graphics myself. That involves a lot of linear algebra. The basis of my graphics approach uses a local, movable coordinate system within a "universe" with fixed global coordinates. That is, the local coordinate axes are defined as unit vectors in the global coordinate system. However, when we look at the screen, we see a 2D surface, not a 3D scene; thus you must use a formula to project from a virtual point in 3D space to a 2D projected virtual coordinate, then transform to pixel space.

In generating the cube, you have to define the vertices in 3D space, and then fill polygons in pixel space at their corresponding locations. However, it is more complicated than that; not all faces are visible at the same time. This project does not use perspective, as that makes it more complicated still. Thus, when you look at a cube with no perspective, you see only three sides at once. For each visible side, the opposite side is blocked. Therefore, at any time you can look at your local coordinate vectors, and thus make binary decisions based on each of their directions as to which faces in each opposite pair to render. I also experimented with lighting, which takes a dot product of a directional light vector with the normal of each surface and then lightens the surface's original color appropriately.

Once you know which faces to render, then you must divide up the parallelogram that defines the visible faces into a lattice of similar parallelograms that display the colored tiles of the cube.

These faces must be manipulated when the user enters in commands to turn faces of the cube. I saved each face in a 2D Java array, each identified by its unchanging center color. When the user turns a face, it does two things: twist the top face, and rotate the side faces (when you turn an internal slice, it only does the latter). The former requires the rotation of rotation of one 2D array. The latter requires the rotation of one row between the 2D arrays corresponding to the four adjacent faces to the turned face.

In addition to rendering a fully-functional cube, I also wrote an algorithm that solves it (for 3 by 3). This algorithm has the following steps:
* Get the "white cross" on the white face, pairing the white center with its adjacent edge pieces, each placed in the proper order.
* Put the corner-edge pairs into place: pair the side edges (i.e. blue-red) with their appropriate white corner (i.e. blue-red-white), and insert it into the proper spot. This will leave the first 2 layers solved.
* Get the "yellow cross" on the top yellow face. In this case, the edges do not need to be in proper order, just have yellow facing up.
* Get all the yellow corners to have yellow face-up on the top face. This requires following a preset set of moves based on the state of the top face following the previous step. There are about 15 cases.
* Permute the top corners. This will put the corners into their proper places. This is either a swap of adjacent corners or a three-corner cycle.
* Permute the edges. This will put the top edges into their proper places. Depending on the state of the cube following the previous step, this will either swap both pairs of opposite edges, swap both pairs of adjacent edges, or cycle three adjacent edges. Now the cube is solved!

# How to manipulate the cube
When running `CubeGraphics.java`, the program uses a preset set of keystrokes to manipulate the cube. It is:

