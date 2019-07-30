package com.attendee.barcodegenerator;

import android.graphics.Bitmap;
import android.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.EnumMap;
import java.util.Map;

import static android.graphics.Color.parseColor;

/**
 * Created by Olipsist on 3/29/16 AD.
 */
public class BarcodeGenerator extends CordovaPlugin {
    private static final String ACTION_GENERATE = "generate";

    private static final String OPT_CONTENT = "content";
    private static final String OPT_HEIGHT = "height";
    private static final String OPT_WIDTH = "width";
    private static final String OPT_FOREGROUND_COLOR = "foregroundColor";
    private static final String OPT_BACKGROUND_COLOR = "backgroundColor";
    private static final String OPT_FORMAT = "format";

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    private static final BarcodeFormat[] BARCODE_FORMATS = {
            BarcodeFormat.AZTEC,            // 0
            BarcodeFormat.CODABAR,          // 1
            BarcodeFormat.CODE_39,          // 2
            BarcodeFormat.CODE_93,          // 3
            BarcodeFormat.CODE_128,         // 4
            BarcodeFormat.DATA_MATRIX,      // 5
            BarcodeFormat.EAN_8,            // 6
            BarcodeFormat.EAN_13,           // 7
            BarcodeFormat.ITF,              // 8
            BarcodeFormat.MAXICODE,         // 9
            BarcodeFormat.PDF_417,          // 10
            BarcodeFormat.QR_CODE,          // 11
            BarcodeFormat.RSS_14,           // 12
            BarcodeFormat.RSS_EXPANDED,     // 13
            BarcodeFormat.UPC_A,            // 14
            BarcodeFormat.UPC_E,            // 15
            BarcodeFormat.UPC_EAN_EXTENSION // 16
        };

    @Override
    public boolean execute(String action, JSONArray inputs, CallbackContext callbackContext) throws JSONException {
        PluginResult result = null;

        if (ACTION_GENERATE.equals(action)){
            result = generate(inputs, callbackContext);
        }

        if(result != null) callbackContext.sendPluginResult( result );

        return false;
    }

    private PluginResult generate(JSONArray inputs, CallbackContext callbackContext) {
        JSONObject options = inputs.optJSONObject(0);

        if(options == null) return null;

        String content = options.optString(OPT_CONTENT, "BarcodeGenerator");
        int height = options.optInt(OPT_HEIGHT, 512);
        int width = options.optInt(OPT_WIDTH, 512);
        int color = BLACK;
        int bgColor = WHITE;
        int format = options.optInt(OPT_FORMAT, 11);

        if (format < 0 || format > BARCODE_FORMATS.length -1) {
            format = 11;
        }

        try {
            color = parseColor(options.optString(OPT_FOREGROUND_COLOR, "#000000"));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            bgColor = parseColor(options.optString(OPT_BACKGROUND_COLOR, "#FFFFFF"));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            callbackContext.success(this.createBarcode(content, height, width, color, bgColor, BARCODE_FORMATS[format]));
        } catch (WriterException e) {
            e.printStackTrace();
            callbackContext.error("Error in createBarcode function BarcodeGenerator.java");
        }

        return null;
    }

    private String createBarcode(String data, int height, int width, int color, int bgColor, BarcodeFormat format) throws WriterException {

        //https://stackoverflow.com/questions/43768886/android-qr-bitmap-need-help-to-remove-margin
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.MARGIN, 0);

        BitMatrix barcode = new MultiFormatWriter().encode(data, format, width, height, hints);

        int barcodeWidth = barcode.getWidth();
        int barcodeHeight = barcode.getHeight();

        int[] pixels = new int[barcodeWidth * barcodeHeight];

        for (int y = 0; y < barcodeHeight; y++) {
            int offset = y * barcodeWidth;

            for (int x = 0; x < barcodeWidth; x++) {
                pixels[offset + x] = barcode.get(x, y) ? color : bgColor;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(barcodeWidth, barcodeHeight, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, barcodeWidth, 0, 0, barcodeWidth, barcodeHeight);

        String base64Image =  bitmapToBase64(bitmap);

        if (data != null && data.length() > 0 && base64Image.length() > 0){
            return base64Image;
        }

        return null;
    }


    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

}
