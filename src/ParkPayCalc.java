import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by manager on 2017/11/27.
 */
public class ParkPayCalc {
    public final int freeMinutes = 15;			// 免费时间
    public final int firstHourPay = 15;			// 第一个小时收费金额
    public final int perHourPay = 3;			// 第一个小时以后每小时收费金额
    public final int dayUpperPay = 50;			// 每天最高收费金额
    /*
     * 计费规则如下：免费时间：15分钟   第一小时15元 以后每小时3元 每天最高50元
     */
    public double CalcPay( String strInTime, String strOutTime, String strParkNo ) throws ParseException {
        double dPay = 0.0f;

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date dateIn = sdf.parse( strInTime );
        Date dateOut = sdf.parse( strOutTime );
        long diff = dateOut.getTime() - dateIn.getTime();
        long minutes = diff/(1000*60);

        if( minutes < freeMinutes ) return 0.0f;
        // 第一步：计算天数
        long days = minutes/(24*60);
        dPay += days * dayUpperPay;
        // 第二步： 计算剩余的分钟数
        minutes -= days * 24 * 60;
        long hours = minutes/60;	// 计算整小时
        long lastmin = minutes%60;	// 多余的分钟
        if( lastmin > 0 ) hours += 1;
        // 每天最高
        dPay += Math.min((hours - 1) * perHourPay + firstHourPay, dayUpperPay);

        return dPay;
    }
}
