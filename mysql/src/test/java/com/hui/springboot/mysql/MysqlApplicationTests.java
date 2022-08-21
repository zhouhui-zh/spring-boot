package com.hui.springboot.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MysqlApplicationTests {

    @Test
    void contextLoads() {
    }


    public static int sumTwo(int[] arr, int n) {
        int sum = 0;
        if (n < arr.length) {
            sum = arr[n] + sumTwo(arr, n + 1);
            System.out.println(sum);
            return sum;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{2, 4, 6};
        int sum = sumTwo(arr, 0);
        System.out.println(sum);
    }

}
