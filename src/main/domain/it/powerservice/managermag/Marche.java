package it.powerservice.managermag;

import jakarta.persistence.*;

@Entity
@Table(name = "marche")
public class Marche {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL")
   private Long id;
    @Column(name = "marca", nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    private String marca;
    @Column(name = "eliminato", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    private Boolean eliminato = false;

    @Transient
    Boolean isUpdating = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Boolean getEliminato() {
        return eliminato;
    }

    public void setEliminato(Boolean eliminato) {
        this.eliminato = eliminato;
    }

    public Boolean getIsUpdating() {
        return isUpdating;
    }

    public void setIsUpdating(Boolean updating) {
        isUpdating = updating;
    }

    @Override
    public String toString() {
        return "Marche{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", eliminato=" + eliminato +
                ", isUpdating=" + isUpdating +
                '}';
    }
}
