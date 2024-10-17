package com.powerservice.managermag;

import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class IndexViewModel {

    private Boolean isRightSidebarOpen = false;
    private Boolean isLeftSidebarOpen = false;
    private String mainContainerMenuClass = "";


    @Command
    @NotifyChange({"isRightSidebarOpen", "mainContainerMenuClass"})
    public void onOpenRightSidebar() {
        isRightSidebarOpen = true;
        mainContainerMenuClass = "right-sidebar-collapsed";
    }
    @Command
    @NotifyChange({"isRightSidebarOpen", "mainContainerMenuClass"})
    public void onCloseRightSidebar() {
        isRightSidebarOpen = false;
        mainContainerMenuClass = "";
    }

    @Command
    @NotifyChange({"isLeftSidebarOpen", "mainContainerMenuClass"})
    public void onToggleLeftSidebar() {
        isLeftSidebarOpen = !isLeftSidebarOpen;
        mainContainerMenuClass = isLeftSidebarOpen ? "left-sidebar-collapsed" : "";

    }

    @Command
    public void onOpenModal(@BindingParam("modal") String modal) {

        var modalUrl = "/zul/" + modal + "/" + modal + ".index.zul";

        Window window = (Window)Executions.createComponents(
                modalUrl, null, null);
        window.doModal();
    }

    public Boolean getIsRightSidebarOpen() {
        return isRightSidebarOpen;
    }

    public Boolean getIsLeftSidebarOpen() {
        return isLeftSidebarOpen;
    }

    public String getMainContainerMenuClass() {
        return mainContainerMenuClass;
    }

}