package com.example.decofficial.syllabus;

public class syllabusData {

    private String branch, pdfTitle, pdfUrl, semester;

    public String getBranch() {
        return branch;
    }

    public syllabusData() {
    }

    public void setBranch(String branch) {
        this.branch = branch;
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
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public syllabusData(String branch, String pdfTitle, String pdfUrl, String semester) {
        this.branch = branch;
        this.pdfTitle = pdfTitle;
        this.pdfUrl = pdfUrl;
        this.semester = semester;
    }
}
