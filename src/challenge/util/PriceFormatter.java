package challenge.util;

import java.text.DecimalFormat;

public abstract class PriceFormatter {

    public static String priceFormat(long price) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(price);
    }
}
