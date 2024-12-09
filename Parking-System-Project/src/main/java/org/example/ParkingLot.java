package org.example;

import java.util.concurrent.Semaphore;

public class ParkingLot {
   public Semaphore lots ;
   public ParkingLot() {

       lots = new Semaphore(4,true);

   }
   public boolean TryToPark(){
       return lots.tryAcquire();
   }
   public void wait_to_park() throws InterruptedException {
       lots.acquire();
   }
   public void Unpark()  {
       lots.release();
   }
   public int GetParkingLots(){
       return lots.availablePermits();
   }
}