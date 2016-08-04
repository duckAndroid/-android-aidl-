package com.pythoncat.ipcorservice.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pythonCat on 2016/8/4.
 */
public class Student implements Parcelable {

    public long id;
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
    }

    public Student() {
    }

    protected Student(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
