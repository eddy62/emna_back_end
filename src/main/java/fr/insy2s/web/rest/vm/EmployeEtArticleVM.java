package fr.insy2s.web.rest.vm;

import java.util.List;

public class EmployeEtArticleVM {
    List<EmployerVM> employerVMList;
    List<ArticleVM> articleVMList;

    public EmployeEtArticleVM(List<EmployerVM> employerVMList, List<ArticleVM> articleVMList) {
        this.employerVMList = employerVMList;
        this.articleVMList = articleVMList;
    }

    public EmployeEtArticleVM() {
    }

    public List<EmployerVM> getEmployerVMList() {
        return employerVMList;
    }

    public void setEmployerVMList(List<EmployerVM> employerVMList) {
        this.employerVMList = employerVMList;
    }

    public List<ArticleVM> getArticleVMList() {
        return articleVMList;
    }

    public void setArticleVMList(List<ArticleVM> articleVMList) {
        this.articleVMList = articleVMList;
    }

    @Override
    public String toString() {
        return "EmployeEtArticleVM{" +
            "employerVMList=" + employerVMList +
            ", articleVMList=" + articleVMList +
            '}';
    }
}
