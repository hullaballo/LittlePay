import Models.Journey;
import Models.Trip;

import java.util.*;

public class LittlePayApplication {

    public static void main(String[] args)
    {
        JourneyImpl journeyImpl = new JourneyImpl();
        List<List<Trip>> filteredJourneys;
        List<Journey> finalList = new ArrayList<>();

        CSVManipulator csvManipulator = new CSVManipulator("test.csv", "output.csv");

        filteredJourneys = journeyImpl.getFilteredJourneys(csvManipulator.parseCSV());

        for (List<Trip> tripList : filteredJourneys)
        {
            finalList.addAll(journeyImpl.checkJourneyStatus(new LinkedList<>(tripList)));
        }

        System.out.println("CSV Write Success: "+ csvManipulator.writeCSV(finalList));
    }
}