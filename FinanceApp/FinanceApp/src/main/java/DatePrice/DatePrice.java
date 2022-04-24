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



    public BigDecimal getPrice() {
        return price;
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
