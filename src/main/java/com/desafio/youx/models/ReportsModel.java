package com.desafio.youx.models;

public class ReportsModel {
    private Double salesInTheYear;
    private String clientWithTheMostSale;
    private String clientWithThemostInvoicingSale;
    private String clientWithThemostInvoicingSaleYear;

    public ReportsModel() {
    }

    public Double getSalesInTheYear() {
        return salesInTheYear;
    }

    public void setSalesInTheYear(Double salesInTheYear) {
        this.salesInTheYear = salesInTheYear;
    }

    public String getClientWithTheMostSale() {
        return clientWithTheMostSale;
    }

    public void setClientWithTheMostSale(String clientWithTheMostSale) {
        this.clientWithTheMostSale = clientWithTheMostSale;
    }

    public String getClientWithThemostInvoicingSale() {
        return clientWithThemostInvoicingSale;
    }

    public void setClientWithThemostInvoicingSale(String clientWithThemostInvoicingSale) {
        this.clientWithThemostInvoicingSale = clientWithThemostInvoicingSale;
    }

    public String getClientWithThemostInvoicingSaleYear() {
        return clientWithThemostInvoicingSaleYear;
    }

    public void setClientWithThemostInvoicingSaleYear(String clientWithThemostInvoicingSaleYear) {
        this.clientWithThemostInvoicingSaleYear = clientWithThemostInvoicingSaleYear;
    }
}
