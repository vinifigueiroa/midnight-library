# midnight-library
UoPeople Programming Assignment (CS1102)

## Assignment Description

You have been assigned the task of developing a program that manages a library system. The program should allow users to borrow and return books, as well as display information about the available books. You are required to implement nested control structures to handle complex scenarios efficiently and incorporate error handling techniques using control structures to address anticipated errors and exceptional situations.

For this assignment, write a Java program that implements a library system using nested control structures and error handling techniques. 

The program should provide the following functionalities:

**Add Books:** Implement a method called addBook that takes the book title, author, and quantity as parameters. The method should add the book to the library system, considering the quantity of books provided. If the book already exists in the system, the quantity should be updated accordingly.

**Borrow Books:** Implement a method called borrowBook that takes the book title and the number of books to be borrowed as parameters. The method should check if the requested number of books is available in the library system. If the books are available, the quantity should be updated accordingly. If the requested number of books is not available or the book does not exist, an appropriate error message should be displayed.

**Return Books:** Implement a method called returnBook that takes the book title and the number of books to be returned as parameters. The method should check if the books being returned belong to the library system. If they do, the quantity should be updated accordingly. If the books being returned do not exist in the system or the quantity to be returned is greater than the borrowed quantity, an appropriate error message should be displayed.

Implement the above functionalities by using nested control structures to handle complex scenarios and incorporate error handling techniques to address anticipated errors and exceptional situations. Assume that the library system is represented by a class called Library with appropriate instance variables and methods. Write the Java program for the library system, including the implementation of the addBook, borrowBook, and returnBook methods, as well as the necessary error handling and nested control structures.
