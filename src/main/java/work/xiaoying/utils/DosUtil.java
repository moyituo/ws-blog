package work.xiaoying.utils;

import cn.hutool.core.text.CharSequenceUtil;
import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * dos跑龙套
 *
 * @author 小樱
 * @date 2022/12/06
 */
public class DosUtil {

    @SneakyThrows
    public static String exec(String command) {

        StringBuilder sb = new StringBuilder();
        Process proc = null;
        proc = Runtime.getRuntime().exec(command);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> errorFuture = executorService.submit(new InputStreamRunnable(proc.getErrorStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
        String line = null;
        while ((line = in.readLine()) != null) {
             sb.append(line).append("\r\n");
        }
        String errorMsg = errorFuture.get();
        proc.waitFor();
        proc.destroy();
        executorService.shutdown();
        return CharSequenceUtil.isNotBlank(sb.toString())?sb.toString():errorMsg;
    }
}

class InputStreamRunnable implements Callable<String> {
    BufferedReader bReader = null;

    public InputStreamRunnable(InputStream is) {
        try {
            bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "GBK"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = bReader.readLine()) != null) {
            sb.append(line).append("\r\n");
        }
        return sb.toString();
    }
}
