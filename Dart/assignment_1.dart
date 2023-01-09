// Assignment 1: Write a program that incorporates variables, loops, and at least 1 class.

import 'package:rpgnamegenerator/rpgnamegenerator.dart';
import 'dart:math';

class CharacterCreator {
  var gender, name, species;

  void setgender(gender) {
    this.gender = gender;
  }

  void setname(name) {
    this.name = name;
  }

  void setspecies(species) {
    this.species = species;
  }

  String get getgender {
    return this.gender;
  }

  String get getname {
    return this.name;
  }

  String get getspecies {
    return this.species;
  }
}

main() {
  // Create a list of 5 characters to choose from
  // Word list for names and species

  var randomnumber = Random();
  List<String> genders = ["m", "f"];
  List<String> species = ['orc', 'elf', 'human'];
  List<CharacterCreator> characters = [];

  for (int i = 0; i < 5; i++) {
    var character = CharacterCreator();
    character.setgender(genders[randomnumber.nextInt(2)]);
    character.setspecies(species[randomnumber.nextInt(3)]);
    character.setname(
        RpgNameGenerator.getName(character.getspecies, character.getgender));
    characters.add(character);
  }

  // Print out the list of characters
  for (int i = 0; i < 5; i++) {
    var option = i + 1;
    print("Character " +
        option.toString() +
        ": " +
        characters[i].getname +
        ", " +
        characters[i].getgender +
        ", " +
        characters[i].getspecies);
  }
}
