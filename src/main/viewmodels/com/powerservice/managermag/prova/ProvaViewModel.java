package com.powerservice.managermag.prova;

import it.powerservice.managermag.Anagrafiche;
import it.powerservice.managermag.AnagraficheService;
import org.zkoss.bind.annotation.*;
import org.zkoss.zhtml.Li;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class ProvaViewModel extends SelectorComposer<Window> {

    @WireVariable
    AnagraficheService anagraficheService;

    private List<Anagrafiche> anagrafiche = new ArrayList<>();
    private List<Colonna> colonne = new ArrayList<>();


    private List<Cella> riga = new ArrayList<>();

    private List<List<Cella>> anagraficheee = new ArrayList<>();


    @Wire("#rows")
    private Rows rows;

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        test();
    }


    @Init
    public void init() {
        anagrafiche = anagraficheService.getAnagrafiche().subList(0, 12);
        initColonne();
        initRow();
        initAnagrafiche();
        System.out.println(rows);
    }

    public List<Anagrafiche> getAnagrafiche() {
        return anagrafiche;
    }

    @Command
    @NotifyChange({"colonne", "riga"})
    public void onDrop(@BindingParam("dragged") DropEvent dropEvent) {
        Component draggedComponent = dropEvent.getDragged();
        Component targetComponent = dropEvent.getTarget();

        if (draggedComponent instanceof Column && targetComponent instanceof Column) {
            Column draggedColumn = (Column) draggedComponent;
            Column targetColumn = (Column) targetComponent;

            int draggedIndex = draggedColumn.getParent().getChildren().indexOf(draggedColumn);
            int targetIndex = targetColumn.getParent().getChildren().indexOf(targetColumn);

            Colonna tempColModel = colonne.get(draggedIndex);
            Cella tempCellModel = riga.get(draggedIndex);

            colonne.set(draggedIndex, colonne.get(targetIndex));
            colonne.set(targetIndex, tempColModel);

            riga.set(draggedIndex, riga.get(targetIndex));
            riga.set(targetIndex, tempCellModel);

        } else {
            System.out.println("Uno o entrambi gli elementi non sono colonne.");
        }
    }



    public List<Colonna> getColonne() {
        return colonne;
    }

    public void initColonne() {
        colonne.add(new Colonna("Id", "id"));
        colonne.add(new Colonna("Codice", "codice"));
        colonne.add(new Colonna("Tipo", "tipo"));
        colonne.add(new Colonna("Cognome/Ragione Sociale", "cognomeRgs"));
        colonne.add(new Colonna("Nome", "nome"));
    }

    public void initRow() {
        riga.add(new Cella("1", 1));
        riga.add(new Cella("C000", "C000"));
        riga.add(new Cella("C", "C"));
        riga.add(new Cella("Licciardello", "Licciarderllo"));
        riga.add(new Cella("Giuseppe", "Giuseppe"));
    }

    public void initAnagrafiche() {
        var result = anagraficheService.getAnagrafiche();

        for (Anagrafiche r : result) {
            List<Cella> row = new ArrayList<>();
            Field[] rowFields = r.getClass().getDeclaredFields();

            for (Field field : rowFields) {
                field.setAccessible(true);
                try {
                    String label = field.getName();
                    Object value = field.get(r);

                    row.add(new Cella(label, value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            this.anagraficheee.add(row);
        }
    }

    public void test() {
        var result = anagraficheService.getAnagrafiche();

        for (Anagrafiche r : result) {
            Row row = new Row();
            Field[] rowFields = r.getClass().getDeclaredFields();

            for (Field field : rowFields) {
                System.out.println(field);
                field.setAccessible(true);
                try {
                    Label label = new Label(field.get(r).toString());
                    row.appendChild(label);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            rows.appendChild(row);
        }
    }

    public List<Cella> getRiga() {
        return riga;
    }

    private void prepareRows() {
        rows.getChildren().clear(); // Pulisce le righe esistenti

        for (Cella cella : riga) {
            Row row = new Row();
            Label label = new Label(cella.getLabel());
            label.setValue("ciaoooo");
            row.appendChild(label);
            rows.appendChild(row);
        }
    }
}
