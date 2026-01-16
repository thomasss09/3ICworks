public class Punto {
 private int x = 0 ;
 private int y = 0 ;
 
 public Punto(int x , int y) {
   this.x = x;
   this.y = y;
 }

 @Override
public String toString() {
   return "Punto [x=" + x + ", y=" + y + "]";
}

 public void trasla(Direzione k, int distanza ){
   switch(k){
      case SU:
         setY(getY()+distanza);
      break;
      case GIU:
         setY(getY()-distanza);
      break;
      case DESTRA:
         setX(getX()+distanza);
      break;
      case SINISTRA:
         setX(getX()-distanza);
            break;
      default:
          System.out.println("hai impostato coordinate errate");
   }
 }

 public void distanza(Punto p , Punto b){
   int distanza = 0;
   distanza = (int)(Math.sqrt(Math.pow(p.getX() - b.getX() , 2)+ Math.pow(p.getY() - b.getY(), 2 )));
   System.out.println(distanza);
 }
 
 @Override
 public boolean equals(Object obj) {
   if (this == obj)
      return true;
   if (obj == null)
      return false;
   if (getClass() != obj.getClass())
      return false;
   Punto other = (Punto) obj;
   if (x != other.x)
      return false;
   if (y != other.y)
      return false;
   return true;
 }
 
 public int getX() {
   return x;
 }
 public int getY() {
   return y;
 }

 public void setX(int x) {
   this.x = x;
 }

 public void setY(int y) {
   this.y = y;
 }

 
}
