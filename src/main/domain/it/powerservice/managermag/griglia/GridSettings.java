package it.powerservice.managermag.griglia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gridsettings")
public class GridSettings {
    @Id
    private Long id;
    @Column(name = "freeze")
    private Integer freeze;
    @Column(name = "collapsed")
    private Boolean collapsed;
    @Column(name = "groupingsapplied")
    private Boolean groupingsApplied;
    @Column(name = "grid")
    private String grid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public Boolean getCollapsed() {
        return collapsed;
    }

    public void setCollapsed(Boolean collapsed) {
        this.collapsed = collapsed;
    }

    public Boolean getGroupingsApplied() {
        return groupingsApplied;
    }

    public void setGroupingsApplied(Boolean groupingsApplied) {
        this.groupingsApplied = groupingsApplied;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }
}
