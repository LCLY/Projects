/**
 * Homework 08
 * This program returns the elements of array sorted from lowest to highest.
 * @author Ley Yen Choo, lab sec 01
 * @version February, 10 2016
 */
public class Matrix {
    int rows;
    int columns;
    int[][] matrix;

    Matrix(int rows, int columns){
        this.rows=rows;
        this.columns=columns;
        matrix = new int [rows][columns];
    }
    public void addData(int[] array){
        int k = 0;
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j <columns ; j++){
                matrix[i][j] = array [k];
                k++;
            }
        }
        }
    /**
     *
     * @param a it calls matrix a from the main into this method
     * @param b it calls matrix b from the main into this method
     * @return the sum of these 2 matrices
     */
    public static Matrix addMatrices(Matrix a, Matrix b){
       int [][] newMatrix = new int [a.rows][a.columns];//create a new matrix with the new values
        for (int i = 0 ; i < a.rows ; i++){
            for (int j = 0 ; j< a.columns ; j++){
                newMatrix[i][j] = a.matrix[i][j]+b.matrix[i][j];//sum up both matrices
            }
        }
        Matrix sum = new Matrix(a.rows,a.columns);
        sum.matrix = newMatrix;
        return sum;
    }


    /**
     * @param args
     * The main method creates matrix objects
     * it also populates numbers into the matrices
     * and finally call the method addMatrices to sum up the matrices
     */
    public static void main(String[] args) {
        Matrix obj1 = new Matrix(2, 2); // creates a Matrix object with two rows and two columns
        Matrix obj2 = new Matrix(2, 2); // creates another Matrix object with two rows and two columns

        obj1.addData(new int[] {1, 2, 3, 4}); // addData() is called using obj1, so {1, 2, 3, 4} is populated into obj1.matrix
        obj2.addData(new int[] {5, 6, 7, 8}); // addData() is called using obj2, so {5, 6, 7, 8} is populated into obj2.matrix
        Matrix sum = addMatrices(obj1,obj2);//should contain {6,8,10,12}.
        //System.out.print(sum.matrix[0][0]);
    }
}
