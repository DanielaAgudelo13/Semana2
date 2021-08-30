package com.example.semana2;

public class pregunta {

    // Atributos

    private int operadoA;
    private int operadoB;
    private String operador;
    private String[] operadores = { "+", "-", "x", "/"};

    // Constructor

    public pregunta() {
        this.operadoA = (int)(Math.random() * 11);
        this.operadoB = (int)((Math.random() * 11) + 1);

        int posicion = (int)(Math.random() * 4);
        this.operador = operadores[posicion];

    }

    // Métodos

    public String getPregunta(){
        return operadoA + " " + operador + " " + operadoB;

    }

    public int getRespuesta() {
        int respuesta = 0;
        switch (operador){
            case "+":
                respuesta = this.operadoA + this.operadoB;
                break;

            case "-":
                respuesta = this.operadoA - this.operadoB;
                break;

            case "x":
                respuesta = this.operadoA * this.operadoB;
                break;

            case "/":
                respuesta = this.operadoA / this.operadoB;
                break;

        }

        return respuesta;

    }
 }
