package com.uniquext.rx_lifecycle_plugin;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class OnDestroyMethodVisitor extends MethodVisitor {
    public OnDestroyMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }

    @Override
    public void visitInsn(int opcode) {
        mv.visitTypeInsn(Opcodes.CHECKCAST, "androidx/lifecycle/LifecycleOwner");
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/uniquext/android/rxlifecycle/RxLifecycle", "unbind", "(Landroidx/lifecycle/LifecycleOwner;)V", false);
        super.visitInsn(opcode);
    }
}
