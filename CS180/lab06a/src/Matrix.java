/**
 * CS180 - Lab 06 - matrix
 * This program generates matrix
 * @author Ley Yen Choo, lchoo@purdue.edu
 */
public class Matrix {
    public boolean isSymmetric(int[][] matrix) {
        int notSymmetry = 0;
        boolean nTimesN = true;
                    for(int i = 0; i< matrix.length; i++){
                        if(matrix[i].length!=matrix.length){
                            return false;
                        }
                        else{
                            continue;
                        }
                     }



                   for (int i = 0; i < matrix.length; i++) {
                       for (int j = 0; j < matrix.length; j++) {

                           if (matrix[i][j] != matrix[j][i]) {
                               notSymmetry = 1;
                               break;
                           }
                       }
                   }

                    if(notSymmetry ==1){
                       return false;
                   }
                   else {
                       return true;
                   }
    }


    public boolean isDiagonal(int[][] matrix) {
        int x = 0;
        int y = 0;
        for(int i = 0; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix.length ; j++){
                if(i != j && matrix[i][j] != 0){
                    x=1;
                    break;
                }
                if(i == j && matrix[i][j] == 0 ){
                    y++;
                }
            }
        }
        if(x == 0 && y < matrix.length){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isIdentity(int[][] matrix) {
        int x = 0;
        for (int i = 0 ; i < matrix.length ;i++ ) {
            for (int j = 0 ; j < matrix.length ; j++ ) {
                if((i == j && matrix[i][j] != 1) || (i != j && matrix[i][j] != 0)) {
                    x = 1;
                    break;
                }
            }
            if( x == 1)
                break;
        }
        if(x == 1) {
            return false;
        }
        else{
            return true;
        }
    }

    public boolean isUpperTriangular(int[][] matrix) {
        int x = 0;


        for(int i = 0; i< matrix.length; i++){
            if(matrix[i].length!=matrix.length){
                return false;
            }
            else{
                continue;
            }
        }

                    for(int i = 0 ; i < matrix.length ; i++) {
                        for(int j = 0; j < i ; j++ ){
                /* Checking that the matrix is Upper Triangular or not */
                            if(matrix[i][j]!=0) { // All elements below the diagonal must be zero
                                x=1;
                                break;
                            }
                        }
                    }

                    if(x==0) {
                        return true;
                    }
                    else{
                        return false;
                    }

    }

    public boolean isTridiagonal(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int cell = matrix[i][j];

                if ((i==j) || (i-1==j) || (i+1==j)) {
                    if (cell == 0) {
                        return false;
                    }
                } else {
                    if (cell != 0) {
                        return false;
                    }
                }
            }
        }
        return true;

    }


    public static void main(String[] args) {
        Matrix obj = new Matrix();
        int[][] matrix = {{1,2,6,3},{0,9,5,9},{0,0,6,3},{0,0,0,2}};
        //  System.out.println( obj.isSymmetric(matrix));
        //  System.out.println( obj.isDiagonal(matrix));
        //  System.out.println( obj.isIdentity(matrix));
    }

}
