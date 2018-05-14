# Driver's Revenge (Game)

For one of my final projects in CMP 428 or video game programming, we had to make a game using the concepts we learn in class. 
Concepts Used were:

#### Parallex scrolling
As a character moves forward the background moves on the opposite direction giving a realistic feel. In parallex scrolling instead of moving all the backgrounds at the same speed we move each layer according to its depth. The farther it is the slower it will move.

#### Sprites
Sprites are animations of objects which can be characters, background objects, etc. 
Collision detection
There are many ways to implement collision detection I chose to use one that is not as proficient but it gets the job done. I used the square or rectangles of sprites and check the distances between the width and height. Basically checking 2 squares are right, left, up, and down if they are not then they must be colliding. 

#### Simple CSV reader
For this project I added a save game to save scores and other stats. Although I did not finish I implemented a simple functions to get data from a CSV reader. I got inspired by python's csv dictionary reader which can easily read without index but rather by title or string. My csv reader can get a column by title or index, get a row by index, change row, sort (only for the data of the game), and edit information. 

#### PS3 Controller
Using jinput api I was able to configure a PS3 controller to work instead of using a keyboard for controls. I was planning on having an input manager for the game but sadly I did not have time. I did finish implementing the controller but missing exceptions and handlers but this was temporarily fixed in the main java file. 

#### Preview
https://youtu.be/XcaYeK0gJIY
