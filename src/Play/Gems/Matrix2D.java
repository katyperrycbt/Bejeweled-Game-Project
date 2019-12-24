package Gems;

import Bejeweled.SizeGameMenu;

/**
 * Create 2D Gem array
 */
public class Matrix2D {
    //Create 2D arrays of Gem class and Position class, one to create Gems, one to store there locations.
    public static Gem[][] matrix;
    public static Position[][] gemLocation;
    //Bejeweled 2  has 7 gem's kinds
    public int[] image = new int[7];
    //Location of in-active gem
    public int activeX;
    public int activeY;

    public boolean notDetectRight = false;
    public boolean notDetectLeft = false;
    public boolean notDetectUp = false;
    public boolean notDetectDown = false;

    public boolean isMatrix2DActive = false;

    /**
     * First, in the double loops, we try to pick randomly kind of gem by GemPicking() method
     */
    public Matrix2D() {
        matrix = new Gem[SizeGameMenu.numOfRow][SizeGameMenu.numOfColumn];
        gemLocation = new Position[SizeGameMenu.numOfRow][SizeGameMenu.numOfColumn];
        for (int i = 0; i < SizeGameMenu.numOfColumn; i++) {
            for (int j = 0; j < SizeGameMenu.numOfRow; j++) {
                int type = GemPicking(i, j, matrix);
                setMatrix(i, j, type);
                setPosition(i, j);
            }
        }
        showMatrix2D();
        showPosition();
    }

    /**
     * Set gem location to 2D array to store.
     * @param i
     * @param j
     */
    public void setPosition(int i, int j) {
        gemLocation[i][j] = new Position(150 + 80 * i, 95 + 80 * j);
    }
    /**
     * Create the matrix of gems by the existed matrix
     *      This matrix has the same number of rows and columns but has bigger dimension unit
     *          to draw gems.
     * @param i
     * @param j
     * @param type
     */
    public void setMatrix(int i, int j, int type) {
        matrix[i][j] = new Gem(type, 150 + 80 * i, 95 + 80 * j);
    }
    /**
     * Just a method to check bugs,
     * This is to show the location matrix in console panel
     */
    public static void showPosition() {
        for (int i = 0; i < SizeGameMenu.numOfRow; i++) {
            for (int j = 0; j < SizeGameMenu.numOfColumn; j++) {
                System.out.print(gemLocation[i][j].x + "," + gemLocation[i][j].y + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    /**
     * Just a method to check bugs,
     * This is to show the matrix in console panel
     */
    public static void showMatrix2D() {
        for (int i = 0; i < SizeGameMenu.numOfRow; i++) {
            for (int j = 0; j < SizeGameMenu.numOfColumn; j++) {
                System.out.print(matrix[i][j].typeGem + " ");
            }
            System.out.println();
        }
        System.out.println("");
    }

    int GemPicking(int i, int j, Gem[][] a) {

        for (int r = 0; r < 7; ++r) {
            image[r] = 0;
        }
        /**
         * In these two methods below, we try to check if:
         *         During the picking & displaying gems, the types of nearby gems are the same or not
         *         If they are the same, we want to change the kind of one gem to a specific value (1)
         */
        LeftsChecker(i, j, a);
        UpsChecker(i, j, a);
        /** Because the type of Gem = 1 is a specific kind which is already defined above, so we try to
         *          avoid pick this kind of gem
         */
        int type = Gem.getTypeGem() + 7;
        while (image[type % 7] == 1) {
            --type;
        }
        return type % 7;

    }

    void LeftsChecker(int i, int j, Gem[][] a) {
        if (j > 1) {
            if (a[i][j - 1].typeGem == a[i][j - 2].typeGem) {
                image[a[i][j - 1].typeGem] = 1;
            }
        }
    }

    void UpsChecker(int i, int j, Gem[][] a) {
        if (i > 1) {
            if (a[i - 1][j].typeGem == a[i - 2][j].typeGem) {
                image[a[i - 1][j].typeGem] = 1;
            }
        }
    }
}
