import br.imd.model.Music;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;


public class MusicTest {
    @Test
    public void ConstructorTest(){
        Music music = new Music("file:/home/jimmy/Música/Die_Walkure_Ride+of+the+Valkyries.mp3");
        assertNotEquals(null, music);
    }
    @Test
    public void InvalidConstructorTst(){
        // @TODO
        //Music music = new Music("invalidPath");
        //assertNotEquals(null, music.getTitle());
    }
}
