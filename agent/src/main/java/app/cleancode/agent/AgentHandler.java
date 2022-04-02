package app.cleancode.agent;

public class AgentHandler {
    public static void pre(String className, String methodName, boolean isInterface) {
        System.out.printf("Calling %s method %s:%s\n", isInterface ? "interface" : "class",
                className, methodName);
    }

    public static void post(String className, String methodName, boolean isInterface) {
        System.out.printf("Returned from %s method %s:%s\n", isInterface ? "interface" : "class",
                className, methodName);
    }
}
