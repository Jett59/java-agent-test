package app.cleancode.agent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodTraceAdapter extends MethodVisitor {
    private final String ownerClass;
    private final String methodName;

    public MethodTraceAdapter(int api, MethodVisitor otherVisitor, String ownerClass,
            String methodName) {
        super(api, otherVisitor);
        this.ownerClass = ownerClass;
        this.methodName = methodName;
    }

    public void loadConstants(Object... constants) {
        for (Object constant : constants) {
            super.visitLdcInsn(constant);
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor,
            boolean isInterface) {
        loadConstants(ownerClass, methodName, owner, name);
        super.visitIntInsn(Opcodes.BIPUSH, isInterface ? 1 : 0);
        super.visitMethodInsn(Opcodes.INVOKESTATIC, "app/cleancode/agent/AgentHandler", "pre",
                "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V",
                false);
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        loadConstants(ownerClass, methodName, owner, name);
        super.visitIntInsn(Opcodes.BIPUSH, isInterface ? 1 : 0);
        super.visitMethodInsn(Opcodes.INVOKESTATIC, "app/cleancode/agent/AgentHandler", "post",
                "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V",
                false);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack + 5, maxLocals);
    }
}
