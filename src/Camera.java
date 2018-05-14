public class Camera
{
   public static int x;
   public static int y;
   private static int resetx, resety;


   public static void setLocation(int x, int y)
   {
      Camera.x = x;
      Camera.y = y;
      Camera.resetx = x;
      Camera.resety = y;
   }
   
   public static void reset(){
	   Camera.x = Camera.resetx;
	   Camera.y = Camera.resety;
   }

   public static void moveBy(int dx, int dy)
   {
      x += dx;
      y += dy;
   }

   public void moveLeft(int dx)
   {
      x -= dx;
   }

   public void moveRight(int dx)
   {
      x += dx;
   }
}