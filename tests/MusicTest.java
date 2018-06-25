import br.imd.model.Music;
import org.junit.Test;

public class MusicTest {
    @Test
    public void ConstructorTest(){
        Music music = new Music("file:/home/jimmy/Música/Die_Walkure_Ride+of+the+Valkyries.mp3");
        if (music != null){
            System.out.println("Loaded file: " + music.getTitle());
        }

    }
    /*@Test
    public void InvalidConstructorTst(){
        Music music = new Music("invalidPath");
    }*/
}
