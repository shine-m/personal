
import java.util.Arrays;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;



public class multiplayer {
    
    
      int counter =0 ;
      boolean check[]= new boolean[9];
      boolean user2[]= new boolean[9];
      boolean user1 []= new boolean[9];
      
      int indices[]= {0,1,2,3,4,5,6,7,8};
      Stage s;
      // 
    void game_board(Stage st)
    {
        
          st.close();
          s= new Stage();
         GridPane gp= new GridPane();

        gp.setPadding(new javafx.geometry.Insets(10,10,10,10));
        gp.setVgap(10);
        gp.setHgap(10);
         ImageView[] im=new ImageView[9];
         for(int i=0;i<3;i++)
             for(int j=0;j<3;j++)
             {
                 im[3*i+j]=new ImageView("img_folder/1.jpg");
                 im[3*i+j].setFitHeight(300);
                 im[3*i+j].setFitWidth(300);
                 gp.add(im[3*i+j], i, j);
             }
         
         
         Arrays.fill(check,true); 
         Arrays.fill(user1,false);
         Arrays.fill(user2,false);
         Scene sc = new Scene(gp,940,940);
         
         
         
         FadeTransition ft = new FadeTransition();
                ft.setCycleCount(1);
                ft.setNode(gp);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.setDuration(Duration.millis(1500));
                ft.play();
                
               
           
         sc.setOnMouseClicked(e->{
             
           if( e.getX()>=10 && e.getX()<=310)
           {
               int row= getRow(e.getY()),in;
               in=row;
                   int index=3*row+0;
                 
               if(check[index] && row!=-1)
               {
                   counter++;
                   check[index]=false;
                  
                   
                       
                   if(counter%2==1){
                       
                   user1[index]=true;
                   replaceImg_player1(gp,im,index,0,row);
                    if(winCondition(user1))
                   {
                         Arrays.fill(check,false); 
                       multiplayer_recursion.reintro(s,"user1");
                      
                     
                   }
                   }
                   else{
                       
                   user2[index]=true;
                   replaceImg_player2(gp,im,index,0,row);
                    if(winCondition(user2))
                   {
                        Arrays.fill(check,false); 
                       multiplayer_recursion.reintro(s,"user2");
                      
                     
                   }
                   }
                       
                   
                   
               //else algo(gp,im);
               check[index]=false;
                   
                   
               }
               
               
           }
           else if(  e.getX()>=320 && e.getX()<=620 )
           {
               int row= getRow(e.getY()),index;
               index=3*row+1;
                if(check[index] && row!=-1)
               {
                   counter++;
                   if(counter%2==1){
                       
                   user1[index]=true;
                   replaceImg_player1(gp,im,index,1,row);
                    if(winCondition(user1))
                   {
                        Arrays.fill(check,false); 
                       multiplayer_recursion.reintro(s,"user1");
                      
                     
                   }
                   }
                   else{
                       
                   user2[index]=true;
                   replaceImg_player2(gp,im,index,1,row);
                    if(winCondition(user2))
                   {
                        Arrays.fill(check,false); 
                       multiplayer_recursion.reintro(s,"user2");
                      
                     
                   }
                   }
                   
                   check[index]=false;
                      
                   
                  
              // else algo(gp,im);
                      
               }
               
           }
           else if(  e.getX()>=630 && e.getX()<=930 )
           {
               int row= getRow(e.getY()),index;
               index=3*row+2;
                if(check[index] && row!=-1)
               {
                   counter++;
                    if(counter%2==1){
                       
                   user1[index]=true;
                   replaceImg_player1(gp,im,index,2,row);
                    if(winCondition(user1))
                   {
                        Arrays.fill(check,false); 
                       multiplayer_recursion.reintro(s,"user1");
                      
                     
                   }
                   }
                   else{
                       
                   user2[index]=true;
                   replaceImg_player2(gp,im,index,2,row);
                    if(winCondition(user2))
                   {
                        Arrays.fill(check,false); 
                       multiplayer_recursion.reintro(s,"user2");
                      
                     
                   }
                   }
                   
               //else algo(gp,im);
                   
                check[index]=false;
          
               }
               
           }
              
                   
         });
         
         sc.setFill(Color.DARKGRAY);

         s.setScene(sc);
         s.show();
         
    }
    
    
 
    int getRow(Double x){
       if( x>=10 && x<=310)
           {
               return 0;
           }
           else if( x>=320 && x<=620 )
           {
               return 1;
           }
           else if(  x>=630 && x<=930 )
           {
               return 2;
           }
       return -1;
    }
    
      void replaceImg_player1(GridPane gp,ImageView[] im,int in,int col,int row){
        im[in]=new ImageView("img_folder/x.jpg");
        im[in].setFitHeight(300);
        im[in].setFitWidth(300);
        gp.add(im[in],col,row);
    }
      
        void replaceImg_player2(GridPane gp,ImageView[] im,int in,int col,int row){
        im[in]=new ImageView("img_folder/o.jpg");
        im[in].setFitHeight(300);
        im[in].setFitWidth(300);
        gp.add(im[in],col,row);
    }
      
            boolean winCondition(boolean[] w)
{
if(w[0]==true && w[1]==true && w[2]==true)
return true;
else if(w[0]==true && w[4] ==true && w[8]==true)
return true;
else if(w[0]==true && w[3]==true && w[6]==true)
return true;
else if(w[2] ==true && w[4]==true && w[6]==true)
return true;
else if(w[2] ==true && w[5] ==true && w[8]==true)
return true;
else if(w[6]==true && w[7]==true && w[8]==true)
return true;
else if(w[3]==true && w[4]==true && w[5]==true)
return true;
else if(w[1]==true && w[4]==true && w[7]==true)
return true;
else if (counter== 9)  {multiplayer_recursion.reintro(s,"tie"); return false;}
else return false;
}

    
}
