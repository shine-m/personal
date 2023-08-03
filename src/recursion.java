
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class recursion {
    
static void reintro( Stage s,String str){
    
       main obj= new main();
    
       
       
        Stage stg= new Stage();
        Text t1,t2;
        t1= new Text("Play Again ?");
        t2=new Text ( " EXIT ");
        Rectangle r1,r2,rc1,rc2;
        rc2=createRec();
        rc1=createRec();
        rc1.setOpacity(0);
        rc2.setOpacity(0);
        StackPane sp1= new StackPane(r1=createRec()), sp2 = new StackPane(r2=createRec());
      
        sp1.getChildren().addAll(t1,rc1);
        sp2.getChildren().addAll(t2,rc2);
       
        HBox hb= new HBox (40);
        VBox vb =new VBox(10);
        Insets is = new Insets(20,20,20,20);
        hb.setPadding(is);
        vb.setPadding(is);
        hb.getChildren().addAll(sp1,sp2);
        ImageView im1=new ImageView("img_folder/lost.jpg") ;
        im1.setFitHeight(190);
        im1.setFitWidth(380);

  if(str=="user")
        {
                       im1 =new ImageView("img_folder/final.jpg");
                       im1.setFitHeight(190);
                       im1.setFitWidth(380);
                       im1.setOpacity(.9);
        }
  else if(str=="tie"){
                       im1 =new ImageView("img_folder/tie.jpg");
                       im1.setFitHeight(190);
                       im1.setFitWidth(380);
                       im1.setOpacity(.9);
      
  }
        // main menu
       Rectangle rc3= createRec(); 
       rc3.setOpacity(0);
       Text t3= new Text(" MAIN MENU ");
       
       StackPane sp3= new StackPane(createRec());
        sp3.getChildren().addAll(t3,rc3);
  
       ///
  
  
  
        vb.getChildren().addAll(im1,hb,sp3);
        hb.setAlignment(Pos.CENTER);
        vb.setAlignment(Pos.CENTER);
        Scene sc = new Scene (vb,400,400);
        sc.setFill(Color.ALICEBLUE);
        stg.setScene(sc);
        stg.show();
        rc1.setOnMouseClicked( e->{

          
            //stg.close();
             s.close();
            obj.game_board(stg);
            
                
        });
        rc3.setOnMouseClicked( e->{

          
            //stg.close();
             s.close();
             stg.close();
          
          obj.start(stg);
            
                
        });
          
        
        rc2.setOnMouseClicked( e->{
             
             s.close();
           System.exit(0);
            
        });
       
        
        
        
    }
 static Rectangle createRec(){
        Rectangle rc = new Rectangle(160,50);
              rc.setOpacity(.5);
              rc.setArcHeight(15);
              rc.setArcWidth(30);
              rc.setFill(Color.AQUAMARINE);
        return rc;
    }
    
    
}
