package lcs;

public class LCS {


    public LCS(String x, String y) {
        this.in1 = x.toCharArray();
        this.in2 = y.toCharArray();
    }

    //the 2 arrays input 1 and 2
    private char[] in1;
    private char[] in2;
    private char[][] b;
    private int[][] c;
    //builder for the strings
    private StringBuilder lcs = new StringBuilder();

    public void lcsDynamicSol() {
        //Copy from the pseudo code
        //D= diagonal arrow
        //U= up arrow
        //L= left arrow
        int m = in1.length;
        int n = in2.length;

        char[][] b = new char[in1.length + 1][in2.length + 1];
        int[][] c = new int[in1.length + 1][in2.length + 1];
        //pseudo code verbatim
        for (int i = 0; i <= m; i++) {
            c[i][0] = 0;
        }
        for (int j = 1; j <= n; j++) {
            c[0][j] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (in1[i - 1] == in2[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 'D';
                    // made the whole thing == a variable instead
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 'U'; //up
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 'L';  //left
                }
            }
        }
        this.b = b;
    }

    //Collections.reverse(array) ended up not using but had values printing weird in beginning
    private String lcsPrint(int i, int j) {
        // dont have to pass string x
        if (i == 0 || j == 0) {
            return "";
        }
        //D for diagonal
        //using the arrow as variables
        if (b[i][j] == 'D') {
            lcsPrint(i - 1, j - 1);
            //System.out.println(in1[i-1]); //just so i can see what I had printing
            //so it starts from beginning
            lcs.append(in1[i - 1]);

            //U for up
        } else if (b[i][j] == 'U') {
            lcsPrint(i - 1, j);
        } else {
            lcsPrint(i, j - 1);
        }
        return lcs.toString();
    }

    public String getLCS() {
        return lcsPrint(in1.length, in2.length);
    }
}
