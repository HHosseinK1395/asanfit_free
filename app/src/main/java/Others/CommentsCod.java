package Others;

/**
 * Created by N550J on 2/25/2018.
 */

public class CommentsCod {
//    @Override
//    protected Map<String, String> getParams() throws AuthFailureError {
//        Map<String, String> myParams = new HashMap<String, String>();
//        myParams.put("name", "customer");
//        myParams.put("surname", "customer");
//        myParams.put("userName", "customer");
//        myParams.put("gender", "0");
//        myParams.put("emailAddress", "me@gmail.com");
//        myParams.put("phoneNumber",  "customer");
//        myParams.put("referedByCode", "string");
//        myParams.put("password", "123456");
//        myParams.put("captchaResponse", "string");
//        myParams.put("tenancyName", "default");
//        return myParams;
//    }


//    @Override
//    public byte[] getBody()
//    {
//
//        return params.toString().getBytes();
//        try {
//            return params.toString().getBytes("utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        Map<String, String> myParams = null;
//        try {
//            myParams = getParams();
//        } catch (AuthFailureError authFailureError) {
//            authFailureError.printStackTrace();
//        }
//        if (myParams != null && myParams.size() > 0) {
//            return encodeParameters(myParams, getParamsEncoding());
//        }
//        return null;
//    }

//    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
//        StringBuilder encodedParams = new StringBuilder();
//        try {
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
//                encodedParams.append('=');
//                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
//                encodedParams.append('&');
//            }
//
//            String str  = encodedParams.toString().substring(0, encodedParams.toString().length() - 1);
//            str = new StringBuffer(str).insert(0, "?").toString();
//            return str.getBytes(paramsEncoding);
//        } catch (UnsupportedEncodingException uee) {
//            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
//        }
//    }
//
//    public static String makePostRequest(String stringUrl, String payload,
//                                         Context context) throws IOException {
//        URL url = new URL(stringUrl);
//        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
//        String line;
//        StringBuffer jsonString = new StringBuffer();
//
//        uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//        uc.setRequestMethod("POST");
//        uc.setDoInput(true);
//        uc.setInstanceFollowRedirects(false);
//        uc.connect();
//        OutputStreamWriter writer = new OutputStreamWriter(uc.getOutputStream(), "UTF-8");
//        writer.write(payload);
//        writer.close();
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//            while((line = br.readLine()) != null){
//                jsonString.append(line);
//            }
//            br.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        uc.disconnect();
//        return jsonString.toString();
//    }

}
