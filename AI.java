public class AI extends Player{
    
    public double reactionTH;
    
    public AI(int ID) {
        
        super(ID);
        
        reactionTH = 450;
        
    }
    
    
    public void runAI(double ballPosX, double ballPosY, double ballSpeedX){
       
       if (playerID == 1) {
       
            //If its over the AI's side, and moving towards the AI
            if (ballPosX <= reactionTH && ballSpeedX < 0){
           
                //If ball is above paddle, move up
                if (ballPosY < pos_y + (paddleHeight/2) + 10){
                    movePaddle(-6);
                }
                //If ball is below paddle, move down
                else if(ballPosY > pos_y + (paddleHeight/2) -10){
                    movePaddle(6); 
                }
            }
       }
       else if (playerID == 2) {
       
            //If its over the AI's side, and moving towards the AI
            if (ballPosX >= reactionTH && ballSpeedX > 0){
           
                //If ball is above paddle, move up
                if (ballPosY < pos_y + (paddleHeight/2) + 10){
                    movePaddle(-6);
                }
                //If ball is below paddle, move down
                else if(ballPosY > pos_y + (paddleHeight/2) -10){
                    movePaddle(6); 
                }
            }
       }
        
       //this code makes it unbeatable (just ask Emily)
       //setPosY(ballPosY);
       
    }
    
    

  
    
}
