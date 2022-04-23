.PHONY: server client

out_server := server.jar 
out_client := client.jar
out_sourceslist := sourceslist.txt
env_var_name := HEHE
env_var_value := data.csv
server_ip := 127.0.0.1
server_port := 1821
server_entry := server.App
client_entry := client.App
manifest := MANIFEST.MF

path_libs += libs/log4j-api-2.17.2.jar
path_libs := $(path_libs):libs/log4j-core-2.17.2.jar

_path_libs := $(subst :, ,$(path_libs))

sourceslist.%:
	find . -name *.java > $(out_sourceslist)
target: $(out_sourceslist)
	javac --class-path $(path_libs) -d target @$(out_sourceslist)
env-var:
	export $(env_var_name)=$(shell pwd)/$(env_var_value)

server: target
	echo "Main-class: $(server_entry) \nClass-Path: target/ $(_path_libs) \n" > $(manifest) 
	jar -cvfm $(out_server) $(manifest) target/* $(_path_libs)

run-server: $(out_server) 
	export $(env_var_name)=$(shell pwd)/$(env_var_value);\
	java -jar $(out_server)	

run-client: $(out_client) 
	java -jar $(out_client)	$(server_ip) $(server_port)

client: target
	echo "Main-class: $(client_entry) \nClass-Path: target/ $(_path_libs) \n" > $(manifest) 
	jar -cvfm $(out_client) $(manifest) target/* $(_path_libs)
clean:
	rm -rf target
	rm $(out_server)
	rm $(out_client)
	rm $(out_sourceslist)
	rm $(manifest)
