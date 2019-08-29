# BarcodeGenerator
Barcode Generator is a cordova plugin that allows generation of barcodes like QRCODE, CODE128 etc.

## About
This project is a fork of the original project from Attendee which I would like to thank for his work, it saved me a lot of time.

The Android part of this plugin has been partially rewritten to enhences features and correct some bugs of the original project.

A minimal browser version has been added and supports generation of QRCodes thanks to the qrcodejs library developped by davidshimjs:

https://davidshimjs.github.io/qrcodejs/

Android part is using google zxing library.

## Supported Platforms

* Android Version >= 4.0
* HTML5 browsers
* iOS Version >= 8.0 (Not tested)

### By now the iOS part has not been modified nor tested.

## Installation
First download the last stable version (or master tree) from git in a folder called 'cordova-plugins':
``````
cd cordova-plugins
git clone https://github.com/fefc/cordova-plugin-barcodegenerator.git
``````
Now go to your cordova project folder and install the plugin as follows:
``````
cordova plugin add ../path-to-folder/cordova-plugins/cordova-plugin-barcodegenerator
``````
If you are using ionic framework:
``````
ionic cordova plugin add ../path-to-folder/cordova-plugins/cordova-plugin-barcodegenerator
``````
Done!

## Features
* Content
* Height and width
* Foreground color
* Background color
* BarcodeType
  * format  0 = AZTEC
  * format  1 = CODABAR
  * format  2 = CODE_39
  * format  3 = CODE_93
  * format  4 = CODE_128
  * format  5 = DATA_MATRIX
  * format  6 = EAN_8
  * format  7 = EAN_13
  * format  8 = ITF
  * format  9 = MAXICODE
  * format 10 = PDF_417
  * format 11 = QR_CODE
  * format 12 = RSS_14
  * format 13 = RSS_EXPANDED
  * format 14 = UPC_A
  * format 15 = UPC_E
  * format 16 = UPC_EAN_EXTENSION

| Barcode Type  | Android | iOS | Browser |
| ------------- | ------------- | ------------- | ------------- |
| AZTEC  | Content Cell  | Content Cell  | Content Cell  |
| CODABAR  | Content Cell  | Content Cell  | Content Cell  |
| CODE_39  | Content Cell  | Content Cell  | Content Cell  |
| CODE_128  | Content Cell  | Content Cell  | Content Cell  |
| DATA_MATRIX  | Content Cell  | Content Cell  | Content Cell  |
| EAN_8  | Content Cell  | Content Cell  | Content Cell  |
| ITF  | Content Cell  | Content Cell  | Content Cell  |
| MAXICODE  | Content Cell  | Content Cell  | Content Cell  |
| PDF_417  | Content Cell  | Content Cell  | Content Cell  |


## Usage
### Cordova
Following code is issued from the original fork, untested
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
                            format: 11, //Set Features for desired format
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
