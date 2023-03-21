package group9.project;

public class Test {
    public static void main(String[] args) {
        double num = 1.1e30;
        Vector3 v = new Vector3(1.1e9, 1.44e7, 5.3e5);
        System.out.println(v);
        Vector3 v2 = Converter.scaleToScreen(v);
        System.out.println(Converter.scaleToSolarSystem(v2));
    }
}
