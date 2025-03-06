package com.hcring.comprehensive.domain;

import java.io.Serializable;
import java.util.Arrays;

/* 불변 객체의 형태로 관리
* setter 제거 후 update 메소드만 사용
* */
public class User implements Serializable {
    private long userNo;
    private String userId;
    private String pwd;
    private int age;
    private String[] hobbies;
    private MbtiType mbtiType;
    private boolean activate;

    public User(long userNo, String userId, String pwd, int age, String[] hobbies, MbtiType mbtiType, boolean activate) {
        this.userNo = userNo;
        this.userId = userId;
        this.pwd = pwd;
        this.age = age;
        this.hobbies = hobbies;
        this.mbtiType = mbtiType;
        this.activate = activate;
    }

    public User update(String userId, int age, String[] hobbies, MbtiType mbtiType, boolean activate) {
        return new User(this.userNo, userId, this.pwd, age, hobbies, mbtiType, this.activate);
    }

    public long getUserNo() { return userNo; }
    public String getUserId() { return userId; }
    public String getPwd() { return pwd; }
    public int getAge() { return age; }
    public String[] getHobbies() { return hobbies; }
    public MbtiType getMbtiType() { return mbtiType; }
    public boolean isActivate() { return activate; }

    public void setUserNo(long userNo) { this.userNo = userNo; }
    public void setActivate(boolean activate) { this.activate = activate; }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", id='" + userId + '\'' +
                ", age=" + age +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", mbtiType=" + mbtiType +
                ", activate=" + activate +
                '}';
    }
}
