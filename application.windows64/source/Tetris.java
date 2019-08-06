import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Tetris extends PApplet {

static ArrayList<Piece> pieces = new ArrayList<Piece>();
static GameObject currentPiece, nextPiece;
static PImage scaryImage;
static int score;
public void setup(){
  
  background(0);
  fill(102);
  score = 0;
  scaryImage = loadImage("theNun.jpg");
  switch (PApplet.parseInt(random(7))){
    case 1:
      currentPiece = new JPiece();
      break;
    case 2:
      currentPiece = new LPiece();
      break;
    case 3:
      currentPiece = new SPiece();
      break;
    case 4:
      currentPiece = new ZPiece();
      break;
    case 5:
      currentPiece = new OPiece();
      break;
    case 6:
      currentPiece = new IPiece();
      break;
    case 0:
      currentPiece = new TPiece();
      break;
  }
  switch (PApplet.parseInt(random(7))){
    case 1:
      nextPiece = new JPiece();
      break;
    case 2:
      nextPiece = new LPiece();
      break;
    case 3:
      nextPiece = new SPiece();
      break;
    case 4:
      nextPiece = new ZPiece();
      break;
    case 5:
      nextPiece = new OPiece();
      break;
    case 6:
      nextPiece = new IPiece();
      break;
    case 0:
      nextPiece = new TPiece();
      break;
  }
  nextPiece.x = 250;
  nextPiece.y = 201;
}
public void draw(){
  background(0);
  stroke(255);
  line(200,0,200,500);
  stroke(0);
  nextPiece.setRotation();
  for (int i = 0; i < pieces.size(); i++){
    pieces.get(i).draw();
  }
  textSize(40);
  text(score,210,100);
  currentPiece.update();
  if(currentPiece.collided()){
    for(int i = 0; i < 20; i++){
      int counter = 0;
      for(int a = 0; a < pieces.size(); a++){
        if (pieces.get(a).y == i*25){
          counter++;
        }
      }
      if (counter == 8){
        for(int a = 0; a < pieces.size(); a++){
        if (pieces.get(a).y == i*25){
          pieces.remove(a);
          a--;
          }
        }
        for(int a = 0; a < pieces.size(); a++){
          if (pieces.get(a).y < i*25){
            pieces.get(a).y += 25;
          }
        }
        score += 100;
      }
    }
    if (currentPiece.p1.y <= 0){
      image(scaryImage,0,0,width,height);
      noLoop();
    }
    if (currentPiece.p2.y <= 0){
      image(scaryImage,0,0,width,height);
      noLoop();
    }
    if (currentPiece.p3.y <= 0){
      image(scaryImage,0,0,width,height);
      noLoop();
    }
    if (currentPiece.p4.y <= 0){
      image(scaryImage,0,0,width,height);
      noLoop();
    }
    currentPiece = nextPiece;
    currentPiece.x = 100;
    currentPiece.y = 0;
    switch (PApplet.parseInt(random(7))){
    case 1:
      nextPiece = new JPiece();
      break;
    case 2:
      nextPiece = new LPiece();
      break;
    case 3:
      nextPiece = new SPiece();
      break;
    case 4:
      nextPiece = new ZPiece();
      break;
    case 5:
      nextPiece = new OPiece();
      break;
    case 6:
      nextPiece = new IPiece();
      break;
    case 0:
      nextPiece = new TPiece();
      break;
    
  }
  nextPiece.x = 250;
  nextPiece.y = 201;
  }
}

public void keyPressed(){
  if (keyCode == UP){
    currentPiece.addRotation();
  }
  
  if (keyCode == RIGHT){
    currentPiece.right();
  }
  if (keyCode == LEFT){
    currentPiece.left();

  }
  if (keyCode == DOWN){
    currentPiece.y += 25;
  }
}
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
class IPiece extends GameObject{
  
  public IPiece(){
    x = 100;
    y = 0;
    p1 = new Piece(x-25,y,255,255,255);
    p2 = new Piece(x,y,255,255,255);
    p3 = new Piece(x+25,y,255,255,255);
    p4 = new Piece(x+50,y,255,255,255);
    
  }
  public void setRotation(){
    switch(rotation%2){
      case 0: 
        p1.setPos(x-25,y);
        p2.setPos(x,y);
        p3.setPos(x+25,y);
        p4.setPos(x+50,y);
        break;
      case 1: 
        p1.setPos(x,y-25);
        p2.setPos(x,y);
        p3.setPos(x,y+25);
        p4.setPos(x,y+50);
        break;
      
    }
  }
  public void update(){
    if (!collided()) {fall();}
    setRotation();
    checkIfInWall();
    checkIfInOtherPiece();
    p1.draw();
    p2.draw();
    p3.draw();
    p4.draw();
  }

}
class JPiece extends GameObject{

  public JPiece(){
    x = 100;
    y = 0;
    p1 = new Piece(x,y-25,255,255,255);
    p2 = new Piece(x,y,255,255,255);
    p3 = new Piece(x,y+25,255,255,255);
    p4 = new Piece(x-25,y+25,255,255,255);
  }
  public void setRotation(){
    switch(rotation%4){
      case 0: 
        p1.setPos(x,y-25);
        p2.setPos(x,y);
        p3.setPos(x,y+25);
        p4.setPos(x-25,y+25);
        break;
      case 1: 
        p1.setPos(x-25,y-25);
        p2.setPos(x,y);
        p3.setPos(x-25,y);
        p4.setPos(x+25,y);
        break;
      case 2: 
        p1.setPos(x+25,y-25);
        p2.setPos(x,y);
        p3.setPos(x,y-25);
        p4.setPos(x,y+25);
        break;
      case 3: 
        p1.setPos(x+25,y+25);
        p2.setPos(x,y);
        p3.setPos(x+25,y);
        p4.setPos(x-25,y);
        break;
      
      
    }
  }
  public void update(){
    if (!collided()) {fall();}
    setRotation();
    checkIfInWall();
    checkIfInOtherPiece();
  
    p1.draw();
    p2.draw();
    p3.draw();
    p4.draw();
  }
  
}
class LPiece extends GameObject{

  public LPiece(){
    x = 100;
    y = 0;
    p1 = new Piece(x+25,y+25,255,255,255);
    p2 = new Piece(x,y,255,255,255);
    p3 = new Piece(x,y+25,255,255,255);
    p4 = new Piece(x,y-25,255,255,255);

  }
  public void setRotation(){
    switch(rotation%4){
      case 0: 
        p1.setPos(x+25,y+25);
        p2.setPos(x,y);
        p3.setPos(x,y+25);
        p4.setPos(x,y-25);
        break;
      case 1: 
        p1.setPos(x-25,y+25);
        p2.setPos(x,y);
        p3.setPos(x-25,y);
        p4.setPos(x+25,y);
        break;
      case 2: 
        p1.setPos(x-25,y-25);
        p2.setPos(x,y);
        p3.setPos(x,y-25);
        p4.setPos(x,y+25);
        break;
      case 3: 
        p1.setPos(x+25,y-25);
        p2.setPos(x,y);
        p3.setPos(x+25,y);
        p4.setPos(x-25,y);
        break;
      
      
    }
  }
  public void update(){
    if (!collided()) {fall();}
    setRotation();
    checkIfInWall();
    checkIfInOtherPiece();

    p1.draw();
    p2.draw();
    p3.draw();
    p4.draw();
  }


}
class OPiece extends GameObject{
  public OPiece(){
    rotation = 0;
    x = 100;
    y = 0;
    p1 = new Piece(x,y-25,255,255,255);
    p2 = new Piece(x,y,255,255,255);
    p3 = new Piece(x+25,y-25,255,255,255);
    p4 = new Piece(x+25,y,255,255,255);

  }
  public void setRotation(){
    p1.setPos(x,y-25);
    p2.setPos(x,y);
    p3.setPos(x+25,y-25);
    p4.setPos(x+25,y);
  }
  public void update(){
    if (!collided()) {fall();}
    setRotation();
    checkIfInWall();
    checkIfInOtherPiece();
    p1.draw();
    p2.draw();
    p3.draw();
    p4.draw();
  }


}
class Piece{
  int x,y,size,r,g,b;
  public Piece(int x, int y, int r, int g, int b){
     this.x = x;
     this.y = y;
     this.size = 25;
     this.r = r;
     this.g = g;
     this.b = b;
     pieces.add(this);
  }
  public void draw(){
    fill(r,g,b);
    square(x,y,size);
  }
  public void setPos(int x,int y){
    this.x = x;
    this.y = y;
  }
}
class SPiece extends GameObject{
  public SPiece(){
    x = 100;
    y = 0;
    p1 = new Piece(x-25,y,255,255,255);
    p2 = new Piece(x,y,255,255,255);
    p3 = new Piece(x,y-25,255,255,255);
    p4 = new Piece(x+25,y-25,255,255,255);
  }
  public void setRotation(){
    switch(rotation%2){
      case 0: 
        p1.setPos(x-25,y);
        p2.setPos(x,y);
        p3.setPos(x,y-25);
        p4.setPos(x+25,y-25);
        break;
      case 1: 
        p1.setPos(x,y-25);
        p2.setPos(x,y);
        p3.setPos(x+25,y);
        p4.setPos(x+25,y+25);
        break;
      
    }
  }
  public void update(){
    if (!collided()) {fall();}
    setRotation();
    checkIfInWall();
    checkIfInOtherPiece();

    p1.draw();
    p2.draw();
    p3.draw();
    p4.draw();
  }


}
class TPiece extends GameObject{
  public TPiece(){
    x = 100;
    y = 0;
    p1 = new Piece(x-25,y,255,255,255);
    p2 = new Piece(x,y,255,255,255);
    p3 = new Piece(x+25,y,255,255,255);
    p4 = new Piece(x,y+25,255,255,255);

  }
  public void setRotation(){
    switch(rotation%4){
      case 0: 
        p1.setPos(x-25,y);
        p2.setPos(x,y);
        p3.setPos(x+25,y);
        p4.setPos(x,y+25);
        break;
      case 1: 
        p1.setPos(x-25,y);
        p2.setPos(x,y);
        p3.setPos(x,y-25);
        p4.setPos(x,y+25);
        break;
      case 2: 
        p1.setPos(x-25,y);
        p2.setPos(x,y);
        p3.setPos(x+25,y);
        p4.setPos(x,y-25);
        break;
      case 3: 
        p1.setPos(x,y-25);
        p2.setPos(x,y);
        p3.setPos(x+25,y);
        p4.setPos(x,y+25);
        break;
      
      
    }
  }
  public void update(){
    if (!collided()) {fall();}
    setRotation();
    checkIfInWall();
    checkIfInOtherPiece();

    p1.draw();
    p2.draw();
    p3.draw();
    p4.draw();
  }


}
class ZPiece extends GameObject{
  public ZPiece(){
    x = 100;
    y = 0;
    p1 = new Piece(x,y-25,255,255,255);
    p2 = new Piece(x,y,255,255,255);
    p3 = new Piece(x-25,y-25,255,255,255);
    p4 = new Piece(x+25,y,255,255,255);
  }
  public void setRotation(){
    switch(rotation%2){
      case 0: 
        p1.setPos(x,y-25);
        p2.setPos(x,y);
        p3.setPos(x-25,y-25);
        p4.setPos(x+25,y);
        break;
      case 1: 
        p1.setPos(x+25,y);
        p2.setPos(x,y);
        p3.setPos(x,y+25);
        p4.setPos(x+25,y-25);
        break;
      
    }
  }
  public void update(){
    if (!collided()) {fall();}
    setRotation();
    checkIfInWall();
    checkIfInOtherPiece();

    p1.draw();
    p2.draw();
    p3.draw();
    p4.draw();
  }

}
  public void settings() {  size(350, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Tetris" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
