package it.powerservice.managermag.griglia;

import jakarta.persistence.*;

@Entity
@Table(name = "gridcolumns")
public class GridColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "position")
    private int position;
    @Column(name = "grid")
    private String grid;

    @Column(name = "title")
    private String title;
    @Column(name = "dataindx")
    private String dataIndx;
    @Column(name = "datatype")
    private String dataType;
    @Column(name = "inputtype")
    private String inputType;
    @Column(name = "format")
    private String format;
    @Column(name = "hidden")
    private Boolean hidden;
    @Column(name = "width")
    private Integer width;
    @Column(name = "grouped")
    private Boolean grouped;
    @Column(name = "groupindx")
    private int groupIndx;
    @Column(name = "grouptype")
    private String groupType;
    @Column(name = "sorted")
    private String sorted;
    @Column(name = "sortindx")
    private int sortIndx;
    @Column(name = "filter1")
    private String filter1;
    @Column(name = "filter2")
    private String filter2;
    @Column(name = "filter3")
    private String filter3;
    @Column(name = "filter4")
    private String filter4;
    @Column(name = "filter5")
    private String filter5;


    public GridColumn() {
    }

    public GridColumn(int position, String grid, String title, String dataIndx, String dataType, String inputType, String format, Boolean hidden, Boolean grouped, int groupIndx, String groupType, String sorted, int sortIndx,String filter1, String filter2, String filter3, String filter4, String filter5) {
        this.position = position;
        this.grid = grid;
        this.title = title;
        this.dataIndx = dataIndx;
        this.dataType = dataType;
        this.inputType = inputType;
        this.format = format;
        this.hidden = hidden;
        this.grouped = grouped;
        this.groupIndx = groupIndx;
        this.groupType = groupType;
        this.sorted = sorted;
        this.sortIndx = sortIndx;
        this.filter1 = filter1;
        this.filter2 = filter2;
        this.filter3 = filter3;
        this.filter4 = filter4;
        this.filter5 = filter5;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDataIndx() {
        return dataIndx;
    }

    public void setDataIndx(String dataIndx) {
        this.dataIndx = dataIndx;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public Integer getWidth() {
        return width;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getGrouped() {
        return grouped;
    }
    public void setGrouped(Boolean grouped) {
        this.grouped = grouped;
    }


    public int getGroupIndx() {
        return groupIndx;
    }

    public void setGroupIndx(int groupIndx) {
        this.groupIndx = groupIndx;
    }
    public String getGroupType() {
        return groupType;
    }
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
    public String getSorted() {
        return sorted;
    }

    public void setSorted(String sorted) {
        this.sorted = sorted;
    }

    public int getSortIndx() {
        return sortIndx;
    }

    public void setSortIndx(int sortIndx) {
        this.sortIndx = sortIndx;
    }

    public String getGrid() {
        return grid;
    }
    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getFilter1() {
        return filter1;
    }

    public void setFilter1(String filter1) {
        this.filter1 = filter1;
    }

    public String getFilter2() {
        return filter2;
    }

    public void setFilter2(String filter2) {
        this.filter2 = filter2;
    }

    public String getFilter3() {
        return filter3;
    }

    public void setFilter3(String filter3) {
        this.filter3 = filter3;
    }

    public String getFilter4() {
        return filter4;
    }

    public void setFilter4(String filter4) {
        this.filter4 = filter4;
    }

    public String getFilter5() {
        return filter5;
    }

    public void setFilter5(String filter5) {
        this.filter5 = filter5;
    }

    @Override
    public String toString() {
        return "GridColumn{" +
                "id=" + id +
                ", position=" + position +
                ", grid='" + grid + '\'' +
                ", title='" + title + '\'' +
                ", dataIndx='" + dataIndx + '\'' +
                ", dataType='" + dataType + '\'' +
                ", format='" + format + '\'' +
                ", hidden=" + hidden +
                ", width=" + width +
                ", grouped=" + grouped +
                ", groupIndx=" + groupIndx +
                ", groupType='" + groupType + '\'' +
                ", sorted='" + sorted + '\'' +
                ", sortIndx=" + sortIndx +
                ", filter1='" + filter1 + '\'' +
                ", filter2='" + filter2 + '\'' +
                ", filter3='" + filter3 + '\'' +
                ", filter4='" + filter4 + '\'' +
                ", filter5='" + filter5 + '\'' +
                '}';
    }
}
