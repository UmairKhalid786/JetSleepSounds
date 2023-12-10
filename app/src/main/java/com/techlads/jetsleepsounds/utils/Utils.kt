package com.techlads.jetsleepsounds.utils

import android.graphics.Bitmap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


fun fastblur(sentBitmap: Bitmap, scale: Float, radius: Int): Bitmap? {
    var sentBitmap = sentBitmap
    val width = Math.round(sentBitmap.width * scale)
    val height = Math.round(sentBitmap.height * scale)
    sentBitmap = Bitmap.createScaledBitmap(sentBitmap, width, height, false)
    val bitmap = sentBitmap.copy(sentBitmap.config, true)
    if (radius < 1) {
        return null
    }
    val w = bitmap.width
    val h = bitmap.height
    val pix = IntArray(w * h)
    bitmap.getPixels(pix, 0, w, 0, 0, w, h)
    val wm = w - 1
    val hm = h - 1
    val wh = w * h
    val div = radius + radius + 1
    val r = IntArray(wh)
    val g = IntArray(wh)
    val b = IntArray(wh)
    val a = IntArray(wh)
    var rsum: Int
    var gsum: Int
    var bsum: Int
    var asum: Int
    var x: Int
    var y: Int
    var i: Int
    var p: Int
    var yp: Int
    var yi: Int
    var yw: Int
    val vmin = IntArray(max(w.toDouble(), h.toDouble()).toInt())
    var divsum = div + 1 shr 1
    divsum *= divsum
    val dv = IntArray(256 * divsum)
    i = 0
    while (i < 256 * divsum) {
        dv[i] = i / divsum
        i++
    }
    yi = 0
    yw = yi
    val stack = Array(div) {
        IntArray(
            4
        )
    }
    var stackpointer: Int
    var stackstart: Int
    var sir: IntArray
    var rbs: Int
    val r1 = radius + 1
    var routsum: Int
    var goutsum: Int
    var boutsum: Int
    var aoutsum: Int
    var rinsum: Int
    var ginsum: Int
    var binsum: Int
    var ainsum: Int
    y = 0
    while (y < h) {
        asum = 0
        bsum = asum
        gsum = bsum
        rsum = gsum
        aoutsum = rsum
        boutsum = aoutsum
        goutsum = boutsum
        routsum = goutsum
        ainsum = routsum
        binsum = ainsum
        ginsum = binsum
        rinsum = ginsum
        i = -radius
        while (i <= radius) {
            p = pix[(yi + min(wm.toDouble(), max(i.toDouble(), 0.0))).toInt()]
            sir = stack[i + radius]
            sir[0] = p and 0xff0000 shr 16
            sir[1] = p and 0x00ff00 shr 8
            sir[2] = p and 0x0000ff
            sir[3] = 0xff and (p shr 24)
            rbs = (r1 - abs(i.toDouble())).toInt()
            rsum += sir[0] * rbs
            gsum += sir[1] * rbs
            bsum += sir[2] * rbs
            asum += sir[3] * rbs
            if (i > 0) {
                rinsum += sir[0]
                ginsum += sir[1]
                binsum += sir[2]
                ainsum += sir[3]
            } else {
                routsum += sir[0]
                goutsum += sir[1]
                boutsum += sir[2]
                aoutsum += sir[3]
            }
            i++
        }
        stackpointer = radius
        x = 0
        while (x < w) {
            r[yi] = dv[rsum]
            g[yi] = dv[gsum]
            b[yi] = dv[bsum]
            a[yi] = dv[asum]
            rsum -= routsum
            gsum -= goutsum
            bsum -= boutsum
            asum -= aoutsum
            stackstart = stackpointer - radius + div
            sir = stack[stackstart % div]
            routsum -= sir[0]
            goutsum -= sir[1]
            boutsum -= sir[2]
            aoutsum -= sir[3]
            if (y == 0) {
                vmin[x] = min((x + radius + 1).toDouble(), wm.toDouble()).toInt()
            }
            p = pix[yw + vmin[x]]
            sir[0] = p and 0xff0000 shr 16
            sir[1] = p and 0x00ff00 shr 8
            sir[2] = p and 0x0000ff
            sir[3] = 0xff and (p shr 24)
            rinsum += sir[0]
            ginsum += sir[1]
            binsum += sir[2]
            ainsum += sir[3]
            rsum += rinsum
            gsum += ginsum
            bsum += binsum
            asum += ainsum
            stackpointer = (stackpointer + 1) % div
            sir = stack[stackpointer % div]
            routsum += sir[0]
            goutsum += sir[1]
            boutsum += sir[2]
            aoutsum += sir[3]
            rinsum -= sir[0]
            ginsum -= sir[1]
            binsum -= sir[2]
            ainsum -= sir[3]
            yi++
            x++
        }
        yw += w
        y++
    }
    x = 0
    while (x < w) {
        asum = 0
        bsum = asum
        gsum = bsum
        rsum = gsum
        aoutsum = rsum
        boutsum = aoutsum
        goutsum = boutsum
        routsum = goutsum
        ainsum = routsum
        binsum = ainsum
        ginsum = binsum
        rinsum = ginsum
        yp = -radius * w
        i = -radius
        while (i <= radius) {
            yi = (max(0.0, yp.toDouble()) + x).toInt()
            sir = stack[i + radius]
            sir[0] = r[yi]
            sir[1] = g[yi]
            sir[2] = b[yi]
            sir[3] = a[yi]
            rbs = (r1 - abs(i.toDouble())).toInt()
            rsum += r[yi] * rbs
            gsum += g[yi] * rbs
            bsum += b[yi] * rbs
            asum += a[yi] * rbs
            if (i > 0) {
                rinsum += sir[0]
                ginsum += sir[1]
                binsum += sir[2]
                ainsum += sir[3]
            } else {
                routsum += sir[0]
                goutsum += sir[1]
                boutsum += sir[2]
                aoutsum += sir[3]
            }
            if (i < hm) {
                yp += w
            }
            i++
        }
        yi = x
        stackpointer = radius
        y = 0
        while (y < h) {
            pix[yi] = dv[asum] shl 24 or (dv[rsum] shl 16) or (dv[gsum] shl 8) or dv[bsum]
            rsum -= routsum
            gsum -= goutsum
            bsum -= boutsum
            asum -= aoutsum
            stackstart = stackpointer - radius + div
            sir = stack[stackstart % div]
            routsum -= sir[0]
            goutsum -= sir[1]
            boutsum -= sir[2]
            aoutsum -= sir[3]
            if (x == 0) {
                vmin[y] = (min((y + r1).toDouble(), hm.toDouble()) * w).toInt()
            }
            p = x + vmin[y]
            sir[0] = r[p]
            sir[1] = g[p]
            sir[2] = b[p]
            sir[3] = a[p]
            rinsum += sir[0]
            ginsum += sir[1]
            binsum += sir[2]
            ainsum += sir[3]
            rsum += rinsum
            gsum += ginsum
            bsum += binsum
            asum += ainsum
            stackpointer = (stackpointer + 1) % div
            sir = stack[stackpointer]
            routsum += sir[0]
            goutsum += sir[1]
            boutsum += sir[2]
            aoutsum += sir[3]
            rinsum -= sir[0]
            ginsum -= sir[1]
            binsum -= sir[2]
            ainsum -= sir[3]
            yi += w
            y++
        }
        x++
    }
    bitmap.setPixels(pix, 0, w, 0, 0, w, h)
    return bitmap
}