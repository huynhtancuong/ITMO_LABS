# What to change in makefile configuration?
* "out_server" - is the file name of server jar file
* "out_client" - is the file name of client jar file
* "out_sourceslist" - is the name of file which contain path to all your java file
* "env_var_name" - is the enviroment variable name, you have to change it according to your enviroment variable name
* "env_var_value" - is the value of the enviroment variable. By default, it is set to be the path of the current directory
* "server_ip" - is the IP address of the server, which is used by client to connect to the server
* "server_port" - is the port of the server, which is used by cleint to connect to the server
* "server_entry" - is the entry class for server jar file
* "client_entry" - is the entry class for client jar file
* "manifest" - it is just a variable to contain MANIFEST.MF file. You don't have to care about it
# How to use makefile?
* To compile java file into class file 
> $ make target
* To make a jar file for server
> $ make server
* To make a jar file for client
> $ make client
* To run server
> $ make run-server
* To run client
> $ make run-client
* To clean the generated files
> $ make clean
