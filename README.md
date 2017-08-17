# MultiProperties
Netbeans implementation of the very useful eclipse plugin "Multiproperties" by Krisztián Zsolt Sallai.

The original eclipse plugin can be found here [Multiproperties eclipse plugin](https://github.com/skazsi/multiproperties)

The files written by the eclipse plugin (*.multiproperties) can be edited under
netbeans using this plugin.

![screenshot](https://user-images.githubusercontent.com/18146968/29413804-f7b72c64-835d-11e7-946e-fd98a173fde1.png)

## Release
This plugin is written from scratch, no code sharing with the original plugin.

Not every feature of the original plugin is implemented.

The plugin is available in the netbeans plugin portal [MultiProperties netbeans plugin](http://plugins.netbeans.org/plugin/63739/?show=true)

## Features
Some features not found in the original are implemented

- Possibility to lock down some lines (read only)
- Merge of two multiproperties file

## Compatibility with Eclipse plugin
The netbeans generated files (*.multiproperties) are compatible with the one
generated by the Eclipe plugin, but there are some limitation:

- Only the "Java Properties" and "Android" handlers are supported
- The relative path are not supported by the eclipse plugin (if you save the
  java handler with a relative path to the .multiproperties file with netbeans,
  Eclipse cannot handle it)
- Additional meta data (like if a field cannot be modified in lockdown mode) is
  lost when opened with eclipse (file format 1.3 is the version specific to
  netbeans)

## Standalone application

A stand alone version is also available which permits for non developer to start
an application and save the multiproperties files.

The standalone version implements the concept of "Session", which is a group of opened
files, so it's easy to switch from a group of file to another group of files.

A Java Web Start for the standalone version can be found at
[tools.knop-tech.com](http://tools.knop-tech.com)

## Todos

- Update eclipse plugin to implement the relative path (to the .multiproperties file)
  for handled save files
- Update eclipse plugin to implement additional meta-data (field cannot be changed
  if in lockdown mode)

## Utils

A ResourceBundle implementation is available to directly use the multiproperties
file for localization.

Example

    ResourceBundle bundle = ResourceBundle.getBundle("lsimedia/multiproperties/utils/ml", new Locale("fr"), MultiPropertiesResourceBundleControl.Control);  
    String key = bundle.getString("word_yes");  