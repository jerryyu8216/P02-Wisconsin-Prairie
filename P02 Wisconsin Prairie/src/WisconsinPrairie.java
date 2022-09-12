//////////////// P02 Wisconsin Prairie //////////////////////////
//
// Title:    Wisconsin Prairie
// Course:   CS 300 Fall 2020
//
// Author:   Jerry Yu
// Email:    jcyu4@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;
public class WisconsinPrairie {
 private static PApplet processing; // PApplet object that represents the graphic
 // interface of the WisconsinPrairie application
 private static PImage backgroundImage; // PImage object that represents the 
 // background image
 private static Cow[] cows; // array storing the current cows present
 // in the Prairie
 private static Random randGen; // Generator of random numbers
 
 public static void main(String[] args) {
  Utility.startApplication(); // starts the application and calls all the methods
 }
 
/**
 * Defines the initial environment properties of the application
 * @param processingObj represents a reference to the graphical interface of
 * the application
 */
 public static void setup(PApplet processingObj) {
  processing = processingObj; // initialize the processing field to the one passed
                    //  into the input argument parameter.
  //initialize and load the image of the background
  backgroundImage = processing.loadImage("images/background.png");
  //Draw the background image at the center of the screen
  //width [resp.height] : System variable of the processing library that stores
  //the width [resp.height] of the display window.
  cows = new Cow[10];
 }
 
/**
 * Draws and updates the application display window.
 * This callback method called in an infinite loop.
 */
 public static void draw() { 
  // continually draws the background image to update the background
  processing.image(backgroundImage, processing.width / 2, processing.height / 2); 
  for(int i = 0; i < cows.length; i++) {
   // draws the cows in the array cows[]
    if(cows[i] != null) { 
      cows[i].draw();
   }
   // sees if a cow is being dragged and sets the position of the cow to the position of the mouse
   else if(cows[i] != null && cows[i].isDragging() == true){
     cows[i].setPositionX(processing.mouseX);
     cows[i].setPositionY(processing.mouseY);
     cows[i].draw();
   }
  }
 }
 
/**
 * Checks if the mouse is over a given cow whose reference is provided
 * as input parameter
 * 
 * @param cow reference to a given cow object
 * @return true if the mouse is over the given cow object (i.e. over
 * the image of the cow), false otherwise
 */
 public static boolean isMouseOver(Cow cow) { 
   // sees if the mouse is over a given cow's position
   if(processing.mouseX < cow.getPositionX() + (cow.getImage().width / 2) && 
       processing.mouseX > cow.getPositionX() - (cow.getImage().width / 2) &&
       processing.mouseY < cow.getPositionY() + (cow.getImage().height / 2) &&
       processing.mouseY > cow.getPositionY() - (cow.getImage().height / 2)){
     return true;
   }
   return false;
 }
 
/**
 * Callback method called each time the user presses the mouse
 */
 public static void mousePressed() {
  // sees if the mouse is over a cow and sets isDragging to true
  for(int i = 0; i < cows.length; i++)
   if(cows[i] != null && isMouseOver(cows[i]) == true) {
    cows[i].setDragging(true);
    break;
  }
 }
/**
 * Callback method called each time the mouse is released
 */
 public static void mouseReleased() {
  for(int i = 0; i < cows.length; i++) {
   // stops all dragging
   if(cows[i] != null) {
     cows[i].setDragging(false);
   }
  }
 }
/**
 * Callback method called each time the user presses a key
 */
 public static void keyPressed() {randGen = new Random();
  // checks if the key c is pressed
  if(processing.key == 'c'||processing.key == 'C') {
   // if c is pressed and there is space in the array then adds a cow at a random position
   for(int i = 0; i < cows.length; i++) {
    if(cows[i] == null) {
     cows[i] = new Cow(processing, (float)randGen.nextInt(processing.width), 
         (float)randGen.nextInt(processing.height));
     break;
    }
   }
  }
  // checks if the key d is pressed
  if(processing.key == 'd'||processing.key == 'D') {
   // if d is pressed and the mouse is over a cow it deletes that cow
   for(int i = 0; i < cows.length; i++) {
    if(cows[i] != null && isMouseOver(cows[i]) == true) {
      cows[i] = null;
      break;
    }
   }
  }
 }
}
