# AlgorithmsAssignment1 - David Walsh 20068382  

This is the application I made for the object detection assignment, the program is run via the GUI I 
have made. It has the option to choose between four different images in the gui but can be changed in 
the ConnectedComponentImage class to take in any image, though the stars image may take some time as 
it is so large. 

The class reads in an image,applies weightedQuickUnion, applies binarisation to the image, colors the components 
in the image, highlights the components with a red boundry box. It can also return the count accuratly 
for the amount of objects in the image and also one for the background. I have also provided around 60% 
or so test coverage though the tests for the colored component is not working though I feel it should.

The GUI is very basic but contains Jtextframes and Jbuttons to carry out the actions. It gives the option
between four images, allows the user to see the original,binary,color and highlighted versions of the images. 
It also returns the count.

Originaly before I had gotten the union working I researched into using Component two pass method of coloring 
the image, I researched 2D arrays and at one point created a seperate pixel class, defined a pixel and filled
a Pixel array with pixels and then change them as they go through two pass. Eventually I fixed my union but feel
the extra research into this area and even just into graph theroy in general has proven to be vital and could
be very usefull in future projects.

-David Walsh
