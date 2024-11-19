package strings;

public class ConditionalUtilsTriangleSides {
    public static boolean isTriangleSides(int aSide, int bSide, int cSide) {
        if (aSide < bSide+cSide  && bSide < aSide+cSide  && cSide < bSide+aSide)
            return true;
        else
            return false;
    }

    private ConditionalUtilsTriangleSides() {

    }

    public static void main(String[] args) {
        System.out.println("При вводе сторон треугольника 1, 2 и 3, метод должен вернуть false - "
                + ConditionalUtilsTriangleSides.isTriangleSides(1, 2, 3));
    }
}