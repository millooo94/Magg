package it.powerservice.managermag.customClass;

public class TabRef {
    private String name;
    private String panel;

    public TabRef(String name, String panel) {
        this.name = name;
        initTabpanel(panel);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPanel() {
        return panel;
    }

    public void setTabpanel(String panel) {
        this.panel = panel;
    }

    public void initTabpanel(String panel) {
        this.panel = "/zul/anagrafiche/sezioni/anagrafiche." + panel + ".section.zul";
    }

    @Override
    public String toString() {
        return "TabRef{" +
                "name='" + name + '\'' +
                ", panel='" + panel + '\'' +
                '}';
    }
}
