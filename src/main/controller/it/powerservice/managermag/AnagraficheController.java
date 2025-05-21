package it.powerservice.managermag;
import it.powerservice.managermag.customClass.AnagraficheEstese;
import it.powerservice.managermag.griglia.GridColumn;
import it.powerservice.managermag.griglia.GridColumnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class AnagraficheController {
    @Autowired
    private AnagraficheService anagraficheService;
    @Autowired
    GridColumnsService gridColumnsService;

    @GetMapping("/anagrafiche")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<AnagraficheEstese> getAnagrafiche() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<GridColumn> columns = gridColumnsService.getAnagraficheColModel();
        List<String> filters = new ArrayList<>();
        StringBuilder whereClause = new StringBuilder("WHERE ");

        boolean firstCondition = true;


        for (GridColumn c : columns) {
            String title = (Objects.equals(c.getDataIndx(), "pIva") || Objects.equals(c.getDataIndx(), "codiceFiscale") ||
                    Objects.equals(c.getDataIndx(), "regione") || Objects.equals(c.getDataIndx(), "provincia") ||
                    Objects.equals(c.getDataIndx(), "comune") || Objects.equals(c.getDataIndx(), "provaData"))
                    ? "i." + c.getDataIndx() : "a." + c.getDataIndx();

            for (int i = 1; i <= 5; i++) {
                String filter = (String) c.getClass().getMethod("getFilter" + i).invoke(c);

                if (filter != null && !filter.isEmpty()) {
                    String[] filterParts = filter.split("/");
                    String operator = FilterUtility.getOperator(filterParts[0]);
                    String filterValue = filterParts[1];


                    if (!firstCondition) {
                        whereClause.append(" AND ");
                    } else {
                        firstCondition = false;
                    }

                    if (operator.equals("CONTAIN")) {
                        operator = "=";
                        String[] multiselectOptions = filterValue.split("-");

                        whereClause.append("(");
                        for (int j = 0; j < multiselectOptions.length; j++) {
                            if (multiselectOptions[j].contains(".")) {
                                multiselectOptions[j] = multiselectOptions[j].replaceAll("\\.", "-");
                            }
                            if (j > 0) {
                                whereClause.append(" OR ");
                            }
                            whereClause.append(title).append(" ").append(operator).append(" '").append(multiselectOptions[j]).append("'");
                        }
                        whereClause.append(")");
                    } else if (operator.equals("NOTCONTAIN")) {
                        operator = "NOT LIKE";
                        String[] multiselectOptions = filterValue.split("-");

                        whereClause.append("(");
                        for (int j = 0; j < multiselectOptions.length; j++) {
                            if (multiselectOptions[j].contains(".")) {
                                multiselectOptions[j] = multiselectOptions[j].replaceAll("\\.", "-");
                            }
                            if (j > 0) {
                                whereClause.append(" AND ");
                            }
                            whereClause.append(title).append(" ").append(operator).append(" '").append(multiselectOptions[j]).append("'");
                        }
                        whereClause.append(")");
                    } else if (operator.equals("BETWEEN") && filterParts.length > 2) {

                        String filterValue2 = filterParts[2];
                        if (c.getDataType().equals("date")) {
                            whereClause.append(title).append(" ").append(operator)
                                    .append(" '").append(filterValue).append("' AND '").append(filterValue2).append("'");
                        } else {
                            whereClause.append(title).append(" ").append(operator)
                                    .append(" '").append(filterValue).append("' AND '").append(filterValue2).append("'");
                        }

                    } else {
                        whereClause.append(title).append(" ").append(operator).append(" '").append(filterValue).append("'");
                    }
                }
            }
        }

        System.out.println(whereClause);

        return anagraficheService.getAnagraficheEstese(whereClause.toString());

    }

    @GetMapping("/autocompleteSearch")
    public List<String> autocompleteSearch(
            @RequestParam String field,
            @RequestParam String value) {
        return anagraficheService.autocompleteSearch(field, value);
    }
}
