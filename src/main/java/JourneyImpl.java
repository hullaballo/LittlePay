import Models.*;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class JourneyImpl
{
    /*
     * Iterate over the unique PANs to retrieve journeys grouped by PAN, CompanyId and BusId allowing us
     */
    protected List<List<Trip>> getFilteredJourneys(List<Trip> initialTripList)
    {
        List<List<Trip>> filteredJourneys = new ArrayList<>();

        for (String pan : initialTripList.stream().map(Trip::getPAN).distinct().collect(Collectors.toList()))
        {
            filteredJourneys.addAll(initialTripList.stream().filter(p -> p.getPAN().equals(pan))
                    .sorted(Comparator.comparing(Trip::getDateTime))
                    .collect(Collectors.groupingBy(Trip::getPAN, Collectors.groupingBy(Trip::getCompanyId, Collectors.groupingBy(Trip::getBusId))))
                    .values().stream()
                    .map(Map::values)
                    .flatMap(Collection::stream)
                    .map(Map::values)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList()));
        }

        return filteredJourneys;
    }

    /*
     * Check trips based on TapType and format to Journeys based on relevant logic
     */
    protected List<Journey> checkJourneyStatus(LinkedList<Trip> tripList)
    {
        List<Journey> tripOutput = new ArrayList<>();

        while(tripList.size() > 0)
        {
            Trip currentTrip = tripList.pop();
            Trip nextTrip = tripList.peek();

            JourneyBuilder journeyBuilder = new JourneyBuilder().setBusID(currentTrip.getBusId())
                    .setCompanyId(currentTrip.getCompanyId())
                    .setPan(currentTrip.getPAN());

            if(nextTrip == null || currentTrip.getTapType().equals(nextTrip.getTapType()))
            {
                journeyBuilder.setStatus(JourneyStatus.INCOMPLETE)
                        .setChargeAmount(Currency.getInstance(Locale.getDefault()).getCurrencyCode()
                                .concat(Double.toString(calculateJourneyTotal(currentTrip.getStopId()))));

                if(currentTrip.getTapType().equals(TapType.ON))
                {
                    journeyBuilder.setStarted(currentTrip.getDateTime())
                            .setFinished(currentTrip.getDateTime())
                            .setFromStopId(currentTrip.getStopId())
                            .setToStopId(StopId.UNKNOWN_STOP)
                            .setDurationSecs(0L);
                }
                else
                {
                    journeyBuilder.setFinished(currentTrip.getDateTime())
                            .setStarted(currentTrip.getDateTime())
                            .setToStopId(currentTrip.getStopId())
                            .setFromStopId(StopId.UNKNOWN_STOP)
                            .setDurationSecs(0L);
                }
            }
            else
            {
                if (currentTrip.getStopId().equals(nextTrip.getStopId()))
                {
                    journeyBuilder.setStatus(JourneyStatus.CANCELLED)
                            .setChargeAmount(Currency.getInstance(Locale.getDefault()).getCurrencyCode()
                                    .concat(Double.toString(0.0)));
                }
                else
                {
                    journeyBuilder.setStatus(JourneyStatus.COMPLETED).setChargeAmount(Currency.getInstance(Locale.getDefault()).getCurrencyCode()
                            .concat(Double.toString(calculateJourneyTotal(currentTrip.getStopId(), nextTrip.getStopId()))));
                }

                journeyBuilder.setStarted(currentTrip.getDateTime())
                        .setFinished(nextTrip.getDateTime())
                        .setDurationSecs(ChronoUnit.SECONDS.between(currentTrip.getDateTime(), nextTrip.getDateTime()))
                        .setFromStopId(currentTrip.getStopId())
                        .setToStopId(nextTrip.getStopId());
                tripList.pop();
            }
            tripOutput.add(journeyBuilder.createJourney());
        }
        return tripOutput;
    }

    protected Double calculateJourneyTotal(StopId firstStop, StopId secondStop)
    {
        List<StopId> stopIdList = Arrays.asList(firstStop, secondStop);
        List<Double> amountsList = Arrays.asList(3.25, 5.5, 7.70);
        Double total = 0.0;

        if(stopIdList.contains(StopId.Stop1) && stopIdList.contains(StopId.Stop2))
        {
            total = amountsList.get(0);
        }
        else if(stopIdList.contains(StopId.Stop1) && stopIdList.contains(StopId.Stop3))
        {
            total = amountsList.get(1);
        }
        else if(stopIdList.contains(StopId.Stop2) && stopIdList.contains(StopId.Stop3))
        {
            total = amountsList.get(2);
        }
        else
        {
            if(firstStop.ordinal() == 0 || firstStop.ordinal() == StopId.values().length -1)
            {
                total = Collections.max(amountsList);
            }
            else
            {
                total = Collections.max(Arrays.asList(amountsList.get(firstStop.ordinal()-1), amountsList.get(secondStop.ordinal())));
            }
        }
        return total;
    }

    //Overload for journeys that have one stop
    protected Double calculateJourneyTotal(StopId stop)
    {
        return calculateJourneyTotal(stop, stop);
    }
}
