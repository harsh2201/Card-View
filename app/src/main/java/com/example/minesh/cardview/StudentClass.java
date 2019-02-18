package com.example.minesh.cardview;

public class StudentClass {
    public String Name,Id,Branch,Year;

    public StudentClass(){}

    public StudentClass(String name, String id, String branch, String year) {
        Name = name;
        Id = id;
        Branch =branch;
        Year=year;
    }

    public String gName() {
        return Name;
    }

    public void sName(String name)
    {
        Name = name;
    }

    public String gid() {
        return Id;
    }

    public void sid(String id)
    {
        Id = id;
    }

    public String gBranch() {
        return Branch;
    }

    public void sBranch(String branch)
    {
        Branch = branch;
    }

    public String gYear() {
        return Year;
    }

    public void sYear(String year)
    {
        Year = year;
    }
}
