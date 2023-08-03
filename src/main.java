import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



public class main extends Application {
    
    public void start (Stage stg){
      
        
        
        Text t1,t2;
        t1= new Text("single player");
        t2=new Text ( " Multiple  Player");
        Rectangle r1,r2,rc1,rc2;
        rc2=createRec();
        rc1=createRec();
        rc1.setOpacity(0);
        rc2.setOpacity(0);
        StackPane sp1= new StackPane(r1=createRec()), sp2 = new StackPane(r2=createRec());
        
      
        sp1.getChildren().addAll(t1,rc1);
        sp2.getChildren().addAll(t2,rc2);
       
        HBox hb= new HBox (40);
        VBox vb =new VBox(0);
        Insets is = new Insets(20,20,20,20);
        hb.setPadding(is);
        vb.setPadding(is);
        hb.getChildren().addAll(sp1,sp2);// add sp2 here if multiplayer is available
        ImageView im1 =new ImageView("img_folder/intro.jpg");
        im1.setFitHeight(150);
        im1.setFitWidth(350);
        vb.getChildren().addAll(im1,hb);
        hb.setAlignment(Pos.CENTER);
        vb.setAlignment(Pos.CENTER);
        Scene sc = new Scene (vb,400,400);
        sc.setFill(Color.BEIGE);
        stg.setScene(sc);
        stg.show();
        rc1.setOnMouseClicked( e->{

          
            game_board(stg);
            
                
        });
          
        
        rc2.setOnMouseClicked( e->{
             
           multiplayer m= new multiplayer();
            m.game_board(stg);
            
        });
       
      
    }
    
    public static void main(String[] args) {
        Application.launch(args);
        
    }

   
    Rectangle createRec(){
        Rectangle rc = new Rectangle(160,50);
              rc.setOpacity(.5);
              rc.setArcHeight(10);
              rc.setArcWidth(30);
              rc.setFill(Color.GREENYELLOW);
        return rc;
    }
    //  global variables
  
      int counter =0 ;
      boolean check[]= new boolean[9];
      boolean comp[]= new boolean[9];
      boolean user []= new boolean[9];
      
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
         Arrays.fill(user,false);
         Arrays.fill(comp,false);
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
                   check[index]=false;
                   user[index]=true;
                  
                   
                       
                   
                   replaceImg(gp,im,index,0,row);
                   reduceUser(index);
                   //algo(gp,im);
                    if(winCondition(user))
                   {
                       Arrays.fill(check,false); 
                       recursion.reintro(s,"user");
                      
                     
                   }
               else algo(gp,im);
                   
                   
               }
               
               
           }
           else if(  e.getX()>=320 && e.getX()<=620 )
           {
               int row= getRow(e.getY()),index;
               index=3*row+1;
                if(check[index] && row!=-1)
               {
                   check[index]=false;
                   user[index]=true;
                  
                      
                   reduceUser(index);
                   replaceImg(gp,im,index,1,row);
                   //algo(gp,im);
                    if(winCondition(user))
                   {
                       Arrays.fill(check,false); 
                       recursion.reintro(s,"user");
                      
                     
                   }
               else algo(gp,im);
                      
               }
               
           }
           else if(  e.getX()>=630 && e.getX()<=930 )
           {
               int row= getRow(e.getY()),index;
               index=3*row+2;
                if(check[index] && row!=-1)
               {
                   check[index]=false;
                   user[index]=true;
               
                
                   reduceUser(index);
                   replaceImg(gp,im,index,2,row);
                    if(winCondition(user))
                   {
                       Arrays.fill(check,false); 
                       recursion.reintro(s,"user");
                      
                     
                   }
               else algo(gp,im);
                   
                
          
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

    void reduceUser(int index)
    { 
        if(indices.length==1){counter++;return;}
        int[] temp= new int[indices.length-1];
             for (int i = 0,j=0; i < indices.length; i++) {
             if(indices[i]==index)continue;
             temp[j]=indices[i];
             j++;
        
    }
              indices = temp;

              counter++;
              
    }
    
  void algo(GridPane gp,ImageView[] im)
          
{
    
             
 
             SecureRandom rd = new SecureRandom();
     
       
             if(counter==1)
             {
                 if(user[0] || user[2] || user[6] || user[8])
                 {
                    comp[4]=true;
                    replaceImgComputer(gp,im,4,1,1);
                    check[4]=false;
                    reduceUser(4);
                     
                 }
                 else{
                     
                 int a[]={0,2,6,8};
                 int i=rd.nextInt(4);
                  comp[a[i]]=true;
                    replaceImgComputer(gp,im,a[i],a[i]%3,a[i]/3);
                    check[a[i]]=false;
                    reduceUser(a[i]);
                 
                 }
                     
             }
             else  if(nextmove()==-1)
             {
           
           int index= rd.nextInt(indices.length);
              if(check[indices[index]])
              {
                  comp[indices[index]]=true;
                  if(winCondition(comp))
                   {
                       Arrays.fill(check,false); 
                     recursion.reintro(s,"comp");
                   }
             replaceImgComputer(gp,im,indices[index],indices[index]%3,indices[index]/3);
            
            check[indices[index]]=false;
            reduceUser(indices[index]);
              }
             }
             
             
             else
             {
                 int index=nextmove();
                  if(check[index])
                  {
                  comp[index]=true;
                  replaceImgComputer(gp,im,index,index%3,index/3);
                  if(winCondition(comp))
                   {
                       Arrays.fill(check,false); 
                       recursion.reintro(s,"comp");
                   }
            
                  check[index]=false;
                   reduceUser(index);
              }
                 
             }
        
         if(counter==9)
             recursion.reintro(s, "tie");
 

}  
  
   void replaceImg(GridPane gp,ImageView[] im,int in,int col,int row){
        im[in]=new ImageView("img_folder/x.jpg");
        im[in].setFitHeight(300);
        im[in].setFitWidth(300);
        gp.add(im[in],col,row);
    }
      void replaceImgComputer(GridPane gp,ImageView[] im,int in,int col,int row){
        im[in]=new ImageView("img_folder/o.jpg");
        im[in].setFitHeight(300);
        im[in].setFitWidth(300);
        gp.add(im[in],col,row);
    }
      int nextmove()
      {
          //horisontal
          if(comp[0] && comp[1] && !user[2] )
              return 2;
          if(comp[3] && comp[4] && !user[5] )
              return 5;
          if(comp[6] && comp[7] && !user[8] )
              return 8;
          if(comp[2] && comp[1] && !user[0] )
              return 0;
          if(comp[5] && comp[4] && !user[3] )
              return 3;
          if(comp[8] && comp[7] && !user[6] )
              return 6;
          //vertical
          if(comp[0] && comp[3] && !user[6] )
              return 6;
          if(comp[6] && comp[3] && !user[0] )
              return 0;
          if(comp[1] && comp[4] && !user[7] )
              return 7;
          if(comp[7] && comp[4] && !user[1] )
              return 1;
          if(comp[2] && comp[5] && !user[8] )
              return 8;
          if(comp[8] && comp[5] && !user[2] )
              return 2;
          //diagonal
          if(comp[0] && comp[4] && !user[8] )
              return 8;
          if(comp[8] && comp[4] && !user[0] )
              return 0;
          if(comp[2] && comp[4] && !user[6] )
              return 6 ;
          if(comp[6] && comp[4] && !user[2] )
              return 2;
          //midgap
          if(comp[0] && comp[2] && !user[1] )
              return 1;
          if(comp[3] && comp[5] && !user[4] )
              return 4;
          if(comp[6] && comp[8] && !user[7] )
              return 7;
          if(comp[0] && comp[6] && !user[3] )
              return 3;
          if(comp[1] && comp[7] && !user[4] )
              return 4;
          if(comp[2] && comp[8] && !user[5] )
              return 5;
          if(comp[0] && comp[8] && !user[4] )
              return 4;
          if(comp[2] && comp[6] && !user[4] )
              return 4;
          
            // do not lose
            
          //hori
          if(user[0] && user[1] && !comp[2] )
              return 2;
          if(user[3] && user[4] && !comp[5] )
              return 5;
          if(user[6] && user[7] && !comp[8] )
              return 8;
          if(user[2] && user[1] && !comp[0] )
              return 0;
          if(user[5] && user[4] && !comp[3] )
              return 3;
          if(user[8] && user[7] && !comp[6] )
              return 6;
          //vert
          if(user[0] && user[3] && !comp[6] )
              return 6;
          if(user[6] && user[3] && !comp[0] )
              return 0;
          if(user[1] && user[4] && !comp[7] )
              return 7;
          if(user[7] && user[4] && !comp[1] )
              return 1;
          if(user[2] && user[5] && !comp[8] )
              return 8;
          if(user[8] && user[5] && !comp[2] )
              return 2;
          //diago
          if(user[0] && user[4] && !comp[8] )
              return 8;
          if(user[8] && user[4] && !comp[0] )
              return 0;
          if(user[2] && user[4] && !comp[6] )
              return 6 ;
          if(user[6] && user[4] && !comp[2] )
              return 2;
          //midgap
          if(user[0] && user[2] && !comp[1] )
              return 1;
          if(user[3] && user[5] && !comp[4] )
              return 4;
          if(user[6] && user[8] && !comp[7] )
              return 7;
          if(user[0] && user[6] && !comp[3] )
              return 3;
          if(user[1] && user[7] && !comp[4] )
              return 4;
          if(user[2] && user[8] && !comp[5] )
              return 5;
          if(user[0] && user[8] && !comp[4] )
              return 4;
          if(user[2] && user[6] && !comp[4] )
              return 4;
        
          return -1;
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
else return false;
}
      
      
  
}








