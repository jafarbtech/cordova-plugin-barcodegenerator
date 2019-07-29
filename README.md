# cordova-plugin-barcode-generator
## Introduction
This project is a fork of the original project from Attendee which I would like to thank for his work, it saved me a lot of time.

The Android part of this plugin has been partially rewritten to enhences features and correct some bugs of the original project.

By now the iOS part has not been modified nor tested.

Barcode Generator is a cordova plugin that allows generation of barcodes like QRCODE, CODE128 etc.
### This plugin is generate only Barcode type QRCODE

## Supported Platforms

* Android Version >= 4.0
* iOS Version >= 8.0 (Not tested)

## Installation
First download the last stable version (or master tree) from git in a folder called 'cordova-plugins':
``````
cd cordova-plugins
git clone https://github.com/fefc/cordova-plugin-barcode-generator.git
``````
Now go to your cordova project folder and install the plugin as follows:
``````
cordova plugin add ../path-to-folder/cordova-plugins/cordova-plugin-barcode-generator
``````
If you are using ionic framework:
``````
ionic cordova plugin add ../path-to-folder/cordova-plugins/cordova-plugin-barcode-generator
``````
Done!

## Usage
### Cordova
``````
in *.js that you want to use this.
var success = function(message) { alert(message); };
var error = function(message) { alert("Oopsie! " + message); };
var barcode = cordova.require("com.attendee.barcodegenerator.BarcodeGenerator");
barcode.generate(success,error,"12345");
The plugin will return base64 string of barcode image for using in cordova.
``````
### Ionic 3
If you are using Ionic 3 you can access the plugin from your typescript code by adding the following line after your imports:
``````
declare var BarcodeGenerator: any;
``````
You can now use the BarcodeGenerator module in your code like:
``````
BarcodeGenerator.generate({
                            content: 'my barcode content',
                            height: 512,
                            width: 512,
                            foregroundColor: '#000000',
                            backgroundColor: '#FFFFFF'
                          },
                          function(base64barcode) {
                            console.log("Success!");
                          },
                          function(error) {
                            console.log("Error");
                          });
``````
