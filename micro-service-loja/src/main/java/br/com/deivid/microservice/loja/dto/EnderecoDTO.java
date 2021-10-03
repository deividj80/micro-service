package br.com.deivid.microservice.loja.dto;

public class EnderecoDTO {
    private String rua;
    private int numrero;
    private String estado;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumrero() {
        return numrero;
    }

    public void setNumrero(int numrero) {
        this.numrero = numrero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EnderecoDTO{" +
                "rua='" + rua + '\'' +
                ", numrero=" + numrero +
                ", estado='" + estado + '\'' +
                '}';
    }
}
