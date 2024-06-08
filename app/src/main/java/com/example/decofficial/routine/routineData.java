package com.example.decofficial.routine;

public class routineData {

    private String Branch, pdfTitle, pdfUrl, Semester;

    public String getBranch() {
        return Branch;
    }

    public routineData() {
    }

    public void setBranch(String Branch) {
        this.Branch = Branch;
    }

    public String getPdfTitle() {
        return pdfTitle;
    }

    public void setPdfTitle(String pdfTitle) {
        this.pdfTitle = pdfTitle;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String Semester) {
        this.Semester = Semester;
    }

    public routineData(String Branch, String pdfTitle, String pdfUrl, String Semester) {
        this.Branch = Branch;
        this.pdfTitle = pdfTitle;
        this.pdfUrl = pdfUrl;
        this.Semester = Semester;
    }

}
