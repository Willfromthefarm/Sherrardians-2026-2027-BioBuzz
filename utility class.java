package org.firstinspires.ftc.teamcode;

public class utilityclass {
    class coordinates{
        private double xCoord;
        private double yCoord;
        public coordinates(double xCoord,double yCoord){
            this.xCoord = xCoord;
            this.yCoord = yCoord;
        }
        public double getXCoord(){
            return xCoord;
        }
        public double getYCoord(){
            return yCoord;
        }
    }
    class Queue{
        private int maxSize;
        private coordinates[ ] queArray;
        private int front;
        private int rear;
        private int nItems;
        //-----------------------------------------
        public Queue(int s){
            maxSize = s;
            queArray = new coordinates[maxSize];
            front = 0;
            rear = -1;
            nItems = 0;
        }
        //-----------------------------------------
        public void insert(coordinates coord){
            if(rear == maxSize-1)
                rear = -1;
            queArray[++rear] = coord;
            nItems++;
        }
        //------------------------------------------
        public coordinates remove(){
            coordinates temp = queArray[front++];
            if(front == maxSize)
                front = 0;
            nItems--;
            return temp;
        }
        //-----------------------------------------
        public coordinates peekFront(){
            return queArray[front];
        }
        public boolean isFull(){
            return (nItems==maxSize);
        }
        public int size(){
            return nItems;
        }
    }
}
