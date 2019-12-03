package com.uniquext.rx_lifecycle_plugin;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LifecycleClassVisitor extends ClassVisitor {

    public LifecycleClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        if ("onCreate".equals(name) ) {
            //处理onCreate
            return new OnCreateMethodVisitor(methodVisitor);
        } else if ("onDestroy".equals(name)) {
            return new OnDestroyMethodVisitor(methodVisitor);
        }
        else {
            return methodVisitor;
        }

    }
}
