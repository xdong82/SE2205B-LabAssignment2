
import java.lang.*;

public class QueueSimulator{
  public enum Event { ARRIVAL, DEPARTURE };
  private double currTime;
  private double arrivalRate;
  private double serviceTime;

  private double timeForNextArrival;
  private double timeForNextDeparture;
  private double totalSimTime;
  LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
  LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();

  private Event e;
  
  public double getRandTime(double arrivalRate){
    double num, time1, max=1, min=0, randNUM;
    randNUM= Math.random();
    time1= (-1/arrivalRate) * (Math.log(1-randNUM));
    //System.out.println(time1);
    return time1;
  }
  
  public QueueSimulator(double aR, double servT, double simT){
    arrivalRate = aR;
    serviceTime = servT;
    totalSimTime = simT;
    timeForNextArrival = getRandTime(arrivalRate);
    timeForNextDeparture = (serviceTime+timeForNextArrival);
    currTime = 0;
  }
  
  public double calcAverageWaitingTime(){
      double totalArrivalTime = 0;
      double totalDepartureTime = 0;
      int numOfData = 0;
      double average;

      while(!eventQueue.isEmpty()) {
          totalArrivalTime += eventQueue.first().getArrivalTime();
          totalDepartureTime += eventQueue.first().getDepartureTime();
          numOfData++;

          eventQueue.dequeue();

      }

      average = (totalDepartureTime - totalArrivalTime)/numOfData;
      return average;

  }
  
  public double runSimulation(){

      while(currTime <= totalSimTime){
         // System.out.println("Time for Next Departure: " + timeForNextDeparture + " Time for Next Arrival: " + timeForNextArrival);
        if(timeForNextDeparture < timeForNextArrival && !buffer.isEmpty())
            e = Event.DEPARTURE;
        else
            e = Event.ARRIVAL;
       // System.out.println("Operation: " + e);
        switch(e){
            case ARRIVAL:
                currTime += timeForNextArrival;
                Data data = new Data();
                data.setArrivalTime(currTime);
                buffer.enqueue(data);
                timeForNextDeparture = timeForNextDeparture - timeForNextArrival;
                timeForNextArrival = getRandTime(arrivalRate);

                break;

            case DEPARTURE:
                currTime += timeForNextDeparture;
                buffer.first().setDepartureTime(currTime);
                eventQueue.enqueue(buffer.dequeue());
                timeForNextArrival = timeForNextArrival - timeForNextDeparture;
                if(buffer.isEmpty())
                    timeForNextDeparture = timeForNextArrival + serviceTime;
                else
                timeForNextDeparture = serviceTime;

                break;
        }
    }

    return calcAverageWaitingTime();
  }
}






