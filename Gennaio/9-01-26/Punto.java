public class Punto {
 private int x;
 private int y;
 
 public Punto() {
    this.x = 0;
    this.y = 0;
 }
 public void trasla(Direzione k, int distanza ){
   switch(k){
      case SU:

         break;
      case GIU:
         
         break;
      case DESTRA:
         
         break;
      case SINISTRA:
         
         break;
      default:
   }
   
 }
 @Override
public String toString() {
   return "Punto [x=" + x + ", y=" + y + "]";
}
 public int getX() {
   return x;
 }
 public void setX(int x) {
   this.x = x;
 }
 public int getY() {
   return y;
 }
 public void setY(int y) {
   this.y = y;
 }
 
}
