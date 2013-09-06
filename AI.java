public class AI extends Player{
    
    
    public void runAI(int ballPosX, int ballPosY, int ballSpeedX){
       
       //If its over the AI's side, and moving towards the AI
       if (ballPosX >= 450 && ballSpeedX > 0){
           //If ball is above paddle, move up
           if (ballPosY <= pos_y){
              movePaddle(-5);
           }
           //If ball is below paddle, move down
           else if(ballPosY >= pos_y){
              movePaddle(5); 
           }
       }
        
       //this code makes it unbeatable (just ask Emily)
       //setPosY(ballPosY);        
    }
    
    

  
    
}
