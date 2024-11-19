public class PrintR {
    public static String PrintR(String[] thisArray) {

        for (int i = 0; i < thisArray.length;  i++) {
            System.out.println("[" + i + "] = " + "\"" + thisArray[i] + "\"");
        }
        return "";
    }

    public static void main(String[] args) {
        String[] arRay = {"vfg","5","hytre r","12","34"};
        PrintR(arRay);
    }
}