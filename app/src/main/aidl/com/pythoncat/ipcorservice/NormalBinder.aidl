// NormalBinder.aidl
package com.pythoncat.ipcorservice;
import com.pythoncat.ipcorservice.bean.Student;
// Declare any non-default types here with import statements

interface NormalBinder {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    int getResult();

    Student getStudent();

    void addStudent(inout Student s);
}
