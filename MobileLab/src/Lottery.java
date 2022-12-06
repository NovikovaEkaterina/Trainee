public class Lottery {
    public static Boolean ticketVerification(int number) {
        String ticket = Integer.toString(number);
        int n = ticket.length();
        if (n >= 2 && n <= 8 && n % 2 == 0) {
            char[] ticketChar = new char[n];
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < n; i++) {
                ticketChar[i] = ticket.charAt(i);
            }
            for (int i = 0; i < n / 2; i++) {
                sum1 += Integer.parseInt(String.valueOf(ticketChar[i]));
            }
            for (int i = n / 2; i < n; i++) {
                sum2 += Integer.parseInt(String.valueOf(ticketChar[i]));
            }
            return sum1 == sum2;

        } else {
            System.out.println("Номер билета введен неверно!!!");
            return false;
        }
    }

    public static void main(String[] args) {

        System.out.println(ticketVerification(223265));
    }
}
