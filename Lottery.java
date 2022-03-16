
package lottery;

import java.util.Arrays;
import java.util.Random;

public class Lottery 
{ 
  //An attribute of type String called ticketwhich holds the digit values.Make it private as that is good practice!
  private String ticket;
  
  
//getter for ticket
public String GetTicket()//done
{
  return ticket;
}


//setter for ticket
public void SetTicket(String newTik)//done
{
 this.ticket = newTik;
}


//One defaultconstructor that sets ticketto an empty string.
public Lottery()//done
{
    ticket = "";
}


//One overloaded that takes a Random class reference.
public Lottery(Random rn)//done overload
{
    //makes it into a string
    ticket = Integer.toString(rn.nextInt(1000000 + 1));
}
   

//makes a Random class object reference as a parameterand the max possible index of the array in a given scenario.
public static String GenerateRandomWinner(Random rn)//done
{
//This method generates a random digits for the ticket and returns it. The type is String.
    return Integer.toString(rn.nextInt(1000000 + 1));
}


//takes a Random class object reference as a parameter and the max possible index of the array in a given scenario.
public static int GenerateSelectWinner(int max,Random rn)//done
{
    //Method returns an index of the array of a winning ticket. 
    return rn.nextInt(max); 
}

//Turns the string array into an int array
public static int[]StrToInt(Lottery[] tickets)//done
{
    int[] toInt = new int[tickets.length];
    
    for(int i=0; i<tickets.length;i++)
    {
        toInt[i]= Integer.parseInt(tickets[i].GetTicket());
    }
  
  return toInt;          
}

// sorts the num tickets
public static void Sort(Lottery[] tickets)//done
{
    int[] ToInt = StrToInt(tickets);
    //array native sort thing
    Arrays.sort(ToInt);
    
       for(int i=0; i<tickets.length;i++)
    {
        //sets the integer array into a array of strings
        tickets[i].SetTicket(Integer.toString(ToInt[i]));
    }
}

public static boolean Solution1(Lottery[] tickets,String ticketNum,int max) //linear search done
{
    //One of the solutions that finds the winning ticket.
    //This method should run O(n).
    //Returns a Boolean if found or not.
    int arrlen = max;
    for (int i = 0; i < arrlen; i++)
    {
        //if what the value of the array is what we are looking for retuen true
        if (tickets[i].GetTicket().equals(ticketNum))
        return true;
    }
    //if we dont find it return false
    return false;
}    

public static boolean Solution2(Lottery[] tickets,int min, int max,String Num) //binary search done
{
    //This method should run O(lgn).
    //Returns a Boolean if found or not.
       
    int left = min;
    int right = max ;
    int mid = left + (right - left) / 2;
    while (left <= right) 
    {
        //checks mid
        if (tickets[mid].GetTicket().equals(Num))
            return true; 
        //ignores the left side
        if (Integer.parseInt(tickets[mid].GetTicket())< Integer.parseInt(Num))
            left = mid + 1;
        // ignores right side
        else               
            right = mid - 1;
        mid = left + (right - left) / 2;
    }         
    //not found return false
    return false;
    
}
   
}
