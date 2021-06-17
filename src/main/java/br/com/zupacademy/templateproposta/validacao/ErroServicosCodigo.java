package br.com.zupacademy.templateproposta.validacao;

import org.springframework.http.HttpStatus;

public class ErroServicosCodigo extends Throwable {

    private HttpStatus codigoHttp;
    private String mensagem;

    public ErroServicosCodigo(HttpStatus httpStatus, String message) {
        super(message);
        this.codigoHttp = httpStatus;
        this.mensagem = message;
    }

    public HttpStatus getCodigoHttp() {
        return codigoHttp;
    }


}
