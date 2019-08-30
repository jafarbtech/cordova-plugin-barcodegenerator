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

## Parameters
| Parameter | Description | Default value |
| --------- | ----------- | ------------- |
| content | String that will be encoded as a barcode | DefaultBarcode |
| height  | Height in pixels | 128 |
| width   | Width in pixels  | 128 |
| foregroundColor | Color of the bars in HTML | #000000 |
| backgroundColor | Background color in HTML  | #FFFFFF |
| format  | Desired barcode type   | 11 |

## Supported barcodes
| Barcode Type | Enum value | Android | iOS | Browser |
| ------------ | ---------- | ------------- | ------------- | ------------- |
| AZTEC             |  0 | :heavy_check_mark:  | :grey_question: | :x: |
| CODABAR           |  1 | :heavy_check_mark:  | :grey_question: | :x: |
| CODE_39           |  2 | :heavy_check_mark:  | :grey_question: | :x: |
| CODE_93           |  3 | :heavy_check_mark:  | :grey_question: | :x: |
| CODE_128          |  4 | :heavy_check_mark:  | :grey_question: | :x: |
| DATA_MATRIX       |  5 | :heavy_check_mark:  | :grey_question: | :x: |
| EAN_8             |  6 | :heavy_check_mark:  | :grey_question: | :x: |
| EAN_13            |  7 | :heavy_check_mark:  | :grey_question: | :x: |
| ITF               |  8 | :heavy_check_mark:  | :grey_question: | :x: |
| MAXICODE          |  9 | :heavy_check_mark:  | :grey_question: | :x: |
| PDF_417           | 10 | :heavy_check_mark:  | :grey_question: | :x: |
| QR_CODE           | 11 | :heavy_check_mark:  | :grey_question: | :heavy_check_mark: |
| RSS_14            | 12 | :heavy_check_mark:  | :grey_question: | :x: |
| RSS_EXPANDED      | 13 | :heavy_check_mark:  | :grey_question: | :x: |
| UPC_A             | 14 | :heavy_check_mark:  | :grey_question: | :x: |
| UPC_E             | 15 | :heavy_check_mark:  | :grey_question: | :x: |
| UPC_EAN_EXTENSION | 16 | :heavy_check_mark:  | :grey_question: | :x: |




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
