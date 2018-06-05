package com.github.tom9163.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tom9163
 * 从xml解析出来的Exam对象
 */
public final class Examination implements Comparable<Examination> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    private Integer order;
    private String name;
    String S_time;

    private Integer getOrder() {
        return order;
    }
    public Examination(String nameInput, String timeInput) {
        name = nameInput;
        S_time = timeInput;
        order = Integer.valueOf(S_time);
    }

    private Date getDate() throws ParseException {
        return format.parse(S_time);
    }

    int getDaysBetween() {
        try {
            return DaysBetween.differentDays(new Date(), getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getDaysBetweenString() {
        return String.valueOf(getDaysBetween());
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return S_time;
    }

    @Override
    public int compareTo(Examination o) {
        return this.getOrder().compareTo(o.getOrder());
    }
}