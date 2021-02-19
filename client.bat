set javafx="E:\Program Files\Java\javafx-sdk-11.0.2\lib"
javac -d classes --module-path %javafx% --add-modules javafx.controls --add-modules javafx.fxml -sourcepath src src/client/*.java 
copy src\gui\sample.fxml classes\gui
java -cp classes --module-path %javafx% --add-modules javafx.controls --add-modules javafx.fxml client.ClientDriver