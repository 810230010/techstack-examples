package asm;

import jdk.internal.org.objectweb.asm.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AsmDemo {
    public static void main(String[] args) {
        String classFilePath = "common/target/classes/basic/StringDemo.class";
        try {
            FileInputStream fis = new FileInputStream(classFilePath);
            try {
                ClassReader classReader = new ClassReader(fis);

                ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                ClassVisitor visitor = new ClassVisitor(Opcodes.ASM5, classWriter) {
                    @Override
                    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                        System.out.println(String.format("%d %s %s %s %s", access, name, desc, signature, value));
                        return super.visitField(access, name, desc, signature, value);
                    }

                    @Override
                    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                        System.out.println(String.format("%d %s %s %s", access, name, desc, signature));
                        return super.visitMethod(access, name, desc, signature, exceptions);
                    }
                };
                classReader.accept(visitor, ClassReader.SKIP_DEBUG);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
