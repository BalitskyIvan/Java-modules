In the directory ImagesToChar:

To run the program, follow these steps:
1. mkdir target &&  find -name "*.java" > source.txt && cp -r src/resources/ target

Compile project:
2. javac -d target @source.txt

Create own images-to-chars-printer.jar archive:
3. jar cvmf src/manifest.txt target/images-to-chars-printer.jar -C target ./edu/school21/printer

Run the program:
4. java -jar target/images-to-chars-printer.jar <charcter to white color> <character to black color> target/resources/image.bmp
