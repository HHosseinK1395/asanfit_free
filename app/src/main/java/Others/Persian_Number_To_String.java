package Others;

/**
 * Created by N550J on 3/18/2018.
 */

public class Persian_Number_To_String
{
    public static String GET_Number_To_PersianString(String TXT)
    {
        String RET = " ", STRVA = " ";
        String[] MainStr = STR_To_Int(TXT);
        int Q = 0;
        for (int i = MainStr.length - 1; i >= 0; i--)
        {
            STRVA = " ";
            if (RET != " " && RET != null)
                STRVA = " و ";
            RET = Convert_STR(GETCountStr(MainStr[i]), Q) + STRVA + RET;
            Q++;
        }
        if (RET == " " || RET == null || RET == "  ")
            RET = "صفر";
        return RET;
    }

    private static String[] STR_To_Int(String STR)
    {
        STR = GETCountStr(STR);
        String[] RET = new String[STR.length() / 3];
        int Q = 0;
        for (int I = 0; I < STR.length(); I += 3)
        {
            RET[Q] = STR.substring(I, 3);
            Q++;
        }
        return RET;
    }

    private static String GETCountStr(String STR)
    {
        String RET = STR;
        int LEN = (STR.length() / 3 + 1) * 3 - STR.length();
        if (LEN < 3)
        {
            for (int i = 0; i < LEN; i++)
            {
                RET = "0" + RET;
            }
        }
        if (RET == "")
            return "000";
        return RET;
    }

    private static String Convert_STR(String INT, int Count)
    {
        String RET = "";
        //یک صد
        if (Count == 0)
        {
            if (INT.substring(1, 1) == "1" && INT.substring(2, 1) != "0")
            {
                RET = GET_Number(3, Integer.parseInt(INT.substring(0, 1)), " ") + GET_Number(1, Integer.parseInt(INT.substring(2, 1)), "");
            }
            else
            {
                String STR = GET_Number(0, Integer.parseInt(INT.substring(2, 1)), "");
                RET = GET_Number(
                        3,
                        Integer.parseInt(INT.substring(0, 1)),
                        GET_Number(2, Integer.parseInt(INT.substring(1, 1)), "") + STR) +
                            GET_Number(2, Integer.parseInt(INT.substring(1, 1)), STR) +
                            GET_Number(0, Integer.parseInt(INT.substring(2, 1)), "");
            }
        }
        //هزار
        else if (Count == 1)
        {
            RET = Convert_STR(INT, 0);
            RET += " هزار";
        }
        //میلیون
        else if (Count == 2)
        {
            RET = Convert_STR(INT, 0);
            RET += " میلیون";
        }
        //میلیارد
        else if (Count == 3)
        {
            RET = Convert_STR(INT, 0);
            RET += " میلیارد";
        }
        //میلیارد
        else if (Count == 4)
        {
            RET = Convert_STR(INT, 0);
            RET += " تیلیارد";
        }
        //میلیارد
        else if (Count == 5)
        {
            RET = Convert_STR(INT, 0);
            RET += " بیلیارد";
        }
        else
        {
            RET = Convert_STR(INT, 0);
            RET += Count + "";
        }
        return RET;
    }

    private static String GET_Number(int Count, int Number, String VA)
    {
        String RET = "";

        if (VA != "" && VA != null)
        {
            VA = " و ";
        }
        if (Count == 0 || Count == 1)
        {
            boolean IsDah = (Count != 0);
            String[] MySTR = new String[10];
            MySTR[1] = IsDah ? "یازده" : "یک" + VA;
            MySTR[2] = IsDah ? "دوازده" : "دو" + VA;
            MySTR[3] = IsDah ? "سیزده" : "سه" + VA;
            MySTR[4] = IsDah ? "چهارده" : "چهار" + VA;
            MySTR[5] = IsDah ? "پانزده" : "پنج" + VA;
            MySTR[6] = IsDah ? "شانزده" : "شش" + VA;
            MySTR[7] = IsDah ? "هفده" : "هفت" + VA;
            MySTR[8] = IsDah ? "هجده" : "هشت" + VA;
            MySTR[9] = IsDah ? "نوزده" : "نه" + VA;
            return MySTR[Number];
        }
        else if (Count == 2)
        {
            String[] MySTR = new String[10];
            MySTR[1] = "ده";
            MySTR[2] = "بیست" + VA;
            MySTR[3] = "سی" + VA;
            MySTR[4] = "چهل" + VA;
            MySTR[5] = "پنجاه" + VA;
            MySTR[6] = "شصت" + VA;
            MySTR[7] = "هفتاد" + VA;
            MySTR[8] = "هشتاد" + VA;
            MySTR[9] = "نود" + VA;
            return MySTR[Number];
        }
        else if (Count == 3)
        {
            String[] MySTR = new String[10];
            MySTR[1] = "یکصد" + VA;
            MySTR[2] = "دویست" + VA;
            MySTR[3] = "سیصد" + VA;
            MySTR[4] = "چهارصد" + VA;
            MySTR[5] = "پانصد" + VA;
            MySTR[6] = "ششصد" + VA;
            MySTR[7] = "هفتصد" + VA;
            MySTR[8] = "هشتصد" + VA;
            MySTR[9] = "نهصد" + VA;
            return MySTR[Number];
        }
        return RET;
    }
}
