public class LinkedListQueue<E> implements Queue<E>{
  private SinglyLinkedList<E> list;

  public LinkedListQueue(){
      list = new SinglyLinkedList<>();
  }

  @Override
  public int size(){
      return list.size();
  }

  @Override
  public boolean isEmpty(){
      return list.isEmpty();
  }

  @Override
  public E first(){ return list.first(); }

  @Override
  public void enqueue(E node){ list.addLast(node); }

  @Override
  public E dequeue() { return list.removeFirst(); }
}
