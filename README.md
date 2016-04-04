# MultiProperties
Netbeans implementation of the very useful Eclipse plugin "Multiproperties" by Krisztián Zsolt Sallai.

The original Eclipse plugin can be found here https://github.com/skazsi/multiproperties

The files written by the eclipse plugin (*.multiproperties) can be edited under
netbeans using this plugin.

## Release
Beta release

The implementation is not yet finished, so you should not use this plugin in a
production environment at the moment...

This plugin is written from scratch, no code sharing with the original plugin.

Not every features of the orginal plugin is implemented

## Compatibility with Eclipse plugin
The netbeans generated files (*.multiproperties) plugin is compatible with the one
generated by the Eclipe plugin, but there are some limitation:

- Only the "Java Properties Handler" is supported
- The relative path are not supported by the Eclipse plugin (if you save the
  java handler with a relative path to the .multiproperties file with netbeans, Eclipse cannot handle it)

## Standalone application

A stand alone version is also available which permit for non developper to start
an application and save the multiproperties files.

The standalone versionimplements the concept of "Session", which is a group of opened
files, so it's easy to switch from a group of file to another group of files.