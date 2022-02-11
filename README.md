# Order-Mngmnt
Application for processing clients' orders for a warehouse, implemented for the Fundamental Programming Techniques course // 2nd year, 2nd semester @ Computer Science, TUCN

The application was implemented in Java and it has a dedicated graphical user interface implemented with JavaFX for processing client orders for a warehouse. The application is structured in packages using a layered architecture (Model, Business Logic, Presentation and Data Access). <br>
JavaDoc files were generated to document the key points of the application. <br>
For storing the data of the application, relational databases were used, containing three tables: Client, Product and Order. <br>
The GUI contains: <br>
- a window for client operations: add new client, edit client, delete client, view all clients in a table; <br>
- a window for product operations: same as client operations; <br>
- a window for creating product orders: the user is able to select an existing product, select an existing client, and insert a desired quantity for the product to create a valid order. <br>

After placing an order, a .pdf bill is generated.

