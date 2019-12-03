package com.uniquext.rx_lifecycle_plugin;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class OnCreateMethodVisitor extends MethodVisitor {
    public OnCreateMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitTypeInsn(Opcodes.CHECKCAST, "androidx/lifecycle/LifecycleOwner");
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/uniquext/android/rxlifecycle/RxLifecycle", "bind", "(Landroidx/lifecycle/LifecycleOwner;)V", false);
    }
}
