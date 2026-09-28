// once again yoinked from will's branch

package org.firstinspires.ftc.teamcode;

public class utilityclass {
    static class coordsandangle{
        private double xCoord;
        private double yCoord;
        private double hdng;
        public coordsandangle(double xCoord, double yCoord, double hdng){
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            this.hdng = hdng;
        }

        public coordsandangle(coordsandangle coordsandangle) {
        }

        public double getXCoord(){
            return xCoord;
        }
        public double getYCoord(){
            return yCoord;
        }
        public double getHdng(){
            return hdng;
        }
    }
    class Queue{
        private int maxSize;
        private coordsandangle[ ] queArray;
        private int front;
        private int rear;
        private int nItems;
        //-----------------------------------------
        public Queue(int s){
            maxSize = s;
            queArray = new coordsandangle[maxSize];
            front = 0;
            rear = -1;
            nItems = 0;
        }
        //-----------------------------------------
        public void insert(coordsandangle coord){
            if(rear == maxSize-1)
                rear = -1;
            queArray[++rear] = coord;
            nItems++;
        }
        //------------------------------------------
        public coordsandangle remove(){
            coordsandangle temp = queArray[front++];
            if(front == maxSize)
                front = 0;
            nItems--;
            return temp;
        }
        //-----------------------------------------
        public coordsandangle peekFront(){
            return queArray[front];
        }
        public boolean isFull(){
            return (nItems==maxSize);
        }
        public boolean isEmpty(){
            return (nItems==0);
        }
        public int size(){
            return nItems;
        }
    }
}
