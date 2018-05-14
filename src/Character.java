
import java.awt.*;

public abstract class Character
{

   protected int x;
   protected int y;
   private int resetx, resety; //for reset purposes
   
// This is a cheat
   protected boolean moving = false;

   protected int direction;
   
   protected Image [] up_img;
   protected Image [] dn_img;
   protected Image [] lt_img;
   protected Image [] rt_img;
   protected Image [] a1_img;
   protected Image [] a2_img;
   protected Image [] a3_img;
   protected Image [] a4_img;
   

   public final static int UP = 0;
   public final static int DN = 1;
   public final static int LT = 2;
   public final static int RT = 3;
   public final static int A1 = 4;
   public final static int A2 = 5;
   public final static int A3 = 6;
   public final static int A4 = 7;
   
   
   protected int frames = 0;
   protected int frame = 0;
   int pacer = 0;
   protected int delay = 0;
   
   String filename;

   public final static int NUMBER_OF_DIRECTIONS = 8;

   //-------------------------------------------------------------------------//

   public Character (int x, int y, int direction, int frames, int delay)
   {
      this.x = x;
      this.y = y;
      this.resetx = x;
      this.resety = y;
      this.frames = frames;
      this.delay = delay;
      this.direction = direction % 8;
   }
   
   public Character (String filename, String extension, int x, int y, int direction, int frames, int delay)
   {
      this.x = x;
      this.y = y;
      this.resetx = x;
      this.resety = y;
      this.frames = frames;
      this.delay = delay;
      this.direction = direction % 8;
      this.filename = filename;
      
      up_img = loadImageSet(filename + "_up",extension);
      dn_img = loadImageSet(filename + "_dn",extension);
      lt_img = loadImageSet(filename + "_lt",extension);
      rt_img = loadImageSet(filename + "_rt",extension);
      a1_img = loadImageSet(filename + "_rt",extension);
      a2_img = loadImageSet(filename + "_rt",extension);
      a3_img = loadImageSet(filename + "_rt",extension);
      a4_img = loadImageSet(filename + "_rt",extension);
   }

   public void reset(){
	   this.x = this.resetx;
	   this.y = this.resety;
   }
   /**
    * 
    * @param c
    */
   public void update(Control c){
	   if(c.Left){
		   
	   }
   }
   
   public void moveUp(int dy)
   {
      direction = UP;
      y -= dy;
   }


   public void moveDn(int dy)
   {
      direction = DN;
      y += dy;
   }


   public void moveLt(int dx)
   {
      direction = LT;
      x -= dx;
      moving = true;
   }


   public void moveRt(int dx)
   {
      direction = RT;
      x += dx;
      moving = true;
   }
   /*
    * need to change the actions
    */
   public void moveA1(int dx)
   {
      direction = RT;
      x += dx;
      moving = true;
   }
   
   public void moveA2(int dx)
   {
      direction = RT;
      x += dx;
      moving = true;
   }
   public void moveA3(int dx)
   {
      direction = RT;
      x += dx;
      moving = true;
   }
   public void moveA4(int dx)
   {
      direction = RT;
      x += dx;
      moving = true;
   }
   
   
   /**
    * the most inefficient way to get collision but simple to understand
    * check if the absolute value half the width of one minus another half width
    * is 
    * @param otherC other character
    * @return if it has collided or not
    */
    //needs another way to find collisions this way does not check collision from the top
   public boolean hasCollided(Character otherC){
	   float thisW = this.lt_img[frame].getWidth(null)/2;
	   float otherW = otherC.lt_img[frame].getWidth(null)/2;
	   float thisH = this.lt_img[frame].getHeight(null)/2;
	   float otherH = otherC.lt_img[frame].getHeight(null)/2;
	   float halfX1 = (x + thisW);
	   float halfX2 = (otherC.x + otherW);
	   float halfY1 = (y + thisH) ;
	   float halfY2 = (otherC.y + otherH);
	   float height2Square = otherH + thisH;
	   
	   return (Math.abs((halfX1 - halfX2)) < thisW+otherW) 
			   && 
			   (Math.abs(halfY1- halfY2) < height2Square);
   }
   
   
   
   public void draw(Graphics g)
   {
      if (direction == UP) drawUp(g);
      else
      if (direction == DN) drawDn(g);
      else
      if (direction == LT) drawLt(g);
      else
      if (direction == RT) drawRt(g);
      else
      if (direction == A1) drawA1(g);
      else
      if (direction == A2) drawA2(g);
      else
      if (direction == A3) drawA3(g);
      else
      if (direction == A4) drawA4(g);
      else;
      if (moving)
      {
         if (pacer == delay)
         {
            frame = (frame + 1) % frames;
            pacer = 0;
         }

         pacer++;
         moving = false;
      }
      else
      {
         frame = frames;
         pacer = delay;
      }

   }


   public void drawDn(Graphics g){
	   g.drawImage(up_img[frame], x - Camera.x, y - Camera.y, null);
   }
   public void drawUp(Graphics g){
	   g.drawImage(dn_img[frame], x - Camera.x, y - Camera.y, null);
   }
   public void drawLt(Graphics g){
	   g.drawImage(lt_img[frame], x - Camera.x, y - Camera.y, null);
   }
   public void drawRt(Graphics g){
	   g.drawImage(rt_img[frame], x - Camera.x, y - Camera.y, null);
   }
   public void drawA1(Graphics g){
	   g.drawImage(a1_img[frame], x - Camera.x, y - Camera.y, null);
   }
   public void drawA2(Graphics g){
	   g.drawImage(a2_img[frame], x - Camera.x, y - Camera.y, null);
   }
   public void drawA3(Graphics g){
	   g.drawImage(a3_img[frame], x - Camera.x, y - Camera.y, null);
   }
   public void drawA4(Graphics g){
	   g.drawImage(a4_img[frame], x - Camera.x, y - Camera.y, null);
   }

   public Image[] loadImageSet(String filename, String extention)
   {
      Image[] img = new Image[frames+1];

      for (int i = 0; i < frames + 1; i++){
         img[i] = Toolkit.getDefaultToolkit().getImage(filename + "_" + i + extention);
      }
      return img;
   }
   
}

