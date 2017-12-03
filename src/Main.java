public class Main {

    public static void main(String[] args) {

        AlienAndHamburgers alienAndHamburgers = new AlienAndHamburgers();

        int[] type = {1, 2, 3, 2, 3, 1, 3, 2, 3, 1, 1, 1};
        int[] taste = {1, 7, -2, 3, -4, -1, 3, 1, 3, -5, -1, 0};

        int maxJoy = alienAndHamburgers.getNumber(type, taste);
        System.out.println("max joy = " + maxJoy);
    }
}
