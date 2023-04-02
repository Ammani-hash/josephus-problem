public class PersonNode {
   public final String name;     // this is final so that you do not try to change the name in a node
   public PersonNode next;
   
   public PersonNode(String name) {
      this.name = name;
      this.next = null;
   }
   
   public PersonNode(String name, PersonNode next) {
      this.name = name;
      this.next = next;
   }
}