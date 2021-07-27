import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class SubmitJob {
    public static void main(String[] args) throws IOException {
        String path = "D:\\JDBCSinkPostgreSql\\target\\JDBCSinkPostgreSql-1.0.jar";

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody bodyUpload = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("jarfile", path,RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(path)))
                .build();
        Request requestUpload = new Request.Builder()
                .url("http://192.168.1.208:8081/jars/upload")
                .method("POST", bodyUpload)
                .build();
        Response responseUpload = client.newCall(requestUpload).execute();
        if (responseUpload.isSuccessful()) {
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody bodyRun = RequestBody.create(mediaType, "{\r\n    \"programArgs\": \"--input jdbc:postgresql://machdatum-demo.postgres.database.azure.com:5432;cnc_client@machdatum-demo;anand;postgres;public.datatest;`Value`_INT,`Timestamp`_BIGINT;cc5ad252-a71a-429c-b213-61d44d2a4efb;164.52.208.60:29092\"\r\n}");
            Request requestRun = new Request.Builder()
                    .url("http://192.168.1.208:8081/jars/b7f559eb-4c53-44ad-98ca-4d0373df8fb9_JDBCSinkPostgreSql-1.0.jar/run")
                    .method("POST", bodyRun)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response responseRun = client.newCall(requestRun).execute();
            if(responseRun.isSuccessful()){
                System.out.println(responseRun.message());
            }
            else
            {
                System.out.println(responseRun.message());
            }
        } else {
            System.out.println(responseUpload.code());
        }
    }
}