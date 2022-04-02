package app.cleancode.agent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassTraceAdapter extends ClassVisitor {
    private final String className;

    public ClassTraceAdapter(int api, ClassVisitor otherVisitor, String className) {
        super(api, otherVisitor);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
            String[] exceptions) {
        if ((access & Opcodes.ACC_NATIVE) == 0) {
            return new MethodTraceAdapter(super.api,
                    super.visitMethod(access, name, descriptor, signature, exceptions), className,
                    name);
        } else {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
    }
}
