package app.cleancode.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class Transformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        if (!className.startsWith("java") && !className.startsWith("sun")
                && !className.startsWith("jdk")
                && !className.equals("app/cleancode/agent/AgentHandler")) {
            ClassReader classReader = new ClassReader(classfileBuffer);
            ClassWriter classWriter = new ClassWriter(0);
            ClassTraceAdapter traceAdapter = new ClassTraceAdapter(Opcodes.ASM9, classWriter);
            classReader.accept(traceAdapter, 0);
            return classWriter.toByteArray();
        } else {
            return classfileBuffer;
        }
    }
}
