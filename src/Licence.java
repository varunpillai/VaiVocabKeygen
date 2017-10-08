import com.twmacinta.util.*;
import org.apache.commons.codec.digest.*;

public class Licence
{
  private static String num2chars(char[] paramArrayOfChar)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfChar.length)
        return new String(paramArrayOfChar);
      if ((paramArrayOfChar[i] >= '0') && (paramArrayOfChar[i] <= '9'))
        paramArrayOfChar[i] = ((char)(65 + ('￐' + paramArrayOfChar[i])));
    }
  }

  public static boolean checkkey2(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      new String(paramString1).toUpperCase();
      new String(paramString2).toUpperCase();
      String str1 = new String("");
//      String str2 = md5(paramString1);
	    MD5 md = new MD5(paramString1.getBytes());

	    String euid = MD5.toHex(md.doFinal());
//	    System.out.println("MD 5  string euid: " + euid);
	    
//      System.out.println("str2:" + euid);
//      Log.d("encoded user id", str2);
//      String str3 = md5(paramString2);
	    md = new MD5(paramString2.getBytes());

	    String eappid = MD5.toHex(md.doFinal());
//	    System.out.println("MD 5  string eappid: " + eappid);
      
      int i = 0;
      int j = euid.length();
      while (true)
      {
        if (i >= j)
        {
          String str4 = str1.toUpperCase();
//          System.out.println(euid);        
//          System.out.println("str4:"+str4);          
//          Log.d("CalculatedKey", str4);
          String str5 = num2chars(str4.toCharArray());
          System.out.println("UNLOCK CODE FOR ANDROID:" +str5);          
//          Log.d("CalculatedKey after n2c ", str5);
          if (str5.compareToIgnoreCase(paramString3) != 0)
            break;
          return true;
        }
        String str6 = str1 + euid.substring(i, i + 1);
//        System.out.println("Key after UID ID"+str6);
        str1 = str6 + eappid.substring(i, i + 1);
//        System.out.println("Key after APP ID" +str1);
        i += 4;
      }
    }
    catch (Exception localException)
    {
    	System.out.println("CheckKey Got exception " + localException);
    }
    return false;
  }
  
  public static void getUnlockCode(String appIdUser, String machineIdUser, String versionNum)
  {
      String str4 = DigestUtils.sha512Hex(appIdUser.toUpperCase()).toUpperCase();
      String str5 = DigestUtils.sha512Hex(machineIdUser.toUpperCase()).toUpperCase(); 
      String str3 = DigestUtils.sha512Hex((str4 + str5).toUpperCase()).toUpperCase();
      String str7 = DigestUtils.sha512Hex(versionNum.toUpperCase()).toUpperCase();
      String str6 = DigestUtils.sha512Hex((str7 + str3).toUpperCase()).toUpperCase();
//      String str = "hashappIdUser is:" + str4 + "\r\nhashmachineIdUser:" + str5 + "\r\nhashAppIdAndMachineId:\r\nhashAppIdAndMachineId:" + str3 + "\r\nhashVersionNo:" + 
//  str7 + "\r\nhashUnlockCode:" + str6;
//      System.out.println(str);
      System.out.println("UNLOCK CODE FOR PC: "+Rot13(str6.substring(0, 0x10).toUpperCase()));
  }
  
  public static String Rot13(String value)
  {
      int num2 = 0x61;
      int num4 = 0x7a;
      int num3 = 0x6d;
      int num5 = 0x41;
      int num7 = 90;
      int num6 = 0x4d;
      char[] chArray = value.toCharArray();
      int num9 = chArray.length - 1;
      for (int i = 0; i <= num9; i++)
      {
          int charCode = (int)chArray[i];        		  

          if ((charCode >= num2) && (charCode <= num4))
          {
              if (charCode > num3)
              {
                  charCode -= 13;
              }
              else
              {
                  charCode += 13;
              }
          }
          else if ((charCode >= num5) && (charCode <= num7))
          {
              if (charCode > num6)
              {
                  charCode -= 13;
              }
              else
              {
                  charCode += 13;
              }
          }
          chArray[i] = (char)charCode;
      }
      return new String(chArray);
  }

  public static void valid_register(String userid, String mcode, String unlock)
  {
    userid = userid.toUpperCase();
    userid = userid.trim();
    mcode = mcode.trim();
    unlock = unlock.trim();

//    System.out.println("user id " + userid);
    MD5 md = new MD5(userid.getBytes());

    String euid = MD5.toHex(md.doFinal());
//    System.out.println("MD 5  string euid: " + euid);

    mcode = mcode.toUpperCase();
//    System.out.println("Machine code : " + mcode);
    md = new MD5(mcode.getBytes());

    String eappid = MD5.toHex(md.doFinal());

//    System.out.println("MD 5  string : " + eappid);
    unlock = unlock.toUpperCase();
    String calulateKey = "";
    int cnt = 0; int len = euid.length();
    while (cnt < len) {
      calulateKey = calulateKey + euid.substring(cnt, cnt + 1);
//      System.out.println("Key after UID ID" + calulateKey);
      calulateKey = calulateKey + eappid.substring(cnt, cnt + 1);
//      System.out.println("Key after APP ID" + calulateKey);
      cnt += 4;
    }
    calulateKey = calulateKey.toUpperCase();
    calulateKey = num2char(calulateKey.toCharArray());
    System.out.println("UNLOCK CODE FOR JAVA: " + calulateKey);
  }

  private static String num2char(char[] c)
  {
    for (int i = 0; i < c.length; i++) {
      if ((c[i] >= '0') && (c[i] <= '9'))
      {
        c[i] = ((char)(c[i] - '0' + 65));
      }
    }
    String str = new String(c);
    return str;
  }

	public static void main(String[] args) {
		
//for java 		
		valid_register("XGF46302".toLowerCase(), "JA_AFGA".toLowerCase(), "");

//for android		
//		checkkey2("XGF46302","AND_357932006612603","");
		checkkey2("XGF46302","AND_352274011892796","");

//for pc		
		getUnlockCode("XGF46302","738A4D05E5AB","V123");
	}

}