package DatePrice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePrice {
    private Calendar time;
    private BigDecimal price;
    
    public DatePrice(Calendar time, BigDecimal price) {
        super();
        this.time = time;
        this.price = price;
    }
    
    

    public Calendar getTime() {
        return time;
    }



    public void setTime(Calendar time) {
        this.time = time;
    }
    
    public int getYear() {
    	return time.get(Calendar.YEAR);
    }
    
    public int getMonth() {
    	return time.get(Calendar.MONTH);
    }
    
    public int getDay() {
    	return time.get(Calendar.DAY_OF_MONTH);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getPriceInt() {
    	return price.intValue();
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = format.format(time.getTime());
        return "datePrice [time=" + formatDate + ", price=" + price + "]";
    }

}
