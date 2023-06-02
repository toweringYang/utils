package cn.bbahh.util.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandUtil {
    public static String runCommand(String cmd) throws IOException, InterruptedException {
        String[] cmds = new String[]{"sh", "-c", cmd};
        System.out.println(cmd);
        String result = null;
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec(cmds);
        int rescode = exec.waitFor();
        System.out.println("退出代码" + rescode);
        BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        StringBuffer sb = new StringBuffer();

        String line;
        while((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }

        result = sb.toString();
        return result;
    }
}
