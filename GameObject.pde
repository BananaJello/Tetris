public class GameObject{
  int x,y,rotation,fallTimer;
  Piece p1,p2,p3,p4;
  public GameObject(){
    rotation = 0;
    fallTimer = 0;
  }
  
  public void update(){
    
  }
  
  public void setRotation(){
    
  }
  public void fall(){
    if (fallTimer >= 50-score/100){
      y += 25;
      fallTimer = 0;
    }else{
      fallTimer++;
  }
  }
  public void addRotation(){
    rotation++;
  }
  
  public void checkIfInWall(){
    if (p1.x < 0 || p2.x < 0 || p3.x < 0 || p4.x < 0){ rotation --;}
           
    if (p1.x > 175 || p2.x > 175 || p3.x > 175 || p4.x > 175){
            rotation--;
     }
  }
  public void checkIfInOtherPiece(){
    for (int i = 0; i < pieces.size(); i++){
      if (pieces.get(i) == p1){break;}
      if (pieces.get(i) == p2){break;}
      if (pieces.get(i) == p3){break;}
      if (pieces.get(i) == p4){break;}
      if (p1.x == pieces.get(i).x && p1.y == pieces.get(i).y){
        rotation--;
      }
      if (p2.x == pieces.get(i).x && p2.y == pieces.get(i).y){
        rotation--;
      }
      if (p3.x == pieces.get(i).x && p3.y == pieces.get(i).y){
        rotation--;
      }
      if (p4.x == pieces.get(i).x && p4.y == pieces.get(i).y){
        rotation--;
      }
    }
  }
  public void left(){
    if (p1.x > 0){
      if (p2.x > 0){
        if (p3.x > 0){
          if (p4.x > 0){
            x -= 25;
         }
        }
      }
    }
    setRotation();
    for (int i = 0; i < pieces.size()-8; i++){
      if (p1.x == pieces.get(i).x && p1.y == pieces.get(i).y){
        x += 25;
        setRotation();
        break;
      }
      if (p2.x == pieces.get(i).x && p2.y == pieces.get(i).y){
        x += 25;
        setRotation();
        break;
      }
      if (p3.x == pieces.get(i).x && p3.y == pieces.get(i).y){
        x += 25;
        setRotation();
        break;
      }
      if (p4.x == pieces.get(i).x && p4.y == pieces.get(i).y){
        x += 25;
        setRotation();
        break;
      }
    }
  }
  public void right(){
    if (p1.x < 175){
      if (p2.x < 175){
        if (p3.x < 175){
          if (p4.x < 175){
            x += 25;
         }
        }
      }
    }
    setRotation();
    for (int i = 0; i < pieces.size()-8; i++){
      if (p1.x == pieces.get(i).x && p1.y == pieces.get(i).y){
        x -= 25;
        print("bad");
        setRotation();
        break;
      }
      if (p2.x == pieces.get(i).x && p2.y == pieces.get(i).y){
        x -= 25;
        setRotation();
        break;
      }
      if (p3.x == pieces.get(i).x && p3.y == pieces.get(i).y){
        x -= 25;
        setRotation();
        break;
      }
      if (p4.x == pieces.get(i).x && p4.y == pieces.get(i).y){
        x -= 25;
        setRotation();
        break;
      }
    }
  }
  public boolean collided(){
    if (p1.y + 25 == 500){
      return true;
    }
    if (p2.y + 25 == 500){
      return true;
    }
    if (p3.y + 25 == 500){
      return true;
    }
    if (p4.y + 25 == 500){
      return true;
    }
    
    for (int i = 0; i < pieces.size(); i++){
      if (pieces.get(i) == p1){
        i++;
      }
      else if (pieces.get(i) == p2){
        i++;
      }
      else if (pieces.get(i) == p3){
        i++;
      }
      else if (pieces.get(i) == p4){
        i++;
      }
      else{
        if (pieces.get(i).x == p1.x){
          if (pieces.get(i).y - 25 == p1.y){
            return true;
          }
        }
        if (pieces.get(i).x == p2.x){
          if (pieces.get(i).y - 25 == p2.y){
            return true;
          }
        }
        if (pieces.get(i).x == p3.x){
          if (pieces.get(i).y - 25 == p3.y){
            return true;
          }
        }
        if (pieces.get(i).x == p4.x){
          if (pieces.get(i).y - 25 == p4.y){
            return true;
          }
        }
      }
    }
    return false;
  }
}
