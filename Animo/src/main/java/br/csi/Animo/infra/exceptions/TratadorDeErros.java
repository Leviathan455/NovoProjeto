package br.csi.Animo.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TratadorDeErros {


    // 404 - Recurso não encontrado
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> tratarErro404(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Recurso não encontrado.");
    }

    // 400 - Argumento inválido no corpo da requisição
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> tratarErro400BadRequest(MethodArgumentTypeMismatchException ex){
        return ResponseEntity.badRequest().body("URL mal formatada: " + ex.getMessage());
    }
    // 500 - Erro genérico de servidor
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErreDadosInvalidos(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getFieldErrors();
        List<DadosErroValidacao>dados = new ArrayList<>();
        for (FieldError fe : errors){
            dados.add(new DadosErroValidacao(fe.getField(), fe.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(dados);
    }
    private record DadosErroValidacao(String campo, String mensagem){

    }
}
