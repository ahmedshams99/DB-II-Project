This code will help you easily drop a database and recreate it with its tables and insert all the data in it with 1 click.

To use this code, simply 
1.copy the 2 methods and paste them in your code
2.Change the password
3.Write in your main method refreshSchema("schema#","yourPassword") above the line connection = DriverManager.getConnection("jdbc:postgresq.....
Ex:
refreshSchema("schema1","verystrongpassword"); //to refresh Schema1 with password verystrongpassword
