**PreRequisite**  
install latest java sdk on your system  

**Build Instructions**  
go to root folder of the project and run following commands  
javac src/main/java/cronParser/parser/*.java  
jar -cvmf manifest.mf parser.jar src/main/java/cronParser/parser/*.class  

a jar file will be created at the root folder.  

**How to run the project**  
java -jar parser.jar "<INPUT ARGUMENT>"  

sample imput  
java -jar parser.jar "*/15 0 1,15 * 1-5 /usr/bin/find"  

sample output  
minute 0,15,30,45  
hours 0  
dayOfMonth 1,15  
month 1,2,3,4,5,6,7,8,9,10,11,12  
dayofWeek 1,2,3,4,5  
command  /usr/bin/find  
