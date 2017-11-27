import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by manager on 2017/11/27.
 */
public class ParkPayCalc {
    public final int freeMinutes = 15;			// ���ʱ��
    public final int firstHourPay = 15;			// ��һ��Сʱ�շѽ��
    public final int perHourPay = 3;			// ��һ��Сʱ�Ժ�ÿСʱ�շѽ��
    public final int dayUpperPay = 50;			// ÿ������շѽ��
    /*
     * �Ʒѹ������£����ʱ�䣺15����   ��һСʱ15Ԫ �Ժ�ÿСʱ3Ԫ ÿ�����50Ԫ
     */
    public double CalcPay( String strInTime, String strOutTime, String strParkNo ) throws ParseException {
        double dPay = 0.0f;

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date dateIn = sdf.parse( strInTime );
        Date dateOut = sdf.parse( strOutTime );
        long diff = dateOut.getTime() - dateIn.getTime();
        long minutes = diff/(1000*60);

        if( minutes < freeMinutes ) return 0.0f;
        // ��һ������������
        long days = minutes/(24*60);
        dPay += days * dayUpperPay;
        // �ڶ����� ����ʣ��ķ�����
        minutes -= days * 24 * 60;
        long hours = minutes/60;	// ������Сʱ
        long lastmin = minutes%60;	// ����ķ���
        if( lastmin > 0 ) hours += 1;
        // ÿ�����
        dPay += Math.min((hours - 1) * perHourPay + firstHourPay, dayUpperPay);

        return dPay;
    }
}
