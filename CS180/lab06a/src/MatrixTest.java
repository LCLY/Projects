/**
 * Created by LeyYen on 2/15/2016.
 */
import static org.junit.Assert.*;
import org.junit.*;


public class MatrixTest {
    private Matrix test;

    @Before
    public void setUp() throws Exception {
        test = new Matrix();
    }

    @Test(timeout = 100)
    public void testIsSymmetric() {

        int data[][] = {{1,0,0},
                        {0,1,0},
                        {0,0,1}};
        boolean returnVal = test.isSymmetric(data);
        String message = "testIsSymmetric(): check when matrix is not symmetry";
        assertTrue(message, returnVal);
    }
    @Test(timeout = 100)
    public void testNotSymmetric() {

        int data[][] = {{1,0,1,6},
                        {0,0,0,8},
                        {0,0,1}};
        boolean returnVal = test.isSymmetric(data);
        String message = "testIsSymmetric(): check when matrix is n x n";
        assertFalse(message, returnVal);
    }

    @Test(timeout = 100)
    public void testInvalidSymmetric() {

        int data[][] = {{1,0,1},
                        {0,1,0},
                        {0,1,1}};
        boolean returnVal = test.isSymmetric(data);
        String message = "testIsSymmetric(): check when matrix is not symmetry";
        assertFalse(message, returnVal);
    }
    @Test(timeout = 100)
    public void testItIsDiagonal() {

        int data[][] = {{1,0,0},
                        {0,1,0},
                        {0,0,1}};
        boolean returnVal = test.isDiagonal(data);
        String message = "testItIsDiagonal(): check when matrix is diagonal";
        assertTrue(message, returnVal);
    }
    @Test(timeout = 100)
    public void testDoNotLieOnMainDiagonal() {

        int data[][] = {{1,1,1},
                        {1,1,1},
                        {1,1,1}};
        boolean returnVal = test.isDiagonal(data);
        String message = "testDoNotLieOnMainDiagonal(): check when all entries that do not lie on the main diagonal must equal 0";
        assertFalse(message, returnVal);
    }

    @Test(timeout = 100)
    public void testInvalidDiagonal() {

        int data[][] = {{1,0,1},
                        {0,1,0},
                        {0,1,1}};
        boolean returnVal = test.isDiagonal(data);
        String message = "testInvalidDiagonal(): check when matrix is not diagonal";
        assertFalse(message, returnVal);
    }
    @Test(timeout = 100)
    public void TestItIsIdentityMatrix() {

        int data[][] = {{1,0,0},
                        {0,1,0},
                        {0,0,1}};
        boolean returnVal = test.isIdentity(data);
        String message = "TestItIsIdentityMatrix(): check when matrix is identity matrix";
        assertTrue(message, returnVal);
    }
    @Test(timeout = 100)
    public void testTheNumberAlongMainDiagonal() {

        int data[][] = {{6,0,0},
                        {0,2,0},
                        {0,0,3}};
        boolean returnVal = test.isIdentity(data);
        String message = "testTheNumberAlongMainDiagonal(): check when all entries along the main diagonal must equal 1";
        assertFalse(message, returnVal);
    }
    @Test(timeout = 100)
    public void testTheNumberOtherThanMainDiagonal() {

        int data[][] = {{1,0,0},
                        {0,1,0},
                        {0,0,1}};
        boolean returnVal = test.isIdentity(data);
        String message = "testTheNumberOtherThanMainDiagonal(): check when all other entries is equal to 0.";
        assertTrue(message, returnVal);
    }
    @Test(timeout = 100)
    public void testItIsUpperTriangular() {

        int data[][] = {{1,0,0},
                        {0,1,0},
                        {0,0,1}};
        boolean returnVal = test.isUpperTriangular(data);
        String message = "testItIsUpperTriangular(): check when matrix is upper triangular";
        assertTrue(message, returnVal);
    }
    @Test(timeout = 100)
    public void testIfItIsNTimesN() {

        int data[][] = {{6,0,0,3},
                        {0,2,0,9},
                        {0,0,3}};
        boolean returnVal = test.isUpperTriangular(data);
        String message = "testIfUpperTriangularIsNTimesN(): check when it is n x n";
        assertFalse(message, returnVal);
    }
    @Test(timeout = 100)
    public void testInvalidUpperTriangular() {

        int data[][] = {{1,0,0},
                        {6,1,0},
                        {5,0,1}};
        boolean returnVal = test.isUpperTriangular(data);
        String message = "testInvalidUpperTriangular(): check all entries below the main diagonal must equal 0.";
        assertFalse(message, returnVal);
    }
    @Test(timeout = 100)
    public void testItIsTriDiagonal() {

        int data[][] = {{1,1,0},
                        {1,1,1},
                        {0,1,1}};
        boolean returnVal = test.isTridiagonal(data);
        String message = "testItIsTriDiagonal(): check when matrix is tridiagonal matrix";
        assertTrue(message, returnVal);
    }
    @Test(timeout = 100)
    public void testIfTridiagonalIsNTimesN() {

        int data[][] = {{6,0,0},
                        {0,2,0,3},
                        {0,0,3}};
        boolean returnVal = test.isTridiagonal(data);
        String message = "testIfTridiagonalIsNTimesN(): check when it is n x n";
        assertFalse(message, returnVal);
    }
    @Test(timeout = 100)
    public void testInvalidTridiagonal() {

        int data[][] = {{1,0,3},
                        {6,1,0},
                        {5,0,1}};
        boolean returnVal = test.isTridiagonal(data);
        String message = "testInvalidUpperTriangular(): check all entries must equal 0 except the main diagonal, the upper diagonal and the lower diagonal.";
        assertFalse(message, returnVal);
    }
}
