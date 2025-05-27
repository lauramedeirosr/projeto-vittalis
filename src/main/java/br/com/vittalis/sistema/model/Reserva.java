package br.com.vittalis.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
public class Reserva {

    @Id
    @GeneratedValue
    private Long id;
    private double valor;
    private enum formaPagamento {PIX, boleto, cartão};
    private int acompanhante;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    // histórico de reservas

    private double subTotal;



}
