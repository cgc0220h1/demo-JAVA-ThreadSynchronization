import java.util.InputMismatchException;

public class Bank {
    public static class Customer {
        private int balance = 100;

        public Customer() {
            System.out.println("Tài khoản của bạn là: " + balance);
        }

        public synchronized void withdraw(int amount) {
            System.out.println("Đang thực hiện giao dịch rút tiền " + amount);
            while (balance < amount) {
                System.out.println("Không đủ tiền rút!!");
                try {
//                    wait();
                } catch (InputMismatchException ie) {
                    System.out.println(ie.toString());
                }
            }
        }

        public synchronized void deposit(int amount) {
            System.out.println("Đang thực hiện giao dịch nạp tiền " + amount);
            balance += amount;
            System.out.println("Nạp tiền thành công. Tài khoản của bạn hiện tại là " + balance);
//            notify();
        }
    }

    public static void main(String[] args) {
        final Customer customer = new Customer();

        Thread thread = new Thread() {
            @Override
            public void run() {
                customer.withdraw(2000);
            }
        };
        thread.start();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                customer.deposit(500);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                customer.deposit(3000);
            }
        };
        thread1.start();
    }
}
