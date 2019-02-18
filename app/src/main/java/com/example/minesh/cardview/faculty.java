package com.example.minesh.cardview;

public class faculty {

    private int id;
    private String name,shortInfo,emali;
    private int image;

    public faculty(int id, String name, String shortInfo, String emali, int image) {
        this.id = id;
        this.name = name;
        this.shortInfo = shortInfo;
        this.emali = emali;
        this.image = image;
    }

    public int gId() {
        return id;
    }

    public void sId(int id) {
        this.id = id;
    }

    public String gName() {
        return name;
    }

    public void sName(String name) {
        this.name = name;
    }

    public String gShortInfo() {
        return shortInfo;
    }

    public void sShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String gEmali() {
        return emali;
    }

    public void sEmali(String emali) {
        this.emali = emali;
    }

    public int gImage() {
        return image;
    }

    public void sImage(int image) {
        this.image = image;
    }
}
