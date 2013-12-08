public class AI extends Player{
    
    public double reactionTH;
    public double paddleSpeed;
    
    public AI(int ID, int pID, Canvas canvas) {
        
        super(ID, pID, canvas);
        
        c = canvas;
        
        paddleSpeed = 10;
        
        // only react when its in its own half of the screen
        reactionTH = c.getWidth() / 2;
        
        TYPE = "AIPADDLE";
        
    }
    
    
    public void runAI(double ballPosX, double ballPosY, double ballSpeedX){
       
       reactionTH = c.getWidth() / 2;
       
       if (playerID == 1) {
       
            //If its over the AI's side, and moving towards the AI
            if (ballPosX <= reactionTH && ballSpeedX < 0){
           
                //If ball is above paddle, move up
                if (ballPosY < pos_y + (paddleHeight/2) + 10){
                    movePaddle(-paddleSpeed);
                }
                //If ball is below paddle, move down
                else if(ballPosY > pos_y + (paddleHeight/2) -10){
                    movePaddle(paddleSpeed); 
                }
            }
       }
       else if (playerID == 2) {
       
            //If its over the AI's side, and moving towards the AI
            if (ballPosX >= reactionTH && ballSpeedX > 0){
           
                //If ball is above paddle, move up
                if (ballPosY < pos_y + (paddleHeight/2) + 10){
                    movePaddle(-paddleSpeed);
                }
                //If ball is below paddle, move down
                else if(ballPosY > pos_y + (paddleHeight/2) -10){
                    movePaddle(paddleSpeed); 
                }
            }
       }
        
       //this code makes it unbeatable (just ask Emily)
       //setPosY(ballPosY);
       
    }
    
    

  
    
}
