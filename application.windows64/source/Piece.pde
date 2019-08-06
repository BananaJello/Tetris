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
