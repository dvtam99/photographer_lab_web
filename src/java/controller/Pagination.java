/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Win
 */
public class Pagination {

    private final int currentPage;
    private final int numberpage;
    private final int before;
    private final int after;
    private final int begin;
    private final int end;

    public Pagination() {
        currentPage = 1;
        numberpage = 0;
        before = 0;
        after = 0;
        begin = 1;
        end = 0;
    }

    public Pagination(int numberpage, int currentPage) {
        int numberPageInSite = 10;
        this.currentPage = currentPage;
        this.numberpage = numberpage;
        this.before = (10 - 1) / 2;
        this.after = 10 - 1 - this.before;
        //If the page number is less than the number of pages that can be displayed in, begin equals 1 end with the page number
        if (this.numberpage < numberPageInSite) {
            this.begin = 1;
            this.end = this.numberpage;
        } else {
            int limit = numberPageInSite - 3;
            //If within the display limits of the first page (from page 1 to the page one page from the end of the page),
            //begin and end will be 1 and the number of pages can be displayed in the page.
            if (currentPage - limit <= 1) {
                this.begin = 1;
                this.end = numberPageInSite;
            //If within the display limits of the first page (from the last page to the page 1 page away from the first page),
            //begin and end will be the number of pages and pages that are about the same number of pages displayed on the page minus 1
            } else if (currentPage + limit >= this.numberpage) {
                this.begin = this.numberpage - numberPageInSite + 1;
                this.end = this.numberpage;
            //otherwise in both cases, the beginning and the end will be the current page number minus 2 equal spaces
            } else {
                this.begin = currentPage - this.before;
                this.end = currentPage + this.after;
            }
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNumberpage() {
        return numberpage;
    }

    public int getBefore() {
        return before;
    }

    public int getAfter() {
        return after;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

}
