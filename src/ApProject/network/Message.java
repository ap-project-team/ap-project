package src.ApProject.network;

public abstract class Message {
    boolean messageDone = false;
    public abstract void run();//todo remember to check the message done after running it
    /*this class and method are added to have a wrapper to send objects through network
    * and to call run method of each type of action and do the task relating to that message
    * using polymorphism*/
    public boolean isMessageDone() {//todo pay attention to this method implement
        boolean temp = messageDone;
        messageDone = true;
        return temp;
    }

    /*
    * different functions that should be called in the game should be called by run method of the specific type of message*/
}
