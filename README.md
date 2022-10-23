# GenericLinkedList
This is a basic linked list that uses Generics in Java. This was part one of developing my database project

This linked list includes methods such as:
- addFront
    - receives an item to add as a parameter, and adds to the front of the list.

- addEnd
    - receives an item to add as a parameter, and adds to the end of the list.

- removeFront
     - removes a node from the front of the list.

- removeEnd
    - removes a node from the end of the list.

- set
    - receives a position and item as parameters, sets the element at this
      position, provided it is within the current size

- get
    - receives a position as a parameter, returns the item at this position,
      provided it is within the current size

- swap
    - receives two index positions as parameters, and swaps the nodes at
      these positions (not just the values inside the nodes), provided 
      both positions are within the current size

- shift
    - receives an integer as a parameter, and shifts the list forward or
      backward this number of nodes, provided it is within the current size
           1,2,3,4,5    shifted +2    3,4,5,1,2
           1,2,3,4,5    shifted -1    5,1,2,3,4

- removeMatching
    - receives a value of the generic type as a parameter and removes all
      occurrences of this value from the list.

- erase 
    - receives an index position and number of elements as parameters, and
      removes elements beginning at the index position for the number of 
      elements specified, provided the index position is within the size
      and together with the number of elements does not exceed the size

- insertList
    - receives a generic List (a Java List) and an index position as parameters, 
      and copies each value of the passed list into the current list starting
      at the index position, provided the index position does not exceed the size.
      For example, if list has a,b,c and another list having 1,2,3 is inserted at
     position 2, the list becomes a,b,1,2,3,c
        
        
