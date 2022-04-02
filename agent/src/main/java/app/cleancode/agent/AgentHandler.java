package app.cleancode.agent;

import java.util.Stack;

public class AgentHandler {

    private static final Stack<Long> functionCallStartTimes = new Stack<>();

    public static void pre(String callerClass, String callerMethod, String className,
            String methodName, boolean isInterface) {
        if (!className.startsWith("java")) {
            functionCallStartTimes.push(System.nanoTime());
        }
    }

    public static void post(String callerClass, String callerMethod, String className,
            String methodName, boolean isInterface) {
        if (!className.startsWith("java")) {
            long callDuration = System.nanoTime() - functionCallStartTimes.pop();
            if (callDuration > 100000000) {
                System.out.printf("Calling %s method %s:%s from %s:%s took %.3fs\n",
                        isInterface ? "interface" : "class", className, methodName, callerClass,
                        callerMethod, callDuration / 1000000000d);
            }
        }
    }
}
