import pl.EngravingScreen;
import pl.FilmPrcessingScreen;
import pl.MainScreen;

public class PhotoKiosk {

    public static void main(String[] args){

        MainScreen mainScreen = new MainScreen();
        FilmPrcessingScreen filmPrcessingScreen = new FilmPrcessingScreen();
        EngravingScreen engravingScreen = new EngravingScreen();


        engravingScreen.setupScreen();
        engravingScreen.setVisible(true);


        //mainScreen.setupScreen();
        //mainScreen.setVisible(true);

        //filmPrcessingScreen.setupScreen();
        //filmPrcessingScreen.setVisible(true);



    }
}
