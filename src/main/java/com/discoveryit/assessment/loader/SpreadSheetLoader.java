package com.discoveryit.assessment.loader;

import com.discoveryit.assessment.model.Planet;
import com.discoveryit.assessment.model.Route;
import com.discoveryit.assessment.repository.PlanetRepository;
import com.discoveryit.assessment.repository.RouteRepository;
import com.discoveryit.assessment.service.InterstellarGraph;
import com.discoveryit.assessment.service.InterstellarService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

@Component
public class SpreadSheetLoader {

    private Workbook workbook;
    private Sheet planetWorksheet;
    private Sheet routeWorkSheet;

    private String xlsFile = "https://curiousoft-bucket.s3.amazonaws.com/test.xlsx";

    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Bean
    public InterstellarGraph initGraph() throws InvalidFormatException, IOException, URISyntaxException {
        System.out.println("post constuct...");
        processSpreadSheet();

        List<Planet> planets = planetRepository.findAll();
        List<Route> routes = routeRepository.findAll();
        return InterstellarGraph.create(planets, routes);

    }

    public void processSpreadSheet() throws InvalidFormatException, IOException, URISyntaxException {
        readSpreadSheet();
        planetWorksheet = readSheet(workbook, 0);
        routeWorkSheet = readSheet(workbook, 1);
        loadPlanets(planetWorksheet);
        loadRoutes(routeWorkSheet);
    }

    /**
     * reads data from spreadsheet
     */
    public void readSpreadSheet() throws InvalidFormatException, IOException, URISyntaxException {
            workbook = createWorkBook();

    }

    private void loadPlanets(Sheet sheet) {
        Iterator<Row> rows = sheet.iterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            // TODO - handle invalid input
            persistPlanet(new Planet(row.getCell(0).toString(), row.getCell(1).toString()));
        }
    }

    /**
     * load routes to the database
     *
     * @param sheet
     */
    private void loadRoutes(Sheet sheet) {
        Iterator<Row> rows = sheet.iterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            if (!row.getCell(0).toString().contains("Route Id")) {
                String id = row.getCell(0).toString();
                Planet source = getPlanet(row.getCell(1).toString());
                Planet destination = getPlanet(row.getCell(2).toString());
                Float distance = (float) row.getCell(3).getNumericCellValue();
                if (source != null && destination != null) {
                    persistRoute(new Route(id, source, destination, distance));
                }

            }
        }
    }

    /**
     * creates workbook
     *
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    private Workbook createWorkBook() throws IOException, InvalidFormatException, URISyntaxException {

        InputStream uri = new URL(xlsFile).openStream();
        return WorkbookFactory.create(uri);
    }

    /**
     * read worksheet
     *
     * @param workbook
     * @param workSheetIndex
     * @return
     */
    private Sheet readSheet(Workbook workbook, int workSheetIndex) {
        return workbook.getSheetAt(workSheetIndex);
    }

    private void persistPlanet(Planet planet) {
        planetRepository.save(planet);
    }

    private void persistRoute(Route route) {
        routeRepository.save(route);
    }

    /**
     * get Planet by ID
     *
     * @param id
     * @return
     */
    private Planet getPlanet(String id) {
        return planetRepository.findById(id).orElse(null);
    }
}
