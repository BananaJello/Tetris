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
