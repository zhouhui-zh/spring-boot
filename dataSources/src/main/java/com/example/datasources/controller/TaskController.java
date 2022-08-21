package com.example.datasources.controller;

import com.example.datasources.async.AsynTaskFature;
import com.example.datasources.async.AsyncMyTask;
import com.example.datasources.async.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-08-07 17:13
 */

@RestController
@Slf4j
public class TaskController {

    @Autowired
    private AsyncTask asyncTask;
    @Autowired
    private AsyncMyTask asyncMyTask;
    @Autowired
    private AsynTaskFature taskService;

    static ConcurrentHashMap<String, Future> maps = new ConcurrentHashMap();

    @GetMapping(path = "/doTask")
    public String doTask() {
        log.info("https://blog.csdn.net/weixin_42272869/article/details/123082657");
        for (int i = 0; i < 20; i++) {
            try {
                asyncTask.doTask(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("all task end ..");
        return "hello, SpringBoot";
    }

    @GetMapping(path = "/doTask2")
    public String doTask2() {
        log.info("https://blog.csdn.net/weixin_42272869/article/details/123082657");
        for (int i = 0; i < 20; i++) {
            try {
                asyncMyTask.doTask(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("all task end ..");
        return "hello, SpringBoot";
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public long test(HttpServletResponse response) throws InterruptedException, ExecutionException {
        log.info("https://blog.csdn.net/weixin_37545129/article/details/88798279");
        long s = System.currentTimeMillis();

        if ((!maps.contains("task1")) || (maps.contains("task1") && maps.get("task1").isDone())) {
            Future<String> task1 = taskService.task1();
            Future<String> rs = maps.put("task1", task1);
            log.info("task1.isDone={}", task1.isDone());
            log.info("{}", rs);
        }
        if ((!maps.contains("task2")) || (maps.contains("task2") && maps.get("task2").isDone())) {
            Future<String> task2 = taskService.task2();
            Future<String> rs = maps.put("task2", task2);
            log.info("{}", rs);
            log.info("task2.isDone={}", task2.isDone());
        }
        if ((!maps.contains("task1")) || (maps.contains("task1") && maps.get("task1").isDone())) {
            Future<String> task3 = taskService.task3();
            Future<String> rs = maps.put("task3", task3);
            log.info("{}", rs);

            log.info("task3.isDone={}", task3.isDone());
        }






       /* for (; ; ) {
            // 回调函数 Future 如果执行完毕就会返回这个函数
            if (task3.isDone()) {
                //System.out.println("task1");
                break;
            }
        }*/
//		System.out.println(task1.get());
//		System.out.println(task2.get());
        // System.out.println(task3.get());


        long e = System.currentTimeMillis();
        log.info("task总耗时:" + (e - s));

        return (e - s);
    }

    @GetMapping(value = "getRes")
    public String getRes(String task) throws ExecutionException, InterruptedException {
        log.info("task={}", task);
        Future<String> future = maps.get(task);
        if (future == null) {
            return future + " 不存在";
        }
        if (future.isDone()) {
            return future.get();
        }
        return " task running";
    }
}
