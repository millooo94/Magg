package it.powerservice.managermag.griglia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gridconf")
public class GridConf {
    @Id
    private Long id;
    @Column(name = "section")
    private String section;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "GridConf{" +
                "id=" + id +
                ", section='" + section + '\'' +
                '}';
    }
}
