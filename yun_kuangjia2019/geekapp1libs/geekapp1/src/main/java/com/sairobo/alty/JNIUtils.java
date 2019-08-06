package com.sairobo.alty;

import java.io.FileDescriptor;

public class JNIUtils {
//    public native String getString();

//    public native static FileDescriptor open(String path, int baudrate, int flags);

//    public native void close();

    static {
        System.loadLibrary("serial_port");
    }
}
