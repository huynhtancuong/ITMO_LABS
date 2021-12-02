cd src/main/java
javac -d target/ -cp .:Pokemon.jar Battleground.java Moves/*.java Pokemons/*.java
jar -cvfm MyBattleground.jar MANIFEST.MF Battleground.class Moves/*.class Pokemons/*.class Pokemon.jar
