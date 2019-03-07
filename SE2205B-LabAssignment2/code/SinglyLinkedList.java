public class SinglyLinkedList<E>{

  private static class Node<E>{
    private E element;
    private Node<E> next;

    public Node(E e, Node<E> n){
        element = e;
        next = n;
    }
    public E getElement(){
        return element;
    }
    public Node<E> getNext(){
        return next;
    }
    public void setNext(Node<E> n){
        next = n;
    }
  }

  private Node<E> head,tail;
  private int size;

  public SinglyLinkedList(){
      head = null;
      tail = null;
      size = 0;
  }
  
  public int size(){ return size; }
  
  public boolean isEmpty(){
      if(size() == 0)
          return true;
      else
          return false;
  }
  
  public E first(){
      if(isEmpty())
          return null;
      else
          return head.getElement();
  }
  
  public E last(){
      return tail.getElement();
  }
  
  public void addFirst(E element){
      Node<E> newNode = new Node<>(element,head);
      head = newNode;

      if(tail == null)
          tail = newNode;

      size++;
  }
  
  public void addLast(E element){
      Node<E> newNode = new Node<>(element,null);

      if(isEmpty()){
          head = newNode;
          tail = newNode;
          size++;
      }else{
          tail.setNext(newNode);
          tail = newNode;
          size++;
      }


  }
  
  public E removeFirst() {
      if (head == null)
          return null;

      // save the one to return
      Node<E> temp = head;

      // do reference manipulation
      head = head.getNext();
      temp.setNext(null);
      size--;
      if(size() == 0)
          tail = null;

      return temp.getElement();
  }

}