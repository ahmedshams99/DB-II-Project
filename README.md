This code will help you easily drop a database and recreate it with its tables and insert all the data in it with 1 click.

To use this code, simply <br>
1.copy the 2 methods in the AutoRefresh_Database.java file and paste them in your code <br>
2.Write in your main method refreshSchema("schema#","yourPassword") above the line connection = DriverManager.getConnection("jdbc:postgresq.....<br>
Ex:<br>
refreshSchema("schema1","verystrongpassword"); //to refresh Schema1 with password verystrongpassword
