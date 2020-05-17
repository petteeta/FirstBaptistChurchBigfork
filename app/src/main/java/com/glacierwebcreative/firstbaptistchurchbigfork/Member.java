// First Baptist Church Bigfork     Member.java

package com.glacierwebcreative.firstbaptistchurchbigfork;

public class Member {

    private String mfirstNameHusband;
    private String mfirstNameWife;
    private String mlastName;


    public Member(String firstNameHusband, String firstNameWife, String lastName) {

        mfirstNameHusband = firstNameHusband;
        mfirstNameWife = firstNameWife;
        mlastName = lastName;
    }


    public String getmFirstNameHusband() {
        return mfirstNameHusband;
    }

    public String getmFirstNameWife() {
        return mfirstNameWife;
    }

    public String getmLastName() {
        return mlastName;
    }


    @Override
    public String toString() {
        return "Member{" +
                "mfirstNameHusband='" + mfirstNameHusband + '\'' +
                ", mfirstNameWife='" + mfirstNameWife + '\'' +
                ", mlastName='" + mlastName + '\'' +
                '}';
    }
}








