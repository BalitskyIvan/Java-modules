In the directory ImagesToChar:

Download jcommander and JCDP archives:
wget https://repo1.maven.org/maven2/com/beust/jcommander/1.69/jcommander-1.69.jar -P lib
wget https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar -P lib

To run the program, follow these steps:
1. mkdir target &&  find -name "*.java" > source.txt && cp -r src/resources/ target

Unzip archives:
2. jar xvf lib/jcommander-1.69.jar && jar xvf lib/JCDP-4.0.2.jar && rm -rf META-INF

Compile project:
3. javac -d target @source.txt && mv com/ target/

Create own images-to-chars-printer.jar archive:
4. jar cvmf src/manifest.txt target/images-to-chars-printer.jar -C target ./

Run the program:
5. java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN
