import java.util.*;
import java.io.*;

public class JosephusSim {
   private PersonNode circle;     // a PersonNode pointer that tracks first node
   private int size;              // the number of people in the circle
   private int eliminationCount;  // the number to count to for elimination       
   private PersonNode track;      // a PersonNode pointer to help with elimination

   public JosephusSim(String fileName) {
      try {
         // load names from the file in order, generating a singly linked list of PersonNodes
         Scanner file = new Scanner(new File(fileName));
         if (file.hasNext()) {
            circle = new PersonNode(file.nextLine());
            size = 1;
            PersonNode current = circle;
            while (file.hasNext()) {
               current.next = new PersonNode(file.nextLine());
               current = current.next;
               size++;
            }
            // make the ring circular by attaching last node's next to front
            current.next = circle;
            
            // remember the last node as the one in front of the next to get eliminated
            track = current;
            
            // generate, print, and save the random elimination count
            eliminationCount = (int) (Math.random() * size);
            System.out.println("The elimination count is: " + eliminationCount);
         } else {
            System.out.println("The file is empty!");
         }
      } catch(FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }
   
   //There is something wrong with this method. It keeps counting from the last eliminated node. Couldn't figure it out.
   public void eliminate() {
       // move track around the circle to the node before the one that will be eliminated
       for (int i = 0; i < eliminationCount - 1; i++) {
           track = track.next;
       }
       
       // print who will be eliminated
       System.out.println(track.next.name + " has been eliminated.");
       
       // eliminate the person and update "front" of the circle and size
       track.next = track.next.next;
       size--;
       
       // move track to the next person who will be eliminated
       track = track.next;
   }
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      return size == 1;
   }
   
   public String toString() {
      StringBuilder nameVar = new StringBuilder();
      
      // if there's only one person left, print them as the last survivor
      if (isOver()) {
         nameVar.append("The survivor is: " + circle.name);
      } else {
         // print the remaining survivors (watch out for infinite loop since list is circular)
         nameVar.append("The remaining survivors are: ");
         PersonNode current = circle;
         while (current.next != circle) {
            nameVar.append(current.name + ", ");
            current = current.next;
         }
         nameVar.append(current.name + ".");
      }
      return nameVar.toString();
   }

}