package customer.test_xb.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class testjuc {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<List<Integer>>> ints = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<List<Integer>> future = executorService.submit(new Callable<List<Integer>>() {
                @Override
                public List<Integer> call() throws Exception {
                    
                    boolean flag = false;
                    // timeout
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName() + "  ");
                    List<Integer> lists = new ArrayList<>();
                    for (int i = 3; i < 100; i++) {
                        flag = false;
                        for (int j = 2; j <= Math.sqrt(i); j++) {
                            if (i % j == 0) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag == false) {
                            lists.add(i);
                        }
                    }
                    return lists;
                }

            });
            ints.add(future);
        }

        for (Future<List<Integer>> future : ints) {
            // System.out.println(future.get());
        }
    }
}
