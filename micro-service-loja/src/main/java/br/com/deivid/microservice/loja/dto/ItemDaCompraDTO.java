package br.com.deivid.microservice.loja.dto;

public class ItemDaCompraDTO {

    private long id;
    private int quatidade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuatidade() {
        return quatidade;
    }

    public void setQuatidade(int quatidade) {
        this.quatidade = quatidade;
    }
}
