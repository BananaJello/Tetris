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
