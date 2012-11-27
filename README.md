UnitConverter
=============

Pervasive Computing converter android app.
[Version controlled on GitHub](https://github.com/RobertShippey/UnitConverter)

##Notes

 * This project was created on a Mac and I've found Eclipse complains about a version dependency when you open the project on Windows. This is a quick fix that Eclipse will explain (Usually Android Tools -> Fix Project properties)
 * When importing this project from existing sources, I've found that it has to be imported as a regular application rather than an Android application. I'm not sure if this is an error on my part or something wrong with Eclipse, but this is how it is.

##Marking Grid
```
+------+---------------------+--------------------------------------+
|      | Item                |  Details                             |
+------+---------------------+--------------------------------------+
| 3rd  | Conversion          | Length conversion (mm, cm, in, ft)   |
|      +---------------------+--------------------------------------+
|      | User Interaction    | Buttons, text fields, spinners       |
|      +---------------------+--------------------------------------+
|      | Menu                | About + Unit selection               |
|      +---------------------+--------------------------------------+
|      | Intents             | Starting internal activities + email |
|      |                     | + website                            |
+------+---------------------+--------------------------------------+
| 2:2  | UI Elements         | Button, text views, spinner, toast,  |
|      |                     | layout,                              |
|      +---------------------+--------------------------------------+
|      | Shared Preferences  | Keeping record of which units are    |
|      |                     | selected                             |
+------+---------------------+--------------------------------------+
| 2:1  | UI Elements         |                                      |
|      +---------------------+--------------------------------------+
|      | Passing Information | Pass selected units between          |
|      |                     | activities with intents, passing     |
|      |                     | info into web + message intents on   |
|      |                     | 'About' activity                     |
+------+---------------------+--------------------------------------+
| 1st  | SQLite              | Keep conversion rates in DB to be    |
|      |                     | queried, rather than lots of if()'s, |
|      |                     | when needed                          |
|      +---------------------+--------------------------------------+
|      | Other               | Localized to German*, French, and    |
|      |                     | Spanish using localised string.xml   |
|      |                     | files (the recomended Android way)   |
+------+---------------------+--------------------------------------+
```

* = Checked by an actual native speaker.