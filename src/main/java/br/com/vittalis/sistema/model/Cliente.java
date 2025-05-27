package br.com.vittalis.sistema.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@DiscriminatorValue(value = "C")
@Entity
public class Cliente extends Pessoa{

}
