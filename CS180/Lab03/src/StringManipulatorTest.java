/**
 * Created by lchoo on 1/26/16.
 */

import static org.junit.Assert.*;
import org.junit.*;
/**
 * JUnit test case class for testing the functionality
 * of methods from the StringManipulator class.
 */
public class StringManipulatorTest {
    private StringManipulator sm;

    @Before
    public void setUp() throws Exception {
        sm = new StringManipulator();
    }
    /**
     * Test the basic functionality of makeUserName.
     * Don't check for correct case.
     */
    @Test(timeout = 100)
    public void testMakeUserNameBasic(){
        String ret = sm.makeUserName("Henry Choo");
        String message = "makeUserName(): check if username follows the basic Unix style structure";
        assertEquals(message, "hchoo", ret);
    }
    @Test(timeout = 100)
    public void testMakeUserNameLower() {
        String ret = sm.makeUserName("John Doe");
        String message= "makeUserName(): check if username is lower case";
        assertEquals(message,"jdoe",ret);
    }
    @Test(timeout = 100)
    public void testMakeEmail() {
      String ret = sm.makeEmail("jdoe", "purdue.edu");
        String message = "makeEmail(): check if the email is correct";
        assertEquals(message, "jdoe@purdue.edu", ret);
    }
}
