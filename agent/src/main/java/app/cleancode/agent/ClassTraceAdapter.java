package app.cleancode.agent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassTraceAdapter extends ClassVisitor {

    public ClassTraceAdapter(int api) {
        super(api);
    }

    public ClassTraceAdapter(int api, ClassVisitor otherVisitor) {
        super(api, otherVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
            String[] exceptions) {
        if ((access & Opcodes.ACC_NATIVE) == 0) {
            return new MethodTraceAdapter(super.api,
                    super.visitMethod(access, name, descriptor, signature, exceptions));
        } else {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
    }
}
