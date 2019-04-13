import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SaveTest{

    @Test
    public void testSaveToTextFile(){
        String testText = "Test text";
        String filename = "MyFile.txt";
        try {
            Save.saveToTextFile(filename, testText);
        }
        catch(IOException i){}

        String textInFile = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            try{
                textInFile = reader.readLine();
            }
            catch(IOException i){}
        }
        catch(FileNotFoundException f){}

        assertEquals("MyFile.txt should contain the text \"Test text\".", "Test text", textInFile);
    }
}