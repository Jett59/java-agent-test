package app.cleancode.agent;

import java.lang.instrument.Instrumentation;

public class Premain {
    public static void premain(String args, Instrumentation instrumentation) {
        Transformer transformer = new Transformer();
        instrumentation.addTransformer(transformer);
    }
}
