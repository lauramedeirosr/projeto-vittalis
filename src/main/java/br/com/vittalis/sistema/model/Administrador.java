package br.com.vittalis.sistema.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@DiscriminatorValue(value = "A")
@Entity
public class Administrador extends Pessoa {

  
}
