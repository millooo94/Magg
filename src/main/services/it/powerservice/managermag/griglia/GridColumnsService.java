package it.powerservice.managermag.griglia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GridColumnsService {
    @Autowired
    GridColumnRepository gridColumnRepository;

    public List<GridColumn> getAnagraficheColModel() {
        return gridColumnRepository.getAnagraficheGridColumns();
    }

    public List<GridColumn> getMarcheColModel() {
        return gridColumnRepository.getMarcheGridColumns();
    }

    public void saveAnagraficheColModel(AnagraficheGridColumn anagraficheColModel) {
        gridColumnRepository.save(anagraficheColModel);
    }

    public void saveAll(List<GridColumn> columns) {
        gridColumnRepository.saveAll(columns);
    }

    public void saveSingle(Long id, String type, String subtype, Integer width) {

        System.out.println("SUBTYPE =====> " + subtype);

        Optional<GridColumn> optionalColumn = gridColumnRepository.findById(id);

        if (optionalColumn.isPresent()) {
            GridColumn existingColumn = optionalColumn.get();

            if (width > 0) {
                existingColumn.setWidth(width);
            } else {
                existingColumn.setWidth((existingColumn.getWidth()));
            }

            switch (type) {
                case "GROUP":
                    existingColumn.setGrouped(!existingColumn.getGrouped());
                    if (existingColumn.getGrouped()) {
                        Integer maxGroupIndex = gridColumnRepository.findMaxGroupIndex().orElse(0);

                        existingColumn.setGroupIndx(maxGroupIndex + 1);

                        if (subtype.equals("y")) {

                            Optional<GridColumn> gridColumn = gridColumnRepository.findById(id);

                            if (gridColumn.isPresent()) {

                                var yearGridColumn = new  GridColumn(
                                        0,
                                        gridColumn.get().getGrid(),
                                        gridColumn.get().getTitle() + " (Anno)",
                                        gridColumn.get().getDataIndx() + "-y",
                                        gridColumn.get().getDataType(),
                                        gridColumn.get().getInputType(),
                                        null,
                                        false,
                                        true,
                                        0,
                                        "y",
                                        "none",
                                        -1,
                                        "",
                                        "",
                                        "",
                                        "",
                                        ""
                                );

                                gridColumnRepository.save(yearGridColumn);

                            }


                        } else if (subtype.equals("ym")) {
                            Optional<GridColumn> gridColumn = gridColumnRepository.findById(id);

                            if (gridColumn.isPresent()) {

                                var yearGridColumn = new  GridColumn(
                                        0,
                                        gridColumn.get().getGrid(),
                                        gridColumn.get().getTitle() + " (Anno)",
                                        gridColumn.get().getDataIndx() + "-y",
                                        gridColumn.get().getDataType(),
                                        gridColumn.get().getInputType(),
                                        null,
                                        false,
                                        true,
                                        0,
                                        "y",
                                        "none",
                                        -1,
                                        "",
                                        "",
                                        "",
                                        "",
                                        ""
                                );

                                var monthGridColumn = new  GridColumn(
                                        1,
                                        gridColumn.get().getGrid(),
                                        gridColumn.get().getTitle() + " (Mese)",
                                        gridColumn.get().getDataIndx() + "-ym",
                                        gridColumn.get().getDataType(),
                                        gridColumn.get().getInputType(),
                                        null,
                                        false,
                                        true,
                                        1,
                                        "ym",
                                        "none",
                                        -1,
                                        "",
                                        "",
                                        "",
                                        "",
                                        ""
                                );

                                gridColumnRepository.save(yearGridColumn);
                                gridColumnRepository.save(monthGridColumn);

                            }

                        }

                    } else {
                        existingColumn.setGroupIndx(-1);
                    }
                    break;
                case "SORT":
                    if (existingColumn.getSorted().equals("none")) {
                        if (subtype.equals("ASC")) {
                            existingColumn.setSorted("up");
                        } else if (subtype.equals("DESC")) {
                            existingColumn.setSorted("down");
                        }
                        Integer maxSortIndex = gridColumnRepository.findMaxSortIndex().orElse(0);
                        System.out.println("MAX SORT => " + maxSortIndex);
                        existingColumn.setSortIndx(maxSortIndex + 1);
                    } else {
                        existingColumn.setSorted("none");
                        existingColumn.setSortIndx(-1);
                    }
            }

            gridColumnRepository.save(existingColumn);
        }
    }



}


