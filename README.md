# mars-rover

Incremental Kata - no peeping ahead!
This is an incremental kata to simulate a real business situation: work your way through the steps in order, but do not read the next requirement before you have finished your current one.

1. You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
2. The rover receives a character array of commands.
3. Implement commands that move the rover forward/backward (f,b).
4. Implement commands that turn the rover left/right (l,r).
5. Implement wrapping at edges. But be careful, planets are spheres.
   6. conflict in requirements between original problem description and kata, going with original as it's comprehensive
   7. instead of a sphere with one rover there is a flat plateau with multiple rovers.
   8. input to the program will be the upper right coordinates of the plateau, the location/orientation of the rover(s) and the movements the rover(s) should take
9. Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point, aborts the sequence and reports the obstacle.
   10. the only obstacle mentioned would be other rovers or the edges of the plateau

thoughts on how to implement obstacle detection
- the current structure of the code means the rover handles its own moves and the plateau validates its current location.
- one solution is to have the plateau call each rover for each command, validate and roll back on obstacle.
  - this would require keeping the previous state of the rover and reverting on invalid coordinate, OR creating the new coordinates without updating the rovers coords, validating and then either aborting the move or updating thr rover.
- another solution would be to allow the rovers to access plateau data about the bounds of the plateau and the coords of the other rovers
  - would be messy to just give every rover all the data, at that point the plateau object is useless
  - could do each rover movement, pass current data to rover, let rover decide where it's final coordinates are, pass updated coords to next rover?
  - makes more sense to have rover validation on the rover
  - wouldn't have to store state and revert, just don't do invalid moves in the first place
  - might be able to refactor this into a nicer solution

further work:
- command line app with a fun ascii representation of the moving rovers
- some more refactoring of the core logic as it's pretty verbose 