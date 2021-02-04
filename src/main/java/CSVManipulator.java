import Models.Journey;
import Models.Trip;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVManipulator {

    private String inputFileName;
    private String outputFileName;

    public CSVManipulator()
    {
        this.inputFileName = "src/main/resources/CSV/Input/taps.csv";
        this.outputFileName = "src/main/resources/CSV/Output/trips.csv";
    }

    public CSVManipulator(String inputFileName)
    {
        this.inputFileName = "src/main/resources/CSV/Input/".concat(inputFileName);
        this.outputFileName = "src/main/resources/CSV/Output/trips".concat(LocalDateTime.now().toString()).concat(".csv");
    }
    public CSVManipulator(String inputFileName, String outputFileName)
    {
        this.inputFileName = "src/main/resources/CSV/Input/".concat(inputFileName);
        this.outputFileName = "src/main/resources/CSV/Output/".concat(outputFileName);
    }

    public List<Trip> parseCSV()
    {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        csvMapper.registerModule(new JavaTimeModule());
        ObjectReader oReader = csvMapper.reader(Trip.class).with(schema);
        List<Trip> tripList = new ArrayList<>();

        try (Reader reader = new FileReader(this.inputFileName))
        {
            MappingIterator<Trip> mi = oReader.readValues(reader);

            while (mi.hasNext())
            {
                tripList.add(mi.next());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return tripList;
    }

    public Boolean writeCSV(List<Journey> journeyList)
    {
        try
        {
            CSVWriter writer = new CSVWriter(new FileWriter(this.outputFileName));
            String[] headers = {"Started", "Finished", "DurationSecs", "FromStopId", "ToStopId", "ChargeAmount", "CompanyId", "BusID",
                    "PAN", "Status"};
            writer.writeNext(headers);
            for (Journey individualJourney : journeyList)
            {
                individualJourney.toStringArray();
                writer.writeNext(individualJourney.toStringArray());
            }

            writer.close();
            return true;
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

}
