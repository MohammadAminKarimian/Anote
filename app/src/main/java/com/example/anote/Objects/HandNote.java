package com.example.anote.Objects;


import java.util.Date;

public class HandNote {

    private String name, teacher_name, university, lesson;
    private int _id, drawable, field;
    private Date upload_date, write_date;


    public HandNote(String name, String teacher_name, String university, int drawable) {
        this.name = name;
        this.teacher_name = teacher_name;
        this.university = university;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public Date getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }

    public Date getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Date write_date) {
        this.write_date = write_date;
    }
}
