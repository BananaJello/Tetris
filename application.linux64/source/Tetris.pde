static ArrayList<Piece> pieces = new ArrayList<Piece>();
static GameObject currentPiece, nextPiece;
static PImage scaryImage;
static int score;
void setup(){
  size(350, 500);
  background(0);
  fill(102);
  score = 0;
  scaryImage = loadImage("theNun.jpg");
  switch (int(random(7))){
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
  switch (int(random(7))){
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
void draw(){
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
    switch (int(random(7))){
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

void keyPressed(){
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
