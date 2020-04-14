public class DeSynchronizedThread {
    static class Table {
        void printTable(int number) {
            for (int index = 1; index <= 10; index++) {
                System.out.println((number * index));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThread1 extends Thread {
        Table table;

        MyThread1(Table table) {
            this.table = table;
        }

        public void run() {
            table.printTable(5);
        }
    }

    static class MyThread2 extends Thread {
        Table table;

        MyThread2(Table table) {
            this.table = table;
        }

        public void run() {
            table.printTable(100);
        }
    }

    public static void main(String[] args) {
        Table tableTest = new Table();
        MyThread1 thread1 = new MyThread1(tableTest);
        MyThread2 thread2 = new MyThread2(tableTest);
        thread1.start();
        thread2.start();
    }
}